/*
 * DECISION-4: We must start refactoring from method getPayroll, since it is the longest and more complex (many ifs)
 * DECISION-5: To minimize ifs, we will use polymporphism: Create a hierarchy of Employee types
 * Option-1: Make first-level subclassing by Position Type
 * Option-2: Make first-level subclassing by Payment Type 
 *     we opt for Option-1, since would reduce the required ifs to 2
 * DECISION-6: How to reduce ifs even further?
 * Option-1: Create an extra level of inheritance (Cons: Many classes / Extension--e.g., add extra pmnt or pos types--would inflate the number of classes)
 * Option-2: Apply the Bridge design pattern
 *     for demonstration purposes, we opt for Option-1
*/

import java.util.ArrayList;
import java.util.Collection;

public abstract class Employee {
	protected String name;
	protected ArrayList<Project> pList;
	
	public Employee(String n) {
		name = n;
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

	public abstract double getPayroll();

	public boolean worksIn(Project p) {		
		return pList.contains(p);
	}

}
