# Τεκμηρίωση κώδικα FirstAPICall

## 1. Εκκίνηση του προγράμματος (MainClass)  

Η `main()` μέθοδος έχει ως στόχο:

- Να καλέσει το URL (https://reqres.in/api/users/) για να πάρει όλες τις εγγραφές.  
- Να χρησιμοποιήσει τη μέθοδο `getLength` για να πάρει τον αριθμό των εγγραφών.   
- Να περάσει κάθε εγγραφή από τη μέθοδο `getResponse` και να την εμφανίσει.   

Βασικά σημεία:

- Ορισμός URL: Η βάση του URL για την ανάκτηση δεδομένων.   
- Χρήση αντικειμένου `HttpHandler`: Διαχωρίζει τη λογική της HTTP κλήσης από την κύρια κλάση.
- Επανάληψη με `for`:
 - Διατρέχει όλες τις εγγραφές χρησιμοποιώντας την getResponse για να πάρει το πλήρες όνομα κάθε εγγραφής.

Γιατί:

- Η χρήση ενός βρόχου διασφαλίζει ότι όλες οι εγγραφές από το API μπορούν να ληφθούν και να εμφανιστούν χωρίς επαναλαμβανόμενο κώδικα.   
- Ο διαχωρισμός της λογικής επιτρέπει καλύτερη αναγνωσιμότητα και επαναχρησιμοποίηση κώδικα.   

## 2. Κλάση HttpHandler

Η `HttpHandler` περιέχει δύο βασικές μεθόδους:

- `getResponse`: Επιστρέφει τα στοιχεία μιας συγκεκριμένης εγγραφής.  
- `getLength`: Υπολογίζει τον αριθμό των εγγραφών που πρέπει να επεξεργαστεί ο βρόχος.  

### 2.1. Μέθοδος `getResponse`

Αυτή η μέθοδος:

- Στέλνει μια HTTP κλήση στο API.  
- Αναλύει την απόκριση JSON και εξάγει τα στοιχεία της εγγραφής που αντιστοιχεί στο index `i`.  

Αναλυτικά:

1. `Unirest.setTimeouts(0, 0)`:  
 - Διασφαλίζει ότι η HTTP κλήση δεν έχει χρονικά όρια.   

2. `HttpResponse<String> response = Unirest.get(url).asString();`:   
 - Γίνεται αίτημα GET στο url.   

4. `JSONObject json = new JSONObject(data)`:   
 - Μετατροπή της απόκρισης σε αντικείμενο JSONObject.   

5. `JSONArray people = json.getJSONArray("data")`:   
 - Λήψη του πίνακα δεδομένων από το πεδίο "data".   

6. `JSONObject person = (JSONObject) people.get(i)`:   
 - Πρόσβαση στο αντικείμενο που αντιστοιχεί στο index `i`.   

7. Συνδυασμός των πεδίων `first_name` και `last_name`:
 - Επιστρέφεται το πλήρες όνομα της εγγραφής.

Παράδειγμα JSON Απόκρισης:

```
{
  "page": 1,
  "per_page": 6,
  "total": 12,
  "total_pages": 2,
  "data": [
    {
      "id": 1,
      "email": "george.bluth@reqres.in",
      "first_name": "George",
      "last_name": "Bluth",
      "avatar": "https://reqres.in/img/faces/1-image.jpg"
    },
    ...
  ]
}
```

Γιατί:

- Ο διαχωρισμός της μεθόδου για κάθε εγγραφή διευκολύνει τη στοχευμένη επεξεργασία δεδομένων.   
- Επιτρέπει την πρόσβαση σε συγκεκριμένα πεδία του JSON για κάθε χρήστη.   

### 2.2. Μέθοδος `getLength`

Αυτή η μέθοδος:

- Επιστρέφει τον αριθμό των εγγραφών που περιλαμβάνονται στο πεδίο `"data"` της JSON απόκρισης.

Αναλυτικά:

1. `HttpResponse<String> response = Unirest.get(url).asString();`:   
- Στέλνει αίτημα GET και παίρνει την απόκριση ως `String`.   

2. `JSONObject json = new JSONObject(data)`:   
- Μετατροπή της απόκρισης σε αντικείμενο `JSONObject`.  

3. `json.get("per_page").toString()`:   
- Παίρνει την τιμή του πεδίου `"per_page"` που δηλώνει τον αριθμό εγγραφών ανά σελίδα.   

4. `Integer.parseInt(total)`:   
- Μετατροπή της συμβολοσειράς `total` σε ακέραιο.   

Γιατί:

- Διασφαλίζει ότι το πρόγραμμα γνωρίζει πόσες επαναλήψεις απαιτούνται, ανάλογα με τον αριθμό των δεδομένων που επιστρέφονται.

## 3. Συνολική λειτουργία

1. Το `main()` καλεί το API για να πάρει τον αριθμό εγγραφών μέσω της `getLength`.   
2. Για κάθε εγγραφή, καλεί τη `getResponse` για να πάρει το πλήρες όνομα.   
3. Τα ονόματα εμφανίζονται στο τερματικό.   

Παραδείγματα Εξόδου: Αν το API επιστρέψει 6 εγγραφές, η έξοδος θα είναι:

```
Length=6
Name: George Bluth
Name: Janet Weaver
Name: Emma Wong
Name: Eve Holt
Name: Charles Morris
Name: Tracey Ramos
```

## 4. Χειρισμός εξαιρέσεων

Όλες οι μέθοδοι περιβάλλονται από `try-catch`, ώστε να καταγράφονται σφάλματα (π.χ., αποτυχία σύνδεσης, μη έγκυρο JSON).

## 5. Προτάσεις για τεκμηρίωση
- MainClass:
 - Εκκινεί το πρόγραμμα, λαμβάνει τον αριθμό εγγραφών και εμφανίζει τα ονόματα.

- HttpHandler:
 - `getResponse`: Επιστρέφει το πλήρες όνομα ενός χρήστη από τη θέση `i` στον πίνακα `data`.  
 - `getLength`: Υπολογίζει τον αριθμό των εγγραφών που περιλαμβάνονται στην απάντηση.   
 
- Παραδείγματα JSON: Περιέλαβε παραδείγματα JSON και εξήγησε πώς διαβάζονται.   
- Διαχείριση εξαιρέσεων: Εξήγησε τη σημασία του `try-catch`.