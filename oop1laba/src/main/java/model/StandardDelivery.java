package model;

public class StandardDelivery implements DeliveryType {
    @Override public String getName() { return "Стандартная доставка"; }
    @Override public double calculateCost(double orderTotal) { return 100.0; }
    @Override public int getDuration() { return 2; }
    @Override public boolean requiresCourier() { return true; }
}