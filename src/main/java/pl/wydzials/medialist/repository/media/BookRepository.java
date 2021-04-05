package pl.wydzials.medialist.repository.media;

import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.media.Book;

@Repository
public interface BookRepository extends GenericMediumRepository<Book> {

}
