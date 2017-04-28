package com.exasol.tests;


import java.util.List;
import java.util.ListIterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.exasol.tests.data.DataGenerator;
import com.exasol.tests.data.Event;

import junit.framework.TestCase;



public class HibernateTest extends TestCase{

	private static EntityManagerFactory sessionFactory;

	private static EntityManagerFactory buildSessionFactory() {
		try {
			sessionFactory = Persistence.createEntityManagerFactory("org.hibernate.exasol.jpa");	
			return sessionFactory;
			}
		catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
			}
		}
	
	public static EntityManagerFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
		return sessionFactory;
		}
	
	
	@Override
	/**
	 * create dataset in EXASOL via Hibernate
	 */
	protected void setUp(){
		List<Event> data = DataGenerator.getEvents();
		EntityManager manager = getSessionFactory().createEntityManager();
		manager.getTransaction().begin();
		ListIterator<Event> it = data.listIterator();
		while(it.hasNext()){
			manager.persist(it.next());
		}
		manager.getTransaction().commit();
		manager.close();	
	}
	
	@Override
	/**
	 * Do nothing, data is erased at next startup
	 */
	protected void tearDown(){
		EntityManager manager = getSessionFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.createQuery("delete from Event").executeUpdate();
		manager.createQuery("delete from EventDetails").executeUpdate();
		manager.getTransaction().commit();
		manager.close();
	}
	
	protected EntityManager getManager(){
		return getSessionFactory().createEntityManager();
	}
	
	
	
}
