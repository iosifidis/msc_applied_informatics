package gr.uom.market_week9;

import java.util.*;
import javax.persistence.*;

@Entity
public class Seller {
	
	@Id
	private String name;
	
	
	@OneToMany(mappedBy="seller", 
	           cascade= CascadeType.ALL,
	           fetch = FetchType.LAZY)
	private List<Sale> sales = new ArrayList<>();
	
	public Seller() {}
	
	public Seller(String n) {
		name = n;
	}
	
	public String getName() {return name;}

	public void addSale(Sale s) {
		sales.add(s);
		 s.setSeller(this); 
	}
	

}
