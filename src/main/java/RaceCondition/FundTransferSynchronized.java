package RaceCondition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FundTransferSynchronized implements FundTransferService {

    private Map<Character, Integer> accountBalance;
    public FundTransferSynchronized() {
        this.accountBalance = new HashMap<>();
    }
    @Override
    public synchronized int fetchBalance(Character account) {
        return accountBalance.getOrDefault(account, 0);
    }

    @Override
    public synchronized void addBalance(Character account, int amount) {
        int curAmount = accountBalance.getOrDefault(account, 0);
        int newBalance = curAmount+amount;
        accountBalance.put(account, newBalance);
    }

    @Override
    public synchronized void transfer(Character source, Character destination, int amount) {
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
