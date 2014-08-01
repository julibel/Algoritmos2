package jaframework.implementators;

import jaframework.def.JAFile;
import jaframework.def.JAIndex;
import jaframework.def.annotations.Index;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JAIndexImpl<T> extends JAFileImpl<T> implements JAIndex<T>
{

	List<String> orderedFileDataset;
	
	public JAIndexImpl(final Class<T> unaClase, String alias) throws IOException
	{
		super(unaClase);
		
		orderedFileDataset = super.fileDataset;
		Collections.sort(orderedFileDataset,
				new Comparator<String>()
				{
					@Override
					public int compare(String o1, String o2)
					{
                        try {
                            T uno = unaClase.newInstance();
                            T otro = unaClase.newInstance();

                            Utils.fill(o1, uno);
                            Utils.fill(o2, otro);

                            Index laAnnotation = unaClase.getAnnotation(Index.class);
                            String key = laAnnotation.key();

                            int posicionMas = key.indexOf('+');
                            int posicionMenos = key.indexOf('-');

                            List<String> listadoIndices = new ArrayList<>();
                            Matcher m = Pattern.compile("(\\-?\\w+)([\\+\\-]\\w+)*").matcher(key);
                            while(m.find())
                                if(m.group().length() != 0)
                                    listadoIndices.add(m.group());

                            for (String indice : listadoIndices) {
                                int resp = 0;
                                int multiplicador = 1;

                                if (indice.indexOf("-") == 0) { // ORDEN DESCENDENTE
                                    indice = indice.replace("-", "");
                                    multiplicador = -1;
                                } else {
                                    indice = indice.replace("+", "");
                                }


                                Class clazz = uno.getClass();
                                Field campo = clazz.getDeclaredField(indice);
                                campo.setAccessible(true);

                                Object elUno = campo.get(uno);
                                Object elOtro = campo.get(otro);

                                if (elUno instanceof Number) {
                                    resp = new BigDecimal(elUno.toString()).compareTo(new BigDecimal(elOtro.toString()));
                                } else {
                                    resp = elUno.toString().compareTo(elOtro.toString());
                                }

                                if (resp == 0)
                                    continue;
                                else
                                    return resp * multiplicador;
                            }
                            return 0;
                        } catch (Exception ex ){
                            throw new RuntimeException(ex);
                        }
					}
				}
		);
        System.out.println(orderedFileDataset);
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
