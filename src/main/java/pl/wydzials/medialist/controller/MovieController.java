package pl.wydzials.medialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wydzials.medialist.model.Movie;
import pl.wydzials.medialist.repository.MovieRepository;
import pl.wydzials.medialist.repository.UserRepository;

@Controller
@RequestMapping("/user/movies")
public class MovieController extends MediumController<Movie, MovieRepository> {

    @Autowired
    public MovieController(MovieRepository repository, UserRepository userRepository) {
        super(repository, userRepository);
        this.className = "movies";
    }

}
