package hexlet.code.schemas;

import hexlet.code.BaseSchema;
import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    private Validator v;
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        v = new Validator();
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

    @Test
    void testMapSchemaShape() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }

    @Test
    void testMapSchemaShapeWithOptionalField() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required());
        schemas.put("email", v.string().contains("@"));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Doe");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", "Doe");
        human2.put("email", "invalid-email");
        assertFalse(schema.isValid(human2));
    }
}
