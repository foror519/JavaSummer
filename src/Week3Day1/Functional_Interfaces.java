package Week3Day1;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BiFunction;

public class Functional_Interfaces {
    public static void main(String[] args) {
        Function<String, Integer> strLength = s -> s.length();
        Integer result = strLength.apply("hello");
        System.out.println(result);
        Predicate<String> isLongerThan3 = s -> s.length() > 3;
        boolean result2 = isLongerThan3.test("Hello");
        System.out.println(result2);
        Consumer<String> capitalWriting = s -> System.out.println(s.toUpperCase());
        capitalWriting.accept("Hello");
        Supplier<String> supplier = () -> "Я поставщик";
        System.out.println(supplier.get());
        BiFunction<Integer, Integer, Integer> sum = (a,b) -> a+b;
        System.out.println(sum.apply(2,3));

        Function<String, Integer> a = s -> Integer.parseInt(s); // статический
        Consumer<String> b = s -> System.out.println(s); // метод конкретного объекта
        Supplier<ArrayList<String>> c = () -> new ArrayList<>(); // конструктор
        Function<String, String> d = String::toUpperCase; // метод произвольного объекта данного типа, ссылка вызывает метод объекта String



    }
}
