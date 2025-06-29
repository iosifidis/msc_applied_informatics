# Εισαγωγή στην Ταξινόμηση (Classification)

## **1. Το Πρόβλημα (The Story)**

*   **Σενάριο:** Μας δίνεται ένα σύνολο δεδομένων (σε μορφή excel spreadsheet) που αφορά ασθενείς με καρκίνο του μαστού.
*   **Δεδομένα:**
    *   Περίπου 600 **παρατηρήσεις** ή **δείγματα** (rows), δηλαδή ασθενείς.
    *   Για κάθε παρατήρηση, υπάρχουν 30 **ιατρικοί δείκτες** ή **χαρακτηριστικά** (features / medical index scores) που σχετίζονται με την φυσιολογία του όγκου.
    *   Η **κατάσταση** (status) του όγκου είναι γνωστή για κάθε παρατήρηση: **κακοήθης** (malignant / cancerous) ή **καλοήθης** (benign / non-cancerous). Αυτή είναι η **ετικέτα** ή η **κλάση στόχος** (label / target class) που θέλουμε να προβλέψουμε.
*   **Ο Στόχος:** Να δημιουργήσουμε ένα **μοντέλο υποστήριξης αποφάσεων** (decision support model).
    *   Το μοντέλο λαμβάνει ως **είσοδο** (input) τις 30 τιμές των δεικτών για έναν όγκο.
    *   Προβλέπει την **κατάσταση** (κλάση) του όγκου: κακοήθης ή καλοήθης.
*   **Περιορισμοί Απόδοσης (Constraints):** Υπάρχουν συγκεκριμένες απαιτήσεις για την ακρίβεια του μοντέλου:
    *   Να ταξινομεί σωστά τουλάχιστον το **90% των κακοήθων** όγκων.
    *   Να ταξινομεί σωστά τουλάχιστον το **80% των καλοήθων** όγκων.
    *   *Σημείωση:* Αυτοί οι περιορισμοί είναι κρίσιμοι και δείχνουν ότι δεν μας ενδιαφέρει μόνο η συνολική ακρίβεια (overall accuracy). Η σωστή ταξινόμηση των κακοήθων (που είναι πιο επικίνδυνο να "χάσουμε") έχει μεγαλύτερη βαρύτητα.

## **2. Επιπλέον Λεπτομέρειες & Προκλήσεις (Further Details & Challenges)**

*   **Δεδομένα "ως έχουν" (Data "As Is"):**
    *   Δεν είχαμε καμία συμμετοχή στην προετοιμασία των δεδομένων.
    *   Δεν μπορούμε να ζητήσουμε πρόσθετες πληροφορίες ή διευκρινίσεις.
*   **Έλλειψη Γνώσης Πεδίου (Lack of Domain Experience):**
    *   Τα δεδομένα μπορεί να προέρχονται από ένα πεδίο (ιατρική) που δεν γνωρίζουμε καλά.
    *   Για εμάς, είναι απλώς αριθμοί: τιμές εισόδου και τιμή εξόδου (κλάση).
    *   Δεν μπορούμε να κρίνουμε αν οι τιμές είναι "λογικές" ή "τυχαίες".
    *   Δεν γνωρίζουμε εκ των προτέρων αν οι απαιτούμενοι περιορισμοί απόδοσης είναι εφικτοί με αυτά τα δεδομένα.

## **3. Μεθοδολογία Αντιμετώπισης (How to Handle Such Situations - Workflow)**

Ακολουθούμε μια δομημένη διαδικασία, τυπική στην Επιστήμη Δεδομένων (Data Science):

*   **Μέρος 1: Κατανόηση της Κατάστασης**
    *   **Προεπεξεργασία Δεδομένων (Data Preprocessing):** Καθαρισμός, μετασχηματισμοί, κ.λπ.
    *   **Οπτικοποίηση (Visualization):** Γραφήματα για την κατανόηση των δεδομένων.
    *   **Ορισμός Προβλήματος (Problem Definition):** Επιβεβαίωση του τι ζητείται.
    *   **Επιλογή Μοντέλου (Model Selection):** Επιλογή κατάλληλων αλγορίθμων.
*   **Μέρος 2: Έλεγχος της Λύσης**
    *   **Πειραματική Ρύθμιση (Experimental Setup):** Πώς θα αξιολογήσουμε το μοντέλο (π.χ., διαχωρισμός σε σύνολα εκπαίδευσης/δοκιμής).
    *   **Ερμηνεία Αποτελεσμάτων (Results Interpretation):** Ανάλυση των μετρικών απόδοσης.
    *   **Ενημερώσεις [αν χρειάζεται] (Updates [if necessary]):** Βελτίωση του μοντέλου ή της διαδικασίας.
    *   **Συμπεράσματα (Conclusions):** Οριστικοποίηση των ευρημάτων.

## **4. Προεπεξεργασία Δεδομένων (Data Preprocessing)**

Αυτό το στάδιο περιλαμβάνει συνήθως:
*   Προφίλ Δεδομένων (Data profiling)
*   Καθαρισμός Δεδομένων (Data cleansing)
*   Μείωση Δεδομένων (Data reduction)
*   Μετασχηματισμός Δεδομένων (Data transformation)
*   Εμπλουτισμός Δεδομένων (Data enrichment)
*   Επικύρωση Δεδομένων (Data validation)

## **5. Εξερεύνηση Δεδομένων (Data Exploration)**

Είναι κρίσιμη για να κατανοήσουμε τη δομή, τις ιδιότητες και τα προβλήματα των δεδομένων.

*   **Έλεγχος Ελλειπουσών Τιμών:**
    *   Στα δεδομένα μας: **Καλά νέα!** Δεν υπάρχουν ελλείπουσες τιμές (NaN Count = 0 για όλα τα χαρακτηριστικά).
*   **Έλεγχος Εύρους Τιμών (Check the Range):**
    *   Είδαμε ότι οι τιμές των χαρακτηριστικών κυμαίνονται σε πολύ διαφορετικά εύρη (π.χ. radius_mean από 6.981000 έως 27.2200, ενώ area_worst από 185.2000 έως 3216.00).
    *   **Κακά νέα!** Αυτό σημαίνει ότι χαρακτηριστικά με μεγαλύτερες τιμές μπορεί να κυριαρχήσουν σε αλγορίθμους που βασίζονται στην απόσταση (όπως ο kNN ή οι SVM).
    *   **Σημαντική Σημείωση:** Αυτό υποδεικνύει την ανάγκη για **κλιμάκωση χαρακτηριστικών** (feature scaling) ως μέρος της προεπεξεργασίας (π.χ. Standardization ή Normalization).
*   **Κατανομή Κλάσεων (Class Distribution):**
    *   Πάντα ελέγχουμε την κατανομή της κλάσης στόχου.
    *   Στο πρόβλημά μας: 357 καλοήθεις (Benign), 212 κακοήθεις (Malignant). Συνολικά ~569 παρατηρήσεις (ο αριθμός 600 στην αρχή ήταν στρογγυλοποίηση).
    *   Ο λόγος (ratio) είναι περίπου 357/212 ≈ 1.7 προς 1.
    *   **Καλά νέα!** Έχουμε **σχετική ισορροπία** μεταξύ των κλάσεων. Αυτό είναι σημαντικό, γιατί η ανισορροπία (class imbalance) είναι μια συχνή πρόκληση.

*   **Προειδοποίηση #1: Ανισορροπία Κλάσεων (Class Imbalance):**
    *   **Ορισμός:** Όταν μία ή περισσότερες κλάσεις έχουν σημαντικά λιγότερα δείγματα από άλλες.
    *   **Συνέπειες:** Τα μοντέλα μπορεί να "προτιμούν" την πλειοψηφούσα κλάση και να έχουν κακή απόδοση στην μειοψηφούσα (που συχνά είναι η πιο σημαντική, όπως εδώ οι κακοήθεις όγκοι). Η **Ακρίβεια (Accuracy)** είναι **αναξιόπιστη μετρική** σε περιπτώσεις έντονης ανισορροπίας.
    *   **Τεχνικές Αντιμετώπισης (Συντομη Αναφορά):** Oversampling (αύξηση δειγμάτων μειοψηφούσας), Undersampling (μείωση δειγμάτων πλειοψηφούσας), χρήση βαρών στις κλάσεις (class weights), Ensemble methods.

*   **Οπτικοποιήσεις για την Κατανόηση των Χαρακτηριστικών:**
    *   **Boxplots:** Δείχνουν την κατανομή των τιμών ενός χαρακτηριστικού ανά κλάση (εύρος, τεταρτημόρια, διάμεσος, ακραίες τιμές - outliers). Μας βοηθούν να δούμε αν οι κλάσεις διαχωρίζονται καλά με βάση ένα χαρακτηριστικό.
    *   **Histograms:** Δείχνουν τη συχνότητα εμφάνισης τιμών ενός χαρακτηριστικού ανά κλάση. Η **επικάλυψη (overlap)** των ιστογραμμάτων δείχνει πόσο εύκολα ή δύσκολα διαχωρίζονται οι κλάσεις με βάση το συγκεκριμένο χαρακτηριστικό.
        *   Μικρή επικάλυψη (Good case): Το χαρακτηριστικό είναι πιθανώς χρήσιμο για ταξινόμηση.
        *   Μεγάλη επικάλυψη (Bad scenario): Το χαρακτηριστικό δεν βοηθάει πολύ στο διαχωρισμό των κλάσεων από μόνο του.
    *   Πραγματοποιήσαμε boxplots και ιστογράμματα για τα 30 χαρακτηριστικά. Παρατηρήσαμε διάφορα επίπεδα επικάλυψης. Κάποια χαρακτηριστικά (π.χ. concavity_mean - Slide 16 & 35) φαίνονται να διαχωρίζουν καλύτερα τις κλάσεις από άλλα (π.χ. texture_se - Slide 18 & 37).
*   **Οπτικοποιήσεις Πολλαπλών Χαρακτηριστικών:**
    *   **Pair plots (Scatter plot matrix):** Δείχνουν τη σχέση (scatter plot) μεταξύ όλων των ζευγών χαρακτηριστικών, καθώς και την κατανομή (histogram) για κάθε χαρακτηριστικό, ανά κλάση.
    *   **3D Scatter plots:** Οπτικοποιούν τα δεδομένα σε τρεις διαστάσεις (χρησιμοποιώντας 3 χαρακτηριστικά).
    *   Αυτές οι οπτικοποιήσεις μας βοηθούν να δούμε αν οι κλάσεις διαχωρίζονται καλύτερα όταν συνδυάζουμε χαρακτηριστικά. Για παράδειγμα, ο συνδυασμός Symmetry_mean, Area_worst και Symmetry_worst σε 3D (Slide 47) δείχνει μια σχετική διαχωρισιμότητα.
*   **Προειδοποίηση #2: Δεν υπάρχει σαφής διαφορά στις τιμές των χαρακτηριστικών (Overlap):**
    *   Όσο μεγαλύτερη είναι η επικάλυψη στις κατανομές των χαρακτηριστικών μεταξύ των κλάσεων (είτε σε 1D - ιστογράμματα/boxplots, είτε σε 2D/3D - scatter plots), τόσο πιο δύσκολη είναι η εργασία της ταξινόμησης για το μοντέλο.

## **6. Προσεγγίσεις Μοντελοποίησης (ML Approaches)**

Εξετάσαμε εν συντομία διάφορους αλγορίθμους ταξινόμησης:

*   **k Πλησιέστεροι Γείτονες (k-Nearest Neighbors - kNN):**
    *   **Ιδέα:** Για να ταξινομήσουμε ένα νέο σημείο, βρίσκουμε τους *k* πλησιέστερους γείτονές του στο σύνολο εκπαίδευσης και του αναθέτουμε την κλάση που είναι η πλειοψηφία μεταξύ αυτών των γειτόνων.
    *   **Πλεονεκτήματα:** Πολύ διαισθητικός, απλός στην κατανόηση.
    *   **Μειονεκτήματα:**
        *   **Αργός (Slow):** Απαιτεί υπολογισμό αποστάσεων από *όλα* τα δείγματα του συνόλου εκπαίδευσης για κάθε νέα πρόβλεψη (ιδιαίτερα σε μεγάλα σύνολα δεδομένων).
        *   **Ευαίσθητος σε Ανισορροπία & Outliers:** Σε ανισόρροπα δεδομένα, η πλειοψηφούσα κλάση μπορεί να κυριαρχήσει στους γείτονες, ειδικά αν τα δείγματα είναι αραιά. Οι ακραίες τιμές (outliers) μπορούν να επηρεάσουν σημαντικά την απόσταση.
        *   **Ευαίσθητος στην Κλίμακα των Χαρακτηριστικών:** Όπως είδαμε, τα εύρη διακύμανσης των χαρακτηριστικών επηρεάζουν την απόσταση. Απαιτεί **κλιμάκωση χαρακτηριστικών**.

*   **Μηχανές Διανυσμάτων Υποστήριξης (Support Vector Machines - SVM):**
    *   **Ιδέα (για γραμμικά διαχωρίσιμα δεδομένα):** Βρίσκουν την **υπερεπίπεδη (hyperplane)** που διαχωρίζει τις κλάσεις με το **μέγιστο περιθώριο (maximum margin)**, δηλαδή τη μεγαλύτερη απόσταση από τα πλησιέστερα σημεία κάθε κλάσης. Αυτά τα πλησιέστερα σημεία ονομάζονται **διανύσματα υποστήριξης (support vectors)**.
    *   **Handling Outliers/Non-separable Data (Soft Margin):** Επιτρέπουν κάποιες παραβιάσεις του περιθωρίου (λάθη ταξινόμησης) για να είναι πιο ανθεκτικές στον θόρυβο και τους outliers, θυσιάζοντας λίγη ακρίβεια για καλύτερη γενίκευση.
    *   **Μη-Γραμμική Ταξινόμηση (Non-Linear SVC):** Χρησιμοποιούν το **Kernel Trick**. Αντί να μετασχηματίζουν τα δεδομένα ρητά σε ένα χώρο υψηλότερης διάστασης όπου είναι γραμμικά διαχωρίσιμα, υπολογίζουν μόνο το **εσωτερικό γινόμενο (inner product)** μεταξύ των δειγμάτων στον μετασχηματισμένο χώρο. Αυτό τους επιτρέπει να μάθουν μη-γραμμικά όρια απόφασης στον αρχικό χώρο χαρακτηριστικών.
    *   Γενικά θεωρούνται **μη-παραμετρικά** μοντέλα λόγω της ευελιξίας που προσφέρουν οι kernels.

*   **Δέντρα Αποφάσεων (Decision Trees):**
    *   **Ιδέα:** Χωρίζουν αναδρομικά το χώρο των χαρακτηριστικών σε ορθογώνιες περιοχές χρησιμοποιώντας απλούς γραμμικούς διαχωρισμούς (π.χ., x₁ ≤ 2 ή x₂ ≥ 4). Κάθε περιοχή αντιστοιχεί σε ένα φύλλο του δέντρου και του ανατίθεται μια κλάση (πλειοψηφία δειγμάτων σε αυτή την περιοχή).
    *   **Αναπαράσταση:** Μπορούν να αναπαρασταθούν ως μια σειρά από κανόνες "Αν... τότε..." (if-then-else).
    *   **Ιδιότητες:** Είναι **μη-παραμετρικά** μοντέλα. Η **βαθύτητα (depth)** του δέντρου ελέγχει την πολυπλοκότητά του. Πολύ "βαθιά" δέντρα μπορεί να οδηγήσουν σε **υπερ-προσαρμογή (overfitting)** στα δεδομένα εκπαίδευσης.
    *   Παράγουν **τμηματικά σταθερά όρια απόφασης (piecewise constant decision boundaries)**.

*   **Αφελής Μπεϋζιανός Ταξινομητής (Naïve Bayes Classifier):**
    *   **Ιδέα:** Βασίζεται στο **Θεώρημα του Bayes** για την ταξινόμηση. Υπολογίζει την πιθανότητα ένα δείγμα να ανήκει σε κάθε κλάση, δεδομένων των τιμών των χαρακτηριστικών του.
    *   **Βασική Υπόθεση:** Η **υπόθεση υπό συνθήκη ανεξαρτησίας των χαρακτηριστικών (conditional independence assumption)**. Υποθέτει ότι τα χαρακτηριστικά είναι ανεξάρτητα μεταξύ τους, *δεδομένης της κλάσης*. Αυτή η υπόθεση είναι συχνά "αφελής" (naïve) στην πράξη, αλλά ο αλγόριθμος μπορεί να λειτουργήσει καλά παρόλα αυτά.
    *   **Gaussian Naïve Bayes:** Μία μορφή του αλγορίθμου που υποθέτει ότι οι τιμές των χαρακτηριστικών ακολουθούν **κανονική (Gaussian) κατανομή** εντός κάθε κλάσης. Οπτικοποιείται ως ελλείψεις στον χώρο χαρακτηριστικών (οι οποίες είναι "ευθυγραμμισμένες με τους άξονες" λόγω της υπόθεσης ανεξαρτησίας).
    *   Είναι ένα **παραμετρικό** μοντέλο (ταξινομεί βάσει των παραμέτρων των κατανομών που υπολογίζει).

## **7. Αξιολόγηση της Απόδοσης (Evaluating the Performance)**

Η σωστή αξιολόγηση είναι κρίσιμη, ειδικά σε ιατρικά σενάρια και με (ακόμη και μικρή) ανισορροπία κλάσεων.

*   **Πίνακας Σύγχυσης (Confusion Matrix):**
    *   Ένας πίνακας που συνοψίζει τα αποτελέσματα της ταξινόμησης συγκρίνοντας τις **πραγματικές κλάσες (Actual)** με τις **προβλεπόμενες κλάσες (Predicted)**.
    *   Για δυαδική ταξινόμηση (2x2 matrix), τα τέσσερα στοιχεία είναι:
        *   **True Positives (TP):** Κακοήθεις όγκοι που ταξινομήθηκαν σωστά ως κακοήθεις.
        *   **True Negatives (TN):** Καλοήθεις όγκοι που ταξινομήθηκαν σωστά ως καλοήθεις.
        *   **False Positives (FP):** Καλοήθεις όγκοι που ταξινομήθηκαν λάθος ως κακοήθεις (**Σφάλμα Τύπου Ι / Type I Error**).
        *   **False Negatives (FN):** Κακοήθεις όγκοι που ταξινομήθηκαν λάθος ως καλοήθεις (**Σφάλμα Τύπου ΙΙ / Type II Error**).
    *   Στο σενάριο μας (όπου Malignant = Positive class):
        *   FP: "Ψευδώς Θετικό" - Προβλέψαμε κακοήθη, ενώ ήταν καλοήθης.
        *   FN: "Ψευδώς Αρνητικό" - Προβλέψαμε καλοήθη, ενώ ήταν κακοήθης (αυτό είναι το πιο επικίνδυνο σφάλμα - χάνουμε έναν καρκίνο!).
    *   Η επιλογή της **"Θετικής" κλάσης** (Positive Class) είναι σημαντική γιατί επηρεάζει τις μετρικές που βασίζονται σε αυτή (Precision, Recall). Στο πρόβλημά μας, επιλέξαμε τον **κακοήθη όγκο** ως τη θετική κλάση, καθώς είναι η κλάση που μας ενδιαφέρει πρωταρχικά να εντοπίσουμε σωστά.

*   **Μετρικές Απόδοσης (Performance Metrics):**
    *   **Ακρίβεια (Accuracy):** Συνολική αναλογία σωστών προβλέψεων.
        *   `Accuracy = (TP + TN) / (TP + TN + FP + FN)`
        *   **Προσοχή:** Όπως τονίστηκε, η Ακρίβεια είναι **παραπλανητική σε ανισόρροπα δεδομένα**. Ένα μοντέλο που απλώς προβλέπει πάντα την πλειοψηφούσα κλάση μπορεί να έχει υψηλή ακρίβεια, αλλά μηδενική απόδοση στην μειοψηφούσα. Είδαμε παράδειγμα όπου Accuracy=0.98, αλλά Recall=0.71 (δηλαδή χάσαμε σχεδόν το 30% των κακοήθων όγκων).
    *   **Ακρίβεια (Precision):** Από όσα το μοντέλο πρόβλεψε ως θετικά, πόσα ήταν *πραγματικά* θετικά. Μας ενδιαφέρει να μην κάνουμε πολλά ψευδώς θετικά (FP).
        *   `Precision = TP / (TP + FP)`
    *   **Ανάκληση (Recall) / Ευαισθησία (Sensitivity):** Από όλα τα *πραγματικά* θετικά δείγματα, πόσα εντόπισε το μοντέλο. Μας ενδιαφέρει να μην χάνουμε πολλά πραγματικά θετικά (FN). **Αυτή η μετρική είναι κρίσιμη για την απαίτηση του 90% των κακοήθων όγκων στο πρόβλημά μας.**
        *   `Recall = TP / (TP + FN)`
    *   **F1-score:** Ο αρμονικός μέσος της Precision και της Recall. Είναι χρήσιμος όταν θέλουμε μια ισορροπία μεταξύ Precision και Recall, ειδικά σε ανισόρροπα δεδομένα.
        *   `F1-score = 2 * (Precision * Recall) / (Precision + Recall)`
    *   Υπάρχουν και άλλες μετρικές, αλλά αυτές είναι οι βασικές για την ταξινόμηση, ιδίως σε δυαδικά προβλήματα.
*   **Αξιολόγηση στο Test Set:** Πάντα αξιολογούμε την τελική απόδοση των μοντέλων σε ένα **ανεξάρτητο σύνολο δοκιμής (test set)**, το οποίο δεν έχει χρησιμοποιηθεί κατά την εκπαίδευση. Αυτό μας δίνει μια πιο ρεαλιστική εικόνα της ικανότητας του μοντέλου να γενικεύει σε νέα, αόρατα δεδομένα.
*   **"Μέσες τιμές μεταξύ folds" (Average values among folds):** Αναφέρεται στην τεχνική της **Σταυροειδούς Επικύρωσης (Cross-Validation)**. Αντί να χωρίζουμε τα δεδομένα μία φορά σε εκπαίδευση/δοκιμή, τα χωρίζουμε σε *k* "διπλώματα" (folds). Εκπαιδεύουμε το μοντέλο *k* φορές, χρησιμοποιώντας κάθε φορά ένα διαφορετικό fold ως σύνολο δοκιμής και τα υπόλοιπα ως σύνολο εκπαίδευσης. Υπολογίζουμε τις μετρικές για κάθε "fold" δοκιμής και παίρνουμε τον μέσο όρο. Αυτό δίνει μια πιο **σταθερή και αξιόπιστη εκτίμηση** της απόδοσης του μοντέλου.

## **8. Συμπεράσματα (Conclusions)**

*   Τα μοντέλα ταξινόμησης είναι όντως απαραίτητα εργαλεία για την υποστήριξη αποφάσεων σε ιατρικά σενάρια (όπως η ανίχνευση καρκίνου του μαστού).
*   Προκλήσεις όπως τα ανισόρροπα δεδομένα απαιτούν προσοχή, ειδικά στην σωστή αναγνώριση τόσο των κακοήθων όσο και των καλοήθων όγκων.
*   Η αξιολόγηση της απόδοσης πρέπει να γίνεται με **πολλαπλές μετρικές** (Accuracy, Precision, Recall, F1-score) και **όχι μόνο με την Accuracy**, καθώς η τελευταία μπορεί να αποκρύψει αδυναμίες, ιδίως σε ανισόρροπα σύνολα.
*   Οι αλγόριθμοι όπως οι k-Nearest Neighbors, τα Δέντρα Αποφάσεων, οι Μηχανές Διανυσμάτων Υποστήριξης και οι Naïve Bayes έχουν ο καθένας τα δικά του πλεονεκτήματα, μειονεκτήματα και περιορισμούς σε διαφορετικά σενάρια. Η επιλογή του κατάλληλου μοντέλου εξαρτάται από τα δεδομένα, τις απαιτήσεις του προβλήματος και τους υπολογιστικούς περιορισμούς.

