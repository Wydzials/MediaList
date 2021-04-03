package pl.wydzials.medialist.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.Medium;

@Repository
@Primary
public interface MediumRepository extends GenericMediumRepository<Medium> {
}
