import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Department dept = new Department("Applied Informatics");
		
		dept.enroll(new GraduateStudent("John", "iis2024", "Nikolaou"));
		dept.enroll(new GraduateStudent("Helen", "mai22012", "Ioannou"));
		dept.enroll(new UnderGraduateStudent("Bob", "ics2024", 3));
	
		dept.printDeptDetails();
		
		dept.printStudentTypes();

	}

}
