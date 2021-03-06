package pl.wydzials.medialist.model.media;

import pl.wydzials.medialist.model.User;

import javax.persistence.Entity;

@Entity
public class Book extends Medium {

    private int pages;

    public Book(String title, int priority, int pages, int timeInMinutes, User user) {
        super(title, priority, timeInMinutes, user);
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
    public void edit(Medium medium) {
        super.edit(medium);
        if (medium instanceof Book) {
            Book book = (Book) medium;
            pages = book.pages;
        }
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
