package core.CLUI;
import java.util.List;

import core.classes.App;
import core.classes.Network;

/**
 * Class to represent the CL for displaying the entire
 * status (stations, parking bays,users) of an a myVelib network velibnetworkName
 * @version 1.0
 */
public class DisplayCommand {

	 public static void helpCommand(String[] args) {
	        System.out.println("The display command");
	        System.out.println("display <velibnetworkName>: to display the entire status (stations, parking bays, users) of an a myVelib network velibnetworkName.");
	    }
	    public static void argumentHelper() {
	    	System.out.println("A problem occured while passing the arguments!");
			System.out.println("Please correct the arguments!");
		}
	    public static boolean hasCorrectInputs(String[] args) {
	        return args.length == 1;
	    }
	    public static void main(String[] args) {

	        if (!hasCorrectInputs(args)) {
	            argumentHelper();
	        } else {
	            System.out.println("...Running the command Display");
	            System.out.println("The display command");
	            List<Network> networks = App.getNetworks();
	            Network currentNetwork = null;
	            for (Network network : networks) {
	                if (network.getName().equals(args[0])) {
	                	currentNetwork = network;
	                    break;
	                }
	            }
	            if (currentNetwork != null) {
	            	currentNetwork.displayNetworkStatus();
	            } else {
	                System.out.println("The network of name : " + args[0] +" doesn't exist");
	            }
	        }
	    }
}
