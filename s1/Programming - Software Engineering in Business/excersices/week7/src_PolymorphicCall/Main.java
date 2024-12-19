import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
			
		System.out.println("Please enter type of student: 1:Stud, 2:Grad");
		int type = in.nextInt();
		in.nextLine();  //flushes the input
		
		Student s;
		
		if(type == 1) {
			//Ζήτα στοιχεία προπτυχιακού
			System.out.println("Enter id: ");
			String id = in.nextLine();
			System.out.println("Enter name: ");
			String name = in.nextLine();
			
			//δημιούργησε προπτυχιακό φοιτητή
			s = new Student(id, name);

		}
		else {
			//Ζήτα στοιχεία μεταπτυχιακού
			System.out.println("Enter id: ");
			String id = in.nextLine();
			System.out.println("Enter name: ");
			String name = in.nextLine();
			System.out.println("Enter supervisor: ");
			String supervisor = in.nextLine();
			
			//δημιούργησε μεταπτυχιακό φοιτητή
			s = new GraduateStudent(id, name, supervisor);
		}
		
		s.printInfo();   //καθυστερημένη διασύνδεση
		                 //πολυμορφική κλήση
			
	}

}
