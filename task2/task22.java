public class task22 {
    private static final int ROWS = 2;
    private static final int COLS = 2;
    private static final int[] maxInRows = new int[ROWS];

    public static void main(String[] args) throws InterruptedException {
        // Матрица задается вручную
        int[][] matrix = {
            {10, 25},
            {30, 45}
        };

        System.out.println("Матрица:");
        printMatrix(matrix);

        // Создаем потоки для обработки строк матрицы
        Thread thread1 = new Thread(new RowMaxFinder(matrix[0], 0));
        Thread thread2 = new Thread(new RowMaxFinder(matrix[1], 1));

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Замораживаем главный поток поочередно
        thread1.join();
        thread2.join();

        // Находим максимальный элемент среди всех строк
        int maxElement = Math.max(maxInRows[0], maxInRows[1]);
        System.out.println("Наибольший элемент в матрице: " + maxElement);
    }

    // Класс, реализующий интерфейс Runnable для нахождения максимума в строке
    static class RowMaxFinder implements Runnable {
        private final int[] row;
        private final int rowIndex;

        RowMaxFinder(int[] row, int rowIndex) {
            this.row = row;
            this.rowIndex = rowIndex;
        }

        @Override
        public void run() {
            int max = row[0];
            for (int i = 1; i < row.length; i++) {
                if (row[i] > max) {
                    max = row[i];
                }
            }
            maxInRows[rowIndex] = max; // Сохраняем результат в массив
            System.out.println("Максимум в строке " + rowIndex + ": " + max);
        }
    }

    // Метод для печати матрицы
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
