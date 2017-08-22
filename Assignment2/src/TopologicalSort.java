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
    static Stack stack = null;
    static Graph graph = null;
    static int[] order = null;
    static int counter = 0;
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
		}catch(ArrayIndexOutOfBoundsException noFile){
			System.out.println("Please enter a file name.");
			System.exit(1);
		}
		if(checkFile(inputFile))
		{
			graph = new Graph(AdjacMatrix);
			stack = new Stack(numOfNodes);//create a stack
			order = new int[numOfNodes];
			if(topoSort())
			{
				printMatrix();
			}
			else
				System.out.println("Failed");
				
		}
			
	}
	
	public static boolean topoSort() {
		int stackCount = 0;
		for(int vertex = 0; vertex < graph.getVNum(); vertex++)
		{
			if(graph.getVisited(vertex) == 0)
			{
				stackCount++;
				graph.setVisited(vertex, stackCount);
				stack.push(vertex);
				while(!stack.isEmpty())
				{
					int top = stack.peak();
					int w = 0;
				   while((w < graph.getVNum()) && (!(graph.isAdjacent(top, w)) || !(graph.getVisited(w) == 0)))
					   w++;
					if(w == graph.getVNum())
					{
						order[counter] = stack.pop();
						counter++;
					}
					else
					{
						if(graph.isAdjacent(top, w) && graph.isAdjacent(w, top))
							return false;
						stackCount++;
						graph.setVisited(w, stackCount);
						stack.push(w);
					}
				}
			}
		}
		return true;
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
		System.out.println();
		for(int i = order.length - 1; i >= 0;i--)
		{
			System.out.print(order[i]);
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
