package core.CLUI.RUN;
import java.util.*;

import core.CLUI.Commands;
import core.CLUI.ReadTestInTxt;

/**
 * Main Class To run the Command lines or test scenarios
 * @version 1.0
 * @author Lidam
 */
public class MainCommand {
	/**
	 * Init the app with a myVelibInit network
	 * with the following config : initNetwork 15 15 1000 0.75
	 * @throws Exception Read file problem or parsing issues
	 */
	public static void initNetwork() throws Exception {
        String[] file = new String[1];
        file[0] = "myVelib.ini.txt";
        try{
        	ReadTestInTxt.main(file);
        	}
        catch(Exception e) {
    		System.out.println(e);
        }
    }
	/**
	 * Main Method
	 * @throws Exception Parsing issues
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("*******************  Welcome to our myVelib project!!!  *********************");
        initNetwork();
        System.out.println("********************** Please enter your command ****************************");
        Scanner scanner = new Scanner(System.in);
        String[] arguments = scanner.nextLine().split(" ");
        Commands.main(arguments);
        System.out.println("******** Write another command (to escape tap: exit) *********");
        arguments = scanner.nextLine().split(" ");
        scanner.close();
	}
	}
