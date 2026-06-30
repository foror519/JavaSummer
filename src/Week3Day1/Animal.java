package Week3Day1;

import java.util.*;

public abstract class Animal implements Comparable<Animal> {
    private final String name;
    private int age;
    private boolean adopted;
    private final long id;

    public Animal(String name, int age, long id) {
        if (age < 0) throw new IllegalArgumentException();
        else this.age = age;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public long getId(){
        return id;
    }

    public boolean isAdopted() {
        return adopted;
    }
    abstract void sound();
    public boolean adopt() {
        if (adopted) return false;
        adopted = true;
        return true;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", adopted=" + adopted +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal a = (Animal)obj;
        return this.getId() == a.getId();
    }

    @Override
    public int compareTo(Animal o) {
        return Integer.compare(o.age, this.age);
    }
}
class Dog extends Animal implements Moving{
    public Dog(String name, int age, long id) {
        super(name, age, id);
    }
    @Override
    void sound(){
        System.out.println("I am dog, I bark!");
    }
    @Override
    public void run() {
        System.out.println("Dog runs");
    }
    @Override
    public void swim(){
        System.out.println("Dogs can swim");
    }
}
class Cat extends Animal implements Moving{
    public Cat(String name, int age, long id) {
        super(name, age, id);
    }
    @Override
    void sound(){
        System.out.println("I am cat, I meow!");
    }

    @Override
    public void run() {
        System.out.println("Cat runs");
    }
    @Override
    public void swim(){
        System.out.println("Cats don't like water");
    }
}
interface Moving{
    void run();
    void swim();
}
class Shelter{
    public int size() {
        return animals.size();
    }
    private final Map<Long, Animal> animals = new HashMap<>();
    Optional<Animal> findById(long id){
        return Optional.ofNullable(animals.get(id));
    }
    double findAverageAge(){
        if (animals.isEmpty()) return 0.0;
        double sumAge = 0;
        for (Animal value: animals.values()){
            sumAge += value.getAge();
        }
        return sumAge/ animals.size();
    }
    boolean add(Animal a){
        if(animals.containsKey(a.getId())) return false;
        else {
            animals.put(a.getId(),a);
        }
        return true;
    }

    List<Animal> sortedByName(){
        List<Animal> list = new ArrayList<>(animals.values());
        list.sort(Comparator.comparing(Animal::getName));
        return list;
    }
    List<Animal> sortedByAge(){
        List<Animal> list = new ArrayList<>(animals.values());
        Collections.sort(list);
        return list;
    }
    int clearShelter(){
        int before = animals.size();
        animals.values().removeIf(Animal::isAdopted);
        return before - animals.size();
    }
    Map<String, List<Animal>> groupByType(){
        Map<String, List<Animal>> result = new HashMap<>();
        for (Animal a : animals.values()) {
            result.computeIfAbsent(a.getClass().getSimpleName(), k -> new ArrayList<>()).add(a);
        }
        return result;
    }

}
class Test{
    public static void main(String[] args) {
        Dog d1 = new Dog("Sharik",1,9999999);
        Dog d2 = new Dog("Oleg",3,4382123);
        Dog d3 = new Dog("Svinya",2,543535);
        Cat c1 = new Cat("Kolya",4,74536654);
        Cat c2 = new Cat("Lesha",5,123213);
        Cat c3 = new Cat("Masha",2,87657);
        Shelter s = new Shelter();
        s.add(d1);
        d1.adopt();
        s.add(d2);
        d2.adopt();
        s.add(d3);
        d3.adopt();
        c1.adopt();
        s.add(c2);
        c2.adopt();
        s.add(c3);
        System.out.println(s.findAverageAge());
        System.out.println(s);
        System.out.println(s.sortedByName());
        System.out.println(s.groupByType());
        System.out.println(s.sortedByAge());
        System.out.println(s.clearShelter());
    }
}

