import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{

    private  int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void increment()throws Exception{

        lock.lock();
         try {

             while (number!=0){
               condition.await();
             }
             number++;
             System.out.println(Thread.currentThread().getName()+"\t"+number);
             condition.signalAll();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
    }
    public void decrement()throws  Exception {

        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}


/*    public synchronized  void increment()throws Exception{
       while(number!=0){
           this.wait();
       }
        number++;
       System.out.println(Thread.currentThread().getName()+"\t"+number);
       this.notifyAll();
    }

    public synchronized  void decrement()throws  Exception{

        while (number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }*/



public class ProdConsumerDemo03 {
    public static void main(String[] args) {

        Aircondition aircondition = new Aircondition();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {

                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {

                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
