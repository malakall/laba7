// Класс грузчика
import java.util.ArrayList;
import java.util.List;

class Loader extends Thread {
    private final String name;
    private final Warehouse warehouse;
    private final List<Product> carriedProducts = new ArrayList<>();
    private static final int MAX_WEIGHT = 150; // Максимальный вес для одного рейса

    public Loader(String name, Warehouse warehouse) {
        this.name = name;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            int totalWeight = 0;

            // Собираем товары до достижения лимита веса
            while (totalWeight < MAX_WEIGHT) {
                Product product = warehouse.takeProduct();
                if (product == null) {
                    break; // Если на складе больше нет товаров
                }

                if (totalWeight + product.getWeight() <= MAX_WEIGHT) {
                    carriedProducts.add(product);
                    totalWeight += product.getWeight();
                    System.out.println(name + " загрузил " + product.getName() + " (" + product.getWeight() + " кг)");
                } else {
                    // Если товар не помещается в текущий рейс, возвращаем его на склад
                    warehouse.addProduct(product);
                    break;
                }
            }

            // Если ничего не собрано, выходим из потока
            if (carriedProducts.isEmpty()) {
                break;
            }

            // Перенос товаров
            unloadProducts(totalWeight);
        }
    }

    // Разгрузка товаров
    private void unloadProducts(int totalWeight) {
        try {
            System.out.println(name + " переносит " + carriedProducts.size() + " товаров общим весом " + totalWeight + " кг на другой склад...");
            Thread.sleep(2000); // Имитация времени переноса
            System.out.println(name + " разгрузил товары на другом складе.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            carriedProducts.clear(); // Очищаем список перенесенных товаров
        }
    }
}