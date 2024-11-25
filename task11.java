public class task11 {

    public static void main(String[] args) throws InterruptedException {
        int[] result = new int[2];

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                result[0] = firstHalf(new int[]{5, 2, 4, 5, 7, 11});
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                result[1] = secondHalf(new int[]{5, 2, 4, 5, 7, 11});
            }
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        int total = result[0] + result[1];
        System.out.println("Общая сумма: " + total);
    }

    public static int firstHalf(int[] m) {
        int res1 = 0;
        for (int i = 0; i < 3; i++) {
            res1 += m[i];
        }
        return res1;
    }

    public static int secondHalf(int[] m) {
        int res2 = 0;
        for (int i = 3; i < 6; i++) {
            res2 += m[i];
        }
        return res2;
    }
}