package Splitwise.request;

import lombok.Data;
@Data
public class ExpenseRequest {
    private Integer payerId;
    private String userId;
    private String expenseName;
}
