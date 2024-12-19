public class TestClass
{
    public static void main(String[] args)
    {
        Database db = new Database();
        CD aCd = new CD("A Swingin' Affair", "Frank Sinatra", 16, 64); // Αρχή υποκατάστασης και αλλάζω από CD σε Item
        DVD aDVD = new DVD("O Brother, Where Art Thou", "Joel & Ethan Coen", 106); // Αρχή υποκατάστασης και αλλάζω από DVD σε Item
        
        // Item πολυμορφική μεταβλητή
        // Στατικός τύπος Item αλλά δυναμικός τύπος ο CD , DVD. Μπορεί να διαφέρει ο τύπος κάθε φορά που εκτελείται.
        
        aCd.setOwn(true);
        aCd.setComment("my favorite album");
        aDVD.setComment("The Coen brothers' best movie!");

        //aCd.print(); // Δυναμικός τύπος. Επιλέγει ποιο print θα εκτελέσει κάθς φορά.
        //aDVD.print(); // Δυναμικός τύπος. Επιλέγει ποιο print θα εκτελέσει κάθς φορά.

        // Τις τροποποιώ σε Item
//        db.addCD(aCd);
//        db.addDVD(aDVD);

      db.addItem(aCd);
      db.addItem(aDVD);
        
        db.list();
    }
}