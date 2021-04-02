package pl.wydzials.medialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wydzials.medialist.model.User;
import pl.wydzials.medialist.repository.UserRepository;

import java.security.Principal;

@Controller
public class MainController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(User user) {
        if(userRepository.findByUsername(user.getUsername()) == null) {
            user.setRoles("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        return "redirect:/login";
    }

    @ModelAttribute("username")
    public String getVersion(Principal principal) {
        if (principal != null)
            return principal.getName();
        return null;
    }
}
