package com.hascode.tutorial;

import com.jetstreamdb.JetstreamDBInstance;
import java.io.Console;
import java.util.List;

public class ArticleApp {

  private static final String USAGE = "Enter a command 'l': list, 'a': add, 'q': quit\n";

  private JetstreamDBInstance<RootData> db = JetstreamDBInstance.New("my-articles-db ", RootData.class);

  public static void main(String[] args) {
    new ArticleApp().run();
  }

  public void run() {

    Console console = System.console();
    prompt(console);
  }

  private void prompt(Console console) {
    System.out.println(USAGE);
    String command = console.readLine();
    switch (command) {
      case "q":
        System.out.println("quitting...");
        System.exit(1);
        break;
      case "l":
        List<Article> articles = db.root().getArticles();
        System.out.printf("%d articles found:%n", articles.size());
        articles.forEach(System.out::println);
        prompt(console);
        break;
      case "a":
        System.out.println("Please enter a title");
        String title = console.readLine();
        List<Article> update = db.root().getArticles();
        update.add(new Article(title));
        db.store(update);
        prompt(console);
        break;
      default:
        System.out.println("invalid command");
        prompt(console);
    }
  }

}