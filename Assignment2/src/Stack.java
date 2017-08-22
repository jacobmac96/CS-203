
public class Stack {
	int[] stack =  null;
	int count = 0;
	
	Stack(int size)
	{
		stack = new int[size];
	}
	
	public void push(int vertex)
	{
		
		stack[count] = vertex;
		count++;
	}
	
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
	
	public int peak()
	{
		return stack[(count - 1)];
	}
	
	boolean isEmpty()
	{
		if(count == 0)
			return true;
		else
			return false;
	}
}
