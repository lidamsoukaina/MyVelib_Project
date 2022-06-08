package core.CLUI;
import java.util.List;
import core.classes.*;
/**
 * Class to represent the CL for adding users
 * @version 1.0
 */
public class AddUserCommand {
    public static boolean hasCorrectInputs(String[] args) {
    	// the parameters are strings no need for a parse, 
    	// we only check if the type of card is supported
        if (args.length == 3) {
        	switch (args[1]) {
	        	case "Vmax":
	        	case "Vlibre":
	        	case "none":
	        		return true;
	        	default:
	                System.out.println("Error: add a correct card type (Vmax, Vlibre, None)");
	        		return false;
        	}
        } else {
        	return false;
        }
    }
    public static void main(String[] args) {
    	try {
	        if (!hasCorrectInputs(args)) {
	            System.out.println("ErrorParsing: Check the arguments passed: userName,cardType, velibnetworkName");
	        } else {
	            System.out.println("...Running the command addUser");
	            // Check if the system exists in the application
	            List<Network> networks = App.getNetworks();
	            Network currentNetwork = null;
	            for (Network system : networks) {
	                if (system.getName().equals(args[2])) {
	                    currentNetwork = system;
	                    break;
	                }
	            }
	            if (currentNetwork != null) { 
	                User user = new User(args[0], args[1]);
	                currentNetwork.addUser(user);
	            } else {
	                System.out.println("This network does not exist");
	            }
	        }
    	}
    	catch(Exception e ) {
    		System.out.println(e.getMessage());
    	}
    }
}
