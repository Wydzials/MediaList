package pl.wydzials.medialist.repository;

import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.Game;

@Repository
public interface GameRepository extends GenericMediumRepository<Game> {

}
