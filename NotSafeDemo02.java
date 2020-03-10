import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotSafeDemo02 {
    public static void main(String[] args) {
        List<String> list=new CopyOnWriteArrayList<>();
        // Collections.synchronizedList(new ArrayList<>()) ;   // new ArrayList<>();

        for (int i = 1; i <=30 ; i++) {

            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(list);
            },String.valueOf(i)).start();

        }

    }
}
