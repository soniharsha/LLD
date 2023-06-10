package cache;

import java.util.ArrayList;
import java.util.List;

public class CacheApplication {
    public static void main(String[] args) {
        CacheService cacheService = new CacheService(1);
//        cacheService.updateOrInset("A", "1");
//        cacheService.updateOrInset("B", "2");
//        System.out.println(cacheService.get("C"));
//        System.out.println(cacheService.get("B"));
//        cacheService.updateOrInset("C","3");
//        System.out.println(cacheService.get("C"));
//        System.out.println(cacheService.get("A"));
//        cacheService.updateOrInset("B","4");
//        System.out.println(cacheService.get("C"));
//        System.out.println(cacheService.get("B"));


        Thread t1 = new Thread(()-> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cacheService.updateOrInset("A", "1");
        });

        Thread t2 = new Thread(()-> {
            System.out.println(cacheService.get("C"));
        });


        List<Thread> threads = new ArrayList<>();
        final int numOfThreads = 3;

//        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(()-> {
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                cacheService.updateOrInset("A", "1");
                System.out.println("A: "+ cacheService.get("A"));
                System.out.println("C: "+cacheService.get("C"));
            }));

            threads.add(new Thread(()-> {
                System.out.println(cacheService.get("C"));
            }));

            threads.add(new Thread(()-> {
                cacheService.updateOrInset("C", "10");
            }));

            threads.add(new Thread(()-> {
                System.out.println(cacheService.get("A"));
            }));
//        }

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }

//        t1.run();
//        t2.run();

    }
}
