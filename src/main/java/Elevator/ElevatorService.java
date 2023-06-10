package Elevator;

import java.util.LinkedList;
import java.util.Queue;

public class ElevatorService implements ElevatorObserver {
    private Elevator elevator;
    private Queue<Task> queuedTasks;

    public ElevatorService(Elevator elevator) {
        this.elevator = elevator;
        this.queuedTasks = new LinkedList<>();
        this.elevator.addObserver(this);

    }

    public void executeRequestTask(Task task) {
        if(elevator.isRunning() && elevator.canExecuteTaskWithOngoingTask(task)) {
            elevator.executeTask(task);
        } else {
            queuedTasks.add(task);
            elevator.startFreshTask(task);
        }
    }

    @Override
    public void elevatorFinishedWorking() {
        queuedTasks.poll();
        if(!queuedTasks.isEmpty()) {
            Task curTask = queuedTasks.poll();
            elevator.startFreshTask(curTask);
        }
    }
}
