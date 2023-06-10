package Splitwise.group;

import Splitwise.request.ExpenseRequest;
import Splitwise.request.GroupCreationRequest;
import Splitwise.request.GroupExpenseRequest;
import Splitwise.request.IndividualExpenseRequest;
import Splitwise.response.TransactionView;
import Splitwise.transaction.Transaction;
import Splitwise.transaction.TransactionService;
import Splitwise.user.User;
import Splitwise.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private GroupManager groupManager;
    private GroupTransactionManager groupTransactionManager;
    private TransactionService transactionService;
    private UserService userService;

    public GroupService() {
        this.groupManager = new GroupManager();
        this.groupTransactionManager = new GroupTransactionManager();
    }

    public Group createGroup(GroupCreationRequest groupCreationRequest) {
        List<Integer> groupMemberUserIdList = new ArrayList<>();
        for(String phoneNumber: groupCreationRequest.getPhoneNumber()) {
            groupMemberUserIdList.add(userService.getUserByPhoneNumber(phoneNumber, groupCreationRequest.getCreatorUserId()).getId());
        }

        for(String emailId: groupCreationRequest.getEmailIds()) {
            groupMemberUserIdList.add(userService.getUserByEmailId(emailId, groupCreationRequest.getCreatorUserId()).getId());
        }

        Group group = new Group();
        group.setId(groupManager.createNewGroupId());
        group.setName(groupCreationRequest.getGroupName());
        group.getGroupMembers().addAll(groupMemberUserIdList);
        return groupManager.addGroup(group);
    }

    public void addGroupExpense(GroupExpenseRequest groupExpenseRequest) {
        Group group = groupManager.getGroupById(groupExpenseRequest.getGroupId());
        GroupTransaction groupTransaction = new GroupTransaction();
        groupTransaction.setId(groupTransactionManager.createNewGroupTransactionId());
        groupTransaction.setGroupId(groupExpenseRequest.getGroupId());
        groupTransaction.setName(groupExpenseRequest.getExpenseName());
        groupTransaction.setPayerId(groupTransaction.getPayerId());
        groupTransaction.setAmountSpent(groupExpenseRequest.getAmountSpent());
        List<Integer> allTransactionInGroupTransaction = new ArrayList<>();
        for(GroupExpenseRequest.IndividualExpense expense: groupExpenseRequest.getIndividualExpenseRequestList()) {
            IndividualExpenseRequest individualExpenseRequest = createIndividualExpenseRequest(groupExpenseRequest, expense);
            Transaction transaction = transactionService.addExpense(individualExpenseRequest);
            allTransactionInGroupTransaction.add(transaction.getId());
        }
        groupTransaction.getTransactionIds().addAll(allTransactionInGroupTransaction);
        group.getGroupTransactionList().add(groupTransaction.getId());
    }

    public void deleteGroupExpense(Integer groupId, Integer groupTransactionId) {
        Group group = groupManager.getGroupById(groupId);
        GroupTransaction groupTransaction = groupTransactionManager.getGroupTransactionById(groupTransactionId);
        List<Integer> transactions = groupTransaction.getTransactionIds();
        for(Integer transactionId: transactions) transactionService.deleteExpense(transactionId);
        groupTransactionManager.deleteTransactionGroupTransaction(groupTransactionId);
        group.getGroupTransactionList().remove(groupTransactionId);
    }

    public List<TransactionView> getAllGroupTransactions(Integer groupId) {
        Group group = groupManager.getGroupById(groupId);
        List<GroupTransaction> allGroupTransactions = new ArrayList<>();
        for(Integer groupTransactionId: group.getGroupTransactionList()) {
            GroupTransaction groupTransaction = groupTransactionManager.getGroupTransactionById(groupTransactionId);
            allGroupTransactions.add(groupTransaction);
        }

        List<TransactionView> allTransactionsView = new ArrayList<>();
        for(GroupTransaction groupTransaction: allGroupTransactions) {
            TransactionView transactionView = new TransactionView();
            transactionView.setTransactionId(groupTransaction.getGroupId());
            transactionView.setName(groupTransaction.getName());
            transactionView.setAmountPaid(groupTransaction.getAmountSpent());
            transactionView.setPayerId(groupTransaction.getPayerId());
            User payer = userService.getUser(groupTransaction.getPayerId());
            transactionView.setPayerName(payer.getName());

            List<TransactionView.PayeeExpense> payeeExpenseList = new ArrayList<>();

            for(Integer individualTransaction: groupTransaction.getTransactionIds()) {
                Transaction transaction = transactionService.getTransactionById(individualTransaction);
                TransactionView.PayeeExpense payeeExpense = new TransactionView.PayeeExpense();
                payeeExpense.setPayeeId(transaction.getPayeeId());
                User payee = userService.getUser(transaction.getPayeeId());
                payeeExpense.setPayeeName(payee.getName());
                payeeExpense.setAmountOwed(transaction.getAmount());
                payeeExpenseList.add(payeeExpense);
            }
            transactionView.setPayeeExpenseList(payeeExpenseList);
            allTransactionsView.add(transactionView);
        }
        return allTransactionsView;
    }

    private IndividualExpenseRequest createIndividualExpenseRequest(ExpenseRequest expenseRequest, GroupExpenseRequest.IndividualExpense individualExpense) {
        IndividualExpenseRequest individualExpenseRequest = new IndividualExpenseRequest();
        individualExpenseRequest.setExpenseName(expenseRequest.getExpenseName());
        individualExpenseRequest.setAmount(individualExpense.getAmount());
        individualExpenseRequest.setPayerId(expenseRequest.getPayerId());
        individualExpenseRequest.setPayeeId(individualExpense.getPayeeId());
        individualExpenseRequest.setUserId(expenseRequest.getUserId());
        return individualExpenseRequest;
    }

}
