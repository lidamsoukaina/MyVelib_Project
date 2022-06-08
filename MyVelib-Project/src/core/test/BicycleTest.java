package core.test;
import org.junit.jupiter.api.*;

import core.classes.Bicycle;
import core.enumerations.BicycleType;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for class Bicycle
 */
class BicycleTest {
    @Test
    @DisplayName("Test uniqueness of id for bicycles")
    public void testUniqueID() {
        // Two electrical bicycles
        Bicycle electricalBicycle1 = new Bicycle(BicycleType.Electrical);
        Bicycle electricalBicycle2 = new Bicycle(BicycleType.Electrical);
        assertTrue(electricalBicycle1.getId() != electricalBicycle2.getId());
    }
    @Test
    @DisplayName("Test the type of bicycles")
    public void testBicycleType() {
        // Test bicycle type
        Bicycle mechanicalBicycle = new Bicycle(BicycleType.Mechanical);
        Bicycle bicycle = new Bicycle(null);
        assertAll("Assert all types are correct",
                () -> assertTrue(mechanicalBicycle.getType() == BicycleType.Mechanical),
                () -> assertTrue(bicycle.getType() == null)
        );
    }

}