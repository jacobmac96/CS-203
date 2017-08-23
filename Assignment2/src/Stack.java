/*********************************************************************/
/*Jacob MacDermaid                                                   */
/*Login ID: macd0919                                                 */
/*CS-203, Summer 2017                                                */
/*Programming Assignment 2                                           */
/*Stack Class: This class is a generic stack class                   */
/*********************************************************************/

public class Stack {
	int[] stack =  null;
	int count = 0;
	
    /*****************************************************************/
    /*Method: Stack                                                  */
    /*Purpose:  constructor for stack                                */
    /*Parameters:                                                    */
    /*  String int size: number of nodes                             */
    /*  Returns: void                                                */
    /*****************************************************************/
	
	Stack(int size)
	{
		stack = new int[size];
	}
	
    /*****************************************************************/
    /*Method: push                                                   */
    /*Purpose:  add vertex to stack                                  */
    /*Parameters:                                                    */
    /*  int vertex: the vertex to be added                           */
    /*  Returns: void                                                */
    /*****************************************************************/
	
	public void push(int vertex)
	{
		
		stack[count] = vertex;
		count++;
	}
	
    /*****************************************************************/
    /*Method: pop                                                    */
    /*Purpose:  remove vertex to stack                               */
    /*Parameters:                                                    */
    /*   void:                                                       */
    /*  Returns: int: vertex at top of stack                         */
    /*****************************************************************/
	
	public int pop()
	{
		int vertex = stack[(count -1)];
		if(count == 0)
		{
			return -1;
		}
		count--;
		return vertex;
	}
	
	   
    /*****************************************************************/
    /*Method: peak                                                   */
    /*Purpose:  look at top of stack                                 */
    /*Parameters:                                                    */
    /*   void:                                                       */
    /*  Returns: int: vertex at top of stack                         */
    /*****************************************************************/
	
	public int peak()
	{
		return stack[(count - 1)];
	}
	
    
  /*****************************************************************/
  /*Method: isEmpty                                                */
  /*Purpose: Check if stack is empty                               */
  /*Parameters:                                                    */
  /*   void:                                                       */
  /*  Returns: boolean: is the stack empty                         */
  /*****************************************************************/
	
	public boolean isEmpty()
	{
		if(count == 0)
			return true;
		else
			return false;
	}
}
