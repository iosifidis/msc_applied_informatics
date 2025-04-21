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

### **1. Κίνηση με Διάνυσμα**
```java
public void move() {
    exactX = exactX + movement.getX();
    exactY = exactY + movement.getY();
    super.setLocation((int) exactX, (int) exactY);
}
```
**Επεξήγηση:** Μετακινεί τον χαρακτήρα με βάση το διάνυσμα κίνησης, χρησιμοποιώντας ακριβείς (double) συντεταγμένες για ομαλή κίνηση.

---

### **2. Προσθήκη Δύναμης**
```java
public void addForce(Vector force) {
    movement.add(force);
}
```
**Επεξήγηση:** Προσθέτει μια νέα δύναμη στο διάνυσμα κίνησης του χαρακτήρα.

---

## **Κλάση Vector**

### **1. Δημιουργία Διανύσματος**
```java
public Vector(int direction, double length) {
    this.direction = direction;
    this.length = length;
    updateCartesian();
}
```
**Επεξήγηση:** Δημιουργεί ένα διάνυσμα με κατεύθυνση (μοίρες) και μήκος, και ενημερώνει τις καρτεσιανές συντεταγμένες.

---

### **2. Αλλαγή Μήκους**
```java
public void setLength(double length) {
    this.length = length;
    updateCartesian();
}
```
**Επεξήγηση:** Ορίζει το μήκος του διανύσματος και ενημερώνει τις καρτεσιανές συντεταγμένες.

---

### **3. Πρόσθεση Διανυσμάτων**
```java
public void add(Vector other) {
    dx += other.dx;
    dy += other.dy;
    updatePolar();
}
```
**Επεξήγηση:** Προσθέτει ένα άλλο διάνυσμα στο τρέχον και ενημερώνει τις πολικές συντεταγμένες.

---

### **Σημειώσεις**
- Οι κλάσεις `Body`, `SmoothMover` και `Vector` συνεργάζονται για να υλοποιήσουν μια προσομοίωση του Ηλιακού Συστήματος με βαρυτικές αλληλεπιδράσεις.
- Η σταθερά `GRAVITY` στην κλάση `Body` καθορίζει την ένταση της βαρυτικής έλξης.
- Οι μέθοδοι `getExactX()` και `getExactY()` χρησιμοποιούνται για ακριβείς υπολογισμούς θέσης.
