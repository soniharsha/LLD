package RaceCondition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FundTransferServiceUsingThreadOrdering implements FundTransferService {
    private final int totalThread = 10;
    private Map<Character, Integer> accountBalance;
    private ExecutorService threadPool[];
    public FundTransferServiceUsingThreadOrdering() {
        this.accountBalance = new HashMap<>();
        this.threadPool = new ExecutorService[totalThread];
        for(int i=0;i<totalThread;i++) {
            threadPool[i] = Executors.newSingleThreadExecutor();
        }
    }

    public int fetchBalance(Character account) {
        return accountBalance.getOrDefault(account, 0);
    }

    public void addBalance(Character account, int amount) {
        ExecutorService threadForExecution = threadPool[getThreadIndexForAccount(account)];
        threadForExecution.submit(()-> addBalanceThreadSafe(account, amount));
    }

    private int getThreadIndexForAccount(Character account) {
        return (account-'A')%10;
    }

    private Void addBalanceThreadSafe(Character account, int amount) {
        int curAmount = accountBalance.getOrDefault(account, 0);
        int newBalance = curAmount+amount;
        accountBalance.put(account, newBalance);
        return null;
    }

    public void transfer(Character source, Character destination, int amount) {
        Boolean canTransfer = checkBalanceAndAdjustBalance(source, amount);
        if(canTransfer) addBalance(destination, amount);
    }

    private Boolean checkBalanceAndAdjustBalance(Character source, int amount) {
        try {
            ExecutorService threadForExecution = threadPool[getThreadIndexForAccount(source)];
            return threadForExecution.submit(() -> {
                int curAmount = accountBalance.getOrDefault(source, 0);
                if (curAmount >= amount) {
                    addBalanceThreadSafe(source, -amount);
                    return true;
                }
                return false;
            }).get();
        } catch (InterruptedException | ExecutionException ex) {
            return false;
        }
    }
}
