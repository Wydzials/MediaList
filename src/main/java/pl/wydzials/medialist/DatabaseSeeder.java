package pl.wydzials.medialist;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wydzials.medialist.model.*;
import pl.wydzials.medialist.repository.MediumRepository;
import pl.wydzials.medialist.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MediumRepository mediumRepository;

    private final PasswordEncoder passwordEncoder;
    private final Faker faker = new Faker();

    private final String[] gamePlatforms =
            {"PC", "Xbox Series", "Xbox One", "PlayStation 5", "PlayStation 4", "Nintendo Switch", "Mobile"};

    @Autowired
    public DatabaseSeeder(UserRepository userRepository, MediumRepository mediumRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mediumRepository = mediumRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        userRepository.deleteAll();

        User szymon = new User("szymon", passwordEncoder.encode("1"), "USER");
        User john = new User("john", passwordEncoder.encode("2"), "USER");
        userRepository.save(szymon);
        userRepository.save(john);

        Book b1 = new Book("The Way of Kings", 5, 1136, 1140, szymon);
        Book b2 = new Book("Harry Potter and the Philosopher's Stone", 2, 328, 400, szymon);
        Book b3 = new Book("Sapiens: A Brief History of Humankind", 3, 544, 550, szymon);

        List<Medium> media = new ArrayList<>(Arrays.asList(b1, b2, b3));

        for (int i = 0; i < 20; i++) {
            media.add(generateBook(szymon));
            media.add(generateMovie(szymon));
            media.add(generateGame(szymon));
        }
        mediumRepository.saveAll(media);
    }

    private Book generateBook(User user) {
        String title = faker.book().title();
        int pages = faker.number().numberBetween(100, 1001);
        int priority = faker.number().numberBetween(1, 6);
        int timeInMinutes = (int) (pages * 1.5);

        return new Book(title, priority, pages, timeInMinutes, user);
    }

    private Movie generateMovie(User user) {
        String title = faker.book().title(); // Unfortunately, Java Faker does not have any movie titles
        int priority = faker.number().numberBetween(1, 6);
        int timeInMinutes = faker.number().numberBetween(60, 200);
        String country = faker.country().name();

        return new Movie(title, priority, country, timeInMinutes, user);
    }

    private Game generateGame(User user) {
        String title = faker.book().title();
        int priority = faker.number().numberBetween(1, 6);
        String platform = gamePlatforms[faker.number().numberBetween(0, gamePlatforms.length)];
        int timeInMinutes = faker.number().numberBetween(120, 2400);

        return new Game(title, priority, platform, timeInMinutes, user);
    }
}
