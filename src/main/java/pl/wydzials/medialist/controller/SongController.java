package pl.wydzials.medialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wydzials.medialist.model.Song;
import pl.wydzials.medialist.repository.SongRepository;
import pl.wydzials.medialist.repository.UserRepository;

@Controller
@RequestMapping("user/songs")
public class SongController extends MediumController<Song, SongRepository> {

    @Autowired
    public SongController(SongRepository repository, UserRepository userRepository) {
        super(repository, userRepository);
        this.className = "songs";
    }
}
