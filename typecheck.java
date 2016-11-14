package simpleTypeCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class typecheck {
	//private static String mystring = "";
	private static int openbracecount = 0;
	private static int closebracecount = 0;
	private static int maincount = 0;
	private static int functioncount = 0;
	private static int returncount = 0;
	
	// hash map that store the function info
	// key is function name, value is an arraylist
	// 1st item in arraylist is function return type
	// 2nd item is function unique id
	// 3rd is function param count
	// 4th param return type, 5th param name
	// 6th param return type, 7th param name
	// calculator add/minus/mul/ div functions only have 2 param.
	private static HashMap<String, ArrayList<String>> mFunctionIndex;
	
	// hashmap that store variable info
	// key is variable name, value is the variable type
	private static HashMap<String, String> mVariableIndex;
	
	// hash map that store return info
	// key is the number of return start at 1 increase by 1 
	// for every return we encounter, value is the variable
	// name that we are returning, use it to access the
	// variable hashmap to obtain the type of the variable.
	// the key (number) match the function unique ID,
	// function unique ID = 1 mean it is linked to return
	// key = 1
	private static HashMap<Integer, String> mReturnIndex;
	
	// hashmap that store pointer info
	// key is pointer name, value is the pointer type
	private static HashMap<String, String> mPointerIndex;
	
	
	/*
	public typecheck(String input){
		mystring = input;
	}*/
	
	// remove all comma, semi colon
	public static String RemoveAllCommaNSemicolon(String in){
		in = in.replace(",", " ");
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
		// trim the string and remove everything un needed
		// but keep the original input string to match the pattern
		String ss = RemoveAllCommaNSemicolon(input);
		// split the string into array of string
		// this arr is global to all if else if statement but if i forget
		// i can still use input and do the trimming inside the block.
		String[] arr = ss.split("\\s+");
		
		// check to make sure we only have 1 main
		// and main pass the pattern int main()
		if(input.contains("main()")){
			maincheck(input, mycheck);
		}
		// check function pattern
		else if(mycheck.visitfunction(input)){
			// check to see if the function name (procedure ID)
			// already appear in the hashmap
			// if it does error 3 no duplicate procedure ID allowed
			// get key from mFunctionIndex and compare to arr[1](the func name)
			if(isFunctionNameDuplicate(arr[1])){
				// we found duplicate error 3
				System.out.println("error 3");
			}
			// no duplicated name found
			// populate the function hashmap with the function information
			else{
				functionPopulate(input);
				System.out.println("function header passed");
			}
		}
		// check variable declaration pattern
		else if(mycheck.visitVariableDeclaration(input)){
			// check for variable duplicate
			if(isVariableNameDuplicate(arr[1])){
				// we found duplicate error 3
				System.out.println("error 4 variable name duplicate");
			}
			else{
				// get the variable name and data type
				// save them in the variable hashmap
				// arr[1] var name, arr[0] var type
				mVariableIndex.put(arr[1], arr[0]);
				System.out.println("variable declaration passed");
			}
		}
		// check function call
		else if(mycheck.visitFunctionCall(input)){
			boolean pass = true;
			String s = input;
			int j = 4;
			s = RemoveAllCommaNSemicolon(s);
			s = s.replaceAll("(", " ");
			s = s.replaceAll(")", "");
			String sarr[] = s.split(" ");
			// 1st item in array is function name
			// go into the function hashmap to check for it existence
			if(!isFunctionNameDuplicate(sarr[0])){
				// no duplicate = no found = function is not declared before it is call
				System.out.println("error 5 function name not found in hashmap");
				pass = false;
			}
			// check for number or argument and param
			// access the function hashmap get the value (arraylist) using
			// the key (function name), then get the 3rd index from array list
			// for the param number (count) compare it against number 2 (because
			// our caculator only have function using 2 argument/param
			else if(!(Integer.parseInt(mFunctionIndex.get(sarr[0]).get(2).trim()) == 2)){
				System.out.println("error 6 miss match argument/param number");
				pass = false;
			}
			else{
				// check argument type error , since argument is variable
				// go to variable hashmap grab the value (the type)
				// compare to the function hashmap param type.
				for(int i = 0; i < sarr.length-1; i++){
					// go to variable hashmap grab the value (the type) using sarr[i+1] the key
					// compare to the function hashmap param type starting at arraylist 4th index increase by 2 each loop.
					if(!(mVariableIndex.get(sarr[i+1]).equals(mFunctionIndex.get(sarr[0]).get(j)))){
						System.out.println("error 7 data type miss match argument/param");
						pass = false;
						break;
					}
					j = j+2;
				}
			}
			if(pass == true){
				System.out.println("function call passed");
			}
			// reset pass for next run
			pass = true;
		}
		else if(mycheck.visitReturn(input)){
			// check for return type
			// return varname;
			// remove the semicolon
			input = RemoveAllCommaNSemicolon(input);
			// arr[] should only have 2 item arr[0] = return, arr[1] = varname
			// pull out varname search variable hash table and get the varname data type
			// match it vs the function return type (which function? look for functionid = returncount
			// returncount start at zero everytime this condition pass it will increase by 1 before exit else if
			
			// pick up the return type (value) from variable hashmap by supply in the key(variable name)
			String varDatatype = mVariableIndex.get(arr[1]);
			// compare return type and function return type
			if(!(varDatatype.equals(functionReturnType(returncount)))){
				System.out.println("error code 8 function return type and return data type do not match");
			}
			// update returncount for next return statement
			returncount++;
		}
		else if(mycheck.visitVarAssignFunc(input)){
			// varname = functioncall(argument1, argument2);
			// if we get this statement
			// first we replace paranthesis with space then remove ; and ,
			input.replaceAll("(", " ");
			input.replaceAll(")", " ");
			// remove comma and semicolon
			input = RemoveAllCommaNSemicolon(input);
			input = input.trim();
			// we get this
			// varname = functioncall argument1 argument2
			// split them into an array using split and space delimiter
			String sVAF[] = input.split(" ");
			// pick up the variable data type by going to the variable hashmap
			// supply the key sVAF[0] and get the value (data type) out
			// compare it to the function return type (go to function hashmap
			// supply the function name for key and get value array then subscript 
			// zero to get function return type. Compare them if they do not match
			// output error 9 else they pass
			if(!(mVariableIndex.get(sVAF[0]).equals(mFunctionIndex.get(sVAF[2]).get(0)))){
				System.out.println("error code 9 function return type and variable data type do not match");
			}
		}

		// pointer declaration
		else if(mycheck.visitPointer(input)){
			// if it match the pointer declaration then add it into the pointer hashmap
			// along with the datatype, key for name, value for data type
			mPointerIndex.put(arr[2], arr[0]);			
		}
		// array declaration
		else if(mycheck.visitArray(input)){
			// add to the variable hash map key = array name value = array data type
			// example int n [ 10 ] ; arr[0] = data type, arr[1] = var name
			mVariableIndex.put(arr[1], arr[0]);
		}
		// fill in 10-16
		
		// 17 address of 
		// can only be applied to integers, chars, and indexed strings (string[i])
		else if(input.contains("&")){
			// loop throught the array to get to the item after &
			for(int i = 0; i < arr.length; i++){
				if(arr[i].equals("&")){
					// look for the one next to it and see if it is a int/char/string[i]
					// go to the variable table and search for the value(data type)
					if(!(mVariableIndex.get(arr[i+1]).equals("int")) && 
						!(mVariableIndex.get(arr[i+1]).equals("char")) && !isStringSub(arr[i+1])){
						// not int / char or string[] so output error code 17
						System.out.println("error code 17 & of something that is not int/char/string[]");
					}
					else{
						System.out.println("& passed");
					}
				}
			}
		}
		// check ^ error code 18 only be applied to integer pointers and char pointers
		// ^ var where var is a pointer
		else if(input.contains("^")){
			// loop throught the array to get to the item after ^
			for(int i = 0; i < arr.length; i++){
				if(arr[i].equals("^")){
					// look for the one next to it and see if it is a int/char pointer
					// go to the pointer table and search for the value(data type)
					if( !(mPointerIndex.get(arr[i+1]).equals("int")) && 
						!(mPointerIndex.get(arr[i+1]).equals("char")) ){
						// not int / char pointer so output error code 18
						System.out.println("error code 18 ^ of something that is not int/char pointer");
					}
					else{
						System.out.println("^ passed");
					}
				}
			}			
		}
		
		// if we get here the line failed all matching
		else{
			System.out.println("failed to pass the typecheck");
		}
			
	}
	
	// is String[] return true if we have a char type []
	// return false otherwise
	public static boolean isStringSub (String var) {
		PatternMatching c = new PatternMatching();
		// match pattern String[]
		if(c.visitArrSub(var)){
			var = var.replace("[", " ");
			String[] v = var.split(" ");
			// v[0] should be the variable name
			// now check the array hashmap to see is it data type 
			// = to char if it is we got a string[] else return false
			if(mVariableIndex.get(v[0]).equals("char")){
				return true;
			}
		}
		return false;
	}
	
	
	// take in returncount which is the equivalence of functioncount
	// get all of the item in function hashmap compare the functioncount
	// to returncount, if functioncount = returncount get the return type of
	// that function.
	public static String functionReturnType(int returncount){
		String[] funcnameList = getFunctionDictionary();
		
		// convert int to string and trim it
		String sreturncount = ""+returncount;
		sreturncount = sreturncount.trim();
		
		// loop the whole list of function name
		for(int i = 0; i < funcnameList.length; i++){
			// go to hashmap of function get the value using the function name key
			// value is an array and the 2nd item in the array is the function
			// unique id (function count) which match the returncount
			if(mFunctionIndex.get(funcnameList[i]).get(1).equals(sreturncount)){
				// we found the function for our return call
				// now get the return type from the function and return it.
				// function return type is 1st item in the arraylist
				return mFunctionIndex.get(funcnameList[i]).get(0);
			}
		}
		return null;
	}
	
	public static void maincheck(String input, PatternMatching mycheck){
		maincount++;
		// case of more than 1 main() appear
		if(maincount > 1){
			System.out.println("error code 1");
		}
		// case of int main() fail the pattern matching
		else if(!mycheck.visitmain(input)){
			System.out.println("error code 2");
			System.out.println("main fail pattern matching not neccessary main with argument");
		}
		else{
			System.out.println("main passed");
		}		
	}
	
	public static void functionPopulate(String input){
		// split the input using split function
		// add each item in array into the hashmap 
		// the function name will be the key
		// value is an arraylist of return type, function unique ID
		// param number(count), 1st param return type, 1st param name, 
		// 2nd param return type, 2nd param name etc...
		// split the string into array of string using space between word
		String[] arr = input.split("\\s+");
		ArrayList<String> value = new ArrayList<String>();
		
		// add the return type first
		value.add(arr[0]);
		
		// add the unique id, this id is used
		// to match it against return type hashmap
		value.add(""+functioncount);			
		// update function count by +1
		functioncount++;
		
		// add number of parameter (param count of the function)
		// the size of the arr[] - 4 (return type, name, open paren, close paren)
		value.add(""+((arr.length - 4)/2));
		
		// we have 1 or more param
		if(!((arr.length - 4)/2 == 0)){
			// loop through the arr[] add all param
			// return type and name into the value arraylist
			for (int i = 0; i < arr.length; i++){
				if(arr[i].equalsIgnoreCase("(")){
					// add param return type and name intp value list
					for(int j = i+1; j < arr.length; j= j+2){
						if(!arr[j].equalsIgnoreCase(")")){
							// param return type
							value.add(arr[j]);
							// param name
							value.add(arr[j+1]);
						}
					}
					break;
				}
			}
		}
		
		mFunctionIndex.put(arr[1], value);		
	}
	
	// loop throught the list of key(function name)  in mFunctionIndex
	// return true if found duplicate, false if there is no duplicate
	public static boolean isFunctionNameDuplicate(String functionName) {
		// we have nothing in the hashmap mean no function was added at all
		// no duplicate possible
		if(mFunctionIndex.isEmpty() == true){
			return false;
		}
		// we have at least 1 function added
		else{
			String[] funcnameList = getFunctionDictionary();
			// loop the whole list of function name
			for(int i = 0; i < funcnameList.length; i++){
				// if found an equal (duplicated) return false
				if(functionName.equals(funcnameList[i])){
					return true;
				}
			}
		}
		return false;
	}
	
	public static String[] getFunctionDictionary() {
		// TO-DO: fill an array of Strings with all the keys from the hashtable.
		// Sort the array and return it.
		List<String> keys = new ArrayList<String>();
		for ( String key : mFunctionIndex.keySet() ) {
			keys.add(key);
		}
			  
		String [] mystrarr = new String[keys.size()];
		for(int j = 0; j < mystrarr.length; j++){
			mystrarr[j] = keys.get(j);
		}
			  
		return mystrarr;
	}
	
	// loop throught the list of key(variable name)  in mVariableIndex
	// return true if found duplicate, false if there is no duplicate
	public static boolean isVariableNameDuplicate(String variableName) {
		// we have nothing in the hashmap mean no variable was added at all
		// no duplicate possible
		if(mVariableIndex.isEmpty() == true){
			return false;
		}
		// we have at least 1 variable added
		else{
			String[] variableList = getVariableDictionary();
			// loop the whole list of variable name
			for(int i = 0; i < variableList.length; i++){
				// if found an equal (duplicated) return false
				if(variableName.equals(variableList[i])){
					return true;
				}
			}
		}
		return false;
	}	
	
	public static String[] getVariableDictionary() {
		// TO-DO: fill an array of Strings with all the keys from the hashtable.
		// Sort the array and return it.
		List<String> keys = new ArrayList<String>();
		for ( String key : mVariableIndex.keySet() ) {
			keys.add(key);
		}
			  
		String [] mystrarr = new String[keys.size()];
		for(int j = 0; j < mystrarr.length; j++){
			mystrarr[j] = keys.get(j);
		}
			  
		return mystrarr;
	}	
	
}
