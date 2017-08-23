/*********************************************************************/
/*Jacob MacDermaid                                                   */
/*Login ID: macd0919                                                 */
/*CS-203, Summer 2017                                                */
/*Programming Assignment 2                                           */
/*Graph Class: This class stores the graph to performs actions       */
/*********************************************************************/
public class Graph {
	
	int vertices;//number of vertex 
	int adjacencyMatrix[][] = null;
	int visitedArray[] = null;
	
    /*****************************************************************/
    /*Method: Graph                                                  */
    /*Purpose:  constructor for graph                                */
    /*Parameters:                                                    */
    /*  String int[][] adjacMatrix: adjacency matrix                 */
    /*  Returns: void                                                */
    /*****************************************************************/
	
	Graph(int[][] adjacMatrix)
	{
		vertices = adjacMatrix.length;
		adjacencyMatrix = adjacMatrix;
		visitedArray = new int[vertices];
	}
	
    /*****************************************************************/
    /*Method: setVisited /getVisited                                 */
    /*Purpose:  Accessor and mutator for visited                     */
    /*Parameters:                                                    */
    /*  int vertex: the vertex that was visited                      */
	/*  int count: what number it was visited                        */
    /*  Returns: visited number                                      */
    /*****************************************************************/
	
	public void setVisited(int vertex, int count)
	{
		if(vertex < vertices)
		{
			visitedArray[vertex] = count;
		}
	}
	
	public int getVisited(int vertex)
	{
		if(vertex < vertices)
		{
			return visitedArray[vertex];
		}
		return 0;
	}
	
    /*****************************************************************/
    /*Method: getVNum                                                */
    /*Purpose:  returns the number of vertices there are             */
    /*Parameters:                                                    */
    /*  void                                                         */
    /*  Returns: number of vertices                                  */
    /*****************************************************************/
	
	public int getVNum()
	{
		return vertices;
	}
	
    /*****************************************************************/
    /*Method: isAdjacent                                             */
    /*Purpose: checks if two vertex are adjacent                     */
    /*Parameters:                                                    */
    /*  int vertexOne: start vertex                                  */
	/*  int vertexTwo: edge vertex                                   */
    /*  Returns: boolean: if they are adjacent                       */
    /*****************************************************************/
	
	public boolean isAdjacent(int vertexOne, int vertexTwo)
	{
		if(vertexOne < vertices && vertexTwo < vertices)
		{
			if(adjacencyMatrix[vertexOne][vertexTwo] == 1)
			{
				return true;
			}
			return false;
		}
		return true;
	}
	
    /*****************************************************************/
    /*Method: removeVertex                                           */
    /*Purpose: removes a vertex once it has been added to sort       */
    /*Parameters:                                                    */
    /*  int vertex: vertex to remove                                 */
    /*  Returns: void                                                */
    /*****************************************************************/
	
	public void removeVertex(int vertex)
	{
		for(int edge = 0; edge <vertices; edge++)
		{
			adjacencyMatrix[vertex][edge] = 0;
		}
	}
}
