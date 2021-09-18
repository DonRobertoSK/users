package sk.skog.users.service;

import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Service for manipulating with database object (entities).
 * 
 * @author Robert Kristofic
 */
public class PersistenceService {
	private static final String PERSISTENCE_UNIT_NAME = "sample";
	private static PersistenceService persistenceService;
	private static EntityManagerFactory entityManagerFactory;
	
	private PersistenceService() {
	}
	
	public static PersistenceService getInstance() {
		if (persistenceService == null) {
			persistenceService = new PersistenceService();
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return persistenceService;
	}
	
	public static void releaseResources() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
	
	public <T> T add(T object) {
		EntityManager entityManager = getEntityManager();
		return doInTransaction(entityManager, () -> {
			entityManager.persist(object);
			return object;
		});
	}
	
	public void deleteAll(Class<?> entityClass) {
		EntityManager entityManager = getEntityManager();
		doInTransaction(entityManager, () -> {
			entityManager
				.createQuery(String.format("delete from %s", entityClass.getName()))
				.executeUpdate();
			return null;
		});
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> entityClass) {
		EntityManager entityManager = getEntityManager();
		return doInTransaction(entityManager, () ->
			entityManager
				.createQuery(String.format("select x from %s x", entityClass.getName()))
				.getResultList());
	}
	
	private static <T> T doInTransaction(EntityManager entityManager, Supplier<T> supplier) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			T result = supplier.get();
			transaction.commit();
			return result;
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	

}
