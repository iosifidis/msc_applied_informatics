# Σημαντικά Στοιχεία της JavaScript

Η **JavaScript** είναι μια object-oriented, loosely-typed, δυναμική scripting γλώσσα. Χρησιμοποιείται κυρίως για τον προγραμματισμό στο web.

---

## Βασικά Χαρακτηριστικά
- **Προγραμματιστικά στυλ:** Object-oriented, imperative, functional.
- **Χρήσεις:** 
  - Δυναμικές ιστοσελίδες (client-side).
  - Server-side προγραμματισμός (Node.js).
  - Εφαρμογές για κινητά και desktop.
- **Συμβατότητα:** Υιοθετεί το πρότυπο ECMAScript.

---

## Εκτέλεση JavaScript
1. **Μέσα σε HTML αρχείο:** Χρησιμοποιώντας την ετικέτα `<script>`.
2. **Στη γραμμή εντολών του browser.**
3. **Μέσω Node.js:** Για server-side ανάπτυξη.

Παράδειγμα:
```html
<body>
  <script>
    var x = 2;
    x = x + 1;
    alert('x είναι: ' + x);
  </script>
  <p onclick="alert('Hello!')">Κάνε κλικ εδώ</p>
</body>
```

## Εισαγωγή εξωτερικού αρχείου
Τοποθετούμε τον παρακάτω κώδικα σε εξωτερικό αρχείο, πριν το κλείσιμο του tag **body**.

```
<script src="myFile.js"></script>
```

Επεξεργαζόμαστε το αρχείο **myFile.js**.

## Τρόποι εμφάνισης

```
alert('Hello world from popup window'); // Ως popup παράθυρο
console.log('Hello world from console'); // Στην κονσόλα
document.getElementById('myId').innerHTML = 'Hello world from browser window';  // Στην ιστοσελίδα μας
```

---

## Τύποι Δεδομένων
- **Primitive Values**: number, string, boolean, null, undefined.
- **Complex Values**: array, object, function.

**Παράδειγμα**:
```
let str = 'JavaScript';
console.log(str.length); // 10
```

## Δήλωση μεταβλητών
Η JavaScript υποστηρίζει τρεις τρόπους δήλωσης μεταβλητών:

- `var`: Χρησιμοποιείται για function scope μεταβλητές και υποστηρίζει hoisting, δηλαδή η δήλωση της μεταβλητής "ανεβαίνει" στην κορυφή της συνάρτησης.   
- `let`: Εισήχθη με το ES6 και υποστηρίζει block scope. Αποφεύγει τα προβλήματα του hoisting.   
- `const`: Επίσης εισήχθη με το ES6. Χρησιμοποιείται για σταθερές τιμές και δεν μπορεί να επαναπροσδιοριστεί.   

**Παράδειγμα**:
```
'use strict';

var x = 10;
let y = 20;
const z = 30;

console.log(x, y, z); // 10 20 30
```

---

## Δομές Ελέγχου και Επαναλήψεις
Υποστηρίζονται οι κλασικές δομές όπως if, switch, for, while.

**Παραδείγματα**:
```
if (x > 10) {
  console.log('Μεγαλύτερο από 10');
}

for (let i = 0; i < 5; i++) {
  console.log(i);
}

while (x < 5) {
  x++;
}
```

---

## Συναρτήσεις
Στην JavaScript, οι συναρτήσεις μπορούν να δηλωθούν είτε με declaration είτε με expression:

- Declaration: Παραδοσιακή μορφή δήλωσης.   
- Expression: Ανώνυμη συνάρτηση που μπορεί να αντιστοιχιστεί σε μεταβλητή.

**Παράδειγμα**:
```
function first() {
  return 'Function Declaration';
}

var second = function () {
  return 'Function Expression';
};

console.log(typeof first === typeof second); // true
console.log(typeof first === 'function'); // true
```

### ES6 Βελτιώσεις

- **let και const**: Block scope μεταβλητές.  
- **Template Strings**: Εύκολη διαμόρφωση αλφαριθμητικών.
```
const name = 'JavaScript';
console.log(`Welcome to ${name}`);
```

### Αντικείμενα
Δημιουργία αντικειμένων με literal notation:
```
const person = {
  firstName: "John",
  lastName: "Doe",
  age: 25,
  greet: function () {
    return `Hello, ${this.firstName}`;
  }
};
console.log(person.greet()); // Hello, John
```

## ΜΕΤΑΤΡΟΠΗ ΑΠΟ ARRAY-LIKE OBJECTS ΣΕ ARRAYS
Η JavaScript υποστηρίζει τη μετατροπή αντικειμένων που μοιάζουν με πίνακες (array-like objects) σε πραγματικούς πίνακες μέσω των παρακάτω μεθόδων:

- ES5: `Array.prototype.slice.call(x)`
- ES6: `Array.from(x)`

**Παράδειγμα**:
```
const arrayLike = { 0: 'a', 1: 'b', length: 2 };

const arr1 = Array.prototype.slice.call(arrayLike); // ES5
const arr2 = Array.from(arrayLike); // ES6

console.log(arr1); // ['a', 'b']
console.log(arr2); // ['a', 'b']
```

## arguments
Το ειδικό αντικείμενο arguments περιέχει όλα τα arguments που περνιούνται σε μια συνάρτηση, ανεξάρτητα από τον αριθμό που ορίζονται στον ορισμό της.

**Παράδειγμα**:
```
function testArguments(x, y) {
  x = x === undefined ? 2 : x;
  y = y === undefined ? 'power' : y;
  console.log(x, y, typeof arguments, arguments);
}

testArguments(); 
// 2 power object Arguments { 0: 2, 1: 'power' }

testArguments(3, 4, 5, 6, 7); 
// 3 4 object Arguments { 0: 3, 1: 4, 2: 5, 3: 6, 4: 7 }
```

---

## Χρήσιμες Πηγές
- [Eloquent JavaScript](https://eloquentjavascript.net/)  
- [Exploring ES6](https://exploringjs.com/es6/)   
- [JSBooks](https://jsbooks.revolunet.com/)   
- [Javascript.info/](https://javascript.info/)


