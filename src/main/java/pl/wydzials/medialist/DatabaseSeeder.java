package pl.wydzials.medialist;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wydzials.medialist.model.Book;
import pl.wydzials.medialist.model.User;
import pl.wydzials.medialist.repository.BookRepository;
import pl.wydzials.medialist.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final Faker faker = new Faker();

    @Autowired
    public DatabaseSeeder(BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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
        List<Book> books = new ArrayList<>(Arrays.asList(b1, b2, b3));

        for (int i = 0; i < 17; i++) {
            books.add(generateBook(szymon));
        }
        bookRepository.saveAll(books);
    }

    private Book generateBook(User user) {
        String title = faker.book().title();
        int pages = faker.number().numberBetween(100, 1201);
        int priority = faker.number().numberBetween(1, 6);
        int timeInMinutes = (int) (pages * 1.5);
        return new Book(title, priority, pages, timeInMinutes, user);
    }
}
