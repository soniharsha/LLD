package Splitwise.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TransactionView {
    private String name;
    private Integer transactionId;
    private String payerName;
    private Integer payerId;
    private BigDecimal amountPaid;
    private List<PayeeExpense> payeeExpenseList;
    @Data
    public static class PayeeExpense {
        private Integer payeeId;
        private String payeeName;
        private BigDecimal amountOwed;
    }
}
