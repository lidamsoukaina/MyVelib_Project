package core.CLUI;

import java.util.List;

import core.classes.App;
import core.classes.Network;
import core.classes.User;

/**
 * Class to represent the CL for to display the statistics
 * of a user userID of a myVelib network
 * @version 1.0
 */
public class DisplayUserCommand {

    public static boolean hasCorrectInputs(String[] args) {
        try {
            int userID = Integer.parseInt(args[1]);
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            System.out.println("Wrong data type. Expecting an integer.");
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
        	System.out.println("Please correct the arguments! Need to provide two arguments: velibnetworkName, userID");
        } else {
            System.out.println("...Running the command DisplayUser");
            List<Network> networks = App.getNetworks();
            Network currentnetwork = null;
            User currentUser = null;
            for (Network network : networks) {
                if (network.getName().equalsIgnoreCase(args[0])) {
                    currentnetwork = network;
                    break;
                }
            }
            if (currentnetwork != null) {
                List<User> users = currentnetwork.getListUsers();
                for (User user : users) {
                    if (user.getId() == Integer.parseInt(args[1])) {
                        currentUser = user;
                        currentnetwork.displayUserBalance(currentUser);
                        break;
                    }
                }
                if (currentUser == null) {
                	System.out.println("The user of id = "+ Integer.parseInt(args[1]) +" does not exist");
                }

            } else {
            	System.out.println("The network does not exist");
            }
        }
    }

}
