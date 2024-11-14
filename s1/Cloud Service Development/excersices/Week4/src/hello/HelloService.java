package com.example.demo.hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;


// Θα κρατάει όλα μας τα δεδομένα
// Για να μπορέσουμε να την χρησιμοποιήσουμε πρέπει να αλλάξουμε στον Controller
// Πρέπει να φτιάξουμε ένα @Autowired και αλλαγή στην getStudent στο HelloController

//Η κλάση Service που διαχειρίζεται την επιχειρηματική λογική
@Service
public class HelloService {
	
	// Λίστα για την αποθήκευση των φοιτητών
	ArrayList<Student> sList = new ArrayList<Student>();
	
	// Μέθοδος για την επιστροφή ενός φοιτητή ως αντικείμενο
	public Student getStudent(String n, int a, String l) {
		return new Student(n, a, l);
	}

	// Μέθοδος προσθήκης φοιτητή στη λίστα
	public void addStudent(String n, int a, String l) {
		sList.add(new Student(n, a, l));
	}

	// Μέθοδος για την ανάκτηση όλων των φοιτητών
	public ArrayList<Student> getAllStudents(){
		return sList;
	}
}
