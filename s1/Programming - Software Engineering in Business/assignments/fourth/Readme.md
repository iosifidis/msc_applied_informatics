﻿# ΑΝΤΙΚΕΙΜΕΝΟΣΤΡΕΦΗΣ ΑΝΑΠΤΥΞΗ ΛΟΓΙΣΜΙΚΟΥ

## A΄ Εξαμήνου Μεταπτυχιακού Προγράμματος στην Εφαρμοσμένη Πληροφορική (Κατεύθυνση: Επιχειρηματική Πληροφορική)

### Project 4: Κληρονομικότητα και Πολυμορφισμός 

Σε μια αλυσίδα πολυκαταστημάτων απασχολούνται: 

- **Ωρομίσθιοι υπάλληλοι**, οι οποίοι πληρώνονται ανάλογα με τον αριθμό των ωρών που δουλεύουν  στη  διάρκεια  ενός  μήνα,  με  ωριαία  αποζημίωση  η  οποία  μπορεί  να  είναι διαφορετική ανάλογα με την προϋπηρεσία του υπαλλήλου. Στην περίπτωση που το σύνολο του μήνα (όπως προκύπτει από το γινόμενο των ωρών με την ωριαία αποζημίωση) είναι μικρότερο των 500 Ευρώ γίνεται προσαύξηση 20%.  
- **Υπάλληλοι πλήρους απασχόλησης**, οι οποίοι λαμβάνουν ένα συγκεκριμένο μηνιαίο μισθό και ένα επιπλέον ποσό για τυχόν υπερωρίες στη διάρκεια του μήνα. Συγκεκριμένα, για κάθε ώρα εργασίας πέραν του ωραρίου ο υπάλληλος παίρνει επιπλέον 0,5% του μηνιαίου μισθού.  

Όλοι οι υπάλληλοι χαρακτηρίζονται από το επώνυμό τους και τον Αριθμό Φορολογικού Μητρώου 

Δημιουργήστε  2  κλάσεις:  την  `PartTimeEmployee`  (ωρομίσθιοι)  και  `FullTimeEmployee` (πλήρους  απασχόλησης)  για  την  αναπαράσταση  των  υπαλλήλων  της  αλυσίδας πολυκαταστημάτων. 

Τα δεδομένα που θα διαχειρίζεται η κλάση `PartTimeEmployee` θα είναι το ονοματεπώνυμο του υπαλλήλου, ο αριθμός φορολογικού μητρώου, η ωριαία αποζημίωση και ο  αριθμός των  ωρών εργασίας στη διάρκεια ενός μήνα. Οι λειτουργίες που θα υποστηρίζονται είναι: 

- Λειτουργία  που  επιτρέπει  να  θέτουμε  τις  ώρες  εργασίας  στο  τέλος  του  μήνα  (μέθοδος `setHours`)   
- Υπολογισμός  και  επιστροφή  του  συνόλου  για  τον  τρέχοντα  μήνα  (μέθοδος `calculateSalary`)   
- Εκτύπωση της κατάστασης μισθοδοσίας ως εξής (μέθοδος `printPayrollReport`): 

```
    PAYMENT RECORD FOR A FULL TIME EMPLOYEE         
    Employee's Last Name: <ονοματεπώνσμο> 
    Tax ID number: <αριθμός υορολογικού μητρώου> 
    Hours: <ώρες> 
    Monthly Total: <σύνολο τρέτοντα μήνα> Euro 
    Remarks 
    Hourly wage = <ωριαία αποζημίωση> Euro 
```

Για να εμφανιστεί το `<σύνολο τρέτοντα μήνα>` θα κληθεί η μέθοδος `calculateSalary`. 

Τα δεδομένα που θα διαχειρίζεται η κλάση `FullTimeEmployee` θα είναι το ονοματεπώνυμο του υπαλλήλου,  ο  αριθμός  φορολογικού  μητρώου,  ο  μηνιαίος  μισθός  και  ο  αριθμός  των  ωρών εργασίας  πέραν  του  ωραρίου  (υπερωρίες)  στη  διάρκεια  ενός  μήνα.  Οι  λειτουργίες  που  θα υποστηρίζονται είναι: 

- Λειτουργία που επιτρέπει να θέτουμε τις ώρες υπερωριακής εργασίας στο τέλος του μήνα (μέθοδος `setHours`)    
- Υπολογισμός  και  επιστροφή  του  συνόλου  για  τον  τρέχοντα  μήνα  (μέθοδος `calculateSalary`)    
- Εκτύπωση της κατάστασης μισθοδοσίας ως εξής (μέθοδος `printPayrollReport`): 

```
    PAYMENT RECORD FOR A FULL TIME EMPLOYEE         
    Employee's Last Name: <ονοματεπώνσμο> 
    Tax ID number: <αριθμός φορολογικού μητρώοσ> 
    Hours: <ώρες> 
    Monthly Total: <σύνολο τρέτοντα μήνα> Euro 
    Remarks 
    Work hours refer to overtime work Monthly Salary = <μηνιαίος μισθός> Euro 
```

Για να εμφανιστεί το <σύνολο τρέτοντα μήνα> θα κληθεί η μέθοδος calculateSalary. 

Η  σχεδίαση  των  κλάσεων  (ιδιότητες  και  λειτουργίες)  να  γίνει  αξιοποιώντας  τις  τεχνικές  της κληρονομικότητας, του πολυμορφισμού και της επικάλυψης (υποσκέλισης).  

Στη συνέχεια, ορίστε μια κλάση, έστω `Branch`, για την αναπαράσταση των υποκαταστημάτων της προαναφερθείσας αλυσίδας και θα περιλαμβάνει: 

- Ιδιότητες για την αποθήκευση της διεύθυνσης (πόλη - περιοχή) του υποκαταστήματος και των  υπαλλήλων  του.  Θεωρείστε  ότι  ένα  υποκατάστημα  μπορεί  να  έχει  το  πολύ  100 υπαλλήλους.    
- Κατασκευαστή  
- Μέθοδο `addEmployee` που δέχεται μέσω παραμέτρου ένα αντικείμενο-υπάλληλο και τον καταχωρεί στο υποκατάστημα.  
- Μέθοδο `setEmployeeHours` που δέχεται τον μήνα ως ένα αλφαριθμητικό και ζητά από τον  χρήστη  να  εισάγει  από  το  πληκτρολόγιο  τις  ώρες  εργασίας  του  κάθε  υπαλλήλου εμφανίζοντας το μήνυμα: 

```
Enter the number of hours for employee <όνομα-υπαλλήλου>: 
```

και καλώντας τη μέθοδο setHours θέτει τις ώρες εργασίας (ή υπερωριακής απασχόλησης) για τον αντίστοιχο υπάλληλο. 

- Μέθοδο printPayroll που δέχεται τον μήνα ως ένα αλφαριθμητικό και εκτυπώνει την κατάσταση μισθοδοσίας για κάθε υπάλληλο καλώντας την αντίστοιχη μέθοδο 

*Δείτε τον τρόπο εμφάνισης των μηνυμάτων/πληροφοριών των δύο παραπάνω μεθόδων στο παράδειγμα εκτέλεσης που παρατίθεται στο τέλος.* 

Τέλος, ορίστε τη μέθοδο `main` όπου: 

- θα δημιουργείται ένα υποκατάστημα (αντικείμενο της κλάσης `Branch`)    
- θα δημιουργείται ένας ωρομίσθιος υπάλληλος με όνομα *Nikolaou*, ΑΦΜ *067832710* και ωριαία αποζημίωση *4* Ευρώ   
- θα δημιουργείται ένας υπάλληλος πλήρους απασχόλησης με όνομα *Papadopoulos*, ΑΦΜ *067832711* και μηνιαίο μισθό 1300 Ευρώ   
- θα καταχωρούνται οι υπάλληλοι στο υποκατάστημα   
- θα  καλείται  για  το  υποκατάστημα  και  για  τον  μήνα  DECEMBER  η  μέθοδος `setEmployeeHours` και στη συνέχεια η μέθοδος εμφάνισης της μισθοδοσίας όλων των υπαλλήλων (`printPayroll`).   

Παρακάτω φαίνεται ένα παράδειγμα εκτέλεσης του προγράμματος: 

```
Enter the number of hours for employee Nikolaou: 100 
Enter the number of hours for employee Papadopoulos: 26 
********** PAYROLL for DECEMBER **********
Branch: Thessaloniki, Tsimiski Branch 

PAYMENT RECORD FOR A PART TIME EMPLOYEE Employee's Last Name: Nikolaou 
Tax ID number: 067832710 
Hours: 100 
Monthly Total: 480.0Euro 
Remarks 
Hourly wage = 4 Euro 

PAYMENT RECORD FOR A FULL TIME EMPLOYEE Employee's Last Name: Papadopoulos 
Tax ID number: 067832711 
Hours: 26 
Monthly Total: 1469.0Euro 
Remarks 
Work hours refer to overtime work 
Monthly Salary = 1300 Euro
```