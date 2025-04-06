# 📘 MongoDB

### 🔹 Τι είναι η MongoDB;
- MongoDB = "humongous" (τεράστιος).
- Είναι ανοιχτού κώδικα, χωρίς σχήμα (schema-free), κλιμακούμενη, υψηλής απόδοσης **document-oriented βάση δεδομένων**.
- Αναπτύχθηκε το 2007 από την **10gen** (σήμερα MongoDB Inc).
- Γραμμένη σε **C++**, υποστηρίζει APIs σε πολλές γλώσσες.
- Δεν χρησιμοποιεί SQL, αλλά βασίζεται σε **έγγραφα τύπου JSON** (ή ακριβέστερα, **BSON** - Binary JSON).

---

### 🔹 Βασικά Στοιχεία Δομής
| Όρος MongoDB         | Αντίστοιχο σε Σχεσιακή Βάση Δεδομένων |
|----------------------|--------------------------------------|
| Database             | Βάση δεδομένων                       |
| Collection           | Πίνακας                              |
| Document             | Γραμμή (row)                         |
| Field                | Στήλη (column)                       |
| Index                | Ευρετήριο (index)                    |
| Cursor               | Δείκτης στο αποτέλεσμα ενός ερωτήματος|

Κάθε έγγραφο μπορεί να έχει **διαφορετικά πεδία** ακόμα και μέσα στην ίδια collection.

---

### 🔹 Πλεονεκτήματα MongoDB
- Ευκολία στις απλές ερωτήσεις.
- Καλή για εφαρμογές web.
- Εύκολη και γρήγορη ενοποίηση δεδομένων.
- Όχι καλή επιλογή για πολύπλοκα συστήματα συναλλαγών.

---

### 🔹 MongoDB Δομή Εγγράφου (Παράδειγμα)
```json
{
  name: "John",
  age: 20,
  phone: "777",
  address: "J-street"
}
```

---

### 🔹 Επεξεργασία Δεδομένων - CRUD

#### Create:
```js
db.collection.insertOne(document)
db.collection.insertMany([doc1, doc2, ...])
```

#### Read:
```js
db.collection.find({query}, {projection})
db.collection.findOne({query})
```

#### Update:
```js
db.collection.updateOne(query, update)
db.collection.updateMany(query, update)
```

#### Delete:
```js
db.collection.deleteOne(query)
db.collection.deleteMany(query)
db.collection.drop()
```

---

### 🔹 Παραδείγματα Ερωτήσεων (Querying)

1. Όλοι οι μονόκεροι (unicorns) με βάρος > 700 και φύλο "αρσενικό":
```js
db.unicorns.find({gender: "m", weight: {$gt: 700}})
```

2. Μονόκεροι χωρίς πεδίο vampires:
```js
db.unicorns.find({vampires: {$exists: false}})
```

3. Ποιοι αγαπούν "apple" ή "orange":
```js
db.unicorns.find({loves: {$in: ["apple", "orange"]}})
```

4. Γυναίκες μονόκεροι που αγαπούν "apple" ή ζυγίζουν < 500:
```js
db.unicorns.find({gender: "f", $or: [{loves: "apple"}, {weight: {$lt: 500}}]})
```

5. Μόνο τα ονόματα όλων των μονόκερων:
```js
db.unicorns.find({}, {name: 1, _id: 0})
```

6. Ταξινόμηση κατά βάρος:
```js
db.unicorns.find().sort({weight: 1})
```

7. Ταξινόμηση κατά όνομα και μετά κατά vampires:
```js
db.unicorns.find().sort({name: 1, vampires: 1})
```

8. Δεύτερος και τρίτος βαρύτερος μονόκερος:
```js
db.unicorns.find().sort({weight: -1}).skip(1).limit(2)
```

9. Πλήθος μονόκερων με περισσότερους από 50 vampires:
```js
db.unicorns.find({vampires: {$gt: 50}}).countDocuments()
```

---

### 🔹 Ενημερώσεις (Update)

- Ενημέρωση πεδίου:
```js
db.unicorns.updateOne({name: "Aurora"}, {$set: {weight: 500}})
```

- Αύξηση/Μείωση τιμής:
```js
db.unicorns.updateOne({name: "Pilot"}, {$inc: {vampires: -2}})
```

- Προσθήκη νέας τιμής σε πίνακα:
```js
db.unicorns.updateOne({name: "Aurora"}, {$push: {loves: "sugar"}})
```

- Ενημέρωση όλων (π.χ. μαρκάρισμα ως εμβολιασμένοι):
```js
db.unicorns.updateMany({}, {$set: {vaccinated: true}})
```

---

### 🔹 Aggregation (Ομαδοποίηση - Παρόμοιο με SQL `GROUP BY`)

#### Παράδειγμα:
```js
db.sales.aggregate([
  { $group: { _id: "$item", total: { $sum: { $multiply: ["$price", "$quantity"] } } }},
  { $match: { total: { $gte: 100 } }}
])
```

#### Ασκήσεις:
1. Πόσοι μονόκεροι υπάρχουν ανά φύλο;
2. Μέσος όρος vampires για μονόκερους < 600 κιλά, ανά φύλο.
3. Ποια τροφή αγαπιέται περισσότερο και από ποιους;

---

### 🔹 MongoDB Shell Εντολές
```bash
show dbs
use mydb
show collections
```

---

### 🔹 Περιορισμοί MongoDB
- **Δεν υπάρχει referential integrity**.
- Η απο-κανονικοποίηση (denormalization) δημιουργεί κόστη στην ενημέρωση.
- Δεν απαιτείται σχήμα, αλλά:
  - Ενδέχεται να δημιουργήσει ασυνέπειες.
  - Πολλές εφαρμογές χρειάζονται ομοιομορφία δομής.

---

## 📚 Πηγές για Μελέτη
- [MongoDB Manual](https://docs.mongodb.com/manual/)
- [The Little MongoDB Book](http://openmymind.net/mongodb.pdf)
- https://www.w3resource.com/mongodb/nosql.php
- https://highlyscalable.wordpress.com


