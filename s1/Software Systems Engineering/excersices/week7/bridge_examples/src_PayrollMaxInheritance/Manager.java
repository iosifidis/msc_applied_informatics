
public abstract class Manager extends Employee {
	public Manager(String n) {
		super(n);
	}

	@Override
	public abstract double getPayroll();
}
