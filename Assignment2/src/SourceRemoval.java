import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*********************************************************************/
/*Jacob MacDermaid                                                   */
/*Login ID: macd0919                                                 */
/*CS-203, Summer 2017                                                */
/*Programming Assignment 2                                           */
/*SourceRemoval Class: This class sorts an adjacency matrix using    */
/*                                         source removal algorithm  */
/*********************************************************************/
public class SourceRemoval {
	static int numOfNodes = 0;//number of nodes in the file
    static int AdjacMatrix[][] = null;
    static Scanner matrixDataScanner = null;//create a scanner to scan through file
    static Graph graph = null;//graph for matrix
    static int[] order = null;//array to hold order of sort
    static int[] remaining = null;//array to know what vertex are removed

    /****************************************************************/
    /*Method: main                                                  */
    /*Purpose: gets file name from arg and starts the sort          */   
    /*Parameters:                                                   */
    /*  int String[] args    file matrix information is stored      */
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
		if(createMatrix(inputFile))
		{
		    //init graph, order, and remaining now that file has been read
			graph = new Graph(AdjacMatrix);
			order = new int[numOfNodes];
			remaining = new int[numOfNodes];
			printMatrix();
			if(sourceRemove())
			{
				printMatrix();
			}
			else
			    System.out.println("Adjacency matrix cannot be sorted."); 
				
		}
			
	}
	
    /****************************************************************/
    /*Method: sourceRemove                                          */
    /*Purpose: performs the source removal sort on the matrix       */   
    /*Parameters:                                                   */
    /*          void                                                */
    /*  Returns: boolean: if the sort was successful                */
    /****************************************************************/
	
	private static boolean sourceRemove() {
	    int counter = 0;//keep track of how many vertex have been removed
		boolean updated = true;//make sure sort can be solved
		while(counter < numOfNodes && updated)
		{
			updated = false;//set updated to false
			for(int vertex = 0; vertex < graph.getVNum(); vertex++)
			{
			    //make sure vertex still needs checked
				if(remaining[vertex] != -1)
				{
					int edge = 0;
					//check if vertex has an incoming edge
					while(edge < graph.getVNum() && !(graph.isAdjacent(edge, vertex)))
						edge++;
					//if no incoming edge was found remove that vertex
					if(edge == graph.getVNum())
					{
						order[counter] = vertex;
						counter++;
						graph.removeVertex(vertex);
						remaining[vertex] = -1;
						updated = true;//make true sense a vertex was removed
					}
					else
					{
						//vertex had incoming edges	
					}
				}
			}
		}
		//check if the sort was successful. 
		if(counter == numOfNodes)
			return true;
		else
			return false;
	}


    /****************************************************************/
    /*Method: printMatrix                                           */
    /*Purpose: prints the matrix and sort                           */   
    /*Parameters:                                                   */
    /*          void                                                */
    /*  Returns: void                                               */
    /****************************************************************/

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
		for(int vertex = 0; vertex < order.length;vertex++)
		{
			System.out.print((order[vertex]+1) + " ");
		}
		
	}
	
    /****************************************************************/
    /*Method: createMatrix                                          */
    /*Purpose: reads the input file and creates a adjacency matrix  */   
    /*Parameters:                                                   */
    /*          String: inputFile file to read from                 */
    /*  Returns: boolean: if the matrix was created or not          */
    /****************************************************************/
    
	
	public static boolean createMatrix(String inputFile)
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
            //no number of nodes was given
            System.out.println("Make sure number of nodes is an integer");
            return false;
        }
        //init the adjacMatrix with number of nodes
        AdjacMatrix = new int[numOfNodes][numOfNodes];
        //keep scanning through line to get edges
        while(matrixDataScanner.hasNextLine() && currNode < numOfNodes)//check if file has another line
        {
            //get the edges for current vertex
            if(getEdges(currNode))
            {
                currNode++;//go to next node
            }
            else
                return false;
            
        }
        return true;    
    }

    /****************************************************************/
    /*Method: getEdges                                              */
    /*Purpose: gets the edges for the current node                  */   
    /*Parameters:                                                   */
    /*          int currNode: the current node being checked        */
    /*  Returns: boolean: if the file has correct edge format       */
    /****************************************************************/

    public static boolean getEdges(int currNode) 
    {
        //pull the whole line so the number of edges can be checked
        String edgeList = matrixDataScanner.nextLine();
        Scanner edgeScanner = new Scanner(edgeList);
        //get edges that current vertex has.
        for(int edge = 0; edge < AdjacMatrix.length; edge++)
        {
            if(edgeScanner.hasNextInt())
            {
                int pair  = edgeScanner.nextInt();
                //make sure file only has 1s and 0s
                if(pair == 0 || pair == 1)
                {
                    AdjacMatrix[currNode][edge] = pair;
                }
                else 
                {
                    System.out.println("Make sure only 1's and 0's are in matrix");
                    edgeScanner.close();
                    return false;   
                }                   
            }
            else
            {
                System.out.println("Make sure node number is correct.");
                edgeScanner.close();
                return false;
            }
                
        }
        //close scanner
        edgeScanner.close();
        return true;
    }
}
