import java.util.ArrayList;

public class School {

	private String name;
	private String principal;
	
	private ArrayList<Teacher> teachers = new ArrayList<>();
	
	public School(String text1, String text2) {
		this.name = text1;
		this.principal = text2;
	}
	
	public String getName() {
		return this.name;
	}

    public String getPrincipalName() {
        return this.principal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrincipalName(String principal_name) {
        this.principal = principal_name;
    }

    public String toString() {
        return "School: " + this.name + "\nPrincipal: " + this.principal;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }
	
}
