package com.exasol.tests;

import java.util.List;
import javax.persistence.EntityManager;



public class ExasolMath extends HibernateTest{

	private EntityManager manager;

	public ExasolMath(){
		this.manager = super.getManager();
	}
	
	
	public void testAvg(){
		 List<Double> res = manager.createQuery("SELECT avg(attendees) FROM Event").getResultList();
		 assertTrue(res.get(0) == 81.25);
	}
	
	public void testAbs(){
		 List<Double> res = manager.createQuery("SELECT abs(price) FROM Event WHERE title='e3' ").getResultList();
		 assertTrue(res.get(0) == 20.5);
	}
	
	
	public void testCeil(){
		 List<Double> res = manager.createQuery("SELECT ceil(price) FROM Event WHERE title='e1' ").getResultList();
		 assertTrue(res.get(0) == 11);
	}
	
	public void testFloor(){
		 List<Double> res = manager.createQuery("SELECT floor(price) FROM Event WHERE title='e1' ").getResultList();
		 assertTrue(res.get(0) == 10);
	}
	
	public void testMax(){
		 List<Integer> res = manager.createQuery("SELECT max(attendees) FROM Event ").getResultList();
		 assertTrue(res.get(0) == 100);
	}

	public void testMin(){
		 List<Integer> res = manager.createQuery("SELECT min(attendees) FROM Event ").getResultList();
		 assertTrue(res.get(0) == 50);
	}
	
	public void testSum(){
		 List<Integer> res = manager.createQuery("SELECT sum(attendees) FROM Event ").getResultList();
		 assertTrue(res.get(0) == 325);
	}

	public void testSqrt(){
		 List<Double> res = manager.createQuery("SELECT sqrt(attendees) FROM Event WHERE title='e3' ").getResultList();
		 assertTrue(res.get(0) == 10);
	}
	
	public void testSin(){
		 List<Double> res = manager.createQuery("SELECT sin(degree) FROM Event WHERE title='e2' ").getResultList();
		 assertTrue(res.get(0) == 0);
	}

	public void testCos(){
		 List<Double> res = manager.createQuery("SELECT cos(degree) FROM Event WHERE title='e2' ").getResultList();
		 assertTrue(res.get(0) == 1);
	}
	
		
	public void testTan(){
		 List<Double> res = manager.createQuery("SELECT tan(degree) FROM Event WHERE title='e2' ").getResultList();
		 assertTrue(res.get(0) == 0);
	}

	
	public void testSign(){
		 List<Integer> res = manager.createQuery("SELECT sign(price) FROM Event WHERE title='e3' ").getResultList();
		 assertTrue(res.get(0) == -1);
	}
	
	public void testLog(){
		 List<Double> res = manager.createQuery("SELECT log(degree,10) FROM Event WHERE title='e3' ").getResultList();
		 assertTrue(res.get(0) == 1);
	}
	
	public void testMod(){
		 List<Integer> res = manager.createQuery("SELECT mod(degree,5) FROM Event WHERE title='e3' ").getResultList();
		 assertTrue(res.get(0) == 0);
	}
	

	
	

	
	
}
