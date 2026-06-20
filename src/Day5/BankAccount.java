package Day5;

public class BankAccount {
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

}
class Test{
    public static void main(String[] args) {
        BankAccount ba = new BankAccount(1,"DefaultUser");
        ba.setOwner("Lizzy");
        ba.deposit(5000.0);
        ba.withdraw(50.0);
        System.out.println("Balance = " + ba.getBalance());
        System.out.print("Owner = " + ba.getOwner());
        System.out.print("Number = " + ba.getNumber());
        SavingsAccount sa = new SavingsAccount(2,"DefaultUser",5);
        sa.addInterest();
        sa.deposit(1000.0);
        sa.addInterest();
        System.out.println(sa.getBalance());
        sa.withdraw(1050);
        sa.deposit(150,"Кинул денег");
        System.out.println(sa.getBalance());

    }
}
class SavingsAccount extends BankAccount{
    double rate;
    SavingsAccount(int number, String owner, double rate){
        super(number,owner);
        this.rate = rate;
    }
    void addInterest(){
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
}


