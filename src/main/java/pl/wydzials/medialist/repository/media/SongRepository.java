package pl.wydzials.medialist.repository.media;

import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.media.Song;

@Repository
public interface SongRepository extends GenericMediumRepository<Song> {

}
