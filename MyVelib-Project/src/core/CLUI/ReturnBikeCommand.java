package core.CLUI;
import core.classes.*;

import java.util.List;
/**
 * Class to represent the CL for returning a bicycle to a station
 * @version 1.0
 */
public class ReturnBikeCommand {
	/**
	 * Checks if two integers are passed in this CL and one double
	 */
	public static boolean hasCorrectInputs(String[] args) {
        if (args.length != 3) {
            return false;
        }
        try {
            int userID = Integer.parseInt(args[0]);
            int stationID = Integer.parseInt(args[1]);
            double duration = Double.parseDouble(args[2]);
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            System.out.println("Wrong data type. Please see your file.txt ;)");
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
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
