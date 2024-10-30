package gr.uom.init;

public class Person {

	private String name;
	private int age;
	private String location;
	
	// Κατασκευαστής
	public Person(String name, int age, String location) {
		this.name = name;
		this.age = age;
		this.location = location;
	}
	
	public String getData() {
		return "Hello " + name + " " + age + " from " + location;
	}
	
}
