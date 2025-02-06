

import java.util.ArrayList;

public class eShop {

	private String name;
    private ArrayList<Product> products = new ArrayList<>();

    public eShop(String name) {
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
