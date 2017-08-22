
public class Graph {
	
	int vertices;
	int edge;
	int visited;
	int adjacencyMatrix[][] = null;
	int visitedArray[] = null;
	Graph(int[][] adjacMatrix)
	{
		vertices = adjacMatrix.length;
		adjacencyMatrix = adjacMatrix;
		visitedArray = new int[vertices];
	}
	
	void setVisited(int vertex, int count)
	{
		if(vertex < vertices)
		{
			visitedArray[vertex] = count;
		}
	}
	
	int getVisited(int vertex)
	{
		if(vertex < vertices)
		{
			return visitedArray[vertex];
		}
		return 0;
	}
	int getVNum()
	{
		return vertices;
	}
	
	boolean isAdjacent(int vertexOne, int vertexTwo)
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
	
}
