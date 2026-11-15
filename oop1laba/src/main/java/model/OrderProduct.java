package model;

public class OrderProduct {
    private Product product;
    private int quantity;

    public OrderProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " (по " + product.getPrice() + " руб.) = " + getTotalPrice() + " руб.";
    }
}