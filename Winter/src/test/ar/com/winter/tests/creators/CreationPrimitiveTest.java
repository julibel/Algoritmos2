package ar.com.winter.tests.creators;

import junit.framework.Assert;
import ar.com.winter.Winter;

import org.junit.Test;

public class CreationPrimitiveTest {

	@Test
	public void crearInteger(){
		Winter a = new Winter();
		Integer unInteger = a.creameUn(Integer.class,"10");
		Assert.assertTrue(10 == unInteger);
	}
	
	@Test
	public void crearIntegerMal(){
		Winter a = new Winter();
		Integer unInteger = a.creameUn(Integer.class,"10");
		Assert.assertFalse(11 == unInteger);
	}
	
	@Test
	public void crearDouble(){
		Winter a = new Winter();
		Double unDouble = a.creameUn(Double.class,"10");
		Assert.assertTrue(10d == unDouble);
	}
	
	@Test
	public void crearFloat(){
		Winter a = new Winter();
		Float unFloat = a.creameUn(Float.class,"10f");
		Assert.assertTrue(10f == unFloat);
	}
}

