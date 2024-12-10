package gr.uom.market_week8;

import javax.persistence.*;

@Entity
public class Product {
	
	@Id
	private Integer code;  // Αλλαγή από int σε Integer
    private String name;
    private Integer price;  // Αλλαγή από int σε Integer
	
    public Product() {
		code = 0;
		name = "";
		price = 0;
	}
    
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
	
//    @Override
//    public String toString() {
//        return "Product{name='" + name + "'}";
//    }


}
