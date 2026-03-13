public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock(5);
        final int N=30;
        final int pirateNum = 3;
        final int scribeNum = 2;

        // Pirates
        Thread[] pirates = new Thread[pirateNum];
        // Scribes
        Thread[] scribes = new Thread[scribeNum];

        for (int i = 0; i < pirateNum; i++) {
            pirates[i] = new Thread(() -> {
                // Sono nel run() di un thread
                for (int count = 0; count < N/pirateNum; count++) {
                    try {
                        stock.deposit(new Item("Item: " + count));
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (Thread p : pirates) p.start();
        try {
            for (Thread p : pirates) p.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < scribeNum; i++) {
            scribes[i] = new Thread(() -> {
                for (int count = 0; count < 15; count++) {
                    try {
                        stock.getItem();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (Thread s : scribes) s.start();
        try {
            for (Thread s : pirates) s.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
