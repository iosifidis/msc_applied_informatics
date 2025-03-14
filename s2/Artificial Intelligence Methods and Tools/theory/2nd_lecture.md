### Σημειώσεις για την Ταξινόμηση

#### Εισαγωγή
Η ταξινόμηση είναι μια βασική τεχνική της μηχανικής μάθησης που χρησιμοποιείται για τη διάκριση μεταξύ διαφορετικών κατηγοριών ή τάξεων. Ένα συνηθισμένο παράδειγμα είναι η ανίχνευση καρκίνου του μαστού, όπου τα δεδομένα αποτελούνται από δείκτες φυσιολογίας του όγκου. Το μοντέλο ταξινόμησης πρέπει να μπορεί να προβλέψει αν ένας όγκος είναι κακοήθης (καρκινικός) ή καλοήθης (μη καρκινικός).

#### Πρόβλημα και Απαιτήσεις
Το πρόβλημα περιλαμβάνει τη δημιουργία ενός μοντέλου που θα λαμβάνει 30 τιμές ως είσοδο και θα προβλέπει την κατάσταση του όγκου. Οι απαιτήσεις περιλαμβάνουν:
- Επιτυχής ταξινόμηση τουλάχιστον του 90% των κακοήθων όγκων.
- Επιτυχής ταξινόμηση τουλάχιστον του 80% των καλοήθων όγκων.

### Προεπεξεργασία Δεδομένων
- **Καθαρισμός δεδομένων**: Δεν υπάρχουν ελλιπείς τιμές στα δεδομένα.
- **Κλίμακες χαρακτηριστικών**: Οι τιμές των χαρακτηριστικών παρουσιάζουν σημαντική απόκλιση (π.χ. από 0 έως 2250), γεγονός που απαιτεί κανονικοποίηση.
- **Οπτικοποίηση δεδομένων**: Χρήση γραφημάτων, όπως boxplots και ιστογράμματα, για την κατανόηση της κατανομής των χαρακτηριστικών.

#### Ανισορροπία Κλάσεων
- Αν ο αριθμός των παραδειγμάτων μιας κατηγορίας είναι πολύ μικρός, το μοντέλο μπορεί να δώσει υπερβολική βαρύτητα στην πλειοψηφική κατηγορία.
- Τεχνικές αντιμετώπισης:
  - Υπερδειγματοληψία (Oversampling)
  - Υποδειγματοληψία (Undersampling)
  - Χρήση βαρών κλάσης

### Αλγόριθμοι Ταξινόμησης
- **K-Nearest Neighbors (KNN)**: Ταξινομεί βάσει των k πιο κοντινών σημείων. Προβλήματα:
  - Αργός υπολογισμός για μεγάλα σύνολα δεδομένων.
  - Δυσκολία σε ανισόρροπα δεδομένα.
- **Δέντρα Απόφασης**: Χωρίζουν τα δεδομένα με βάση χαρακτηριστικά και τιμές. Κατάλληλα για ερμηνεύσιμα μοντέλα.
- **Γραμμικός Διαχωρισμός**: Κατάλληλος για δεδομένα που είναι γραμμικά διαχωρίσιμα.
- **Μη γραμμικοί διαχωριστές (SVC)**: Κατάλληλοι για σύνθετα σύνολα δεδομένων που δεν διαχωρίζονται γραμμικά.
- **Ναϊβής Ταξινομητής Bayes (Naïve Bayes)**: Απλός και γρήγορος, ιδανικός όταν τα χαρακτηριστικά είναι ανεξάρτητα.

### Μέτρηση Απόδοσης
Η αξιολόγηση της απόδοσης των μοντέλων γίνεται με διάφορους δείκτες:
- **Ορθότητα (Accuracy)**: Συνολικό ποσοστό σωστών προβλέψεων.
- **Ανάκτηση (Recall)**: Ποσοστό των θετικών περιπτώσεων που προβλέφθηκαν σωστά.
- **Ακρίβεια (Precision)**: Ποσοστό των θετικών προβλέψεων που είναι σωστές.
- **F1-Score**: Ο μέσος όρος αρμονικής ακρίβειας και ανάκλησης.
- **Μητρώο Σύγχυσης (Confusion Matrix)**: Παρουσίαση των αληθινών θετικών (TP), αληθινών αρνητικών (TN), ψευδών θετικών (FP) και ψευδών αρνητικών (FN).

### Συμπεράσματα
Η ταξινόμηση αποτελεί βασικό εργαλείο στη μηχανική μάθηση με εφαρμογές όπως η ανίχνευση καρκίνου. Η σωστή επιλογή μοντέλου και οι κατάλληλες τεχνικές επεξεργασίας δεδομένων είναι απαραίτητες για αξιόπιστα αποτελέσματα.


