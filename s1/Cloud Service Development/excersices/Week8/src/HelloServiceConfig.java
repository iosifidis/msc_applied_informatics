package com.example.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration // Δηλώνει ότι αυτή η κλάση είναι μια πηγή ρυθμίσεων για την εφαρμογή.
public class HelloServiceConfig implements CommandLineRunner {

    @Autowired
    private HelloService hs; // Inject το service που διαχειρίζεται τους φοιτητές.

    @Override
    public void run(String... args) throws Exception {
        // Προσθέτει προκαθορισμένους φοιτητές κατά την εκκίνηση της εφαρμογής.
        // Αυτοί οι φοιτητές αποθηκεύονται στη βάση δεδομένων μέσω του HelloService.
        hs.addStudent(new Student("Apostolis", 43, "Thessaloniki")); // Προσθήκη του Apostolis.
        hs.addStudent(new Student("Alex", 48, "Thessaloniki")); // Προσθήκη του Alex.
        hs.addStudent(new Student("Daniel", 35, "Groningen")); // Προσθήκη του Daniel.
    }

}
