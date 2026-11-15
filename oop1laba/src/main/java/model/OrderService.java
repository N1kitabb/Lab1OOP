package model;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final ConsoleInput input;
    private final Catalog catalog;
    private final List<Order> orders = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();
    private final List<DeliveryType> availableDeliveries = new ArrayList<>();

    public OrderService(ConsoleInput input, Catalog catalog) {
        this.input = input;
        this.catalog = catalog;

        // Заполняем список реализациями доставки
        availableDeliveries.add(new StandardDelivery());
        availableDeliveries.add(new ExpressDelivery());
        availableDeliveries.add(new PickupDelivery());
    }

    public void addCourier(Courier courier) {
        couriers.add(courier);
    }

    // --- ВОТ ОН, ТОТ САМЫЙ МЕТОД, КОТОРЫЙ ИЩЕТ MAINMENU ---
    public void createOrder(Client client) {
        Order order = new Order(client);
        int choice = -1;

        System.out.println("\n--- Оформление нового заказа ---");

        while (choice != 0) {
            System.out.println("\nТекущий заказ №" + order.getId() + " (Сумма: " + order.getItemsTotal() + " руб.)");
            System.out.println("1. Добавить товар в заказ");
            System.out.println("2. Выбрать способ доставки");
            System.out.println("3. Назначить курьера");
            System.out.println("4. Завершить оформление заказа");
            System.out.println("0. Отменить заказ");

            choice = input.readInt("> ");

            switch (choice) {
                case 1 -> addItemToOrder(order);
                case 2 -> chooseDelivery(order);
                case 3 -> assignCourier(order);
                case 4 -> {
                    if (order.getItems().isEmpty()) {
                        System.out.println("Нельзя оформить пустой заказ! Добавьте товары.");
                    } else {
                        orders.add(order);
                        System.out.println("\nЗаказ №" + order.getId() + " успешно оформлен!");
                        System.out.println(order.toString());
                        choice = 0;
                    }
                }
                case 0 -> {
                    System.out.println("Создание заказа отменено.");
                    choice = 0;
                }
                default -> System.out.println("Неверная команда.");
            }
        }
    }

    public void showOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("\nИстория заказов пуста.");
            return;
        }

        System.out.println("\n--- История заказов ---");
        for (Order o : orders) {
            System.out.println(o.toString());
            System.out.println("----------------------");
        }
    }

    private void addItemToOrder(Order order) {
        catalog.printCatalog();
        int choice = input.readInt("Выберите товар (0 - отмена): ");
        if (choice > 0) {
            CatalogItem p = catalog.getProduct(choice);
            if (p != null) {
                int qty = input.readInt("Количество: ");
                order.addItem(new OrderProduct(p, qty));
                System.out.println("Товар добавлен!");
            } else {
                System.out.println("Неверный номер товара.");
            }
        }
    }

    private void chooseDelivery(Order order) {
        System.out.println("\n--- Доступные способы доставки ---");

        for (int i = 0; i < availableDeliveries.size(); i++) {
            DeliveryType dt = availableDeliveries.get(i);
            double cost = dt.calculateCost(order.getItemsTotal());
            String costStr = cost > 0 ? cost + " руб." : "Бесплатно";

            System.out.println((i + 1) + ". " + dt.getName() + " (" + costStr + ", " + dt.getDuration() + " ч.)");
        }

        System.out.println("0. Отмена");

        int choice = input.readInt("> ");
        if (choice <= 0 || choice > availableDeliveries.size()) {
            System.out.println("Отмена выбора доставки.");
            return;
        }

        DeliveryType selectedDelivery = availableDeliveries.get(choice - 1);
        order.setDeliveryType(selectedDelivery);

        System.out.println("Выбрана доставка: " + selectedDelivery.getName());
        System.out.println("Стоимость доставки: " + selectedDelivery.calculateCost(order.getItemsTotal()) + " руб.");
        System.out.println("Ориентировочный срок: " + selectedDelivery.getDuration() + " ч.");
    }

    private void assignCourier(Order order) {
        if (order.getDeliveryType() == null) {
            System.out.println("Сначала выберите доставку!");
            return;
        }
        if (!order.getDeliveryType().requiresCourier()) {
            System.out.println("Для самовывоза курьер не нужен.");
            return;
        }
        if (couriers.isEmpty()) {
            System.out.println("Нет доступных курьеров.");
            return;
        }

        System.out.println("Доступные курьеры:");
        for (int i = 0; i < couriers.size(); i++) {
            Courier c = couriers.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " (" + (c.isFree() ? "Свободен" : "Занят") + ")");
        }

        int choice = input.readInt("> ");
        if (choice > 0 && choice <= couriers.size()) {
            Courier selected = couriers.get(choice - 1);
            if (selected.isFree()) {
                order.assignCourier(selected);
                System.out.println("Курьер назначен!");
            } else {
                System.out.println("Курьер занят.");
            }
        }
    }
}