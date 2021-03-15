package fr.pops.simpleCalc;
import java.util.Deque;
import java.util.LinkedList;

public class Calculator {
	
	public double calculate(String expression) {
		Deque<String> stack = new LinkedList<>();
		int i = 0, j;

		while(i < expression.length()){
			j = i;
			StringBuilder number = new StringBuilder();
			
			//find the first number and add it to the stack
			while(j < expression.length() && expression.substring(i, j+1).matches("^-?[0-9]*[.]?[0-9]*$")){
				number.append(expression.charAt(j));
				j++;
				}
			stack.addLast(number.toString());
			i += j-i;
			
			//add the operator too
			if(i < expression.length())
				stack.addLast(expression.charAt(i)+"");
			i++;
			}
		
		String numA, op, numB;
		//handling the mult and div first
		while(stack.contains("*") || stack.contains("/")){
			numA = stack.pop();
			op = stack.pop();
			numB = stack.pop();

			switch(op){
				case "*":
				stack.push(""+(Double.parseDouble(numA) * Double.parseDouble(numB)));
				break;
				case "/":
				stack.push(""+(Double.parseDouble(numA) / Double.parseDouble(numB)));
				break;
				default:
				stack.addLast(op);
				stack.addLast(numA);
				stack.addFirst(numB);
				}
			}

		//Then the + and -
		if(stack.peekLast().equals("+")) stack.addLast(stack.pop());
		while(stack.size() > 1){
			numA = stack.pop();
			op = stack.pop();
			numB = stack.pop();
			
			switch(op){
				case "+":
				stack.addFirst(""+(Double.parseDouble(numA) + Double.parseDouble(numB)));
				break;
				case "-":
				stack.addFirst(""+(Double.parseDouble(numA) - Double.parseDouble(numB)));
				break;
				}
			}
		
		return Double.parseDouble(stack.pop());
		}
	}