package org.brienze.biscoint.cucumber.context;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private static Context instance;
    private final Map<String, Object> data;

    private Context() {
        data = new HashMap<>();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(data.get(key));
    }

    public <T> void set(String key, T object) {
        data.put(key, object);
    }

    public void reset() {
        data.clear();
    }

}
