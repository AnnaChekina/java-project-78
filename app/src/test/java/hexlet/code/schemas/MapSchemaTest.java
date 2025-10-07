package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    private MapSchema schema;

    @BeforeEach
    void setUp() {
        Validator v = new Validator();
        schema = v.map();
    }

    @Test
    void testMapSchemaRequired() {
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }

    @Test
    void testMapSchemaSizeof() {
        schema.sizeof(2);

        assertTrue(schema.isValid(null));

        var data = new HashMap<String, String>();

        assertFalse(schema.isValid(data));

        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    void testMapSchemaMethodChaining() {
        schema.required()
                .sizeof(2);

        var data = new HashMap<String, String>();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(data));

        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }
}
