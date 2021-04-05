package pl.wydzials.medialist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wydzials.medialist.model.Game;
import pl.wydzials.medialist.repository.GameRepository;
import pl.wydzials.medialist.repository.UserRepository;

@Controller
@RequestMapping("/user/games")
public class GameController extends MediumController<Game, GameRepository> {

    public GameController(GameRepository repository, UserRepository userRepository) {
        super(repository, userRepository);
        this.className = "games";
    }

}
