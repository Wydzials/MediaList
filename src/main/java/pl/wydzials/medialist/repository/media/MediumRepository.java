package pl.wydzials.medialist.repository.media;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.wydzials.medialist.model.media.Medium;

@Repository
@Primary
public interface MediumRepository extends GenericMediumRepository<Medium> {
}
