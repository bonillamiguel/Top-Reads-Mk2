import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.Button; // to set the style
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.ArrayList;


public class MainMenu extends Application{

    private BookManager bookManager;  // we will manage and include book features

    // this is the entry point for JavaFx UI
    @Override
    public void start(Stage primaryStage){
        // Initializing BookManager and load the data for this class
        bookManager = new BookManager();
        bookManager.loadBooks("booksData3.0.csv"); // path to load the books from Book manager method


        // for the heading / Title
        Label titelLabel = new Label("Top Reads");
        titelLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #3498db;");

        // Subtitle
        Label subtitleLabel = new Label("The best platform for discovering your next favorite book!");
        subtitleLabel.setStyle("-fx-font-size: 20px; -fx-font-wight: bold; -fx-text-fill: #1a73e8;");

        // Creating buttons for the menu
        Button searchByGenreButton = new Button("Search by Genre");
        Button viewCatalogButton = new Button("View Catalog");

        // Style buttons using tailwind

        searchByGenreButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        viewCatalogButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Add button actions on click or onAction

        searchByGenreButton.setOnAction(e -> showGenrePrompt());    // both methods will be implemented below
        viewCatalogButton.setOnAction(e-> showCatalog());

        // Add a small quote at the bottom
        Label quoteLabel = new Label("- Read, explore, and let your mind wander -");
        quoteLabel.setStyle("-fx-font-size: 15px; -fx-font-style: italic; -fx-text-fill: #555555;");

        // Create layout
        VBox layout = new VBox(20, titelLabel, subtitleLabel, searchByGenreButton, viewCatalogButton, quoteLabel);
        layout.setAlignment(Pos.CENTER);  // align and set position to the center
        layout.setStyle("-fx-background-color: #2c3e50;");  // set the background color of the window

        // Set up the Scene
        Scene scene = new Scene(layout, 800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Application");
        primaryStage.show();    // to make the Scene visible


    }

    // button methods for button actions above
    private void showGenrePrompt(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search by Genre");
        dialog.setHeaderText("""
                Enter a Genre to find the top 10 books:
                Available genre
                * Classic  * Fantasy   * Romance * Science Fiction * Historical * Mystery/Thriller""");

        dialog.setContentText("Genre:");

        dialog.showAndWait().ifPresent(genre -> {
            // get the top 10 books from the bookManager and display them
            ArrayList<Books> topBooks = bookManager.filterBooksByGenre(genre);  // goes to the implementation
            displayBooks(topBooks); // will define the method below
        });
    }


    // Creating the Catalog method
    private  void showCatalog(){
        // display all the books call the getBooks() from the implementation from Features
        ArrayList<Books> allBooks = bookManager.getBooks();
        displayBooks(allBooks);
    }


    // Implement display Books
    private void displayBooks(ArrayList<Books> books){

        Stage bookStage = new Stage();

        // create a VBox for the book buttons
        VBox layout = new VBox(10);     // spacing between items
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-padding: 20px");


        // create buttons for each book
        for (Books book: books){

            Button bookButton = new Button(book.getTitle() + " - " + book.getAuthor());
            bookButton.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
            // open book details when it's clicked
            bookButton.setOnAction(e -> showBookDetails(book));   // method implemented below openInWebView
            layout.getChildren().add(bookButton);

        }

        // wrap the VBox in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(layout);

        scrollPane.setContent(layout);  // set the VBox as content
        scrollPane.setFitToWidth(true); // make the content resizes match the ScrollPanes width
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);  // to add a vertical scroll bar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);   // to allow dragging to scroll
        scrollPane.setStyle("-fx-background-color: black;");  // set
        scrollPane.setPrefViewportHeight(200);

        // Debugging statements
        //System.out.println("Number of books: " + books.size());
        //System.out.println("ScrollPane content set: " + (scrollPane.getContent() != null));


        // create a scene and show the stage
        Scene scene = new Scene(scrollPane, 600, 400);
        bookStage.setScene(scene);
        bookStage.setTitle("Books");
        bookStage.show();
    }

    // when the user clicks on the URL calls this method
    // previously it was on displayBooks() it was added to the method below
    // to call after displaying book info like a card
    private void openBookInWebView(EBook book){
        Stage webStage = new Stage();
        WebView webView = new WebView();
        webView.getEngine().load(book.getUrl());

        Scene scene = new Scene(webView, 800, 600);
        webStage.setScene(scene);
        webStage.setTitle("Read Book - " + book.getTitle());
        webStage.show();
    }


    // create a method to show book details


    private void showBookDetails(Books book){

        //create a new stage for the book details


        Stage detailsStage = new Stage();
        detailsStage.setTitle("Book Details");

        /* OLD VERSION
        Label titleLabel = new Label("Title: " + book.getTitle());
        Label authorLabel = new Label("Author: " + book.getAuthor());
        Label genreLabel = new Label("Genre: " + book.getGenre());
        Label descriptionLabel = new Label("Description: " + book.getDescription());
        Label ratingLabel = new Label("Rating: " + book.getRating());

        // Style the labels
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        descriptionLabel.setWrapText(true); // Allow wrapping for long descriptions

        // Create a "Read Online" button
        Button readOnlineButton = new Button("Read Book");
        readOnlineButton.setOnAction(e -> openBookInWebView(book));

        // Layout for the book details
        VBox layout = new VBox(10, titleLabel, authorLabel, genreLabel, descriptionLabel, ratingLabel, readOnlineButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px;");
        */


        // NEW VERSION
        // Create labels with new style
        Label titleLabel = new Label("Title: " + book.getTitle());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label authorLabel = new Label("Author: " + book.getAuthor());
        authorLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label genreLabel = new Label("Genre: " + book.getGenre());
        genreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #3498db;");

        Label descriptionLabel = new Label("Description: " + book.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        descriptionLabel.setWrapText(true); // Wrap long text

        Label ratingLabel = new Label("Rating: " + book.getRating());
        ratingLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #27ae60;"); // Green color

        // Create a "Read Online" button
        Button readOnlineButton = new Button("Read Book");
        readOnlineButton.setStyle("-fx-font-size: 14px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-padding: 8px 16px;");
        readOnlineButton.setOnAction(e -> openBookInWebView((EBook) book));

        // Layout for the book details
        VBox layout = new VBox(10, titleLabel, authorLabel, genreLabel, descriptionLabel, ratingLabel, readOnlineButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px; -fx-background-color: #f7f7f7;"); // Light background

        // Create the scene and show the stage
        Scene scene = new Scene(layout, 400, 300);
        detailsStage.setScene(scene);
        detailsStage.show();
    }

    // starting point in the application calls the start() method
    public static void main(String[] args){
        launch(args);
    }
}
