package model;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();
    private final Catalog catalog;
    private final List<DeliveryType> availableDeliveries = new ArrayList<>();

    public OrderService(Catalog catalog) {
        this.catalog = catalog;

        availableDeliveries.add(new StandardDelivery());
        availableDeliveries.add(new ExpressDelivery());
        availableDeliveries.add(new PickupDelivery());
    }

    public void addCourier(Courier courier) { couriers.add(courier); }
    public void saveOrder(Order order) { orders.add(order); }

    public List<Courier> getCouriers() { return couriers; }
    public Catalog getCatalog() { return catalog; }
    public List<DeliveryType> getAvailableDeliveries() { return availableDeliveries; }
    public List<Order> getOrders() { return orders; }
}