package Day4;

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



    }
}

