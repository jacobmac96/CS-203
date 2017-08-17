import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Author Jacob MacDermaid
 */
public class TopologicalSort {
    static int numOfNodes = 0;
    static int AdjacMatrix[][] = null;
    static Scanner matrixDataScanner = null;//create a scanner to scan through file
    /****************************************************************/
    /*Method: main                                                  */
    /*Purpose: gets file name and ask user for a command to complete*/   
    /*Parameters:                                                   */
    /*  int String[] args    file station information is stored     */
    /*  Returns: void:                                              */
    /****************************************************************/
    
	public static void main(String[] args)
	{
		String inputFile = null; //stores file name
		try{
			inputFile = args[0];//text file name for matrix information
		}catch(ArrayIndexOutOfBoundsException a){
			System.out.println("Please enter a file name.");
			System.exit(1);
		}
		if(checkFile(inputFile))
		{
			printMatrix();
		}
			
	}
	
	private static void printMatrix() {
		for(int i = 0; i< AdjacMatrix.length; i++)
		{
			System.out.println();
			for(int j = 0; j < AdjacMatrix.length; j++)
			{
				System.out.print(AdjacMatrix[i][j] + " ");
			}
		}
		
	}

	public static boolean checkFile(String inputFile)
	{
		int currNode = 0;//keep track of line in file
		File matrixDataFile = new File(inputFile);//create file from input string
        //Scanner matrixDataScanner = null;//create a scanner to scan through file
        try {//try to create scanner for file if available
        	matrixDataScanner = new Scanner(matrixDataFile);
        } catch (FileNotFoundException noFile) {
            System.out.println("Please check if file is in correct location");
            System.exit(2);
        }
        //get the number of nodes
        if(matrixDataScanner.hasNextInt())
        {
        	numOfNodes = matrixDataScanner.nextInt();
        	matrixDataScanner.nextLine();
        }
        else
        {
        	System.out.println("Make sure number of nodes is an integer");
        	return false;
        }
        AdjacMatrix = new int[numOfNodes][numOfNodes];
        while(matrixDataScanner.hasNextLine() && currNode < numOfNodes)//check if file has another line
        {
        	if(getEdges(currNode))
        	{
        		currNode++;//go to next node
        		if(matrixDataScanner.hasNextLine())
        			matrixDataScanner.nextLine();
        	}
        	else
        		return false;
        	
        }
		return true;
		
	}

	public static boolean getEdges(int currNode) 
	{
		for(int edge = 0; edge < AdjacMatrix.length; edge++)
		{
			if(matrixDataScanner.hasNextInt())
			{
				int pair  = matrixDataScanner.nextInt();
				if(pair == 0 || pair == 1)
				{
					AdjacMatrix[currNode][edge] = pair;
				}
				else 
					return false;	
			}
			else
			{
				System.out.println("Make sure only 1's and 0's are in matrix");
				return false;
			}
				
		}
		return true;
	}
}
