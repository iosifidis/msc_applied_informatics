package com.example.demo.hello;


import javax.persistence.*;

@Entity // Ενδεικνύει ότι αυτή η κλάση είναι οντότητα στη βάση δεδομένων
public class Student {
	
	@Id // Σημαίνει ότι αυτό το πεδίο είναι το πρωτεύον κλειδί της οντότητας
	private String name; // Το όνομα του φοιτητή
	
	private int age; // Η ηλικία του φοιτητή
	
	private String location; // Η τοποθεσία της κατοικίας του φοιτητή
	
	// Κατασκευαστής - Απαιτείται από το JPA
	public Student() {
		name = "";
		age = 0;
		location = "";
	}
	
	// Κατασκευαστής με παραμέτρους για τη δημιουργία ενός αντικειμένου Student με συγκεκριμένα χαρακτηριστικά
	public Student(String n, int a, String l) {
		name = n;
		age = a;
		location = l;
	}
	
	// Μέθοδος getter για το όνομα
	public String getName() {
		return name;
	}
	
	// Μέθοδος getter για την ηλικία
	public int getAge() {
		return age;
	}
	
	// Μέθοδος getter για την τοποθεσία
	public String getLocation() {
		return location;
	}
	
	// Remove: Otherwise it would be part of the json
/*	public String getData() {
		return name + "[" + age + "], from: " + location;
	}
*/
}
