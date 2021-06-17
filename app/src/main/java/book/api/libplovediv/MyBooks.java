package book.api.libplovediv;

public class MyBooks {
    String titleBook;
    String authorBook;

    public MyBooks() {

    }

    public MyBooks(String titleBook, String authorBook) {
        this.titleBook = titleBook;
        this.authorBook = authorBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(String authorBook) {
        this.authorBook = authorBook;
    }
}
