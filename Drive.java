
public class Drive {

	public static void main(String[] args) throws Exception {
		String input = "3+4*2/(1-5)^2^3";
		Mathematics one = new Mathematics(input);
		one.displayInput();
		System.out.print(one.getResult());
		Stack s1 = new Stack();
		System.out.println(s1.peek());
		Node n1 = new Node();
		System.out.println(n1.getPayload());
	}

}
