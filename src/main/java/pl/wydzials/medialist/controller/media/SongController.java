package pl.wydzials.medialist.controller.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wydzials.medialist.model.media.Song;
import pl.wydzials.medialist.repository.UserRepository;
import pl.wydzials.medialist.repository.media.SongRepository;

@Controller
@RequestMapping("user/songs")
public class SongController extends MediumController<Song, SongRepository> {

    @Autowired
    public SongController(SongRepository repository, UserRepository userRepository) {
        super(repository, userRepository);
        this.className = "songs";
    }
}
