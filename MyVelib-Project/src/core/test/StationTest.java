package core.test;
import org.junit.jupiter.api.*;

import core.classes.Bicycle;
import core.classes.NoRegistrationCard;
import core.classes.Slot;
import core.classes.Station;
import core.classes.User;
import core.classes.VlibreRegistrationCard;
import core.enumerations.BicycleType;
import core.enumerations.SlotStatus;
import core.enumerations.StationStatus;
import core.enumerations.StationType;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
/**
 * Test class for class Station testing
 */
class StationTest {
	// Initialization
    Station station = new Station(new Point(6, 4), StationStatus.OnService, StationType.Standard);
    Bicycle mechanicalBicycle1 = new Bicycle(BicycleType.Mechanical);
    Bicycle mechanicalBicycle2 = new Bicycle(BicycleType.Mechanical);
    Slot SlotOccupiedMechanical1 = new Slot(mechanicalBicycle1);
    Slot SlotOccupiedMechanical2 = new Slot(mechanicalBicycle2);
    Slot SlotFree = new Slot();
	// ----------------- Testing hasBicycleOfType method --------------------------
    @Test
    @DisplayName("Test of the method hasBicycleOfType")
    public void testHasBike() throws Exception {
        station.addSlot(SlotOccupiedMechanical2);

        assertAll("Assert if satation has a bicycle of a specified type",
                () -> assertTrue(station.hasBicycleOfType(BicycleType.Mechanical)),
                () -> assertFalse(station.hasBicycleOfType(BicycleType.Electrical))
        );
    }
	// ----------------- Testing hasFreeSlot method --------------------------
    @Test
    @DisplayName("Test of the method hasFreeSlot")
    void testHasFreeSlot() throws Exception{
        Station station2 = new Station(new Point(16, 4), StationStatus.OnService, StationType.Standard);
        station.addSlot(SlotFree);
        assertAll("Station with free parking slot",
                () -> assertTrue(station.hasFreeSlot()),
                () -> assertFalse(station2.hasFreeSlot())
                );
    }
	// ----------------- Testing getParkingSlotsCount method --------------------------
    @Test
    @DisplayName("Test of the method getParkingSlotsCount")
    void testGetParkingSlotsCountMehod() {
        Station station2 = new Station(new Point(16, 4), StationStatus.OnService, StationType.Standard);
        station.addSlot(SlotFree);
        assertAll("Station with free parking slot",
                () -> assertTrue(station.getParkingSlotsCount() == 1),
                () -> assertTrue(station2.getParkingSlotsCount() == 0)
                );
    }
	// ----------------- Testing getFreeSlotsCount method --------------------------
    @Test
    @DisplayName("Test of the method getFreeSlotsCount")
    void testGetFreeSlotsCountMehod() {
        Station station2 = new Station(new Point(16, 4), StationStatus.OnService, StationType.Standard);
        station.addSlot(SlotFree);
        assertAll("Station with free parking slot",
                () -> assertTrue(station.getFreeSlotsCount() == 1),
                () -> assertTrue(station2.getFreeSlotsCount() == 0)
                );
    }
    
	// ----------------- Testing getBicyclesCount method --------------------------
    @Test
    @DisplayName("Test of the method getBicyclesCount")
    void testGetBicyclesCountMehod() {
        Station station2 = new Station(new Point(16, 4), StationStatus.OnService, StationType.Standard);
        station.addSlot(SlotOccupiedMechanical1);
        station.addSlot(SlotOccupiedMechanical2);
        assertAll("Station with free parking slot",
                () -> assertTrue(station.getBicyclesCount(BicycleType.Electrical) == 0),
                () -> assertTrue(station.getBicyclesCount(BicycleType.Mechanical) == 2),
                () -> assertTrue(station2.getBicyclesCount(BicycleType.Mechanical) == 0)
                );
    }
    
    
    
    
}