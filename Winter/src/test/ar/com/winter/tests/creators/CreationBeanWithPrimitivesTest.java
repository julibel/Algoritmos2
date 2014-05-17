package ar.com.winter.tests.creators;

import junit.framework.Assert;

import org.junit.Test;

import ar.com.winter.Winter;
import ar.com.winter.tests.utils.Persona;

public class CreationBeanWithPrimitivesTest {

	Winter a = new Winter();
	
	@Test
	public void primitivaSinPropiedades(){
		Object obj = (Persona) Winter.creameUn(Persona.class);
		Assert.assertEquals(obj, new Persona()); 
	}
	
	@Test
	public void primitivaConUnaPropiedadConString(){
		Persona obj = new Persona();
		Winter a = new Winter();
		a.inyectaleA(obj,"nombre","pepe");
		Assert.assertEquals("pepe", obj.getNombre()); 
	}
	
	@Test
	public void primitivaConDosPropiedadesConStrings(){
		Persona obj = new Persona();
		a.inyectaleA(obj,"nombre","pepa");
		a.inyectaleA(obj,"direccion","Calle Falsa 123");
		Assert.assertEquals("pepa", obj.getNombre());
		Assert.assertEquals("Calle Falsa 123", obj.getDireccion());
	}
	
	@Test
	public void primitivaConUnaPropiedadConInteger(){
		Persona obj = new Persona();
		a.inyectaleA(obj,"dni","3030457");
		Assert.assertEquals(3030456, obj.getDni());
	}
	
	@Test
	public void inyeccionDePrimitivaConDosPropiedadesConDosIntegers(){
		Persona obj = new Persona();
		a.inyectaleA(obj,"dni","3030456");
		a.inyectaleA(obj,"edad","23");
		Assert.assertEquals(3030456, obj.getDni());
		Assert.assertEquals(23, obj.getEdad()));
	}
}
