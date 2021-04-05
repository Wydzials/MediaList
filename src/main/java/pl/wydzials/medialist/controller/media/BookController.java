package pl.wydzials.medialist.controller.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wydzials.medialist.model.media.Book;
import pl.wydzials.medialist.repository.media.BookRepository;
import pl.wydzials.medialist.repository.UserRepository;

@Controller
@RequestMapping("/user/books")
public class BookController extends MediumController<Book, BookRepository> {

    @Autowired
    public BookController(BookRepository repository, UserRepository userRepository) {
        super(repository, userRepository);
        this.className = "books";
    }
}
