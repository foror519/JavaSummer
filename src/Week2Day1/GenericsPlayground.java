package Week2Day1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GenericsPlayground {
    public static <T> int countNulls(List<T> list){
        if (list.isEmpty()) return 0;
        int count = 0;
        for(T m: list){
            if (m == null) count++;
        }
        return count;
    }
    public static void main(String[] args) {
        Box<String> box_string = new Box<>();
        Box<Integer> box_integer = new Box<>();
        // box_string.set(5) --- не получилось
        System.out.println(box_integer.sum(List.of(1,2,3,4,5)));
        System.out.println(box_string.first(List.of(7,52,14)));
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add(null);
        list.add("450");
        list.add(null);
        list.add(null);
        list.add("0");
        System.out.println(countNulls(list));
        box_string.set("Hello");
        System.out.println(box_string.get());

    }
}
class Box<T>{
    private T value;
    void set(T t) {
        this.value = t;
    }
    T get(){
        return value;
    }
    <T> T first (List<T> list){
        return list.getFirst();
    }
    <T> T last (List<T> list){
        if (!list.isEmpty()) return list.get(list.size()-1);
        else throw new NoSuchElementException();
    }
    <T extends Number> double sum(List<T> nums){
        double result = 0;
        for(T m: nums){
            result += m.doubleValue();
        }
        return result;
    }
}
