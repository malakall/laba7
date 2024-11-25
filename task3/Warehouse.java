// Класс склада
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Warehouse {
    private final List<Product> products = new ArrayList<>();
    private final Lock lock = new ReentrantLock(); // Для синхронизации доступа к складу

    // Добавление товара на склад
    public void addProduct(Product product) {
        products.add(product);
    }

    // Извлечение товара со склада (синхронизированное)
    public Product takeProduct() {
        lock.lock();
        try {
            if (!products.isEmpty()) {
                return products.remove(0);
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }

    // Проверка, пуст ли склад
    public boolean isEmpty() {
        return products.isEmpty();
    }
}