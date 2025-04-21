# Ολοκληρωμένες Μεθόδοι από το Newton's Lab Project

## **Κλάση Space (World)**

### **1. Δημιουργία Ηλιακού Συστήματος**
```java
public void sunAndPlanet() {
    removeAllObjects();
    addObject(new Body(50, 240.0, new Vector(270, 0.03), new Color(255, 216, 0)), 460, 270);
    addObject(new Body(20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 695, 260);
}
```
**Επεξήγηση:** Δημιουργεί έναν Ήλιο και έναν πλανήτη με συγκεκριμένες παραμέτρους (μέγεθος, μάζα, διάνυσμα κίνησης(κατεύθυνση σε μοίρες, μήκος (ταχύτητα), χρώμα(r,g,b), x, y).

---

```java
public void sunAndTwoPlanets() {
    removeAllObjects();
    addObject(new Body(50, 240.0, new Vector(270, 0.0), new Color(255, 216, 0)), 460, 310);
    addObject(new Body(20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 695, 300);
    addObject(new Body(24, 4.6, new Vector(270, 1.8), new Color(248, 160, 86)), 180, 290);
}
```
**Επεξήγηση:** Δημιουργεί έναν Ήλιο και δύο πλανήτες με διαφορετικές τροχιές και χρώματα.

---

```java
public void sunPlanetMoon() {
    removeAllObjects();
    addObject(new Body(50, 240.0, new Vector(270, 0.0), new Color(255, 216, 0)), 460, 270);
    addObject(new Body(20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 720, 260);
    addObject(new Body(5, 0.8, new Vector(90, 3.25), new Color(240, 220, 96)), 748, 260);
}
```
**Επεξήγηση:** Δημιουργεί έναν Ήλιο, έναν πλανήτη και ένα φεγγάρι με ιεραρχική κίνηση.

---

### **2. Καθαρισμός του Κόσμου**
```java
private void removeAllObjects() {
    removeObjects(getObjects(Actor.class));
}
```
**Επεξήγηση:** Αφαιρεί όλα τα αντικείμενα από τον κόσμο (World) για προετοιμασία νέας προσομοίωσης.

---

## **Κλάση Body (SmoothMover)**

### **1. Αρχικοποίηση Σώματος**
```java
public Body(int size, double mass, Vector movement, Color color) {
    this.mass = mass;
    addForce(movement);
    GreenfootImage image = new GreenfootImage(size, size);
    image.setColor(color);
    image.fillOval(0, 0, size-1, size-1);
    setImage(image);
}
```
**Επεξήγηση:** Δημιουργεί ένα σώμα με συγκεκριμένο μέγεθος, μάζα, διάνυσμα κίνησης και χρώμα. Το σώμα εμφανίζεται ως γεμισμένος κύκλος.

---

### **2. Βασική Συμπεριφορά (act)**
```java
public void act() {
    applyForces();
    move();
}
```
**Επεξήγηση:** Εφαρμόζει τις δυνάμεις βαρύτητας από άλλα σώματα και μετακινεί το τρέχον σώμα.

---

### **3. Εφαρμογή Δυνάμεων Βαρύτητας**
```java
private void applyForces() {
    List<Body> bodies = getWorld().getObjects(Body.class);
    for (Body body : bodies) {
        if (body != this) {
            applyGravity(body);
        }
    }
}
```
**Επεξήγηση:** Υπολογίζει και εφαρμόζει τις δυνάμεις βαρύτητας από όλα τα άλλα σώματα στον κόσμο.

---

### **4. Υπολογισμός Βαρύτητας (Νόμος του Νεύτωνα)**
```java
private void applyGravity(Body other) {
    double dx = other.getExactX() - this.getExactX();
    double dy = other.getExactY() - this.getExactY();
    Vector force = new Vector(dx, dy);
    double distance = Math.sqrt(dx*dx + dy*dy);
    double strength = GRAVITY * this.mass * other.mass / (distance * distance);
    double acceleration = strength / this.mass;
    force.setLength(acceleration);
    addForce(force);
}
```
**Επεξήγηση:** Υπολογίζει τη δύναμη βαρύτητας μεταξύ δύο σωμάτων χρησιμοποιώντας τον νόμο της παγκόσμιας έλξης και ενημερώνει το διάνυσμα κίνησης.

---

## **Κλάση SmoothMover**

## **1. Κατασκευαστές**
```java
public SmoothMover() {
    this(new Vector());
}
```
**Επεξήγηση:** Δημιουργεί έναν SmoothMover με ουδέτερο διάνυσμα κίνησης (μηδενικό μήκος).

```java
public SmoothMover(Vector movement) {
    this.movement = movement;
}
```
**Επεξήγηση:** Δημιουργεί έναν SmoothMover με συγκεκριμένο διάνυσμα κίνησης.

---

## **2. Μέθοδοι Κίνησης**
```java
public void move() {
    exactX = exactX + movement.getX();
    exactY = exactY + movement.getY();
    super.setLocation((int) exactX, (int) exactY);
}
```
**Επεξήγηση:** Μετακινεί τον χαρακτήρα βάσει του διανύσματος κίνησης, χρησιμοποιώντας ακριβείς (double) συντεταγμένες.

```java
public void setLocation(double x, double y) {
    exactX = x;
    exactY = y;
    super.setLocation((int) x, (int) y);
}
```
**Επεξήγηση:** Ορίζει τη θέση χρησιμοποιώντας ακριβείς συντεταγμένες (double).

```java
public void setLocation(int x, int y) {
    exactX = x;
    exactY = y;
    super.setLocation(x, y);
}
```
**Επεξήγηση:** Ορίζει τη θέση με ακέραιες συντεταγμένες, συγχρονίζοντας και τις ακριβείς τιμές.

---

## **3. Μέθοδοι Πρόσβασης**
```java
public double getExactX() {
    return exactX;
}
```
**Επεξήγηση:** Επιστρέφει την ακριβή x συντεταγμένη (double).

```java
public double getExactY() {
    return exactY;
}
```
**Επεξήγηση:** Επιστρέφει την ακριβή y συντεταγμένη (double).

```java
public Vector getMovement() {
    return movement;
}
```
**Επεξήγηση:** Επιστρέφει το τρέχον διάνυσμα κίνησης.

```java
public double getSpeed() {
    return movement.getLength();
}
```
**Επεξήγηση:** Επιστρέφει το μέτρο της ταχύτητας (μήκος διανύσματος).

---

## **4. Μέθοδοι Τροποποίησης Κίνησης**
```java
public void addForce(Vector force) {
    movement.add(force);
}
```
**Επεξήγηση:** Προσθέτει μια δύναμη (διάνυσμα) στο τρέχον διάνυσμα κίνησης.

```java
public void accelerate(double factor) {
    movement.scale(factor);
    if (movement.getLength() < 0.15) {
        movement.setNeutral();
    }
}
```
**Επεξήγηση:** Επιταχύνει ή επιβραδύνει την κίνηση κατά ένα παράγοντα. Αν η ταχύτητα γίνει πολύ μικρή, μηδενίζεται.

---

### **Σημειώσεις**
- Η κλάση `SmoothMover` παρέχει ομαλή κίνηση χρησιμοποιώντας ακριβείς (double) συντεταγμένες, σε αντίθεση με την τυπική ακέραια κίνηση του Greenfoot.   
- Το διάνυσμα κίνησης (`Vector movement`) καθορίζει την κατεύθυνση και την ταχύτητα της κίνησης.   
- Οι μέθοδοι `setLocation()` διασφαλίζουν συγχρονισμό μεταξύ ακέραιων και ακριβών συντεταγμένων.   
- Η μέθοδος `accelerate()` επιτρέπει εύκολη εφαρμογή επιτάχυνσης/επιβράδυνσης.   
- Οι μέθοδοι `getExactX()` και `getExactY()` χρησιμοποιούνται για ακριβείς υπολογισμούς θέσης.   
- Η σταθερά `GRAVITY` στην κλάση `Body` καθορίζει την ένταση της βαρυτικής έλξης.   

---

## **Κλάση Vector**

# Κλάση Vector - Ολοκληρωμένες Μεθόδοι

## **1. Κατασκευαστές**
```java
public Vector() {
    // Default constructor creates neutral vector (0,0)
}
```
**Επεξήγηση:** Δημιουργεί ουδέτερο διάνυσμα (μήκος 0).

```java
public Vector(int direction, double length) {
    this.length = length;
    this.direction = direction;
    updateCartesian();
}
```
**Επεξήγηση:** Δημιουργεί διάνυσμα από πολικές συντεταγμένες (κατεύθυνση σε μοίρες και μήκος).

```java
public Vector(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
    updatePolar();
}
```
**Επεξήγηση:** Δημιουργεί διάνυσμα από καρτεσιανές συντεταγμένες (x,y).

---

## **2. Μέθοδοι Πρόσβασης**
```java
public double getX() {
    return dx;
}
```
**Επεξήγηση:** Επιστρέφει την x συνιστώσα του διανύσματος.

```java
public double getY() {
    return dy;
}
```
**Επεξήγηση:** Επιστρέφει την y συνιστώσα του διανύσματος.

```java
public int getDirection() {
    return direction;
}
```
**Επεξήγηση:** Επιστρέφει την κατεύθυνση του διανύσματος σε μοίρες (0° = EAST).

```java
public double getLength() {
    return length;
}
```
**Επεξήγηση:** Επιστρέφει το μήκος του διανύσματος.

---

## **3. Μέθοδοι Τροποποίησης**
```java
public void setDirection(int direction) {
    this.direction = direction;
    updateCartesian();
}
```
**Επεξήγηση:** Αλλάζει την κατεύθυνση διατηρώντας το μήκος.

```java
public void setLength(double length) {
    this.length = length;
    updateCartesian();
}
```
**Επεξήγηση:** Αλλάζει το μήκος διατηρώντας την κατεύθυνση.

```java
public void scale(double factor) {
    length = length * factor;
    updateCartesian();
}
```
**Επεξήγηση:** Κλιμακώνει το διάνυσμα κατά ένα παράγοντα (factor > 1 μεγέθυνση, factor < 1 σμίκρυνση).

```java
public void setNeutral() {
    dx = 0.0;
    dy = 0.0;
    length = 0.0;
    direction = 0;
}
```
**Επεξήγηση:** Μηδενίζει το διάνυσμα.

---

## **4. Πράξεις Διανυσμάτων**
```java
public void add(Vector other) {
    dx += other.dx;
    dy += other.dy;
    updatePolar();
}
```
**Επεξήγηση:** Προσθέτει ένα άλλο διάνυσμα στο τρέχον.

```java
public void revertHorizontal() {
    dx = -dx;
    updatePolar();
}
```
**Επεξήγηση:** Αντιστρέφει την οριζόντια συνιστώσα.

```java
public void revertVertical() {
    dy = -dy;
    updatePolar();
}
```
**Επεξήγηση:** Αντιστρέφει την κατακόρυφη συνιστώσα.

---

## **5. Βοηθητικές Μέθοδοι**
```java
private void updatePolar() {
    this.direction = (int) Math.toDegrees(Math.atan2(dy, dx));
    this.length = Math.sqrt(dx*dx+dy*dy);
}
```
**Επεξήγηση:** Ενημερώνει τις πολικές συντεταγμένες από τις καρτεσιανές.

```java
private void updateCartesian() {
    dx = length * Math.cos(Math.toRadians(direction));
    dy = length * Math.sin(Math.toRadians(direction));   
}
```
**Επεξήγηση:** Ενημερώνει τις καρτεσιανές συντεταγμένες από τις πολικές.

---

### **Σημειώσεις**
- Η κλάση `Vector` μπορεί να αναπαραστήσει και να χειριστεί διανύσματα και στις δύο μορφές (πολική και καρτεσιανή).
- Οι μέθοδοι `updatePolar()` και `updateCartesian()` διασφαλίζουν τη συνοχή των συντεταγμένων.
- Όλες οι πράξεις διατηρούν την ακεραιότητα του διανύσματος.
- Η κατεύθυνση μετράται σε μοίρες (0° = EAST, 90° = NORTH, 180° = WEST, 270° = SOUTH).

---

### **Γενικές Σημειώσεις**
- Οι κλάσεις `Body`, `SmoothMover` και `Vector` συνεργάζονται για να υλοποιήσουν μια προσομοίωση του Ηλιακού Συστήματος με βαρυτικές αλληλεπιδράσεις.


