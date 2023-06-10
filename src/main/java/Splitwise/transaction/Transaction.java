package Splitwise.transaction;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Transaction {
    private final Integer id;
    private final BigDecimal amount;
    private final String name;
    private final String addedBy;
    private final String updatedBy;
    private final Integer payeeId;
    private final Integer payerId;
}
