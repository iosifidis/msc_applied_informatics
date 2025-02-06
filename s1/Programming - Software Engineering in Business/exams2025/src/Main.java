

public class Main {
    public static void main(String[] args) {
    	eShop magazaki = new eShop("thesShop");

        Product pr1 = new TaxFreeProduct("SERVER", 1000, 0.10);
        Product pr2 = new TaxFreeProduct("PC", 800, 0.20);
        Product pr3 = new LargeProduct("MOBILE", 1200, 0.30);

        magazaki.addProduct(pr1);
        magazaki.addProduct(pr2);
        magazaki.addProduct(pr3);

        InputFrame inputFrame = new InputFrame(magazaki);
    }
}