package pl.wydzials.medialist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wydzials.medialist.model.Book;
import pl.wydzials.medialist.repository.BookRepository;

import java.security.Principal;

@Controller
@RequestMapping("/user/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public String books(Model model) {
        model.addAttribute("books", bookRepository.findAllByOrderByPriorityDesc());
        return "books";
    }

    @GetMapping("/add")
    public String addBookGet(Model model) {
        return "add_book";
    }

    @PostMapping("/add")
    public String addBookPost(@ModelAttribute Book book) {
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
