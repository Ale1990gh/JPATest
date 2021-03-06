package it.dstech.jpatest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ServicesCrud {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ServicesCrud(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	public void jpaCreate(Object obj) {
		em.getTransaction().begin();
		em.persist(obj); //rende persistente un ogg sul db
		em.getTransaction().commit();
		em.detach(obj);
	}

	public Query jpaRead(String query) {
		return em.createQuery(query);
	}

	public void jpaUpdate(Object obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		em.detach(obj);//chiudi
	}

	public void jpaDelete(Object obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	public void closeLogicaJPA() { //chiude la logica di connessione al db
		em.close();
		emf.close();
	}
}
