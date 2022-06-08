package core.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import core.classes.Bicycle;
import core.classes.NoRegistrationCard;
import core.classes.Slot;
import core.classes.Station;
import core.classes.User;
import core.classes.VlibreRegistrationCard;
import core.enumerations.BicycleType;
import core.enumerations.StationStatus;
import core.enumerations.StationType;


import java.awt.Point;

class UserTest {
	// Initialization
    User user1 = new User("Bashir", new Point(5, 4), "LCL53623458235832",
            new NoRegistrationCard());
    User user2 = new User("Soukaina", new Point(12, 10), "Express23523555",
            new VlibreRegistrationCard());
    Station station = new Station(new Point(6, 4), StationStatus.OnService, StationType.Standard);
    Bicycle mechanicalBicycle1 = new Bicycle(BicycleType.Mechanical);
    Bicycle mechanicalBicycle2 = new Bicycle(BicycleType.Mechanical);
    Slot SlotOccupiedMechanical1 = new Slot(mechanicalBicycle1);
    Slot SlotOccupiedMechanical2 = new Slot(mechanicalBicycle2);
    Slot SlotFree = new Slot();
	// ------------------ Test rent event ------------------ 
    @Test
    void rentImpossible() {
    	
        assertThrows(Exception.class, () -> user1.rent(station, BicycleType.Electrical));
    }
    @Test
    void rent() throws Exception {
        station.addSlot(SlotFree);
        station.addSlot(SlotOccupiedMechanical1);
        user1.rent(station,BicycleType.Mechanical); 
        
        assertAll(
                () -> assertTrue(user1.getRentedBicycle().getType() == BicycleType.Mechanical),
                () -> assertTrue(user1.getTotalRides() == 0)
        );
    }
	// ------------------ Test drop event ------------------ 
    @Test
    void dropImpossible() {
        assertThrows(Exception.class, () -> user1.drop(station, 15));
    }
    @Test
    void drop() throws Exception {
        station.addSlot(SlotFree);
        station.addSlot(SlotOccupiedMechanical1);
        station.addSlot(SlotOccupiedMechanical2);
        user1.rent(station, BicycleType.Mechanical);
        user2.rent(station, BicycleType.Mechanical);
        user1.drop(station, 15);
        
        assertTrue(user1.getRentedBicycle() == null);
        assertTrue(user2.getRentedBicycle().getType() == BicycleType.Mechanical);
        assertTrue(user1.getTotalRides() == 1.0);
        assertTrue(user2.getTotalRides() == 0.0);
    }

}