
public class WithSalary extends PaymentType {
	
	@Override
	public double payDeveloper() {
		return 1500 + (2 * num_projects);
	}

	@Override
	public double payManager() {
		return 4000 + (10 * num_projects);
	}

	@Override
	public double payAnalyst() {
		return 3500 + (5 * num_projects);
	}



}
