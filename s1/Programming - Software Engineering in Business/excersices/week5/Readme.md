# Βιβλιοθήκες - Αλφαριθμητικά - Μετατροπές - Διάβασμα δεδομένων

## Χρήση κλάσεων βιβλιοθήκης

Η δεσμευμένη λέξη **static** χρησιμοποιείται για τη δήλωση μεταβλητών κλάσης (class variables), ή αλλιώς στατικών μεταβλητών (static variables). Οι μεταβλητές κλάσεις είναι πεδία που αποθηκεύονται στην ίδια την κλάση και όχι σε ένα αντικείμενο. Κάθε στιγμή δηλαδή κατά τη διάρκεια εκτέλεσης ενός προγράμματος υπάρχει ένα μόνο αντίγραφο μιας μεταβλητής κλάσης ανεξάρτητα από τον αριθμό των στιγμιοτύπων/αντικειμένων της.

Η δεσμευμένη λέξη **final** χρησιμοποιείται συχνά για τον ορισμό σταθερών (constants), η τιμή των οποίων παραμένει αμετάβλητη σε όλη τη διάρκεια εκτέλεσης ενός προγράμματος. Η δήλωση σταθερών στη Java γίνεται με τη δεσμευμένη λέξη final.

Πολλές φορές μια σταθερά χρησιμοποιείται από όλα τα στιγμιότυπα μιας κλάσης. Σε αυτή την περίπτωση δηλώνουμε μια σταθερά κλάσης (class constant) συνδυάζοντας τις δεσμευμένες λέξεις static και final, όπως στην περίπτωση της gravity παραπάνω.

- private static final int gravity = 3;

## Αλφαριθμητικά

Έλεγχος ισότητας αλφαριθμητικών: όχι με το = αλλά με τη μέθοδο equals()

- π.χ. if (string1.equals(string2))

Αν δεν θέλουμε να λαμβάνονται υπόψη πεζά-κεφαλαία τότε κάνουμε χρήση της equalsIngnoreCase().

Μέγεθος αλφαριθμητικού int i = str1.length()   
Αντικατάσταση αλφαριθμητικού str1.replace('g','f')

### Μετατροπές

Μετατροπή άλλων τύπων σε αλφαριθμητικό:   
- μεταβλητή_string.valueOf(μη-αλφαριθμητική-τιμή)

Μετατροπή αλφαριθμητικών σε άλλους τύπους:   
- μεταβλητή_int = Integer.parseInt(αλφαριθμητικό);   
- μεταβλητή_double = Double.parseDouble(αλφαριθμητικό);   
- μεταβλητή_float = Float.parseFloat(αλφαριθμητικό);   

πχ
- String st1 = String.valueOf(22);   
- int i = Integer.parseInt("22");   
- double d = Double.parseDouble("22.2");   

## Διάβασμα δεδομένων από την κονσόλα

Scanner in = new Scanner(System.in);    
System.out.print("Enter quantity ");    
int quantity = in.nextInt();    

### Διάφοροι τύποι

int x = in.nextInt();   
double y = in.nextDouble();   
String s = in.nextLine();   

int x = Integer.parseInt(in.nextLine());   
double y = Double.parseDouble(in.nextLine());   
String s = in.nextLine();   