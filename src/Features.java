import java.util.ArrayList;

public interface Features {

    ArrayList<Books> getBooks();
    ArrayList<Books> filterBooksByGenre(String genre);
    ArrayList<Books> get_Top10_BooksByRating();
    ArrayList<Books> recommendBooks(String genre, int year, double minRating);
}
