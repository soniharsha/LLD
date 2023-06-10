package Splitwise.group;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {
    private Integer id;
    private String name;
    private List<Integer> groupMembers;
    private List<Integer> groupTransactionList;

    public Group() {
        this.groupMembers = new ArrayList<>();
        this.groupTransactionList = new ArrayList<>();
    }
}
