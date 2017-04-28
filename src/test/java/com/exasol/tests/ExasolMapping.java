package com.exasol.tests;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;

import com.exasol.tests.data.Event;
import com.exasol.tests.data.EventDetails;
import com.exasol.tests.data.Person;

public class ExasolMapping extends HibernateTest{

	private EntityManager manager;	

	public ExasolMapping(){
		this.manager = super.getManager();

	}
	

	public void testDeleteEntity(){
		List<Long> res1 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		//delete first event
		Event e1 = manager.find(Event.class, res1.get(0));
		manager.getTransaction().begin();
		manager.remove(e1);
		manager.getTransaction().commit();
		//query all events in db
		List<Long> res = manager.createQuery("SELECT id FROM Event").getResultList();
		assertTrue(!res.contains(new Long(1)));
	}
	
	public void testUpdateEntity(){
		List<Long> res1 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		//delete first event
		Event e1 = manager.find(Event.class, res1.get(0));
		manager.getTransaction().begin();
		int att_old = e1.getAttendees();
		e1.setAttendees(att_old+100);
		manager.getTransaction().commit();
		//query all events in db
		List<Integer> res = manager.createQuery("SELECT attendees FROM Event where title='e1'").getResultList();
		assertTrue(res.get(0).intValue()==(att_old+100));
	}
	
	
	public void testOneToOneCreate(){
		List<Long> res1 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		Event e1 = manager.find(Event.class, res1.get(0));
		manager.getTransaction().begin();
		e1.setDetails(new EventDetails("6th street", true));
		manager.getTransaction().commit();
		List<Integer> res = manager.createQuery("SELECT COUNT(*) FROM EventDetails").getResultList();
		assertTrue(res.get(0).intValue()>0);
	}
	
	public void testOneToOneCascade(){
		List<Long> res1 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		Event e1 = manager.find(Event.class, res1.get(0));
		manager.getTransaction().begin();
		e1.setDetails(new EventDetails("6th street", true));
		manager.getTransaction().commit();
		List<Integer> res = manager.createQuery("SELECT COUNT(*) FROM EventDetails").getResultList();
		assertTrue(res.get(0).intValue()>0);
		manager.getTransaction().begin();
		manager.remove(e1);
		manager.getTransaction().commit();
		List<Integer> res2 = manager.createQuery("SELECT COUNT(*) FROM EventDetails").getResultList();
		assertTrue(res2.get(0).intValue()==0);
	}
	
	public void testManyToManyCreate(){
		List<Long> res1 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		Event e1 = manager.find(Event.class, res1.get(0));
		manager.getTransaction().begin();
		List<Person> plist = new LinkedList<Person>();
		plist.add(new Person("Jane Doe", 23));
		e1.setPerson(plist);
		manager.getTransaction().commit();
		List<Integer> res = manager.createQuery("SELECT COUNT(*) FROM Person").getResultList();
		assertTrue(res.get(0).intValue()>0);
		List<BigInteger> res2 = manager.createNativeQuery("SELECT COUNT(*) FROM TEST.EVENT_PERSON").getResultList();
		assertTrue(res2.get(0).intValue()>0);
	}
	
	public void testManyToManyCascade(){
		List<Long> res1 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		Event e1 = manager.find(Event.class, res1.get(0));
		manager.getTransaction().begin();
		List<Person> plist = new LinkedList<Person>();
		plist.add(new Person("Jane Doe", 23));
		e1.setPerson(plist);
		manager.getTransaction().commit();
		List<Integer> res = manager.createQuery("SELECT COUNT(*) FROM Person").getResultList();
		assertTrue(res.get(0).intValue()>0);
		List<BigInteger> res2 = manager.createNativeQuery("SELECT COUNT(*) FROM TEST.EVENT_PERSON").getResultList();
		assertTrue(res2.get(0).intValue()>0);
		manager.getTransaction().begin();
		manager.remove(e1);
		manager.getTransaction().commit();
		List<Integer> res3 = manager.createQuery("SELECT COUNT(*) FROM Person").getResultList();
		assertTrue(res3.get(0).intValue()==0);
		List<BigInteger> res4 = manager.createNativeQuery("SELECT COUNT(*) FROM TEST.EVENT_PERSON").getResultList();
		assertTrue(res4.get(0).intValue()==0);
	}
	
	public void testManyToManyJoin(){
		List<Long> res1 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		Event e1 = manager.find(Event.class, res1.get(0));
		manager.getTransaction().begin();
		List<Person> plist = new LinkedList<Person>();
		plist.add(new Person("Jane Doe", 23));
		e1.setPerson(plist);
		manager.getTransaction().commit();
		manager.getTransaction().begin();
		List<Long> res2 = manager.createQuery("SELECT MIN(id) FROM Event").getResultList();
		Event e2 = manager.find(Event.class, res2.get(0));
		Person p = e2.getPerson().get(0); 
		manager.getTransaction().commit();
		assertTrue(p.getAge()==23);
	}
	
}
