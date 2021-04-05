package pl.wydzials.medialist.controller.media;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wydzials.medialist.model.media.Medium;
import pl.wydzials.medialist.model.User;
import pl.wydzials.medialist.repository.media.GenericMediumRepository;
import pl.wydzials.medialist.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

public abstract class MediumController<M extends Medium, R extends GenericMediumRepository<M>> {
    protected final R repository;
    protected final UserRepository userRepository;

    protected String className = "medium";

    public MediumController(R repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String all(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("media", repository.findAllByUserOrderByPriorityDesc(user));
        return className + "/" + className;
    }

    @GetMapping("/add")
    public String add() {
        return className + "/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute M medium, Principal principal) {
        medium.setUser(userRepository.findByUsername(principal.getName()));
        repository.save(medium);
        return "redirect:/user/" + className;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, Principal principal) {
        repository.deleteById(id);
        return "redirect:/user/" + className;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model, Principal principal) {
        Optional<M> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/user/" + className;
        }
        M medium = optional.get();
        if (medium.getUser().getUsername().equals(principal.getName())) {
            model.addAttribute("medium", medium);
            return className + "/edit";
        }
        return "redirect:/user/" + className;
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable long id, M editedMedium, Principal principal) {
        Optional<M> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/user/" + className;
        }
        M medium = optional.get();
        if (medium.getUser().getUsername().equals(principal.getName())) {
            medium.edit(editedMedium);
            repository.save(medium);
        }
        return "redirect:/user/" + className;
    }

    @ModelAttribute("username")
    public String getVersion(Principal principal) {
        return principal.getName();
    }
}
