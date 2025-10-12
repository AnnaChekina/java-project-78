package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    private final Map<String, Predicate<T>> validations = new HashMap<>();

    protected final void addValidation(String key, Predicate<T> validation) {
        validations.put(key, validation);
    }

    public final boolean isValid(T o) {
        if (validations.containsKey("required") && o == null) {
            return false;
        }
        if (o == null) {
            return true;
        }

        return validations.values().stream().allMatch(validation -> validation.test(o));
    }
}
