package pl.wydzials.medialist.repository;

import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.Song;

@Repository
public interface SongRepository extends GenericMediumRepository<Song> {

}
