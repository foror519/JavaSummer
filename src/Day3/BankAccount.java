package Day3;

public class BankAccount {
    final int number;
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
    BankAccount(){
        this(0,"",0.0);
    }
    BankAccount(BankAccount ba){
        this.number= ba.number;
        this.owner = ba.owner;
        this.balance = ba.balance;
    }
//    void change_number(){
//        number = 5; не сработал из-за final
//    }
}
class Test{
    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        BankAccount ba_copy = new BankAccount(ba);
        System.out.println(ba_copy.number);
        System.out.println(ba_copy.balance);
        System.out.println(ba_copy.owner);
//        Test2 t = new Test2(1,"Check"); // приватный конструктор не позволил
        Test2 t = Test2.create(1,"Check");
        System.out.println(t.num);
        System.out.println(t.name);

    }
}
class Test2{
    int num;
    String name;
    private Test2(int num, String name){
        this.num = num;
        this.name = name;
    }
    static Test2 create(int num, String name){
        return new Test2(num,name);
    }
}
