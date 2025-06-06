# **Ερωτήσεις Πολλαπλής Επιλογής**

1.  **Ποιο από τα παρακάτω ΔΕΝ αποτελεί θέμα της διάλεξης (slide 2);**
    *   α. Perceptron.
    *   β. Gradient descent.
    *   γ. Convolutional Neural Networks.
    *   δ. Backpropagation.

2.  **Σύμφωνα με το slide 5, ποια δεκαετία χαρακτηρίζεται ως "Age of the Neural Network";**
    *   α. 1950s
    *   β. 1980s
    *   γ. 2000s
    *   δ. 2010s

3.  **Τι υπολογίζει ένα Perceptron πριν εφαρμόσει τη συνάρτηση βήματος (unit step function) ή τη συνάρτηση προσήμου (sign function) (slides 4, 7);**
    *   α. Τον μέσο όρο των εισόδων.
    *   β. Το σταθμισμένο άθροισμα (weighted sum) των εισόδων.
    *   γ. Τη μέγιστη τιμή εισόδου.
    *   δ. Την Ευκλείδεια απόσταση των εισόδων.

4.  **Στον αλγόριθμο του Perceptron (slide 9), πότε ενημερώνονται τα βάρη (weights);**
    *   α. Σε κάθε επανάληψη, ανεξάρτητα από την πρόβλεψη.
    *   β. Μόνο όταν η πρόβλεψη είναι σωστή.
    *   γ. Μόνο όταν η πρόβλεψη ŷ(t) είναι διαφορετική από την πραγματική ετικέτα y(t).
    *   δ. Ποτέ, τα βάρη παραμένουν σταθερά.

5.  **Ποια συνάρτηση ενεργοποίησης αναφέρεται στον κώδικα της "forward pass" στο slide 28;**
    *   α. ReLU
    *   β. Tanh
    *   γ. Sigmoid (logistic function)
    *   δ. Linear

6.  **Σύμφωνα με τα slides 30-33, τι επιτρέπει ο συνδυασμός νευρώνων και η προσθήκη επιπέδων σε ένα νευρωνικό δίκτυο;**
    *   α. Να μειώνει πάντα τον χρόνο εκπαίδευσης.
    *   β. Να μοντελοποιεί όλο και πιο πολύπλοκες συναρτήσεις.
    *   γ. Να χρησιμοποιεί λιγότερα δεδομένα εκπαίδευσης.
    *   δ. Να απλοποιεί πάντα τη δομή του μοντέλου.

7.  **Τι ονομάζεται ένα επίπεδο σε ένα νευρωνικό δίκτυο όπου όλοι οι νευρώνες του συνδέονται με όλους τους νευρώνες του προηγούμενου και του επόμενου επιπέδου (slide 45-46);**
    *   α. Sparse layer (Αραιό επίπεδο)
    *   β. Convolutional layer (Συνελικτικό επίπεδο)
    *   γ. Fully connected layer (Πλήρως συνδεδεμένο επίπεδο)
    *   δ. Output layer (Επίπεδο εξόδου) μόνο

8.  **Στο παράδειγμα νευρωνικού δικτύου του slide 50, πόσες είναι οι συνολικές μαθησιακές παράμετροι (learnable parameters), συμπεριλαμβανομένων των βαρών και των όρων πόλωσης (bias terms);**
    *   α. 6
    *   β. 20
    *   γ. 26
    *   δ. 30

9.  **Σύμφωνα με το Universal Approximation Theorem (slide 59), τι μπορεί να προσεγγίσει ένα νευρωνικό δίκτυο με ένα μόνο κρυφό επίπεδο και γραμμική μονάδα εξόδου;**
    *   α. Μόνο γραμμικές συναρτήσεις.
    *   β. Οποιαδήποτε συνεχής συνάρτηση, αυθαίρετα καλά, με αρκετές κρυφές μονάδες.
    *   γ. Μόνο πολυωνυμικές συναρτήσεις.
    *   δ. Οποιαδήποτε συνάρτηση χωρίς κανέναν περιορισμό.

10. **Ποιο είναι ένα πιθανό μειονέκτημα (Caveat) του Universal Approximation Theorem (slide 59);**
    *   α. Μπορεί να χρειαστούν εκθετικά πολλές κρυφές μονάδες.
    *   β. Λειτουργεί μόνο με τη συνάρτηση ReLU.
    *   γ. Δεν ισχύει για δίκτυα με όρους πόλωσης.
    *   δ. Απαιτεί πάντα μη γραμμική μονάδα εξόδου.

11. **Ποια από τις παρακάτω ΔΕΝ είναι τυπική συνάρτηση ενεργοποίησης που χρησιμοποιείται στα ενδιάμεσα κρυφά επίπεδα ενός νευρωνικού δικτύου (slide 63);**
    *   α. Sigmoid
    *   β. ReLU
    *   γ. Linear
    *   δ. Softmax (συνήθως χρησιμοποιείται στο τελευταίο επίπεδο για ταξινόμηση)

12. **Ποιο είναι ένα χαρακτηριστικό της γραμμικής συνάρτησης ενεργοποίησης (Linear activation) (slide 64);**
    *   α. Εισάγει έντονη μη-γραμμικότητα.
    *   β. Η σύνθεση επιπέδων γραμμικών μονάδων είναι ισοδύναμη με ένα μόνο επίπεδο γραμμικών μονάδων.
    *   γ. Περιορίζει την έξοδο μεταξύ 0 και 1.
    *   δ. Είναι πάντα η καλύτερη επιλογή για βαθιά δίκτυα.

13. **Ποιο είναι το εύρος τιμών εξόδου της συνάρτησης Sigmoid (slide 65);**
    *   α. (-1, 1)
    *   β. (0, 1)
    *   γ. (-∞, +∞)
    *   δ. [0, +∞)

14. **Ποιο είναι ένα πλεονέκτημα της συνάρτησης ενεργοποίησης ReLU (Rectified Linear Unit) (slide 67);**
    *   α. Είναι παραγωγίσιμη παντού.
    *   β. Είναι λιγότερο επιρρεπής στο πρόβλημα των "εξαφανιζόμενων κλίσεων" (vanishing gradients) σε σύγκριση με τις sigmoid/tanh.
    *   γ. Περιορίζει την έξοδο μεταξύ -1 και 1.
    *   δ. Δεν οδηγεί ποτέ σε "νεκρούς" νευρώνες.

15. **Τι είναι το πρόβλημα "dying ReLU" (slide 68);**
    *   α. Οι νευρώνες ReLU ενεργοποιούνται πολύ συχνά, προκαλώντας υπολογιστικό κόστος.
    *   β. Οι νευρώνες ReLU μπορεί να σταματήσουν να μαθαίνουν (να γίνουν "νεκροί") αν η κλίση τους γίνει μόνιμα μηδέν για αρνητικές εισόδους.
    *   γ. Η συνάρτηση ReLU δεν είναι καθόλου παραγωγίσιμη.
    *   δ. Η συνάρτηση ReLU μπορεί να χρησιμοποιηθεί μόνο στο επίπεδο εξόδου.

16. **Πώς η συνάρτηση Leaky ReLU προσπαθεί να λύσει το πρόβλημα "dying ReLU" (slide 69);**
    *   α. Επιτρέποντας μια μικρή, μη μηδενική κλίση για αρνητικές τιμές εισόδου.
    *   β. Θέτοντας όλες τις αρνητικές εισόδους σε 1.
    *   γ. Χρησιμοποιώντας μια εκθετική συνάρτηση.
    *   δ. Περιορίζοντας την έξοδο στο [0,1].

17. **Σύμφωνα με το θεώρημα των Montufar et al. (2014) (slide 70), πώς τα βαθύτερα δίκτυα (περισσότερα κρυφά επίπεδα) με ReLU ενεργοποιήσεις συγκρίνονται με τα πιο πλατιά ως προς την εκφραστική τους δύναμη;**
    *   α. Τα πιο πλατιά δίκτυα είναι πάντα εκθετικά πιο εκφραστικά.
    *   β. Τα βαθύτερα δίκτυα μπορούν να παρέχουν εκθετικά πιο εκφραστικές (πιο συμπαγείς) προσεγγίσεις.
    *   γ. Το βάθος και το πλάτος έχουν πάντα την ίδια επίδραση.
    *   δ. Το θεώρημα ισχύει μόνο για sigmoid ενεργοποιήσεις.

18. **Γιατί η "αναπαράσταση" (representation) των δεδομένων είναι σημαντική, όπως φαίνεται στο παράδειγμα Καρτεσιανών και Πολικών συντεταγμένων (slide 71);**
    *   α. Οι πολικές συντεταγμένες είναι πάντα καλύτερες.
    *   β. Η κατάλληλη αναπαράσταση μπορεί να κάνει ένα μη γραμμικά διαχωρίσιμο πρόβλημα πιο εύκολα διαχειρίσιμο ή γραμμικά διαχωρίσιμο.
    *   γ. Η αναπαράσταση δεν επηρεάζει την απόδοση των νευρωνικών δικτύων.
    *   δ. Οι Καρτεσιανές συντεταγμένες απαιτούν λιγότερους νευρώνες.

19. **Ποιος είναι ο σκοπός μιας συνάρτησης απώλειας (Loss Function) (slide 75);**
    *   α. Να επιταχύνει την εκπαίδευση του μοντέλου.
    *   β. Να αξιολογήσει πόσο καλά ο αλγόριθμος μοντελοποιεί το σύνολο δεδομένων.
    *   γ. Να επιλέξει τον αριθμό των κρυφών επιπέδων.
    *   δ. Να κανονικοποιήσει τις εισόδους του δικτύου.

20. **Ποια συνάρτηση απώλειας ΔΕΝ αναφέρεται ως κατάλληλη για προβλήματα παλινδρόμησης (Regression) στο slide 76/77;**
    *   α. MSE (Mean Squared Error)
    *   β. MAE (Mean Absolute Error)
    *   γ. Binary cross-entropy
    *   δ. Hubber loss

21. **Ποιο είναι ένα πλεονέκτημα της συνάρτησης απώλειας MSE (Mean Squared Error) για παλινδρόμηση (slide 78);**
    *   α. Είναι ανθεκτική στις ακραίες τιμές (outliers).
    *   β. Η μονάδα σφάλματος είναι ίδια με την αρχική έξοδο.
    *   γ. Είναι πάντα παραγωγίσιμη και έχει συνήθως ένα μόνο τοπικό ελάχιστο (για γραμμική παλινδρόμηση).
    *   δ. Είναι η απλούστερη συνάρτηση απώλειας.

22. **Ποιο είναι ένα πλεονέκτημα της συνάρτησης απώλειας MAE (Mean Absolute Error) για παλινδρόμηση (slide 79);**
    *   α. Είναι πάντα παραγωγίσιμη παντού.
    *   β. Είναι πιο ανθεκτική στις ακραίες τιμές (outliers).
    *   γ. Η μονάδα σφάλματος είναι στο τετράγωνο.
    *   δ. Έχει πάντα μόνο ένα τοπικό ελάχιστο.

23. **Τι χρησιμοποιείται συνήθως για την αξιολόγηση προβλημάτων δυαδικής ταξινόμησης (binary classification) (slide 80);**
    *   α. Mean Squared Error (MSE)
    *   β. Binary Cross Entropy (log loss)
    *   γ. KL Divergence
    *   δ. Triplet loss

24. **Στις καμπύλες απώλειας (loss curves) (slide 82), τι συνήθως απεικονίζεται;**
    *   α. Η απώλεια εκπαίδευσης (training loss) και η απώλεια επικύρωσης (validation loss) συναρτήσει των εποχών (epochs).
    *   β. Η ακρίβεια εκπαίδευσης και η ακρίβεια επικύρωσης.
    *   γ. Η απώλεια εκπαίδευσης και ο ρυθμός μάθησης.
    *   δ. Ο αριθμός των παραμέτρων και η απώλεια επικύρωσης.

25. **Πώς αναγνωρίζεται η υποπροσαρμογή (underfit) από τις καμπύλες εκμάθησης (slide 84-85);**
    *   α. Η απώλεια εκπαίδευσης είναι πολύ χαμηλή, αλλά η απώλεια επικύρωσης είναι υψηλή και αυξάνεται.
    *   β. Τόσο η απώλεια εκπαίδευσης όσο και η απώλεια επικύρωσης παραμένουν υψηλές και δεν βελτιώνονται σημαντικά με περισσότερη εκπαίδευση.
    *   γ. Η απώλεια εκπαίδευσης και η απώλεια επικύρωσης είναι πολύ κοντά και χαμηλές.
    *   δ. Η απώλεια επικύρωσης είναι σημαντικά χαμηλότερη από την απώλεια εκπαίδευσης.

26. **Πώς αναγνωρίζεται η υπερπροσαρμογή (overfit) από τις καμπύλες εκμάθησης (slide 88-89);**
    *   α. Η απώλεια εκπαίδευσης συνεχίζει να μειώνεται, ενώ η απώλεια επικύρωσης αρχίζει να αυξάνεται.
    *   β. Τόσο η απώλεια εκπαίδευσης όσο και η απώλεια επικύρωσης είναι υψηλές.
    *   γ. Η απώλεια εκπαίδευσης και η απώλεια επικύρωσης συγκλίνουν σε μια χαμηλή τιμή με μικρό κενό.
    *   δ. Η απώλεια εκπαίδευσης σταματά να μειώνεται ενώ η απώλεια επικύρωσης συνεχίζει να μειώνεται.

---

**Απαντήσεις**

1.  **γ.** Convolutional Neural Networks.
2.  **β.** 1980s
3.  **β.** Το σταθμισμένο άθροισμα (weighted sum) των εισόδων.
4.  **γ.** Μόνο όταν η πρόβλεψη ŷ(t) είναι διαφορετική από την πραγματική ετικέτα y(t).
5.  **γ.** Sigmoid (logistic function)
6.  **β.** Να μοντελοποιεί όλο και πιο πολύπλοκες συναρτήσεις.
7.  **γ.** Fully connected layer (Πλήρως συνδεδεμένο επίπεδο)
8.  **γ.** 26 (20 βάρη + 4 bias στο πρώτο κρυφό + 2 bias στο επίπεδο εξόδου)
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
