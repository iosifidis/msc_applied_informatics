package exams;

public class Student implements Comparable<Student> {
	
	private String firstName;
	private String lastName;
	private double GPA;
	
	public Student(String firstName, String lastName, double gPA) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.GPA = gPA;
	}

	public double getGPA() {
		return GPA;
	}

	@Override
	public String toString() {
		return firstName + ", " + lastName + ", " + GPA;
	}

	@Override
	public int compareTo(Student otherStudent) {
		return Double.compare(this.GPA, otherStudent.GPA);
	}

}