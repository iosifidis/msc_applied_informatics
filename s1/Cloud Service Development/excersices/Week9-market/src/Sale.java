package gr.uom.market_week9;

import java.util.*;
import javax.persistence.*;

@Entity
public class Sale {

	@Id
	private int code;
	private int quantity; 
	
	
	@ManyToMany(mappedBy="sales")
	private Set<Product> products = new HashSet<Product>();
	
	@ManyToOne
	@JoinColumn(name="seller_name")
	private Seller seller;
	
	public Sale() {
		
	}
	
	public Sale(int c, int q) {
		code = c;
		quantity =q;
	}
	
	public void setSeller(Seller s) {
		seller = s;
	}
	
	public int getCode() {return code;}
	public int getQuantity() {return quantity;}
	public Seller getSeller() {return seller;}

	public void addPoduct(Product p) {
		products.add(p);
	}
	
}
