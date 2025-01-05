package com.example.demo.hello;

import java.util.*;
import javax.persistence.*;

@Entity // Δηλώνει ότι αυτή η κλάση είναι μια οντότητα που αντιπροσωπεύει έναν πίνακα στη βάση δεδομένων
public class Student {

	@Id // Δηλώνει ότι το πεδίο name είναι το μοναδικό αναγνωριστικό της οντότητας
	private String name;
	private int age;
	private String location;

	// Ορισμός της πολλαπλής σχέσης πολλών-προς-πολλούς μεταξύ φοιτητών και μαθημάτων
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name = "student_courses", // Όνομα του ενδιάμεσου πίνακα
		joinColumns = @JoinColumn(name = "student_name"), // Στήλη που συνδέεται με το Student
		inverseJoinColumns = @JoinColumn(name = "course_name") // Στήλη που συνδέεται με το Course
	)
	private Set<Course> courses = new HashSet<Course>();

	// Προεπιλεγμένος constructor (απαιτείται από το JPA)
	public Student() {}

	/**
	 * Constructor για τη δημιουργία ενός νέου φοιτητή.
	 * 
	 * @param n Όνομα φοιτητή.
	 * @param a Ηλικία φοιτητή.
	 * @param l Τοποθεσία φοιτητή.
	 */
	public Student(String n, int a, String l) {
		name = n;
		age = a;
		location = l;
	}

	/**
	 * Προσθέτει ένα μάθημα στη λίστα μαθημάτων του φοιτητή.
	 * 
	 * @param c Το αντικείμενο Course που θα προστεθεί.
	 */
	public void addCourse(Course c) {
		courses.add(c);
	}

	/**
	 * Επιστρέφει το όνομα του φοιτητή.
	 * 
	 * @return Το όνομα του φοιτητή.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Επιστρέφει την ηλικία του φοιτητή.
	 * 
	 * @return Η ηλικία του φοιτητή.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Επιστρέφει την τοποθεσία του φοιτητή.
	 * 
	 * @return Η τοποθεσία του φοιτητή.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Επιστρέφει το σύνολο των μαθημάτων του φοιτητή.
	 * 
	 * @return Το σύνολο μαθημάτων.
	 */
	public Set<Course> getCourses() {
		return courses;
	}
}
