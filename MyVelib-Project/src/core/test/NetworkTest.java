package core.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import core.classes.Network;
import core.classes.User;
import core.classes.VlibreRegistrationCard;


public class NetworkTest {
	// Initialization
    Network soukainaNetwork = new Network("Maroc");
    User user1 = new User("Taha", new Point(12, 10), "Express23523555",
            new VlibreRegistrationCard());
	// ------------------ Test addUser Method ------------------ 
    @Test
    void falseParametersInput() {
        assertThrows(Exception.class, () -> new Network("Maroc", 100, 2, 1, 157));
    }
    @Test
    void addUser() {
    	soukainaNetwork.addUser(user1);
        assertAll(
                () -> assertTrue(soukainaNetwork.getListUsers().size() == 1)
        );
    }
}