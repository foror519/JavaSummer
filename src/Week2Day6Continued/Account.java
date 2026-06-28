package Week2Day6Continued;

import java.util.*;

public class Account {
    private final int id;
    private final String owner;
    private double balance;

    public Account(int id, String owner) {
        this.id = id;
        this.owner = owner;
    }
    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return id == account.id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
    boolean deposit(double sum){
        if (sum <= 0){
            System.out.println("Сумма депозита не может быть отрицательной/равна нулю");
            return false;
        }
        balance += sum;
        return true;
    }
    boolean withdraw(double sum){
        if (sum > 0 && sum <= balance) {
            balance -= sum;
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
class Bank {
    private final Map<Integer, Account> accounts = new HashMap<>();
    boolean open(Account a){
        if (accounts.containsKey(a.getId())) return false;
        else {
            accounts.put(a.getId(),a);
            return true;
        }
    }
    Optional<Account> findById(int id){
        return Optional.ofNullable(accounts.get(id));
    }
    Map<String, Double> totalByOwner(){
        Map<String, Double> result = new HashMap<>();
        for (Account value : accounts.values()) {
            result.merge(value.getOwner(),value.getBalance(), Double::sum);
        }
        return result;
    }
//    void closeEmptyAccounts(){
//        for (Account value: accounts.values()){
//            if (value.getBalance() == 0) accounts.remove(value.getId());
//        }
//    }
// если 1 пустой акк, то  не вылетит ConcurrentModificationException, если два - вылетит
        void closeEmptyAccounts(){
                accounts.values().removeIf(acc -> acc.getBalance() == 0);
        }
        List<Account> sortedByBalanceThenOwner(){
            List<Account> list = new ArrayList<>(accounts.values());
            list.sort(Comparator.comparingDouble(Account::getBalance).reversed().thenComparing(Account::getOwner));
            return list;
        }
    public static void main(String[] args) {
        Bank b = new Bank();
        b.open(new Account(1, "Liza"));
        b.open(new Account(2, "Dima"));
        b.open(new Account(3, "Vasya"));
        b.open(new Account(4,"Oleg"));
        b.open(new Account(5,"Stepan"));
        b.open(new Account(6,"Yan"));
        b.findById(1).ifPresent(acc -> acc.deposit(100));
        b.findById(2).ifPresent(acc -> acc.deposit(200));
        b.findById(3).ifPresent(acc -> acc.deposit(300));
        b.findById(6).ifPresent(acc -> acc.deposit(200));
        System.out.println(b.totalByOwner());
        System.out.println("До удаления пустых акков: " + b.accounts);
        b.closeEmptyAccounts();
        System.out.println("После удаления пустых акков: " + b.accounts);
        System.out.println("------------------------");
        System.out.println("До сортировки по балансу убыванию, при равенстве = owner по возрастанию: " + b.accounts);
        System.out.println("После сортировки по балансу убыванию, при равенстве = owner по возрастанию: " + b.sortedByBalanceThenOwner());


    }
}
