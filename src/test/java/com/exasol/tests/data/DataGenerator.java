package com.exasol.tests.data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DataGenerator {

	private static DataGenerator instance;
	
	
	private DataGenerator(){
		
	}
	
	
	public static List<Event> getEvents(){
		List<Event> results = new LinkedList<Event>();
		results.add(new Event("e1",new Date(117,4,19,5,5,5),50,10.5,180));
		results.add(new Event("e2",new Date(118,5,18,5,5,5),75,15.5,0));
		results.add(new Event("e3",new Date(117,4,19,4,4,4),100,-20.5,10));
		results.add(new Event(" E4 ",new Date(117,4,19,4,4,4),100,-20.5,10));
		return results;
	}
	
	
	
	
	public static DataGenerator getInstance(){
		if(instance==null){
			instance = new DataGenerator();
		}
		return instance;
	}
	
}
