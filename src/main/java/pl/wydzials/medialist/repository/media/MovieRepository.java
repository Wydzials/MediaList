package pl.wydzials.medialist.repository.media;

import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.media.Movie;

@Repository
public interface MovieRepository extends GenericMediumRepository<Movie> {

}
