# **Οδηγός Κώδικα Greenfoot από τις Διαφάνειες**

**Γενικά για το Greenfoot & τη Βασική API**

*   **Τι είναι:** Εκπαιδευτικό περιβάλλον προγραμματισμού για παιχνίδια/προσομοιώσεις σε 2D, χρησιμοποιώντας **Java**. Απλοποιεί γραφική διασύνδεση και συμπεριφορά αντικειμένων.
*   **Δομή:** Βασίζεται στις κλάσεις **`World`** (ο κόσμος/επίπεδο) και **`Actor`** (τα αντικείμενα/χαρακτήρες). Δημιουργούμε υποκλάσεις αυτών.
*   **API:** Παρέχει μια απλοποιημένη διεπαφή προγραμματισμού με βασικές λειτουργίες.

    *   `import greenfoot.*;`
        *   **Τι κάνει:** Εισάγει όλες τις βασικές κλάσεις της Greenfoot API στο αρχείο.
        *   **Γιατί/Πώς:** Είναι απαραίτητο στην αρχή κάθε αρχείου κώδικα κλάσης για να μπορείτε να χρησιμοποιήσετε τις κλάσεις όπως `World`, `Actor`, `GreenfootImage`, `Greenfoot`.

    *   `public class MyWorld extends World` (Παράδειγμα: `CrabWorld`, `Space`, `MazeWorld` κτλ.)
        *   **Τι κάνει:** Δηλώνει μια νέα κλάση για τον κόσμο/επίπεδο του παιχνιδιού, η οποία κληρονομεί λειτουργικότητα από την βασική κλάση `World`.
        *   **Γιατί/Πώς:** Κάθε σενάριο Greenfoot χρειάζεται τουλάχιστον μία υποκλάση της `World` για να ορίσει το περιβάλλον του παιχνιδιού.

    *   `public class MyActor extends Actor` (Παράδειγμα: `Crab`, `Lobster`, `Worm`, `Body`, `Rocket`, `Asteroid` κτλ.)
        *   **Τι κάνει:** Δηλώνει μια νέα κλάση για ένα αντικείμενο/χαρακτήρα του παιχνιδιού, η οποία κληρονομεί λειτουργικότητα από την βασική κλάση `Actor`.
        *   **Γιατί/Πώς:** Κάθε αντικείμενο που εμφανίζεται στον κόσμο και μπορεί να αλληλεπιδρά, πρέπει να είναι στιγμιότυπο (instance) μιας υποκλάσης της `Actor`.

    *   `super(width, height, cellsize)`
        ```java
        public CrabWorld()
        {
            super(600, 400, 1);
        }
        ```
        *   **Τι κάνει:** Καθορίζει τις διαστάσεις του κόσμου σε **κελιά** (`width`, `height`) και το μέγεθος κάθε κελιού σε **pixels** (`cellsize`).
        *   **Γιατί/Πώς:** Ο κατασκευαστής της `World` χρησιμοποιείται για να ορίσει τις βασικές διαστάσεις του περιβάλλοντος παιχνιδιού. Πρέπει να καλείται στην πρώτη γραμμή του κατασκευαστή της δικής σας υποκλάσης `World` (`super(...)` call).

    *   `void act()`
        ```java
        public void act()
        {
            // Code executed in each game step
        }
        ```
        *   **Τι κάνει:** Είναι η βασική μέθοδος συμπεριφοράς ενός `Actor`. Καλείται αυτόματα κάθε φορά που "τρέχει" ο βρόχος του παιχνιδιού (είτε πατώντας 'Act' είτε 'Run').
        *   **Γιατί/Πώς:** Περιλαμβάνει τον κώδικα που ορίζει τι πρέπει να κάνει το αντικείμενο σε κάθε βήμα (π.χ. κίνηση, έλεγχος πλήκτρων, έλεγχος συγκρούσεων).

---

**Project Demo 1: Crabs, Worms and Lobsters**
(Διαφάνειες 42-81 - Βασική κίνηση, έλεγχος πλήκτρων, σύγκρουση, σκορ, επίπεδα)

*   **Χαρακτήρες:** `Crab`, `Worm`, `Lobster` (υποκλάσεις της `Actor`). Χρήση αφηρημένης κλάσης `Animal` (εισαγόμενη από Greenfoot Library) ως ενδιάμεση υπερκλάση για κοινές συμπεριφορές (κίνηση, έλεγχος ορίων/αντικειμένων, φάγωμα).

    *   `void turn(int angle)` (Στην κλάση `Animal`)
        ```java
        public void turn(int angle)
        {
            setRotation(getRotation() + angle);
        }
        ```
        *   **Τι κάνει:** Στρίβει το αντικείμενο κατά `angle` μοίρες σε σχέση με την τρέχουσα περιστροφή.
        *   **Γιατί/Πώς:** Χρησιμοποιεί τις ενσωματωμένες μεθόδοus `setRotation` (ορίζει περιστροφή) και `getRotation` (ανακτά τρέχουσα περιστροφή) της κλάσης `Actor` για να αλλάξει την κατεύθυνση του αντικειμένου.

    *   `void move()` (Στην κλάση `Animal`)
        ```java
        public void move()
        {
            double angle = Math.toRadians( getRotation() );
            int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
            int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
            setLocation(x, y);
        }
        ```
        *   **Τι κάνει:** Μετακινεί το αντικείμενο προς την τρέχουσα κατεύθυνση (σύμφωνα με την περιστροφή) κατά `WALKING_SPEED` pixels.
        *   **Γιατί/Πώς:** Υπολογίζει τη νέα θέση (x, y) χρησιμοποιώντας το sin/cos της τρέχουσας περιστροφής (μετατρεμμένη σε ακτίνια) και την απόσταση (`WALKING_SPEED`), και ορίζει τη νέα θέση με `setLocation(x, y)` της κλάσης `Actor`. `WALKING_SPEED` είναι μια σταθερά. (Βλ. διαφάνιση 53-54).

    *   `boolean atWorldEdge()` (Στην κλάση `Animal`)
        ```java
        public boolean atWorldEdge()
        {
            if(getX() < 20 || getX() > getWorld().getWidth() - 20)
                 return true;
            if(getY() < 20 || getY() > getWorld().getHeight() - 20)
                 return true;
            return false;
        }
        ```
        *   **Τι κάνει:** Επιστρέφει `true` αν το αντικείμενο βρίσκεται κοντά στα όρια του κόσμου, αλλιώς `false`.
        *   **Γιατί/Πώς:** Χρησιμοποιεί τις μεθόδους `getX()`, `getY()` (θέση Actor) και `getWorld().getWidth()`, `getWorld().getHeight()` (διαστάσεις World) για να ελέγξει αν το αντικείμενο είναι εντός συγκεκριμένης απόστασης (π.χ. 20 pixels) από τα όρια. (Βλ. διαφάνιση 55).

    *   `boolean canSee(Class clss)` (Στην κλάση `Animal`)
        ```java
        public boolean canSee(Class clss)
        {
            Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
            return actor != null;
        }
        ```
        *   **Τι κάνει:** Ελέγχει αν υπάρχει αντικείμενο συγκεκριμένου τύπου (`clss`) στην ίδια θέση με το τρέχον αντικείμενο.
        *   **Γιατί/Πώς:** Χρησιμοποιεί την ενσωματωμένη μέθοδο `getOneObjectAtOffset` της κλάσης `Actor` με offset (0, 0) για να βρει ένα αντικείμενο στον ίδιο "κελί/pixel". Επιστρέφει `true` αν βρει αντικείμενο (αν η αναφορά `actor` δεν είναι `null`). (Βλ. διαφάνιση 56).

    *   `void eat(Class clss)` (Στην κλάση `Animal`)
        ```java
        public void eat(Class clss)
        {
            Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
            if(actor != null) {
                getWorld().removeObject(actor);
            }
        }
        ```
        *   **Τι κάνει:** Αν υπάρχει αντικείμενο συγκεκριμένου τύπου (`clss`) στην ίδια θέση, το αφαιρεί από τον κόσμο.
        *   **Γιατί/Πώς:** Χρησιμοποιεί την `getOneObjectAtOffset` (όπως η `canSee`) και αν βρει αντικείμενο, καλεί την ενσωματωμένη μέθοδο `getWorld().removeObject(actor)` της κλάσης `World` για να το αφαιρέσει. (Βλ. διαφάνιση 57).

    *   `void checkKeypress()` (Στην κλάση `Crab`)
        ```java
        public void checkKeypress()
        {
            if (Greenfoot.isKeyDown("left"))
            {
                turn(-4);
            }
            if (Greenfoot.isKeyDown("right"))
            {
                turn(4);
            }
        }
        ```
        *   **Τι κάνει:** Ελέγχει αν τα πλήκτρα αριστερά ή δεξιά πατιούνται και στρίβει τον κάβουρα κατάλληλα.
        *   **Γιατί/Πώς:** Χειρίζεται την είσοδος του παίκτη. Χρησιμοποιεί την static μέθοδο `Greenfoot.isKeyDown(keyName)` για να ελέγξει την κατάσταση συγκεκριμένων πλήκτρων. (Βλ. διαφάνιση 62).

    *   `void lookForWorm()` (Στην κλάση `Crab`)
        ```java
        public void lookForWorm()
        {
            if ( canSee(Worm.class) )
            {
                eat(Worm.class);
                Greenfoot.playSound("slurp.wav");
                wormsEaten = wormsEaten + 1;
                if (wormsEaten == 8)
                {
                    Greenfoot.playSound("fanfare.wav");
                    Greenfoot.stop();
                }
            }
        }
        ```
        *   **Τι κάνει:** Ελέγχει αν υπάρχει σκουλήκι στην ίδια θέση, το τρώει, αυξάνει το σκορ και ελέγχει αν ο παίκτης κέρδισε.
        *   **Γιατί/Πώς:** Υλοποιεί την αλληλεπίδραση του κάβουρα με τα σκουλήκια και τη συνθήκη νίκης. Χρησιμοποιεί την `canSee()`, `eat()`, μια μεταβλητή σκορ (`wormsEaten`), `Greenfoot.playSound()` για ήχο και `Greenfoot.stop()` για τερματισμό. (Βλ. διαφάνιση 63).

    *   `void act()` (Στην κλάση `Crab`)
        ```java
        public void act()
        {
            checkKeypress();
            move();
            lookForWorm();
        }
        ```
        *   **Τι κάνει:** Εκτελεί την πλήρη συμπεριφορά του κάβουρα σε κάθε βήμα του παιχνιδιού.
        *   **Γιατί/Πώς:** Είναι η κύρια μέθοδος που καλείται αυτόματα από το Greenfoot και συγχρονίζει τις διάφορες ενέργειες του αντικειμένου. (Βλ. διαφάνιση 64).

    *   `void turnAtEdge()` (Στην κλάση `Lobster`)
        ```java
        public void turnAtEdge()
        {
            if ( atWorldEdge() )
            {
                turn(17);
            }
        }
        ```
        *   **Τι κάνει:** Αν ο αστακός φτάσει στα όρια του κόσμου, στρίβει κατά 17 μοίρες.
        *   **Γιατί/Πώς:** Υλοποιεί απλή συμπεριφορά "αναπήδησης" στα όρια. Χρησιμοποιεί την κληρονομημένη μέθοδο `atWorldEdge()`. (Βλ. διαφάνιση 69).

    *   `void randomTurn()` (Στην κλάση `Lobster`)
        ```java
        public void randomTurn()
        {
            if (Greenfoot.getRandomNumber(100) > 90) {
                turn(Greenfoot.getRandomNumber(90)-45);
            }
        }
        ```
        *   **Τι κάνει:** Με πιθανότητα 10% (αν τυχαίος αριθμός 0-99 είναι > 90), στρίβει τυχαία κατά μια γωνία μεταξύ -45 και 45 μοιρών.
        *   **Γιατί/Πώς:** Υλοποιεί τυχαία κίνηση. Χρησιμοποιεί `Greenfoot.getRandomNumber(limit)` για τυχαίους ακέραιους 0 .. `limit`-1. (Βλ. διαφάνιση 70).

    *   `void lookForCrab()` (Στην κλάση `Lobster`)
        ```java
        public void lookForCrab()
        {
            if ( canSee(Crab.class) )
            {
                eat(Crab.class);
                Greenfoot.playSound("au.wav");
                Greenfoot.stop();
            }
        }
        ```
        *   **Τι κάνει:** Ελέγχει αν υπάρχει κάβουρας στην ίδια θέση, τον τρώει και τερματίζει το παιχνίδι (ήττα).
        *   **Γιατί/Πώς:** Υλοποιεί την αλληλεπίδραση του αστακού (ανταγωνιστής) με τον κάβουρα (παίκτης) και τη συνθήκη ήττας. Χρησιμοποιεί `canSee()`, `eat()`, `Greenfoot.playSound()`, `Greenfoot.stop()`. (Βλ. διαφάνιση 71).

    *   `void act()` (Στην κλάση `Lobster`)
        ```java
        public void act()
        {
            turnAtEdge();
            randomTurn();
            move();
            lookForCrab();
        }
        ```
        *   **Τι κάνει:** Εκτελεί την πλήρη συμπεριφορά του αστακού.
        *   **Γιατί/Πώς:** Είναι η κύρια μέθοδος που καλείται αυτόματα από το Greenfoot και συγχρονίζει τις ενέργειες του αστακού. (Βλ. διαφάνιση 72).

    *   **Υλοποίηση Animation (Crab):**
        ```java
        public class Crab extends Animal
        {
             private GreenfootImage image1;
             private GreenfootImage image2;
             private int wormsEaten = 0; // Score field
             public Crab() // Constructor
             {
                  image1 = new GreenfootImage("crab.png");
                  image2 = new GreenfootImage("crab2.png");
                  setImage(image1);
             }

             public void act() // ... calls checkKeypress, move, lookForWorm, switchImage
             {
                 checkKeypress();
                 move();
                 lookForWorm();
                 switchImage(); // Call the animation method in act()
             }

             public void switchImage() // Animation method
             {
                  if (getImage() == image1)
                  {
                      setImage(image2);
                  }
                  else
                  {
                      setImage(image1);
                  }
             }
             // ... rest of Crab methods (checkKeypress, lookForWorm)
        }
        ```
        *   **Τι κάνει:** Δίνει την ψευδαίσθηση κίνησης στον κάβουρα εναλλάσσοντας δύο εικόνες (με τα πόδια μέσα/έξω).
        *   **Γιατί/Πώς:** Είναι η απλούστερη μορφή animation. Απαιτεί δύο εικόνες (`GreenfootImage`), πεδία για την αποθήκευσή τους και μια μέθοδο που τις εναλλάσσει (συνήθως στην act()). (Βλ. διαφάνιση 66-67, 42).

    *   **Προσθήκη Επιπέδων:**
        ```java
        public class CrabWorld2 extends World // New Level Class
        {
            // Level 2 specific fields/constructor parameters
            public CrabWorld2(Crab crab) // Constructor receiving the player object
            {
                super(600, 600, 1); // Set world dimensions
                prepare(crab); // Call prepare to add objects including player
            }
            
            private void prepare(Crab crab) // Prepare method
            {
                addObject(crab, 308, 303); // Add player to this level
                // Add Lobsters/Worms for Level 2
            }
        }
        ```
        ```java
        public class Crab extends Animal
        {
            private int level = 1; // Field to track current level

            // ... other fields and methods ...

            private void checkNextLevel() // Method to check and change level
            {
                if (level==1 && wormsEaten==8) // Condition for next level
                {
                     level = 2;
                     wormsEaten = 0;
                     getWorld().removeObject(this); // Remove player from current world
                     Greenfoot.playSound("fanfare.wav");
                     Greenfoot.setWorld(new CrabWorld2(this)); // Change world, pass player
                } else if (level==2 && wormsEaten==8) // Condition for winning
                {
                     Greenfoot.playSound("fanfare.wav");
                     // Call win message / Stop game
                }
            }

            public void act() // act() should call checkNextLevel
            {
                // ... keypress, move, lookForWorm ...
                checkNextLevel(); // Call level check
            }
        }
        ```
        *   **Τι κάνει:** Δημιουργεί ένα δεύτερο επίπεδο, επιτρέπει τον παίκτη (`Crab`) να μεταφερθεί σε αυτό και διαχειρίζεται την αλλαγή επιπέδου όταν επιτευχθεί ο στόχος του επιπέδου.
        *   **Γιατί/Πώς:** Υλοποιεί ένα παιχνίδι με πολλαπλά επίπεδα. Απαιτεί νέες υποκλάσεις `World` για κάθε επίπεδο, πέρασμα του αντικειμένου του παίκτη μεταξύ των επιπέδων (μέσω κατασκευαστή/μεθόδου `prepare`) και έλεγχο συνθήκης αλλαγής επιπέδου (`checkNextLevel`) καλώντας την static μέθοδο `Greenfoot.setWorld(worldObject)`. (Βλ. διαφάνιση 33-38).

---

**Project Demo 2: Newton's Lab**
(Διαφάνεις 51-81 - Προσομοίωση Φυσικής)

*   **Χαρακτήρες:** `Body` (πλανήτης, ήλιος, φεγγάρι - υποκλάση `SmoothMover`), `Space` (ο κόσμος - υποκλάση `World`).
*   **Βοηθητικές Κλάσεις:** `SmoothMover` (κληρονομείται), `Vector` (χρησιμοποιείται ως πεδίο/τύπος παραμέτρου).

    *   **`SmoothMover`** (Εισαγόμενη από Greenfoot Library):
        *   **Κώδικας:** `double getExactX()`, `double getExactY()`, `Vector getMovement()`, `double getSpeed()`, `void move()`, `void accelerate(double factor)`, `void addForce(Vector force)`, `void setLocation(double x, double y)` (βλ. διαφάνιση 65-66).
        *   **Τι κάνει:** Βασική λειτουργικότητα για ομαλή κίνηση με διανύσματα και εφαρμογή δυνάμεων.
        *   **Γιατί/Πώς:** Δίνει πιο ρεαλιστική κίνηση από την απλή ακέραιη μετακίνηση. Η `move()` μετακινεί με βάση το τρέχον διάνυσμα `movement`. Η `addForce(Vector)` προσθέτει ένα διάνυσμα δύναμης στο τρέχον διάνυσμα κίνησης (επιτάχυνση).

    *   **`Vector`** (Εισαγόμενη από Greenfoot Library):
        *   **Κώδικας:** Constructor `Vector(double dx, double dy)` ή `Vector(int direction, double length)`, `void add(Vector other)`, `double getLength()`, `double getDx()`, `double getDy()`, `void setLength(double length)`, κτλ. (βλ. διαφάνιση 67-69).
        *   **Τι κάνει:** Αναπαριστά και χειρίζεται διανύσματα (κίνηση, δυνάμεις).
        *   **Γιατί/Πώς:** Διευκολύνει τον υπολογισμό δυνάμεων και κίνησης στην φυσική προσομοίωση.

    *   **`Body`** (Υποκλάση `SmoothMover`)
        ```java
        public class Body extends SmoothMover
        {
             // Constants, fields (mass, movement vector, color)
             private double mass; // Example field

             // Constructor (receives size, mass, initial movement, color)

             public void act() // act() method
             {
                 applyForces(); // Calculate and apply forces from other bodies
                 move(); // Move according to current movement vector
             }

             private void applyForces() // Method to calculate and apply total forces
             {
                 List<Body> bodies = getWorld().getObjects(Body.class);
                 for (Body body : bodies)
                 {
                      if (body != this) // Avoid self-gravity
                      {
                           applyGravity(body); // Apply gravity from this specific body
                      }
                 }
             }

             private void applyGravity(Body other) // Method to apply gravity from one other body
             {
                  double dx = other.getExactX() - this.getExactX();
                  double dy = other.getExactY() - this.getExactY();
                  Vector force = new Vector(dx, dy); // Force direction is from this to other

                  double distance = Math.sqrt(dx*dx + dy*dy); // Calculate distance

                  double strength = GRAVITY * this.mass * other.mass / (distance * distance); // Newton's Law

                  double acceleration = strength / this.mass; // Acceleration due to this force

                  // Update movement vector
                  this.addForce(force); // Apply direction of force
                  this.getMovement().setLength(this.getMovement().getLength() + acceleration); // Apply magnitude (acceleration) to update velocity
             }
        }
        ```
        *   **Τι κάνει:** Αναπαριστά ένα σώμα που επηρεάζεται από τη φυσική, υπολογίζει και εφαρμόζει δυνάμεις βαρύτητας από άλλα σώματα και κινείται ανάλογα.
        *   **Γιατί/Πώς:** Υλοποιεί τη συμπεριφορά των πλανητών/αστρών. Ενσωματώνει τον υπολογισμό βαρύτητας (`applyGravity`) και την εφαρμογή των συνολικών δυνάμεων (`applyForces`) στον βρόχο `act()`. Χρησιμοποιεί τις κληρονομημένες μεθόδους `move()` και `addForce()` της `SmoothMover`. `GRAVITY` είναι μια σταθερά.

---

**Project Demo 3: Asteroids**
(Διαφάνεις 82-118 - Έλεγχος Συγκρούσεων, Dynamic Objects, Score, Game Over)

*   **Χαρακτήρες:** `Rocket`, `Asteroid` (υποκλάσεις `SmoothMover`), `ProtonWave`, `Explosion`, `ScoreBoard`, `Counter` (υποκλάσεις `Actor`), `Space` (κόσμος).

    *   **Έλεγχος Συγκρούσεων (`Collider methods`):** (Κληρονομούνται από Actor)
        *   `java.util.List getObjects(Class cls)`
        *   `Actor getOneIntersectingObject(Class cls)`
        *   `java.util.List getObjectsInRange(int radius, Class cls)`
        *   **Τι κάνει:** Βρίσκει αντικείμενα συγκεκριμένου τύπου: όλα στον κόσμο, ένα που τέμνεται με το όριο του Actor, ή όλα σε συγκεκριμένη ακτίνα από το κέντρο του Actor.
        *   **Γιατί/Πώς:** Βασικές μέθοδοι της Greenfoot API για την ανίχνευση γειτονικών ή επικαλυπτόμενων αντικειμένων, απαραίτητες για αλληλεπιδράσεις και συγκρούσεις. (Βλ. διαφάνιση 94-97).

    *   **`void checkCollision()`** (Στην κλάση `Rocket`)
        ```java
        private void checkCollision()
        {
            Actor a = getOneIntersectingObject(Asteroid.class); // Check collision with Asteroid
            if (a != null) // If collision occurred
            {
                Space space = (Space) getWorld(); // Get reference to the World object
                space.addObject(new Explosion(), getX(), getY()); // Add explosion at rocket's position
                space.removeObject(this); // Remove the rocket
                space.gameOver(); // Call gameOver method in World (Space class)
            }
        }
        ```
        *   **Τι κάνει:** Ελέγχει αν ο πύραυλος συγκρούεται με αστεροειδή, αφαιρεί τον πύραυλο, προσθέτει έκρηξη και καλεί τη μέθοδο `gameOver()` του κόσμου (`Space`).
        *   **Γιατί/Πώς:** Υλοποιεί τη συνθήκη ήττας (σύγκρουση παίκτη). Χρησιμοποιεί `getOneIntersectingObject` για τον εντοπισμό, `getWorld().removeObject(this)` για την αφαίρεση του ίδιου του αντικειμένου και καλεί method στον κόσμο (`Space`). (Βλ. διαφάνιση 98).

    *   **`void gameOver()`** (Στην κλάση `Space`)
        ```java
        public void gameOver()
        {
             addObject(new ScoreBoard(999), getWidth()/2, getHeight()/2); // Example: adding ScoreBoard with dummy score
             // In a real game, you'd pass the actual score.
             Greenfoot.stop(); // Stop the game simulation
        }
        ```
        *   **Τι κάνει:** Δημιουργεί ένα αντικείμενο `ScoreBoard` (UI) και το προσθέτει στον κόσμο στο κέντρο. Σταματά την προσομοίωση.
        *   **Γιατί/Πώς:** Υλοποιεί την οθόνη τερματισμού του παιχνιδιού. Χρησιμοποιεί την μέθοδο `addObject(Actor, x, y)` της κλάσης `World` και την static μέθοδο `Greenfoot.stop()`. (Βλ. διαφάνιση 104).

    *   **`ScoreBoard`** (Υποκλάση `Actor`)
        ```java
        public class ScoreBoard extends Actor
        {
            // ... static final fields for font size, width, height ...
            public ScoreBoard(int score) // Constructor
            {
                // ... code to create the visual representation using GreenfootImage ...
                makeImage("Game Over", "Score: ", score); // Call internal method to draw text
            }

            private void makeImage(String title, String prefix, int score)
            {
                GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
                // ... code to draw background, text (title, prefix+score) on 'image' ...
                setImage(image); // Set this image as the Actor's visual
            }
        }
        ```
        *   **Τι κάνει:** Δημιουργεί την οπτική αναπαράσταση του πίνακα σκορ/μηνύματος Game Over με κείμενο.
        *   **Γιατί/Πώς:** Είναι το UI στοιχείο που δείχνει την κατάσταση του παιχνιδιού. Χρησιμοποιεί την κλάση `GreenfootImage` και τις μεθόδους της (`setColor`, `setFont`, `drawString`, `fill`) για να σχεδιάσει κείμενο και σχήματα στην εικόνα του Actor. (Βλ. διαφάνιση 102-103).

    *   **`ProtonWave`** (Υποκλάση `Actor`, δυναμικό αντικείμενο/βλήμα)
        ```java
        public class ProtonWave extends Actor // Example methods from this class
        {
            // static final fields (DAMAGE, NUMBER_IMAGES), GreenfootImage[] images

            // Constructor calls initializeImages, sets initial image, plays sound

            private void grow() // Animation/Size Increase method
            {
                imageCount++;
                if (imageCount >= NUMBER_IMAGES)
                {
                     getWorld().removeObject(this); // Remove self when animation done
                }
                else
                {
                     setImage(images[imageCount]); // Set next animation frame
                     // ... optionally check collision here ...
                }
            }

            private void checkCollision() // Collision check method for ProtonWave
            {
                 int range = getImage().getWidth()/2; // Use current image size as range
                 List<Asteroid> asteroids = getObjectsInRange(range, Asteroid.class); // Find Asteroids in range

                 for (Asteroid a : asteroids) // For each found Asteroid
                 {
                      a.hit(DAMAGE); // Call the hit method on the Asteroid, passing damage
                 }
            }

             public void act() // act() calls grow and checkCollision
            {
                grow(); // Animate/grow in size
                checkCollision(); // Check collision at current size/position
            }

            // static initializeImages() method to load scaled frames
            // ...
        }
        ```
        *   **Τι κάνει:** Υλοποιεί ένα ισχυρό βλήμα με animation, καθυστέρηση πυροβολισμού, ζημιά σε εχθρούς και καταστροφή τους. Μεγαλώνει σε μέγεθος και αφαιρείται.
        *   **Γιατί/Πώς:** Δυναμική δημιουργία και αλληλεπίδραση με εχθρούς. Χρησιμοποιεί animation με πολλά frames (`images`, `grow`), static μεταβλητές (`DAMAGE`), μετρητή χρόνου (`protonDelayCount`). Η `act()` συγχρονίζει ανάπτυξη (`grow`) και έλεγχο σύγκρουσης (`checkCollision`). `checkCollision()` χρησιμοποιεί `getObjectsInRange` για εντοπισμό και καλεί `hit(DAMAGE)` στα `Asteroid` που βρήκε. (Βλ. διαφάνιση 105-116).

    *   **`void hit(int damage)`** (Στην κλάση `Asteroid`)
        ```java
        public class Asteroid extends SmoothMover
        {
             private int stability; // Health/Stability field

             public void hit(int damage) // Method called when hit
             {
                  stability = stability - damage;
                  if (stability <= 0) // If health is zero or less
                  {
                      breakUp(); // Call the death/breakup method
                  }
             }

             private void breakUp() // Method for death/breakup
             {
                  Greenfoot.playSound("Explosion.wav"); // Play sound
                  if ( /* size is small */ ) // Check size condition
                  {
                      getWorld().removeObject(this); // Remove self
                  } else { // If size is large
                      // Create 2 new smaller asteroids, set their movement, add to world
                      // Remove self
                  }
             }
             // ... other Asteroid methods and fields
        }
        ```
        *   **Τι κάνει:** Μειώνει την υγεία/σταθερότητα του αστεροειδούς, και αν μηδενιστεί, καλεί `breakUp()`.
        *   **Γιατί/Πώς:** Υλοποιεί τη λήψη ζημιάς και τον "θάνατο" εχθρού. Η `breakUp()` υλοποιεί την καταστροφή (αφαίρεση) ή διάσπαση (δημιουργία νέων αντικειμένων) ανάλογα με την κατάσταση του αντικειμένου. (Βλ. διαφάνιση 116-117).

---

**Project Demo 4: MazeWorld**
(Διαφάνεις 119-133 - Tilemaps, Scrolling, AI, A\*)

*   **Χαρακτήρες:** `Spider`, `Snake`, `Mouse` (υποκλάσεις `ScrollingEnemy`), `Hiker` (παίκτης), `BlackBlock`, `BlueBlock`, `WhiteBlock`, `GoldBlock` (Tiles, υποκλάσεις `ScrollingObstacle`). `MazeWorld` (κόσμος).
*   **Βοηθητικές Κλάσεις:** `ScrollingEnemy` (κληρονομείται), `ScrollingObstacle` (κληρονομείται), `TiledWorldPathfinding` (υλοποιεί A\*), `Tile`, `Point` (για A\*).

    *   **Tilemap (`Level` or `MazeWorld` methods):**
        ```java
        public class MazeWorld extends World
        {
            private static final String[] WORLD = { // Tilemap data as String Array
                 "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                 "BWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWB",
                 // ... more rows ...
            };
            // ... Fields (offset, size, tilemap array)

            private void createWorldFromTiles() // Method to build the world from array
            {
                for (int i=0; i < WORLD.length; i++) // Loop through rows
                {
                    for (int j=0; j < WORLD[i].length(); j++) // Loop through columns
                    {
                        char c = WORLD[i].charAt(j); // Get char for the tile type
                        // Call addActorAtTileLocation(c, j, i); // Call helper method to add Actor
                    }
                }
            }

            private void addActorAtTileLocation(char c, int x, int y) // Helper method to add Actor based on char
            {
                Actor tile = null;
                switch(c) // Use switch case based on char
                {
                     case 'W': tile = new WhiteBlock(); break;
                     case 'B': tile = new BlackBlock(); break;
                     // ... other cases for 'U', 'G' ...
                }
                if (tile != null) // If a tile actor was created
                {
                    addObject(tile, TILEOFFSET+x*TWIDTH, TILEOFFSET+y*THEIGHT); // Add tile actor at calculated pixel position
                }
                // ... add other Actors (Hiker, Enemies) based on specific char positions or separate logic ...
            }

            // ... Other methods (act, constructor calling createWorldFromTiles)
        }
        ```
        *   **Τι κάνει:** Αποθηκεύει τη δομή του χάρτη (ποιο tile είναι κάθε θέση) σε ένα String array και δημιουργίζει τα αντίστοιχά τους αντικείμενα στον κόσμο κατά την εκτέλεση.
        *   **Γιατί/Πώς:** Υλοποιεί ένα tile-based επίπεδο. Ο πίνακας `WORLD` είναι η αναπαράσταση του χάρτη. `createWorldFromTiles` διατρέχει τον πίνακα. `addActorAtTileLocation` δημιουργεί το κατάλληλο αντικείμενο (`new WhiteBlock()`, `new Snake()`) στην σωστή pixel θέση. (Βλ. διαφάνιση 121-123).

    *   **`ScrollingEnemy`** (Αφηρημένη κλάση για AI εχθρούς)
        ```java
        public abstract class ScrollingEnemy extends Actor // Abstract base class for enemy AI
        {
            // Fields (speedX, speedY, reference to game world/level etc)

            public final void act() // Final act method calling AI steps
            {
                 sense(); // Step 1: Perceive environment
                 reaction(); // Step 2: Decide action based on perception
                 boundedMove(); // Step 3: Perform movement (common logic)
            }

            protected abstract void sense(); // Abstract method: To be implemented by subclasses
            protected abstract void reaction(); // Abstract method: To be implemented by subclasses

            protected final void boundedMove() // Final method: Common movement logic
            {
                 setLocation(getX()+speedX, getY()+speedY); // Attempt to move

                 // Collision check with obstacles (ScrollingObstacle class)
                 if (isTouching(ScrollingObstacle.class))
                 {
                     // Revert to previous position
                     setLocation(getX()-speedX, getY()-speedY);
                 }
            }
        }
        ```
        *   **Τι κάνει:** Ορίζει τη βασική δομή συμπεριφοράς των "έξυπνων" εχθρών (αντίληψη περιβάλλοντος, επιλογή δράσης, μετακίνηση).
        *   **Γιατί/Πώς:** Αφηρημένη κλάση για κληρονομικότητα και επαναχρησιμοποίηση κώδικα. `act()` συντονίζει τις 3 φάσεις (Αισθάνεται, Επιλέγει δράση, Μετακινείται). `sense` & `reaction` είναι abstract και υλοποιούνται στις υποκλάσεις. `boundedMove` υλοποιείται εδώ, βασίζεται σε `speedX/Y` και ελέγχει σύγκρουση με `ScrollingObstacle`. (Βλ. διαφάνιση 125-126).

    *   **`Spider`** (Υποκλάση `ScrollingEnemy`, Τυχαία AI)
        ```java
        public class Spider extends ScrollingEnemy // Implements Random AI
        {
             // Fields (SPEEDVARIATION, SPEEDCHANGECHANCE etc)

             protected void sense() { /* Does nothing for random AI */ } // Empty implementation
             
             protected void reaction() // Implements random decision making
             {
                 if (Greenfoot.getRandomNumber(1000) < SPEEDCHANGECHANCE) // Check probability
                 {
                     // Assign random speedX, speedY (e.g., 1, 0, -1)
                 }
             }
             // boundedMove inherited from base class
        }
        ```
        *   **Τι κάνει:** Κινείται τυχαία στον χάρτη.
        *   **Γιατί/Πώς:** Η απλούστερη AI. Δεν υλοποιεί `sense`. `reaction` χρησιμοποιεί `Greenfoot.getRandomNumber` για να αλλάζει κατεύθυνση σποραδικά. (Βλ. διαφάνιση 127).

    *   **`Snake`** (Υποκλάση `ScrollingEnemy`, Ευρετική AI)
        ```java
        public class Snake extends ScrollingEnemy // Implements Heuristic AI
        {
             // Fields (pathing boolean flag, pathCounter, PATHLENGTH)

             protected void sense() // Implements player proximity check
             {
                 // Check if Hiker is in range (using getObjectsInRange)
                 // Set pathing flag based on proximity
             }

             protected void reaction() // Implements behavior based on pathing
             {
                  if (pathing) { // If player is in range
                     // Set speedX/Y towards player (using ?: operator)
                  } else { // If player is not in range
                     // Set speedX/Y for back-and-forth movement
                  }
             }
             // boundedMove inherited from base class
        }
        ```
        *   **Τι κάνει:** Ελέγχει αν ο παίκτης είναι κοντά, αν ναι τον κυνηγά, αλλιώς κινείται πίσω-μπρος.
        *   **Γιατί/Πώς:** Υλοποιεί απλούς "αν-τότε" κανόνες (heuristics). `sense` χρησιμοποιεί `getObjectsInRange` και ενημερώνει ένα boolean (`pathing`). `reaction` χρησιμοποιεί την τιμή του `pathing` για να ορίσει ταχύτητα (`speedX/Y`). (Βλ. διαφάνιση 128-129).

    *   **`Mouse`** (Υποκλάση `ScrollingEnemy`, A\* AI)
        *   **Κώδικας:** (Χρησιμοποιεί την κλάση `TiledWorldPathfinding` και βοηθητικές `Tile`, `Point`. Κώδικας A\* υλοποιείται σε αυτές τις κλάσεις.)
        *   **Τι κάνει:** Βρίσκει βέλτιστο μονοπατι από την τρέχουσα θέση στην θέση-στόχο (Player) αποφεύγοντας εμπόδια (B, U tiles).
        *   **Γιατί/Πώς:** Προηγμένη AI για εύρεση μονοπατιού. Ο αλγόριθμος A\* συγκρίνει μονοπάτια χρησιμοποιώντας την τιμή F = G (διανυθείσα απόσταση) + H (εκτίμηση απόστασης για στόχο - Manhattan distance). Επιλέγει το μονοπατι με το ελάχιστο F. (Βλ. διαφάνιση 130-133).

    *   **Scrolling:**
        *   **Κώδικας:** `private PointF scrollPos`, `private void drawTilemap()` (loops + calculation based on scrollPos), `void doUpdate()` (calls drawTilemap after updating scrollPos based on input), `Form1_KeyUp()` (updates boolean key flags, βλ. διαφάνιση 130, 131, 133).
        *   **Τι κάνει:** Εμφανίζει ένα τμήμα ενός μεγάλου χάρτη, με τον χρήστη να ελέγχει ποιο τμήμα εμφανίζεται με πλήκτρα.
        *   **Γιατί/Πώς:** Επιτρέπει την υλοποίηση κόσμων μεγαλύτερων από την οθόνη. Απαιτεί μεταβλητή `scrollPos` για τη θέση του πάνω αριστερού ορατού tile/pixel. Η μέθοδος σχεδίασης (`drawTilemap`) διαβάζει από τον πίνακα δεδομένων (`WORLD`) τα tiles που αντιστοιχούν στην ορατή περιοχή (συνάρτηση των βρόχων επανάληψης και του `scrollPos`). Η `scrollPos` ενημερώνεται από την είσοδο του χρήστη (π.χ., σε `doUpdate` ή σε event handlers KeyUp). Υπάρχει η τεχνική **Full-Tile Scrolling** (κύλιση κατά 1 tile) και **Sub-Tile Scrolling** (κύλιση pixel-pixel, χρησιμοποιώντας extra buffer, βλ. διαφάνιση 133).

