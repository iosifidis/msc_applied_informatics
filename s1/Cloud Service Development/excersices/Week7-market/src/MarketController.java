package gr.uom.market_week6;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarketController {
	
	@Autowired
	private MarketService ms;
	
	@GetMapping(path="/allproducts")
	public List<Product> getAllProducts() throws Exception{
		return ms.getAllProducts();
	} 
	
	@PostMapping(path="/addproduct")
	public void addProduct(@RequestBody Product pr) throws Exception {
		ms.addProduct(pr);
	}
	
	@PostMapping(path = "/findproduct")
	public Product findProduct(@RequestBody String pr) throws Exception {
	    return ms.findProduct(pr);
	}
}
