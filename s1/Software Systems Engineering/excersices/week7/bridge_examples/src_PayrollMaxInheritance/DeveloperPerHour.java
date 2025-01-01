
public class DeveloperPerHour extends Developer {

	int hours;
	
	public DeveloperPerHour(String n, int h) {
		super(n);
		hours = h;
	}

	@Override
	public double getPayroll() {
		return 20 * hours;
	}

}
