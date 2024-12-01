
// creating a book class structure to store book details

// creating a simple book class to store and organize data that we will read from the CSV files.

// the main purpose of this class is to hold data that we read from the CSV files (can create subclasses later or Inherit)


public abstract class Books {

    private final String title;
    private final String author;
    private final String genre;
    private final String description;
    private final double rating;
    private final int year;
    // create the constructor

    public Books(String title, String author, String genre, double rating, String description, int year){

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.year = year;
    }

    // create getters to access data for the private instance variables

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getGenre(){
        return this.genre;
    }

    public String getDescription(){ return this.description; }

    public double getRating(){
        return  this.rating;
    }

    public int getYear() { return this.year; }

    // Overriding the toString() from the Object class to make a readable format when printing logs + debugging

    @Override
    public String toString(){
        return ("Title: " + title + '\n' +
                "Author: "+ author + '\n' +
                "Genre: " + genre + '\n' +
                "Description: "+  description + '\n' +
                "Year: " + year + '\n');
    }

    // to open the url to the book on click
    public abstract void readBook();
}
