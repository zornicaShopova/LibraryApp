package book.api.libplovediv;

public class BookInfo {
    String titleBook;
    String authorBook;
    String publishedDate;
    String rateBook;
    String url;
    String barcode;

    public BookInfo() {

    }

    public BookInfo(String titleBook, String authorBook) {
        this.titleBook = titleBook;
        this.authorBook = authorBook;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getRateBook() {
        return rateBook;
    }

    public void setRateBook(String rateBook) {
        this.rateBook = rateBook;
    }


}
