package jaframework.implementators;

import jaframework.def.JAFile;
import jaframework.def.JAIndex;
import jaframework.def.annotations.Index;
import jaframework.def.annotations.Indexes;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JAIndexImpl<T> extends JAFileImpl<T> implements JAIndex<T>
{
    Class<T> unaClase;

	public JAIndexImpl(final Class<T> unaClase, String alias) throws IOException
	{
		super(unaClase);

        Index unaAnnotation = unaClase.getAnnotation(Index.class);


        if(unaAnnotation == null) { //Verifico si tiene Indexes
            Indexes ann = unaClase.getAnnotation(Indexes.class);
            Index[] value = ann.value();
            for(Index i : value) {
                if(i.alias().equals(alias))
                    unaAnnotation = i;
            }
        }

        final Index laAnnotation = unaAnnotation;
        final String key = laAnnotation.key();
        this.unaClase = unaClase;
		Collections.sort(super.fileDataset,
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

                        List<String> listadoIndices = new ArrayList<>();
                        Matcher m = Pattern.compile("^(\\-?\\w+)([\\+\\-]\\w+)*$").matcher(key);
                        m.find();

                        for(int i = 1; i <= m.groupCount(); i++)
                            listadoIndices.add(m.group(i));


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
	}

	@Override
	public JAFile<T> getFile()
	{
		return this;
	}

	@Override
	public void add(T key)
	{
        throw new NoEstaDocumentadoException();
	}

	@Override
	public String getKey()
	{
		return null;
	}

	@Override
	public int search(T toSearch)
	{
        try {
            int i = 0;
            for(String elem : super.fileDataset){

                T actual = unaClase.newInstance();
                Utils.fill(elem, actual);

                Field[] declaredFields = toSearch.getClass().getDeclaredFields();
                boolean saliPorCamposDistintos = false;
                for(Field field : declaredFields){
                    field.setAccessible(true);
                    Object campoDelObjetoQueRecibo = field.get(toSearch);
                    Object campoActual = field.get(actual);
                    if(campoDelObjetoQueRecibo == null ||
                       campoDelObjetoQueRecibo.equals(0) ||
                       campoDelObjetoQueRecibo.equals(0.0) ||
                       campoDelObjetoQueRecibo.equals('\u0000') )

                        continue;
                    else if (!campoDelObjetoQueRecibo.equals(campoActual)){
                        saliPorCamposDistintos = true;
                        break;
                    }
                }
                if(!saliPorCamposDistintos) {
                    return i;
                }
                i++;
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
		return -1;
	}

}
