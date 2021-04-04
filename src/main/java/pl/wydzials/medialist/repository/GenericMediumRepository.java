package pl.wydzials.medialist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wydzials.medialist.model.Medium;
import pl.wydzials.medialist.model.User;

import java.util.List;

public interface GenericMediumRepository<M extends Medium> extends PagingAndSortingRepository<M, Long> {

    List<M> findAllByUserOrderByPriorityDesc(User user);
}