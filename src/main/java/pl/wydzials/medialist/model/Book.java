package pl.wydzials.medialist.model;

import javax.persistence.Entity;

@Entity
public class Book extends Medium {

    private int pages;

    public Book(String title, int priority, int pages) {
        super(title, priority);
        this.pages = pages;
    }

    public Book() {
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + "'" +
                ", created='" + getCreated() + "'" +
                ", pages=" + getPages() +
                '}';
    }
}
