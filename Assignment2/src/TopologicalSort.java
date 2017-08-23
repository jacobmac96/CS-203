import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*********************************************************************/
/*Jacob MacDermaid                                                   */
/*Login ID: macd0919                                                 */
/*CS-203, Summer 2017                                                */
/*Programming Assignment 2                                           */
/*TopologicalSort Class: This class sorts an adjacency matrix using  */
/*  										  a DFS-based algorithm  */
/*********************************************************************/
public class TopologicalSort {
    static int numOfNodes = 0; //number of nodes in the file
    static int AdjacMatrix[][] = null;//2D array to store matrix
    static Scanner matrixDataScanner = null;//create a scanner to scan through file
    static Stack stack = null;//stack for sort
    static Graph graph = null;//graph for adjacency matrix
    static int[] sort = null;//order to print the sort
    static int sortCounter = 0;//keep track of sort spot
    
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
		    //error if no file was found
			System.out.println("Please enter a file name.");
			System.exit(1);
		}
		//creates matrix from input file
		if(createMatrix(inputFile))
		{
		    //init graph, stack, and sort now that file has been read
			graph = new Graph(AdjacMatrix);
			stack = new Stack(numOfNodes);
			sort = new int[numOfNodes];
			//perform the sort
		     long startTime = System.nanoTime();
			if(topoSort())
			{
			    long endTime = System.nanoTime();
		        long totalTime = (endTime - startTime);
			    //print the matrix and the sort
				printMatrix();
				System.out.println("Time taken to complete " +totalTime + "ns");
			}
			else
			{
			    long endTime = System.nanoTime();
		        long totalTime = (endTime - startTime);
				System.out.println("Adjacency matrix cannot be sorted.");			
				System.out.println("Time taken to complete " +totalTime + "ns");
			}
		}
			
	}
	
    /****************************************************************/
    /*Method: topoSort                                              */
    /*Purpose: performs the topological sort on the matrix          */   
    /*Parameters:                                                   */
    /*          void                                                */
    /*  Returns: boolean: if the sort was successful                */
    /****************************************************************/
	
	public static boolean topoSort() {
	    //keep track of when vertex was pushed to stack
		int stackCount = 0;
		for(int vertex = 0; vertex < graph.getVNum(); vertex++)
		{
		    //make sure vertex has not been visited
			if(graph.getVisited(vertex) == 0)
			{
			    //push vertex into stack
				stackCount++;
				graph.setVisited(vertex, stackCount);
				stack.push(vertex);
				//keep running until stack becomes empty
				while(!stack.isEmpty())
				{
				    //get the vertex at top of stack
					int topVertex = stack.peak();
					int  edge= 0;//variable for keeping track of edge being checked
					//go through all edges until an adjacent non visited edge is found
					while((edge < graph.getVNum()) && (!(graph.isAdjacent(topVertex, edge)) 
				                               || !(graph.getVisited(edge) == 0)))
					{
				       edge++;
					}
					//check if broke out because ran out of edges
					if(edge == graph.getVNum())
					{
					    //pop that vertex and store it for the sort
					    sort[sortCounter] = stack.pop();
					    sortCounter++;
					}
					else
					{
					    //check if there is a back edge.
						if(graph.isAdjacent(topVertex, edge) && graph.isAdjacent(edge, topVertex))
							return false;
						//if no back edge then push current vertex to stack
						stackCount++;
						graph.setVisited(edge, stackCount);
						stack.push(edge);
					}
				}
			}
		}
		//when finished return true
		return true;
	}

    /****************************************************************/
    /*Method: printMatrix                                           */
    /*Purpose: prints the matrix and sort                           */   
    /*Parameters:                                                   */
    /*          void                                                */
    /*  Returns: void                                               */
    /****************************************************************/
	
	public static void printMatrix() {
	    //print the adjacency matrix
	    System.out.print("The adjacency Matrix:");
		for(int vertex = 0; vertex< AdjacMatrix.length; vertex++)
		{
			System.out.println();
			for(int edge = 0; edge < AdjacMatrix.length; edge++)
			{
				System.out.print(AdjacMatrix[vertex][edge] + " ");
			}
			
		}
		System.out.println();//new line for format
		System.out.print("The sort is: ");
		//print in reverse order than popped off stack
		for(int vertex = sort.length - 1; vertex >= 0;vertex--)
		{
			System.out.print((sort[vertex] + 1)  + " ");
			
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
