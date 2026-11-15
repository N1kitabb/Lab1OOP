package model;

public interface DeliveryType {
    String getName();
    double calculateCost(double orderTotal);
    int getDuration();
    boolean requiresCourier();
}