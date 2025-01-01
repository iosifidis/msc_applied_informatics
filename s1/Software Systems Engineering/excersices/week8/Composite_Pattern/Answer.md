# Αναλυτική Απάντηση για την Εργασία

## 1. Επεξήγηση Συστήματος
Το σύστημα είναι ένα 3D racing παιχνίδι που αποτελείται από τα εξής στοιχεία:
- **Σκηνικό παιχνιδιού:** Περιλαμβάνει αντικείμενα όπως αυτοκίνητα, δρόμους και δέντρα.
- **Αυτοκίνητα:** Αποτελούνται από επιμέρους μέρη (σασί, τροχοί, παράθυρα) που πρέπει να κινούνται τόσο αυτόνομα όσο και συνδυαστικά.
- **Συναρτήσεις σχεδίασης και κίνησης:** Ήδη υλοποιημένες σε legacy εφαρμογές και πρέπει να ενσωματωθούν.

**Κύριος στόχος:** 
Το σύστημα πρέπει να διαχειρίζεται όλα τα αντικείμενα με ενιαίο και διαφανή τρόπο, ανεξάρτητα από τη φύση ή τη δομή τους.

---

## 2. Εντοπισμός Προβλήματος
Το πρόβλημα που πρέπει να λυθεί είναι:
- Η ανάγκη ενιαίας διαχείρισης των αντικειμένων στη σκηνή (αυτοκίνητα, δρόμοι, δέντρα) χωρίς να απαιτούνται εξειδικευμένες υλοποιήσεις για κάθε τύπο αντικειμένου.
- Ο συνδυασμός αντικειμένων διαφορετικής πολυπλοκότητας (π.χ. το αυτοκίνητο έχει μέρη, ενώ ο δρόμος είναι μονολιθικό αντικείμενο).
- Η χρήση των υπαρχουσών συναρτήσεων χωρίς να αλλάξουν ή να περιπλεχθούν.

---

## 3. Σχεδιαστικές Αποφάσεις
Το **Composite Pattern** είναι η κατάλληλη επιλογή για το πρόβλημα. 

**Λόγοι επιλογής:**
1. Το Composite Pattern επιτρέπει τη διαχείριση αντικειμένων και ομάδων αντικειμένων με την ίδια διεπαφή.
2. Ενισχύει την επεκτασιμότητα και τη συντηρησιμότητα του κώδικα, καθώς τα νέα αντικείμενα μπορούν να προστεθούν εύκολα.
3. Επιτρέπει την ομοιόμορφη χρήση συναρτήσεων (π.χ. σχεδίαση, κίνηση) ανεξάρτητα από τη φύση του αντικειμένου.

**Δομή που προτείνεται:**
- **Abstract Class ή Interface (`SceneComponent`):** Όλα τα αντικείμενα υλοποιούν την ίδια διεπαφή.
- **Concrete Implementations:**
  - `Car`: Αντικείμενο που περιλαμβάνει μέρη όπως `Body`, `Wheels`, και `Windows`.
  - `Road` και `Tree`: Αυτοτελή αντικείμενα.
- **Composite Class (`Scene`):** Διαχειρίζεται όλα τα αντικείμενα ως ενιαίο σύνολο.

---

## 4. Λύση Προβλήματος

### Ψευδοκώδικας για το Composite Pattern
```java
// Κοινή διεπαφή για όλα τα αντικείμενα
interface SceneComponent {
    void render(); // Σχεδίαση
    void animate(); // Κίνηση
}

// Μονολιθικά αντικείμενα (π.χ. δέντρο, δρόμος)
class Road implements SceneComponent {
    public void render() { System.out.println("Render Road"); }
    public void animate() { System.out.println("Animate Road"); }
}

class Tree implements SceneComponent {
    public void render() { System.out.println("Render Tree"); }
    public void animate() { System.out.println("Animate Tree"); }
}

// Σύνθετο αντικείμενο (π.χ. αυτοκίνητο)
class Car implements SceneComponent {
    private List<SceneComponent> parts = new ArrayList<>();
    
    public Car() {
        parts.add(new Body());
        parts.add(new Wheels());
        parts.add(new Windows());
    }
    
    public void render() {
        for (SceneComponent part : parts) {
            part.render();
        }
    }
    
    public void animate() {
        for (SceneComponent part : parts) {
            part.animate();
        }
    }
}

class Body implements SceneComponent {
    public void render() { System.out.println("Render Body"); }
    public void animate() { System.out.println("Animate Body"); }
}

class Wheels implements SceneComponent {
    public void render() { System.out.println("Render Wheels"); }
    public void animate() { System.out.println("Animate Wheels"); }
}

class Windows implements SceneComponent {
    public void render() { System.out.println("Render Windows"); }
    public void animate() { System.out.println("Animate Windows"); }
}

// Composite class για τη σκηνή
class Scene implements SceneComponent {
    private List<SceneComponent> components = new ArrayList<>();
    
    public void addComponent(SceneComponent component) {
        components.add(component);
    }
    
    public void render() {
        for (SceneComponent component : components) {
            component.render();
        }
    }
    
    public void animate() {
        for (SceneComponent component : components) {
            component.animate();
        }
    }
}
```

---

## 5. Συζήτηση Τελικής Λύσης

### Πλεονεκτήματα:
- **Ενιαία διαχείριση**: Όλα τα αντικείμενα χρησιμοποιούν την ίδια διεπαφή.   
- **Επεκτασιμότητα**: Νέα αντικείμενα μπορούν να προστεθούν χωρίς αλλαγές στον υπάρχοντα κώδικα.   
- **Ευκολία χρήσης**: Οι συναρτήσεις σχεδίασης και κίνησης εφαρμόζονται με συνέπεια.

### Μειονεκτήματα:
- Αυξημένη πολυπλοκότητα λόγω της ιεραρχικής δομής.   
- Ενδέχεται να χρειαστεί προσαρμογή των legacy συναρτήσεων για πλήρη ενσωμάτωση.

### Εναλλακτικές:
Η χρήση άλλων patterns, όπως το Decorator Pattern, μπορεί να είναι χρήσιμη αν χρειαστούν διαφορετικές συμπεριφορές ανά αντικείμενο. Ωστόσο, το Composite Pattern είναι πιο κατάλληλο για τη συγκεκριμένη εργασία λόγω της ανάγκης διαχείρισης ιεραρχιών.
