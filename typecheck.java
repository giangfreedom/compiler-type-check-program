package simpleTypeCheck;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class typecheck {
	//private static String mystring = "";
	private static int openbracecount = 0;
	private static int closebracecount = 0;
	private static int maincount = 0;
	
	private HashMap<String, String[]> mIndex;
	/*
	public typecheck(String input){
		mystring = input;
	}*/
	
	// remove all comma, semi colon
	public static String RemoveAllCommaNSemicolon(String in){
		in = in.replace(",", "");
		in = in.replace(";", "");
		in = in.trim();
		return in;
	}

	public static void check(String input){
		PatternMatching mycheck = new PatternMatching();
		input = RemoveAllCommaNSemicolon(input);
		// keep track of declared variable and function
		// so when we get an expression of the form
		// assignment we can check to see if
		// the variable exist , and does the function
		// return type match the variable type.
		
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
		if(input.contains("main()")){
			maincount++;
			if(maincount > 1){
				System.out.println("error code 1");
			}
		}
		// split the string into array of string
		String[] arr = input.split("\\s+");
		// the string begin with a primitive type
		if(mycheck.visitPrimitive(arr[0])){
			// match the string against pattern
			
			// match against main pattern
			// output accepted 1 if it pass
			// int main()
			if(mycheck.visitmain(input)){
				System.out.println("accepted 1");
			}
			// match against func declaration
			// exp: int myfunc (int xyz) 
			else if(mycheck.visitfunction(input)){
				System.out.println("accepted 2");
			}
			// match against variable declaration
			// int a = 1;
			
			// int a;						
		}
		// the string start with a variable name
		else if(mycheck.visitVariable(input)){
			// a = 1 + 1;
			// a = 5;
			// a + b = c + d;
		}
		// start with return 
		
		// start with function name follow by ()
		// for this one we need to save function name in
		// the hashmap every time we matched a function pattern
		// so we can remember the function declation/param/returntype
		// for futher checking
		
		// if we get here the line failed all matching
		else{
			System.out.println("failed to pass the typecheck");
		}
			
	}

}
