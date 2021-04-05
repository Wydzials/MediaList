package pl.wydzials.medialist.model.media;

import pl.wydzials.medialist.model.User;

import javax.persistence.Entity;

@Entity
public class Movie extends Medium {

    private String country;

    public Movie(String title, int priority, String country, int timeInMinutes, User user) {
        super(title, priority, timeInMinutes, user);
        this.country = country;
    }

    public Movie() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public void edit(Medium medium) {
        super.edit(medium);
        if (medium instanceof Movie) {
            Movie movie = (Movie) medium;
            country = movie.country;
        }
    }
}
