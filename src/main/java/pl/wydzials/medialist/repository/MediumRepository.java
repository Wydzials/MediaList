package pl.wydzials.medialist.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.Medium;
import pl.wydzials.medialist.model.User;

import java.util.List;

@Repository
@Primary
public interface MediumRepository<M extends Medium> extends PagingAndSortingRepository<M, Long> {

    List<M> findAllByUserOrderByPriorityDesc(User user);
}
