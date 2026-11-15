package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 1;
    private int id;
    private Client client;
    private List<OrderProduct> items;
    private String status;
    private DeliveryType deliveryType;
    private Courier courier;

    public Order(Client client) {
        this.id = nextId++;
        this.client = client;
        this.items = new ArrayList<>();
        this.status = "Новый";
    }

    public void addItem(OrderProduct item) {
        items.add(item);
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void assignCourier(Courier courier) {
        if (deliveryType != null && !deliveryType.requiresCourier()) {
            System.out.println("Ошибка: для этого способа доставки курьер не требуется!");
            return;
        }
        this.courier = courier;
        if (courier != null) {
            courier.setFree(false);
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() { return id; }
    public Client getClient() { return client; }
    public List<OrderProduct> getItems() { return items; }
    public String getStatus() { return status; }
    public DeliveryType getDeliveryType() { return deliveryType; }
    public Courier getCourier() { return courier; }

    public double getItemsTotal() {
        double sum = 0;
        for (OrderProduct item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public double getFinalCost() {
        double total = getItemsTotal();
        if (deliveryType != null) {
            total += deliveryType.calculateCost(total);
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ №").append(id).append(" для ").append(client.getName()).append("\n");
        sb.append("Статус: ").append(status).append("\n");
        sb.append("--- Позиции ---\n");
        for (OrderProduct item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Сумма товаров: ").append(getItemsTotal()).append(" руб.\n");

        if (deliveryType != null) {
            sb.append("Доставка: ").append(deliveryType.getName())
                    .append(" (Срок: ").append(deliveryType.getDuration()).append(" ч.)\n");
            if (deliveryType.requiresCourier()) {
                sb.append("Курьер: ").append(courier != null ? courier.getName() : "Не назначен").append("\n");
            } else {
                sb.append("Курьер: Не требуется (самовывоз)\n");
            }
        } else {
            sb.append("Доставка: Не выбрана\n");
        }
        sb.append("ИТОГО: ").append(getFinalCost()).append(" руб.");
        return sb.toString();
    }
}