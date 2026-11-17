package model;

public enum OrderStatus {
    CREATED("Создан"),
    COOKING("Готовится"),
    DELIVERING("Везем"),
    DELIVERED("Доставлен");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}