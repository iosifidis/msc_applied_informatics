# Εισαγωγή στο Spring Boot

Αυτός ο κώδικας αφορά μια εφαρμογή Spring Boot που παρέχει μια σειρά από RESTful endpoints για την επιστροφή μηνυμάτων και δεδομένων. Ας αναλύσουμε τις κλάσεις και τις λειτουργίες τους:

## 1. Κλάση `InitApplication`

Η κύρια κλάση που εκκινεί την εφαρμογή Spring Boot.

Λειτουργικότητα:
- Χρησιμοποιεί τη μέθοδο `SpringApplication.run()` για να εκκινήσει την εφαρμογή.   
- Η χρήση του annotation `@SpringBootApplication` επιτρέπει στο Spring Boot να σαρώσει αυτόματα όλες τις κλάσεις στο πακέτο `gr.uom.init` και να τις καταχωρίσει ως beans.

Γιατί:

- Απλή, γρήγορη εκκίνηση με ελάχιστη παραμετροποίηση.

## 2. Κλάση `Controller`

Ένας REST Controller με πολλαπλά endpoints που διαχειρίζονται διαφορετικές λειτουργίες.

Endpoints:
1. `/hello`

- Επιστρέφει το μήνυμα `"Hello World"`.
- URL: `http://localhost:8081/hello`

2. `/hello/{name}`

- Επιστρέφει ένα προσωποποιημένο μήνυμα με το όνομα από το URL.   
- Παράδειγμα URL: `http://localhost:8081/hello/Stathis`   
- Απάντηση: `"Hello Stathis"`   

3. `/helloandage/{name}`

- Επιστρέφει ένα μήνυμα με το όνομα και την ηλικία.   
- Δέχεται την ηλικία ως query parameter (`age`).   
- Παράδειγμα URL: `http://localhost:8081/helloandage/Stathis?age=40`   
- Απάντηση: `"Hello Stathis 40"`   

4. `/helloandlocation/{name}`

- Επιστρέφει ένα μήνυμα με το όνομα, την ηλικία και την τοποθεσία.   
- Χρησιμοποιεί την κλάση `Person` για να δημιουργήσει και να επιστρέψει δεδομένα.   
- Παράδειγμα URL: `http://localhost:8081/helloandlocation/Stathis?age=40&location=Thessaloniki`   
- Απάντηση: `"Hello Stathis 40 from Thessaloniki"`   

5. `/list`

- Επιστρέφει μια λίστα μηνυμάτων σε μορφή JSON.   
- Παράδειγμα URL: `http://localhost:8081/hello/list`   

Απάντηση:
```
[
  "Hola",
  "Me llamo Estathis",
  "Tengo 40 años"
]
```

Γιατί:

- Δείχνει τη χρήση διαφορετικών τρόπων ανάκτησης δεδομένων (`PathVariable, RequestParam`).   
- Παρέχει ένα παράδειγμα απλών RESTful υπηρεσιών.

## 3. Κλάση `Person`

Μια απλή POJO (Plain Old Java Object) κλάση που αναπαριστά ένα άτομο.

Ιδιότητες:
- `name`: Το όνομα του ατόμου.  
- `age`: Η ηλικία του.  
- `location`: Η τοποθεσία του.  

Μέθοδοι:
- Κατασκευαστής:
 - Αρχικοποιεί τα πεδία `name`, `age`, `location`.   
- `getData`:
 - Επιστρέφει μια συμβολοσειρά με όλα τα δεδομένα του ατόμου.   

Γιατί:

- Δείχνει πώς χρησιμοποιούνται αντικείμενα σε RESTful endpoints.

## 4. Κλάση GoodNight

Ένας ακόμα REST Controller που προσθέτει ένα endpoint για ένα μήνυμα "Good Night".

Endpoint:  
1. `/goodnight`   
 - Επιστρέφει το μήνυμα `"Good Night"`.   
 - Παράδειγμα URL: `http://localhost:8081/goodnight`
 - Απάντηση: `"Good Night"`

Γιατί:

- Εμφανίζει πώς να δημιουργήσεις πολλαπλούς controllers στην ίδια εφαρμογή.

## Διάγραμμα Ενότητας

<pre>
+-----------------------+
| InitApplication       |  
+-----------------------+
           |
           v
+-----------------------+
| Controller            |----> /hello
|  - /hello/{name}      |----> /hello/{name}
|  - /helloandage       |----> /helloandage/{name}?age=40
|  - /helloandlocation  |----> /helloandlocation/{name}?age=40&location=... 
|  - /list              |----> /list
+-----------------------+
           |
           v
+-----------------------+
| Person                |
|  - name, age, location|
|  - getData()          |
+-----------------------+
           |
           v
+-----------------------+
| GoodNight             |----> /goodnight
+-----------------------+
</pre>


## Παράδειγμα Εκτέλεσης

1. Εκκίνηση Εφαρμογής

 - Εκτελέστε την κλάση `InitApplication`.   
 - Το Spring Boot ξεκινά στον `localhost:8081` (προεπιλεγμένη θύρα).   
 
2. Κλήσεις στα Endpoints

 - Για το `http://localhost:8081/hello/Stathis`:   

```
Hello Stathis
```

 - Για το `http://localhost:8081/helloandlocation/Stathis?age=40&location=Thessaloniki`:  

```
Hello Stathis 40 from Thessaloniki
```

3. JSON Απόκριση

 - Για το `http://localhost:8081/hello/list`:
 
```
[
  "Hola",
  "Me llamo Estathis",
  "Tengo 40 años"
]
```

## Λίστα με Annotations και Επεξήγηση

**@RestController**
- Τοποθεσία: Controller και GoodNight κλάσεις.   
- Λειτουργία: Συνδυασμός των annotations @Controller και @ResponseBody. Δηλώνει ότι η κλάση αυτή είναι ένα RESTful controller, και οι μέθοδοι της επιστρέφουν JSON ή άλλο αντικείμενο ως HTTP response αντί για view.   

**@RequestMapping**
- Τοποθεσία: Controller και GoodNight κλάσεις.   
- Λειτουργία: Χρησιμοποιείται για τον ορισμό του βασικού URL στο οποίο θα απαντά η κλάση. Ορίζοντας π.χ. @RequestMapping("/hello") στην Controller κλάση, όλες οι διαδρομές (endpoints) μέσα στην κλάση θα ξεκινούν από το /hello.  

**@GetMapping**
- Τοποθεσία: Στις μεθόδους των controllers.  
- Λειτουργία: Δηλώνει ότι μια μέθοδος είναι HTTP GET request endpoint. Χρησιμοποιείται για να ανακτήσει δεδομένα από τον server, όπως στη μέθοδο hello() που επιστρέφει "Hello World" και τις άλλες helloName, helloNameAndAge.  

**@PathVariable**
- Τοποθεσία: Στις παραμέτρους των μεθόδων όπως helloName(@PathVariable(value="name") String name).  
- Λειτουργία: Δίνει τη δυνατότητα να λαμβάνει η μέθοδος δυναμικά τιμές από το URL (π.χ., /hello/Stathis), τοποθετώντας το path variable από το URL στο ορισμένο όρισμα της μεθόδου.  

**@RequestParam**
- Τοποθεσία: Στις παραμέτρους των μεθόδων όπως helloNameAndAge(@RequestParam(value="age") int age).  
- Λειτουργία: Λαμβάνει παραμέτρους από το URL, οι οποίοι ορίζονται μετά το ? στο URL (π.χ., /helloandage/Stathis?age=40). Χρησιμοποιείται για την πρόσβαση σε query parameters που δεν είναι μέρος της διαδρομής (path).  







