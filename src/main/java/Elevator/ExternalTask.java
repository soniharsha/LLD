package Elevator;

import lombok.Data;

@Data
public class ExternalTask {

    private int requestLevel;
    private Direction destinationDirection;
}
