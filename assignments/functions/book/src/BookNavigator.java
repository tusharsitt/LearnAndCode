public class BookNavigator {

    private final Book book;

    private int currentPageIndex = 0;

    public BookNavigator(Book book) {
        this.book = book;
    }

    public void turnPage() {
        currentPageIndex++;
    }

    public String getCurrentPageContent() {
        return "Content of page " + currentPageIndex;
    }
}