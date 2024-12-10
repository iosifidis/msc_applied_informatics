package gr.uom.market_week8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

	@Autowired
	private MarketRepository productRepository;

	public Product getProduct(int n, String a, int l) {
		return new Product(n, a, l); 
	}
	
	public List<Product> getAllProducts() throws Exception {
		return productRepository.findAll();
	}

	public void addProduct(Product pr) throws Exception {
		productRepository.save(pr);
	}
	
	 public Product findProduct(String pr) throws Exception {
		 return productRepository.findByName(pr);
		 
	 }

}
