public class PlainTextPrinter implements  Printer {

    @Override
    public void printPage(String pageContent) {
        System.out.println("PlainTextPrinter printing");
    }
}

