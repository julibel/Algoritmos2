package ar.com.winter.tests.creators;

import junit.framework.Assert;

import org.junit.Test;

import ar.com.winter.tests.utils.Persona;

public class CreationBeanEnterprise {

	@Test
	public void inyeccionDeUnaFecha(){
		Persona obj = new Persona();
		Winter.inyectaleA(obj,"fechaNacimiento","24/10/1988");
		Assert.assertEquals("???", obj.fechaNacimiento());
	}
	
}
