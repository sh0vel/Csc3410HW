/*
 * Shovon Hossain
 * Calculator program - Bhola
 * 11/2/14 
 *
 * The purpose of this program is to convert any given infix expression and
 * convert it to postfix and solve using stack. It contains two static String 
 * variables: inExpr and postExpr which is used to store the infix and postfix 
 * versions of the expression. It also uses two static stacks, one for
 * characters, called stack which is used to convert from in fix to post fix 
 * and the other uses String, called calcStack which is used to calculate
 *  the post fix expression. Upon execution the user is to enter any
 * expression which is converted into post fix and displayed. It will be
 * checked for errors using the checkValidity method. The checkValidity
 * method checks for common errors in the expression the user enters. If an
 * error exits in the expression making it uncomputable, the program will
 * display error and exit. If no errors are found, the method will call on
 * the unaryOp method. The unoaryOp method focuses on unary operators. It
 * goes through the inExpr string and looks for + and - , when found it
 * checks if the char to left of the + or - is and operator or an operand.
 * If its an operator the + or - is declared an unary operator, if the left
 * char is an operand, the+ or - is declared a binary operator. If this
 * method finds an unary + it will remove it from the expression since all
 * numbers are considered positive from the start. If a unary - is found, it
 * will be changed into an underscore. This method will update inExpr and
 * then call on the toPostfix method. The toPostfix method uses a stack to
 * convert inExpr into a post fix expression. It makes use of the isOp and
 * stackOp methods. It checks for binary operators using the isOp method and
 * when one is found its checks if the stack is empty, if so it will push
 * the operator into the stack, else it calls on the stackOp method to
 * appropriately place into the stack and append it to postExpr. For all non
 * operators the method instantly appends it to the postExpr string.
 * postExpr will eventually become the post fix version of inFix. The
 * resulting expression will then be displayed and if the expression
 * contains the variable x, the calculateX method will be started else the
 * calculate method starts. The isOp method has to versions, one that takes
 * Staring parameter and one that takes a char, they both simply return true
 * if the parameters is an operator, else false is returned The toPostfix
 * method uses a stack to convert inExpr into a post fix expression. It
 * makes use of the isOp and stackOp methods. It checks for binary operators
 * using the isOp method and when one is found its checks if the stack is
 * empty, if so it will push the operator into the stack, else it calls on
 * the stackOp method to appropriately place into the stack and append it to
 * postExpr. For all non operators the method instantly appends it to the
 * postExpr string. postExpr will eventually become the post fix version of
 * inFix. The resulting expression will then be displayed and if the
 * expression contains the variable x, the calculateX method will be started
 * else the calculate method starts. the calculateX method continuously asks
 * the user to enter a value for x, it then replaces all the x variables in
 * the postExpr and calls on the calculate method with the updated postExpr
 * as its parameter. If the user enters q for x, the program terminates. The
 * calculate method takes postExpr as its parameter and uses a stack along
 * with the doOperator method to solve the expression. It takes the postExpr
 * changes the underscored numbers to negative and creates an array with one
 * integer or operator per element. It then checks each element of the array
 * for an operator, if it isn't, the integer is pushed into the stack. If it
 * does encounter an operator, it will pop the stack and assign returned
 * value to the int variable right. It then pops the stack again and assigns
 * the value to the int variable left. It then calls the doOperator method
 * using the variables right, left and the operator as the parameter. The
 * doOperator pushes a new value into the stack and this process repeats
 * until there is only one element left in the stack and there is nothing
 * left to push. The remaining elements is the answer to the expression and
 * is popped and printed. The doOperator has three parameters, left right
 * and operator. It simply uses a switch to calculate left and right using
 * the given operator. The result of the operation is then pushed into the
 * stack. This method is used by the calculate method.
 */

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
	static Stack<Character> stack = new Stack<Character>();
	static Stack<String> calcStack = new Stack<String>();
	static Scanner UI = new Scanner(System.in);
	static String inExpr;
	static String postExpr = "";

	public static void main(String[] args) {
		System.out.print("Enter infix expression: ");
		inExpr = UI.nextLine().trim().replaceAll(" +", "");
		checkValidity();

	}

	/*
	 * The checkValidity method checks for common errors in the expression the
	 * user enters. If an error exits in the expression making it uncomputable,
	 * the program will display error and exit. If no errors are found, the
	 * method will call on the unaryOp method.
	 */
	public static void checkValidity() {
		int openP = 0;
		int closeP = 0;
		for (int i = 0; i < inExpr.length(); i++) {
			if (inExpr.charAt(i) == '(') {
				openP++;
			} else if (inExpr.charAt(i) == ')') {
				closeP++;
			}
		}
		if (openP != closeP) {
			System.out
					.println("Error in expression: Amount of opening and closing paranthesis dont match.");
			System.exit(0);
		}
		if (inExpr.contains("-*") || inExpr.contains("-/")
				|| inExpr.matches(".*[^*+-/%][(].*") || inExpr.contains("-)")
				|| inExpr.contains("-%") || inExpr.contains("+/")
				|| inExpr.contains("+*") || inExpr.contains("+)")
				|| inExpr.contains("+%") || inExpr.contains("**")
				|| inExpr.contains("*/") || inExpr.contains("*)")
				|| inExpr.contains("*%") || inExpr.contains("//")
				|| inExpr.contains("/*") || inExpr.contains("/)")
				|| inExpr.contains("/%") || inExpr.contains("(*")
				|| inExpr.contains("(/") || inExpr.contains("(%")
				|| inExpr.endsWith("+") || inExpr.endsWith("-")
				|| inExpr.endsWith("*") || inExpr.endsWith("/")
				|| inExpr.endsWith("(") || inExpr.endsWith("%")) {
			System.out
					.println("Error in expression: Invalid opperator placement");
			System.exit(0);
		}

		if (inExpr.contains(".")) {
			System.out
					.println("Error in expression: Decimal values not allowed");
			System.exit(0);
		}
		unaryOp();

	}

	/*
	 * The unoaryOp method focuses on unary operators. It goes through the
	 * inExpr string and looks for + and - , when found it checks if the char to
	 * left of the + or - is and operator or an operand. If its an operator the
	 * + or - is declared an unary operator, if the left char is an operand, the
	 * + or - is declared a binary opperator. If this method finds an unary + it
	 * will remove it from the expression since all numbers are considered
	 * positive from the start. If a unary - is found, it will be changed into
	 * an underscore. This method will update inExpr and then call on the
	 * toPostfix method.
	 */
	public static void unaryOp() {

		String unaryInFix = "";
		char left;
		for (int i = 0; i < inExpr.length(); i++) {
			char charAtI = inExpr.charAt(i);
			if (i == 0) {
				if (charAtI == '+') {
					unaryInFix += "";
				} else if (charAtI == '-') {
					unaryInFix += "_";
				} else {
					unaryInFix += charAtI;
				}
			} else {
				left = inExpr.charAt((i - 1));
				if ((charAtI != '+') && (charAtI != '-')) {
					unaryInFix += charAtI;
				} else if (charAtI == '+') {
					if (isOp(left) && !(left == ')')) {
						unaryInFix += "";
					} else {
						unaryInFix += "+";
					}
				} else if (charAtI == '-') {
					if (isOp(left) && left != ')') {
						unaryInFix += "_";
					} else {
						unaryInFix += "-";
					}
				}
			}
		}
		inExpr = unaryInFix;
		toPostfix();
	}

	/*
	 * The toPostfix method uses a stack to convert inExpr into a post fix
	 * expression. It makes use of the isOp and stackOp methods. It checks for
	 * binary operators using the isOp method and when one is found its checks
	 * if the stack is empty, if so it will push the operator into the stack,
	 * else it calls on the stackOp method to appropriately place into the stack
	 * and append it to postExpr. For all non operators the method instantly
	 * appends it to the postExpr string. postExpr will eventually become the
	 * post fix version of inFix. The resulting expression will then be
	 * displayed and if the expression contains the variable x, the calculateX
	 * method will be started else the calculate method starts.
	 */
	public static void toPostfix() {
		int i = 0;
		while (i < inExpr.length()) {
			if (!isOp(inExpr.charAt(i))) {
				postExpr += inExpr.charAt(i);
			} else if (isOp(inExpr.charAt(i))) {
				if (!(inExpr.charAt(i) == '(') || !(inExpr.charAt(i) == '(')) {
					postExpr += " ";
				}
				if (stack.isEmpty()) {
					stack.push(inExpr.charAt(i));
				} else {
					stackOp(inExpr.charAt(i));
				}
			}
			i++;
		}
		while (!stack.isEmpty()) {
			postExpr += " ";
			postExpr += stack.pop();
		}
		postExpr = postExpr.trim().replaceAll(" +", " ");
		System.out.println("Converted postfix expression: " + postExpr);
		if (postExpr.contains("x")) {
			calculateX();
		} else {
			calculate(postExpr);
		}
	}

	/*
	 * This isOp method simply returns true if the given char parameter is an
	 * operator or a parenthesis, else false is returned. this method is used in
	 * the toPostfix method.
	 */
	public static boolean isOp(char ch) {
		if (ch == '-' || ch == '+' || ch == '*' || ch == '/' || ch == '%'
				|| ch == '(' || ch == ')') {
			return true;
		}
		return false;
	}

	/*
	 * This isOp method simply returns true if the given String parameter is and
	 * operator, else it returns false. This method is used in the calculate
	 * method.
	 */
	public static boolean isOp(String ch) {
		if (ch.equals("-") || ch.equals("+") || ch.equals("*")
				|| ch.equals("/") || ch.equals("%")) {
			return true;
		}
		return false;
	}

	/*
	 * The stackOp method takes a char ch as its parameter which is a operator
	 * or parenthesis giving to it by the toPostfix method. The given parameter
	 * is then compared to the top of the stack using peek. If ch has lower or
	 * equal precedence then the top of stack, it will pop the stack and append
	 * the output to postExpr. For + and - operators, it will check the stack
	 * twice given its empty because if a * % / is at the top, a + or - may be
	 * beneath those. If the ch doesn't have a lower or equal precedence it will
	 * be pushed onto the stack. If ch is an opening parenthesis, it will be
	 * pushed. If ch is a closing parenthesis, the stack will be popped and
	 * appended to postExpr until an opening parenthesis is reached. The opening
	 * Parenthesis will then be popped. This method is to help the toPostfix
	 * method convert the inExpr to post fix.
	 */
	public static void stackOp(char ch) {

		switch (ch) {
		case '-':
			if (stack.peek() == '*' || stack.peek() == '/'
					|| stack.peek() == '-' || stack.peek() == '+'
					|| stack.peek() == '%') {
				postExpr += stack.pop();
				postExpr += " ";
				if (!stack.isEmpty() && stack.peek() == '-' || !stack.isEmpty()
						&& stack.peek() == '+') {
					postExpr += stack.pop();
					postExpr += " ";
				}
			}
			stack.push(ch);
			break;
		case '+':
			if (stack.peek() == '-' || stack.peek() == '+'
					|| stack.peek() == '*' || stack.peek() == '/'
					|| stack.peek() == '%') {
				postExpr += stack.pop();
				postExpr += " ";
				if (!stack.isEmpty() && stack.peek() == '-' || !stack.isEmpty()
						&& stack.peek() == '+') {
					postExpr += stack.pop();
					postExpr += " ";
				}
			}
			stack.push(ch);
			break;
		case '*':
			if (stack.peek() == '-' || stack.peek() == '+'
					|| stack.peek() == '(') {
				stack.push(ch);
			} else if (stack.peek() == '*' || stack.peek() == '/'
					|| stack.peek() == '%') {
				postExpr += stack.pop();
				postExpr += " ";
				stack.push(ch);
			}
			break;
		case '/':
			if (stack.peek() == '-' || stack.peek() == '+'
					|| stack.peek() == '(') {
				stack.push(ch);
			} else if (stack.peek() == '*' || stack.peek() == '/'
					|| stack.peek() == '%') {
				postExpr += stack.pop();
				postExpr += " ";
				stack.push(ch);
			}
			break;
		case '%':
			if (stack.peek() == '-' || stack.peek() == '+'
					|| stack.peek() == '(') {
				stack.push(ch);
			} else if (stack.peek() == '*' || stack.peek() == '/'
					|| stack.peek() == '%') {
				postExpr += stack.pop();
				postExpr += " ";
				stack.push(ch);
			}
			break;
		case '(':
			stack.push(ch);
			break;
		case ')':
			while (!(stack.peek() == '(')) {
				postExpr += stack.pop();
				postExpr += " ";
			}
			stack.pop();
			break;
		}
	}

	/*
	 * the calculateX method continuously asks the user to enter a value for x,
	 * it then replaces all the x variables in the postExpr and calls on the
	 * calculate method with the updated postExpr as its parameter. If the user
	 * enters q for x, the program terminates
	 */
	public static void calculateX() {

		while (true) {
			System.out.print("Enter value of x: ");
			String x = "x";
			x = UI.next();
			if (x.equals("q")) {
				System.out.println("\nExiting program... \nProgram terminated");
				System.exit(0);
			}
			calculate(postExpr.replaceAll("[xX]", x));
		}
	}

	/*
	 * The calculate method takes postExpr as its parameter and uses a stack
	 * along with the doOperator method to solve the expression. It takes the
	 * postExpr changes the underscored numbers to negative and creates an array
	 * with one integer or operator per element. It then checks each element of
	 * the array for an operator, if it isnt, the integer is pushed into the
	 * stack. If it does encounter an operator, it will pop the stack and assign
	 * returned value to the int variable right. It then pops the stack again
	 * and assigns the value to the int variable left. It then calls the
	 * doOperator method using the variables right, left and the operator as the
	 * parameter. The doOperator pushes a new value into the stack and this
	 * procces repeats until there is only one element left in the stack and
	 * there is nothing left to push. The remaining elements is the answer to
	 * the expression and is popped and printed.
	 */
	public static void calculate(String postExpr) {

		String toCalc[] = postExpr.replaceAll("_", "-").split(" ");
		int left, right;

		int i = 0;
		while (i < toCalc.length) {
			if (!(isOp(toCalc[i]))) {
				calcStack.push(toCalc[i]);
			} else if (isOp(toCalc[i])) {
				right = Integer.parseInt(calcStack.pop());
				left = Integer.parseInt(calcStack.pop());
				doOperator(left, right, toCalc[i]);
			}
			i++;
		}
		System.out.println("Answer is: " + calcStack.pop());
	}

	/*
	 * The doOperator has three parameters, left right and operator. It simply
	 * uses a switch to calculate left and right using the given operator. The
	 * result of the operation is then pushed into the stack. This method is
	 * used by the calculate method.
	 */
	public static void doOperator(int left, int right, String operator) {
		String result = "";
		switch (operator) {
		case "+":
			result += (left + right);
			calcStack.push(result);
			break;
		case "-":
			result += (left - right);
			calcStack.push(result);
			break;
		case "*":
			result += (left * right);
			calcStack.push(result);
			break;
		case "/":
			result += (left / right);
			calcStack.push(result);
			break;
		case "%":
			result += (left % right);
			calcStack.push(result);
		}
	}
}
