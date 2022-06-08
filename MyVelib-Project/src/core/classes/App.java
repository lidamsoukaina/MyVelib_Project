package core.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for myVelib application representation.
 * It is created to gather the all the networks.
 * @version 1.0
 */
public class App {
	// Attributes
    private static List<Network> networks = new ArrayList<>();
    //Methods
    /**
     * Adds an existing network to the application
     * @version 1.0
     * @throws Exception if network already exists
     */
	public static void addNetwork(Network network) throws Exception {
    	networks.add(network);
		if(!networks.contains(network)) {
            networks.add(network);
            System.out.println("The network is succussefully created and has the id " + network.getId()); 	
        }
        else {
        	throw new Exception("Error: this network is already created and contained in the app.");
        }
    }
    //Methods
    /**
     * Adds a new network to the Application after creating it
     * @version 1.0
     * @throws Exception while network creation
     */
	public static void addNetwork(String networkName, int numberOfStations, int numberOfSlots, double side,float nbikes) throws Exception {
		Network network = new Network(networkName, numberOfStations,numberOfSlots,side,nbikes);
		if(!networks.contains(network)) {
            networks.add(network);
            System.out.println("The network is succussefully created and has the id " + network.getId()); 	
        } else {
        	throw new Exception("Error: this network is already created and contained in the app.");
        }
    }
    // Getters and Setters
	public static void setNetworks(List<Network> networks) {
		App.networks = networks;
	}
    public static List<Network> getNetworks() {
		return networks;
	}
}
