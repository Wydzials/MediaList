package pl.wydzials.medialist.model;

import javax.persistence.Entity;

@Entity
public class Song extends Medium {

    private String genre;

    public Song(String title, int priority, String genre, int timeInMinutes, User user) {
        super(title, priority, timeInMinutes, user);
        this.genre = genre;
    }

    public Song() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void edit(Medium medium) {
        super.edit(medium);
        if (medium instanceof Song) {
            Song song = (Song) medium;
            genre = song.genre;
        }
    }
}
