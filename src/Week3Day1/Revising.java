package Week3Day1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Revising  {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("7777777");
        list.add("1");
        list.add("333");
        list.add("22");
        list.add("666666");
        list.add("4444");
        list.add("55555");
        list.sort((a,b) -> a.length() - b.length());
        System.out.println(list);
        Comparator<String> byLength = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        list.sort(byLength);
        System.out.println(list);

    }
}
