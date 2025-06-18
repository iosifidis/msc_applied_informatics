# **Greenfoot Java - Cheat Sheet**

Αυτός ο οδηγός περιέχει αποσπάσματα κώδικα από τα παραδείγματα για την ανάπτυξη παιχνιδιών στο Greenfoot, οργανωμένα ανά θέμα.

---

### **1. Βασική Δομή & Κλάσεις**

#### Δημιουργία Κόσμου (World)
Ορίζει τον κόσμο του παιχνιδιού με συγκεκριμένες διαστάσεις.
```java
import greenfoot.*;

public class CrabWorld extends World {
    public CrabWorld() {
        super(600, 400, 1);
    }
}
```

#### Η Βασική Μέθοδος 'act'
Η καρδιά κάθε Actor. Ο κώδικας εδώ εκτελείται συνεχώς.
```java
public void act() {
    // Παραδείγματα κλήσεων
    checkKeypress();
    move();
    lookForWorm();
}
```

---

### **2. Κίνηση & Έλεγχος**

#### Στροφή Αντικειμένου
Στρίβει το αντικείμενο κατά τις μοίρες που δίνονται.
```java
public void turn(int angle) {
    setRotation(getRotation() + angle);
}
```

#### Απλή Μετακίνηση
Κινεί το αντικείμενο προς την τρέχουσα κατεύθυνσή του.
```java
public void move() {
    double angle = Math.toRadians(getRotation());
    int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
    int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
    setLocation(x, y);
}
```

#### Έλεγχος Πληκτρολογίου
Ελέγχει αν πατήθηκε ένα πλήκτρο για να εκτελέσει μια ενέργεια.
```java
public void checkKeypress() {
    if (Greenfoot.isKeyDown("left")) {
        turn(-4);
    }
    if (Greenfoot.isKeyDown("right")) {
        turn(4);
    }
}
```

#### Τυχαία Στροφή
Στρίβει το αντικείμενο τυχαία, με μια μικρή πιθανότητα.
```java
public void randomTurn() {
    if (Greenfoot.getRandomNumber(100) > 90) {
        turn(Greenfoot.getRandomNumber(90) - 45);
    }
}
```

---

### **3. Αλληλεπίδραση & Έλεγχοι Ορίων**

#### Έλεγχος Ορίων Κόσμου
Ελέγχει αν το αντικείμενο έχει φτάσει στα όρια του κόσμου.
```java
public boolean atWorldEdge() {
    if (getX() < 20 || getX() > getWorld().getWidth() - 20)
        return true;
    if (getY() < 20 || getY() > getWorld().getHeight() - 20)
        return true;
    return false;
}
```

#### Εντοπισμός Αντικειμένου στην τρέχουσα Θέση
Ελέγχει αν υπάρχει ένα αντικείμενο συγκεκριμένου τύπου (`clss`) στην τρέχουσα θέση.
```java
public boolean canSee(Class clss) {
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    return actor != null;
}
```

#### Αφαίρεση Αντικειμένου
Αν βρεθεί αντικείμενο συγκεκριμένου τύπου, το αφαιρεί από τον κόσμο.
```java
public void eat(Class clss) {
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    if (actor != null) {
        getWorld().removeObject(actor);
    }
}
```

---

### **4. Σχεδίαση Σκηνικού**

#### Προσθήκη Αστεροειδών κατά τη Δημιουργία
Στον constructor του World, καλεί μια μέθοδο για να προσθέσει αντικείμενα.
```java
// Στον constructor της κλάσης World (π.χ. Space)
addAsteroids(5);

// Η μέθοδος
private void addAsteroids(int count) {
    for (int i = 0; i < count; i++) {
        Asteroid a = new Asteroid();
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        addObject(a, x, y);
    }
}
```

#### Σχεδίαση Αστεριών στο Υπόβαθρο
Γεμίζει το υπόβαθρο με τυχαία αστεράκια (μικρούς κύκλους).
```java
// Κλήση από τον constructor του World
createStars(300);

// Η μέθοδος
private void createStars(int number) {
    GreenfootImage background = getBackground();
    for(int i=0; i < number; i++) {
        int x = Greenfoot.getRandomNumber( getWidth() );
        int y = Greenfoot.getRandomNumber( getHeight() );
        int color = 120 - Greenfoot.getRandomNumber(100);
        background.setColor(new Color(color, color, color));
        background.fillOval(x, y, 2, 2);
    }
}
```

---

### **5. Κινούμενη Εικόνα (Animation)**

#### Απλή Κίνηση με 2 Εικόνες
Εναλλάσσει δύο εικόνες για να δημιουργήσει την ψευδαίσθηση της κίνησης.
```java
public class Crab extends Animal {
    private GreenfootImage image1;
    private GreenfootImage image2;

    public Crab() {
        image1 = new GreenfootImage("crab.png");
        image2 = new GreenfootImage("crab2.png");
        setImage(image1);
    }

    public void switchImage() {
        if (getImage() == image1) {
            setImage(image2);
        } else {
            setImage(image1);
        }
    }
}
```

#### Κίνηση με Πολλαπλά Frames (Πίνακας)
Χρησιμοποιεί έναν πίνακα από εικόνες και έναν μετρητή για ομαλότερη κίνηση.
```java
public class Crab extends Animal {
    private static final int NUM_OF_IMAGES = 2;
    private GreenfootImage[] images;
    private int currentImage;

    public Crab() {
        images = new GreenfootImage[NUM_OF_IMAGES];
        for (int i = 0; i < images.length; i++)
            images[i] = new GreenfootImage("crab" + i + ".png");
        currentImage = 0;
        setImage(images[currentImage]);
    }

    public void switchImage() {
        currentImage = (currentImage + 1) % NUM_OF_IMAGES;
        setImage(images[currentImage]);
    }
}
```

#### Χρήση Αρχείων GIF
Χρησιμοποιεί την κλάση `GifImage` για να αποδώσει ένα αρχείο GIF.
```java
public class Bear extends Actor {
    private GifImage gif;

    public Bear() {
        gif = new GifImage("bear.gif");
    }

    public void act() {
        setImage(gif.getCurrentImage());
    }
}
```

---

### **6. Λογική Παιχνιδιού**

#### Βασική Λογική: Σκορ & Νίκη
Ψάχνει για σκουλήκι, το τρώει, αυξάνει το σκορ και ελέγχει για νίκη.
```java
public void lookForWorm() {
    if (canSee(Worm.class)) {
        eat(Worm.class);
        Greenfoot.playSound("slurp.wav");
        wormsEaten = wormsEaten + 1;
        if (wormsEaten == 8) {
            Greenfoot.playSound("fanfare.wav");
            Greenfoot.stop();
        }
    }
}
```

#### Διαχείριση Επιπέδων
Ελέγχει αν ο παίκτης πληροί τις προϋποθέσεις για να πάει στο επόμενο επίπεδο.
```java
private void checkNextLevel() {
    if (level == 1 && wormsEaten == 8) {
        level = 2;
        wormsEaten = 0;
        Greenfoot.setWorld(new CrabWorld2(this)); // 'this' είναι το καβούρι
    } else if (level == 2 && wormsEaten == 8) {
        winMessage();
        Greenfoot.stop();
    }
}
```

#### Εμφάνιση Μηνύματος & Τερματισμός
Εμφανίζει ένα μήνυμα στην οθόνη (π.χ. "WINNER" ή "Game Over").
```java
// Μήνυμα Νίκης
private void winMessage() {
    GreenfootImage bg = getWorld().getBackground();
    bg.setColor(Color.RED);
    bg.setFont(bg.getFont().deriveFont(48f));
    bg.drawString("WINNER", 200, 250);
}

// Τερματισμός παιχνιδιού (στην κλάση World)
public void gameOver() {
    showText("Game Over", getWidth()/2, getHeight()/2);
    Greenfoot.stop();
}
```

#### Ενημέρωση Scoreboard (Κλάση Counter)
Μέθοδος σε μια κλάση Counter για την αύξηση και εμφάνιση του σκορ.
```java
// Στην κλάση Counter
public void addScore(int points) {
    value += points;
    setValue("Score: " + value);
}
```

---

### **7. Προηγμένη Φυσική (Βαρύτητα & Διάνυσμα)**

#### Εφαρμογή Δύναμης/Ώθησης (Κλάση Rocket)
Προσθέτει μια δύναμη (ώθηση) στο αντικείμενο όταν πατιέται το πλήκτρο "up".
```java
public void act() {
    if (Greenfoot.isKeyDown("up")) {
        Vector thrust = new Vector(getRotation(), 0.3);
        addForce(thrust);
    }
    move(); // Το move προέρχεται από την κλάση SmoothMover
}
```

#### Υπολογισμός Βαρυτικής Έλξης
Υπολογίζει τη δύναμη της βαρύτητας μεταξύ δύο αντικειμένων (`Body`) και την εφαρμόζει.
```java
private void applyGravity(Body other) {
    double dx = other.getExactX() - this.getExactX();
    double dy = other.getExactY() - this.getExactY();
    Vector force = new Vector(dx, dy);

    double distance = Math.sqrt(dx * dx + dy * dy);
    double strength = GRAVITY * this.mass * other.mass / (distance * distance);
    double acceleration = strength / this.mass;

    force.setLength(acceleration);
    addForce(force);
}
```

---

### **8. Σύγκρουση & Καταστροφή (Παράδειγμα: Asteroids)**

#### Έλεγχος Σύγκρουσης Rocket & Asteroid
Ελέγχει για σύγκρουση, αφαιρεί το πύραυλο και καλεί τη μέθοδο `gameOver`.
```java
private void checkCollision() {
    Asteroid a = (Asteroid) getOneObjectAtOffset(0, 0, Asteroid.class);
    if (a != null) {
        getWorld().removeObject(this); // Αφαιρεί τον πύραυλο
        getWorld().addObject(new Explosion(), getX(), getY());
        ((Space)getWorld()).gameOver();
    }
}
```

#### Διάσπαση Αστεροειδή
Αν το μέγεθος ενός αστεροειδή είναι μεγάλο, τον διασπά σε δύο μικρότερους.
```java
public void breakUp() {
    if (getSize() <= 16) {
        getWorld().removeObject(this);
    } else {
        int newSize = getSize() / 2;
        Asteroid a1 = new Asteroid(newSize);
        Asteroid a2 = new Asteroid(newSize);
        a1.setVector(new Vector(getRotation() + 30, getSpeed() * 1.2));
        a2.setVector(new Vector(getRotation() - 30, getSpeed() * 1.2));
        getWorld().addObject(a1, getX(), getY());
        getWorld().addObject(a2, getX(), getY());
        getWorld().removeObject(this);
    }
}
```

---

### **9. AI & Σχεδίαση Χάρτη**

#### Δημιουργία Χάρτη από Πλακίδια (Tile-Based)
Δημιουργεί τον κόσμο διαβάζοντας έναν πίνακα από Strings που αναπαριστούν τον χάρτη.
```java
public class TileWorld extends World {
    public static final int TWIDTH = 25;
    public static final int THEIGHT = 25;
    public static final String[] WORLD = {
        "BBBBBBBBBB",
        "BWUGWUUWBW",
        "BWWWWWWWBW",
        "BBBBBBBBBB"
    };

    public TileWorld() {
        super(WORLD[0].length() * TWIDTH, WORLD.length * THEIGHT, 1);
        for (int row = 0; row < WORLD.length; row++) {
            for (int col = 0; col < WORLD[row].length(); col++) {
                // ... κώδικας για την προσθήκη του σωστού block
            }
        }
    }
}
```

#### AI: Αλγόριθμος Εύρεσης Μονοπατιού (Α*)
Ένας σκελετός του αλγορίθμου Α* για την εύρεση του βέλτιστου μονοπατιού.
```java
public static List<Tile> findPath(Tile start, Tile goal) {
    PriorityQueue<Tile> open = new PriorityQueue<>(Comparator.comparingInt(t -> t.g + t.h));
    Set<Tile> closed = new HashSet<>();
    start.g = 0;
    start.h = heuristic(start, goal);
    open.add(start);

    while (!open.isEmpty()) {
        Tile current = open.poll();
        if (current.equals(goal)) return reconstructPath(current);
        
        closed.add(current);
        for (Tile neighbor : current.getNeighbors()) {
            if (closed.contains(neighbor) || neighbor.isObstacle()) continue;
            
            int tentativeG = current.g + distance(current, neighbor);
            if (tentativeG < neighbor.g || !open.contains(neighbor)) {
                neighbor.parent = current;
                neighbor.g = tentativeG;
                neighbor.h = heuristic(neighbor, goal);
                open.add(neighbor);
            }
        }
    }
    return Collections.emptyList();
}
```
