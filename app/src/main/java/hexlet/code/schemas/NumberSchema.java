package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        removeValidation("positive");
        addValidation("positive", n -> n > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        removeValidation("range");
        addValidation("range", n -> n >= min && n <= max);
        return this;
    }
}
