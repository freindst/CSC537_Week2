
public class Drive {

	public static void main(String[] args) throws Exception {
		String input = "3+4*2/(1-5)^2^3";
		Mathematics one = new Mathematics(input);
		one.displayInput();
		System.out.print(one.getResult());
		String input1 = "3*3^3/(1+2+(2+4))";
		Mathematics two = new Mathematics(input1);
		two.displayInput();
		System.out.println(two.getResult());		
	}
}
