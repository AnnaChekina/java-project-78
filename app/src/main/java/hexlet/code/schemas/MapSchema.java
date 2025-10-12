package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation("sizeof", s -> s.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addValidation(
                "shape",
                map -> {
                    if (map == null) {
                        return true;
                    }
                    return schemas.entrySet().stream().allMatch(e -> {
                        String fieldName = e.getKey();
                        BaseSchema<T> fieldSchema = e.getValue();
                        T fieldValue = (T) map.get(fieldName);
                        return fieldSchema.isValid(fieldValue);
                    });
                }
        );
        return this;
    }
}
