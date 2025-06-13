# Greenfoot TileBasedWorld AI

## MazeWorld: Ένα Παράδειγμα Σύνθετου Κόσμου (Slide 2)

- Το project "MazeWorld" παρουσιάζει ένα παιχνίδι όπου ο παίκτης (ένας πεζοπόρος - Hiker) κινείται σε ένα **λαβύρινθο** γεμάτο **εμπόδια** και **εχθρούς**.
- Οι εχθροί έχουν διαφορετικά επίπεδα **νοημοσύνης**, κάνοντας το παιχνίδι πιο δύσκολο και ενδιαφέρον.

---

## Σχεδίαση του Χάρτη του Παιχνιδιού (Slides 3-5)

- Στο MazeWorld, ο χάρτης του παιχνιδιού δημιουργείται από **πλακίδια (tiles)**. Κάθε πλακίδιο έχει συγκεκριμένη διάσταση (25x25 pixels).

- **Τύποι Πλακιδίων (Slide 3):** Υπάρχουν 4 διαφορετικοί τύποι πλακιδίων που συμβολίζονται με τους χαρακτήρες και σχεδιάζονται με τη βοήθεια 4 κλάσεων (υποκλάσεις της `ScrollingObstacle`):

  - **B:** μη προσβάσιμη περιοχή (μαύρο χρώμα - `BlackBlock`)
  - **U:** σταθερό εμπόδιο (μπλε χρώμα - `BlueBlock`)
  - **W:** προσβάσιμη περιοχή (λευκό χρώμα - `WhiteBlock`)
  - **G:** στόχος – θησαυρός (κίτρινο χρώμα - `GoldBlock`)

- **Αποθήκευση Πληροφορίας Χάρτη (Slide 3-4):** Η πληροφορία για τον τύπο κάθε ενός από τα πλακίδια που αποτελούν τον χάρτη του παιχνιδιού αποθηκεύεται στον πίνακα αλφαριθμητικών `WORLD`:

```java
private final static String[] WORLD =
{
    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
    "BWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWB",
    "BWWWWWWWWWWWWWUUWWWWWWWWUUUUUUUWWWWWWWWWWWUWWWWB",
    "BWWWWWUUUUUWWWUUWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWB",
    "BWWWWWUUUUUWWWWWWWWWWWWWWWWWWWWWWWWWUUUWWWWWWWWB",
    "BWWWWWWWWWWWWWWWWWUUUUUWWWWWWWWUUUUUUWWWWWWWWWWB",
    "BWWWWWWWWWWWWWWWWWUUUUWWWWWWWWWUUUUUUUUWWWWWWWWB",
    "BWWWWUUUUUUUWWWUWWWWWWWWWWWWWWWUWWWWWWWWWWWWWWWB",
    "BWWWWWWWUUUWWWWOWWWWWWWWWWOWWWWUWWWWWWWWWWWWWWWB",
    "BWWWWWWWWWWWWWWWWWWWWOWWWWWWWWWWWWWWWWWWOWWB",
    "BWWWWWWWWWWWWWWWWWWWUUUUUUUWWWWWWWWWUUUUWWWWOWWB",
    "BWWWWWWWWWWWWWUUWWWWWWWWWWWWWWWWWWWWUUUUWWWWUWWB",
    "BWWWWWWWUUUUUUUUDJOWWWWWW WWWWWWWWWWWWWWWWWUUUUUUWWOWWB",
    "BWWWWWWWUUUUUUUUUWWWWWWWWWUUWWWWWWWWWWWWWWWWUWWB",
    "BWWWWWWWUWWWWWWWWWWWWWWWWWUUWWWWWWWWWWWWWWWWUWGB",
    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
};
```

- **Δημιουργία Κόσμου από Πλακίδια (Slide 5):**
  - Η κλάση `MazeWorld` κληρονομεί από την `World`.
  - Υπάρχουν ιδιωτικές μεταβλητές που ορίζουν διαστάσεις:

```java
public class MazeWorld extends World
{
    private int xOffset = 0;
    private Hiker hiker;
    private final static int SWIDTH = 600; // Screen Width
    private final static int SHEIGHT = 400; // Screen Height
    private final static int WWIDTH = 1200; // World Width (potentially larger than screen)
    private final static int TWIDTH = 25; // Tile Width
    private final static int THEIGHT = TWIDTH; // Tile Height
    private final static int TILEOFFSET = TWIDTH/2; // Offset for centering actors
    private final static String validSpaces = "WG"; // Tile types where actor can spawn? (Based on context)
    // ... rest of class (WORLD array, methods)
```

    *   Υπάρχει μια ιδιωτική μέθοδος `createWorldFromTiles()` που διαβάζει τον πίνακα `WORLD` και δημιουργεί τα αντίστοιχα αντικείμενα:

```java
private void createWorldFromTiles() {
    for(int i=0; i < WORLD.length; i++) { // Iterate rows
        for(int j=0; j < WORLD[i].length(); j++) { // Iterate columns
            addActorAtTileLocation (WORLD[i].charAt(j), j, i); // Add actor for character at (j, i)
        }
    }
}
```

    *   Η μέθοδος `addActorAtTileLocation()` δημιουργεί το αντικείμενο (`Actor`) βασιζόμενη στον χαρακτήρα (`char c`) και το προσθέτει στον κόσμο στην σωστή pixel θέση (μετατρέποντας τις συντεταγμένες του πλέγματος `(x, y)` σε συντεταγμένες κόσμου).

```java
private void addActorAtTileLocation (char c, int x, int y) {
    Actor tile = null;
    switch(c) {
        case 'W':
            tile = new WhiteBlock();
            break;
        case 'B':
            tile = new BlackBlock();
            break;
        case 'U':
            tile = new BlueBlock();
            break;
        case 'G':
            tile = new GoldBlock();
            break;
    }
    if (tile != null)
        addObject (tile, TILEOFFSET+x*TWIDTH, TILEOFFSET+y*THEIGHT); // Add the created tile actor
}
```

- **Κλάσεις για Πλακίδια και Χαρακτήρες (Slide 5):** Το διάγραμμα δείχνει την ιεραρχία των κλάσεων:
  - `Actor` (Greenfoot Base Class)
  - `ScrollingActor` (Εφαρμόζει Scrolling συμπεριφορά)
    - `ScrollingObstacle` (Αφηρημένη κλάση για εμπόδια)
      - `BlueBlock`, `BlackBlock`, `GoldBlock`, `WhiteBlock` (Οι συγκεκριμένοι τύποι πλακιδίων)
    - `ScrollingEnemy` (Αφηρημένη κλάση για εχθρούς)
      - `Snake`, `Mouse`, `Spider` (Οι συγκεκριμένοι εχθροί)
    - `Hiker` (Ο παίκτης)

---

## Χαρακτήρες με Νοημοσύνη (Slides 6-11)

- Στο MazeWorld, οι εχθροί έχουν διαφορετικά επίπεδα νοημοσύνης (`AI`) για να δυσκολεύουν τον παίκτη.
- **Τεχνικές Τεχνητής Νοημοσύνης (Slide 6):**

  - **Τυχαία κίνηση:** Η πιο απλή τεχνική (π.χ., `Spider`).
  - **Ευρετικοί κανόνες συμπεριφοράς:** Βασίζονται σε απλούς κανόνες "αν-τότε" (π.χ., `Snake`).
  - **Έξυπνη εύρεση μονοπατιού:** Χρήση αλγορίθμων όπως ο Α\* για εύρεση βέλτιστου μονοπατιού αποφεύγοντας εμπόδια (π.χ., `Mouse`).

- **Αφηρημένη κλάση ScrollingEnemy (Slides 7-8):**
  - Ορίζει κοινά σημεία στη συμπεριφορά των "έξυπνων" χαρακτήρων.
  - Η μέθοδος `act()` περιλαμβάνει 3 αφηρημένες ενέργειες (μεθόδους) που υλοποιούνται στις υποκλάσεις: `sense()`, `reaction()`, `boundedMove()`.
  - Η υπογραφή της μεθόδου act:

```java
public void act() {
    sense(); // Sense the environment
    reaction(); // Choose action based on sensing (and previous state)
    boundedMove(); // Perform the move
}
```

    *   **Ενημέρωση κίνησης:** Οι μέθοδοι `sense` και `reaction` αλλάζουν τις τιμές των μεταβλητών `speedX` και `speedY` του εχθρού (αυτές οι μεταβλητές είναι πιθανότατα μέλη της `ScrollingActor` ή `ScrollingEnemy`), επηρεάζοντας την κίνηση.
    *   **`boundedMove()`:** Υλοποιείται στην `ScrollingEnemy`. Μετακινεί το αντικείμενο κατά `speedX`, `speedY` και ελέγχει αν η νέα θέση τέμνεται με εμπόδιο (`ScrollingObstacle`). Αν ναι, επαναφέρει την προηγούμενη θέση.

```java
protected void boundedMove () {
    setLocation(getX()+speedX, getY() +speedY); // Attempt to move
    if(isTouching (ScrollingObstacle.class) ) { // Check for collision with obstacles
        setLocation (getX()-speedX, getY()-speedY); // Revert to previous location
    }
}
```

- **Τυχαία κίνηση: Spider (Slide 9):**
  - Υποκλάση της `ScrollingEnemy`.
  - Απλή τεχνική: Η αράχνη κινείται με τυχαίο τρόπο.
  - Δεν υλοποιεί τη μέθοδο `sense()` (ή έχει μια κενή υλοποίηση).
  - Η μέθοδος `reaction()` θέτει στις μεταβλητές `speedX` και `speedY` την τιμή 1, 0, ή -1 με μικρή πιθανότητα για να αλλάξει κατεύθυνση σποραδικά.

```java
public class Spider extends ScrollingEnemy
{
    private final static int SPEEDVARIATION = 3; // Possible speed values: -1, 0, 1 (from 0 to 2 then subtract 1)
    private final static int SPEEDCHANGECHANCE = 20; // Probability inverse (1 in 20 attempts)

    protected void reaction() {
        // Change speedX with a low probability
        speedX = Greenfoot.getRandomNumber (1000) < SPEEDCHANGECHANCE ?
                 Greenfoot.getRandomNumber (SPEEDVARIATION)-1 : speedX;
        // Change speedY with a low probability
        speedY = Greenfoot.getRandomNumber (1000) < SPEEDCHANGECHANCE ?
                 Greenfoot.getRandomNumber (SPEEDVARIATION)-1 : speedY;
    }
    // ... rest of class
}
```

_Σημείωση: Το `Greenfoot.getRandomNumber(1000) < SPEEDCHANGECHANCE` με `SPEEDCHANGECHANCE = 20` σημαίνει αλλαγή με πιθανότητα 20/1000 = 2% σε κάθε frame. Το `Greenfoot.getRandomNumber(SPEEDVARIATION)-1` με `SPEEDVARIATION = 3` δίνει τυχαία -1, 0, ή 1._

- **Ευρετικοί κανόνες συμπεριφοράς: Snake (Slides 10-11):**
  - Υποκλάση της `ScrollingEnemy`.
  - Η μέθοδος `sense()` ελέγχει αν ο παίκτης (`Hiker`) βρίσκεται σε μια ακτίνα γύρω από το φίδι. Ενημερώνει ένα πεδίο `pathing` (πιθανώς boolean ή λίστα με αντικείμενα) ανάλογα.

```java
protected void sense() {
    // If near, move towards enemy
    java.util.List lse = getObjectsInRange (INRANGE, Hiker.class); // Get objects (Hikers) within a specified range
    pathing = lse.isEmpty(); // Set pathing to true if list is NOT empty (i.e., Hiker is in range) -> Note: The slide shows pathing=lse.isEmpty() which seems counterintuitive based on the 'If near, move towards enemy' comment and reaction method. Let's assume 'pathing' indicates "should I use pathfinding or specific behavior?". A better variable name might be `isHikerNearby` and set it to `!lse.isEmpty()`. Based on the reaction logic, `pathing = lse.isEmpty()` means the snake goes back-and-forth when the hiker is *not* nearby, and chases when the hiker *is* nearby (`!pathing` case). So, `pathing` actually means "should I *not* path/chase?". Let's stick to the variable name from the slide but clarify its meaning based on the reaction method. `pathing == true` means Hiker is *NOT* in range, `pathing == false` means Hiker *IS* in range.
}
```

    *   Η μέθοδος `reaction()`, ανάλογα με την τιμή του `pathing`:

```java
protected void reaction() {
    if (pathing) { // if Hiker is NOT in range (based on interpretation above)
        speedX = rememberSpeedX; // Keep the speedX for back-and-forth movement
        speedY = 0; // Don't move vertically in this state
        if(--pathCounter == 0) { // Counter for back-and-forth movement
            pathCounter = PATHLENGTH; // Reset counter
            speedX = rememberSpeedX = -speedX; // Reverse horizontal direction and store it
        }
    } else { // else (if Hiker IS in range)
        // Chase the hiker - set speedX and speedY towards the hiker's location
        java.util.List lse = getObjectsInRange(INRANGE, Hiker.class); // Re-check for nearby hikers
        if (!lse.isEmpty()) { // Ensure a hiker is actually found (belt-and-suspenders check)
             Actor target = (Actor) lse.get(0); // Get the first hiker found
             speedX = target.getX() > getX() ? 1 : -1; // Move horizontally towards target
             speedY = target.getY() > getY() ? 1 : -1; // Move vertically towards target
        }
    }
}
```

_Σημείωση: `rememberSpeedX`, `pathCounter`, `PATHLENGTH`, `INRANGE` είναι μεταβλητές/σταθερές που δεν φαίνονται στην κλάση `Snake` αλλά χρησιμοποιούνται._

- **Έξυπνη εύρεση μονοπατιού: Mouse (Slides 12-14)**

  - Υποκλάση της `ScrollingEnemy`.
  - Χρησιμοποιεί τον **αλγόριθμο Α\*** για να βρει ένα μονοπάτι ανάμεσα σε 2 σημεία (από την τρέχουσα θέση του ποντικιού στη θέση του παίκτη) αποφεύγοντας τα εμπόδια.
  - **Αναπαράσταση Περιοχής:** Χρησιμοποιείται ένα στιγμιότυπο της κλάσης **`Tile`** για κάθε περιοχή (πλακίδιο) του χάρτη.
  - **Θέση Tile:** Η θέση κάθε `Tile` προσδιορίζεται με ένα στιγμιότυπο της κλάσης **`Point`** (καθορίζει γραμμή και στήλη) μέσα στο νοητό πλέγμα του χάρτη.
  - **Πληροφορία Χάρτη:** Η πληροφορία για όλα τα `Tiles` αποθηκεύεται σε έναν δισδιάστατο πίνακα **`tileWorld`** (αυτός είναι πιθανώς ένα grid από αντικείμενα `Tile` σε αντίθεση με τον πίνακα `WORLD` που είναι string data).
  - **Πώς Λειτουργεί ο Α\* (Slide 13):** Ο αλγόριθμος εξερευνά περιοχές στον χάρτη σε επαναλήψεις. Σε κάθε επανάληψη, συγκρίνει τις "υποψήφιες" περιοχές (τους κόμβους στην Open List) και επιλέγει εκείνη με την μικρότερη τιμή **F**.
  - **Υπολογισμός Τιμής F (Slide 14):** Για κάθε περιοχή (κόμβο), ο Α\* υπολογίζει μια τιμή F βασιζόμενος σε δύο παράγοντες:

    - **H (Heuristic):** Προσεγγίζει την **απόσταση που απομένει** μέχρι τη θέση-στόχο. Στο MazeWorld, χρησιμοποιείται η **Manhattan distance** (άθροισμα των απόλυτων διαφορών συντεταγμένων x και y).
    - **G (Cost):** Η **απόσταση που έχει ήδη διανυθεί** από την αρχική θέση μέχρι τον τρέχοντα κόμβο.
    - Η τιμή F είναι το **άθροισμα G + H**. Ο αλγόριθμος Α\* αναζητά το μονοπάτι με την μικρότερη συνολική τιμή F.

  - **Υλοποίηση Α\* (Slide 14-15):** Ο αλγόριθμος υλοποιείται στην κλάση **`TiledWorldPathfinding`**. Τα βήματα του αλγορίθμου είναι:
    1.  Πρόσθεσε την αρχική θέση στην Open List.
    2.  Διάλεξε τον κόμβο στην Open List με την ελάχιστη τιμή F.
    3.  Αφαίρεσε τον επιλεγμένο κόμβο από την Open List και πρόσθεσέ τον στην Closed List.
    4.  Για κάθε γείτονα του επιλεγμένου κόμβου που δεν είναι ήδη στην Closed List και δεν περιέχει εμπόδιο:
        α. Υπολόγισε την τιμή F και όρισε τον τρέχοντα κόμβο ως γονέα του γείτονα.
        β. Αν ο γείτονας δεν είναι ήδη στην Open List, πρόσθεσέ τον.
        γ. Αν ο γείτονας είναι ήδη στην Open List, ενημέρωσε την τιμή F και τον γονέα του αν βρεθεί καλύτερο μονοπάτι.
    5.  Αν ο στόχος δεν έχει επιτευχθεί, πήγαινε στο βήμα 2.
    6.  Αν ο στόχος επιτευχθεί, κατασκεύασε το μονοπάτι από την αρχική θέση μέχρι την τελική ανατρέχοντας στους γονείς (backtracking).
