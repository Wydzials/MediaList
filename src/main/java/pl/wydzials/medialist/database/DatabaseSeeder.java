package pl.wydzials.medialist.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wydzials.medialist.model.Book;
import pl.wydzials.medialist.model.Medium;
import pl.wydzials.medialist.model.User;
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
    private final MediaGenerator mediaGenerator;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository, MediumRepository mediumRepository,
                          PasswordEncoder passwordEncoder, MediaGenerator mediaGenerator) {
        this.userRepository = userRepository;
        this.mediumRepository = mediumRepository;
        this.passwordEncoder = passwordEncoder;
        this.mediaGenerator = mediaGenerator;
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
            media.add(mediaGenerator.generateBook(szymon));
            media.add(mediaGenerator.generateGame(szymon));
        }
        for (int i = 0; i < 45; i++) {
            media.add(mediaGenerator.generateMovie(szymon));
        }
        for (int i = 0; i < 62; i++) {
            media.add(mediaGenerator.generateSong(szymon));
        }

        mediumRepository.saveAll(media);
    }
}
