public class Main {

    public static void main(String[] args) {
        
        // Δημιουργία ενός ρολογιού χωρίς αρχικές τιμές (ξεκινά από "00:00")
        ClockDisplay clock1 = new ClockDisplay();
        System.out.println("Clock1 - Αρχική ώρα: " + clock1.getTime());

        // Προσθέτουμε 3 λεπτά και εμφανίζουμε την ώρα μετά από κάθε αλλαγή
        System.out.println("\nΠροσθέτουμε 3 λεπτά στο Clock1:");
        for (int i = 0; i < 3; i++) { 
            clock1.timeTick();
            System.out.println("Clock1 - Τρέχουσα ώρα: " + clock1.getTime());
        }

        // Δημιουργία ενός δεύτερου ρολογιού με προκαθορισμένη ώρα (π.χ. "12:45")
        ClockDisplay clock2 = new ClockDisplay(12, 45);
        System.out.println("\nClock2 - Αρχική ώρα: " + clock2.getTime());

        // Προσθέτουμε 20 λεπτά στο Clock2 και εμφανίζουμε την ώρα μετά από κάθε αλλαγή
        System.out.println("\nΠροσθέτουμε 20 λεπτά στο Clock2:");
        for (int i = 0; i < 20; i++) { 
            clock2.timeTick();
            System.out.println("Clock2 - Τρέχουσα ώρα: " + clock2.getTime());
        }

        // Δημιουργία ενός τρίτου ρολογιού που ξεκινά κοντά στο τέλος της ημέρας (π.χ. "23:58")
        ClockDisplay clock3 = new ClockDisplay(23, 58);
        System.out.println("\nClock3 - Αρχική ώρα: " + clock3.getTime());

        // Προσθέτουμε 5 λεπτά για να δούμε την αλλαγή ημέρας
        System.out.println("\nΠροσθέτουμε 5 λεπτά στο Clock3:");
        for (int i = 0; i < 5; i++) {
            clock3.timeTick();
            System.out.println("Clock3 - Τρέχουσα ώρα: " + clock3.getTime());
        }
    }
}
