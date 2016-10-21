package simpleTypeCheck;

import java.util.regex.Pattern;

public class PatternMatching {
	// a single char lower or upper case
	private static final String c = "[a-zA-z]+";
	// a single number 0-9
	private static final String n = "[0-9]";
	   
	private static final String patternPrimitive = ("int|double|char|float|long|short|bool");
	
	private static final Pattern primitive = Pattern.compile(patternPrimitive);
	   
	private static final String patternPrimitiveArray = ("int[]|double[]|char[]|float[]|long[]|short[]");
	   
	private static final String patternReturntype = ("int|double|char|float|long|short|bool|void");
	    
	//private static final String patternmain = patternPrimitive + " main()";
	
	private static final String patternmain = "int main()";		

	private static final Pattern main = Pattern.compile(patternmain);
	
	private static final String patternfuncname = c;
	
	private static final String patternvariablename = "^" + c + "[a-zA-z0-9]*" +"$";
	private static final Pattern variable = Pattern.compile(patternvariablename);
	
	private static final String patternparameter = primitive + patternvariablename +"$";
	private static final Pattern parameter = Pattern.compile(patternparameter);
	
	private static final String patternfuncheader = patternReturntype +"\\s"+ patternfuncname +"\\s(" + patternparameter + ")$";
	
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
