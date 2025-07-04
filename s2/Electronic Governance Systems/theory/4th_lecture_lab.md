# BPMN Tasks & Subprocesses

---

#### **1. Εισαγωγή στις Επιχειρησιακές Διαδικασίες με BPMN**
- Η BPMN (Business Process Model and Notation) αποτελεί το βασικό πρότυπο για την αναπαράσταση και μοντελοποίηση επιχειρησιακών διαδικασιών.
- Βασικός στόχος της BPMN είναι η κατανοητή και σαφής αναπαράσταση των διαδικασιών για όλους τους χρήστες (αναλυτές, προγραμματιστές, στελέχη).
- Η χρήση της BPMN ενισχύει την οπτικοποίηση και την κατανόηση των ροών εργασιών, καθώς και τη βελτίωση της διαλειτουργικότητας μεταξύ διαφορετικών συστημάτων.

---

#### **2. Κατηγορίες Μοντέλων στη BPMN**
- **Ιδιωτικές Διαδικασίες (Private Processes):** Αφορούν εσωτερικές διεργασίες που δεν είναι ορατές σε εξωτερικά μέρη.
- **Συνεργατικές Διαδικασίες (Collaborative Processes):** Αναπαριστούν αλληλεπιδράσεις μεταξύ διαφορετικών οργανισμών ή τμημάτων.
- **Δεξαμενές (Pools):** Απεικονίζουν αυτόνομες επιχειρησιακές διαδικασίες διαφορετικών οντοτήτων.
- **Λωρίδες (Lanes):** Υποδιαιρέσεις δεξαμενών που αναπαριστούν επιμέρους τμήματα ή ρόλους εντός της ίδιας οντότητας.

---

#### **3. Δραστηριότητες στη BPMN**
- Οι δραστηριότητες (tasks) αντιπροσωπεύουν συγκεκριμένες ενέργειες εντός μιας διαδικασίας.
- Οι κύριοι τύποι δραστηριοτήτων είναι:
  - **Undefined Task:** Γενική δραστηριότητα χωρίς συγκεκριμένο χαρακτηρισμό.
  - **Receive Task:** Λήψη μηνύματος.
  - **Script Task:** Εκτέλεση κώδικα μέσω script.
  - **Manual Task:** Χειροκίνητη εκτέλεση από άνθρωπο.
  - **Service Task:** Κλήση πληροφοριακού συστήματος ή υπηρεσίας.
  - **User Task:** Ανάθεση σε χρήστη μέσω συστήματος.
  - **Send Task:** Αποστολή δεδομένων ή μηνυμάτων.
  - **Business Rule Task:** Εφαρμογή επιχειρησιακού κανόνα.

---

#### **4. Χαρακτηρισμοί Δραστηριοτήτων (Task Markers)**
- **Loop:** Η δραστηριότητα επαναλαμβάνεται μέχρι να ικανοποιηθεί μια συνθήκη.
- **Parallel/Sequential Multiple Instance:** Η δραστηριότητα εκτελείται πολλαπλές φορές είτε σειριακά είτε παράλληλα, ανάλογα με τη ρύθμιση.

---

#### **5. Υπο-διαδικασίες (Sub-processes)**
- Οι υπο-διαδικασίες συμβάλλουν στην απόκρυψη και διαχείριση της πολυπλοκότητας των επιχειρησιακών διαδικασιών.
- Διακρίνονται σε:
  - **Collapsed View:** Εμφανίζονται σαν απλό task.
  - **Expanded View:** Εμφανίζονται οι επιμέρους δραστηριότητες της υπο-διαδικασίας.

---

#### **6. Συνεργατικές Διαδικασίες και Δεξαμενές (Pools)**
- Οι συνεργατικές διαδικασίες (collaborative business processes) αναπαριστούν τις αλληλεπιδράσεις μεταξύ ανεξάρτητων οντοτήτων.
- **Δεξαμενές (Pools):** Αναπαριστούν διακριτές οντότητες που αλληλεπιδρούν μέσω ροών μηνυμάτων.
- **Λωρίδες (Lanes):** Αντιπροσωπεύουν επιμέρους λειτουργίες ή ρόλους εντός της δεξαμενής.

---

#### **7. Σύνδεσμοι και Ροές στη BPMN**
- **Ροή Αλληλουχίας (Sequence Flow):** Συνδέει δραστηριότητες εντός της ίδιας δεξαμενής.
- **Ροή Μηνυμάτων (Message Flow):** Ανταλλαγή πληροφοριών μεταξύ διαφορετικών δεξαμενών.
- **Συσχέτιση (Association):** Χρήση σχολίων ή ροών δεδομένων μεταξύ δραστηριοτήτων.

---

#### **8. Σημεία Αλληλεπίδρασης (Message Flows)**
- Χρησιμοποιούνται για την αναπαράσταση της επικοινωνίας μεταξύ συμμετεχόντων.
- Η ροή μηνύματος (message flow) αναπαρίσταται με διακεκομμένο βέλος και συνοδεύεται από λεκτική περιγραφή.
- Η ροή μηνύματος χρησιμοποιείται μόνο για την επικοινωνία μεταξύ διαφορετικών δεξαμενών.

---

#### **9. Δημόσια και Ιδιωτική Οπτική Διαδικασίας**
- **Δημόσια Οπτική (Public View):** Εμφάνιση της ροής της διαδικασίας μέσα στη δεξαμενή.
- **Ιδιωτική Οπτική (Private View):** Η δεξαμενή θεωρείται "μαύρο κουτί" (black box pool), χωρίς πρόσβαση στη ροή.

---

#### **10. Παρατηρήσεις**

1. Η ροή μιας δεξαμενής δεν θα πρέπει να διακόπτεται   
2. Μία ροή ακολουθίας δεν μπορεί να διαπερνά τα όρια της δεξαμενής στην οποία βρίσκεται   
3. Μια ροή μηνύματος δεν μπορεί να συνδέει δραστηριότητες της ίδιας δεξαμενής.   
4. Μια ροή μηνύματος χρησιμοποιείται μόνο για να αποδώσει τις αλληλεπιδράσεις μεταξύ διαφορετικών δεξαμενών.   
5. Μία ροή ακολουθίας χρησιμοποιείται μόνο για να συνδέσει τις δραστηριότητες της ίδιας δεξαμενής.   

---

#### **11. Πλεονεκτήματα Χρήσης BPMN**
- **Αποδοτικότητα:** Βελτιώνει τη διαχείριση σύνθετων διαδικασιών.
- **Κατανόηση:** Ενισχύει την επικοινωνία και την κατανόηση από όλους τους εμπλεκόμενους.
- **Ανάλυση και Βελτιστοποίηση:** Διευκολύνει την αποτύπωση και ανάλυση σεναρίων.
- **Διαλειτουργικότητα:** Εξασφαλίζει τη συνεργασία μεταξύ πληροφοριακών συστημάτων.

---

#### **12. Συμπεράσματα**
- Η BPMN παρέχει μια τυποποιημένη και οπτικά κατανοητή γλώσσα για την αναπαράσταση επιχειρησιακών διαδικασιών.
- Ο διαχωρισμός δραστηριοτήτων, υπο-διαδικασιών και συνεργατικών μοντέλων επιτρέπει την ορθολογική διαχείριση και βελτίωση των διαδικασιών.
- Η χρήση υπο-διαδικασιών και συνεργατικών μοντέλων εξασφαλίζει την απλοποίηση και την κατανόηση πολύπλοκων επιχειρησιακών ροών.
