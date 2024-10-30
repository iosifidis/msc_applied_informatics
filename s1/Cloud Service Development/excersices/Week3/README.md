# Εισαγωγή στο Spring Boot

## 1. Δημιουργία του Spring Boot Project

## 2. Αρχείο pom.xml

## 3. Δημιουργία των Classes και Controllers

## Έλεγχος των Endpoints

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

