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
        const aliakmonasSegments = await db.collection('potamoi').find(
            { "properties.name": /ΑΛΙΑΚΜΩΝ/i }
        ).toArray();

        const cityNamesNearAliakmonas = new Set();

        for (const seg of aliakmonasSegments) {
            if (!seg.geometry) continue;
            let lines = [];
            if (seg.geometry.type === "LineString") {
                lines = [seg.geometry.coordinates];
            } else if (seg.geometry.type === "MultiLineString") {
                lines = seg.geometry.coordinates;
            }
            for (const line of lines) {
                for (const pointCoords of line) {
                    const point = {
                        type: "Point",
                        coordinates: pointCoords
                    };
                    const cities = await db.collection('poleis').find({
                        geometry: {
                            $nearSphere: {
                                $geometry: point,
                                $maxDistance: 10000 // 10km
                            }
                        }
                    }).toArray();
                    for (const city of cities) {
                        const cityName = city.properties && (city.properties.onoma || city.properties.name_gr || city.properties.NAME);
                        if (cityName) cityNamesNearAliakmonas.add(cityName);
                    }
                }
            }
        }

        const result0b = Array.from(cityNamesNearAliakmonas);
        console.log('\nQuery 0b result:', result0b);


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

        const thessalonikiNomosBordering = await db.collection('nomoi').findOne({
            "properties.name_gr": "Ν. ΘΕΣΣΑΛΟΝΙΚΗΣ"
        });

        if (!thessalonikiNomosBordering || !thessalonikiNomosBordering.geometry) {
            console.log('Query 2 (bordering only) result:', []);
        } else {
            // Εφάρμοσε μικρό buffer στη γεωμετρία της Θεσσαλονίκης
            let geometryForIntersection = thessalonikiNomosBordering.geometry;
            try {
                const buffered = turf.buffer(thessalonikiNomosBordering.geometry, 0.001, { units: 'kilometers' });
                if (buffered && buffered.geometry) {
                    geometryForIntersection = buffered.geometry;
                }
            } catch (e) {
                // Αν αποτύχει το buffer, χρησιμοποίησε την αρχική γεωμετρία
            }

            const borders = await db.collection('nomoi').find({
                "properties.name_gr": { $ne: thessalonikiNomosBordering.properties.name_gr },
                geometry: {
                    $geoIntersects: {
                        $geometry: geometryForIntersection
                    }
                }
            }).toArray();

            const resultQuery2Bordering = [];
            for (const nomos of borders) {
                if (!nomos.geometry) continue;
                const lakeExists = await db.collection('limnes').findOne({
                    geometry: {
                        $geoIntersects: {
                            $geometry: nomos.geometry
                        }
                    }
                }, { projection: { _id: 1 } });

                if (lakeExists) {
                    resultQuery2Bordering.push(nomos.properties.name_gr);
                }
            }
            console.log('\nQuery 2 result:', resultQuery2Bordering);
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
