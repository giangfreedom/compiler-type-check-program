package deadcode;

public class check {
	//private String	input;
	
	//public check (String in){
	//	input = in;
	//}
	
	
	// input param is a string
	// check to see if the string is "int"
	// return true if it is else false
	public static boolean isint (String target) {
		return target.equals("int");
	}
	
	// input param is a string
	// check to see if the string is "main"
	// return true if it is else false
	public static boolean ismain (String target) {
		return target.equals("main");
	}

	// input param is a string
	// check to see if the string is "("
	// return true if it is else false
	public static boolean isLeftParenthesis (String target) {
		return target.equals("(");
	}
	
	// input param is a string
	// check to see if the string is ")"
	// return true if it is else false
	public static boolean isRightParenthesis (String target) {
		return target.equals(")");
	}
	
	// input param is a string
	// check to see if the string is "()"
	// return true if it is else false
	public static boolean isFullParenthesis (String target) {
		return target.equals("()");
	}
	
	// input param is a string
	// check to see if the string is "{"
	// return true if it is else false
	public static boolean isOpenCurlyBrace (String target) {
		return target.equals("{");
	}
	
	// input param is a string
	// check to see if the string is "}"
	// return true if it is else false
	public static boolean isCloseCurlyBrace (String target) {
		return target.equals("}");
	}
	
	// input param is a string
	// check to see if the string is "="
	// return true if it is else false
	public static boolean isEqual (String target) {
		return target.equals("=");
	}
	// input param is a string
	// check to see if the string is "<"
	// return true if it is else false
	public static boolean issmaller (String target) {
		return target.equals("<");
	}
	// input param is a string
	// check to see if the string is ">"
	// return true if it is else false
	public static boolean isgreater (String target) {
		return target.equals(">");
	}
	// input param is a string
	// check to see if the string is "=="
	// return true if it is else false
	public static boolean isequalequal (String target) {
		return target.equals("==");
	}
	
	// input param is a string
	// check to see if the string is ">="
	// return true if it is else false
	public static boolean isgreaterequal (String target) {
		return target.equals(">=");
	}
	
	// input param is a string
	// check to see if the string is "<="
	// return true if it is else false
	public static boolean issmallerequal (String target) {
		return target.equals("<=");
	}
	
	// input param is a string
	// check to see if the string is "printf"
	// return true if it is else false
	public static boolean isprintf (String target) {
		return target.equals("printf");
	}	
	
	// input param is a string
	// check to see if the string is integer
	// return true if it is else false
	public static boolean isinteger (String target) {
		int myint = Integer.parseInt(target);
		return (myint == (int) myint);
	}
	
	// input param is a string
	// check to see if the string is double
	// return true if it is else false
	public static boolean isdouble (String target) {
		double mydouble = Double.parseDouble(target);
		return (mydouble == (double) mydouble);
	}
	
	// input param is a string
	// check to see if the string is character array (string)
	// return true if it is else false
	public static boolean ischarArray (String target) {
		if(target.contains("[0-9]+")){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean isdatatype (String target){
		return (target.equals("int") || target.equals("double") 
				|| target.equals("char") || target.equals("float")
				|| target.equals("void"));
	}
	// parser long
	
	// parser float
		
}
