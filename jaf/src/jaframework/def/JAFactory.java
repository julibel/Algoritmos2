package jaframework.def;

import jaframework.implementators.Repository;

/**
 * Created by usuario on 15/06/14.
 */
public class JAFactory {

    private static Repository repository = new Repository();
    public static void registerMapping(Class<?> clazz) {
        repository.register(clazz);
    }

    public static JASession getSession() {
        return repository;
    }
}
