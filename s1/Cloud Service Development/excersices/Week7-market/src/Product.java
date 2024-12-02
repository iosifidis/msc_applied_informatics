package gr.uom.market_week6;

public class Product {
	
	private Integer code;  // Αλλαγή από int σε Integer
    private String name;
    private Integer price;  // Αλλαγή από int σε Integer
	
	public Product(Integer c, String n, Integer t) {
		code = c;
		name = n;
		price = t;
	}

	public int getCode() {
	    return code;
	}


	public String getName() {
		return name;
	}


	public int getPrice() {
		return price;
	}
	
    @Override
    public String toString() {
        return "Product{name='" + name + "'}";
    }


}
