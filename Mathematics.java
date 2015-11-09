
public class Mathematics {
	private Stack queue;
	private Stack oprand;
	private Stack input;
	
	public Mathematics(String input)
	{
		this.queue = new Stack();
		this.oprand = new Stack();
		this.input = parseString2Stack(input);
	}
	
	public void displayInput()
	{
		this.input.display();
	}
	
	public Stack parseString2Stack(String input)
	{
		Stack result = new Stack();
		String temp = "";
		int n = 0;
		if (input.charAt(0) == '-')
		{
			temp += '-';
			n++;
		}
		for (int i = n; i < input.length(); i++)
		{
			if (input.charAt(i) == ' ')
			{
				
			}
			else if (isNumber(input.charAt(i)))
			{
				temp += input.charAt(i);
			}
			else if(input.charAt(i) == '-' && !isNumber(result.peek()))
			{
				temp += input.charAt(i);
			}
			else
			{
				if (temp != "")
				{
					result.stacks.addEnd(temp);
				}
				result.stacks.addEnd(""+input.charAt(i));
				temp = "";
			}
		}
		if (temp != "")
		{
			result.stacks.addEnd(temp);
		}
		return result;
	}
	
	private int op_preced(char op)
	{
		int op_priority = 0;
		switch(op)
		{
			case '+':
				op_priority = 1;
				break;
			case '-':
				op_priority = 1;
				break;
			case '*':
				op_priority = 2;
				break;
			case '/':
				op_priority = 2;
				break;
			case '^':
				op_priority = 3;
				break;
			default:
				op_priority = 0;
				break;
		}
		return op_priority;
	}
	
	private Boolean isNumber(char num)
	{
		String numericString = "0123456789.";
		if (numericString.indexOf(num) > 0)
		{
			return true;
		}
		return false; 
	}
	
	private Boolean isNumber(String num)
	{
		int n = 0;
		if (num.charAt(0) == '-')
		{
			n++;
		}
		for (int i = n; i < num.length(); i++)
		{
			if (!isNumber(num.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	public String getResult() throws Exception
	{
		String currNumber = "";
		while (this.input.peek() != null)
		{
			String temp = this.input.pop();
			System.out.println(temp);
			if (temp == "(")
				//meet "(" start a new calculating operation
			{
				System.out.println(temp);
				parseLeftParenthesis();
			}
			else if (isNumber(temp))
				//meet a number
			{
				currNumber = temp;
			}
			else	//meet a arithmetic operator
			{
				if (temp != "^")
				{
					while ((oprand.peek() != null) && (op_preced(temp.charAt(0)) <= op_preced(oprand.peek().charAt(0))))
					{
						String rightNum = currNumber;
						String leftNum = queue.pop();
						char op = oprand.pop().charAt(0);
						currNumber = goOperation(leftNum, rightNum, op);
					}
				}
				queue.push(currNumber);
				oprand.push(temp);
				currNumber = "";
			}
		}
		while (oprand.peek() != null)
		{
			String rightNum = currNumber;
			String leftNum = queue.pop();
			char op = oprand.pop().charAt(0);
			currNumber = goOperation(leftNum, rightNum, op);
		}
		return currNumber;
	}

	public void parseLeftParenthesis() throws Exception
	{
		String currNumber = "";
		while (this.input.peek() != ")")
		{
			String temp = this.input.pop();
				if (temp == "(")
				//meet "(" start a new calculating operation
			{
				parseLeftParenthesis();
			}
			else if (isNumber(temp))
				//meet a digit char, concatenate it to a temperate string variable
			{
				currNumber = temp;
			}
			else	//meet a arithmetic operator
			{
				if (temp != "^")
				{
					System.out.println(oprand.peek());
					System.out.println(queue.peek());
					while ((oprand.peek() != null) && (op_preced(temp.charAt(0)) <= op_preced(oprand.peek().charAt(0))))
					{
						String rightNum = currNumber;
						String leftNum = queue.pop();
						char op = oprand.pop().charAt(0);
						currNumber = goOperation(leftNum, rightNum, op);
					}
				}
				queue.push(currNumber);
				oprand.push(temp);
				currNumber = "";
			}
		}
		while (oprand.peek() != null)
		{
			String rightNum = currNumber;
			String leftNum = queue.pop();
			char op = oprand.pop().charAt(0);
			currNumber = goOperation(leftNum, rightNum, op);
		}
		this.input.pop();
		this.input.push(currNumber);
	}
	
	private String goOperation(String leftNum, String rightNum, char op)
	{
		double leftNumber = Double.parseDouble(leftNum);
		double rightNumber = Double.parseDouble(rightNum);
		String result = "";
		switch(op)
		{
			case '^':
				result += Math.pow(leftNumber, rightNumber);
				break;
			case '*':
				result += leftNumber * rightNumber;
				break;
			case '/':
				result += leftNumber / rightNumber;
				break;
			case '+':
				result += leftNumber + rightNumber;
				break;
			case '-':
				result += leftNumber - rightNumber;
				break;
			default:
				break;
		}
		return result;
	}
	
}
