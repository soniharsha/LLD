package Splitwise.transaction;

import Splitwise.request.IndividualExpenseRequest;

public class TransactionService {
    private TransactionManager transactionManager;

    public TransactionService() {
        this.transactionManager = new TransactionManager();
    }

    public Transaction addExpense(IndividualExpenseRequest expenseRequest) {
        Transaction transaction = new Transaction(transactionManager.getNewTransactionId(), expenseRequest.getAmount(),
                expenseRequest.getExpenseName(), expenseRequest.getUserId(), expenseRequest.getUserId(),expenseRequest.getPayeeId(),
                expenseRequest.getPayerId());
        return transactionManager.addTransaction(transaction);
    }

    public Transaction updateExpense(IndividualExpenseRequest expenseRequest, Integer transactionId) {
        Transaction oldTransaction = transactionManager.getTransaction(transactionId);
        Transaction updateTransaction = new Transaction(oldTransaction.getId(), expenseRequest.getAmount(),
                expenseRequest.getExpenseName(), oldTransaction.getAddedBy(), expenseRequest.getUserId(),expenseRequest.getPayeeId(),
                expenseRequest.getPayerId());
        return transactionManager.addTransaction(updateTransaction);
    }

    public void deleteExpense(Integer transactionId) {
        transactionManager.deleteTransaction(transactionId);
    }

    public Transaction getTransactionById(Integer transactionId) {
        return transactionManager.getTransaction(transactionId);
    }

}
