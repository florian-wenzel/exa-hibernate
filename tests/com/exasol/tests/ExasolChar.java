package com.exasol.tests;

import java.util.List;
import javax.persistence.EntityManager;


public class ExasolChar extends HibernateTest {

//private Session session;
private EntityManager manager;

	public ExasolChar(){
		this.manager = super.getManager();
	}
	
	public void testUpper(){
		 List<String> res = manager.createQuery("SELECT upper(title) FROM Event WHERE title='e2' ").getResultList();
		 assertTrue(res.get(0).equals("E2"));
	}
	
	public void testLtrim(){
		 List<String> res = manager.createQuery("SELECT ltrim(title) FROM Event WHERE title=' E4 ' ").getResultList();
		 assertTrue(res.get(0).equals("E4 "));
	}
	
	public void testRtrim(){
		 List<String> res = manager.createQuery("SELECT rtrim(title) FROM Event WHERE title=' E4 ' ").getResultList();
		 assertTrue(res.get(0).equals(" E4"));
	}
	
	public void testRpad(){
		 List<String> res = manager.createQuery("SELECT rpad(title,5,'X') FROM Event WHERE title='e2' ").getResultList();
		 System.out.println(res.get(0));
		 assertTrue(res.get(0).equals("e2XXX"));
	}
	
	public void testLength(){
		 List<Integer> res = manager.createQuery("SELECT length(title) FROM Event WHERE title='e2' ").getResultList();
		 assertTrue(res.get(0)==2);
	}
	
	public void testCharacterLength(){
		 List<Integer> res = manager.createQuery("SELECT character_length(title) FROM Event WHERE title='e2' ").getResultList();
		 assertTrue(res.get(0)==2);
	}
}
