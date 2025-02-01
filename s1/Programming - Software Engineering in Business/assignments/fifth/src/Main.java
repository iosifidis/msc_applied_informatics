// Ευστάθιος Ιωσηφίδης. ΑΜ: mai25017

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		
		School school1 = new School("1st Highschool Polygyros", "Papadopoulos");
		School school2 = new School("17th Highschool Thessaloniki", "Nikolaou");
		School school3 = new School("3rd Highschool Patras", "Iosifidou");
		
		ArrayList<School> schools = new ArrayList<School>();
		schools.add(school1);
		schools.add(school2);
		schools.add(school3);
		
        Teacher teacher1 = new FullTimeTeacher("Papadopoulos", "100000001", 1600, 0.15, 2);
        Teacher teacher2 = new FullTimeTeacher("Antoniou", "100000002", 1800, 0.2, 3);
        Teacher teacher3 = new FullTimeTeacher("Grigriou", "100000003", 1500, 0.16, 1);
        Teacher teacher4 = new FullTimeTeacher("Sapountzi", "100000004", 1700, 0.15, 2);

        Teacher teacher5 = new AssociateTeacher("Papadimas", "100000005", 10,  170);
        Teacher teacher6 = new AssociateTeacher("Grigoriadou", "100000006", 11,  160);
        Teacher teacher7 = new AssociateTeacher("Mpoutzeti", "100000007", 12,  130);
        Teacher teacher8 = new AssociateTeacher("Agrapidis", "100000008", 13,  120);

        school1.addTeacher(teacher1);
        school1.addTeacher(teacher5);
        school1.addTeacher(teacher6);
        school2.addTeacher(teacher8);
        school2.addTeacher(teacher2);
        school2.addTeacher(teacher3);
        school3.addTeacher(teacher4);
        school3.addTeacher(teacher7);
		
		InputForm form = new InputForm(schools);

	}

}
