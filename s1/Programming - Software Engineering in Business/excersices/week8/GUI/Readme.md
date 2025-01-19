# Οδηγός: Δημιουργία Απλού GUI με Java Swing

## Βήματα Δημιουργίας του GUI

### 1. Δημιουργία Υποδοχέα
Ο υποδοχέας είναι η βάση που περιέχει όλα τα συστατικά του GUI. Στο παράδειγμα αυτό χρησιμοποιούμε ένα `JPanel`.

```java
private JPanel panel = new JPanel();
```

### 2. Δημιουργία Γραφικών Συστατικών
Ορίζουμε τα γραφικά συστατικά που θέλουμε να εμφανίζονται, όπως πεδία κειμένου και κουμπιά.

```java
private JTextField textField = new JTextField("Εισάγετε το όνομά σας");
private JButton button = new JButton("Πατήστε Με");
```

### 3. Προσθήκη Συστατικών στον Υποδοχέα
Προσθέτουμε τα συστατικά στον υποδοχέα χρησιμοποιώντας τη μέθοδο `add()`.

```java
panel.add(textField);
panel.add(button);
```

### 4. Σύνδεση Υποδοχέα με το Παράθυρο
Χρησιμοποιούμε τη μέθοδο `setContentPane()` για να συνδέσουμε τον υποδοχέα με το κύριο παράθυρο (`JFrame`).

```java
this.setContentPane(panel);
```

### 5. Διαχείριση Συμβάντων
Το Swing χρησιμοποιεί το πρότυπο παρατηρητή (observer pattern) για τη διαχείριση συμβάντων. Τα βήματα για τη διαχείριση κλικ στο κουμπί είναι τα εξής:

#### Βήμα 1: Δημιουργία Κλάσης Ακροατή
Ορίζουμε μία κλάση που υλοποιεί τη διεπαφή `ActionListener`.

```java
class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Γεια σου φίλε μου " + textField.getText());
    }
}
```

#### Βήμα 2: Δημιουργία Αντικειμένου του Ακροατή
Δημιουργούμε ένα αντικείμενο της κλάσης ακροατή.

```java
ButtonListener listener = new ButtonListener();
```

#### Βήμα 3: Εγγραφή Ακροατή στην Πηγή του Συμβάντος
Συνδέουμε τον ακροατή με το κουμπί χρησιμοποιώντας τη μέθοδο `addActionListener()`.

```java
button.addActionListener(listener);
```

## Εκκίνηση της Εφαρμογής
Για να εμφανιστεί το παράθυρο και να επιτρέπεται η αλληλεπίδραση του χρήστη, διαμορφώνουμε και ξεκινάμε το `JFrame`:

```java
this.setVisible(true);
this.setSize(400, 400);
this.setTitle("Το πρώτο μου παράθυρο");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
```

## Ολοκληρωμένος Κώδικας

```java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    // Βήμα 1: Δημιουργία υποδοχέα
    private JPanel panel = new JPanel();

    // Βήμα 2: Δημιουργία γραφικών συστατικών
    private JTextField textField = new JTextField("Εισάγετε το όνομά σας");
    private JButton button = new JButton("Πατήστε Με");

    public MyFrame() {
        // Βήμα 3: Προσθήκη συστατικών στον υποδοχέα
        panel.add(textField);
        panel.add(button);

        // Βήμα 4: Σύνδεση υποδοχέα με το παράθυρο
        this.setContentPane(panel);

        // Βήμα 5: Διαχείριση συμβάντων
        ButtonListener listener = new ButtonListener();
        button.addActionListener(listener);

        // Διαμόρφωση παραθύρου
        this.setVisible(true);
        this.setSize(400, 400);
        this.setTitle("Το πρώτο μου παράθυρο");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Κλάση ακροατή για διαχείριση συμβάντων κλικ στο κουμπί
    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Γεια σου φίλε μου " + textField.getText());
        }
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
```

## Περίληψη
Το παράδειγμα αυτό δείχνει τη δημιουργία ενός απλού GUI χρησιμοποιώντας το Java Swing. Τα βήματα περιλαμβάνουν τη δημιουργία συστατικών, την προσθήκη τους στον υποδοχέα, τη σύνδεση του υποδοχέα με το παράθυρο και τη διαχείριση συμβάντων. Ακολουθήστε αυτά τα βήματα για να δημιουργήσετε τις δικές σας γραφικές διεπαφές!

