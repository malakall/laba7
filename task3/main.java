public class main {

    public static void main(String[] args) {
        // Создаем склад с товарами
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(new Product("Товар1", 50));
        warehouse.addProduct(new Product("Товар2", 40));
        warehouse.addProduct(new Product("Товар3", 60));
        warehouse.addProduct(new Product("Товар4", 30));
        warehouse.addProduct(new Product("Товар5", 20));
        warehouse.addProduct(new Product("Товар6", 70));

        // Создаем грузчиков
        Loader loader1 = new Loader("Грузчик 1", warehouse);
        Loader loader2 = new Loader("Грузчик 2", warehouse);
        Loader loader3 = new Loader("Грузчик 3", warehouse);

        // Запускаем потоки грузчиков
        loader1.start();
        loader2.start();
        loader3.start();
    }
}



