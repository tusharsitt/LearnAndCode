public class HtmlPrinter implements Printer{

    @Override
    public void printPage(String pageContent) {
        System.out.println("HtmlPrinter printing");
    }
}
