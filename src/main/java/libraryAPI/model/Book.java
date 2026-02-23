package libraryAPI.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private Integer published_year;
    private String genre;

    public Book() {

    }

    public Book(String title, String author, Integer published_year, String genre) {
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.genre = genre;
    }

    public Book(int id, String title, String author, Integer published_year, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.genre = genre;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublished_year() {
        return published_year;
    }

    public void setPublished_year(Integer published_year) {
        this.published_year = published_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
