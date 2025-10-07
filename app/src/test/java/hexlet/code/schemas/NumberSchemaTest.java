package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        Validator v = new Validator();
        schema = v.number();
    }

    @Test
    void testNumberSchemaRequired() {
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(0));
    }

    @Test
    void testNumberSchemaPositive() {
        schema.positive();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testNumberSchemaRange() {
        schema.range(5, 10);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testNumberSchemaMethodChaining() {
        schema.required()
              .positive()
              .range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
