package Week2Day6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account{
    private double balance;
    private String owner;

    public Account(double balance, String owner) {
        this.balance = balance;
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", owner='" + owner + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Account a1 = new Account(100,"Liza");
        Account a2 = new Account(200,"Dima");
        Account a3 = new Account(300,"Vasya");
        Account a4 = new Account(200,"Yan");
        List<Account> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a4);
        list.add(a3);
        Comparator<Account> byOwner = Comparator.comparing(Account::getOwner);
        list.sort(byOwner);
        System.out.println(list);
        Comparator<Account> byBalance = Comparator.comparingDouble(Account::getBalance);
        list.sort(byBalance);
        System.out.println(list);
        Comparator<Account> byBalanceAndOwner = Comparator.comparingDouble(Account::getBalance).thenComparing(Account::getOwner);
        list.sort(byBalanceAndOwner);
        System.out.println(list);
        Comparator<Account> byBalanceDescendingAndOwner = Comparator.comparingDouble(Account::getBalance).reversed().thenComparing(Account::getOwner);
        list.sort(byBalanceDescendingAndOwner);
        System.out.println(list);


    }


}
