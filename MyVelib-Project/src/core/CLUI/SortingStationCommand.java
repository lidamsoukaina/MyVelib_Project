package core.CLUI;

import java.util.List;

import core.classes.App;
import core.classes.Network;
import core.classes.SortingStationsByMostUsed;
import core.classes.SortingStationsByLeastOccupied;
import core.enumerations.StationsSortingPolicy;

/**
 * Class to represent the CL for displaying the stations in
 * increasing order w.r.t. to a sorting policy .
 * @version 1.0
 */
public class SortingStationCommand {
	/**
	 * Checks if two arguments are passed by the user
	 */
    public static boolean hasCorrectInputs(String[] args) {
        if (args.length == 2) {
            return true;
        }
        return false;
    }
	/**
	 * Main method
	 * @version 1.0
	 */
    public static void main(String[] args) {
        if (!hasCorrectInputs(args)) {
        	System.out.println("Please correct the arguments!");
        	System.out.println("Need to provide two arguments: velibnetworkName, sortpolicy");
        } else {
            System.out.println("...Running the command SortingStation");
            List<Network> networks = App.getNetworks();
            Network currentNetwork = null;
            for (Network network : networks) {
                if (network.getName().equals(args[0])) {
                	currentNetwork = network;
                    break;
                }
            }
            String policy = args[1];
            if (StationsSortingPolicy.MostUsed.toString().equals(policy))
            {
            	currentNetwork.sortStation(new SortingStationsByMostUsed());
            	System.out.println("Stations Sorted!");
            }
            else if (StationsSortingPolicy.LeastOccupied.toString().endsWith(policy))
            {
            	currentNetwork.sortStation(new SortingStationsByLeastOccupied());
            	System.out.println("Stations Sorted!");

            }
            else {
            	System.out.println("The requested policy is not supported : " + policy);
            }
        }
    }
}
