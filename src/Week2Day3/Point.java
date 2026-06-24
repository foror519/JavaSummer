package Week2Day3;

import java.util.*;

public class Point implements Comparable<Point> {
    final int x;
    final int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                 // та же ссылка → точно равны, быстрый выход
        if (obj == null || getClass() != obj.getClass()) return false;  // не тот тип → не равны
        Point other = (Point) obj;                    // теперь безопасно привести
        return x == other.x && y == other.y;          // сравнение по значению полей
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        HashSet<Point> set = new HashSet<>();
        set.add(new Point(1,2));
        set.add(new Point(1,2));
        System.out.println(set.size());
        System.out.println(set.contains(new Point(1,2)));
        TreeSet<Point> treeSet = new TreeSet<>();
        treeSet.add(new Point(2,3));
        treeSet.add(new Point(9,8));
        treeSet.add(new Point(1,2));
        System.out.println(treeSet.size());
        System.out.println(treeSet);
        LinkedHashSet<Point> linked = new LinkedHashSet<>();
        linked.add(new Point(9, 8));
        linked.add(new Point(1, 2));
        linked.add(new Point(5, 5));
        linked.add(new Point(9, 8));   // дубликат
        System.out.println(linked);

    }

    @Override
    public int compareTo(Point o) {
        int byX = Integer.compare(this.x, o.x);
        if (byX != 0) return byX;            // x различаются порядок задаёт x
        return Integer.compare(this.y, o.y); // x равны разрешаем по y
    }
}

