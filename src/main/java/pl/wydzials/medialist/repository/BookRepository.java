package pl.wydzials.medialist.repository;

import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.Book;

@Repository
public interface BookRepository extends GenericMediumRepository<Book> {

}
