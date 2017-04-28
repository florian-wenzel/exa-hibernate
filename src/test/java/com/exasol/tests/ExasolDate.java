package com.exasol.tests;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;






public class ExasolDate extends HibernateTest{


private EntityManager manager;	

	public ExasolDate(){
		this.manager = super.getManager();
	}
	
	
	
	public void testAddYears(){
		List<Date> res = manager.createQuery("SELECT add_years(date,1) FROM Event WHERE title='e1'").getResultList();
		Date compare = new Date(118,4,19,5,5,5);
		 assertTrue(res.get(0).compareTo(compare)==0);
	}
	
	public void testAddMonths(){
		List<Date> res = manager.createQuery("SELECT add_months(date,1) FROM Event WHERE title='e1'").getResultList();
		Date compare = new Date(117,5,19,5,5,5);
		 assertTrue(res.get(0).compareTo(compare)==0);
	}
	
	public void testAddDays(){
		List<Date> res = manager.createQuery("SELECT add_days(date,1) FROM Event WHERE title='e1'").getResultList();
		Date compare = new Date(117,4,20,5,5,5);
		 assertTrue(res.get(0).compareTo(compare)==0);
	}
	
	public void testAddHours(){
		List<Date> res = manager.createQuery("SELECT add_hours(date,1) FROM Event WHERE title='e1'").getResultList();
		Date compare = new Date(117,4,19,6,5,5);
		 assertTrue(res.get(0).compareTo(compare)==0);
	}

	public void testAddMinutes(){
		List<Date> res = manager.createQuery("SELECT add_minutes(date,1) FROM Event WHERE title='e1'").getResultList();
		Date compare = new Date(117,4,19,5,6,5);
		 assertTrue(res.get(0).compareTo(compare)==0);
	}

	public void testAddSeconds(){
		List<Date> res = manager.createQuery("SELECT add_seconds(date,1) FROM Event WHERE title='e1'").getResultList();
		Date compare = new Date(117,4,19,5,5,6);
		assertTrue(res.get(0).compareTo(compare)==0);
	}
	

	public void testYearsBetween(){
		List<Double> res = manager.createQuery("SELECT years_between('2018-5-19',date) FROM Event WHERE title='e1'").getResultList();
		assertTrue(res.get(0)==1);
	}
	
	public void testMonthsBetween(){
		List<Double> res = manager.createQuery("SELECT months_between('2017-6-19',date) FROM Event WHERE title='e1'").getResultList();
		assertTrue(res.get(0)==1);
	}
	
	public void testDaysBetween(){
		List<Double> res = manager.createQuery("SELECT days_between('2017-5-20',date) FROM Event WHERE title='e1'").getResultList();
		System.out.println("Days:"+res.get(0));
		assertTrue(res.get(0)==1.0);
	}
	
	
	public void testHoursBetween(){
		List<Double> res = manager.createQuery("SELECT hours_between('2017-5-19 06:05:05',date) FROM Event WHERE title='e1'").getResultList();
		assertTrue(res.get(0)==1);
	}
		
	public void testMinutesBetween(){
		List<Double> res = manager.createQuery("SELECT minutes_between('2017-5-19 05:06:05',date) FROM Event WHERE title='e1'").getResultList();
		assertTrue(res.get(0)==1);
	}
	
	public void testSecondsBetween(){
		List<Double> res = manager.createQuery("SELECT seconds_between('2017-5-19 05:05:06',date) FROM Event WHERE title='e1'").getResultList();
		assertTrue(res.get(0)==1);
	}
	

	
	
}








