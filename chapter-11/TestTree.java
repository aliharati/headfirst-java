import java.util.*;

public class TestTree {
    public static void main(String[] args) {
        new TestTree().go();
    }
    public void go() {
        Book b1 = new Book("How Cats Work");
        System.out.println(b1.getTitle());
        Book b2 = new Book("Remix your Body");
        Book b3 = new Book("Finding Emo");
        Set<Book> tree = new TreeSet<>();
        tree.add(b1);
        tree.add(b2);
        tree.add(b3);
        System.out.println(tree);
    }
}
    class Book implements Comparable<Book>{
    private String title;
    public Book(String t) {
        title = t;
    }
    public String getTitle(){
        return title;
    }
    @Override
        public int compareTo(Book o) {
            return title.compareTo(o.getTitle());
        }
    public String toString(){
        return title;
    }
}
