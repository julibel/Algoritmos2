package jaframework.implementators;

import jaframework.implementators.exceptions.Creador;

import java.lang.reflect.Field;

/**
 * Created by usuario on 15/06/14.
 */
public class Utils {
    public static <T> boolean fill(String linea, T record) {
        if (linea == null || linea.trim().equals(""))
            return false;

        String[] campos = linea.split(",");

        /*
        0 -> 0010
        1 -> Pedro
        2 -> 1991/08/23
         */

        int pos = 0;

     
        for (Field field : record.getClass().getDeclaredFields()) {

            Class type = field.getType();
            String name = field.getName();

            jaframework.def.annotations.Field annotations = field.getAnnotation(jaframework.def.annotations.Field.class);
            if (annotations == null)
                continue;

            fillField(record, field, campos[pos].substring(0,Math.min(annotations.size(),campos[pos].length())));
            pos++;
        }
        return true;
    }

    private static <T> void fillField(T record, Field field, String value) {
        Object leoComparin = Creador.creameUn(field.getType(), value);
        try {
            field.setAccessible(true); // Magia para entrar a donde no fuiste invitado.
            field.set(record, leoComparin);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static <T> String getAlias(Class<T> fileWrapper) {
        return  fileWrapper.getAnnotation(jaframework.def.annotations.File.class).alias();
    }

    public static <T> String encode(T record) throws IllegalAccessException {
        StringBuffer sbuf = new StringBuffer();
        for (Field field : record.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Class type = field.getType();
            String name = field.getName();

            jaframework.def.annotations.Field annotations = field.getAnnotation(jaframework.def.annotations.Field.class);
            if (annotations == null)
                continue;

            sbuf.append(Creador.encodeameUn(field.get(record), annotations.size()));
            sbuf.append(",");
        }

        sbuf.deleteCharAt(sbuf.length()-1);
        return sbuf.toString();
    }
}
