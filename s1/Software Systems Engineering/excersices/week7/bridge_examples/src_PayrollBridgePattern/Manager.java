
public class Manager extends Employee {

	public Manager(PaymentType pType, String n) {
		super(pType, n);
	}

	@Override
	public double getPayroll() {
		return pType.payManager();
	}

}
