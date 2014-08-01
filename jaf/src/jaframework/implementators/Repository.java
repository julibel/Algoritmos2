package jaframework.implementators;

import jaframework.def.JAFile;
import jaframework.def.JAIndex;
import jaframework.def.JASession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by usuario on 15/06/14.
 */
public class Repository implements JASession{

    List<Class<?>> registeredWrappers = new ArrayList<Class<?>>();
    Map<String, Class<?>> byAlias = new HashMap<String,Class<?>>();

    public Repository(){
    }

    @Override
    public <T> JAFile<T> getFile(Class<T> recClazz) {
        try {
            return new JAFileImpl(recClazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> JAFile<T> getFileByAlias(String alias) {
        try {
            return new JAFileImpl(byAlias.get(alias));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> JAIndex<T> getIndexByAlias(JAFile<T> jafile, String indexAlias) {
        try
		{
			return new JAIndexImpl<T>(
						(Class<T>) this.byAlias.get(jafile.getAlias()),
						indexAlias
					);
		}
		catch(IOException ex)
		{
			throw new RuntimeException("OH NO!!!");
		}
    }

    public void register(Class<?> clazz) {
        registeredWrappers.add(clazz);
        String alias = Utils.getAlias(clazz);
        byAlias.put(alias,clazz);
    }
}
