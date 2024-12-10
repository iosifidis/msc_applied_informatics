package gr.uom.market_week8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketServiceConfig implements CommandLineRunner {

	@Autowired
	private MarketService ms;
	
	@Override
	public void run(String... args) throws Exception {
		ms.addProduct(new Product(1,"Kaseri",4));
		ms.addProduct(new Product(2,"Salata",5));
		ms.addProduct(new Product(3,"Gala",1));
		System.out.println("DB has been created to MarketService!!!");
	}


	
}
