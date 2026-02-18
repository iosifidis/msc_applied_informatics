
import java.util.ArrayList;

public class Pharmacy {

	private String name;
    private ArrayList<Product> products = new ArrayList<>();

    public Pharmacy(String name) {
        this.name = name;
    }

    public void addProduct(Product pr) {
        products.add(pr);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }
}
