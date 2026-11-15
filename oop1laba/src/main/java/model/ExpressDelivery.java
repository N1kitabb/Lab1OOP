package model;

public class ExpressDelivery implements DeliveryType {
    @Override public String getName() { return "Экспресс-доставка"; }
    @Override public double calculateCost(double orderTotal) { return 200.0; }
    @Override public int getDuration() { return 3; }
    @Override public boolean requiresCourier() { return true; }
}