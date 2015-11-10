
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
	
	//convert input string into a stack of numbers and operators
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
	
	//define the priority of an operator
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
	
	//check whether a char is a number
	//period is considered as legal as it may be a float
	private Boolean isNumber(char num)
	{
		String numericString = "0123456789.";
		if (numericString.indexOf(num) < 0)
		{
			return false;
		}
		return true; 
	}
	
	//check whether a string is a number, including negative value
	private Boolean isNumber(String num)
	{
		if (num.charAt(0) == '-')
		{
			if (num.length() < 2)
			{
				return false;
			}
			for (int i = 1; i < num.length(); i++)
			{
				if (!isNumber(num.charAt(i)))
				{
					return false;
				}
			}
		} else
		{
			for (int i = 0; i < num.length(); i++)
			{
				if (!isNumber(num.charAt(i)))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	//get the result
	public String getResult() throws Exception
	{
		String currNumber = "";
		while (this.input.peek() != null)
		{
			String temp = this.input.pop();
			if (temp.charAt(0) == '(')
				//meet "(" start a new calculating operation
			{
				parseLeftParenthesis();
			}
			else if (isNumber(temp))
				//meet a number
			{
				currNumber = temp;
			}
			else	//meet a arithmetic operator
			{
				if (temp.charAt(0) != '^')
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

	//if there is a left parenthesis, go to a new instance
	public void parseLeftParenthesis() throws Exception
	{
		String currNumber = "";
		Stack queue = new Stack();
		Stack oprand = new Stack();
		while (this.input.peek().charAt(0) != ')')
		{
			String temp = this.input.pop();
			if (temp.charAt(0) == '(')
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
				if (temp.charAt(0) != '^')
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
		this.input.pop();
		this.input.push(currNumber);
	}
	
	//calculate and return the result of two numbers and an operator
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
