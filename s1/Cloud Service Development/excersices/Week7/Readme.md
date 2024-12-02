# Τεκμηρίωση για το project Student Management (week 6) με προσθήκη POST Mapping και swagger 

## 1. Περιγραφή

Το παράδειγμα είναι ίδιο με της προηγούμενης εβδομάδας (week 6). Η εφαρμογή επικοινωνεί με το backend μέσω REST API endpoints, επιτρέποντας την προβολή όλων των φοιτητών και την προσθήκη νέων. Οι αλλαγές ενσωματώνουν καλύτερη χρήση HTTP με τη δημιουργία ενός POST endpoint για την προσθήκη φοιτητών, αντί του GET. Επίσης, έχει προστεθεί το Swagger για την τεκμηρίωση και δοκιμή των API.

<hr>

## 2. Ενημερωμένα Endpoints

1. `/students`  

  - Ρόλος: Επιστρέφει τη λίστα όλων των φοιτητών από τη βάση δεδομένων.

  - Παράδειγμα URL:

`http://localhost:8080/students`

  - Απάντηση (JSON):
  
```
[
    {
        "name": "John Doe",
        "age": 21,
        "location": "Athens"
    },
    {
        "name": "Jane Smith",
        "age": 23,
        "location": "Thessaloniki"
    }
]
```

  - Κώδικας:
```
@GetMapping(path = "/students")
public List<Student> getAllStudent() throws Exception {
    return hs.getAllStudents();
}
```

2. `/addStudent`  

  - Ρόλος: Προσθέτει έναν νέο φοιτητή στη βάση δεδομένων.   
  - Παράδειγμα URL:   
`http://localhost:8080/addStudent`

  - Σώμα που προσθέτει (JSON):
```
{
    "name": "John Doe",
    "age": 21,
    "location": "Athens"
}
```

  - Κώδικας  
```
@PostMapping(path = "/addStudent")
public void addStudent(@RequestBody Student st) throws Exception {
    hs.addStudent(st);
}
```

## 3. Testing μέσω Swagger UI

Για ευκολότερο testing των endpoints, έχει προστεθεί το Swagger UI:

- Προσθήκη Dependencies (στο αρχείο `pom.xml`):   
```
		<dependency>
		    <groupId>org.springdoc</groupId>
		    <artifactId>springdoc-openapi-ui</artifactId>
		    <version>1.6.15</version>
		</dependency>
		
```

και στο αρχείο `application.properties` μέσα στα resources:
```
springdoc.api-docs.path=/api
springdoc.swagger-ui.path=/api-ui
```

**URL Swagger**:  

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Στο Swagger, τα διαθέσιμα endpoints (/students και /addStudent) εμφανίζονται με περιγραφές και δυνατότητα εκτέλεσης HTTP αιτημάτων.

## 4. Ενημερωμένη JavaScript Λειτουργία

Η function addStudent() αποστέλλει POST αίτημα για να προσθέσει έναν φοιτητή:
```
function addStudent() {  
    var settings = {
        "url": "http://localhost:8080/addStudent",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            "name": document.getElementById("name").value,
            "age": document.getElementById("age").value,
            "location": document.getElementById("location").value
        }),
    };

    $.ajax(settings).done(function (response) {
        showAll(); // Ενημερώνει τη λίστα των φοιτητών μετά την προσθήκη
    });
}
```

## 5. Τελικό Διάγραμμα Ενότητας

```
+---------------------+
| DemoApplication     |
| (Entry Point)       |
+---------------------+
           |
           v
+----------------------+
| HelloController      |
| (REST API)           |
|  - /students (GET)   |
|  - /addStudent (POST)|
+----------------------+
           |
           v
+---------------------+
| HelloService        |
| (Business Logic)    |
|  - addStudent       |
|  - getAllStudents   |
+---------------------+
           |
           v
+---------------------+
| Student             |
| (Data Model)        |
|  - name, age, loc.  |
+---------------------+
```

## 6. Συμπεράσματα

- Το POST endpoint αντικατέστησε το GET για την προσθήκη φοιτητών, εξασφαλίζοντας καλύτερη χρήση των HTTP μεθόδων.    
- Το Swagger UI διευκολύνει την τεκμηρίωση και τον έλεγχο των API.    
- Η λειτουργία του frontend ενημερώθηκε με τη χρήση του POST endpoint.   

## 7. Χρησιμοποιούμενα Annotations

- `@RestController`: Δημιουργεί RESTful API.   
- `@GetMapping`: Χειρίζεται HTTP GET αιτήματα.   
- `@PostMapping`: Χειρίζεται HTTP POST αιτήματα.   
- `@RequestBody`: Δέχεται JSON δεδομένα από το body του αιτήματος.   
- `@Autowired`: Κάνει inject το service class στον controller.   
