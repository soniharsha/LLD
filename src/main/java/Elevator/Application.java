package Elevator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Application {

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        ElevatorService elevatorService = new ElevatorService(elevator);
        Task t1 = new Task(10, Direction.UP);
        Task t2 = new Task(8, Direction.UP);
        Task t3 = new Task(1, Direction.DOWN);
        Task t4 = new Task(12, Direction.UP);

        Thread th1 = new Thread(()-> {
            elevatorService.executeRequestTask(t1);
        });

        Thread th2 = new Thread(()-> {
            elevatorService.executeRequestTask(t2);
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(th1);
//        executorService.submit(th2);
//        executorService.submit(()->{
//            elevatorService.executeRequestTask(t3);
//        });
//        executorService.submit(()->{
//            elevatorService.executeRequestTask(t4);
//        });
        th1.start();
        th2.start();
    }
}
