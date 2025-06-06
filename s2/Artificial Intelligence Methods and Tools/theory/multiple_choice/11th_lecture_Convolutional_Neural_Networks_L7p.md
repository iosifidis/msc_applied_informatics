# **Ερωτήσεις Πολλαπλής Επιλογής**

1.  **Σύμφωνα με το slide 8, τι είναι το "Representation Learning";**
    *   α. Μια τεχνική για την χειροκίνητη δημιουργία χαρακτηριστικών.
    *   β. Μια τεχνική που επιτρέπει σε ένα σύστημα να βρίσκει αυτόματα τα σχετικά χαρακτηριστικά για μια δεδομένη εργασία.
    *   γ. Μια μέθοδος που χρησιμοποιείται αποκλειστικά σε μη επιβλεπόμενη μάθηση.
    *   δ. Η διαδικασία οπτικοποίησης των δεδομένων.

2.  **Ποιο είναι ένα από τα κύρια μειονεκτήματα της χρήσης ενός απλού MLP (Multi-layer Perceptron) για την ανίχνευση αντικειμένων σε εικόνες, όπως φαίνεται στο slide 9;**
    *   α. Δεν μπορεί να μάθει καθόλου.
    *   β. Μαθαίνει πλεονάζοντα χαρακτηριστικά και δεν είναι ανθεκτικό στις μετατοπίσεις του αντικειμένου.
    *   γ. Απαιτεί πάντα πολύ λίγα βάρη.
    *   δ. Είναι πολύ καλό στο να χειρίζεται μετατοπίσεις.

3.  **Γιατί η πολυπλοκότητα ενός MLP γίνεται "εξαιρετικά υψηλή" για εικόνες όπως αυτές του ImageNet (slide 10);**
    *   α. Λόγω του μικρού αριθμού pixels στις εικόνες.
    *   β. Επειδή τα MLP χρησιμοποιούν πολύ λίγους νευρώνες.
    *   γ. Λόγω του μεγάλου αριθμού pixels (π.χ., 224x224x3) που οδηγεί σε τεράστιο αριθμό βαρών ανά νευρώνα σε ένα πλήρως συνδεδεμένο επίπεδο.
    *   δ. Επειδή τα MLP αποφεύγουν την υπερπροσαρμογή.

4.  **Ποιες δύο ιδιότητες των εικόνων εκμεταλλεύονται τα CNNs (slide 11);**
    *   α. Ότι είναι Τυχαίες και Δυσμετάβλητες (Random and Invariant).
    *   β. Ότι είναι Τοπικές και Ιεραρχικές (Local and Hierarchical).
    *   γ. Ότι είναι Γενικές και Μη-Τοπικές (Global and Non-Local).
    *   δ. Ότι είναι Πλήρως Συνδεδεμένες και Επίπεδες (Fully Connected and Flat).

5.  **Τι είναι η λειτουργία "Convolution" σε ένα CNN, όπως φαίνεται στο slide 13;**
    *   α. Η εφαρμογή ενός φίλτρου (kernel) που "γλιστρά" πάνω στην εικόνα εισόδου για να δημιουργήσει έναν χάρτη χαρακτηριστικών (feature map).
    *   β. Η πλήρης σύνδεση όλων των pixels με όλους τους νευρώνες.
    *   γ. Η τυχαία επιλογή pixels για την εξαγωγή χαρακτηριστικών.
    *   δ. Η μείωση του μεγέθους της εικόνας χωρίς εξαγωγή χαρακτηριστικών.

6.  **Ποια ήταν η σημαντική συνεισφορά της αρχιτεκτονικής SuperVision (AlexNet) στον διαγωνισμό ImageNet του 2012 (slide 14);**
    *   α. Είχε το υψηλότερο σφάλμα από όλους τους ανταγωνιστές.
    *   β. Πέτυχε ένα σφάλμα παρόμοιο με τις παραδοσιακές μεθόδους.
    *   γ. Μείωσε δραματικά το ποσοστό σφάλματος σε σύγκριση με άλλες μεθόδους, σηματοδοτώντας μια νέα εποχή.
    *   δ. Χρησιμοποίησε χειροκίνητα εξαγόμενα χαρακτηριστικά.

7.  **Ποια από τις παρακάτω ΔΕΝ αποτελεί βασική λειτουργία σε ένα τυπικό συνελικτικό επίπεδο ενός CNN (slides 17, 20-22);**
    *   α. Convolution (Συνέλιξη)
    *   β. Non-linearity (Μη-γραμμικότητα, π.χ., ReLU)
    *   γ. Fully connected layer (Πλήρως συνδεδεμένο επίπεδο)
    *   δ. Spatial pooling (Χωρική συγκέντρωση/pooling)

8.  **Ποιος είναι ο ρόλος του "Spatial Pooling" (π.χ., Max pooling) σε ένα CNN (slide 22);**
    *   α. Να αυξήσει τη διάσταση των χαρτών χαρακτηριστικών.
    *   β. Να εισάγει μη-γραμμικότητα.
    *   γ. Να μειώσει τη διάσταση των χαρτών χαρακτηριστικών (down-sampling) και να παρέχει κάποια ανθεκτικότητα σε μικρές μετατοπίσεις.
    *   δ. Να μάθει τα βάρη των φίλτρων.

9.  **Πώς τα CNNs αντιμετωπίζουν το πρόβλημα της μη ανθεκτικότητας στις μετατοπίσεις (translations) που έχουν τα MLPs (slide 23-24);**
    *   α. Αφαιρώντας πληροφορίες θέσης των pixels.
    *   β. Χρησιμοποιώντας φίλτρα (kernels) που εφαρμόζονται σε ολόκληρη την εικόνα (parameter sharing) και pooling, επιτυγχάνοντας translation invariance.
    *   γ. Έχοντας πάντα μόνο ένα κρυφό επίπεδο.
    *   δ. Αυξάνοντας δραματικά τον αριθμό των βαρών.

10. **Τι είναι το "stride" σε μια συνέλιξη (convolution) (slides 27-37);**
    *   α. Το μέγεθος του φίλτρου (kernel).
    *   β. Ο αριθμός των φίλτρων που χρησιμοποιούνται.
    *   γ. Το βήμα με το οποίο το φίλτρο "γλιστρά" πάνω στην εικόνα εισόδου.
    *   δ. Το padding που προστίθεται στις άκρες της εικόνας.

11. **Ποιος είναι ο σκοπός του "padding" σε μια συνέλιξη (slides 38-39);**
    *   α. Να αυξήσει πάντα τον αριθμό των χαρακτηριστικών.
    *   β. Να μειώσει τον χρόνο υπολογισμού.
    *   γ. Να ελέγξει το μέγεθος του χάρτη χαρακτηριστικών εξόδου, συχνά για να διατηρήσει το ίδιο μέγεθος με την είσοδο (same padding) ή για να επιτρέψει στο φίλτρο να επισκεφθεί τις άκρες.
    *   δ. Να αφαιρέσει τον θόρυβο από την εικόνα.

12. **Σύμφωνα με τον τύπο στο slide 44 (w\_out = (w\_in + 2p - k)/s + 1), αν μια εικόνα εισόδου έχει πλάτος w\_in=32, χρησιμοποιείται ένα kernel μεγέθους k=3, padding p=1 και stride s=1, ποιο θα είναι το πλάτος της εξόδου w\_out;**
    *   α. 30
    *   β. 32
    *   γ. 34
    *   δ. 16

13. **Όταν εφαρμόζουμε συνέλιξη σε μια εικόνα RGB (3 κανάλια), πώς λειτουργούν τα φίλτρα (slide 46-47);**
    *   α. Κάθε φίλτρο επεξεργάζεται μόνο ένα κανάλι ξεχωριστά.
    *   β. Κάθε φίλτρο είναι ένα 3D "κυβάκι" που εφαρμόζεται σε ολόκληρο το βάθος (όλα τα κανάλια) της εισόδου, αλλά παράγει έναν 2D χάρτη χαρακτηριστικών.
    *   γ. Τα φίλτρα είναι πάντα 2D και εφαρμόζονται σε κάθε κανάλι ξεχωριστά, παράγοντας 3 χάρτες χαρακτηριστικών ανά φίλτρο.
    *   δ. Η συνέλιξη εφαρμόζεται μόνο σε ασπρόμαυρες εικόνες.

14. **Ποια είναι η πιο επιτυχημένη μη-γραμμικότητα (non-linearity) για τα CNNs, σύμφωνα με το slide 50;**
    *   α. Sigmoid
    *   β. Tanh
    *   γ. Linear
    *   δ. Rectified Non-Linear unit (ReLU)

15. **Ποιοι είναι οι τρεις τυπικοί τύποι επιπέδων από τους οποίους αποτελείται ένα CNN (slide 52);**
    *   α. Input, Hidden, Output Layers
    *   β. Convolutional, Recurrent, Attention Layers
    *   γ. Convolutional, Pooling, Fully connected Layers
    *   δ. Embedding, Transformation, Classification Layers

16. **Τι μαθαίνουν συνήθως τα πρώτα επίπεδα ενός CNN (slide 59);**
    *   α. Ολόκληρα αντικείμενα.
    *   β. Βασικά χαρακτηριστικά όπως ακμές και γωνίες.
    *   γ. Σύνθετα μέρη αντικειμένων όπως μάτια ή μύτες.
    *   δ. Τις πιθανότητες των κλάσεων.

17. **Ποια ήταν η κύρια συνεισφορά της αρχιτεκτονικής LeNet (slide 65);**
    *   α. Ήταν η πρώτη που χρησιμοποίησε αποκλειστικά πλήρως συνδεδεμένα επίπεδα.
    *   β. Εισήγαγε την ιδέα των residual blocks.
    *   γ. Ήταν μια πρώιμη "μοντέρνα" αρχιτεκτονική CNN για αναγνώριση εγγράφων.
    *   δ. Κέρδισε τον διαγωνισμό ImageNet του 2012.

18. **Ποια ήταν μια από τις κύριες συνεισφορές της AlexNet (slide 66);**
    *   α. Η χρήση πολύ μικρών φίλτρων (1x1).
    *   β. Η χρήση ReLU ενεργοποίησης και η εκπαίδευση σε GPUs.
    *   γ. Η κατάργηση των pooling layers.
    *   δ. Η εισαγωγή των inception modules.

19. **Ποια είναι η κύρια ιδέα πίσω από τα Inception modules (GoogLeNet) (slide 69);**
    *   α. Η χρήση μόνο ενός τύπου φίλτρου σε κάθε επίπεδο.
    *   β. Η χρήση παράλληλων συνελικτικών επιπέδων με διαφορετικά μεγέθη φίλτρων, επιτρέποντας στο δίκτυο να αποφασίσει ποιο μέγεθος είναι καλύτερο.
    *   γ. Η αφαίρεση όλων των συνελικτικών επιπέδων.
    *   δ. Η χρήση μόνο 1x1 συνελίξεων για μείωση διαστάσεων.

20. **Ποια είναι η βασική ιδέα των Residual blocks στην ResNet (slide 70-71);**
    *   α. Να κάνουν τα δίκτυα πιο πλατιά αντί για πιο βαθιά.
    *   β. Να επιτρέπουν την εκπαίδευση εξαιρετικά βαθιών δικτύων, μαθαίνοντας τις υπολειμματικές αντιστοιχίσεις (residual mappings) που μπορεί να είναι ευκολότερο να βελτιστοποιηθούν.
    *   γ. Να χρησιμοποιούν μόνο max pooling.
    *   δ. Να αντικαθιστούν όλες τις συναρτήσεις ενεργοποίησης με γραμμικές.

21. **Πώς η DenseNet επεκτείνει την ιδέα της ResNet (slide 72-73);**
    *   α. Κάθε επίπεδο λαμβάνει ως είσοδο μόνο την έξοδο του αμέσως προηγούμενου επιπέδου.
    *   β. Κάθε επίπεδο (ή block) λαμβάνει ως είσοδο τους χάρτες χαρακτηριστικών από όλα τα προηγούμενα επίπεδα.
    *   γ. Χρησιμοποιεί μόνο 1x1 συνελίξεις.
    *   δ. Αφαιρεί εντελώς τις συνδέσεις παράκαμψης (skip connections).

22. **Ποια είναι η κύρια ιδέα των depthwise-separable convolutions που χρησιμοποιούνται στην MobileNet (slide 74);**
    *   α. Η εφαρμογή μιας πλήρους 3D συνέλιξης με μεγάλα φίλτρα.
    *   β. Ο διαχωρισμός της συνέλιξης σε μια depthwise convolution (ένα φίλτρο ανά κανάλι εισόδου) ακολουθούμενη από μια pointwise convolution (1x1) για τον συνδυασμό των εξόδων.
    *   γ. Η χρήση μόνο pooling layers.
    *   δ. Η αύξηση του αριθμού των παραμέτρων για καλύτερη ακρίβεια.

23. **Ποιο βήμα ΔΕΝ αποτελεί τυπικό μέρος της εκπαίδευσης ενός CNN (slide 78);**
    *   α. Διαχωρισμός και προεπεξεργασία των δεδομένων.
    *   β. Επιλογή της αρχιτεκτονικής του δικτύου.
    *   γ. Αρχικοποίηση των βαρών σε μηδέν.
    *   δ. Ελαχιστοποίηση της απώλειας και παρακολούθηση της προόδου.

24. **Τι είναι η τεχνική "Data Augmentation" που αναφέρεται στο slide 88;**
    *   α. Η αφαίρεση δεδομένων για την επιτάχυνση της εκπαίδευσης.
    *   β. Η τεχνητή αύξηση του μεγέθους του συνόλου εκπαίδευσης με τη δημιουργία τροποποιημένων αντιγράφων των υπαρχόντων δεδομένων (π.χ., τυχαίες περικοπές, αναστροφές).
    *   γ. Η χρήση μόνο ενός μικρού υποσυνόλου των δεδομένων.
    *   δ. Η προσθήκη θορύβου στα βάρη του δικτύου.

25. **Ποιος είναι ο ρόλος του "validation set" κατά την εκπαίδευση ενός νευρωνικού δικτύου (slide 83);**
    *   α. Χρησιμοποιείται για την εκπαίδευση του τελικού μοντέλου.
    *   β. Χρησιμοποιείται για τον υπολογισμό των κλίσεων (gradients).
    *   γ. Χρησιμοποιείται για τον καθορισμό των υπερπαραμέτρων (π.χ. ρυθμός μάθησης, ισχύς κανονικοποίησης) και την επιλογή της αρχιτεκτονικής.
    *   δ. Χρησιμοποιείται μόνο για την τελική αναφορά της απόδοσης.

26. **Τι είναι η τεχνική "Dropout" (slides 110-112);**
    *   α. Μια μέθοδος για την αύξηση του αριθμού των νευρώνων.
    *   β. Μια τεχνική κανονικοποίησης όπου τυχαίοι νευρώνες "απενεργοποιούνται" κατά τη διάρκεια της εκπαίδευσης για τη μείωση της υπερπροσαρμογής.
    *   γ. Μια τεχνική για την αύξηση του ρυθμού μάθησης.
    *   δ. Μια μέθοδος για την αρχικοποίηση των βαρών.

---

**Απαντήσεις**

1.  **γ.** Convolutional Neural Networks.
2.  **β.** 1980s
3.  **β.** Το σταθμισμένο άθροισμα (weighted sum) των εισόδων.
4.  **γ.** Μόνο όταν η πρόβλεψη ŷ(t) είναι διαφορετική από την πραγματική ετικέτα y(t).
5.  **γ.** Sigmoid (logistic function)
6.  **β.** Να μοντελοποιεί όλο και πιο πολύπλοκες συναρτήσεις.
7.  **γ.** Fully connected layer (Πλήρως συνδεδεμένο επίπεδο)
8.  **γ.** 26
9.  **β.** Οποιαδήποτε συνεχής συνάρτηση, αυθαίρετα καλά, με αρκετές κρυφές μονάδες.
10. **α.** Μπορεί να χρειαστούν εκθετικά πολλές κρυφές μονάδες.
11. **δ.** Softmax (συνήθως χρησιμοποιείται στο τελευταίο επίπεδο για ταξινόμηση)
12. **β.** Η σύνθεση επιπέδων γραμμικών μονάδων είναι ισοδύναμη με ένα μόνο επίπεδο γραμμικών μονάδων.
13. **β.** (0, 1)
14. **β.** Είναι λιγότερο επιρρεπής στο πρόβλημα των "εξαφανιζόμενων κλίσεων" (vanishing gradients) σε σύγκριση με τις sigmoid/tanh.
15. **β.** Οι νευρώνες ReLU μπορεί να σταματήσουν να μαθαίνουν (να γίνουν "νεκροί") αν η κλίση τους γίνει μόνιμα μηδέν για αρνητικές εισόδους.
16. **α.** Επιτρέποντας μια μικρή, μη μηδενική κλίση για αρνητικές τιμές εισόδου.
17. **β.** Τα βαθύτερα δίκτυα μπορούν να παρέχουν εκθετικά πιο εκφραστικές (πιο συμπαγείς) προσεγγίσεις.
18. **β.** Η κατάλληλη αναπαράσταση μπορεί να κάνει ένα μη γραμμικά διαχωρίσιμο πρόβλημα πιο εύκολα διαχειρίσιμο ή γραμμικά διαχωρίσιμο.
19. **β.** Να αξιολογήσει πόσο καλά ο αλγόριθμος μοντελοποιεί το σύνολο δεδομένων.
20. **γ.** Binary cross-entropy
21. **γ.** Είναι πάντα παραγωγίσιμη και έχει συνήθως ένα μόνο τοπικό ελάχιστο (για γραμμική παλινδρόμηση).
22. **β.** Είναι πιο ανθεκτική στις ακραίες τιμές (outliers).
23. **β.** Binary Cross Entropy (log loss)
24. **α.** Η απώλεια εκπαίδευσης (training loss) και η απώλεια επικύρωσης (validation loss) συναρτήσει των εποχών (epochs).
25. **β.** Τόσο η απώλεια εκπαίδευσης όσο και η απώλεια επικύρωσης παραμένουν υψηλές και δεν βελτιώνονται σημαντικά με περισσότερη εκπαίδευση.
26. **α.** Η απώλεια εκπαίδευσης συνεχίζει να μειώνεται, ενώ η απώλεια επικύρωσης αρχίζει να αυξάνεται.
