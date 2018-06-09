package com.hascode.tutorial;

public class Article {

  private final String title;

  public Article(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Article{");
    sb.append("title='").append(title).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
