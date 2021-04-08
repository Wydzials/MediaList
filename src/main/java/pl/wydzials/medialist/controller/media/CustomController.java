package pl.wydzials.medialist.controller.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wydzials.medialist.model.media.Custom;
import pl.wydzials.medialist.repository.UserRepository;
import pl.wydzials.medialist.repository.media.CustomRepository;

@Controller
@RequestMapping("user/custom")
public class CustomController extends MediumController<Custom, CustomRepository> {

    @Autowired
    public CustomController(CustomRepository repository, UserRepository userRepository) {
        super(repository, userRepository);
        this.className = "custom";
    }
}
