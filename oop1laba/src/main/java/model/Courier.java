package model;

public class Courier {
    private String name;
    private String phoneNumber;
    private boolean isFree;

    public Courier(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isFree = true;
    }
    public void setFree(boolean free) {
        this.isFree = free;
    }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public boolean isFree() { return isFree; }

    @Override
    public String toString() {
        String status = isFree ? "Свободен" : "Занят на заказе";
        return "Курьер: " + name + "\nТелефон: " + phoneNumber + "\nЗанятость: " + status;
    }
}