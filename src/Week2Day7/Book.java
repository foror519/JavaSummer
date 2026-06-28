package Week2Day7;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {
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
    void setRating(int rating){
        if (rating < 0 || rating > 5) throw new IllegalArgumentException();
        else this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.isbn);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn == book.isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                '}';
    }

}
class Catalog{
    private final Map<Long, Book> books = new HashMap<>();

    public int size() {
        return books.size();
    }

    boolean add(Book b){
        if (books.containsKey(b.getIsbn())) return false;
        else books.put(b.getIsbn(),b);
        return true;
    }
    Optional<Book> findByIsbn(long isbn){
        return Optional.ofNullable(books.get(isbn));
    }
    Map<String, Long> countByAuthor(){
        Map<String, Long> result = new HashMap<>();
        for (Book value : books.values()) {
            result.merge(value.getAuthor(), 1L, Long::sum);
        }
        return result;
    }
    List<Book> sortedByRatingDescThenTitle(){
        List<Book> list = new ArrayList<>(books.values());
        list.sort(Comparator.comparingInt(Book::getRating).reversed().thenComparing(Book::getTitle));
        return list;
    }
    int removeUnrated(){
        int before = books.size();
        books.values().removeIf(b -> b.getRating() == 0);
        return before - books.size();
        // for (Book b : books.values()) {
        //     if (b.getRating() == 0) books.remove(b.getIsbn());  // рвёт modCount мимо итератора
        // }
    }
    double averageRating() {
        if (books.isEmpty()) {
            return 0.0; // или Double.NaN, но по условию нужно не падать
        }
        double sum = 0;
        for (Book b : books.values()) {
            sum += b.getRating();
        }
        return sum / books.size();
    }
    static <T> List<T> dedup(List<T> in) {
        return new ArrayList<>(new LinkedHashSet<>(in));
    }


    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Book b1 = new Book(9780001L, "Clean Code", "Robert Martin", 5);
        Book b2 = new Book(9780002L, "The Pragmatic Programmer", "Andrew Hunt", 5);
        Book b3 = new Book(9780003L, "Refactoring", "Martin Fowler", 4);
        Book b4 = new Book(9780004L, "Effective Java", "Joshua Bloch", 5);
        Book b5 = new Book(9780005L, "Some Draft", "Robert Martin", 0);
        Book b6 = new Book(9780006L, "Another Draft", "Unknown", 0);

        System.out.println("Добавление книг");
        System.out.println("Добавление b1: " + catalog.add(b1)); // true
        System.out.println("Добавление b2: " + catalog.add(b2));
        System.out.println("Добавление b3: " + catalog.add(b3));
        System.out.println("Добавление b4: " + catalog.add(b4));
        System.out.println("Добавление b5: " + catalog.add(b5));
        System.out.println("Добавление b6: " + catalog.add(b6));
        System.out.println("Размер каталога: " + catalog.size()); // 6

        // 2. Повторное добавление с тем же isbn → false
        System.out.println("Повторное добавление");
        Book duplicate = new Book(9780001L, "Duplicate", "Someone", 3);
        System.out.println("Добавление дубликата: " + catalog.add(duplicate)); // false
        System.out.println("Размер после дубликата: " + catalog.size()); // всё ещё 6

        // 3. countByAuthor
        System.out.println("countByAuthor");
        System.out.println(catalog.countByAuthor());
        // Ожидается: {Robert Martin=2, Andrew Hunt=1, Martin Fowler=1, Joshua Bloch=1, Unknown=1}

        // 4. sortedByRatingDescThenTitle
        System.out.println("sortedByRatingDescThenTitle");
        List<Book> sorted = catalog.sortedByRatingDescThenTitle();
        sorted.forEach(System.out::println);
        // Ожидаемый порядок: Clean Code (5), Effective Java (5), The Pragmatic Programmer (5), затем Refactoring (4)

        // 5. removeUnrated
        System.out.println("removeUnrated");
        int removed = catalog.removeUnrated();
        System.out.println("Удалено книг с рейтингом 0: " + removed); // 2
        System.out.println("Размер каталога после удаления: " + catalog.size()); // 4

        // 6. averageRating до удаления (сейчас уже удалили, поэтому создадим новый каталог для проверки)
        Catalog freshCatalog = new Catalog();
        freshCatalog.add(b1);
        freshCatalog.add(b2);
        freshCatalog.add(b3);
        freshCatalog.add(b4);
        freshCatalog.add(b5);
        freshCatalog.add(b6);
        System.out.println("averageRating до удаления");
        System.out.println("Средний рейтинг: " + freshCatalog.averageRating()); // 3.1667

        // 7. averageRating на пустом каталоге
        Catalog emptyCatalog = new Catalog();
        System.out.println("Средний рейтинг пустого каталога: " + emptyCatalog.averageRating()); // 0.0

        // 8. dedup
        System.out.println("dedup");
        List<String> withDup = List.of("a", "b", "a", "c", "b");
        List<String> unique = Catalog.dedup(withDup);
        System.out.println("Исходный список: " + withDup);
        System.out.println("Без дубликатов: " + unique); // [a, b, c]

        // 9. findByIsbn
        System.out.println("findByIsbn");
        long existingIsbn = 9780001L;
        long nonExistingIsbn = 9999999L;
        catalog.findByIsbn(existingIsbn).ifPresentOrElse(
                book -> System.out.println("Найдена книга: " + book),
                () -> System.out.println("Книга с isbn " + existingIsbn + " не найдена")
        );
        catalog.findByIsbn(nonExistingIsbn).ifPresentOrElse(
                book -> System.out.println("Найдена книга: " + book),
                () -> System.out.println("Книга с isbn " + nonExistingIsbn + " не найдена (ожидаемо)")
        );
    }
}
