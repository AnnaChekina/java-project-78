package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorTest {

    @Test
    void testStringSchemaRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("test"));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    void testStringSchemaMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(5);

        assertFalse(schema.isValid("test"));
        assertTrue(schema.isValid("test1"));
        assertTrue(schema.isValid("test123"));
    }

    @Test
    void testStringSchemaContains() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().contains("test");

        assertTrue(schema.isValid("this is a test"));
        assertTrue(schema.isValid("testing"));
        assertFalse(schema.isValid("this is wrong"));
        assertFalse(schema.isValid("tes"));
    }

    @Test
    void testStringSchemaMethodChaining() {
        Validator v = new Validator();
        StringSchema schema = v.string()
                .required()
                .minLength(3)
                .contains("abc");

        // Все условия выполняются
        assertTrue(schema.isValid("123abc456"));

        // Нарушено одно из условий
        assertFalse(schema.isValid("12"));
        assertFalse(schema.isValid("123def456"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testExampleScenario() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
