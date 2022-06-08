package core.CLUI;
import java.util.List;
import core.classes.*;

/**
 * Class to represent the CL for to display the statistics
 * of station stationID of a myVelib network
 * @version 1.0
 */
public class DisplayStationCommand {
    public static boolean hasCorrectInputs(String[] args) {
        try {
            int stationID = Integer.parseInt(args[1]);
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            System.out.println("Wrong data type, check your input arguments");
            return false;
        }
        return args.length == 2;
    }
	/**
	 * Main method
	 * @version 1.0
	 */
    public static void main(String[] args) {
        if (!hasCorrectInputs(args)) {
        	System.out.println("Please correct the arguments! Need to provide two arguments: velibnetworkName, stationID");
        } else {
            System.out.println("...Running the command DisplayStation");
            List<Network> networks = App.getNetworks();
            Network currentNetwork = null;
            Station currentStation = null;
            for (Network system : networks) {
                if (system.getName().equalsIgnoreCase(args[0])) {
                    currentNetwork = system;
                    break;
                }
            }
            if (currentNetwork != null) {
                List<Station> stations = currentNetwork.getListStations();
                for (Station station : stations) {
                    if (station.getId() == Integer.parseInt(args[1])) {
                        currentStation = station;
                        currentNetwork.displayStationBalance(station);
                        break;
                    }
                }
                if (currentStation == null)
                    System.out.println("The id of the station does not exist");
            } else {
                System.out.println("The network does not exist");
            }
        }

    }
}
