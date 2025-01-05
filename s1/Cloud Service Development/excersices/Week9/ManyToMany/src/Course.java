package com.example.demo.hello;

import java.util.*;
import javax.persistence.*;

@Entity // Δηλώνει ότι η κλάση αποτελεί οντότητα που αντιπροσωπεύει πίνακα στη βάση δεδομένων
public class Course {

	@Id // Δηλώνει ότι το πεδίο name είναι το μοναδικό αναγνωριστικό της οντότητας
	private String name;
	private int semester;

	// Ορισμός της σχέσης πολλών-προς-πολλούς με την κλάση Student
	@ManyToMany(mappedBy = "courses") // Αναφέρεται στη μεταβλητή courses της κλάσης Student
	private Set<Student> students = new HashSet<Student>();

	// Προεπιλεγμένος constructor (απαιτείται από το JPA)
	public Course() {}

	/**
	 * Constructor για τη δημιουργία ενός νέου μαθήματος.
	 * 
	 * @param n Το όνομα του μαθήματος.
	 * @param s Το εξάμηνο στο οποίο ανήκει το μάθημα.
	 */
	public Course(String n, int s) {
		name = n;
		semester = s;
	}

	/**
	 * Επιστρέφει το όνομα του μαθήματος.
	 * 
	 * @return Το όνομα του μαθήματος.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Επιστρέφει το εξάμηνο του μαθήματος.
	 * 
	 * @return Το εξάμηνο του μαθήματος.
	 */
	public int getSemester() {
		return semester;
	}
}