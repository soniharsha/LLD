package Elevator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {

    private int stoppageLevel;
    private Direction directionOfDesiredDestinationFromCurrentLevel;
}
