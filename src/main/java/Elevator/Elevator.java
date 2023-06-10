package Elevator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Data
public class Elevator {
    private int level;
    private Direction direction;
    private Stack<Integer> sequenceOfStoppage;
    private boolean interrupted;
    private List<ElevatorObserver> elevatorObserverList;

    public Elevator() {
        this.level = 0;
        this.direction = Direction.STATIC;
        this.sequenceOfStoppage = new Stack<>();
        this.interrupted = false;
        this.elevatorObserverList = new ArrayList<>();
    }

    public void addObserver(ElevatorObserver elevatorObserver) {
        this.elevatorObserverList.add(elevatorObserver);
    }
    public boolean startFreshTask(Task task) {
        if(direction!=Direction.STATIC) return false;

        direction = task.getDirectionOfDesiredDestinationFromCurrentLevel();
        switch (task.getDirectionOfDesiredDestinationFromCurrentLevel()) {
            case UP:
                if(this.level>=task.getStoppageLevel()) this.direction = Direction.DOWN;
            case DOWN:
                if(this.level<=task.getStoppageLevel()) this.direction = Direction.UP;
        }
        return executeTask(task);
    }

    public boolean executeTask(Task task) {
        System.out.println(this.direction);
        interrupted=true;
        sequenceOfStoppage.add(task.getStoppageLevel());
        interrupted=false;
        if(direction==Direction.DOWN) return executeDownward();
        if(direction==Direction.UP) return executeUpward();
        return false;
    }

    public boolean canExecuteTaskWithOngoingTask(Task task) {
        if(direction!=task.getDirectionOfDesiredDestinationFromCurrentLevel()) return false;
        return true;
    }

    private boolean executeDownward() {
        while(!sequenceOfStoppage.isEmpty()) {
            int nextLevel = sequenceOfStoppage.peek();
            if(this.level<nextLevel) {
                updateStateAndPublishStateChange();
                return false;
            }
            while (!interrupted && level >= nextLevel) {
                if(level==nextLevel) {
                    System.out.println(nextLevel);
                    sequenceOfStoppage.pop();
                    break;
                }
                try {
                    Thread.sleep(1000);
                    level--;
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
            if(interrupted) return false;
        }
        updateStateAndPublishStateChange();
        return true;
    }

    private boolean executeUpward() {
        while(!sequenceOfStoppage.isEmpty()) {
            int nextLevel = sequenceOfStoppage.peek();
            if(this.level>nextLevel) {
                updateStateAndPublishStateChange();
                return false;
            }
            while (!interrupted && level <= nextLevel) {
                if(level==nextLevel) {
                    System.out.println(nextLevel);
                    sequenceOfStoppage.pop();
                    break;
                }
                try {
                    Thread.sleep(1000);
                    level++;
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
            if(interrupted) return false;
        }
        updateStateAndPublishStateChange();
        return true;
    }

    private void updateStateAndPublishStateChange() {
        this.direction = Direction.STATIC;
        for(ElevatorObserver observer: elevatorObserverList) {
            observer.elevatorFinishedWorking();
        }
    }

    public boolean isRunning() {
        return direction!=Direction.STATIC;
    }
}