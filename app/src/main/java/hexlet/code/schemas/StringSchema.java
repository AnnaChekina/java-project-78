package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", Objects::nonNull);
        addValidation("nonEmpty", s -> !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addValidation("minLength", s -> s.isEmpty() || s.length() >= minLength);
        return this;
    }

    public StringSchema contains(String param) {
        addValidation("contains", s -> s.isEmpty() || s.contains(param));
        return this;
    }
}
