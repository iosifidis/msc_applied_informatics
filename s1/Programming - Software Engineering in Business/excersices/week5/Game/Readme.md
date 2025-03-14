# Παράδειγμα Game με στρατιωτάκι

## Κλάση main

Ο κώδικας παρουσιάζει την υλοποίηση της κλάσης Main και τη χρήση της κλάσης Soldier. Ο κύριος σκοπός του κώδικα είναι να επιδείξει τη χρήση μιας **στατικής (static)** μεταβλητής και στατικών μεθόδων. Η στατική μέθοδος getCounter χρησιμοποιείται για να επιστρέψει τον συνολικό αριθμό των αντικειμένων Soldier που έχουν δημιουργηθεί.

### Σημεία προσοχής

- Στατική μέθοδος και μεταβλητή: Η getCounter είναι μια στατική μέθοδος, πράγμα που σημαίνει ότι ανήκει στην κλάση Soldier και όχι στα επιμέρους αντικείμενά της. Μπορεί να κληθεί είτε με το όνομα της κλάσης είτε με το όνομα του αντικειμένου, όμως η σωστή χρήση είναι μέσω της κλάσης (Soldier.getCounter()).   

- Δημιουργία αντικειμένων και στατική μεταβλητή: Ο μετρητής (counter) υποτίθεται ότι αυξάνεται με τη δημιουργία κάθε νέου αντικειμένου Soldier, οπότε θα περιμέναμε η στατική μεταβλητή να ενημερώνεται αυτόματα με κάθε δημιουργία αντικειμένου.

## Κλάση Soldier

Ο κώδικας παρουσιάζει την υλοποίηση της κλάσης Soldier, η οποία χρησιμοποιεί μια στατική (static) μεταβλητή counter για να παρακολουθεί τον αριθμό των αντικειμένων Soldier που έχουν δημιουργηθεί. Κάθε φορά που δημιουργείται ένα νέο αντικείμενο Soldier, το counter αυξάνεται.

### Σημεία προσοχής

- Στατική μεταβλητή counter: Η μεταβλητή counter είναι κοινή για όλα τα αντικείμενα της κλάσης Soldier και αυξάνεται κάθε φορά που δημιουργείται ένα νέο αντικείμενο. Είναι στατική, επομένως ανήκει στην κλάση και όχι στα μεμονωμένα αντικείμενα.   

- Κατασκευαστής Soldier: Ο κατασκευαστής αρχικοποιεί την ιδιότητα id με την τιμή που παρέχεται κατά τη δημιουργία του αντικειμένου και αυξάνει τον counter κατά 1, ενημερώνοντας έτσι τον συνολικό αριθμό των αντικειμένων Soldier.   

- Στατική μέθοδος getCounter: Η getCounter είναι στατική και μπορεί να κληθεί χωρίς να δημιουργηθεί αντικείμενο Soldier, παρέχοντας πρόσβαση στον αριθμό των αντικειμένων που έχουν δημιουργηθεί.   
