package Splitwise.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndividualExpenseRequest extends ExpenseRequest {
    private Integer payeeId;
    private BigDecimal amount;
}
