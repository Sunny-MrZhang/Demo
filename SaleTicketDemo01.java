import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {

    private int number = 30;
    Lock lock = new ReentrantLock();


    public void sale() {
        lock.lock();
        try {

            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "\t还剩下" + number);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}


public class SaleTicketDemo01 {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket.sale();
        }, "C").start();
    }
}
