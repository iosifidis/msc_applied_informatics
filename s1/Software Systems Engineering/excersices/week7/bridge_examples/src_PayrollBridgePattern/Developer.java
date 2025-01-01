
public class Developer extends Employee {

	public Developer(PaymentType pType, String n) {
		super(pType, n);
	}

	@Override
	public double getPayroll() {
		return pType.payDeveloper();
	}

}
