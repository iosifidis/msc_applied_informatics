# Σημειώσεις για το Docker

## Εισαγωγή
Το Docker είναι ένα εργαλείο που επιτρέπει τη συσκευασία και εκτέλεση εφαρμογών σε απομονωμένα περιβάλλοντα που ονομάζονται containers.

### Πλεονεκτήματα του Docker:
- Ελαφρύ.
- Ασφαλές.
- Αυξάνει την παραγωγικότητα των προγραμματιστών.
- Βελτιώνει την αποδοτικότητα πόρων.

## Docker και Εικονικές Μηχανές (VM)
Το Docker διαφέρει από τις Εικονικές Μηχανές καθώς τα containers χρησιμοποιούν το ίδιο λειτουργικό σύστημα, κάνοντάς τα πιο ελαφριά και γρήγορα.

## Στοιχεία του Docker
- **Docker Desktop:** Εργαλείο για την εγκατάσταση και διαχείριση του Docker τοπικά.
- **Docker Hub:** Αποθετήριο για εικόνες Docker.
- **Dockerfile:** Αρχείο ρυθμίσεων για τη δημιουργία Docker images.
- **docker-compose:** Εργαλείο για τον ορισμό και την εκτέλεση πολλαπλών containers.

## Βασική Διαδικασία Εργασίας
1. **Δημιουργία Dockerfile:** Δημιουργήστε ένα αρχείο `Dockerfile` που περιγράφει το περιβάλλον και τις εντολές για το container.
   ```dockerfile
   FROM adoptopenjdk:16-jre-hotspot
   COPY target/*.jar app.jar
   ENTRYPOINT ["java", "-jar", "/app.jar"]
   ```
2. **Δημιουργία εικόνας:**
   ```bash
   docker build . --tag username/test
   ```
3. **Εκτέλεση container:**
   ```bash
   docker run --name test -p 8081:8080 username/test
   ```
4. **Διακοπή και επανεκκίνηση του container:**
   ```bash
   docker stop test
   docker start test
   ```
5. **Προώθηση της εικόνας στο Docker Hub:**
   ```bash
   docker push username/test
   ```
6. **Ανάκτηση εικόνας από το Docker Hub:**
   ```bash
   docker pull username/test
   ```

## Βασικές Εντολές Docker
- **Δημιουργία εικόνας:**
  ```bash
  docker build . --tag image_name
  ```
- **Εκτέλεση container:**
  ```bash
  docker run --name container_name -p host_port:container_port image_name
  ```
- **Λίστα των containers:**
  - Ενεργά containers:
    ```bash
    docker ps
    ```
  - Όλα τα containers:
    ```bash
    docker ps -a
    ```
- **Διακοπή container:**
  ```bash
  docker stop container_name
  ```
- **Εκτέλεση εντολής σε container:**
  ```bash
  docker exec container_name sh
  ```
- **Διαγραφή container:**
  ```bash
  docker rm container_name
  ```

## Docker Compose
Το `docker-compose` επιτρέπει τον ορισμό πολλαπλών services σε ένα αρχείο `docker-compose.yml` και την εκτέλεσή τους με μία εντολή:

```yaml
version: '3'
services:
  app:
    build: .
    ports:
      - "8081:8080"
```

Εκτέλεση όλων των services:
```bash
docker-compose up
```

## Προτεινόμενη Άσκηση
1. Δημιουργήστε ένα αρχείο `Dockerfile` για μια εφαρμογή.
2. Δημιουργήστε την εικόνα με `docker build`.
3. Εκτελέστε την εφαρμογή σε container.
4. Χρησιμοποιήστε το `docker-compose` για να τρέξετε πολλαπλά containers.

