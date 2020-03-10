import java.util.concurrent.TimeUnit;

class HoidLockThread implements Runnable {
    private String lockA;
    private String lockB;


    public HoidLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        synchronized (lockA) {

            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t尝试获得：" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (lockB) {

            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB+ "\t尝试获得：" + lockA);

        }

    }
}

public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA="lockA";
        String lockB="lockB";
        new Thread(new HoidLockThread(lockA,lockB),"AAAA").start();
        new Thread(new HoidLockThread(lockB,lockA),"BBBB").start();



    }
}
