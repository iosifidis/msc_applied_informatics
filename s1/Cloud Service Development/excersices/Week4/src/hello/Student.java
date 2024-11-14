package com.example.demo.hello;

public class Student {
	
	// Constructor για την αρχικοποίηση των πεδίων
	private String name;
	private int age;
	private String location;
	
	public Student(String n, int a, String l) {
		name = n;
		age = a;
		location =l;
	}
	
	
	// Το framework ότι ξεκινάει από get αντιλαμβάνεται ως πεδίο της κλάσης και είναι να επιστρέψει κάτι.	
	// ΠΡΕΠΕΙ το endpoint στον controller να επιστρέφει 1 αντικείμενο (γραμμή 26 να είναι return s;)
	/*
	   // Επιστροφή ως αλφαριθμητικό όταν ήταν το getData()
 	   public String getData() {
		  return name + "[" + age + "], from: " + location;
	   }
	 */
	
	// Getters για να επιτρέψουν την ανάκτηση των τιμών των πεδίων από το framework (Spring)
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getLocation() {
		return location;
	}
}
