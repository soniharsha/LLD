package Splitwise.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GroupExpenseRequest extends ExpenseRequest {
    private Integer groupId;
    private BigDecimal amountSpent;
    private List<IndividualExpense> individualExpenseRequestList;
    @Data
    public static class IndividualExpense {
        private Integer payeeId;
        private BigDecimal amount;
    }
}
