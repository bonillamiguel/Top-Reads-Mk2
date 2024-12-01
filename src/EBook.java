// to add the readBook() feature

public class EBook extends Books {
    private final String url;
    public EBook(String title, String author, String genre, double rating, String description, int year, String url){
        super(title, author, genre, rating, description, year);
        this.url = url;
    }
    public String getUrl(){
        return  url;
    }


    // to read the book on its default browser
    @Override
    public void readBook(){
        try{
            java.awt.Desktop.getDesktop().browse(new java.net.URI(getUrl()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
