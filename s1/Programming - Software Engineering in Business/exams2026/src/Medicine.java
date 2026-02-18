
class Medicine extends Product {
    private boolean prescription;
    private double medTax = 0.065;
    
   public Medicine(String company, String name, double netPrice, boolean prescription) {
		super(company, name, netPrice);
		this.prescription = prescription;
	}

    @Override
    public double calculatePrice() {
        return netPrice * (1 + medTax);
    }
    
    @Override
    public void printProductInfo() {
    	System.out.println("Medicine: " + getCompany() + " " + getName() + ", Net Price: " + getNetPrice());
    	if (prescription) {
			System.out.println("Requires prescription.");
		} else {
			System.out.println("Does not require prescription.");
		}
    }
}