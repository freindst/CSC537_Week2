
public class Stack {
	public LinkedList stacks;
	
	public Stack()
	{
		this.stacks = new LinkedList();
	}
	
	public Stack(String input)
	{
		this.stacks = new LinkedList(input);
	}
	
	public Stack push(String value)
	{
		this.stacks.addFront(value);
		return this;
	}
	
	public String pop() throws Exception
	{
		return this.stacks.removeFront();
	}
	
	public String peek()
	{
		if (this.stacks.getCount() == 0)
		{
			return null;
		}
		return this.stacks.getHead();
	}
	
	public void display()
	{
		this.stacks.display();
	}
}
