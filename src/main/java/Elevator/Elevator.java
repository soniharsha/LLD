package Elevator;

import lombok.Data;

@Data
public class Elevator {
    private int curLevel;
    private State state;
    public Elevator() {
        this.curLevel = 0;
        this.state = State.STOP;
    }

    public void updateStateOnCompletionOfInternalRequest() {
        this.state = State.IDLE;
    }

    public void updateStateOnCompletionOfAllRequest() {
        this.state = State.STOP;
    }
    public void executeTask(Task task) {
        this.state = State.MOVING;
        if (task.getDestinationLevel() < curLevel) {
            executeDownward(task.getDestinationLevel());
        }
        if (task.getDestinationLevel() > curLevel) {
            executeUpward(task.getDestinationLevel());
        }
        System.out.println("Reached destination---" + task.getDestinationLevel());
    }
    private void executeDownward(int levelToReach) {
        while(curLevel>levelToReach) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ie) {
//
//            }
            System.out.println("Moving downward at level-- "+ curLevel);
            curLevel--;
        }
    }

    private void executeUpward(int levelToReach) {
        while(curLevel<levelToReach) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ie) {
//
//            }
            System.out.println("Moving upward at level-- "+ curLevel);
            curLevel++;
        }
    }
    public boolean isRunning() {
        return state==State.MOVING;
    }

    public boolean hasStopped() {
        return state==State.STOP;
    }
}