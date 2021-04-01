package pl.wydzials.medialist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    public List<Book> findAllByOrderByPriorityDesc();

}
