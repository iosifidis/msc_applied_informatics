# Factory Pattern: Παράδειγμα

## **Design Pattern: Factory Pattern**

Το Factory Pattern είναι ένα από τα βασικά design patterns του λογισμικού. Βοηθά στη δημιουργία αντικειμένων χωρίς να αποκαλύπτεται η λογική δημιουργίας τους στον πελάτη (client code), προάγοντας την αρχή της ενθυλάκωσης (encapsulation).

---

### **Σκοπός**

- **Απομόνωση** της λογικής δημιουργίας αντικειμένων.
- **Ευκολία συντήρησης** του κώδικα. Όταν αλλάζουν τα αντικείμενα που δημιουργούνται, οι αλλαγές περιορίζονται στο εργοστάσιο (factory).
- **Υποστήριξη επέκτασης** για νέες οικογένειες αντικειμένων χωρίς τροποποίηση του υπάρχοντος κώδικα.

---

### **Παράδειγμα: Εφαρμογή σε Έπιπλα**

#### Περιγραφή Προβλήματος

- Δημιουργείτε μια προσομοίωση καταστήματος επίπλων.
- Τα προϊόντα χωρίζονται σε οικογένειες (π.χ. Καρέκλα, Καναπές, Τραπέζι Καφέ).
- Κάθε οικογένεια διαθέτει διάφορες παραλλαγές (π.χ. Μοντέρνο, Βικτωριανό, Art Deco).
- Πρέπει να διασφαλιστεί ότι όλα τα αντικείμενα μιας οικογένειας ταιριάζουν μεταξύ τους.

---

#### **Λύση με Factory Pattern**

1. **Ορισμός ενός κοινού interface για κάθε οικογένεια προϊόντων**:
   ```java
   interface Chair {
       void sitOn();
   }

   interface Sofa {
       void lieOn();
   }

   interface CoffeeTable {
       void placeItems();
   }
   ```

2. **Υλοποίηση παραλλαγών για κάθε προϊόν**:
   ```java
   class ModernChair implements Chair {
       @Override
       public void sitOn() {
           System.out.println("Sitting on a modern chair.");
       }
   }

   class VictorianSofa implements Sofa {
       @Override
       public void lieOn() {
           System.out.println("Lying on a Victorian sofa.");
       }
   }
   ```

3. **Ορισμός ενός εργοστασίου για κάθε οικογένεια**:
   ```java
   interface FurnitureFactory {
       Chair createChair();
       Sofa createSofa();
       CoffeeTable createCoffeeTable();
   }

   class ModernFurnitureFactory implements FurnitureFactory {
       @Override
       public Chair createChair() {
           return new ModernChair();
       }

       @Override
       public Sofa createSofa() {
           return new ModernSofa();
       }

       @Override
       public CoffeeTable createCoffeeTable() {
           return new ModernCoffeeTable();
       }
   }
   ```

4. **Χρήση του εργοστασίου στο πρόγραμμα**:
   ```java
   public class FurnitureShop {
       private FurnitureFactory factory;

       public FurnitureShop(FurnitureFactory factory) {
           this.factory = factory;
       }

       public void showcase() {
           Chair chair = factory.createChair();
           Sofa sofa = factory.createSofa();
           CoffeeTable table = factory.createCoffeeTable();

           chair.sitOn();
           sofa.lieOn();
           table.placeItems();
       }
   }

   public static void main(String[] args) {
       FurnitureFactory factory = new ModernFurnitureFactory();
       FurnitureShop shop = new FurnitureShop(factory);
       shop.showcase();
   }
   ```

---

Με αυτόν τον τρόπο, μπορούμε να προσθέσουμε νέες οικογένειες προϊόντων (π.χ. "Industrial") χωρίς να τροποποιήσουμε τον ήδη υπάρχοντα κώδικα. Αυτή η δομή διασφαλίζει την επεκτασιμότητα και την ευκολία συντήρησης.

---

## Βίντεο

- [The Factory Method Pattern Explained and Implemented in Java | Creational Design Patterns | Geekific](https://youtu.be/EdFq_JIThqM?si=f3fOeBvN2tcfNflZ)   
- [The Abstract Factory Pattern Explained and Implemented | Creational Design Patterns | Geekific](https://youtu.be/QNpwWkdFvgQ?si=0nbi1CjA7xrowxcc)   
- [Factory Method Pattern – Design Patterns (ep 4)](https://youtu.be/EcFVTgRHJLM?si=-QIOpGNd6LcQnteJ)   
- [Abstract Factory Pattern – Design Patterns (ep 5)](https://youtu.be/v-GiuMmsXj4?si=nMyGB1KyzqOqpPBk)   
- [How to use Factory Method Design Pattern to design a course website like Udacity, Edx, Coursera...](https://youtu.be/s3Wr5_tsODs?si=1Y8z0l7sdksin7lj)   
- [Factory Design Pattern in detail | Interview Question](https://youtu.be/D5d1f9Lcmv4?si=vjFm15Rt9YX7e4gq)  

---

## Δείτε περισσότερα:

1. [Furniture Pattern](Pattern)   
2. [Παράδειγμα κώδικα](Examples)
