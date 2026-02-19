
abstract class Product {
    private String company;
    private String name;
    protected double netPrice;

    public Product(String company, String name, double netPrice) {
    	this.company = company;
    	this.name=name;	
        this.netPrice = netPrice;
    }

    public abstract double calculatePrice();
    
    public abstract void printProductInfo();

    public String getCompany() {
		return company;
	}
    
    public String getName() {
    	return name;
    }
    
    public double getNetPrice() {
		return netPrice;
	}
}
