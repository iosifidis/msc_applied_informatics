
public class Student {
	
	protected String id;
	protected String name;
	
	public Student(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public void printInfo() {
		System.out.println("Name: " + name);
		System.out.println("Id: " + id);
	}
	
	public String getName() {
		return name;
	}
}
