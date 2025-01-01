import java.util.ArrayList;
import java.util.Collection;

public class Employee {
	String posType;
	String pType;
	String name;
	int hours;
	ArrayList<Project> pList;
	
	public Employee(String position, String payment, 
			String n, int h) {
		posType= position;
		pType = payment;
		name = n;
		hours = h;
		pList = new ArrayList<Project>();
	}

	public boolean hasName(String empName) {
		return name.equals(empName);
	}

	public void addProject(Project pr) {
		pList.add(pr);
	}

	public void printPayroll() {
		System.out.println("\t" + name + " gets: " + 
				getPayroll() + " euros");
	}

	public ArrayList<Project> getAllProjects() {
		return pList;
	}

	public double getPayroll() {
		int cost = 0;
		
		if (pType.equals("perHour")) {
			if (posType.equals("Developer")){
				cost = 20 * hours;
			} else if (posType.equals("Analyst")) {
				cost = 35 * hours;
			} else if (posType.equals("Manager")) {
				cost = 40 * hours;
			}
		} else {
			if (posType.equals("Developer")){
				cost = 1800 + (2 * pList.size());
			} else if (posType.equals("Analyst")) {
				cost = 2500 + (5 * pList.size());
			} else if (posType.equals("Manager")) {
				cost = 4000 + (10 * pList.size());
			}
		}
		return cost;
	}

	public boolean worksIn(Project p) {		
		return pList.contains(p);
	}

}
