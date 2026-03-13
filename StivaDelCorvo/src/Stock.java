import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Stock {
    private Queue<Item> stock;
    private final int MAX_SIZE;

    public Stock (int maxSize) {
        this.MAX_SIZE = maxSize;
        this.stock = new LinkedList<>();
    }

    public synchronized void deposit(Item item) throws InterruptedException {
        while (stock.size() == MAX_SIZE) {
            System.out.println(Thread.currentThread().getName()+": in attesa...");
            wait();
        }
        stock.add(item);
        System.out.println(Thread.currentThread().getName()+": ha aggiunto "+item.toString());
        notifyAll();
    }


     public synchronized Item getItem() throws InterruptedException {
         while(stock.isEmpty()) {
             System.out.println(Thread.currentThread().getName()+": in attesa...");
             wait();
         }
         assert stock.peek() != null;
         System.out.println(Thread.currentThread().getName()+": ha prelevato "+stock.peek().toString());
         notifyAll();
         return stock.poll();
     }

}
