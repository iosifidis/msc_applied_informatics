
public class GraduateStudent extends Student {
	
	private String supervisor;

	public GraduateStudent(String id, String name, String supervisor) {
		super(id, name);  //κλήση του κατασκευαστή της υπερκλάσης
		this.supervisor = supervisor;
	}
	
	//επικάλυψη μεθόδου - override
	public void printInfo() {
		super.printInfo();
		System.out.println("Supervisor: " + supervisor);
	}
	
}
