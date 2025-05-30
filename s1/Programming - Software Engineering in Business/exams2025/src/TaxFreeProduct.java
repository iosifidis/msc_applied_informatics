

class TaxFreeProduct extends Product {
    private double percentage;

    public TaxFreeProduct(String description, double price, double percentage) {
        super(description, price);
        this.percentage = percentage;
    }

    @Override
    public double calculatePrice() {
        return price * (1 - percentage);
    }
}
