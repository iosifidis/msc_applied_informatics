
public class AnalystWithSalary extends Analyst {

	public AnalystWithSalary(String n) {
		super(n);
	}

	@Override
	public double getPayroll() {
		return 2500 + (5 * pList.size());
	}

}
