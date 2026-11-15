package model;

public class CatalogItem {
    private String name;
    private double price;

    public CatalogItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " — " + price + " руб.";
    }
}