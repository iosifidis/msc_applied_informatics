import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Company {
	ArrayList<Employee> eList;
	
	public Company() {
		eList = new ArrayList<Employee>();
	}
	
	public void addEmployee(Employee emp) {
		eList.add(emp);		
	}

	public void addProjectToEmployee(String empName, Project pr) {
		// Search for empName in List
		for (int i=0; i<eList.size();i++) {
			// WRONG: The check MUST be placed in the class 
			// that holds the argument
			
			//String currName = eList.get(i).getName();
			//if (currName.equals(empName)) 
				// Add Project to the specific employee
				//eList.get(i).addProject(pr);			
			//}
			if (eList.get(i).hasName(empName)) {
				// Add Project to the specific employee
				eList.get(i).addProject(pr);
			}
		}		
	}

	public void printPayroll() {
		System.out.println("********** Payroll ************");
		for (int i=0; i<eList.size();i++) {
			eList.get(i).printPayroll();
		}
		System.out.println("********************************");		
	}

	public void printCost() {
		// Find all projects of company
		Set<Project> pSet = new HashSet<Project>();
		for (int i=0; i<eList.size();i++) {
			pSet.addAll(eList.get(i).getAllProjects());
		}		

		System.out.println("******** Product Costs *********");		
		for (Project p: pSet) {
			// Calc cost of all employees
			double exp = 0;
			for (int i=0; i<eList.size();i++) {
				// If the employee works in project P
				// Then we add his/her payroll in the EXPENSES of the project
				// NOTE!!! You ask the employee "Do you work in project P?"
				//         You are not getting into "Employee" and get what you need
				if (eList.get(i).worksIn(p)) {
					// AGAIN! You ask the employee "How much do you earn?"
					exp += eList.get(i).getPayroll();
				}
			}
			// Calc cost of project
			p.printCost(exp);
		}
		
		System.out.println("********************************");				
	}

}
