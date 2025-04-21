# Μεθόδοι από το Greenfoot Project (Crab, Lobster, Worm, Animal)

## 🐾 **Μέθοδοι Κλάσης `Animal` (υπερκλάση Crab και Lobster)**

### **1. Στροφή κατά `angle` μοίρες `public void turn(int angle)`**  
```java
public void turn(int angle) {
    setRotation(getRotation() + angle);
}
```

- Στρέφει τον χαρακτήρα κατά `angle` μοίρες προς τη φορά των δεικτών του ρολογιού (δεξιόστροφα).

### **2. Κίνηση προς την τρέχουσα κατεύθυνση `public void move()`**  
```java
public void move() {
    double angle = Math.toRadians(getRotation());
    int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
    int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
    setLocation(x, y);
}
```

- Μετακινεί τον χαρακτήρα προς την τρέχουσα κατεύθυνση κατά `WALKING_SPEED` pixels.  

### **3. Έλεγχος αν ο χαρακτήρας είναι στα όρια του κόσμου `public boolean atWorldEdge()`**  
```java
public boolean atWorldEdge() {
    if (getX() < 20 || getX() > getWorld().getWidth() - 20) return true;
    if (getY() < 20 || getY() > getWorld().getHeight() - 20) return true;
    return false;
}
```

- Επιστρέφει `true` αν ο χαρακτήρας βρίσκεται κοντά στα όρια του κόσμου.  


### **4. Έλεγχος ύπαρξης αντικειμένου τύπου `clss` στην τρέχουσα θέση `public boolean canSee(Class clss)`**  
```java
public boolean canSee(Class clss) {
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    return actor != null;
}
```

- Επιστρέφει `true` αν υπάρχει αντικείμενο τύπου `clss` στην τρέχουσα θέση.    

### **5. Κατανάλωση αντικειμένου τύπου `clss`, `public void eat(Class clss)`**  
```java
public void eat(Class clss) {
    Actor actor = (Actor) getOneObjectAtOffset(0, 0, clss);
    if (actor != null) {
        getWorld().removeObject(actor);
    }
}
```

- Αφαιρεί το αντικείμενο τύπου `clss` από τον κόσμο αν βρίσκεται στην ίδια θέση.   
   
---

## 🦀 **Κλάση Crab (Υποκλάση Animal)**

### **1. Έλεγχος πληκτρολογίου για στροφή `public void checkKeypress()`**  
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

- Ελέγχει αν πατήθηκε το αριστερό ή δεξί βελάκι και στρέφει τον καβούρα ανάλογα.

**Τίτλος:** Στρέφει τον καβούρα αριστερά ή δεξιά ανάλογα με το πλήκτρο.  

### **2. Αναζήτηση και κατανάλωση σκουληκιού `public void lookForWorm()`**  
```java
public void lookForWorm() {
    if (canSee(Worm.class)) {
        eat(Worm.class);
        Greenfoot.playSound("slurp.wav");
        wormsEaten++;
        if (wormsEaten == 8) {
            Greenfoot.playSound("fanfare.wav");
            Greenfoot.stop();
        }
    }
}
```

- Ελέγχει αν ο καβούρας βρίσκεται πάνω σε σκουλήκι, το τρώει, αυξάνει το σκορ και τερματίζει το παιχνίδι αν φτάσει τα 8 σκουλήκια.  

### **3. Βασική συμπεριφορά (act) `public void act()`**  
```java
public void act() {
    checkKeypress();
    move();
    lookForWorm();
}
```

- Καλεί τις μεθόδους `checkKeypress()`, `move()` και `lookForWorm()` σε κάθε βήμα. Βασική συμπεριφορά: στρίβει, κινείται και ψάχνει για σκουλήκια. Εκτελείται σε κάθε κύκλο (Act/Run).   
   
### **4. Αλλαγή εικόνας για εφέ κίνησης `public void switchImage()`**  
```java
public Crab()
{
    image1 = new GreenfootImage("crab.png");
    image2 = new GreenfootImage("crab2.png");
    setImage(image1);
    wormsEaten = 0;
}

public void act()
{
    …
    switchImage();
}

public void switchImage() {
    if (getImage() == image1) {
        setImage(image2);
    } else {
        setImage(image1);
    }
}
```

- Αλλάζει την εικόνα του καβούρα για να δημιουργήσει εφέ κίνησης.   
  
### 5. **`private void checkNextLevel()`**  
```java
private void checkNextLevel()
{
    if (level==1 && wormsEaten==8)
    {
        level = 2;
        wormsEaten = 0;
        getWorld().removeObject(this);
        Greenfoot.playSound("fanfare.wav");
        Greenfoot.setWorld(new CrabWorld2(this));
    }
    else if (level==2 && wormsEaten==8)
    {
        Greenfoot.playSound("fanfare.wav");
        winMessage();
        Greenfoot.stop();
    }
}
```

- Ελέγχει αν πρέπει να αλλάξει επίπεδο ανάλογα με το `wormsEaten` και `level`.

### 6. **`private void winMessage()`**  
```java
private void winMessage()
{
    GreenfootImage bg = getWorld().getBackground();
    Font font = bg.getFont();
    bg.setColor(Color.RED);
    bg.setFont(font.deriveFont(48));
    bg.drawString("WINNER", 200, 250);
}
```

- Εμφανίζει το μήνυμα “WINNER” στην οθόνη όταν κερδίζει το παιχνίδι.

---

## 🦞 **Κλάση Lobster (Υποκλάση Animal)**

### **1. Στροφή όταν φτάσει στα όρια `public void turnAtEdge()`**  
```java
public void turnAtEdge() {
    if (atWorldEdge()) {
        turn(17);
    }
}
```

 - Στρέφει τον αστακό κατά 17 μοίρες αν φτάσει στα όρια του κόσμου. 

### **2. Τυχαία στροφή `public void randomTurn()`**  
```java
public void randomTurn() {
    if (Greenfoot.getRandomNumber(100) > 90) {
        turn(Greenfoot.getRandomNumber(90) - 45);
    }
}
```

- Στρέφει τον αστακό τυχαία κατά ±45 μοίρες με πιθανότητα 9%.

### **3. Αναζήτηση και κατανάλωση καβούρι `public void lookForCrab()`**  
```java
public void lookForCrab() {
    if (canSee(Crab.class)) {
        eat(Crab.class);
        Greenfoot.playSound("au.wav");
        Greenfoot.stop();
    }
}
```

- Ελέγχει αν ο αστακός βρίσκεται πάνω σε καβούρι, το τρώει και τερματίζει το παιχνίδι.
   
### **4. Βασική συμπεριφορά (act) `public void act()`**  
```java
public void act() {
    turnAtEdge();
    randomTurn();
    move();
    lookForCrab();
}
```

- Καλεί τις μεθόδους `turnAtEdge()`, `randomTurn()`, `move()` και `lookForCrab()` σε κάθε βήμα. 
   

---

## **Κλάση Worm (Υποκλάση Animal)**
- Δεν περιλαμβάνει επιπλέον μεθόδους. Χρησιμοποιεί τις μεθόδους της `Animal` και `Actor`.

---

## 🌍 **Μέθοδοι Κλάσης `CrabWorld2`**

### 1. **`public CrabWorld2(Crab crab)`**  
```java
public class CrabWorld2 extends World
{
    // Αλλαγή κατασκευαστή ώστε να δέχεται τον πρωταγωνιστή
    public CrabWorld2(Crab crab)
    {
        super(600, 600, 1);
        prepare(crab);
    }
    private void prepare(Crab crab)
    {
        //ο αστακός περνάει πλέον μέσω παραμέτρου
        addObject(crab, 308, 303);
        …
    }
}
```

- Κατασκευαστής που δέχεται αντικείμενο `Crab` για μεταφορά του στο νέο επίπεδο.

### 2. **`private void prepare(Crab crab)`**  
```java
private void prepare(Crab crab)
    {
        //ο αστακός περνάει πλέον μέσω παραμέτρου
        addObject(crab, 308, 303);
        …
    }
```

- Τοποθετεί το καβούρι στον νέο κόσμο/επίπεδο.
    
---

### **Σημειώσεις:**
- Οι μέθοδοι `getX()`, `getY()`, `setRotation()`, `getWorld()` κ.λπ. ανήκουν στις κλάσεις `Actor` και `World` του Greenfoot.
- Οι μέθοδοι `playSound()` και `stop()` ανήκουν στην κλάση `Greenfoot` και χρησιμοποιούνται για ήχους και έλεγχο της προσομοίωσης.
