

abstract class Product {
    private String description;
    protected double price;

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public abstract double calculatePrice();

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
