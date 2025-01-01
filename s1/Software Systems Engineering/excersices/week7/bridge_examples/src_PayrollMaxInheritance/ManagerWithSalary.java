
public class ManagerWithSalary extends Manager {

	public ManagerWithSalary(String n) {
		super(n);
	}

	@Override
	public double getPayroll() {
		return 4000 + (10 * pList.size());
	}

}
