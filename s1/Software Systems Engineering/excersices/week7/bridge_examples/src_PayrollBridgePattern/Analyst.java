
public class Analyst extends Employee {

	public Analyst(PaymentType pType, String n) {
		super(pType, n);
	}

	@Override
	public double getPayroll() {
		return pType.payDeveloper();
	}

}
