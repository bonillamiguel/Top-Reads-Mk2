
// creating a book class structure to store book details

// creating a simple book class to store and organize data that we will read from the CSV files.

// the main purpose of this class is to hold data that we read from the CSV files (can create subclasses later or Inherit)


public abstract class Books {

    private final String title;
    private final String author;
    private final String genre;
    private final String description;
    private final double rating;
    private final String url;

    // create the constructor

    public Books(String title, String author, String genre, double rating, String description, String url){

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.url = url;
    }

    // create getters to access data for the private instance variables

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getGenre(){
        return genre;
    }

    public String getDescription(){
        return description;
    }

    public double getRating(){
        return  rating;
    }

    public String getUrl(){
        return  url;
    }

    // Overriding the toString() from the Object class to make a readable format when printing logs + debugging

    @Override
    public String toString(){
        return ("Title: " + title + '\n' +
                "Author: "+ author + '\n' +
                "Genre: " + genre + '\n' +
                "Description: "+  description + '\n');
    }

    // to open the url to the book on click
    public abstract void readBook();
}
