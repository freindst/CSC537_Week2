
public class LinkedList {
	private Node head;
	private int count;

	public LinkedList() {
		this.head = null;
		this.count = 0;
	}
	
	public LinkedList(String string)
	{
		this.head = null;
		this.count = 0;
		for (int i = 0; i < string.length(); i++)
		{
			if (string.charAt(i) != ' ')
			{
				this.addEnd("" + string.charAt(i));
			}			
		}
	}
	
	public void addFront(String value) {
		this.count++;
		Node n = new Node(value);
		n.setNextNode(this.head);
		this.head = n;
	}
	
	public String removeFront() throws Exception
	{
		if(this.head == null)
		{
			throw new Exception("Empty List Exception: Can't remove from empty list");
		}
		else
		{
			Node nodeToReturn = head;
			head = head.getNextNode();
			nodeToReturn.setNextNode(null);
			this.count--;
			return nodeToReturn.getPayload();
		}
	}
	
	public void addEnd(String value) {
		this.count++;
		if (head == null) {
			this.addFront(value);
		} else {
			Node n = new Node(value);
			Node currNode = head;
			while(currNode.getNextNode() != null) {
				currNode = currNode.getNextNode();
			}
			currNode.setNextNode(n);
		}
	}
	
	public String removeEnd() throws Exception
	{
		if(head == null)
		{
			return this.removeFront();
		}
		else
		{
			this.count--;
			Node currNode = head;
			while(currNode.getNextNode() != null && currNode.getNextNode().getNextNode() != null)
			{
				currNode = currNode.getNextNode();
			}
			if(currNode == head && currNode.getNextNode() == null)
			{
				head = null;
				return currNode.getPayload();
			}
			else
			{
				Node nodeToReturn = currNode.getNextNode();
				currNode.setNextNode(null);
				return nodeToReturn.getPayload();
			}
		}
	}
	
	public String getHead()
	{
		if (this.head == null)
		{
			return "";
		}
		return this.head.getPayload();
	}
	
	public void display()
	{
		if(head == null)
		{
			System.out.println("Empty List");
		}
		else
		{
			System.out.println("Count: " + this.count);
			this.head.display();
		}
	}
	
	public int getCount()
	{
		return this.count;
	}
}
