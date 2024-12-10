# Επίλυση άσκησης δημιουργίας Υπηρεσίας Διαχείρισης Αποθήκης

Για εκφώνηση δείτε [εδώ](ΕxerciseData.md).

<hr>

## 1. Περιγραφή

Η εφαρμογή Market Management επικοινωνεί με το backend μέσω REST API endpoints, επιτρέποντας την αναζήτηση προϊόντων στη βάση δεδομένων, την εμφάνιση όλων των προϊόντων και την προσθήκη νέων. Η λειτουργικότητα περιλαμβάνει AJAX αιτήματα στο frontend για τη διασύνδεση με τα REST endpoints, ενώ γίνεται χρήση της βιβλιοθήκης jQuery για την επικοινωνία.

<hr>

## 2. Ενημερωμένα Endpoints

- /allproducts

  - Ρόλος: Επιστρέφει τη λίστα όλων των προϊόντων από τη βάση δεδομένων.   
  - Παράδειγμα URL: `http://localhost:8080/allproducts`   

  - Απάντηση (JSON):

```
[
    {
        "code": 1,
        "name": "Gala",
        "price": 1
    },
    {
        "code": 2,
        "name": "Tiri",
        "price": 4
    }
]
```

Κώδικας:
```
@GetMapping(path = "/allproducts")
public List<Product> getAllProducts() throws Exception {
    return ms.getAllProducts();
}
```

- /addproduct

  - Ρόλος: Προσθέτει ένα νέο προϊόν στη βάση δεδομένων.   
  - Παράδειγμα URL: `http://localhost:8080/addproduct`   

  - Σώμα (JSON):
```
{
    "code": 3,
    "name": "Portokalia",
    "price": 3
}
```

Κώδικας:
```
@PostMapping(path = "/addproduct")
public String addProduct(@RequestBody Product pr) throws Exception {
    ms.addProduct(pr);
    return "Το προϊόν προστέθηκε με επιτυχία!";
}
```

- /findproduct

  - Ρόλος: Αναζητά ένα προϊόν με βάση το όνομα που εισάγεται στο frontend.
  - Παράδειγμα URL: `http://localhost:8080/findproduct?name=Apple`
  
Απάντηση (String):
```
Το προϊόν Apple βρέθηκε.
```

Κώδικας:
```
@GetMapping(path = "/findproduct")
 public String findProduct(String name) {
	 return ms.findProduct(name);
 }
```

ενώ η κλάση `Service` έχει υλοποίηση:

```
	 public String findProduct(String pr) {

		 String result = "Το προϊόν " + pr + " δεν βρέθηκε.";
		 
//			// Έλεγχος αν το όνομα είναι άκυρο
//		    if (pr == null || pr.isBlank()) {
//		        result = "Σφάλμα: Δεν δόθηκε όνομα προϊόντος.";
//		    }

		    // Εύρεση του προϊόντος. Αναζητά το προϊόν στη λίστα συγκρίνοντας τα ονόματα
		    for (Product product : pList) {
		         if (product.getName().equalsIgnoreCase(pr)) {
		        	 result = "Το προϊόν " + pr + " βρέθηκε."; // Επιστροφή του προϊόντος αν βρεθεί
		         }

		    }
		    return result;
	 }
```

<hr>

## 3. Frontend (AJAX)

- Λειτουργία: addProduct()

Περιγραφή: Αποστέλλει POST αίτημα στο endpoint /addproduct και ενημερώνει τη σελίδα με το αποτέλεσμα.

Κώδικας
```
			function addProduct() {  
			    var code = document.getElementById("code").value; 
			    var name = document.getElementById("name").value; 
			    var price = document.getElementById("price").value;
			    
			    
			    if (!code || !name || !price) {
				alert("Please fill in all fields.");
				return;
			    }
			
			    
			    var settings = {
				"url": "http://localhost:8080/addproduct",
				"method": "POST", // Ορίζουμε ότι θα κάνουμε POST request.
				"timeout": 0, // Δεν υπάρχει χρονικό όριο για το request.
				"headers": {
				    "Content-Type": "application/json" 
				},
				"data": JSON.stringify({ 
				    "code": code, 
				    "name": name,   
				    "price": price 
				}),
			    };

			    // Εκτέλεση του AJAX request.
			    $.ajax(settings).done(function (response) {
				// Μετά την επιτυχή ολοκλήρωση, καλούμε τη συνάρτηση showAll().
				showAll(); 
			    });
			}
```
Κώδικας φόρμας (πίνακας):   
```
	<table>
			<tr>
			  <td> Code: </td>
			  <td> <input type='text' id='code' /></td>
			</tr> 
			<tr>
			  <td> Name: </td>
			  <td> <input type='text' id='name' /></td>
			</tr>
			<tr>
			  <td> Price: </td>
			  <td> <input type='text' id='price' /></td>
			</tr>

		</table>
		<button name='addProduct' onclick="addProduct();"> Add Product </button>
```

- Λειτουργία: findProduct()

Περιγραφή: Αποστέλλει GET αίτημα στο endpoint /findproduct και ενημερώνει τη σελίδα με το αποτέλεσμα.

Κώδικας:
```
function findProduct() {
    var productName = document.getElementById("searchName").value.trim();

    if (productName === "") {
        alert("Please enter a product name.");
        return;
    }

    var settings = {
        "url": "http://localhost:8080/findproduct?name=" + encodeURIComponent(productName),
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        document.getElementById("searchResult").innerHTML = response;
    }).fail(function () {
        document.getElementById("searchResult").innerHTML = "Error: Unable to find product.";
    });
}
```

Κώδικας φόρμας:
```
		<h2>Search Product</h2>
		<table>
			<tr>
			  <td> Name: </td>
			  <td> <input type='text' id='searchName' /></td>
			</tr>
		</table>
		<button name='findProduct' onclick="findProduct();"> Find Product </button>
		<br>
		<div id="searchResult" style="margin-top: 20px; color: blue; font-weight: bold;"></div>	
```

<hr>

## 4. Διάγραμμα Ενότητας

```
+-----------------------------+
| MarketWeek7Application      |
| (Entry Point)               |
+-----------------------------+
               |
               v
+-----------------------------+
| MarketController            |
| (REST API)                  |
|  - /allproducts (GET)       |
|  - /addproduct (POST)       |
|  - /findproduct (GET)       |
+-----------------------------+
               |
               v
+-----------------------------+
| MarketService               |
| (Business Logic)            |
|  - addProduct               |
|  - getAllProducts           |
|  - findProduct              |
+-----------------------------+
               |
               v
+-----------------------------+
| Product                     |
| (Data Model)                |
|  - code, name, price        |
+-----------------------------+
```

<hr>

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

Στο Swagger, τα διαθέσιμα endpoints (/allproducts, /addproduct και /findproduct) εμφανίζονται με περιγραφές και δυνατότητα εκτέλεσης HTTP αιτημάτων.

<hr>

## 5. Συμπεράσματα
- Υλοποιήθηκαν REST API endpoints για την εμφάνιση, αναζήτηση και προσθήκη προϊόντων.   
- Το frontend υποστηρίζει δυναμική αλληλεπίδραση με το backend μέσω AJAX αιτημάτων.   
- Η λειτουργικότητα μπορεί να επεκταθεί με πρόσθετα χαρακτηριστικά, όπως ενημέρωση και διαγραφή προϊόντων.   
