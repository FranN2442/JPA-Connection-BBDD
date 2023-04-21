package ejemplo.bbdd;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaService {
    
    private static JpaService instance;

    private EntityManagerFactory  entityManagerFactory;
    
    private JpaService(){

        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-demo-local");

    }

    public static synchronized JpaService getInstance() {

        return instance == null ? instance = new JpaService() : instance;
    }

    public void shutDown() {

        if (entityManagerFactory != null){
        
            entityManagerFactory.close();

        }
        
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public <T> T runInTransaction(Function<EntityManager, T> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        boolean success = false;
        try {
            T returnValue = function.apply(entityManager);
            success = true;
            return returnValue;
 
        } finally {
            if (success) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
    }

    


}
