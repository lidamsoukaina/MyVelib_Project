package core.CLUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to run scenario test from .txt files
 * @version 1.0
 */
public class ReadTestInTxt {
	/**
	 * Check that user passed a valid scenario test file
	 */
	public static List<String> readTextFile(String filePath) {
        List<String> returnValue = new ArrayList<>();
        FileReader file = null;
        BufferedReader reader = null;
        try {
            file = new FileReader(filePath);
            reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                returnValue.add(line);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        }
        finally {
            if (file != null) {
                try {
                    file.close();
                    assert reader != null;
                    reader.close();

                } catch (IOException e) {
                    System.out.println("An error occured!");
                }
            }
        }
        return returnValue;
    }
	public static void main(String[] args) {
		try {
			System.out.println("...Excecution of the runtest command");
	        if (args.length != 0) {
	            String fileName = args[0];
	            File directory = new File("./");
	            String filePath = ".\\src\\eval\\" + fileName;
	            List<String> textFile = readTextFile(filePath);
	            boolean fileHasProblem = false;
	            if (!fileHasProblem) {
	                for (String item : textFile) {
	                    String[] argsCommand = item.split(" ");
	                    System.out.println("Init the command: " + item);
	                    Commands.main(argsCommand);
	                    System.out.println("Done with : " + item);
	                }
	            } else {
	                System.out.println("Please check the scenario test file syntax");
	            }
	        } else {
	        	System.out.println("Please add a file path as argument");
	        }
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
