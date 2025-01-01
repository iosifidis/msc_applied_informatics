/*
 * DECISION-7: pay methods need differnt info in the subclasses: PerHour needs hours, WithSalary needs num_projects
 * hours can be declared as attribute, since it is known from object creation time. BUT num_projects is dynamic
 * Option-1: use num_projects as a param in pay methods (CONS: the param would not be used in PerHour class)
 * Option-2: add num_projects as an attribute in WithSalary class (CONS: to increaseProjects would need an empty method in PerHour class)
 * Option-3: add num_projects as an attribute in PaymentType
 *     we opt for Option-3
*/

public abstract class PaymentType {
	protected int num_projects;
	
	public abstract double payDeveloper();
	public abstract double payManager();
	public abstract double payAnalyst();
	
	public void increaseProjects() {
		num_projects++;
	}
}
