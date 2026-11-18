package model;

public class Client {
    private String name;
    private String phoneNumber;
    private String address;

    public Client(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Клиент: " + name + "\nТелефон: " + phoneNumber + "\nАдрес: " + address;
    }
}