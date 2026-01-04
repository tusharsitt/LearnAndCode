package org.example;

public class App 
{
    public static void main( String[] args )
    {
        IApiClient client = new TumblrApiClient();
        IParser parser = new TumblrJsonParser();
        IView view = new ConsoleView();

        TumblrAppController app = new TumblrAppController(client, parser, view);

        // Start App
        app.run();
    }
}
