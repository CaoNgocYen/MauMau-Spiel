package de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.DAO;

import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.export.Spiel;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.exceptions.DaoException;
import de.htwberlin.kbe.gruppe7.MauMauSpiel.spielverwaltung.exceptions.GameNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.Objects;

@Repository
public class SpielDaoImpl implements SpielDao {
    private EntityManagerFactory emf;

    @Autowired
    private EntityManager entityManager;

    private static Logger logger = LogManager.getLogger(SpielDaoImpl.class);

    @Override
    public Spiel findById(Long id) {
        try {
            Spiel game = entityManager.find(Spiel.class, id);
            if (Objects.isNull(game)) {
                logger.info("Game with ID %d is not found", id);
                throw new GameNotFoundException(String.format("Game with ID %d not found.", id));
            }
            logger.info("Game with ID %d is found", game.getId());
            return game;
        } catch (PersistenceException e) {
            logger.error("DaoException is thrown: %s", e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean findSpiel() {
        try {
            int number = ((Number) entityManager.createNamedQuery("Game.countAll").getSingleResult()).intValue();
            logger.info("%d saved Games are found", number);
            return number > 0;
        } catch (PersistenceException e) {
            logger.error("DaoException is thrown: %s", e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void saveSpiel(Spiel spiel) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(spiel);
            entityManager.getTransaction().commit();
            logger.info("Spiel mit ID {} wird gespeichert", spiel.getId());
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            logger.error("DaoException wird geworfen: {}", e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }


    @Override
    public void deleteSpiel(Spiel spiel) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(spiel));
            entityManager.getTransaction().commit();
            logger.info("Game with ID {} is deleted", spiel.getId());
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            logger.error("DaoException is thrown: {}", e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
