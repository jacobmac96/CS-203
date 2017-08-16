import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Author Jacob MacDermaid
 */
public class TopologicalSort {
    static int numOfNodes = 0;
    /****************************************************************/
    /*Method: main                                                  */
    /*Purpose: gets file name and ask user for a command to complete*/   
    /*Parameters:                                                   */
    /*  int String[] args    file station information is stored     */
    /*  Returns: void:                                              */
    /****************************************************************/
    
	public static void main(String[] args)
	{
		int inputLine = -1;//value user inputs for a command
		String inputFile = null; //stores file name
		try{
			inputFile = args[0];//text file name for matrix information
		}catch(ArrayIndexOutOfBoundsException a){
			System.out.println("Please enter a file name.");
			System.exit(1);
		}
		if(checkFile(inputFile))
		{
			
		}
			
	}
	
	public static boolean checkFile(String inputFile)
	{
		File matrixDataFile = new File(inputFile);//create file from input string
        Scanner matrixDataScanner = null;//create a scanner to scan through file
        try {//try to create scanner for file if available
        	matrixDataScanner = new Scanner(matrixDataFile);
        } catch (FileNotFoundException noFile) {
            System.out.println("Please check if file is in correct location");
            System.exit(2);
        }
        //get the number of nodes
        if(matrixDataScanner.hasNextLine())
        {
        	numOfNodes = matrixDataScanner.nextInt();
        	matrixDataScanner.nextLine();
        }
        while(matrixDataScanner.hasNextLine())//check if file has another line
        {
        	
        }
		return false;
		
	}
}
