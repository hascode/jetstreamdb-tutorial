package com.hascode.tutorial;

import com.jetstreamdb.JetstreamDBInstance;
import java.io.Console;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArticleApp {

  private static final String USAGE = "Enter a command 'l': list, 'a': add, 'q': quit 'd': db info\n";

  private JetstreamDBInstance<RootData> db;

  public ArticleApp() {
    db = JetstreamDBInstance.New("my-articles-db ", RootData.class);
    Path databaseDirectory = Paths.get("").resolve("article-db");
    System.out.printf("creating database directory in %s%n", databaseDirectory.toAbsolutePath());
    db.configuration().properties().setStorageDirectory(databaseDirectory.toFile());
  }

  public static void main(String[] args) {
    new ArticleApp().run();
  }

  public void run() {
    prompt();
  }

  private void prompt() {
    Console console = System.console();
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
        prompt();
        break;
      case "a":
        System.out.println("Please enter a title");
        String title = console.readLine();
        List<Article> update = db.root().getArticles();
        update.add(new Article(title));
        db.store(update);
        prompt();
        break;
      case "d":
        System.out.printf("basedir: %s, name: %s%n", db.configuration().getBaseDir(),
            db.configuration().name());
        prompt();
        break;
      default:
        System.out.println("invalid command");
        prompt();
    }
  }

}