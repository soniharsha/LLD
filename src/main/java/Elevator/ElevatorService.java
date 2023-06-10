package Elevator;

import java.util.LinkedList;
import java.util.Queue;

public class ElevatorService {
    private long lastExternalRequestTime = System.currentTimeMillis();
    private Elevator elevator;
    private Queue<Task> internalTasks;
    private Queue<Task> externalTasks;
    public ElevatorService(Elevator elevator) {
        this.elevator = elevator;
        this.externalTasks =  new LinkedList<>();
        this.internalTasks =  new LinkedList<>();
    }

    public void executeInternalTask(Task task) {
        this.internalTasks.add(task);
    }

    public void executeExternalTask(Task task) {
        this.externalTasks.add(task);
    }
    public void executeNextTask() {
        while(true) {
            if (elevator.isRunning()) {
                while (!internalTasks.isEmpty()) {
                    Task task = internalTasks.poll();
                    elevator.executeTask(task);
                }
                if (lastExternalRequestTime < System.currentTimeMillis() - 2000) {
                    elevator.updateStateOnCompletionOfInternalRequest();
                }
            } else {
                if (!externalTasks.isEmpty() && !elevator.isRunning()) {
                    lastExternalRequestTime = System.currentTimeMillis();
                    Task task = externalTasks.poll();
                    elevator.executeTask(task);
                }
            }
            if (internalTasks.isEmpty() && externalTasks.isEmpty()) elevator.updateStateOnCompletionOfAllRequest();
        }
    }

}
