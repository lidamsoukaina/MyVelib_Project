package core.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import core.classes.Bicycle;
import core.classes.ConcreteRegistrationCardVisitor;
import core.classes.Invoice;
import core.classes.NoRegistrationCard;
import core.classes.RegistrationCard;
import core.classes.Slot;
import core.classes.Station;
import core.classes.User;
import core.classes.VlibreRegistrationCard;
import core.classes.VmaxRegistrationCard;
import core.enumerations.BicycleType;
import core.enumerations.StationStatus;
import core.enumerations.StationType;
import core.interfaces.RegistrationCardVisitor;

public class RegistrationCardTest {
	// Some inputs
	RegistrationCard noCard = new NoRegistrationCard();
    RegistrationCard vLibreCard = new VlibreRegistrationCard();
    RegistrationCard vMaxCard = new VmaxRegistrationCard();
    User noCardUser = new User("Taha Rafai", new Point(22, 12), "FR3593204740384",noCard);
    User vlibreUser = new User("Soukaina Lidam", new Point(32, 11), "FR3593204740243", vLibreCard);
    User vmaxUser = new User("Paolo Ballarini", new Point(12, 10), "3538493204740384", vMaxCard);

    Station station = new Station(new Point(6, 4), StationStatus.OnService, StationType.Standard);
    Bicycle mechanicalBicycle1 = new Bicycle(BicycleType.Mechanical);
    Bicycle mechanicalBicycle2 = new Bicycle(BicycleType.Mechanical);
    Slot SlotOccupiedMechanical1 = new Slot(mechanicalBicycle1);
    Slot SlotOccupiedMechanical2 = new Slot(mechanicalBicycle2);
    Slot SlotFree = new Slot();
    RegistrationCardVisitor registrationCardVisitor = new ConcreteRegistrationCardVisitor();
    // ------------------ test billing the ride ------------------
    @Test
    void testBillingSuccess1() throws Exception {
    	station.addSlot(SlotOccupiedMechanical1);
    	station.addSlot(SlotOccupiedMechanical2);
    	noCardUser.rent(station, BicycleType.Mechanical);
    	vlibreUser.rent(station, BicycleType.Mechanical);
    	noCardUser.drop(station, 120);
    	vlibreUser.drop(station, 120);
        assertAll("Station with free parking slot",
                () -> assertTrue(noCardUser.getTotalCharges() == 2),
                () -> assertTrue(vlibreUser.getTotalCharges() == 1)
                );
    }
    void testBillingSuccess2() throws Exception {
    	station.addSlot(SlotOccupiedMechanical1);
    	vmaxUser.rent(station, BicycleType.Mechanical);
    	vmaxUser.drop(station, 130);
    	assertTrue(vmaxUser.getTotalCharges() == 1);
    }
}
