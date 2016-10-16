package simpleTypeCheck;

import java.util.Scanner;

public class typecheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner("C\\user\\file.txt");
		check test = new check();
		int openbracecount = 0;
		int closebracecount = 0;
		int maincount = 0;
		// keep track of declared variable and function
		// so when we get an expression of the form
		// assignment we can check to see if
		// the variable exist , and does the function
		// return type match the variable type.
		
		// as long as there is a nextline we continue to read
		while(in.hasNextLine()){
			String input = in.nextLine();
			//remove semicolon
			if(input.contains(";")){
				input.replace(";", "");
				input = input.trim();
			}
			//check number of open and close curly brace
			if(input.contains("{")){
				openbracecount++;
				input.replace("{", "");
				input = input.trim();
			}
			if(input.contains("}")){
				closebracecount++;
				input.replace("}", "");
				input = input.trim();
			}
			// check to make sure we only have 1 main
			if(input.contains("main")){
				maincount++;
			}
			if(maincount > 1){
				System.out.println("error 1");
				break;
			}
			// move to next line if we get empty string after remove
			// ; or { or }
			if(input.isEmpty()){
				continue;
			}
			//check for function call first
			// function call with empty argument
			if (input.charAt(input.length()-1) == ')' && input.charAt(input.length()-2) == '('){
				// case fool ()
				// case fool ()
				// case a = fool ()
			}
			
			String[] arr = input.split("\\s+");
			
			try {
				switch(arr[0]){
				// if the line begin with the word "int"
				case "int":
					// int a = 1;
					if((arr.length == 4) && test.ischarArray(arr[1]) && test.isEqual(arr[2]) && test.isint(arr[3])){
						System.out.println("accepted 1");
					}
					// int a;
					else if((arr.length == 2) && test.ischarArray(arr[1])){
						System.out.println("accepted 2");
					}
					// int main ()
					else if((arr.length == 3) && test.ismain(arr[1]) && test.isFullParenthesis(arr[2])){
						System.out.println("accepted 3");
					}
					// function declaration where int is return type
					// int funcname ()
					else if((arr.length == 3) && test.ischarArray(arr[1]) && test.isFullParenthesis(arr[2])){
						// duplicate procedures maybe an arraylist to save all of the function name
						// then check if the function name is in the list of not?
						System.out.println("accepted 3");
					}
					// int funcname ( int name )
					else if((arr.length == 6) && test.ischarArray(arr[1]) && test.isLeftParenthesis(arr[2])
							&& test.isdatatype(arr[3]) && test.ischarArray(arr[4]) && test.isRightParenthesis(arr[5])){
						System.out.println("accepted 4");
					}
					// int a = 1 + 1;
					// int a = 1 + 1 + 1;
					// endless cases if we keep on checking this way
					else {
						System.out.println("error begin with int");
					}
					break;
					
				case "double":
					// double a;
					// double a = 1;
					// double a = 1 + 1;
					break;
					
				case "printf":
					// printf ( abcdefg );
					break;
				default:
					System.out.println("all case failed");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error switch statement");
			}
		}

	}
		
}
