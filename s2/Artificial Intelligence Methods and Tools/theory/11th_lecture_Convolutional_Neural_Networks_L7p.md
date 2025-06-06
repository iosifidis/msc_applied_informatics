# **Συνελικτικά Νευρωνικά Δίκτυα (Convolutional Neural Networks - CNNs)**

### **Εισαγωγή & Πρόκληση Αναγνώρισης Εικόνων (Διαφάνειες 1-7)**

*   **Θέμα:** Συνελικτικά Νευρωνικά Δίκτυα (CNNs).
*   **Πρόκληση στην Ανάλυση Εικόνας (Image Analysis):**
    *   Η αναγνώριση αντικειμένων (π.χ., κύκνων) σε εικόνες είναι πολύπλοκη.
    *   Τα αντικείμενα μπορούν να εμφανίζονται με διαφορετικά σχήματα, προσανατολισμούς, μερική απόκρυψη, και παραλλαγές (π.χ., λευκός vs. μαύρος κύκνος).
    *   Η χειροκίνητη περιγραφή χαρακτηριστικών (manual feature engineering) είναι δύσκολη και μη επεκτάσιμη για όλες τις πιθανές περιπτώσεις (π.χ., φουσκωτός κύκνος, άνθρωπος σε σκηνή κύκνου).

### **Εκμάθηση Αναπαραστάσεων & Περιορισμοί των MLPs (Διαφάνειες 8-10)**

*   **Εκμάθηση Αναπαραστάσεων (Representation Learning / Feature Learning):**
    *   Αντί να ορίζουμε χειροκίνητα τα χαρακτηριστικά, θέλουμε ένα σύστημα που να **μαθαίνει αυτόματα** τα σχετικά χαρακτηριστικά από τα δεδομένα.
    *   Τεχνικές: Μη επιβλεπόμενες (K-means, PCA) και Επιβλεπόμενες (Νευρωνικά Δίκτυα).
*   **Μειονεκτήματα των Πολυεπίπεδων Perceptrons (MLPs) για Εικόνες:**
    1.  **Πλεονάζοντα Χαρακτηριστικά & Έλλειψη Ανθεκτικότητας στη Θέση (Διαφ. 9):**
        *   Ένα MLP θα μάθαινε διαφορετικά βάρη για την ανίχνευση του ίδιου αντικειμένου (π.χ., γάτα) σε διαφορετικές θέσεις της εικόνας. Αυτό είναι αναποτελεσματικό και οδηγεί σε εκμάθηση πλεοναζόντων χαρακτηριστικών.
    2.  **Υψηλή Υπολογιστική Πολυπλοκότητα & Υπερπροσαρμογή (Overfitting) (Διαφ. 10):**
        *   Για μια τυπική εικόνα (π.χ., 224x224x3 από ImageNet), κάθε νευρώνας στο πρώτο επίπεδο ενός MLP θα είχε πάρα πολλά βάρη (π.χ., 224*224*3 = 150,129 βάρη ανά νευρώνα).
        *   Ακόμα και με ένα μικρό πρώτο επίπεδο (π.χ., 128 νευρώνες), ο συνολικός αριθμός παραμέτρων γίνεται τεράστιος.
        *   Αυτό οδηγεί σε εξαιρετικά υψηλή πολυπλοκότητα μοντέλου και έντονη τάση για υπερπροσαρμογή.

### **Ιδιότητες των Εικόνων & η Λειτουργία της ΣΥΝΕΛΙΞΗΣ (Διαφάνειες 11-13)**

*   **Οι Εικόνες είναι Τοπικές και Ιεραρχικές (Local and Hierarchical) (Διαφ. 11):**
    *   **Τοπικότητα:** Γειτονικά pixels σχετίζονται ισχυρότερα από απομακρυσμένα pixels.
    *   **Ιεραρχία:** Τα αντικείμενα δομούνται από μικρότερα μέρη (π.χ., ένα πρόσωπο αποτελείται από μάτια, μύτη, στόμα, τα οποία αποτελούνται από ακμές και υφές).
*   **Οι Εικόνες είναι Αμετάβλητες (Invariant) (Διαφ. 12):**
    *   Η ταυτότητα ενός αντικειμένου δεν αλλάζει με μικρές μεταβολές στην:
        *   **Θέση (Translation):** Ένας ηλίανθος είναι ηλίανθος όπου κι αν βρίσκεται στην εικόνα.
        *   **Κλίμακα (Scale):** Ένας μικρός ή μεγάλος ηλίανθος είναι ακόμα ηλίανθος.
        *   **Περιστροφή (Rotation):** Ένας περιστραμμένος ηλίανθος είναι ακόμα ηλίανθος.
        *   (Και άλλες παραμορφώσεις όπως φωτισμός, προοπτική κ.λπ.)
*   **Λειτουργία "ΣΥΝΕΛΙΞΗΣ" (Convolution Operation) (Διαφ. 13):**
    *   **Κλειδί για τα CNNs!**
    *   Ένα μικρό **φίλτρο (filter) ή πυρήνας (kernel)** (π.χ., 3x3) "γλιστράει" πάνω στην εικόνα εισόδου.
    *   Σε κάθε θέση, υπολογίζεται το **εσωτερικό γινόμενο** (dot product) μεταξύ των τιμών του φίλτρου και των αντίστοιχων τιμών των pixels της εικόνας που καλύπτει το φίλτρο.
    *   Το αποτέλεσμα αυτού του υπολογισμού γίνεται ένα pixel στην **εικόνα εξόδου (feature map)**.
    *   Παράδειγμα: `(-1*3) + (0*0) + (1*1) + (-2*2) + (0*6) + (2*2) + (-1*2) + (0*4) + (1*1) = -3`.
    *   **"Suddenly, life changed!"** - Η συνέλιξη επιτρέπει την αποτελεσματική επεξεργασία εικόνων.

### **Η Επανάσταση των CNNs (Διαφάνειες 14-16)**

*   **Διαγωνισμός ImageNet 2012 - Μια Νέα Εποχή (Διαφ. 14):**
    *   Το CNN με όνομα "SuperVision" (αργότερα γνωστό ως AlexNet) είχε δραματικά χαμηλότερο σφάλμα (error rate ~15%) σε σχέση με τις παραδοσιακές μεθόδους (~26-36%).
    *   Αυτό σηματοδότησε την κυριαρχία των CNNs στην αναγνώριση εικόνων.
*   **Πώς Είναι Δυνατόν; Αρχιτεκτονική End-to-End (Διαφ. 15):**
    *   **Παλιά Προσέγγιση (Old school approach):** Πολλά διακριτά, χειροκίνητα βήματα: Εικόνα -> Σχολιασμός μερών -> Δημιουργία παραμορφώσιμου αντικειμένου μερών -> Εκμάθηση κανονικοποιημένης πόζας -> Εξαγωγή χαρακτηριστικών -> Χρήση ταξινομητή. Πολύπλοκο και εύθραυστο.
    *   **Νέα Εποχή (The new era - CNNs):** Το CNN μαθαίνει τις αναπαραστάσεις (χαρακτηριστικά) απευθείας από τα δεδομένα (end-to-end). Η διαδικασία εξαγωγής χαρακτηριστικών και ταξινόμησης είναι ενσωματωμένη και βελτιστοποιείται από κοινού.
*   **CNN Features Off-the-Shelf (Διαφ. 16):**
    *   Μελέτη (Razavian et al. CVPR 2014) έδειξε ότι τα χαρακτηριστικά που εξάγονται από προ-εκπαιδευμένα CNNs (ακόμα και χωρίς fine-tuning) αποτελούν μια εξαιρετικά ισχυρή γραμμή βάσης (baseline) για διάφορες εργασίες αναγνώρισης, συχνά ξεπερνώντας τις state-of-the-art μεθόδους της εποχής.

### **Αρχιτεκτονική ενός CNN (Διαφάνεια 17)**

*   **Τυπική Δομή:**
    1.  **Είσοδος (Input):** Η αρχική εικόνα.
    2.  **Συνελικτικό Επίπεδο 1 (Convolutional layer 1) + ReLU:**
        *   Εφαρμογή φίλτρων (συνελίξεις) για τη δημιουργία **χαρτών χαρακτηριστικών (feature maps)**.
        *   Εφαρμογή συνάρτησης ενεργοποίησης ReLU (Rectified Linear Unit) για εισαγωγή μη-γραμμικότητας.
    3.  **Συγκέντρωση 1 (Pooling 1):**
        *   Μείωση της διάστασης των feature maps (π.χ., max pooling).
        *   Δημιουργία **συγκεντρωτικών χαρτών χαρακτηριστικών (pooled feature maps)**.
    4.  **Συνελικτικό Επίπεδο 2 (Convolutional layer 2) + ReLU:** Περισσότερες συνελίξεις και ReLU.
    5.  **Συγκέντρωση 2 (Pooling 2):** Περαιτέρω μείωση διάστασης.
    6.  **(Προαιρετικά: Global Average Pooling - όπως στην αρχική διαφάνεια 1)**
    7.  **Πλήρως Συνδεδεμένο Επίπεδο 1 (Fully-connected 1):** Οι τελικοί feature maps "ισοπεδώνονται" (flattened) και τροφοδοτούνται σε ένα ή περισσότερα πλήρως συνδεδεμένα επίπεδα.
    8.  **Έξοδος (Outputs):** Συνήθως ένα πλήρως συνδεδεμένο επίπεδο με συνάρτηση ενεργοποίησης Softmax για την παραγωγή πιθανοτήτων κλάσης `p(y|x)`.

### **Λειτουργίες Κλειδιά σε ένα CNN (Διαφάνειες 18-22)**

1.  **Συνέλιξη ως Εξαγωγή Χαρακτηριστικών (Convolution as feature extraction) (Διαφ. 18-20):**
    *   Ένα φίλτρο (π.χ., ανιχνευτής ακμών Gabor) εφαρμόζεται στην εικόνα εισόδου.
    *   Ο παραγόμενος feature map επισημαίνει τις περιοχές της εικόνας όπου το συγκεκριμένο χαρακτηριστικό (που ανιχνεύει το φίλτρο) είναι παρόν.
    *   Τα **φίλτρα μαθαίνονται** κατά την εκπαίδευση του CNN.
2.  **Μη-Γραμμικότητα (Non-linearity) (Διαφ. 21):**
    *   Συνήθως η **ReLU (Rectified Linear Unit): `f(x) = max(0, x)`**.
    *   Εφαρμόζεται στοιχείο προς στοιχείο στους feature maps μετά τη συνέλιξη.
    *   Εισάγει μη-γραμμικότητα, επιτρέποντας στο δίκτυο να μάθει πιο πολύπλοκες σχέσεις.
3.  **Χωρική Συγκέντρωση (Spatial Pooling) (Διαφ. 22):**
    *   Συνήθως **Max Pooling**.
    *   Μια μικρή περιοχή (π.χ., 2x2) του feature map συνοψίζεται στην μέγιστη τιμή της.
    *   **Στόχοι:**
        *   Μειώνει τη χωρική διάσταση των feature maps (μειώνει τον αριθμό των παραμέτρων και τον υπολογιστικό φόρτο).
        *   Παρέχει κάποιο βαθμό **αμεταβλητότητας στη μετάθεση (translation invariance)** (μικρές μετατοπίσεις του χαρακτηριστικού εντός της περιοχής pooling δεν επηρεάζουν την έξοδο).

### **Βασικές Αρχές των CNNs (Διαφάνειες 23-24)**

*   **Γιατί CNNs αντί για MLPs για εικόνες; (Διαφ. 23):**
    *   MLPs **δεν κλιμακώνονται καλά** για εικόνες.
    *   MLPs **αγνοούν** την πληροφορία που προέρχεται από τη **θέση των pixels** και τη **συσχέτιση με γείτονες**.
    *   MLPs **δεν μπορούν να χειριστούν μεταθέσεις** (αν το αντικείμενο μετακινηθεί, χρειάζονται νέα βάρη).
*   **Γενική Ιδέα των CNNs (Διαφ. 23):** Προσαρμόζονται έξυπνα στις ιδιότητες των εικόνων:
    *   Η θέση των pixels και η γειτονιά τους έχουν **σημασιολογικό νόημα**.
    *   Τα στοιχεία ενδιαφέροντος μπορούν να εμφανιστούν **οπουδήποτε στην εικόνα**.
*   **Δομή CNN (Διαφ. 24):**
    *   Αποτελούνται από επίπεδα, αλλά **όχι πλήρως συνδεδεμένα** (όπως τα MLPs).
    *   Χρησιμοποιούν **φίλτρα (filters)**: σύνολα από κυβοειδή βάρη που εφαρμόζονται σε όλη την εικόνα. Κάθε 2D "φέτα" ενός φίλτρου ονομάζεται **πυρήνας (kernel)**.
    *   Αυτά τα φίλτρα εισάγουν:
        *   **Αμεταβλητότητα στη Μετάθεση (Translation Invariance):** Το ίδιο φίλτρο χρησιμοποιείται για την ανίχνευση ενός χαρακτηριστικού οπουδήποτε στην εικόνα.
        *   **Κοινή Χρήση Παραμέτρων (Parameter Sharing):** Τα βάρη ενός φίλτρου είναι τα ίδια καθώς αυτό "γλιστράει" πάνω στην εικόνα. Αυτό μειώνει δραστικά τον αριθμό των παραμέτρων σε σχέση με τα MLPs.
    *   **Πώς εφαρμόζονται; Μέσω Συνελίξεων!**

### **Συνέλιξη και Διασταυρούμενη Συσχέτιση (Διαφάνεια 25)**

*   **Συνέλιξη (Convolution):** `(f * g)(t) = ∫ f(a)g(t - a)da`
    *   Μία από τις συναρτήσεις **αντιστρέφεται (inverted)** και μετατοπίζεται.
*   **Διακριτή Συνέλιξη:** `(f * g)(t) = Σₐ f(a)g(t - a)`
*   **Διακριτή Διασταυρούμενη Συσχέτιση (Cross-correlation):** `(f ⋆ g)(t) = Σₐ f(a)g(t + a)`
    *   Δεν υπάρχει αντιστροφή της δεύτερης συνάρτησης.
    *   **Σημείωση:** Στα πλαίσια των CNNs, ο όρος "συνέλιξη" συχνά αναφέρεται στην πράξη της διασταυρούμενης συσχέτισης, καθώς τα φίλτρα μαθαίνονται και η αντιστροφή δεν είναι κρίσιμη (μπορεί να απορροφηθεί στα μαθημένα βάρη).

### **Λεπτομέρειες Λειτουργίας Συνέλιξης (Διαφάνειες 26-45)**

*   **Συνελίξεις Βήμα προς Βήμα (Διαφ. 26):** Οπτικοποίηση της εφαρμογής ενός φίλτρου (π.χ., 3x3x βάθος_εισόδου) σε μια περιοχή της εικόνας εισόδου για την παραγωγή ενός μόνο αριθμού στην εικόνα εξόδου.
*   **Stride (Βήμα) (Διαφ. 27-37):**
    *   Καθορίζει πόσο "γλιστράει" το φίλτρο στην εικόνα εισόδου σε κάθε βήμα.
    *   **Stride = 1 (Διαφ. 27-32):** Το φίλτρο μετακινείται κατά ένα pixel κάθε φορά.
    *   Σε κάθε θέση, γίνεται ένα **3D άθροισμα** (λαμβάνοντας υπόψη το βάθος/κανάλια) (Διαφ. 33).
    *   **Stride > 1 (π.χ., stride = 2) (Διαφ. 34-37):** Το φίλτρο μετακινείται κατά περισσότερα pixels. Αυτό οδηγεί σε μικρότερο feature map στην έξοδο (downsampling). Μπορεί να μην καλύψει όλη την είσοδο.
*   **Τι Συμβαίνει στις Άκρες; (Convolutions – what happens at the edges?) (Διαφ. 38):**
    *   Χωρίς padding, η εφαρμογή συνελίξεων μειώνει το μέγεθος της εικόνας εξόδου, ανάλογα με το μέγεθος του φίλτρου.
*   **Padding (Συμπλήρωση) (Διαφ. 39-43):**
    *   Προσθήκη pixels (συνήθως μηδενικών) γύρω από τα όρια της εικόνας εισόδου.
    *   **Full padding:** Προσθέτει τόσα μηδενικά ώστε όλα τα pixels της αρχικής εισόδου να "επισκεφθούν" από το φίλτρο τον ίδιο αριθμό φορών. Αυξάνει το μέγεθος της εξόδου.
    *   **Same padding (ή 'half' padding):** Εξασφαλίζει ότι η εικόνα εξόδου έχει το **ίδιο χωρικό μέγεθος** με την εικόνα εισόδου (για stride=1).
    *   Παράδειγμα με pad=1, stride=2 (Διαφ. 40-43).
*   **Μέγεθος Εξόδου Συνέλιξης (Διαφ. 44-45):**
    *   Γενικός τύπος για το πλάτος (ή ύψος) της εξόδου:
        `w_out = floor((w_in + 2p - k) / s) + 1`
        όπου:
        *   `w_in`: πλάτος εισόδου
        *   `p`: padding
        *   `k`: μέγεθος πυρήνα (kernel size)
        *   `s`: stride
    *   **Παράδειγμα (VGGNet-like):** k=3, s=1, p=1 => `w_out = floor((w_in + 2*1 - 3) / 1) + 1 = w_in`. Αυτό είναι το "same" padding.

### **Συνελικτικά Επίπεδα (Convolutional Layers) (Διαφάνειες 46-47)**

*   **Ασπρόμαυρη Εικόνα (1 κανάλι) (Διαφ. 46, αριστερά):**
    *   Η είσοδος είναι 2D.
    *   Τα φίλτρα είναι 2D (π.χ., 3x3).
    *   Κάθε φίλτρο παράγει έναν 2D feature map. Πολλαπλά φίλτρα παράγουν πολλαπλούς feature maps.
*   **Έγχρωμη Εικόνα (π.χ., RGB - 3 κανάλια) (Διαφ. 46, δεξιά):**
    *   Η είσοδος είναι 3D (πλάτος x ύψος x βάθος/κανάλια).
    *   Τα φίλτρα είναι επίσης **3D (κύβοι)**. Το βάθος του φίλτρου πρέπει να ταιριάζει με το βάθος της εισόδου.
    *   Κάθε 3D φίλτρο εφαρμόζεται σε **ολόκληρο το βάθος** της εισόδου.
    *   Κάθε (3D) φίλτρο παράγει έναν **2D feature map** (Διαφ. 47).
    *   Αν έχουμε πολλαπλά φίλτρα, η έξοδος του συνελικτικού επιπέδου είναι ένας 3D "κύβος" από feature maps (ένας 2D feature map ανά φίλτρο).
    *   Η διάσταση του feature map (αριθμός φίλτρων) μπορεί να αλλάξει δραστικά από το ένα συνελικτικό επίπεδο στο επόμενο.

### **Γιατί Λειτουργεί; (Διαφάνεια 48)**

*   Μια εικόνα είναι απλά ένας πίνακας από pixels.
*   Η συνέλιξη της εικόνας με ένα φίλτρο παράγει έναν feature map.
*   Αυτός ο χάρτης **επισημαίνει την παρουσία ενός συγκεκριμένου χαρακτηριστικού** (που ανιχνεύει το φίλτρο) στην εικόνα.

### **Εκμάθηση στα CNNs & Μη-Γραμμικότητα (Διαφάνειες 49-50)**

*   **Εκμάθηση Φίλτρων (Διαφ. 49):**
    *   Σε ένα συνελικτικό επίπεδο, εφαρμόζουμε πολλαπλά φίλτρα για να εξάγουμε διαφορετικά χαρακτηριστικά.
    *   Το πιο σημαντικό: **Μαθαίνουμε αυτά τα φίλτρα!** Τα βάρη των φίλτρων είναι οι παράμετροι που προσαρμόζονται κατά την εκπαίδευση.
*   **Έλλειψη Μη-Γραμμικότητας (Διαφ. 49):** Μέχρι στιγμής, η συνέλιξη είναι μια γραμμική πράξη. Χρειαζόμαστε μη-γραμμικότητα.
*   **Εισαγωγή ReLU (Διαφ. 50):**
    *   Η πιο επιτυχημένη μη-γραμμικότητα για CNNs είναι η **Rectified Linear Unit (ReLU): `Output = Max(0, Input)`**.
    *   **Πλεονεκτήματα:**
        *   Καταπολεμά το πρόβλημα των "εξαφανιζόμενων κλίσεων" (vanishing gradients) που εμφανίζεται στις σιγμοειδείς.
        *   Είναι ευκολότερη στον υπολογισμό.
        *   Παράγει **αραιότητα (sparsity)** (πολλές έξοδοι είναι μηδέν), κάτι που μπορεί να είναι υπολογιστικά αποδοτικό (αν και όχι πάντα ευεργετικό για την απόδοση).

### **Σύνοψη Συνελικτικών Επιπέδων (Διαφάνεια 51)**

*   Ένα συνελικτικό επίπεδο εφαρμόζει συνέλιξη μεταξύ καθενός από τα φίλτρα του και της εισόδου.
*   **Είσοδος:** 3D τανυστής (Πλάτος x Ύψος x Κανάλια/Feature Maps).
*   **Έξοδος:** 3D τανυστής (Πλάτος x Ύψος x Νέοι Feature Maps - ένας για κάθε φίλτρο).
*   Εφαρμόζει μη-γραμμική συνάρτηση ενεργοποίησης (συνήθως ReLU) σε κάθε τιμή της εξόδου.
*   **Πολλαπλές παράμετροι προς ορισμό:** Αριθμός φίλτρων, μέγεθος φίλτρων, stride, padding, συνάρτηση ενεργοποίησης, κανονικοποίηση.

### **Δόμηση ενός CNN (Διαφάνειες 52-57)**

*   Ένα CNN χτίζεται στοιβαδίζοντας επίπεδα, τυπικά 3 ειδών:
    1.  **Συνελικτικά Επίπεδα (Convolutional Layers) (Διαφ. 53):**
        *   **Δράση:** Εφαρμογή φίλτρων για εξαγωγή χαρακτηριστικών. Φίλτρα από μικρούς, μαθημένους πυρήνες. Ένα bias ανά φίλτρο. Εφαρμογή συνάρτησης ενεργοποίησης.
        *   **Παράμετροι:** Αριθμός πυρήνων, μέγεθος πυρήνων (W, H - το Βάθος D ορίζεται από την είσοδο), συνάρτηση ενεργοποίησης, stride, padding, τύπος κανονικοποίησης.
        *   **Είσοδος/Έξοδος (I/O):** Είσοδος 3D κύβος (προηγούμενο σύνολο feature maps). Έξοδος 3D κύβος (ένας 2D χάρτης ανά φίλτρο).
    2.  **Επίπεδα Συγκέντρωσης (Pooling Layers) (Διαφ. 55):**
        *   **Δράση:** Μείωση διαστατικότητας. Εξαγωγή του μέγιστου ή του μέσου όρου μιας περιοχής. Προσέγγιση συρόμενου παραθύρου.
        *   **Παράμετροι:** Stride, μέγεθος παραθύρου.
        *   **Είσοδος/Έξοδος (I/O):** Είσοδος 3D κύβος. Έξοδος 3D κύβος (ένας 2D χάρτης ανά φίλτρο εισόδου, αλλά με μειωμένες χωρικές διαστάσεις).
    3.  **Πλήρως Συνδεδεμένα Επίπεδα (Fully Connected Layers) (Διαφ. 57):**
        *   **Δράση:** Συγκέντρωση πληροφορίας από τους τελικούς feature maps. Παραγωγή τελικής ταξινόμησης.
        *   **Παράμετροι:** Αριθμός κόμβων. Συνάρτηση ενεργοποίησης (συνήθως αλλάζει ανάλογα με τον ρόλο του επιπέδου: ReLU για συγκέντρωση πληροφορίας, Softmax για τελική ταξινόμηση).
        *   **Είσοδος/Έξοδος (I/O):** Είσοδος "ισοπεδωμένος" (FLATTENED) 3D κύβος (από προηγούμενους feature maps). Έξοδος μπορεί να είναι 1D διάνυσμα (για Softmax) ή feature maps αν ακολουθούν άλλα επίπεδα. *Η διαφάνεια αναφέρει "Output: 3D cube, one 2D map per filter" που είναι λίγο συγκεχυμένο για τυπικά FC layers στο τέλος ενός CNN για ταξινόμηση. Συνήθως η έξοδος είναι 1D διάνυσμα πιθανοτήτων.*

### **Παράδειγμα Πλήρους CNN (VGG) (Διαφάνεια 58)**

*   Οπτικοποίηση της αρχιτεκτονικής VGG.
*   Δείχνει τη ροή: Εικόνα εισόδου (224x224x3) -> Στοίβες από συνελικτικά επίπεδα (με ReLU) και max pooling επίπεδα.
*   Καθώς το δίκτυο βαθαίνει, οι χωρικές διαστάσεις (πλάτος, ύψος) των feature maps μειώνονται (λόγω pooling και strides), ενώ το βάθος (αριθμός feature maps/φίλτρων) αυξάνεται.
*   Στο τέλος, υπάρχουν πλήρως συνδεδεμένα επίπεδα και ένα Softmax για την έξοδο.

**Τι Μαθαίνουν τα Επίπεδα ενός CNN; (Διαφάνειες 59-60)**

*   **Ιεραρχική Εκμάθηση Χαρακτηριστικών:**
    *   Κάθε επίπεδο CNN μαθαίνει φίλτρα αυξανόμενης πολυπλοκότητας.
    *   **Πρώτα επίπεδα:** Μαθαίνουν βασικά φίλτρα ανίχνευσης χαρακτηριστικών: **ακμές (edges), γωνίες (corners)**, κηλίδες χρώματος κ.λπ. (Διαφ. 60, κάτω σειρά).
    *   **Μεσαία επίπεδα:** Μαθαίνουν φίλτρα που ανιχνεύουν **μέρη αντικειμένων (parts of objects)**. Για πρόσωπα, μπορεί να μάθουν να ανταποκρίνονται σε μάτια, μύτες κ.λπ. (Διαφ. 60, μεσαία σειρά).
    *   **Τελευταία επίπεδα (πριν τα FC):** Έχουν αναπαραστάσεις υψηλότερου επιπέδου. Μαθαίνουν να αναγνωρίζουν **πλήρη αντικείμενα (full objects)**, σε διαφορετικά σχήματα και θέσεις. (Διαφ. 60, πάνω σειρά).

### **Παραδείγματα Υπολογισμού Παραμέτρων (Διαφάνειες 61-62)**

*   **Παράδειγμα 1 (Διαφ. 61):**
    *   Συνελικτικό επίπεδο με 16 φίλτρα 3x3, είσοδος RGB εικόνα (3 κανάλια).
    *   **Παράμετροι φίλτρων:** (αριθμός_φίλτρων) x (μέγεθος_φίλτρου_Υ) x (μέγεθος_φίλτρου_Π) x (κανάλια_εισόδου)
        `16 * 3 * 3 * 3 = 432`
    *   **Biases:** Ένα bias ανά φίλτρο: `16`
    *   **Σύνολο παραμέτρων:** `432 + 16 = 448`
*   **Παράδειγμα 2 (Διαφ. 62):** Αρχιτεκτονική ενός CNN:
    *   Input: 32x32x3
    *   Conv1: 8 φίλτρα 3x3, stride 1, padding=same. Είσοδος 32x32x3.
        *   Παράμετροι Conv1: `(8 * 3 * 3 * 3) + 8` (filters * height * width * input_channels + biases)
    *   Conv2: 16 φίλτρα 5x5, stride 2, padding=same. Είσοδος από Conv1 θα είναι 32x32x8 (λόγω padding=same, stride=1).
        *   Παράμετροι Conv2: `(16 * 5 * 5 * 8) + 16`
    *   Flatten layer: Μετατρέπει τον 3D κύβο εξόδου του Conv2 σε 1D διάνυσμα. Αν η έξοδος του Conv2 είναι H'xW'xC', το ισοπεδωμένο διάνυσμα θα έχει H'*W'*C' στοιχεία. Με stride=2 στο Conv2 και padding=same, η έξοδος θα είναι περίπου (32/2)x(32/2)x16 = 16x16x16.
        *   Μέγεθος ισοπεδωμένου: `16 * 16 * 16 = 4096` (αν η έξοδος του Conv2 είναι 16x16x16)
    *   Dense1: 512 κόμβοι. Είσοδος από Flatten.
        *   Παράμετροι Dense1: `(μέγεθος_ισοπεδωμένου * 512) + 512` = `(4096 * 512) + 512`
    *   Dense2: 4 κόμβοι. Είσοδος από Dense1.
        *   Παράμετροι Dense2: `(512 * 4) + 4`
    *   **Συνολικές Παράμετροι:** Το άθροισμα των παραμέτρων από κάθε επίπεδο.
        *   Η διαφάνεια δίνει: `(8x3x3x3+8) + (16x5x5x8+16) + (16x16x16x512+512) + (512x4+4)`.
        *   *Σημείωση:* Η διάσταση `16x16x16` για την είσοδο του Dense1 υπονοεί ότι η έξοδος του Conv2 είναι 16x16 (χωρικά) και 16 κανάλια (αριθμός φίλτρων του Conv2). Αυτό είναι σωστό.

### **Εξέλιξη των CNNs - Ιστορική Αναδρομή (Διαφάνειες 63-75)**

*   **Αρχικές Ιδέες (Διαφ. 64):**
    *   **NeoCognitron (Fukushima, 1980):** Πρώιμη έρευνα παρόμοια με CNN, εμπνευσμένη από τον οπτικό φλοιό των θηλαστικών, για αναγνώριση χειρόγραφων χαρακτήρων.
    *   **Τέλη δεκαετίας '80:**
        *   Backpropagation (LeCun, 1985 - και άλλοι ανεξάρτητα).
        *   TDNN (Time Delay Neural Network) (Waiber et al., 1989): Δίκτυο τύπου συνελικτικού εκπαιδευμένο με backprop.
        *   Εφαρμογή Backpropagation σε αναγνώριση ταχυδρομικών κωδικών (LeCun et al., 1989).
*   **LeNet (Διαφ. 65):**
    *   (LeCun, 1998 - LeNet-5) "Μοντέρνα" αρχιτεκτονική CNN για αναγνώριση εγγράφων. Θεμελιώδης εργασία.
*   **AlexNet (Διαφ. 66):**
    *   (Krizhevsky, Sutskever, Hinton, 2012). Νικητής του ImageNet 2012.
    *   Έδειξε τα οφέλη των CNNs και ξεκίνησε την επανάσταση της Βαθιάς Μάθησης.
    *   Top-5 σφάλμα 15.3% (10.8% καλύτερο από τον δεύτερο).
    *   **Κύριες Συνεισφορές:** Εκπαίδευση σε ImageNet με αύξηση δεδομένων (data augmentation), αυξημένο βάθος μοντέλου, εκπαίδευση σε GPU (5-6 μέρες), έξυπνος βελτιστοποιητής, επίπεδα Dropout, ενεργοποίηση ReLU.
*   **ZFNet (Διαφ. 67):**
    *   (Zeiler, Fergus, 2013). Νικητής ILSVRC 2013 (11.2% σφάλμα). Μειωμένα μεγέθη φίλτρων.
    *   Εκπαίδευση για 12 μέρες.
    *   Παρουσίασε τεχνική οπτικοποίησης **Deconvolutional Network** για εξέταση ενεργοποιήσεων χαρακτηριστικών.
*   **VGG (Διαφ. 68):**
    *   (Simonyan, Zisserman - Oxford, 2014).
    *   **Κύρια σημεία: Απλότητα και Βάθος.** Χρήση αποκλειστικά φίλτρων 3x3 και MaxPool 2x2 (stride 2).
    *   Έδειξε ότι δύο φίλτρα 3x3 έχουν ισοδύναμο "receptive field" (πεδίο αντίληψης) με ένα 5x5.
    *   Καθώς το χωρικό μέγεθος μειώνεται, το βάθος αυξάνεται.
    *   Εκπαίδευση για 2-3 εβδομάδες. Ακόμα χρησιμοποιείται σήμερα.
*   **GoogLeNet (Inception-v1) (Διαφ. 69):**
    *   (Szegedy et al. - Google, 2014). Νικητής ILSVRC 2014.
    *   Εισάγει την **ενότητα Inception (inception module):** Παράλληλα συνελικτικά επίπεδα με διαφορετικά μεγέθη φίλτρων. Κίνητρο: αφήνει το δίκτυο να αποφασίσει ποιο μέγεθος φίλτρου είναι καλύτερο.
    *   Χρήση συνελίξεων 1x1 για μείωση του αριθμού των παραμέτρων πριν από τις πιο ακριβές συνελίξεις 3x3 και 5x5.
    *   Όχι πλήρως συνδεδεμένο επίπεδο στο τέλος. Χρήση AvgPool. 12x λιγότερες παράμετροι από AlexNet.
*   **ResNet (Residual Network) (Διαφ. 70-71):**
    *   (He et al. - Microsoft, 2015). Νικητής ILSVRC 2015 σε πολλαπλές κατηγορίες.
    *   **Κύρια Ιδέα: Residual Block (Υπολειμματικό Μπλοκ).** Επιτρέπει την εκπαίδευση εξαιρετικά βαθιών δικτύων.
    *   Αντί να μαθαίνει μια απεικόνιση `H(x)`, το μπλοκ μαθαίνει μια υπολειμματική απεικόνιση `F(x) = H(x) - x`. Τότε `H(x) = F(x) + x`.
    *   Η προσθήκη της ταυτότητας `x` (skip connection) διευκολύνει τη ροή της κλίσης και την εκπαίδευση.
    *   Το μπλοκ μπορεί να αποφασίσει να "απενεργοποιηθεί" (shut itself down) αν χρειαστεί (αν το `F(x)` γίνει μηδέν).
    *   Τα διαγράμματα σφάλματος δείχνουν ότι τα απλά (plain) βαθιά δίκτυα έχουν υψηλότερο σφάλμα εκπαίδευσης από τα πιο ρηχά, ενώ τα ResNets όχι.
*   **DenseNet (Διαφ. 72-73):**
    *   (Huang et al., 2016). Ριζική επέκταση της ιδέας του ResNet.
    *   **Κάθε μπλοκ χρησιμοποιεί κάθε προηγούμενο feature map ως είσοδο** (μέσω συνενώσεων - concatenations).
    *   Ιδέα: Αποφυγή επανυπολογισμού πλεοναζόντων χαρακτηριστικών. Όλη η προηγούμενη πληροφορία είναι διαθέσιμη.
    *   Παραδόξως, μειώνει τον αριθμό των απαιτούμενων παραμέτρων.
*   **MobileNet (Διαφ. 74):**
    *   (Howard et al., 2017). Εξαιρετικά αποδοτικό δίκτυο με αξιοπρεπή ακρίβεια (για mobile/embedded συσκευές).
    *   **Κύρια Έννοια: Depthwise-Separable Convolutions.**
        1.  **Depthwise Convolution:** Εφαρμογή ενός ξεχωριστού 2D φίλτρου σε κάθε κανάλι εισόδου.
        2.  **Pointwise Convolution (1x1 Convolution):** Εφαρμογή συνέλιξης 1x1 για συνδυασμό των εξόδων της depthwise συνέλιξης.
    *   Προσεγγίζει τις κλασικές συνελίξεις με πολύ λιγότερους υπολογισμούς.
*   **Πέρα από αυτά (Beyond) (Διαφ. 75):** Λίστα με άλλες σημαντικές αρχιτεκτονικές CNN (MobileNetV2, Inception-ResNet, Wide-ResNet, Xception, ResNeXt, ShuffleNet, Squeeze and Excitation Nets).

### **Εκπαίδευση CNNs (Διαφάνειες 76-104)**

*   **Πώς Εκπαιδεύουμε Αυτά τα Πράγματα; (Διαφ. 77):**
    1.  Συλλογή επισημασμένων δεδομένων (labeled data).
    2.  Εύρεση μιας αρχιτεκτονικής ConvNet.
    3.  Ελαχιστοποίηση της απώλειας (minimize the loss).
*   **Βήματα Εκπαίδευσης ενός CNN (Διαφ. 78):**
    1.  Διαίρεση και προεπεξεργασία των δεδομένων σας.
    2.  Επιλογή της αρχιτεκτονικής του δικτύου σας.
    3.  Αρχικοποίηση των βαρών.
    4.  Εύρεση ενός ρυθμού μάθησης (learning rate) και ισχύος κανονικοποίησης (regularization strength).
    5.  Ελαχιστοποίηση της απώλειας και παρακολούθηση της προόδου.
    6.  "Παίξιμο με τα κουμπιά" (Fiddle with knobs - ρύθμιση υπερπαραμέτρων).
*   **Mini-batch Gradient Descent (Διαφ. 79):**
    *   **Βρόχος (Loop):**
        1.  Δειγματοληψία μιας παρτίδας (batch) δεδομένων εκπαίδευσης (~100 εικόνες).
        2.  **Forwards pass:** Υπολογισμός της απώλειας (μέσος όρος πάνω στην παρτίδα).
        3.  **Backwards pass:** Υπολογισμός της κλίσης (gradient).
        4.  Ενημέρωση όλων των παραμέτρων.
    *   **Σημείωση:** Συχνά ονομάζεται "stochastic gradient descent" (SGD) ακόμα κι αν το SGD αυστηρά έχει μέγεθος παρτίδας 1.
*   **Κανονικοποίηση (Regularization) (Διαφ. 80):**
    *   Μειώνει την υπερπροσαρμογή.
    *   Η συνολική απώλεια `L = L_data + L_reg`.
    *   `L_reg = λ * (1/2) * ||W||₂²` (L2 regularization).
    *   Διαφορετικές τιμές του `λ` οδηγούν σε διαφορετικά όρια απόφασης (πιο ομαλά για μεγαλύτερο `λ`).
*   **Υπερπροσαρμογή (Overfitting) (Διαφ. 81):**
    *   **Overfitting:** Μοντελοποίηση του θορύβου στο σύνολο εκπαίδευσης αντί της "αληθινής" υποκείμενης σχέσης.
    *   **Underfitting:** Ανεπαρκής μοντελοποίηση της σχέσης στο σύνολο εκπαίδευσης.
    *   **Γενικός κανόνας:** Μοντέλα που είναι "μεγαλύτερα" ή έχουν περισσότερη χωρητικότητα (capacity) είναι πιο πιθανό να υπερπροσαρμοστούν.
*   **(0) Διαχωρισμός Δεδομένων (Dataset Split) (Διαφ. 82-84):**
    *   Χωρίστε τα δεδομένα σας σε "train", "validation", και "test".
    *   **Train set:** Για gradient descent και βελτιστοποίηση παραμέτρων.
    *   **Validation set:** Για τον καθορισμό υπερπαραμέτρων (learning rate, regularization strength κ.λπ.) και την επιλογή αρχιτεκτονικής.
    *   **Test set:** Για την εκτίμηση της απόδοσης στον "πραγματικό κόσμο". **Μην το χρησιμοποιείτε κατά την ανάπτυξη/βελτιστοποίηση.**
    *   **Προσοχή στην ψευδή ανακάλυψη (false discovery):** Μόλις χρησιμοποιήσετε το test set, ιδανικά δεν το ξαναχρησιμοποιείτε. Προσπαθήστε να αποφύγετε να κοιτάτε το σκορ του test set μέχρι το τέλος.
*   **(1) Προεπεξεργασία Δεδομένων (Data Preprocessing) (Διαφ. 85-88):**
    *   **Zero-centering (Μηδενικός Μέσος Όρος):** Αφαίρεση του μέσου όρου από κάθε χαρακτηριστικό.
    *   **Normalization (Κανονικοποίηση):** Διαίρεση με την τυπική απόκλιση.
    *   **PCA και Whitening (Λεύκανση):** Άλλες τεχνικές (δεδομένα έχουν διαγώνιο πίνακα συνδιακύμανσης ή πίνακα ταυτότητας). (Διαφ. 86)
    *   **Για ConvNets (Διαφ. 87):** Τυπικά, μόνο ο μέσος όρος αφαιρείται (είτε ο συνολικός μέσος όρος της εικόνας, είτε ο μέσος όρος ανά κανάλι R,G,B).
    *   **Αύξηση Δεδομένων (Data Augmentation) (Διαφ. 88):**
        *   Εξαγωγή τυχαίων "κοψιμάτων" (crops) από την είσοδο, με ελαφρώς μετατοπισμένα offsets.
        *   Τυχαίες οριζόντιες αναστροφές (reflections).
        *   Εκτέλεση της αύξησης "ζωντανά" κατά την εκπαίδευση.
        *   Βοηθά στην αποφυγή υπερπροσαρμογής.
*   **(2) Επιλογή Αρχιτεκτονικής (Διαφ. 89):** Παράδειγμα απλού MLP για CIFAR-10.
*   **(3) Αρχικοποίηση Βαρών (Διαφ. 90-92):**
    *   **Βάρη (W):** Θέστε τα σε μικρούς τυχαίους αριθμούς, π.χ., `W = np.random.randn(D, H) * 0.001` (από Γκαουσιανή κατανομή). Το μέγεθος είναι σημαντικό.
    *   **Biases (b):** Θέστε τα σε μηδέν (ή μικρούς μη μηδενικούς): `b = np.zeros(H)`.
    *   **Έλεγχος Απώλειας (Sanity Check):**
        *   Αρχικά, απενεργοποιήστε την κανονικοποίηση (reg=0.0). Η απώλεια πρέπει να είναι λογική (π.χ., για Softmax με 10 κλάσεις, η αρχική απώλεια πρέπει να είναι κοντά στο `-ln(1/10) ≈ 2.3`).
        *   Στη συνέχεια, αυξήστε την κανονικοποίηση. Η απώλεια πρέπει να αυξηθεί. (Διαφ. 91-92).
*   **(4) Υπερπροσαρμογή σε Μικρό Τμήμα Δεδομένων (Overfit a small portion of the data) (Διαφ. 93-95):**
    *   Πάρτε ένα μικρό υποσύνολο των δεδομένων εκπαίδευσης (π.χ., 20 δείγματα).
    *   Προσπαθήστε να επιτύχετε 100% ακρίβεια εκπαίδευσης (ή απώλεια κοντά στο μηδέν) σε αυτό το μικρό σύνολο.
    *   **Στόχος:** Να αποδείξετε ότι το μοντέλο είναι αρκετά πολύπλοκο για να μοντελοποιήσει (τουλάχιστον αυτά) τα δεδομένα. Αν δεν μπορεί να υπερπροσαρμοστεί ούτε σε ένα μικρό σύνολο, κάτι πάει λάθος (π.χ., learning rate πολύ μικρό, λάθος στην υλοποίηση).
    *   Οι καμπύλες θα δείχνουν σχεδόν μηδενικό σφάλμα για τα δεδομένα εκπαίδευσης και υψηλό σφάλμα για τα (λίγα) δεδομένα επικύρωσης.
*   **(4) Εύρεση Ρυθμού Μάθησης (Find a learning rate) (Διαφ. 96-104):**
    *   **Ξεκινήστε με μικρή κανονικοποίηση.**
    *   **Δοκιμάστε διαφορετικούς ρυθμούς μάθησης.**
        *   Αν η απώλεια σχεδόν δεν αλλάζει ή η ακρίβεια είναι πολύ χαμηλή (π.χ., τυχαία πρόβλεψη), ο ρυθμός μάθησης είναι **πολύ χαμηλός** (ή η κανονικοποίηση πολύ υψηλή). (Διαφ. 97)
        *   Αν η απώλεια "εκρήγνυται" (γίνεται NaN) ή ταλαντώνεται άγρια, ο ρυθμός μάθησης είναι **πολύ υψηλός**. (Διαφ. 98)
    *   **Αναζήτηση από Χονδρική σε Λεπτομερή (Coarse to fine search) (Διαφ. 99):**
        *   1ο στάδιο: Λίγες εποχές για μια γενική ιδέα.
        *   2ο στάδιο: Περισσότερος χρόνος εκτέλεσης, πιο λεπτομερής αναζήτηση.
        *   **Συμβουλή:** Αν η απώλεια > 3 * αρχική_απώλεια, σταματήστε νωρίς (learning rate πολύ υψηλό).
    *   **Οπτικοποίηση Απώλειας (Διαφ. 100):**
        *   Για πολύ μικρούς ρυθμούς μάθησης, η απώλεια μειώνεται γραμμικά και αργά.
        *   Μεγαλύτεροι ρυθμοί μάθησης τείνουν να φαίνονται πιο εκθετικοί.
        *   Καμπύλες για: πολύ υψηλό, χαμηλό, υψηλό, καλό learning rate.
    *   **Τυπική Απώλεια Εκπαίδευσης (Διαφ. 101):**
        *   Γιατί κυμαίνεται τόσο γρήγορα; Το εύρος της καμπύλης σχετίζεται με το `batchsize`. Αν είναι πολύ θορυβώδης, αυξήστε το `batchsize`.
        *   Μπορεί να είναι πολύ γραμμική (learning rate πολύ μικρό).
    *   **Οπτικοποίηση Ακρίβειας (Διαφ. 102):**
        *   **Μεγάλο κενό (Big gap)** μεταξύ ακρίβειας εκπαίδευσης και επικύρωσης: **overfitting** (αύξηση κανονικοποίησης).
        *   **Χωρίς κενό (No gap)** ή και οι δύο χαμηλές: **underfitting** (αύξηση χωρητικότητας μοντέλου, π.χ., μεγαλύτερα επίπεδα, ή μείωση κανονικοποίησης).
    *   **Οπτικοποίηση Βαρών (Φίλτρων) (Διαφ. 103-104):**
        *   **Θορυβώδη βάρη (Noisy weights):** Πιθανώς η κανονικοποίηση δεν είναι αρκετά ισχυρή.
        *   **Ωραία, καθαρά βάρη (Nice clean weights):** Η εκπαίδευση προχωρά καλά. Δείχνουν ότι τα φίλτρα μαθαίνουν δομές.

### **Πρόγραμμα Ρυθμού Μάθησης (Learning Rate Schedule) (Διαφάνεια 105)**

*   Πώς αλλάζουμε τον ρυθμό μάθησης με την πάροδο του χρόνου;
*   **Διάφορες επιλογές:**
    *   **Step down:** Μείωση κατά έναν παράγοντα (π.χ., 0.1) κάθε Χ mini-batches (χρησιμοποιήθηκε από SuperVision).
    *   **Decrease by a factor:** Μείωση κατά έναν παράγοντα (π.χ., 0.97) κάθε εποχή (χρησιμοποιήθηκε από GoogLeNet).
    *   **Scale by sqrt(1-t/max_t)**
    *   **Scale by 1/t**
    *   **Scale by exp(-t)**
    *   Η ιδέα είναι να ξεκινήσουμε με μεγαλύτερο ρυθμό μάθησης για γρήγορη πρόοδο και να τον μειώσουμε καθώς πλησιάζουμε στο ελάχιστο για λεπτομερή ρύθμιση.

### **Σύνοψη Πραγμάτων προς "Πείραγμα" (Διαφάνεια 106)**

*   Αρχιτεκτονική δικτύου
*   Ρυθμός μάθησης, πρόγραμμα μείωσης (decay schedule), τύπος ενημέρωσης (π.χ., SGD, Adam)
*   Κανονικοποίηση (L2, L1, maxnorm, dropout, ...)
*   Συνάρτηση απώλειας (softmax, SVM, ...)
*   Αρχικοποίηση βαρών

### **Τεχνικές Κανονικοποίησης (Διαφάνειες 107-116)**

*   **(Υπενθύμιση) Κανονικοποίηση μειώνει την υπερπροσαρμογή (Διαφ. 107).**
*   **Παραδείγματα Κανονικοποιητών (Example Regularizers) (Διαφ. 108):**
    *   **L2 regularization:** `L_reg = λ * (1/2) * ||W||₂²` (ενθαρρύνει μικρά βάρη).
    *   **L1 regularization:** `L_reg = λ * ||W||₁ = λ * Σ |wᵢⱼ|` (ενθαρρύνει αραιά βάρη - κάποια βάρη γίνονται ακριβώς μηδέν).
    *   **"Elastic net":** `L_reg = λ₁ * ||W||₁ + λ₂ * ||W||₂²` (συνδυασμός L1 και L2).
    *   **Max norm:** Περιορίζει το μέτρο (norm) των βαρών κάθε νευρώνα: `||W||₂² ≤ c`.
*   **"Weight Decay" (Φθορά Βαρών) (Διαφ. 109):**
    *   Η κανονικοποίηση L2 ονομάζεται επίσης "weight decay" γιατί τα βάρη "φθείρονται" (μειώνονται) σε κάθε επανάληψη.
    *   Η παράγωγος του `L_reg` ως προς `W` είναι `λW`.
    *   Στο βήμα του gradient descent: `W ← W - α(∂L_data/∂W + λW) = W - αλW - α(∂L_data/∂W)`.
    *   Τα βάρη πάντα μειώνονται κατά `αλW` (εκτός από την ενημέρωση από την κλίση των δεδομένων).
    *   **Σημείωση:** Τα biases μερικές φορές εξαιρούνται από την κανονικοποίηση.
*   **Dropout (Διαφ. 110-116):** Απλή αλλά ισχυρή τεχνική για μείωση της υπερπροσαρμογής.
    *   **Κατά την εκπαίδευση (At training time) (Διαφ. 110a, 112a, 115):** Κάθε νευρώνας (ή ενεργοποίηση) "παρουσιάζεται" (διατηρείται ενεργός) με πιθανότητα `p`, αλλιώς "απορρίπτεται" (μηδενίζεται) με πιθανότητα `1-p`.
    *   **Κατά τον έλεγχο (At test time) (Διαφ. 110b, 116):** Όλοι οι νευρώνες είναι "πάντα παρόντες". Οι ενεργοποιήσεις τους **πολλαπλασιάζονται επί `p`** (inverted dropout). Αυτό γίνεται για να διατηρηθεί η ίδια αναμενόμενη τιμή εξόδου όπως κατά την εκπαίδευση.
        *   Αναμενόμενη τιμή ενός νευρώνα `h` με dropout: `E[h] = p*h + (1-p)*0 = ph`.
    *   **Αποτέλεσμα (Διαφ. 111):** Το δίκτυο με dropout έχει χαμηλότερο σφάλμα ταξινόμησης και γενικεύει καλύτερα.
    *   **Ερμηνεία (Διαφ. 112):** Το Dropout μπορεί να ερμηνευθεί ως μια προσέγγιση της λήψης του γεωμετρικού μέσου όρου ενός συνόλου (ensemble) από εκθετικά πολλά μοντέλα (κάθε πιθανός συνδυασμός απορριφθέντων νευρώνων).
    *   **Πόσο dropout; (Διαφ. 113):** Συνήθως γύρω στο `p = 0.5`.
    *   **Παράδειγμα (Krizhevsky 2012 - AlexNet) (Διαφ. 114):** Το Dropout εφαρμόζεται στα πλήρως συνδεδεμένα επίπεδα, όχι απαραίτητα στα συνελικτικά. Στα συνελικτικά επίπεδα, η κοινή χρήση παραμέτρων και η συγκέντρωση παρέχουν ήδη κάποια κανονικοποίηση. Τα FC επίπεδα έχουν πολλές παραμέτρους και είναι πιο επιρρεπή σε overfitting.
    *   **Υλοποίηση (Διαφ. 115-116):** Κώδικας για την εφαρμογή dropout κατά την εκπαίδευση (δημιουργία μάσκας) και την κλιμάκωση κατά τον έλεγχο.

### **Σύνοψη (Διαφάνεια 117)**

*   Προεπεξεργασία των δεδομένων (αφαίρεση μέσου, sub-crops).
*   Προσεκτική αρχικοποίηση βαρών.
*   Χρήση Dropout.
*   Χρήση SGD + Momentum (δεν καλύφθηκε λεπτομερώς, αλλά το Momentum βοηθά στην επιτάχυνση της σύγκλισης).
*   Fine-tune από ImageNet (χρήση προ-εκπαιδευμένων βαρών ως σημείο εκκίνησης).
*   "Babysit the network as it trains" (παρακολούθηση και προσαρμογή υπερπαραμέτρων).
