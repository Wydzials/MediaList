package pl.wydzials.medialist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wydzials.medialist.model.Book;
import pl.wydzials.medialist.model.User;
import pl.wydzials.medialist.repository.BookRepository;
import pl.wydzials.medialist.repository.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/user/books")
public class BookController {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String books(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("books", bookRepository.findAllByUserOrderByPriorityDesc(user));
        return "books";
    }

    @GetMapping("/add")
    public String addBookGet(Model model) {
        return "add_book";
    }

    @PostMapping("/add")
    public String addBookPost(@ModelAttribute Book book, Principal principal) {
        book.setUser(userRepository.findByUsername(principal.getName()));
        bookRepository.save(book);
        return "redirect:/user/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
        return "redirect:/user/books";
    }

    @ModelAttribute("username")
    public String getVersion(Principal principal) {
        return principal.getName();
    }
}
