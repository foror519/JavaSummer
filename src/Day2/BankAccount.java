package Day2;

public class BankAccount {
    int number;
    String owner;
    double balance;
    BankAccount(int number, String owner, double balance){
        this.number = number;
        this.owner = owner;
        this.balance = balance;
    }
    void deposit(double sum){
        balance += sum;
    }
    void withdraw(double sum){
        balance -= sum;
    }
    void reassign(BankAccount ba){
        ba.owner = "Changed owner";
        ba = new BankAccount(100, "Tried to change owner", 0);
    }
}
class Test{
    public static void main(String[] args) {
        BankAccount ba1 = new BankAccount(1, "Holly", 2000.0);
        BankAccount ba2 = new BankAccount(2, "Derek", 3000.0);
        BankAccount ba3 = new BankAccount(3, "Will", 4000.0);
        BankAccount ba4 = new BankAccount(777,"ChangeMe",999);
        ba1.deposit(1000);
        ba2.withdraw(2000);
        ba3.deposit(3000);
        System.out.println(ba1.balance); // здесь должно быть 3000
        System.out.println(ba2.balance);
        System.out.println(ba3.balance);
        BankAccount b = ba1; // тут тоже 3000
        b.deposit(1000); // тут уже 4000
        System.out.println(b.balance); // обе переменные ссылаются на один и тот же объект
        ba4.reassign(ba4);
        System.out.println(ba4.owner); // будет Changed owner
    }
}
