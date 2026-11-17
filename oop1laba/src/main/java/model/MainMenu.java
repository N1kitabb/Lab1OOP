package model;

public class MainMenu {
    private final ConsoleInput input;
    private Client client;
    private final OrderService orderService;
    private final OrderUI orderUI;

    public MainMenu(ConsoleInput input) {
        this.input = input;
        Catalog catalog = new Catalog();

        // Создаем сервис (он ничего не знает о консоли)
        this.orderService = new OrderService(catalog);

        // Создаем UI и передаем ему сервис
        this.orderUI = new OrderUI(input, orderService);
    }

    public void printMainMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Главное меню ---");
            System.out.println("1. Создать Курьера");
            System.out.println("2. Создать Пользователя");
            if (client != null) {
                System.out.println("3. Создать заказ");
                System.out.println("4. История заказов");
            }
            System.out.println("0. Выход");

            choice = input.readInt("> ");

            switch (choice) {
                case 1 -> createCourier();
                case 2 -> createClient();
                case 3 -> {
                    if (client != null) {
                        orderUI.createOrder(client); // Делегируем UI
                    } else {
                        System.out.println("Сначала создайте клиента!");
                    }
                }
                case 4 -> {
                    if (client != null) {
                        orderUI.showOrderHistory(); // Делегируем UI
                    } else {
                        System.out.println("Сначала создайте клиента!");
                    }
                }
                case 0 -> System.out.println("Выход...");
                default -> System.out.println("Неверная команда.");
            }
        }
    }

    private void createClient() {
        String name = input.readString("Имя: ");
        String phone = input.readPhone("Телефон: ");
        String address = input.readString("Адрес: ");

        client = new Client(name, phone, address);
        System.out.println("Клиент успешно создан.");
    }

    private void createCourier() {
        String name = input.readString("Имя курьера: ");
        String phone = input.readPhone("Телефон курьера: ");

        Courier courier = new Courier(name, phone);
        orderService.addCourier(courier);
        System.out.println("Курьер успешно создан:\n" + courier);
    }
}