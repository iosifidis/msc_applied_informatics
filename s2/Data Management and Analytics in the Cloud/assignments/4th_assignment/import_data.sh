#!/bin/bash

# Prompt the user to input the absolute path of the JSON data
read -p "Enter the absolute path of the directory containing the JSON files: " json_data_path

# Prompt the user to input the name of the MongoDB container
read -p "Enter the name of the MongoDB container: " container_name

# Copy JSON files to the container
docker cp "$json_data_path/aerodromia.geojsonl.json" "$container_name:/data/aerodromia.geojsonl.json"
docker cp "$json_data_path/iso_metadata.geojsonl.json" "$container_name:/data/iso_metadata.geojsonl.json"
docker cp "$json_data_path/limnes.geojsonl.json" "$container_name:/data/limnes.geojsonl.json"
docker cp "$json_data_path/nomoi.geojsonl.json" "$container_name:/data/nomoi.geojsonl.json"
docker cp "$json_data_path/oikismoi.geojsonl.json" "$container_name:/data/oikismoi.geojsonl.json"
docker cp "$json_data_path/poleis.geojsonl.json" "$container_name:/data/poleis.geojsonl.json"
docker cp "$json_data_path/potamoi.geojsonl.json" "$container_name:/data/potamoi.geojsonl.json"
docker cp "$json_data_path/sid_diktio.geojsonl.json" "$container_name:/data/sid_diktio.geojsonl.json"

# Import JSON files into MongoDB
docker exec "$container_name" mongoimport --db mydatabase --collection aerodromia --file /data/aerodromia.geojsonl.json
docker exec "$container_name" mongoimport --db mydatabase --collection iso_metadata --file /data/iso_metadata.geojsonl.json
docker exec "$container_name" mongoimport --db mydatabase --collection limnes --file /data/limnes.geojsonl.json
docker exec "$container_name" mongoimport --db mydatabase --collection nomoi --file /data/nomoi.geojsonl.json
docker exec "$container_name" mongoimport --db mydatabase --collection oikismoi --file /data/oikismoi.geojsonl.json
docker exec "$container_name" mongoimport --db mydatabase --collection poleis --file /data/poleis.geojsonl.json
docker exec "$container_name" mongoimport --db mydatabase --collection potamoi --file /data/potamoi.geojsonl.json
docker exec "$container_name" mongoimport --db mydatabase --collection sid_diktio --file /data/sid_diktio.geojsonl.json
