package Splitwise.group;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class GroupTransaction {
    private Integer id;
    private Integer groupId;
    private String name;
    private Integer payerId;
    private BigDecimal amountSpent;
    private List<Integer> transactionIds;

    public GroupTransaction() {
        this.transactionIds = new ArrayList<>();
    }
}
