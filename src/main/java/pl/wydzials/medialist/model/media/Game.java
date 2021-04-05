package pl.wydzials.medialist.model.media;

import pl.wydzials.medialist.model.User;

import javax.persistence.Entity;

@Entity
public class Game extends Medium {

    private String platform;

    public Game(String title, int priority, String platform, int timeInMinutes, User user) {
        super(title, priority, timeInMinutes, user);
        this.platform = platform;
    }

    public Game() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public void edit(Medium medium) {
        super.edit(medium);
        if (medium instanceof Game) {
            Game game = (Game) medium;
            platform = game.platform;
        }
    }
}
