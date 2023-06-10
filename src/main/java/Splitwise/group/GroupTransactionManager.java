package Splitwise.group;

import java.util.HashMap;
import java.util.Map;

class GroupTransactionManager {
    private Integer id;
    private Map<Integer, GroupTransaction> groupTransactionMap;

    public GroupTransactionManager() {
        this.id = 0;
        this.groupTransactionMap = new HashMap<>();
    }

    public Integer createNewGroupTransactionId() {
        return ++this.id;
    }
    public GroupTransaction getGroupTransactionById(Integer groupId) {
        return groupTransactionMap.get(groupId);
    }

    public GroupTransaction addGroupTransaction(GroupTransaction groupTransaction) {
        this.groupTransactionMap.put(groupTransaction.getId(), groupTransaction);
        return getGroupTransactionById(groupTransaction.getId());
    }

    public void deleteTransactionGroupTransaction(Integer groupTransactionId) {
        this.groupTransactionMap.remove(groupTransactionId);
    }
}
