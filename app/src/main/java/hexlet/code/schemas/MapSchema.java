package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        removeValidation("sizeof");
        addValidation("sizeof", s -> s.size() == size);
        return this;
    }
}
