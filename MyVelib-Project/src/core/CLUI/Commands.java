package core.CLUI;

import core.enumerations.StationStatus;

/**
 * Class incorporating all command lines
 * @version 1.0
 * @author LIDAM and RAFAI
 */
public class Commands {
	public static void main(final String[] args){
		try {
	        if (args.length == 0) {
	            System.out.println("No argument input");
	        } else {
	            String[] commandArguments = new String[args.length - 1];
	            System.arraycopy(args, 1, commandArguments, 0, args.length - 1);
	            switch (args[0]) {
	            // switch over the requested commands
	            	case "runtest":
	            		ReadTestInTxt.main(commandArguments);
	            		break;
		            case "setup":
		                SetupCommand.main(commandArguments);
		                break;
	                case "addUser":
	                    AddUserCommand.main(commandArguments);
	                    break;
	                case "offline":
	                	ChangeStationStatusCommand.main(StationStatus.Offline, commandArguments);
	                	break;
	                case "online":
	                	ChangeStationStatusCommand.main(StationStatus.OnService,commandArguments);
	                	break;
	                case "rentBike":
	                	RentBikeCommand.main(commandArguments);
	                	break;
	                case "returnBike":
	                	ReturnBikeCommand.main(commandArguments);
	                	break;
	                case "displayStation":
	                	DisplayStationCommand.main(commandArguments);
	                	break;
	                case "displayUser":
	                	DisplayUserCommand.main(commandArguments);
	                	break;
	                case "sortStation":
	                	SortingStationCommand.main(commandArguments);
	                	break;
	                case "display":
	                    DisplayCommand.main(commandArguments);
	                    break;
	                case "exit":
	                	System.exit(0);
	                	break;
	                default:
	                    System.out.println(" This command:  " + args[0] +"is not currently supported :( ");
	            }
	        }}
		catch (Exception e) {
                System.out.println(e.getMessage());
	        }
		}
    }
