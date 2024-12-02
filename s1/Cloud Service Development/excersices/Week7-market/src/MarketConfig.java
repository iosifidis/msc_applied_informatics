package gr.uom.market_week6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketConfig implements CommandLineRunner {

	@Autowired
	private MarketService ms;
	
	@Override
	public void run(String... args) throws Exception {
		ms.setList(loadFromDB());
		System.out.println("DB has been loaded to MarketService!!!");
	}

	private ArrayList<Product> loadFromDB() throws Exception {
		  ArrayList<Product> pList = new ArrayList<Product>();
		  
	      // create our mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost/market";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM products";

	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int code = rs.getInt("code");  
	        String name = rs.getString("name");
	        int price = rs.getInt("price");
	        pList.add(new Product(code, name, price));
	      }
	      st.close();
	      
	      return pList;
	}
	
}
