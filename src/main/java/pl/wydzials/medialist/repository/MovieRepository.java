package pl.wydzials.medialist.repository;

import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.Movie;

@Repository
public interface MovieRepository extends GenericMediumRepository<Movie> {

}
