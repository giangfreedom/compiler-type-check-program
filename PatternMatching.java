package simpleTypeCheck;

import java.util.regex.Pattern;

public class PatternMatching {
	// a single char lower or upper case
	private static final String c = "[a-zA-z]";
	// a number between 0-9 
	private static final String n = "[0-9]";
	
	// operator +/-/*/ /
	private static final Pattern patternOperator = Pattern.compile("[+|-|*|/]");
	
	//  true false boolean
	private static final Pattern patternBoolean = Pattern.compile("true|false");
	
	// left parathesis (
	private static final Pattern patternLParen = Pattern.compile("[(]");
	// right parathesis )
	private static final Pattern patternRParen = Pattern.compile("[)]");
	// comparasion
	private static final Pattern patternComparasion = Pattern.compile("<|>|<=|>=|==");
	//assignment
	private static final Pattern patternAssignment = Pattern.compile("=");
	// and or not
	private static final Pattern patternChoice = Pattern.compile("&&| || |!");
	// conditional
	private static final Pattern patternCondition = Pattern.compile("if|else if|while|do|for");
	
	private static final Pattern patternElse= Pattern.compile("else");
	
	private static final Pattern patternBreak= Pattern.compile("break");
	private static final Pattern patternCont= Pattern.compile("continue");
	
	// end of file
	private static final Pattern patternEOF= Pattern.compile("EOF");
	
	   
	//private static final String patternPrimitive = ("int|double|char|float|long|short|bool");
	
	private static final Pattern primitive = Pattern.compile("(^)int($)|(^)double($)|(^)char($)|(^)float($)|(^)long($)|(^)short($)|(^)bool($)");
	   
	private static final String patternPrimitiveArray = ("(^)int[]($)|(^)double[]($)|(^)char[]($)|(^)float[]($)|(^)long[]($)|(^)short[]($)");
	   
	private static final String patternReturntype = ("(^)int($)|(^)double($)|(^)char($)|(^)float($)|(^)long($)|(^)short($)|(^)bool($)|(^)void($)");
	    	
	private static final String patternmain = "int main()";		

	private static final Pattern main = Pattern.compile(patternmain);
	
	private static final String patternfuncname = "^" + c + "[a-zA-z0-9]{1,14}" +"$";
	
	private static final String patternvariablename = "^"+c + "[a-zA-z0-9]{1,14}$";
	private static final Pattern variable = Pattern.compile(patternvariablename);
	
	
	private static final String patternparameter = "[(^)int|(^)double|(^)char|(^)float|(^)long|(^)short|(^)bool]\\s[a-zA-z][a-zA-z0-9]{1,14}$";
	private static final Pattern parameter = Pattern.compile(patternparameter);
	
	private static final String patternfuncreturntype = ("[(^)int|(^)double|(^)char|(^)float|(^)long|(^)short|(^)bool|(^)void]");
	private static final String patternfuncheader = "[(^)int|(^)double|(^)char|(^)float|(^)long|(^)short|(^)bool|(^)void]\\s[a-zA-z][a-zA-z0-9]{1,14}\\s" + patternparameter;
	
	private static final Pattern funcheader = Pattern.compile(patternfuncheader);
	
	public static boolean visitVariable (String target) {
		return variable.matcher(target).find();
	}
	
	public static boolean visitParam (String target) {
		return parameter.matcher(target).find();
	}
	
	public static boolean visitPrimitive (String target) {
		return primitive.matcher(target).find();
	}
       
   	public static boolean visitmain (String target) {
		return main.matcher(target).find();
	}
   	
   	public static boolean visitfunction (String target) {
		return funcheader.matcher(target).find();
	}
   	
	// remove all comma, semi colon
	public String RemoveAllCommaNSemicolon(String token){
		token = token.replace(",", "");
		token = token.replace(";", "");
		return token;
	}

}
