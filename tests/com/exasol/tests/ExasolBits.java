package com.exasol.tests;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;

/**
 * bit_and, bit_or, bit_xor
 * @author fw
 *
 */
public class ExasolBits extends HibernateTest{

//private Session session;
	private EntityManager manager;

	public ExasolBits(){
		this.manager = super.getManager();
	}
	
	public void testBitAnd(){
		 List<BigInteger> res = manager.createQuery("SELECT bit_and(degree, 3) FROM Event where title='e3'").getResultList();
		 assertTrue(res.get(0).compareTo(BigInteger.valueOf(2)) == 0);
	}
	
	public void testBitOr(){
		 List<BigInteger> res = manager.createQuery("SELECT bit_or(degree, 3) FROM Event where title='e3'").getResultList();
		 assertTrue(res.get(0).compareTo(BigInteger.valueOf(11)) == 0);
	}
	
	public void testBitXor(){
		 List<BigInteger> res = manager.createQuery("SELECT bit_xor(degree, 3) FROM Event where title='e3'").getResultList();
		 assertTrue(res.get(0).compareTo(BigInteger.valueOf(9)) == 0);
	}
	
	public void testBitNot(){
		 List<BigInteger> res = manager.createQuery("SELECT bit_not(degree) FROM Event where title='e3'").getResultList();
		 assertTrue(res.get(0).compareTo(new BigInteger("18446744073709551605")) == 0);
	}
	
	public void testBitCheck(){
		 List<Boolean> res = manager.createQuery("SELECT bit_check(degree,1) FROM Event where title='e3'").getResultList();
		 assertTrue(res.get(0));
	}
	
	public void testBitSet(){
		 List<BigInteger> res = manager.createQuery("SELECT bit_set(degree,2) FROM Event where title='e3'").getResultList();
		 assertTrue(res.get(0).compareTo(BigInteger.valueOf(14))==0);
	}
}
