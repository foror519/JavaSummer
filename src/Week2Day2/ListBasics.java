package Week2Day2;

import java.util.ArrayList;
import java.util.List;

public class ListBasics {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Dima");
        list.add("Liza");
        list.add("Lesha");
        list.add("Katya");
        list.add("Vova");
        System.out.println(list.get(3)); // должен вывестись Katya, доступ O(1)
        list.remove(2); // должен удалиться Lesha, удаление O(N) из-за сдвига элементов, что были справа - влево
        list.remove("Katya"); // удаление по значению
        System.out.println(list); // Dima - 0, Liza - 1, Vova 2
        for(String s: list){ // вывод через foreach
            System.out.println(s);
        }
        // List raw = new ArrayList(); - проверка типов более не работает на этапе компиляции,
        // в рантайм может возникнуть ClassCastException если применить неподходящий метод
    }
}
