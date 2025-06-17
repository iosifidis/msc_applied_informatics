const { MongoClient } = require('mongodb');
const turf = require('@turf/turf');

const uri = 'mongodb://localhost:27017';
const dbName = 'greece_db';

const runQueries = async (client) => {
    const db = client.db(dbName);

    console.log('--- Starting MongoDB Queries ---');

    try {
        /////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////////////////////////////
        // Query 0a: Νομοί που έχουν οικισμούς που το όνομά τους ξεκινά από Ω
        /////////////////////////////////////////////////////////////////////////////////////////////////
        console.log('\nQuery 0a: Nomoi that have oikismous whose name begins with Ω');
        const nomoi = await db.collection('nomoi').find().toArray();
        const result0a = [];
        for (const nomos of nomoi) {
            const count = await db.collection('oikismoi').countDocuments({
                'properties.name_oik': { $regex: '^Ω', $options: 'i' },
                geometry: { $geoWithin: { $geometry: nomos.geometry } }
            });
            if (count > 0) {
                result0a.push(nomos.properties.name_gr);
            }
        }
        console.log('\nQuery 0a result:', result0a);

        /////////////////////////////////////////////////////////////////////////////////////////////////
        // Query 0b: Πρωτεύουσες που απέχουν ως 10χλμ από τον ποταμό Αλιάκμονα
        ////////////////////////////////////////////////////////////////////////////////////////////////

        console.log('\nQuery 0b: Cities within 10km of Aliakmonas river');
        const aliakmonasQueryField = "properties.name";
        const aliakmonasRegex = /ΑΛΙΑΚΜΩΝ/i;

        const aliakmonasSegments = await db.collection('potamoi').find(
            { [aliakmonasQueryField]: aliakmonasRegex }
        ).toArray();

        let cityNamesForQuery0bTurf = [];

        if (aliakmonasSegments.length === 0) {
            console.log(`No parts found for Aliakmonas with the query: {"${aliakmonasQueryField}": ${aliakmonasRegex.toString()}}`);
        } else {
            let riverGeometryToBuffer;
            const allCoordinates = [];
            aliakmonasSegments.forEach(segment => {
                if (segment.geometry) {
                    if (segment.geometry.type === "LineString") {
                        allCoordinates.push(segment.geometry.coordinates);
                    } else if (segment.geometry.type === "MultiLineString") {
                        segment.geometry.coordinates.forEach(lineCoords => allCoordinates.push(lineCoords));
                    }
                }
            });

            if (allCoordinates.length > 0) {
                riverGeometryToBuffer = turf.multiLineString(allCoordinates); // Δημιουργεί ένα turf Feature<MultiLineString>

                const bufferOptions = { units: 'kilometers' };
                const bufferedRiver = turf.buffer(riverGeometryToBuffer, 10, bufferOptions); // 10 χλμ buffer

                if (bufferedRiver && bufferedRiver.geometry) {
                    const citiesInBufferedArea = await db.collection('poleis').find({
                        geometry: {
                            $geoWithin: {
                                $geometry: bufferedRiver.geometry // Η γεωμετρία του buffer polygon
                            }
                        }
                    }).toArray();

                    const uniqueCityNamesTurf = new Set();
                    citiesInBufferedArea.forEach(city => {
                        const cityName = city.properties && (city.properties.onoma || city.properties.name_gr || city.properties.NAME);
                        if (cityName && !uniqueCityNamesTurf.has(cityName)) {
                            cityNamesForQuery0bTurf.push(cityName);
                            uniqueCityNamesTurf.add(cityName);
                        }
                    });
                } else {
                    console.log('Failure to create a buffer for Aliakmonas.');
                }
            } else {
                console.log('No valid geometries were found for the Aliakmon for buffer creation.');
            }
        }

        console.log('\nQuery 0b result:', cityNamesForQuery0bTurf);
  
        /////////////////////////////////////////////////////////////////////////////////////////////////
        // Query 1: Νομοί από τους οποίους περνά το δίκτυο του ΟΣΕ
        /////////////////////////////////////////////////////////////////////////////////////////////////
        console.log('\nQuery 1: Nomoi through which the OSE network passes');
        const result1 = [];
        for (const nomos of nomoi) {
            const count = await db.collection('sid_diktio').countDocuments({
                geometry: { $geoIntersects: { $geometry: nomos.geometry } }
            });
            if (count > 0) {
                result1.push(nomos.properties.name_gr);
            }
        }
        console.log('\nQuery 1 result:', result1);

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // Query 2: Νομοί με τους οποίους συνορεύει ο νομός Θεσσαλονίκης και έχουν στο έδαφός τους λίμνη
        //////////////////////////////////////////////////////////////////////////////////////////////////
        console.log('\nQuery 2: Nomoi bordering Thessaloniki and having a lake');

        const thessalonikiNomos = await db.collection('nomoi').findOne({
            "properties.name_gr": "Ν. ΘΕΣΣΑΛΟΝΙΚΗΣ"
        });

        if (!thessalonikiNomos || !thessalonikiNomos.geometry) {
            console.error('Query 2 Error: The Nomos of Thessaloniki was not found in the “nomoi” collection or has no geometric data.');
            console.log('Query 2 result:', []);
        } else {
            // console.log(`DEBUG Query 2: Nomos Thessalonikis found. Geometry type: ${thessalonikiNomos.geometry.type}`);

            let geometryForIntersection = thessalonikiNomos.geometry;

            try {
                const bufferedThess = turf.buffer(thessalonikiNomos.geometry, 0.001, { units: 'kilometers' });
                if (bufferedThess && bufferedThess.geometry) {
                    geometryForIntersection = bufferedThess.geometry;
                }
            } catch (turfError) {
               // console.error("DEBUG Query 2: Error when creating buffer with Turf.js:“, turfError.message, ”The original geometry is used.");
            }

            const borderingNomoi = await db.collection('nomoi').find({
                "properties.name_gr": { $ne: "Ν. ΘΕΣΣΑΛΟΝΙΚΗΣ" },
                geometry: {
                    $geoIntersects: {
                        $geometry: geometryForIntersection
                    }
                }
            }).project({ "properties.name_gr": 1, geometry: 1, _id: 0 }).toArray();

            // console.log(`DEBUG Query 2: Found ${borderingNomoi.length} bordering nomoi:`, borderingNomoi.map(n => n.properties.name_gr));

            const resultQuery2 = [];
            for (const nomos of borderingNomoi) {
                if (!nomos.geometry) continue;

                const lakeExists = await db.collection('limnes').findOne({
                    geometry: {
                        $geoIntersects: {
                            $geometry: nomos.geometry
                        }
                    }
                }, { projection: { _id: 1 } });

                if (lakeExists) {
                    resultQuery2.push(nomos.properties.name_gr);
                }
            }
            console.log('Query 2 result:', resultQuery2);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        // Query 3: Νομοί που δεν έχουν αεροδρόμιο
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        console.log('\nQuery 3: Nomoi without airports');
        const result3 = [];
        for (const nomos of nomoi) {
            const count = await db.collection('aerodromia').countDocuments({
                geometry: { $geoWithin: { $geometry: nomos.geometry } }
            });
            if (count === 0) {
                result3.push(nomos.properties.name_gr);
            }
        }
        console.log('\nQuery 3 result:', result3);

        ////////////////////////////////////////////////////////////////////////////////////////////////////


        
   /////////////////////////////////////////////////////////////////////////////////////////////////
    } catch (err) {
        console.error('Error running queries:', err);
    }
};

const main = async () => {
    const client = new MongoClient(uri);

    try {
        await client.connect();
        console.log('Connected to MongoDB');
        await runQueries(client);
    } catch (err) {
        console.error('Error connecting to MongoDB:', err);
    } finally {
        await client.close();
        console.log('MongoDB connection closed');
    }
};

main();
