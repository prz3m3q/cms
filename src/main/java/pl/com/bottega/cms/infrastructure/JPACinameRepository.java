package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.repositories.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Component
public class JPACinameRepository implements CinemaRepository {

    private EntityManager entityManager;

    public JPACinameRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public boolean isOccupied(CreateCinemaCommand cmd) {
        return getCinema(cmd).isPresent();
    }

    private Optional<Cinema> getCinema(CreateCinemaCommand cmd) {
        try {
            Cinema cinema= (Cinema) entityManager.createQuery("FROM Cinema c WHERE c.name = :name AND c.city = :city")
                    .setParameter("name", cmd.getName() ).setParameter("city", cmd.getCity()).getSingleResult();
            return Optional.of(cinema);
        }
        catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
