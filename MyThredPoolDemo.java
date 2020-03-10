import java.util.concurrent.*;

public class MyThredPoolDemo {
    public static void main(String[] args) {

        ExecutorService threaPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        
        try {
            for (int i = 0; i < 12; i++) {
                threaPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threaPool.shutdown();
        }
    }
}