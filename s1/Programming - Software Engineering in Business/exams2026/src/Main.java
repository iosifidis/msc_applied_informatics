
public class Main {
    public static void main(String[] args) {
    	
    	Pharmacy shop = new Pharmacy("Shop");

        Product pr1 = new Cosmetic("LOREAL", "SUNCREAM", 50.0, "Face");
        Product pr2 = new Cosmetic("LOREAL", "CRAYON", 30.0, "Lips");
        Product pr3 = new Medicine("ASPIRIN", "PILL", 10.0, true);
        Product pr4 = new Medicine("VOLTAREN", "PILL", 20.0, false);
        
        shop.addProduct(pr1);
        shop.addProduct(pr2);
        shop.addProduct(pr3);
        shop.addProduct(pr4);

        GUI inputFrame = new GUI(shop);
    }
}