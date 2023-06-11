package RaceCondition;

public interface FundTransferService {
    int fetchBalance(Character account);
    void addBalance(Character account, int amount);
    void transfer(Character source, Character destination, int amount);
}
