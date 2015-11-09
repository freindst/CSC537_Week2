
public class Node {
	private String payload;
	private Node nextNode;
	
	public Node()
	{
		this.payload = null;
		this.nextNode = null;
	}
	
	public Node(String payload) {
		this.payload = payload;
		this.nextNode = null;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	public void display(){
		{
			System.out.print(this.payload + " -> ");
			if(this.nextNode != null)
			{
				this.nextNode.display();
			}
			else
			{
				System.out.println("end");
			}
		}
	}
}
