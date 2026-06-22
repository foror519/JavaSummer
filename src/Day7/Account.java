package Day7;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private int number;
    private String owner;
    private double balance;
    protected Account(int number, String owner, double balance){
        this.number = number;
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double sum){
        if (sum > 0) balance += sum;
    }
    public void withdraw(double sum){
        if (sum > 0 && sum <= balance) balance -= sum;
    }
    public double getBalance(){
        return balance;
    }
    public String getOwner(){
        return owner;
    }
    public int getNumber(){
        return number;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    abstract String accountType();
}
class CheckingAccount extends Account{
    CheckingAccount(int number, String owner, double balance){
        super(number, owner, balance);
    }
    String accountType(){
        return "This is Checking account";
    }
}
interface InterestBearing{
    void addInterest();
}
class SavingsAccount extends Account implements InterestBearing{
    double rate;
    SavingsAccount(int number, String owner, double balance, double rate){
        super(number, owner, balance);
        this.rate = rate;
    }
    @Override
    public void addInterest() {
        deposit(getBalance()*(rate/100));
    }
    @Override
    String accountType() {
        return "This is Savings Account";
    }
    @Override
    public void withdraw(double sum){
        super.withdraw(sum);
        System.out.println("Withdraw from savings account");
    }
}
class Bank{
    List<Account> list = new ArrayList<>();
    void openAccount(Account a1){
        list.add(a1);
    }
    void transfer(Account a1, Account a2, double sum){
        double temp = a1.getBalance();
        a1.withdraw(sum);
        if (a1.getBalance() != temp) a2.deposit(sum);
    }
    void addRate(Account a1, int x){
        if (a1 instanceof SavingsAccount) ((SavingsAccount)a1).rate += x;
    }
}
class Test{
    public static void main(String[] args) {
        CheckingAccount c1 = new CheckingAccount(1, "Lizzy", 3000);
        SavingsAccount s1 = new SavingsAccount(2,"Bob",5000,5);
        Bank b = new Bank();
        b.openAccount(c1);
        b.openAccount(s1);
        b.addRate(s1,3);
        b.addRate(c1,5); // по сути ничего не должно произойти
        System.out.println(s1.rate); // должно быть 8
//      System.out.println(c1.rate); не сработает поскольку нет поля rate у CheckingAccount
        System.out.println(c1.accountType());
        System.out.println(s1.accountType());
        b.transfer(c1,s1,2000);
        System.out.println(c1.getBalance()); // 1000
        System.out.println(s1.getBalance()); // 7000
        s1.addInterest(); // 7000 * 1.08 = 7560
        System.out.println(s1.getBalance());
        c1.withdraw(10000);
        System.out.println(c1.getBalance()); // осталась 1000

    }
}