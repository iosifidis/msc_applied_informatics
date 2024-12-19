### Σημειώσεις: Βιβλιοθήκες, Αλφαριθμητικά, Μετατροπές και Διάβασμα Δεδομένων

#### Χρήση Κλάσεων Βιβλιοθήκης

- Η δεσμευμένη λέξη **static** χρησιμοποιείται για τη δήλωση μεταβλητών κλάσης (class variables) ή αλλιώς στατικών μεταβλητών (static variables). 
  - Οι μεταβλητές κλάσεις αποθηκεύονται στην ίδια την κλάση και όχι σε αντικείμενα. Υπάρχει μόνο ένα αντίγραφο κατά την εκτέλεση ενός προγράμματος, ανεξάρτητα από τον αριθμό των στιγμιοτύπων.
  - Παράδειγμα:
    ```java
    private static final int gravity = 3;
    ```

- Η δεσμευμένη λέξη **final** χρησιμοποιείται για τον ορισμό σταθερών (constants), οι οποίες παραμένουν αμετάβλητες κατά την εκτέλεση του προγράμματος.
  - Για κοινή χρήση μιας σταθεράς σε όλα τα στιγμιότυπα, δηλώνουμε τη σταθερά ως "class constant" με `static final`.

#### Αλφαριθμητικά (Strings)

- **Έλεγχος Ισότητας:**
  - Δεν χρησιμοποιούμε το `=` αλλά τη μέθοδο `equals()`.
    ```java
    if (string1.equals(string2)) {
        // Τα strings είναι ίσα
    }
    ```
  - Για αδιαφορία σε πεζά-κεφαλαία: `equalsIgnoreCase()`.

- **Άλλες Χρήσιμες Μέθοδοι:**
  - Μήκος αλφαριθμητικού:
    ```java
    int length = str1.length();
    ```
  - Αντικατάσταση χαρακτήρων:
    ```java
    String newString = str1.replace('g', 'f');
    ```

#### Μετατροπές

- **Μετατροπή Άλλων Τύπων σε Αλφαριθμητικό:**
  ```java
  String str = String.valueOf(nonStringValue);
  ```

- **Μετατροπή Αλφαριθμητικών σε Άλλους Τύπους:**
  ```java
  int intValue = Integer.parseInt(stringValue);
  double doubleValue = Double.parseDouble(stringValue);
  float floatValue = Float.parseFloat(stringValue);
  ```

- **Παραδείγματα:**
  ```java
  String st1 = String.valueOf(22);
  int i = Integer.parseInt("22");
  double d = Double.parseDouble("22.2");
  ```

#### Διάβασμα Δεδομένων από την Κονσόλα

- **Εισαγωγή Δεδομένων:**
  ```java
  Scanner in = new Scanner(System.in);
  System.out.print("Enter quantity: ");
  int quantity = in.nextInt();
  ```

- **Διάφοροι Τύποι Δεδομένων:**
  ```java
  int x = in.nextInt();
  double y = in.nextDouble();
  String s = in.nextLine();
  ```

- **Μετατροπές από String σε Άλλους Τύπους:**
  ```java
  int x = Integer.parseInt(in.nextLine());
  double y = Double.parseDouble(in.nextLine());
  String s = in.nextLine();
  ```

#### Πρόσθετα Σημεία

- Η αρχή της "απόκρυψης πληροφοριών" (information hiding) εξασφαλίζει καλύτερη δομή της εφαρμογής.
  - Τα πεδία πρέπει να είναι `private`, και η πρόσβαση σε αυτά να γίνεται μέσω μεθόδων `public` αν απαιτείται.

- **Παραδείγματα για τη χρήση κλάσεων βιβλιοθήκης και String:**
  - Εύρεση χαρακτήρων:
    ```java
    int index = str1.indexOf('e');
    ```
  - Υποαλφαριθμητικά:
    ```java
    String subStr = str1.substring(5, 10);
    ```


