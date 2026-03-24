package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe scenario context to share data between step definitions.
 */
public class ScenarioContext {

    private static final ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        context.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) context.get().get(key);
    }

    public static boolean contains(String key) {
        return context.get().containsKey(key);
    }

    public static void clear() {
        context.get().clear();
    }
}

