package jaframework.implementators;

import jaframework.def.JAFile;
import jaframework.def.JAIndex;
import jaframework.def.annotations.Index;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JAIndexImpl<T> extends JAFileImpl<T> implements JAIndex<T>
{

	List<String> orderedFileDataset;
	
	public JAIndexImpl(final Class<T> unaClase, String alias) throws IOException
	{
		super(unaClase);
		
		orderedFileDataset = new ArrayList<String>();
		Collections.copy(orderedFileDataset,super.fileDataset);
		Collections.sort(orderedFileDataset,
				new Comparator<String>()
				{
					@Override
					public int compare(String o1, String o2)
					{
						T uno = null;
						T otro = null;
						try
						{
							uno = unaClase.newInstance();
							otro = unaClase.newInstance();
						}
						catch(InstantiationException|IllegalAccessException ex)
						{
							ex.printStackTrace();
						}
						
						Utils.fill(o1, uno);
						Utils.fill(o2, otro);
						
						Index laAnnotation = unaClase.getAnnotation(Index.class);
						String key = laAnnotation.key();
						
						int posicionMas = key.indexOf('+');
						int posicionMenos = key.indexOf('-');
						Pattern p = Pattern.compile("^(\\-?\\w+)([\\+\\-]\\w+)*$");
						Matcher m = p.matcher(key);
						List<String> listadoIndices = new ArrayList<String>();
						
						for(int i = 1; i < m.groupCount(); i++){
							listadoIndices.add(m.group(i));
						}
						
						for(String indice : listadoIndices){
							int resp = 0;
							int multiplicador = 1;
							
							if(indice.indexOf("-") == 0){ // ORDEN DESCENDENTE
								indice = indice.replace("-","");
								multiplicador = -1;
							} else {
								indice = indice.replace("+","");
							}
							
							Field campo = null;
							
							try
							{
								campo = uno.getClass().getField(indice);
							}
							catch(NoSuchFieldException|SecurityException ex)
							{
								ex.printStackTrace();
							}

							Object elUno;
							Object elOtro;
							try
							{
								elUno =campo.get(uno);
								elOtro = campo.get(otro);
							}
							catch(IllegalArgumentException|IllegalAccessException ex)
							{
								throw new RuntimeException();
							}
							
							
							if(elUno instanceof Number){
								resp = new BigDecimal(elUno.toString()).compareTo(new BigDecimal(elOtro.toString()));
							} else {
								resp = elUno.toString().compareTo(elOtro.toString());
							}

							if(resp == 0)
								continue;
							else
								return resp * multiplicador;
						}
						return 0;
					}
				}
		);
	}

	@Override
	public JAFile<T> getFile()
	{
		
		return null;
	}

	@Override
	public void add(T key)
	{

	}

	@Override
	public String getKey()
	{
		return null;
	}

	@Override
	public int search(T toSearch)
	{
		return 0;
	}

}
