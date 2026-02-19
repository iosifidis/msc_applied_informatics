
class Cosmetic extends Product {
    private String category;
    private double cosmeticTax = 0.23;

    public Cosmetic(String company, String name, double netPrice, String category) {
		super(company, name, netPrice);
		this.category = category;
	}
    
    public String getCategory() {
		return category;
	} 

    @Override
    public double calculatePrice() {
        return netPrice * (1 - cosmeticTax);
    }
    
    @Override
    public void printProductInfo() {
    	System.out.println("Cosmetic: " + getCompany() + ", Name: " + getName() + ", Net Price: " + getNetPrice() + ", Category: " + getCategory());
	 	
    }
}
