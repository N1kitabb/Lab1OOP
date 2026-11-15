package model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<CatalogItem> items = new ArrayList<>(); // Заменили List<Product> на List<CatalogItem>

    public Catalog() {
        // И здесь создаем CatalogItem вместо Product
        items.add(new CatalogItem("Пицца", 550.0));
        items.add(new CatalogItem("Бургер", 300.0));
        items.add(new CatalogItem("Картошка фри", 150.0));
        items.add(new CatalogItem("Кола", 100.0));
    }

    public void printCatalog() {
        System.out.println("\n--- Каталог товаров ---");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).toString());
        }
    }

    public CatalogItem getProduct(int index) { // Заменили возвращаемый тип
        if (index > 0 && index <= items.size()) {
            return items.get(index - 1);
        }
        return null;
    }
}