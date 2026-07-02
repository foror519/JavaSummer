package Week3Day2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static class Book {
        private final long isbn;     // бизнес-ключ (идентичность)
        private final String title;
        private final String author;
        private int rating;

        public Book(long isbn, String title, String author, int rating) {
            if (rating < 0 || rating > 5) throw new IllegalArgumentException();
            else this.rating = rating;
            this.isbn = isbn;
            this.title = title;
            this.author = author;
        }

        public long getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getRating() {
            return rating;
        }
    }

    public static void main(String[] args) {
        Book b1 = new Book(9780001L, "Clean Code", "Robert Martin", 5);
        Book b2 = new Book(9780002L, "The Pragmatic Programmer", "Andrew Hunt", 5);
        Book b3 = new Book(9780003L, "Refactoring", "Martin Fowler", 4);
        Book b4 = new Book(9780004L, "Effective Java", "Joshua Bloch", 5);
        Book b5 = new Book(9780005L, "Some Draft", "Robert Martin", 0);
        Book b6 = new Book(9780006L, "Another Draft", "Unknown", 0);
        List<Book> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);
        list.add(b6);
        System.out.println(list.stream().filter(x -> x.getRating() > 3).map(x -> x.getTitle()).collect(Collectors.toList()));
        list.stream().filter(x -> x.getRating() > 3).peek(x -> System.out.println("прошло фильтр: " + x.getTitle())).collect(Collectors.toList());
        list.stream().peek(x -> System.out.println("до фильтра: " + x.getTitle())).filter(x -> x.getRating() > 3).peek(x -> System.out.println("  прошло фильтр: " + x.getTitle())).collect(Collectors.toList());
    }
}
