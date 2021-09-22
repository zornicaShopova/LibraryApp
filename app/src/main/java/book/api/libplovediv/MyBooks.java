package book.api.libplovediv;
//reservations
public class MyBooks {
    String titleBook;
    String authorBook;
    String pickupDate;
    String returnDate;

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

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
