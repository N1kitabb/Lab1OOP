package model;

public class OrderProduct {
    private CatalogItem product; // Заменили тип
    private int quantity;

    // В конструкторе тоже меняем тип
    public OrderProduct(CatalogItem product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public CatalogItem getProduct() { return product; } // Заменили тип
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " (по " + product.getPrice() + " руб.) = " + getTotalPrice() + " руб.";
    }
}