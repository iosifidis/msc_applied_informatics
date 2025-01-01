/*
 * DECISION-4: We must start refactoring from method getPayroll, since it is the longest and more complex (many ifs)
 * DECISION-5: To minimize ifs, we will use polymporphism: Create a hierarchy of Employee types
 * Option-1: Make first-level subclassing by Position Type
 * Option-2: Make first-level subclassing by Payment Type 
 *     we opt for Option-1, since would reduce the required ifs to 2
 * DECISION-6: How to reduce ifs even further?
 * Option-1: Create an extra level of inheritance (Cons: Many classes / Extension--e.g., add extra pmnt or pos types--would inflate the number of classes)
 * Option-2: Apply the Bridge design pattern
 *     We opt for Option-2
*/

import java.util.ArrayList;
import java.util.Collection;

public abstract class Employee {
	protected PaymentType pType;
	protected String name;
	protected ArrayList<Project> pList;
	
	public Employee(PaymentType payment, String n) {
		pType = payment;
		name = n;
		pList = new ArrayList<Project>();
	}

	public boolean hasName(String empName) {
		return name.equals(empName);
	}

	public void addProject(Project pr) {
		pList.add(pr);
		pType.increaseProjects();
	}

	public void printPayroll() {
		System.out.println("\t" + name + " gets: " + 
				getPayroll() + " euros");
	}

	public ArrayList<Project> getAllProjects() {
		return pList;
	}

	// TO IMPLEMENT THIS FEATURE WE USE THE BRIDGE DESIGN PATTERN
	/* Roles
	      Abstraction: Employee
		  Refined Abstractions: Developer, Analyst ManagerFactoryParameters
		  Implementation: PaymentType
		  Concrete Implementations: PerHour, WithSalary
		  
		  action / function: getPayroll 
		  
	   Notes on BRIDGE:
	      We use BRIDGE to link two hierarchies of objects that need to collaborate 
		  for brining the functionality
		  PROBLEM: The execution of the functionality, requires input from both hierarchies
		  SOLUTION: 
		     1. Bridge the hierarchies, through object composition
			 2. In containee hierachy create one polymorphic method for every refined abstraction 
			    (combinations of "Refined Abstractions" and "Concrete Implementations" are covered)
			 3. In the implementation of the function in each Refined Abstraction, call the corresponding
			    method that has been created
			 4. Further decisions might be needed for data management: e.g., DECISION-7
	*/
	
	public abstract double getPayroll(); 

	public boolean worksIn(Project p) {		
		return pList.contains(p);
	}

}
