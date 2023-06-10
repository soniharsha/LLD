package Elevator;

public class Application {

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        ElevatorService elevatorService = new ElevatorService(elevator);

        Thread starter = new Thread(()-> {
            elevatorService.executeNextTask();
        });
        starter.start();

        new Thread(()-> {
            elevatorService.executeExternalTask(new Task(10));
        }).start();

        new Thread(()-> {
            elevatorService.executeInternalTask(new Task(12));
        }).start();

        new Thread(()-> {
            elevatorService.executeInternalTask(new Task(5));
        }).start();

        new Thread(()-> {
            elevatorService.executeExternalTask(new Task(6));
        }).start();

        new Thread(()-> {
            elevatorService.executeExternalTask(new Task(10));
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }

        new Thread(()-> {
            elevatorService.executeInternalTask(new Task(5));
        }).start();

        new Thread(()-> {
            elevatorService.executeExternalTask(new Task(15));
        }).start();
    }
}
