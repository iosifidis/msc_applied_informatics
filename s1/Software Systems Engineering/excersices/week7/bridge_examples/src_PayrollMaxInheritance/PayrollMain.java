/*
 * DECISION-1: How many classes is the minimum?
 * 2 for the entities + 1 as a container for the 2 entities and the methods that 
 * correspond to ALL projects and employees
 * 
 * DECISION-2: What relations do I add in the classes?
 * Option-1: Two-directional relation: REJECTEd-> Duplication, Threat of Inconsistencies, More complex code
 * Option-2: Uni-directional relation
 *     DECISION-2.1: Employee contains Project, or vice-verse?
 *        We opt for Employee contains Project
 *            RATIONALE: calcPayroll must be promoted in terms of less complex code
 *            since it is called more often
 *            PERMORMANCE GAIN, LESS BUGS, EASIER MAINTENANCE
 *            
 * DECISION-3: Shall I use getters?
 * Avoid using getters, but respect the rule of using attributes of own class
 * Check comments in the code
 */

public class PayrollMain {

	public static void main(String[] args) {
		Company c = new Company();
		
		/*
			PROCESS: We start refactoring from HERE
			   1. Change employee creation
			   2. Change employee constructor
			   3. Create new classes
			   4. Create constructors to new classes (use of super)
			   5. Make methods / classes, which are not leafs in the tree, as abstract
			   6. Implement abstract methods
		*/
		
		c.addEmployee(new DeveloperPerHour("AA", 15));    // Assumption-1: Employees are unique by name
				                                             // Assumption-2: The set hours are overall (not by project)
		c.addEmployee(new AnalystWithSalary("BB"));				
		c.addEmployee(new ManagerWithSalary("CC"));
		
		c.addProjectToEmployee("AA", new Project("Dev","Mobile"));   // Assumption-3: Projects are unique by name
		
		c.addProjectToEmployee("BB", new Project("Tech","Support"));
		c.addProjectToEmployee("BB", new Project("Dev","Web"));
		
		c.addProjectToEmployee("CC", new Project("Tech","Support"));

		c.printPayroll();
		System.out.println("\n");
		c.printCost();

	}

}
