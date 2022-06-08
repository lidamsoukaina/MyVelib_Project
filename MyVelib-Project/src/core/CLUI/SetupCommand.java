package core.CLUI;

import java.util.List;

import core.classes.App;
import core.classes.Network;
import core.classes.Station;
import core.classes.User;

/**
 * Class to represent the CL for creating a network in the app
 * @version 1.0
 */
public class SetupCommand {
	/**
	 * method to check if input arguments are correct
	 * and returns True if so, otherwise false
	 * @param args list of the arguments
	 * @version 1.0
	 */
	public static boolean hasCorrectInputs(String[] args) {
		if (args.length == 5) {
			try {
				int nStations = Integer.parseInt(args[1]);
				int nSlots = Integer.parseInt(args[2]);
				double side = Double.parseDouble(args[3]);
				float nBikes = Float.parseFloat(args[4]);
				// Check if there are enough slots for the bikes
				if (nBikes > (nSlots*nStations)) {
					return false;
				}
			} catch (NumberFormatException numberFormatException) {
				System.out.println(numberFormatException.getMessage());
				System.out.println("Wrong data type in arguments;)");
				return false;
			}
		}
		// There are 2 command supported currently one with one argument and one with 5
		return (args.length == 5 || args.length == 1);
	}
	/**
	 * Main method
	 * @version 1.0
	 */
	public static synchronized void main(String[] args) {
		try {
	        if (!hasCorrectInputs(args)) {
	        	System.out.println("Please correct the arguments! Need to provide three arguments: userID, stationID, duration");
	        } else {
	            System.out.println("...Running the command returnBike");
	            List<Network> networks = App.getNetworks();
	            int userID = Integer.parseInt(args[0]);
	            int stationID = Integer.parseInt(args[1]);
	            double duration = Double.parseDouble(args[2]);
	            // Check if user is in a network
	            boolean isfound = false;
	            for (Network network : networks) {
	                for (User user : network.getListUsers()) {
	                    if (!isfound && user.getId() == userID) {
	                        for (Station station : network.getListStations()) {
	                            if (station.getId() == stationID) {
	                            	isfound = true;
	                            	user.drop(station,duration);
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
