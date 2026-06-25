package Week2Day4;

import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private final String type;
    private final int amount;

    public Transaction(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }

    static Map<String, Integer> calculateTotals(Transaction[] transactions) {
        Map<String, Integer> map = new HashMap<>();
        for (Transaction t: transactions)
            map.put(t.type, map.getOrDefault(t.type, 0) + t.amount);
        return map;
    }


    public static void main(String[] args) {
        Transaction[] array = {new Transaction("Deposit", 300), new Transaction("Withdraw", 100)};
        System.out.println(calculateTotals(array));
    }
}



