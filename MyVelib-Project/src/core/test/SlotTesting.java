package core.test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import core.classes.Bicycle;
import core.classes.Slot;
import core.enumerations.BicycleType;

import core.enumerations.SlotStatus;

/**
 * Test class for class Slot
 */
class SlotTesting {
    Slot freeSlot = new Slot();
    Bicycle mechanicalBicycle = new Bicycle(BicycleType.Mechanical);
    Bicycle electricalBicycle = new Bicycle(BicycleType.Electrical);
    Slot SlotOccupiedMechanical = new Slot(mechanicalBicycle);
    Slot SlotOccupiedElectrical = new Slot(electricalBicycle);
    // --------------- Testing addBicycle Method --------------------
    @Test
    void failAdd() {
        assertThrows(Exception.class, () -> SlotOccupiedMechanical.addBicycle(mechanicalBicycle));
        assertThrows(Exception.class, () -> SlotOccupiedElectrical.addBicycle(mechanicalBicycle));
        assertThrows(Exception.class, () -> new Slot(SlotStatus.Occupied));
        assertThrows(Exception.class, () -> new Slot(SlotStatus.Occupied,null));
        assertThrows(Exception.class, () -> new Slot(SlotStatus.Free,mechanicalBicycle));
    }
    @Test
    public void addBicyleTest() throws Exception {
    	freeSlot.addBicycle(mechanicalBicycle);
    	assertTrue(freeSlot.getStatus() == SlotStatus.Occupied);
    	
    }
}