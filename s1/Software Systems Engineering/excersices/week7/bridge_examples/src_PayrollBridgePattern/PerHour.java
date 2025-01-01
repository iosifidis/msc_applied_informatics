
public class PerHour extends PaymentType {
	private int hours;
	
	public PerHour(int h) {
		hours = h;
	}

	@Override
	public double payDeveloper() {
		return 15 * hours;
	}

	@Override
	public double payManager() {
		return 40 * hours;
	}

	@Override
	public double payAnalyst() {
		return 35 * hours;
	}



}
