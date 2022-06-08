package core.CLUI;

import java.util.List;

import core.classes.App;
import core.classes.Network;
import core.classes.Slot;
import core.classes.Station;
import core.classes.User;
import core.enumerations.BicycleType;
import core.enumerations.SlotStatus;

/**
 * Class to represent the CL for renting a bike from a station
 * @version 1.0
 */
public class RentBikeCommand {
	/**
	 * Checks if two integers are passed in this CL
	 */
	public static boolean hasCorrectInputs(String[] args) {
        if (args.length != 2) {
            return false;
        }
        try {
            int userID = Integer.parseInt(args[0]);
            int stationID = Integer.parseInt(args[1]);
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            System.out.println("Wrong data type. Expecting Integer.");
            return false;
        }
        return true;
    }
	/**
	 * Main method
	 * @version 1.0
	 */
	public static void main(String[] args) {
		try {
			if (!hasCorrectInputs(args)) {
	        	System.out.println("Please correct the arguments! Need to provide two arguments: userID, stationID");
	        } else {
	            System.out.println("...Running the command rentBike");
	            List<Network> networks = App.getNetworks();
	            // Check if user is in a network
	            boolean isfound = false;
	            for (Network network : networks) {
	                for (User user : network.getListUsers()) {
	                    if (!isfound && user.getId() == Integer.parseInt(args[0])) {
	                        for (Station station : network.getListStations()) {
	                            if (station.getId() == Integer.parseInt(args[1])) {
	                            	isfound = true;
	                            	Slot occupiedSlot = station.getSlotOfStatus(SlotStatus.Occupied);
	                            	// get the type of available bicycle
	                            	BicycleType bicycleType = occupiedSlot.getBicycle().getType();
	                            	user.rent(station, bicycleType);
	                            }
	                        }
	                    }
	                }
	            }
	            if (isfound == false) {
	                System.out.println("Failed reservation, please check the provided information!");
	            }
		}
			}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
        }
    }