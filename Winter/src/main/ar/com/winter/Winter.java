package ar.com.winter;

import java.util.HashMap;
import java.util.Map;

import ar.com.winter.tests.utils.Persona;

public class Winter {

	Map<Class<?>, Creator<?>> creators = new HashMap<Class<?>, Creator<?>>(){
	{
		this.put(Integer.class, new Creator<Integer>(){
			@Override
			public Integer create(String string) {
				return Integer.parseInt(string);
			}
		});
		this.put(Double.class, new Creator<Double>(){
			@Override
			public Double create(String string) {
				return Double.parseDouble(string);
			}
		});
		this.put(Float.class, new Creator<Float>(){
			@Override
			public Float create(String string) {
				return Float.parseFloat(string);
			}
		});
	}};
	
	public <T> T creameUn(Class<T> class1, String string) {
		return (T) creators.get(class1).create(string);
	}

	/**
	 * 
	 * Para hacer esto hay que usar reflection (O Intrsopeccion, que para hacer esto es más facil):
	 *  - Hay que revisar todos los métodos que tiene.
	 *  - Obtener el getter/setter cuyo nombre sea el valor de "propiedad"
	 *  - llamarlo y setearle el valor "valor".
	 * 
	 * @param obj
	 * @param string
	 * @param string2
	 */
	public void inyectaleA(Persona obj, String propiedad, String valor) {
		
	}

	/**
	 * 
	 * Para hacer esto hay que usar reflection.
	 *  - Hay que obtener el constructor por defecto de la clase.
	 *  - Hay que llamar el constructor
	 *  - Hay que retornarlo.  
	 * 
	 * @param class1
	 * @return
	 */
	public <T> T creameUn(Class<T> class1) {
		return null;
	}
	
	
	

		
}
