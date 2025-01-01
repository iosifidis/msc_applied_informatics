
public class Project {
	String pName;
	String pType;
	
	public Project(String type, String name) {
		pName = name;
		pType = type;
	}

	public int hashCode() {
		return pName.hashCode();
	}
	
	public void printCost(double exp) {
		System.out.println("\t" + pName + " costs "
				+ calcCost(exp) + " euros");
		
	}

	private double calcCost(double exp) {
		double cost = 0;
		
		if (pType.equals("Dev")) {
			cost = exp * 0.2;
		} else {
			cost = exp * 1.2;			
		}
		return cost;
	}

	public boolean equals(Object p) {
		Project pr = (Project) p;
		return pName.equals(pr.pName);
	}

}
