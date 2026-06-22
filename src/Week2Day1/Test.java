package Week2Day1;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Box<String> box_string = new Box<>();
        Box<Integer> box_integer = new Box<>();
        // box_string.set(5) --- не получилось
        System.out.println(box_integer.sum(List.of(1,2,3,4,5)));
        System.out.println(box_string.first(List.of(7,52,14)));

    }
}
class Box<T>{
    void set(T t) {

    }
    void get(){

    }
    <T> T first (List<T> list){
        return list.getFirst();
    }
    <T extends Number> double sum(List<T> nums){
        double result = 0;
        for(T m: nums){
            result += m.doubleValue();
        }
        return result;
    }
}
