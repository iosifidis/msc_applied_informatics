import java.util.ArrayList;

public class Department {
	
	private String name;
	private ArrayList<Student> enrolledStudents = new ArrayList<>();;

	public Department(String name) {
		this.name = name;
	}
	
	public void enroll(Student aStudent) {
		enrolledStudents.add(aStudent);
		System.out.println("Student has been enrolled");
	}
	
	public void printDeptDetails() {
		System.out.println("Department: " + name);
		System.out.println("has the following students: ");
		for(Student student: enrolledStudents)
			student.printInfo();
	}
	
	public void printStudentTypes() {
		for(Student student: enrolledStudents)
			student.printType();
			
	}
	
	

}
