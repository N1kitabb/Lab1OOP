package delivery;

public class PickupDelivery implements DeliveryType {
    @Override public String getName() { return "Самовывоз"; }
    @Override public double calculateCost(double orderTotal) { return 0.0; }
    @Override public int getDuration() { return 0; }
    @Override public boolean requiresCourier() { return false; }
}