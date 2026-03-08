# 🐾 PIMS - Practice Information Management System (Σύστημα Διαχείρισης Κτηνιατρείου)

Αυτό το αποθετήριο περιέχει τον πηγαίο κώδικα για το Πληροφοριακό Σύστημα Διαχείρισης Κτηνιατρείου (PIMS), το οποίο αναπτύχθηκε στο πλαίσιο Διπλωματικής Εργασίας του Προγράμματος Μεταπτυχιακών Σπουδών του Τμήματος Εφαρμοσμένης Πληροφορικής, στο Πανεπιστήμιο Μακεδονίας.

## 📌 Θέμα Διπλωματικής
**Ανάπτυξη και Αξιολόγηση Πληροφοριακού Συστήματος (Διαχείριση Κτηνιατρείου) με χρήση βοηθών παραγωγής κώδικα Τεχνητής Νοημοσύνης: Μια μελέτη περίπτωσης εστιασμένη στην αξιολόγηση της ποιότητας κώδικα και τη στρατηγική διαδικασία Prompting**.

## 🛠️ Τεχνολογίες (Tech Stack)
* **Μοντέλο AI:** Google Gemini 3 Pro
* **Backend:** Spring Boot (Java 21), REST API, Spring Security (JWT)
* **Frontend:** React.js (Single Page Application)
* **Βάση Δεδομένων:** PostgreSQL (Dockerized)
* **Εργαλεία Αξιολόγησης:** SonarQube (Στατική Ανάλυση Κώδικα), Google Lighthouse (Μετρήσεις Απόδοσης)

## 📊 Βασικά Αποτελέσματα
Η χρήση του Gemini 3 Pro ως "συνεργάτη" στην ανάπτυξη (AI-Assisted Development) ανέδειξε τα εξής:
* **Ταχύτητα Ανάπτυξης:** Μείωση του χρόνου ανάπτυξης κατά 60-70%.
* **Ποιότητα Κώδικα (Backend):** Υψηλή αξιοπιστία (Reliability Rating: A, 0 Bugs) και συντηρησιμότητα (Maintainability: A). Ωστόσο, προέκυψαν ζητήματα ασφαλείας (Security Rating: E) εξαιτίας hardcoded κωδικών.
* **Ποιότητα Frontend:** Άριστη προσβασιμότητα (Accessibility Score: 96/100). Παρόλα αυτά, υπήρξε υψηλό ποσοστό επανάληψης κώδικα (Duplication ~39.4%), γεγονός που δείχνει ότι η ΤΝ έλυνε προβλήματα ad-hoc αντί να δημιουργεί αφαιρετικές δομές.
* **Απόδοση ΑΙ:** 50% επιτυχία με την πρώτη προσπάθεια (One-shot success) σε μεμονωμένες εργασίες (όπως Entities, DTOs) και 50% ανάγκη για επαναληπτικές διορθώσεις (Iterative fixes) σε πολύπλοκα προβλήματα ενσωμάτωσης (όπως CORS και JWT flow).
* **Ο Ρόλος του Προγραμματιστή:** Ο άνθρωπος-προγραμματιστής παραμένει απαραίτητος για την επιβολή της αρχιτεκτονικής συνοχής, την ασφάλεια και την αποσφαλμάτωση, αναλαμβάνοντας πλέον έναν ρόλο "Ορχηστρωτή / Software Architect". 

## Βαθμός: 9,4

## 🔗 Αποθετήριο Ανάπτυξης
Ο κώδικας του έργου φιλοξενείται στο εξής αποθετήριο:
[https://github.com/iosifidis/msc-pims](https://github.com/iosifidis/msc-pims)

---

# 🐾 PIMS - Practice Information Management System

This repository contains the source code for the Practice Information Management System (PIMS), developed as part of a Master's Thesis for the Applied Informatics postgraduate program at the University of Macedonia.

## 📌 Thesis Topic
**Development and Evaluation of an Information System (Veterinary Clinic Management) using AI Code Generation Assistants: A case study focusing on code quality evaluation and prompting strategy**.

## 🛠️ Tech Stack
* **AI Model:** Google Gemini 3 Pro
* **Backend:** Spring Boot (Java 21), REST API, Spring Security (JWT)
* **Frontend:** React.js (Single Page Application)
* **Database:** PostgreSQL (Dockerized)
* **Evaluation Tools:** SonarQube (Static Code Analysis), Google Lighthouse (Performance Metrics)

## 📊 Key Results
Using Gemini 3 Pro as a development partner (AI-Assisted Development) yielded the following insights:
* **Development Speed:** Overall development time was accelerated by approximately 60-70%.
* **Code Quality (Backend):** High reliability (Rating: A, 0 Bugs) and maintainability (Rating: A). However, security vulnerabilities were identified (Rating: E) primarily due to hardcoded credentials.
* **Frontend Quality:** Exceptional accessibility (Score: 96/100). Conversely, a high code duplication rate was observed (~39.4%), indicating the AI's tendency to copy logic ad-hoc rather than using proper architectural abstraction.
* **AI Performance:** 50% One-shot success on isolated, boilerplate tasks (e.g., Entities, DTOs), but required iterative fixes (50%) for complex, cross-system integrations like CORS and JWT.
* **The Developer's Role:** Human insight is crucial for ensuring security, architectural coherence, and solving complex logic. The developer's role shifts from writing manual code to acting as a "Software Architect / Orchestrator".

## Grade: 9.4 / 10

## 🔗 Repository
The full source code of this project can be found here:
[https://github.com/iosifidis/msc-pims](https://github.com/iosifidis/msc-pims)
