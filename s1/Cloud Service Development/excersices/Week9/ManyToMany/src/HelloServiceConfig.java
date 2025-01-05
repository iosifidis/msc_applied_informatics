package com.example.demo.hello;

import org.springframework.boot.*;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Configuration
public class HelloServiceConfig implements CommandLineRunner {
	
	// Αυτόματη σύνδεση της υπηρεσίας HelloService για χρήση μέσα στην κλάση
	@Autowired
	private HelloService hs;

	/**
	 * Εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής.
	 * Δημιουργεί δείγματα δεδομένων (φοιτητές και μαθήματα) και τα αποθηκεύει.
	 * 
	 * @param args Παράμετροι γραμμής εντολών (δεν χρησιμοποιούνται εδώ).
	 * @throws Exception Εξαίρεση αν παρουσιαστεί πρόβλημα κατά την εκτέλεση.
	 */
	@Override
	public void run(String... args) throws Exception {
		// Δημιουργία φοιτητή Apostolis με όνομα, ηλικία και πόλη
		Student s1 = new Student("Apostolis", 43, "Thessaloniki");
		// Προσθήκη μαθημάτων στον Apostolis
		s1.addCourse(new Course("SQA", 5)); // Software Quality Assurance, 5 μονάδες
		s1.addCourse(new Course("Mobile Development", 6)); // Mobile Development, 6 μονάδες
		// Αποθήκευση του Apostolis στη βάση δεδομένων μέσω του HelloService
		hs.addStudent(s1);

		// Δημιουργία φοιτητή Alex με μαθήματα
		Student s2 = new Student("Alex", 48, "Thessaloniki");
		s2.addCourse(new Course("OOP", 3)); // Object-Oriented Programming, 3 μονάδες
		s2.addCourse(new Course("SE", 4)); // Software Engineering, 4 μονάδες
		hs.addStudent(s2);

		// Δημιουργία φοιτητή Daniel από το Groningen
		Student s3 = new Student("Daniel", 35, "Groningen");
		s3.addCourse(new Course("Patterns", 7)); // Design Patterns, 7 μονάδες
		hs.addStudent(s3);
	}
}