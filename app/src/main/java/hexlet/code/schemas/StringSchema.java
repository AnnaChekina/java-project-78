package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.Objects;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", Objects::nonNull);
        addValidation("nonEmpty", s -> !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addValidation("minLength", s -> s.length() >= minLength);
        return this;
    }

    public StringSchema contains(String param) {
        addValidation("contains", s -> s.contains(param));
        return this;
    }
}
