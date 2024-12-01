
// this class manages all the books (objs) and handles file reading from the data set

import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;


public class BookManager implements Features{

    private ArrayList<Books> books;

    // create a construct to initialize the arraylist
    public BookManager(){
        books = new ArrayList<>();
    }

    // create a public method to read for CSV

    public void loadBooks(String filePath){
        try(BufferedReader read = new BufferedReader(new FileReader(filePath))){
            String line;
            read.readLine(); // skip the headers in the CSV like title, author etc...
            while((line = read.readLine()) != null){
                String[] data = line.split(",");  // split each data into array of strings based on ','
                String title = data[0];
                String author = data[1];
                String genre = data[2];
                double rating = Double.parseDouble(data[3]);
                String description = data[4];
                String url = data[5]; // for the new method for web reader
                books.add(new EBook(title, author, genre, rating, description, url));
            }
        }catch (IOException e){
            System.out.println("Error Occurred when reading the files: " + e.getMessage());
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

        ArrayList<Books> filteredBooks = new ArrayList<>();
        for (Books book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
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

}