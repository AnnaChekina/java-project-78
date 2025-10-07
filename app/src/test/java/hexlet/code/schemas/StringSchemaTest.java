package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    private StringSchema schema;

    @BeforeEach
    void setUp() {
        Validator v = new Validator();
        schema = v.string();
    }

    @Test
    void testStringSchemaRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("test"));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("test"));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    void testStringSchemaMinLength() {
        schema.minLength(5);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertFalse(schema.isValid("test"));
        assertTrue(schema.isValid("test1"));
        assertTrue(schema.isValid("test123"));
    }

    @Test
    void testStringSchemaContains() {
        schema.contains("test");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("this is a test"));
        assertTrue(schema.isValid("testing"));
        assertFalse(schema.isValid("this is wrong"));
        assertFalse(schema.isValid("tes"));
    }

    @Test
    void testStringSchemaMethodChaining() {
        schema.required()
              .minLength(3)
              .contains("abc");

        assertTrue(schema.isValid("123abc456"));
        assertFalse(schema.isValid("12"));
        assertFalse(schema.isValid("123def456"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }
}
