package RaceCondition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class FundTransferServiceUsingConcurrentHashMap implements FundTransferService {
    private Map<Character, Integer> accountBalance;
    public FundTransferServiceUsingConcurrentHashMap() {
        this.accountBalance = new ConcurrentHashMap<>();
    }

    public int fetchBalance(Character account) {
        return accountBalance.getOrDefault(account, 0);
    }

    public void addBalance(Character account, int amount) {
        int curAmount = accountBalance.getOrDefault(account, 0);
        int newBalance = curAmount+amount;
        accountBalance.put(account, newBalance);
    }

    public void transfer(Character source, Character destination, int amount) {
        Boolean canTransfer = checkBalanceAndAdjustBalance(source, amount);
        if(canTransfer) addBalance(destination, amount);
    }

    private Boolean checkBalanceAndAdjustBalance(Character source, int amount) {
        int curAmount = accountBalance.getOrDefault(source, 0);
        if (curAmount >= amount) {
            addBalance(source, -amount);
            return true;
        }
        return false;
    }

}
