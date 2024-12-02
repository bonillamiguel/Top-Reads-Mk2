
// this class manages all the books (objs) and handles file reading from the data set

import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class BookManager implements Features{

    private ArrayList<Books> books;

    // create a construct to initialize the arraylist
    public BookManager(){
        books = new ArrayList<>();
    }

    // create a public method to read for CSV

    public void loadBooks(String filePath){
        try(CSVReader read = new CSVReader(new FileReader(filePath))){
            String[] data;
            read.readNext(); // skip the headers in the CSV like title, author etc...
            while((data = read.readNext()) != null){
                String title = data[0].trim();
                String author = data[1].trim();
                String genre = data[2].trim();
                double rating = Double.parseDouble(data[3].trim());
                String description = data[4].trim();
                String url = data[5].trim(); // for the new method for web reader
                int year = Integer.parseInt(data[6].trim()); //year of the book
                books.add(new EBook(title, author, genre, rating, description, year, url));
            }
        }catch (IOException e){
            System.out.println("Error Occurred when reading the files: " + e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    // to get the list of all books
    @Override
    public ArrayList<Books> getBooks(){
        return books;
    }


    // adding a new method for filtering books by genre
    @Override
    public ArrayList<Books> filterBooksByGenre(String genre) {

        return (ArrayList<Books>) books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }


    // adding a method to sort books by rating in descending order
    // using the sorting algo bubble
    @Override
    public ArrayList<Books> get_Top10_BooksByRating(){
        ArrayList<Books> sortedBooks = new ArrayList<>(books);

        int n = sortedBooks.size();
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(sortedBooks.get(j).getRating() < sortedBooks.get(j + 1).getRating()){
                    // swap j and j + 1
                    Books temp = sortedBooks.get(j);
                    sortedBooks.set(j, sortedBooks.get(j + 1));
                    sortedBooks.set(j + 1, temp);
                }
            }
        }

        return new ArrayList<>(sortedBooks.subList(0, Math.min(10, sortedBooks.size())));
    }

    @Override
    public ArrayList<Books> recommendBooks(String genre, int year, double minRating) {
        return (ArrayList<Books>) books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .filter(book -> book.getYear() == year)
                .filter(book -> book.getRating() >= minRating)
                .collect(Collectors.toList());
    }
    public ArrayList<Books> searchBooks(String searchterm) {
        return (ArrayList<Books>) books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchterm.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(searchterm.toLowerCase()))
                .collect(Collectors.toList());
    }
}