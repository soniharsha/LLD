package Splitwise.transaction;

import java.util.HashMap;
import java.util.Map;
class TransactionManager {
    private Integer id;
    private Map<Integer, Transaction> transactionMap;

    public TransactionManager() {
        this.id = 0;
        this.transactionMap = new HashMap<>();
    }

    public Integer getNewTransactionId() {
        return ++this.id;
    }

    public Transaction getTransaction(Integer id) {
        return transactionMap.get(id);
    }

    public Transaction addTransaction(Transaction transaction) {
        this.transactionMap.put(transaction.getId(), transaction);
        return getTransaction(transaction.getId());
    }

    public void deleteTransaction(Integer transactionId) {
        this.transactionMap.remove(transactionId);
    }
}
