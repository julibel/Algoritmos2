package jaframework.implementators.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by usuario on 15/06/14.
 */
public class Creador {
    public interface Creator<T> {
        T create(String string);
    }
    public interface Encoder<T>{
        String encode(T value, int size);
    }


    public final static Map<Class<?>, Creator<?>> creators = new HashMap<Class<?>, Creator<?>>(){
        {
            this.put(String.class, new Creator<String>() {
                @Override
                public String create(String string) {
                    return string.trim();
                }
            });

            Creator integerCreator = new Creator<Integer>(){
                @Override
                public Integer create(String string) {
                    return Integer.parseInt(string);
                }
            };

            Creator doubleCreator = new Creator<Double>(){
                @Override
                public Double create(String string) {
                    return Double.parseDouble(string);
                }
            };

            Creator floatCreator = new Creator<Float>(){
                @Override
                public Float create(String string) {
                    return Float.parseFloat(string);
                }
            };

            Creator charCreator = new Creator<Character> () {
                @Override
                public Character create(String string) {
                    return string.charAt(0);
                }
            };

            this.put(Integer.class, integerCreator );
            this.put(int.class, integerCreator );


            this.put(Double.class, doubleCreator);
            this.put(double.class, doubleCreator);

            this.put(Float.class, floatCreator);
            this.put(float.class, floatCreator);

            this.put(Character.class, charCreator);
            this.put(char.class, charCreator);

        }};


    public final static Map<Class<?>, Encoder<?>> encoders = new HashMap<Class<?>, Encoder<?>>() {{

        Encoder<Object> tostringRightEncoder = new Encoder<Object>() {

            @Override
            public String encode(Object elem, int size) {
                StringBuffer sb = new StringBuffer();
                String value;

                if (elem == null)
                    value = "";
                else
                    value = elem.toString();

                if (value.length() > size)
                    return value.substring(0, size);
                else if (value.length() == size)
                    return value;

                sb.append(value);

                while (sb.length() < size) {
                    sb.append(" ");
                }

                return sb.toString();
            }
        };

        Encoder<Object> tostringLeftEncoder = new Encoder<Object>() {

            @Override
            public String encode(Object elem, int size) {
                StringBuffer sb = new StringBuffer();
                String value;

                if (elem == null)
                    value = "";
                else
                    value = elem.toString();



                if (value.length() > size)
                    return value.substring(0, size);
                else if (value.length() == size)
                    return value;

                while (sb.length() < size - value.length()) {
                    sb.append("0");
                }

                sb.append(value);

                return sb.toString();
            }
        };

        this.put(String.class,tostringRightEncoder);
        this.put(Integer.class,tostringLeftEncoder);
        this.put(int.class,tostringLeftEncoder);
        this.put(Double.class,tostringLeftEncoder);
        this.put(double.class,tostringLeftEncoder);
        this.put(Character.class,tostringLeftEncoder);
        this.put(char.class,tostringLeftEncoder);
        this.put(Float.class,tostringLeftEncoder);
        this.put(float.class,tostringLeftEncoder);
        this.put(Byte.class,tostringLeftEncoder);
        this.put(byte.class,tostringLeftEncoder);
    }};

    public static <T> T creameUn(Class<T> class1, String string) {
        return (T) creators.get(class1).create(string);
    }

    public static <T> String encodeameUn(T value, Integer size){
        Encoder encoder = encoders.get(value.getClass());
        return encoder.encode(value,size);
    }

}
