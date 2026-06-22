package Day6;

import java.util.ArrayList;
import java.util.List;

public class BankAccount extends Account{
    private final int number;
    private String owner;
    private double balance;
    BankAccount(int number, String owner){
        this.number = number;
        this.owner = owner;
    }
    void deposit(double sum){
        balance += sum;
    }
    void withdraw(double sum){
        if (balance - sum < 0) System.out.println("Ошибка вывода");
        else balance -= sum;
    }
    public int getNumber(){
        return this.number;
    }
    public String getOwner(){
        return this.owner;
    }
    public double getBalance(){
        return this.balance;
    }
    public void setOwner(String own){
        this.owner = own;
    }

    @Override
    String accountType() {
        return "Checking";
    }
}
class Test{
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        BankAccount ba = new BankAccount(1,"Lizzy");
        SavingsAccount sa = new SavingsAccount(2,"Bob",5);
        ba.deposit(1000.0);
        sa.deposit(1000.0,"sa");
        accounts.add(ba);
        accounts.add(sa);
        for(BankAccount s: accounts){
            s.withdraw(999.0);
            System.out.println(s.getOwner() + "balance = " + s.getBalance());
        }
//        new Account(); не скомпилировался, потому что абстрактный класс нельзя инстанцировать



    }
}
class SavingsAccount extends BankAccount implements InterestBearing{
    double rate;
    SavingsAccount(int number, String owner, double rate){
        super(number,owner);
        this.rate = rate;
    }
    public void addInterest(){
        deposit(getBalance()*(rate/100));
    }
    @Override
    void withdraw(double sum){
        if (getBalance()-sum < 100) System.out.println("Баланс будет меньше 100");
        else super.withdraw(sum);
    }
    @Override
    void deposit(double sum){
        System.out.println("Overriden method");
        super.deposit(sum);
    }
    void deposit(double sum, String note){
        System.out.println("Note: " + note);
        super.deposit(sum);
    }
    @Override
    String accountType(){
        return "Savings";
    }
}
abstract class Account{
    abstract String accountType();
}
interface InterestBearing{
    void addInterest();
}
