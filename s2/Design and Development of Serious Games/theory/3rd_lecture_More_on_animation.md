# More on animation

## Αλλαγή Εικόνας (frame) Χαρακτήρα (Slide 2)

- Όπως είδαμε στο προηγούμενο μάθημα με τον κάβουρα, η πιο απλή μορφή animation είναι η **εναλλαγή μεταξύ δύο εικόνων (frames)**.
- Στην κλάση `Crab`, προσθέσαμε δύο ιδιότητες τύπου `GreenfootImage` (`image1`, `image2`) για να αποθηκεύσουμε τις δύο εικόνες.
- Στον **κατασκευαστή** της κλάσης (`public Crab()`), φορτώσαμε τις εικόνες από αρχεία (`"crab.png"`, `"crab2.png"`) σε αυτές τις ιδιότητες και αρχικοποιήσαμε την εικόνα του αντικειμένου (`setImage(image1)`).
- Δημιουργήσαμε μια μέθοδο `switchImage()` που εναλλάσσει την εικόνα του αντικειμένου (`setImage()`) ανάλογα με την τρέχουσα εικόνα (`getImage()`).
- Τέλος, καλέσαμε τη μέθοδο `switchImage()` μέσα στην `act()` μέθοδο, ώστε η εικόνα να αλλάζει σε κάθε βήμα εκτέλεσης του παιχνιδιού.

_(Ο κώδικας για την κλάση `Crab` με 2 frames animation παρουσιάστηκε και στο προηγούμενο μάθημα και βρίσκεται στη Slide 2 του τρέχοντος αρχείου)_:

```java
// Από τη Slide 2: Κλάση Crab με απλό animation (2 frames)
public class Crab extends Animal // Υποκλάση της Animal, όπως είδαμε στο προηγούμενο μάθημα
{
    // Ιδιότητες για τις δύο εικόνες
    private GreenfootImage image1;
    private GreenfootImage image2;

    // ... Άλλες ιδιότητες, π.χ. private int wormsEaten; ...

    /**
     * Constructor for objects of class Crab.
     */
    public Crab()
    {
        // Φόρτωση των δύο εικόνων
        image1 = new GreenfootImage("crab.png"); // Όνομα αρχείου για το frame 1
        image2 = new GreenfootImage("crab2.png"); // Όνομα αρχείου για το frame 2

        // Ορισμός της αρχικής εικόνας
        setImage(image1);

        // ... Αρχικοποίηση άλλων ιδιοτήτων, π.χ. wormsEaten = 0; ...
    }

    /**
     * Act - do whatever the Crab wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // ... Λογική κίνησης, ελέγχου πλήκτρων, αλληλεπίδρασης κτλ. ...

        // Καλέστε τη μέθοδο switchImage() για εναλλαγή εικόνας
        switchImage();
    }

    /**
     * Εναλλάσσει την εικόνα του καβουριού για animation.
     */
    public void switchImage()
    {
        // Ελέγχει ποια είναι η τρέχουσα εικόνα και την αλλάζει στην άλλη
        if (getImage() == image1)
        {
            setImage(image2);
        }
        else
        {
            setImage(image1);
        }
    }
}
```

---

## Χρήση Περισσότερων Frames (Slides 3-4)

- Για ένα καλύτερο **αισθητικό αποτέλεσμα** και μεγαλύτερη **πιστότητα στην κίνηση**, συνήθως χρησιμοποιούμε **περισσότερα από 2 frames** για κάθε κατάσταση του χαρακτήρα (π.χ., περπάτημα, τρέξιμο).
- Η λύση με ξεχωριστή ιδιότητα `GreenfootImage` για κάθε frame **δεν είναι πρακτική ή σχεδιαστικά αποτελεσματική** όταν τα frames είναι πολλά.
- **Προτεινόμενη Προσέγγιση:**
  - Δώστε στα αρχεία εικόνων των frames το **ίδιο όνομα** με έναν **αύξοντα αριθμό** αμέσως μετά (π.χ., `crab0.png`, `crab1.png`, `crab2.png`, ...).
  - Φορτώστε όλες αυτές τις εικόνες σε έναν **πίνακα (array)** τύπου `GreenfootImage[]`.
  - Κρατήστε σε ένα πεδίο (`private int currentImage;`) τον **δείκτη** του τρέχοντος frame μέσα στον πίνακα.
  - Υπολογίστε και επιλέξτε το **επόμενο frame** με βάση τον τρέχοντα δείκτη και το μέγεθος του πίνακα.
- **Υλοποίηση (Slide 4):**

```java
// Από τη Slide 4: Κλάση Crab με animation από πίνακα frames
public class Crab extends Animal
{
    // Σταθερά για το συνολικό αριθμό frames
    private static final int NUM_OF_IMAGES = 2; // Εδώ ακόμα με 2 frames όπως στη διαφάνεια

    // Πίνακας για την αποθήκευση των εικόνων (frames)
    private GreenfootImage[] images;

    // Δείκτης για το τρέχον frame
    private int currentImage;

    // ... Άλλες ιδιότητες ...

    /**
     * Constructor for objects of class Crab.
     */
    public Crab()
    {
        // Δημιουργία του πίνακα frames με το συγκεκριμένο μέγεθος
        images = new GreenfootImage[NUM_OF_IMAGES];

        // Φόρτωση των εικόνων στον πίνακα χρησιμοποιώντας βρόχο
        // Υποθέτουμε τα αρχεία είναι ονοματισμένα "crab0.png", "crab1.png", ...
        for(int i = 0; i < images.length; i++)
        {
            images[i] = new GreenfootImage("crab" + i + ".png");
        }

        // Αρχικοποίηση του δείκτη στο πρώτο frame
        currentImage = 0;

        // Ορισμός της αρχικής εικόνας από τον πίνακα
        setImage(images[currentImage]);

        // ... Αρχικοποίηση άλλων ιδιοτήτων ...
    }

    /**
     * Act - do whatever the Crab wants to do.
     */
    public void act()
    {
        // ... Λογική κίνησης, ελέγχου πλήκτρων, αλληλεπίδρασης κτλ. ...

        // Καλέστε τη μέθοδο switchImage() για εναλλαγή frame
        switchImage();
    }

    /**
     * Υπολογίζει τον επόμενο δείκτη frame και ορίζει την εικόνα.
     */
    public void switchImage()
    {
        // Υπολογισμός του επόμενου δείκτη frame χρησιμοποιώντας modulo
        // currentImage++ αυξάνει τον δείκτη
        // % NUM_OF_IMAGES επαναφέρει τον δείκτη στο 0 όταν φτάσει στο τέλος του πίνακα
        currentImage = (currentImage + 1) % NUM_OF_IMAGES;

        // Ορισμός της εικόνας του αντικειμένου στο τρέχον frame
        setImage(images[currentImage]);
    }
}
```

---

## Χρήση της κλάσης GifImage και αρχείων gif (Slides 5-6)

- Μια εναλλακτική προσέγγιση για την υλοποίηση animation είναι η χρήση της **βοηθητικής κλάσης `GifImage`** του Greenfoot και αρχείων εικόνας τύπου **.gif**.
- Μπορούμε να βρούμε αρχεία gif με animation ελεύθερα στο διαδίκτυο. (Βλ. Slide 5 - δυνατότητα import GifImage)
- **Πώς Λειτουργεί:**
  - Εισάγετε την κλάση `GifImage` στο project σας (`Edit -> Import Class...` και επιλέξτε `GifImage`).
  - Στην κλάση του χαρακτήρα (π.χ., `Bear`), δηλώστε ένα πεδίο τύπου `GifImage` (`private GifImage gif;`).
  - Στον **κατασκευαστή**, δημιουργήστε ένα νέο στιγμιότυπο της `GifImage`, περνώντας ως όρισμα το όνομα του αρχείου gif (π.χ., `gif = new GifImage("bear.gif");`).
  - Στη μέθοδο `act()`, καλέστε τη μέθοδο `setImage(gif.getCurrentImage());`. Η μέθοδος `getCurrentImage()` της κλάσης `GifImage` επιστρέφει **αυτόματα** το επόμενο frame του animation του gif σε κάθε κλήση, κάνοντας την εναλλαγή εικόνων πολύ εύκολη.

_(Ο κώδικας για την κλάση `Bear` που χρησιμοποιεί `GifImage` βρίσκεται στη Slide 6)_:

```java
// Από τη Slide 6: Κλάση Bear με animation από αρχείο gif
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GifImage; // Πρέπει να κάνετε import την κλάση GifImage

public class Bear extends Actor // Υποκλάση της Actor (ή όποιας άλλης βάσης)
{
    // Ιδιότητα για το αντικείμενο GifImage
    private GifImage gif;

    /**
     * Constructor for objects of class Bear.
     */
    public Bear()
    {
        // Δημιουργία του αντικειμένου GifImage, περνώντας το όνομα του αρχείου gif
        gif = new GifImage("bear.gif"); // Όνομα αρχείου gif

        // ... Άλλη αρχικοποίηση ...
    }

    /**
     * Act - do whatever the Bear wants to do.
     */
    public void act()
    {
        // ... Λογική κίνησης, ελέγχου κτλ. ...

        // Ορισμός της εικόνας στο τρέχον frame του animation του gif
        // Η getCurrentImage() προχωράει αυτόματα στο επόμενο frame σε κάθε κλήση
        setImage(gif.getCurrentImage());
    }

    // ... Άλλες μέθοδοι ...
}
```

---

## Χρήση Διαφορετικών Frames ανά Κατεύθυνση (Slides 7-10)

- Για ακόμα καλύτερα αποτελέσματα, ειδικά σε παιχνίδια όπου οι χαρακτήρες κινούνται προς πολλές κατευθύνσεις (π.χ., πάνω, κάτω, αριστερά, δεξιά), είναι σύνηθες να χρησιμοποιούμε **διαφορετικό σύνολο frames** για κάθε κατεύθυνση. (Βλ. Slide 7 - Παράδειγμα frames για δεξιά/αριστερά)
- **Παράδειγμα:** Έχουμε 3 frames για την κίνηση "δεξιά" (`hero0.png`, `hero1.png`, `hero2.png`) και 3 frames για την κίνηση "αριστερά" (`hero3.png`, `hero4.png`, `hero5.png`).
- **Πώς το υλοποιούμε:**
  - Όπως και πριν, αποθηκεύουμε **όλα τα frames** σε έναν πίνακα `GreenfootImage[] images;`. Ο συνολικός αριθμός frames (`NUM_OF_IMAGES`) είναι το άθροισμα των frames για όλες τις κατευθύνσεις. (Βλ. Slide 8)
  - Ορίστε μια σταθερά για τον **αριθμό των frames ανά κατεύθυνση** (`private static final int IMAGES_PER_DIRECTION = 3;`). (Βλ. Slide 8)
  - Διατηρήστε τον δείκτη του τρέχοντος frame `currentImage;`. (Βλ. Slide 8)
  - Δημιουργήστε μια μέθοδο animation (π.χ., `animate(int first, int last)`) που θα αναλαμβάνει την εναλλαγή των frames **εντός ενός συγκεκριμένου εύρους** του πίνακα. Τα ορίσματα `first` και `last` θα καθορίζουν το **εύρος των δεικτών** του πίνακα που αντιστοιχούν στα frames για μια συγκεκριμένη κατεύθυνση. (Βλ. Slide 9)
  - Στη μέθοδο `animate()`, ελέγξτε αν ο τρέχων δείκτης (`currentImage`) βρίσκεται εντός του συγκεκριμένου εύρους (`first` έως `last`). Αν δεν είναι, επαναφέρετε τον δείκτη στην αρχή του εύρους (`currentImage = first;`). Αν είναι εντός, αυξήστε τον δείκτη (`currentImage++`).
  - Ορίστε την εικόνα του αντικειμένου στο `images[currentImage]`. (Βλ. Slide 9)
  - Στη μέθοδο ελέγχου πλήκτρων (π.χ., `checkKeys()`), αν πατηθεί ένα πλήκτρο για μια συγκεκριμένη κατεύθυνση (π.χ., "right"): (Βλ. Slide 9)
    - Καλέστε την μέθοδο `animate()`, περνώντας της τους **δείκτες του εύρους** που αντιστοιχούν στα frames αυτής της κατεύθυνσης. Για παράδειγμα, αν τα frames για δεξιά είναι από 0 έως 2, καλέστε `animate(0, 2);`.
    - Αν πατηθεί το πλήκτρο για την αντίθετη κατεύθυνση (π.χ., "left"), καλέστε την `animate()` με το αντίστοιχο εύρος δεικτών (π.χ., `animate(3, 5);`).
  - Στην `act()` μέθοδο, καλέστε την `checkKeys()`. (Αν έχουμε και αυτόματη κίνηση, θα πρέπει να χειριστούμε και πότε γίνεται η αλλαγή εικόνας όταν δεν πατιέται πλήκτρο). (Βλ. Slide 8 - act καλεί checkKeys)
- **Επεκτάσεις:** Φυσικά, μπορούμε να χρησιμοποιήσουμε:
  - Περισσότερα frames για κάθε κατεύθυνση.
  - Διαφορετικά σύνολα frames για τις υπόλοιπες κατευθύνσεις (πάνω, κάτω, διαγώνιες). (Βλ. Slide 10 - παράδειγμα με 4 κατευθύνσεις)

_(Ο κώδικας για την κλάση `Hero` με animation ανά κατεύθυνση βρίσκεται στις Slides 8 και 9)_:

```java
// Από τη Slide 8: Κλάση Hero με animation ανά κατεύθυνση - Μέρος 1
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Hero extends Actor // Υποκλάση της Actor (ή όποιας άλλης βάσης)
{
    // Σταθερά για το συνολικό αριθμό frames
    private static final int NUM_OF_IMAGES = 6; // Υποθέτουμε 6 frames συνολικά (π.χ. 3 για δεξιά, 3 για αριστερά)

    // Σταθερά για τον αριθμό frames ανά κατεύθυνση
    private static final int IMAGES_PER_DIRECTION = 3; // Υποθέτουμε 3 frames ανά κατεύθυνση

    // Πίνακας για την αποθήκευση όλων των frames
    private GreenfootImage[] images;

    // Δείκτης για το τρέχον frame
    private int currentImage;

    // ... Άλλες ιδιότητες ...

    /**
     * Constructor for objects of class Hero.
     */
    public Hero()
    {
        // Δημιουργία του πίνακα frames
        images = new GreenfootImage[NUM_OF_IMAGES];

        // Φόρτωση όλων των εικόνων στον πίνακα
        // Υποθέτουμε τα αρχεία είναι hero0.png, hero1.png, ..., hero5.png
        for(int i = 0; i < images.length; i++)
        {
            images[i] = new GreenfootImage("hero" + i + ".png");
        }

        // Αρχικοποίηση του δείκτη και της εικόνας
        currentImage = 0;
        setImage(images[currentImage]);

        // ... Άλλη αρχικοποίηση ...
    }

    /**
     * Act - do whatever the Hero wants to do.
     */
    public void act()
    {
        // ... Λογική κίνησης, αλληλεπίδρασης κτλ. ...

        // Έλεγχος πλήκτρων και αλλαγή frame/κατεύθυνσης
        checkKeys();
    }

    // ... Άλλες μέθοδοι (π.χ. για κίνηση) ...

    // Από τη Slide 9: Κλάση Hero με animation ανά κατεύθυνση - Μέρος 2
    /**
     * Εναλλάσσει τα frames εντός ενός συγκεκριμένου εύρους [first, last].
     */
    public void animate(int first, int last)
    {
        // Ελέγχει αν ο τρέχων δείκτης βρίσκεται εκτός του επιθυμητού εύρους
        if (currentImage < first || currentImage > last)
        {
            // Αν ναι, επαναφέρει τον δείκτη στην αρχή του εύρους
            currentImage = first;
        }
        else
        {
            // Αν είναι εντός του εύρους, προχωράει στο επόμενο frame
            currentImage++;
            // Ελέγχει αν ο δείκτης ξεπέρασε το τέλος του εύρους
            if (currentImage > last) {
                 currentImage = first; // Αν ναι, επαναφέρει στην αρχή του εύρους
            }
            // Σημείωση: Η διαφάνεια χρησιμοποιεί "currentImage >= last",
            // που επαναφέρει λίγο νωρίτερα. Η "currentImage > last"
            // επιτρέπει στο τελευταίο frame του εύρους να εμφανιστεί κανονικά.
        }

        // Ορίζει την εικόνα του αντικειμένου στο τρέχον frame
        setImage(images[currentImage]);
    }

    /**
     * Ελέγχει τα πατημένα πλήκτρα και καλεί το κατάλληλο animation.
     */
    private void checkKeys()
    {
        // Αν πατήθηκε το δεξί βελάκι
        if (Greenfoot.isKeyDown("right"))
        {
            // Κάλεσε animation για κίνηση προς τα δεξιά (frames 0, 1, 2)
            animate(0, 2);
            // ... Προσθέστε και λογική κίνησης (π.χ. setLocation, move) ...
        }
        // Αν πατήθηκε το αριστερό βελάκι
        if (Greenfoot.isKeyDown("left"))
        {
            // Κάλεσε animation για κίνηση προς τα αριστερά (frames 3, 4, 5)
            animate(3, 5);
            // ... Προσθέστε και λογική κίνησης (π.χ. setLocation, move) ...
        }
        // ... Προσθέστε ελέγχους και για άλλες κατευθύνσεις αν υπάρχουν ...
    }
}
```
