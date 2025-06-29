# Scenery ScrollingSprites Portals

Η δομή αυτής της ενότητας είναι: **Προσθήκη αντικειμένων κατά την εκτέλεση -> Κίνηση sprites σε κυλιόμενο χάρτη -> Προσθήκη portals**. Θα δούμε πώς να ενσωματώσουμε δυναμικά αντικείμενα στον κόσμο μας και πώς να διαχειριστούμε την αλληλεπίδραση με αυτά σε έναν χάρτη με κύλιση.

---

## Εισαγωγή (Slide 2)

*   Στα προηγούμενα παραδείγματα, εξετάσαμε βασικά στοιχεία προγραμματισμού παιχνιδιών.
*   Σε αρκετές περιπτώσεις, γράψαμε κώδικα απευθείας στο αρχείο της φόρμας για λόγους εξοικονόμησης χρόνου στην παρουσίαση.
*   Είναι πλέον καιρός να **οργανώσουμε σωστά τον κώδικα σε κλάσεις** και να ορίσουμε κατάλληλα τις συσχετίσεις τους, προκειμένου ο κώδικάς μας να μπορεί εύκολα να **επαναχρησιμοποιηθεί** σε επόμενα project.
*   Μετά από αυτό, θα είμαστε έτοιμοι να δούμε πώς:
    *   **Προσθέτουμε αντικείμενα** στο παιχνίδι κατά την εκτέλεση.
    *   **Κινούμε τον ήρωά μας** μέσα στον κυλιόμενο χάρτη.
    *   **Προσθέτουμε portals** (πύλες τηλεμεταφοράς).

---

## Κλάση Game (Slides 3-4)

*   Η κλάση `Game` είναι μια **υποστηρικτική κλάση** που περιλαμβάνει τις βασικές λειτουργίες σχεδίασης και διαχείρισης που χρειαζόμαστε (βλ. προηγούμενα μαθήματα για τον ορισμό της).
*   **Λειτουργικότητα (Slide 3):**
    *   Διατηρεί αναφορές στη φόρμα (`p_frm`) και στο PictureBox (`p_pb`) όπου σχεδιάζονται τα γραφικά.
    *   Παρέχει τον μηχανισμό σχεδίασης (`p_device` - Graphics) σε ένα Bitmap που τελικά ενημερώνει την ιδιότητα `Image` του PictureBox.
    *   Παρέχει μέθοδο (`LoadBitmap`) φόρτωσης bitmaps.
    *   Παρέχει μεθόδους σχεδίασης bitmaps.
    *   Παρέχει μεθόδους εμφάνισης/σχεδίασης πληροφοριών και ορισμού γραμματοσειράς.
*   **Επιπλέον Μεθόδοι (Slide 4):**
    *   Προσθέτουμε μια μέθοδο (`FrameRate()`) για τον **υπολογισμό του πραγματικού ρυθμού ανανέωσης** των frames του παιχνιδιού (ο κώδικας υπήρχε προηγουμένως στο σώμα του βρόχου της φόρμας).
    *   Προσθέτουμε **2 μεθόδους `Random`** για την επιστροφή ενός τυχαίου ακέραιου αριθμού (στο διάστημα 0..max και min..max αντίστοιχα).

---

## Κλάση Sprite (Slides 5-6)

*   Η κλάση `Sprite` χρησιμοποιείται για την **αναπαράσταση των sprites** (βλ. προηγούμενα μαθήματα για τον ορισμό της).
*   **Λειτουργικότητα (Slide 5):**
    *   Διατηρεί αναφορά στο αντικείμενο της κλάσης `Game` (`p_game`).
    *   Περιλαμβάνει πεδία για την τρέχουσα θέση (`p_position`), το sprite sheet (`p_bitmap`), τον αριθμό frames (`p_totalFrames`), το τρέχον frame (`p_currentFrame`), την κατεύθυνση και την ταχύτητα (`p_velocity`), αν είναι ζωντανό (`p_alive`), κ.α.
    *   Περιλαμβάνει properties για ευκολότερη πρόσβαση/μετάλλαξη των πεδίων.
    *   Ειδικότερα, το property `Bounds` ενημερώνεται συνεχώς με το πλαίσιο οριοθέτησης του sprite και σε συνδυασμό με τη μέθοδο `IsColliding` χρησιμοποιείται για τον έλεγχο συγκρούσεων.
    *   Περιλαμβάνει μέθοδο `Animate` που εντοπίζει το frame ανάλογα με την κατεύθυνση κίνησης και τον τρόπο επανάληψης.
    *   Περιλαμβάνει μέθοδο `Draw` για τη σχεδίαση του τρέχοντος frame στην κατάλληλη θέση.

*   **Επέκταση της μεθόδου Draw (Slide 6):**
    *   Στην κλάση `Sprite`, προσθέτουμε μια ακόμα έκδοση της μεθόδου `Draw` (`public void Draw(int x, int y)`).
    *   Αυτή η έκδοση **δέχεται τις συντεταγμένες x, y** και σχεδιάζει σε αυτές ένα **αντίγραφο** του sprite.
    *   Η διαφορά είναι ότι **δεν αλλάζει** την εσωτερική θέση του sprite (τις τιμές των πεδίων/properties). Αυτό είναι χρήσιμο για να σχεδιάζουμε ένα sprite σε συγκεκριμένη θέση στην οθόνη, π.χ., για να το τοποθετήσουμε στον κυλιόμενο χάρτη.

---

## Κλάση Level (Slides 7-11)

*   Η κλάση `Level` χρησιμοποιείται για την **αναπαράσταση και διαχείριση των επιπέδων** του παιχνιδιού (βλ. προηγούμενο μάθημα για τον ορισμό της).
*   **Λειτουργικότητα (Slide 7):**
    *   Ενημερώθηκε ο τύπος δομής (`tilemapStruct`) που χρησιμοποιείται στη δήλωση του πίνακα δομών, ώστε να υπάρχει δυνατότητα αποθήκευσης περισσότερων πληροφοριών για κάθε tile (π.χ., ύπαρξη portal).
    *   Ενημερώθηκε η μέθοδος `loadTilemap` ώστε να μεταφέρονται όλες οι πληροφορίες από το αρχείο XML στον πίνακα δομών.
*   **Σύνοψη Βασικών Στοιχείων (Slide 8):** Συνοψίζουμε κάποια βασικά στοιχεία της κλάσης `Level` που αφορούν στη σχεδίαση:
    *   Ο πίνακας δομών (`tilemapStruct[] p_tilemap`) για την αποθήκευση δεδομένων του tilemap.
    *   Ο πίνακας έχει 128x128 στοιχεία (tiles).
    *   Διατηρεί αναφορά στην κλάση `Game` (`p_game`).
    *   Περιλαμβάνει πεδία για τη διάσταση κάθε tile (`p_tileSize`), την παλέτα των tiles (`p_bmpTiles`) και τον αριθμό στηλών της (`p_columns`).
    *   Περιλαμβάνει πεδία για τη διάσταση του παραθύρου σε tiles (`p_windowSize`) και του χάρτη/επιπέδου σε pixels (`p_mapSize`).
*   **Βασικές Μέθοδοι (Slide 9):**
    *   `loadTilemap`: Μεταφέρει πληροφορίες από το αρχείο XML στον πίνακα δομών.
    *   `loadPalette`: Ενημερώνει τα σχετικά πεδία με την παλέτα των tiles (bitmap) και τον αριθμό στηλών της παλέτας.
    *   `drawTileNumber`: Δέχεται γραμμή/στήλη και αριθμό tile στην παλέτα και σχεδιάζει το συγκεκριμένο tile στην κατάλληλη θέση.
*   **Scrolling (Slide 10):** Στην περίπτωση που θέλουμε να έχουμε κύλιση, αυτή δεν αφορά παρά σε ένα μικρό αριθμό από pixels και όχι σε ολόκληρο tile (π.χ., 32 pixels κάθε φορά).
*   **Scroll Buffer (Slide 10):** Αυτό επιτυγχάνεται χρησιμοποιώντας έναν **επιπλέον buffer** (Bitmap `p_bmpScrollBuffer`) – έστω scroll buffer – που έχει διάσταση μεγαλύτερη κατά 1 tile οριζόντια και κατακόρυφα σε σχέση με το παράθυρο σχεδίασης.
*   **Μεταβλητές Scrolling (Slide 10):** Χρησιμοποιούνται 3 πεδία τύπου `PointF` για την παρακολούθηση:
    *   `p_oldScrollPos`: Προηγούμενο σημείο κύλισης.
    *   `p_scrollPos`: Τρέχον σημείο κύλισης.
    *   `p_subtile`: Σημείο που προσδιορίζει το μέγεθος των sub tiles που δεν σχεδιάζονται ολόκληρα.
*   **Μέθοδοι Scrolling (Slide 11):**
    *   `Update`: Σχεδιάζει τον scroll buffer και ενημερώνει κατάλληλα τις 3 προαναφερθείσες μεταβλητές (`p_oldScrollPos`, `p_scrollPos`, `p_subtile`).
    *   `Draw (με 4 ορίσματα)`: Αντιγράφει το κατάλληλο τμήμα του scroll buffer προκειμένου αυτό τελικά να εμφανιστεί στο παράθυρο του παιχνιδιού.

---

## Ένας Νέος Βρόχος για το Παιχνίδι (Slides 12-14)

*   Αν εκτελέσετε το project Sub-Tile Smooth Scroller θα διαπιστώσετε ότι η χρήση της CPU είναι ιδιαίτερα μεγάλη (κατάχρηση CPU οφείλεται στην χρήση χρονομέτρου για την ενημέρωση & σχεδίαση του επιπέδου).
*   **Timer vs Application.DoEvents (Slide 12):** Η κλάση `Timer` χρησιμοποιείται συνήθως για να πυροδοτήσει συμβάντα για τον έλεγχο αλλαγών σε βάσεις δεδομένων ή τον έλεγχο λειτουργίας ενός server, όχι για την υλοποίηση του βρόχου ενός παιχνιδιού.
*   **Νέος Βρόχος (Slides 13-14):** Οι αλλαγές που θα κάνουμε στον βρόχο του παιχνιδιού (που βρίσκεται στη μέθοδο `Main` της φόρμας) είναι:
    *   Θα προσθέσουμε έναν βρόχο `while` στο συμβάν `From1_Load` της κλάσης `Form1`.
    *   Θα ορίσουμε τη μέθοδο `doUpdate` για να περιλαμβάνει τον κώδικα του βρόχου.
    *   Τα γραφικά του παιχνιδιού θα ενημερώνονται με ρυθμό 60 FPS καλώντας την `Level.Update`.
    *   Για να μην "παγώνει" η φόρμα στην περίπτωση συμβάντων που προκαλούνται από ενέργειες του παίκτη, θα καλούμε την `Application.DoEvents()`.
    *   Για να μην έχουμε κατάχρηση της CPU, θα χρησιμοποιούμε `Thread.Sleep(1)`.

---

## Προσθήκη Αντικειμένων κατά την Εκτέλεση (Slides 16-17)

*   **Random Tree Demo (Slide 16):** Το παράδειγμα αυτό δείχνει πώς μπορούμε να προσθέσουμε αντικείμενα στον κόσμο του παιχνιδιού κατά την εκτέλεση.
*   **Υλοποίηση (Slide 17):**
    *   Χρησιμοποιούμε μια **λίστα** (`List<Sprite> trees`) στην οποία θα δημιουργήσουμε και θα προσθέσουμε 100 δέντρα ως αντικείμενα της κλάσης `Sprite`.
    *   Επιλέγεται **τυχαία** ένα από τα διαθέσιμα δέντρα ως "κοστούμι" (`treeImage`) για κάθε δέντρο-sprite (από ένα sprite sheet με διάφορα δέντρα).
    *   Επιλέγονται με **τυχαίο τρόπο** οι συντεταγμένες στην περιοχή (π.χ., 1000x1000 pixels).
    *   Δημιουργούνται τα αντικείμενα `Sprite` (`new Sprite(...)`) και προστίθενται στη λίστα (`trees.Add(tree);`).
*   **Σχεδίαση Αντικειμένων (Slide 20):** Η μέθοδος `DrawTrees()` διασχίζει τη λίστα των δέντρων. Για κάθε δέντρο, ελέγχει αν βρίσκεται στο ορατό τμήμα του επιπέδου και σχεδιάζει το δέντρο (Sprite) στο ορατό τμήμα του επιπέδου του παιχνιδιού (στο παράθυρο).

---

## Ο Κώδικας του Βρόχου του Παιχνιδιού (Slides 21-22)

Ο κώδικας της μεθόδου `doUpdate` που υλοποιεί το βρόχο του παιχνιδιού:

*   **Ελέγχει την κατάσταση των πλήκτρων** κύλισης στον κόσμο και ενημερώνει την ιδιότητα `ScrollPos` του αντικειμένου `level` με τις συντεταγμένες του νέου σημείου κύλισης.
*   Καλεί τη μέθοδος `Update` του αντικειμένου `level` προκειμένου ο scroll buffer να ανανεωθεί.
*   Καλεί τη μέθοδο `FrameRate` του αντικειμένου `game` προκειμένου να υπολογιστεί και να επιστραφεί ο πραγματικός αριθμός ανανέωσης του frame του παιχνιδιού.
*   **Εμφάνιση (Slide 22):**
    *   Σχεδιάζεται το ορατό τμήμα του χάρτη του παιχνιδιού καλώντας την μέθοδο `Draw` του αντικειμένου `level`.
    *   Σχεδιάζονται τα δέντρα που βρίσκονται στο ορατό τμήμα του χάρτη του παιχνιδιού καλώντας τη μέθοδο `DrawTrees()`.
    *   Εμφανίζονται οι πληροφορίες (συντεταγμένες αρχής, ρυθμός ανανέωσης, αριθμός ορατών δέντρων) καλώντας τη μέθοδο `Print` του αντικειμένου `game`.
    *   Ενημερώνεται το PictureBox της φόρμας με το νέο περιεχόμενο καλώντας την `game.Update()`.
    *   Εκτελούνται όλα τα συμβάντα χωρίς να "παγώνει" η φόρμα καλώντας την `Application.DoEvents()`.
    *   Εκτελούνται όλα τα συμβάντα χωρίς να "παγώνει" η φόρμα καλώντας την `Application.DoEvents()`.

---

## Προσθήκη Ελεγχόμενου Κινούμενου Χαρακτήρα (WalkAboutDemo) (Slides 23-27)

*   **WalkAboutDemo (Slide 23):** Το παράδειγμα δείχνει την υλοποίηση ενός ελεγχόμενου κινούμενου χαρακτήρα σε κυλιόμενο χάρτη.
*   **Δήλωση Μεταβλητών & Δημιουργία Sprite (Slide 24):** Δηλώνουμε μεταβλητές για τον χαρακτήρα (`Sprite hero`) και την αρχική του κατεύθυνση (`int heroDir = 0;`). Δημιουργούμε και αρχικοποιούμε το sprite του χαρακτήρα (π.χ., hero_sword_walk.png). Τοποθετούμε τον χαρακτήρα στο κέντρο της φόρμας.
*   **Ενημέρωση Μεταβλητών (Slides 25-26):**
    *   Στη μέθοδο `doUpdate` ελέγχεται η κατάσταση των πλήκτρων κύλισης και ενημερώνεται η ιδιότητα `ScrollPos` του αντικειμένου `level` με τις συντεταγμένες του νέου σημείου κύλισης.
    *   Ελέγχεται η κατάσταση των πλήκτρων κύλισης και ενημερώνεται η κατεύθυνση του χαρακτήρα (`heroDir`). Υπάρχει δυνατότητα μετακίνησης διαγώνια με συνδυασμό πλήκτρων.
    *   **ΣΗΜΑΝΤΙΚΟ:** Μιας και η κύλιση μέσα στον χάρτη γίνεται βάσει της κίνησης του βασικού ήρωα, πρέπει να φροντίσουμε να ενημερώσουμε τις συντεταγμένες του (`hero.Position`).
    *   **Σχεδίαση Sprite (Slide 27):** Στη μέθοδο `doUpdate` (εντός του χρονομετρούμενου τμήματος) καλείται η μέθοδος `Animate` της κλάσης `Sprite` για την εύρεση του κατάλληλου frame από το sprite sheet και στη συνέχεια καλείται η μέθοδος `Draw` για τη σχεδίασή του.
    *   **Προβλήματα:** Όπως θα διαπιστώσετε, ο ήρωας μας δεν μπορεί να κινηθεί στο πάνω αριστερό άκρο της οθόνης, λόγω της αρχικής του θέσης στο κέντρο της οθόνης. Αυτό θα διορθωθεί στο επόμενο παράδειγμα.

---

## Portals (Slides 28-32)

*   **Portal Demo (Slide 28):** Το παράδειγμα δείχνει την υλοποίηση **portals** (πύλες τηλεμεταφοράς).
*   **Ορισμός Portals (Slide 29):** Ο tilemap editor μας δίνει τη δυνατότητα καθορισμού σημείων τηλεμεταφοράς (portals) στο χάρτη του παιχνιδιού (ή και σε άλλο χάρτη/επίπεδο). Τα σχετικά δεδομένα διαβάζονται από το αρχείο XML.
*   **Εντοπισμός Portals (Slide 29):** Αυτό που χρειάζεται να κάνουμε είναι να απομονώσουμε το τμήμα του sprite (τα πόδια του ήρωα) που ακουμπάει στο “έδαφος” για να ελέγχουμε αν βρίσκεται σε ένα portal tile.
    *   Τυπικά το sprite-ήρωας τοποθετείται στην οθόνη βάσει των συντεταγμένων του πάνω αριστερού άκρου του.
    *   Στην περίπτωσή μας που το μέγεθος του ήρωα είναι 96x96 pixels και το μέγεθος κάθε tile είναι 32x32 pixels, αν χρησιμοποιηθεί το πάνω αριστερό άκρο του ήρωα τότε ο ήρωας θα αλληλεπιδρά με tiles στο “έδαφος” που βρίσκονται επάνω και αριστερά από το κεφάλι του!
    *   Οπότε πρέπει να εντοπίσουμε το tile στο οποίο περπατάει ο ήρωας (το tile στο οποίο βρίσκεται το σημείο των ποδιών του).
    *   Το μπλε περίγραμμα αναπαριστά το πλαίσιο οριοθέτησης/σύγκρουσης του χαρακτήρα. Το κόκκινο περίγραμμα (και η κουκίδα στο κέντρο) το πλαίσιο που περιλαμβάνει τα πόδια του ήρωα.

*   **Υλοποίηση Portals (Slides 30-32):**
    *   Αν ο ήρωας βρίσκεται σε κάποιο portal, για να τηλεμεταφερθεί πρέπει να πατήσουμε το Space (Slide 30). Θα μπορούσε βέβαια απευθείας να μεταβαίνει στη νέα θέση.
    *   **Έλεγχος Collidable (Slide 31):** Στο κώδικα ελέγχεται επιπλέον η ιδιότητα Collidable για κάθε tile, χωρίς όμως ακόμα ο ήρωας μας να ανταποκρίνεται στα εμπόδια.

