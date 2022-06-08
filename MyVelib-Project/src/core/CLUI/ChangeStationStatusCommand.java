package core.CLUI;
import core.classes.*;
import core.enumerations.StationStatus;
import java.util.List;

/**
 * Class to represent the CL for putting a station offline
 * @version 1.0
 */
public class ChangeStationStatusCommand {
	/**
	 * Check that user passed two arguments velibnetworkName, stationID where the second is an int
	 * @version 1.0
	 */
    public static boolean hasCorrectInputs(String[] args) {
        if (args.length != 2) {
            return false;
        }
        try {
            int stationID = Integer.parseInt(args[1]);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Wrong data type. Expecting an integer.");
            System.out.println(numberFormatException.getMessage());
            return false;
        }
        return true;
    }
	/**
	 * Main method
	 * @version 1.0
	 */
    public static void main(StationStatus status,String[] args) {
        if (!hasCorrectInputs(args)) {
        	System.out.println("Erreur: you need to pass exactly 2 arguments");
        } else {
            System.out.println("...Running the command to change station status to: "+ status);
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
                        currentStation.setStationStatus(status);
                        break;
                    }
                }
                if (currentStation == null)
                    System.out.println("The id of the station does not exist");
            } else
                System.out.println("The system does not exist");
        }
    }


}
