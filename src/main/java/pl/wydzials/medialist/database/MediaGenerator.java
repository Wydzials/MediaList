package pl.wydzials.medialist.database;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import pl.wydzials.medialist.model.User;
import pl.wydzials.medialist.model.media.*;

@Component
public class MediaGenerator {

    private final Faker faker = new Faker();

    private final String[] gamePlatforms =
            {"PC", "Xbox Series", "Xbox One", "PlayStation 5", "PlayStation 4", "Nintendo Switch", "Mobile"};

    private final String[] customTypes =
            {"Theatre play", "Live show", "Exhibition", "Festival", "LARP"};

    public Book generateBook(User user) {
        int pages = faker.number().numberBetween(100, 1001);
        int time = (int) (pages * 1.5);
        return new Book(title(), priority(), pages, time, user);
    }

    public Movie generateMovie(User user) {
        String country = faker.country().name();
        return new Movie(title(), priority(), country, time(60, 200), user);
    }

    public Game generateGame(User user) {
        String platform = gamePlatforms[faker.number().numberBetween(0, gamePlatforms.length)];
        return new Game(title(), priority(), platform, time(120, 2400), user);
    }

    public Song generateSong(User user) {
        String genre = faker.music().genre();
        return new Song(title(), priority(), genre, time(3, 7), user);
    }

    public Custom generateCustom(User user) {
        String type = customTypes[faker.number().numberBetween(0, customTypes.length)];
        return new Custom(title(), priority(), type, time(120, 600), user);
    }

    private String title() {
        return faker.book().title();
    }

    private int priority() {
        return faker.number().numberBetween(1, 6);
    }

    private int time(int min, int max) {
        return faker.number().numberBetween(min, max);
    }
}
