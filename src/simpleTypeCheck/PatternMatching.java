package simpleTypeCheck;

import java.util.regex.Pattern;

public class PatternMatching {
	// a single char lower or upper case
	private static final String c = "[a-zA-z]";
	// a number between 0-9 
	private static final String n = "[0-9]";
	
	// operator +/-/*/ /
	private static final Pattern patternOperator = Pattern.compile("(^)+($)|(^)-($)|(^)*($)|(^)/($)");
	
	//  true false boolean
	private static final Pattern patternBoolean = Pattern.compile("true|false");
	
	// left parathesis (
	private static final Pattern patternLParen = Pattern.compile("(^)[(]($)");
	// right parathesis )
	private static final Pattern patternRParen = Pattern.compile("(^)[)]($)");
	// comparasion
	private static final Pattern patternComparasion = Pattern.compile("<|>|<=|>=|==");
	//assignment
	private static final Pattern patternAssignment = Pattern.compile("=");
	// and or not
	private static final Pattern patternChoice = Pattern.compile("&&|or|!");
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
	   
	private static final Pattern Returntype = Pattern.compile("(^)int($)|(^)double($)|(^)char($)|(^)float($)|(^)long($)|(^)short($)|(^)bool($)|(^)void($)");	

	private static final Pattern main = Pattern.compile("int main()");
	private static final Pattern mainret = Pattern.compile("return 0");
	
	private static final String patternfuncname = c + "[a-zA-z0-9]{1,14}";
	
	private static final String patternvariablename = "^[a-zA-z][a-zA-z0-9]{1,14}$";
	private static final Pattern variable = Pattern.compile(patternvariablename);
	
	
	private static final String patternparameter = "[int|double|char|float|long|short|bool]\\s[a-zA-z][a-zA-z0-9]{1,14}$";
	private static final Pattern parameter = Pattern.compile(patternparameter);
	
	private static final String patternfuncreturntype = ("[int|double|char|float|long|short|bool|void]");

	//void add (double a, double b)
	private static final Pattern funcheader = Pattern.compile("[int|double|char|float|long|short|bool|void]\\s[a-zA-z0-9]{1,14}\\s\\((.*?)\\)");
	private static final Pattern commonfuncheader = Pattern.compile("(\\w+)\\s(\\w+)\\s\\((.*?)\\)");
	
	private static final Pattern funcCall = Pattern.compile("[a-zA-z0-9]{1,14}\\s\\((.*?)\\);");
	
	private static final Pattern patternReturn = Pattern.compile("(^)return\\s[a-zA-z][a-zA-z0-9]{1,14}[;]$");
	
	// NOTE we won't have " int a = 1+2 " or "int a = a+b" a+# or #+b or a+b+c or etc...
	// WE WILL ONLY HAVE THE BASE CASE int a; or int a = #;
	private static final Pattern varNumberVer1 = Pattern.compile("[int|double|float|long|short]\\s[a-zA-z][a-zA-z0-9]{0,14}[;]$");
	private static final Pattern varnumberVer2 = Pattern.compile("[int|double|float|long|short]\\s[a-zA-z][a-zA-z0-9]{0,14}\\s"+ "=\\s[0-9]{1,14};$");
	private static final Pattern varCharVer1 = Pattern.compile("char\\s[a-zA-z][a-zA-z0-9]{0,14};$");
	private static final Pattern varCharVer2 = Pattern.compile("char\\s[a-zA-z][a-zA-z0-9]{0,14}\\s"+ "=\\s[']"+"[a-zA-z]"+"['][;]$");
	private static final Pattern varBoolVer1 = Pattern.compile("bool\\s[a-zA-z][a-zA-z0-9]{0,14};$");
	private static final Pattern varBoolVer2 = Pattern.compile("bool\\s[a-zA-z][a-zA-z0-9]{0,14}\\s"+ "=\\s(true|false)"+"[;]$");
	
	/*
 	int    * ip;    pointer to an integer 
	double * dp;    pointer to a double 
	float  * fp;    pointer to a float 
	char   * ch     pointer to a character 
	*/
	private static final Pattern patternpointer = Pattern.compile("[int|double|float|char]\\s[*]\\s[a-zA-z][a-zA-z0-9]{0,14}"+"[;]$");
	
	// array declaration: int n [ 10 ] ; 
	// array maximum size is 99 minimum is 0
	private static final Pattern patternArray= Pattern.compile("[int|double|char]\\s[a-zA-z][a-zA-z0-9]{0,14}\\s"+"\\[\\s" + "[1-9][0-9]?\\s"+ "\\][;]$");
	// array subscript string[i]
	private static final Pattern patternArrSub = Pattern.compile("[a-zA-z][a-zA-z0-9]{0,14}"+"\\s\\[" + "[0-9]{1,3}"+ "\\]$");
	
	private static final Pattern patternPrintf = Pattern.compile("printf\\((.*?)\\)[;]$");
	private static final Pattern patternScanf = Pattern.compile("scanf\\((.*?)\\)[;]$");
	private static final Pattern patternStdio = Pattern.compile("#\\sinclude\\s<stdio.h>");
	
	// common form of var/arr/ptr declaration
	private static final Pattern CommonVar = Pattern.compile("^?(\\w+)\\s^?(\\w+);$");
	private static final Pattern CommonVarWithInitialization = Pattern.compile("^?(\\w+)\\s^?(\\w+)\\s=\\s(.*?);$");
	private static final Pattern CommonPtr = Pattern.compile("^?(\\w+)\\s?[*]\\s?(\\w+);$");
	private static final Pattern CommonArr = Pattern.compile("^?(\\w+)\\s(\\w+)\\s?\\[(.*?)];$");
	//---------------------------------------

	public static boolean visitCommonVar (String target) {
		return (CommonVar.matcher(target).find() || CommonVarWithInitialization.matcher(target).find());
	}
	public static boolean visitCommonPtr (String target) {
		return CommonPtr.matcher(target).find();
	}
	public static boolean visitCommonArr (String target) {
		return CommonArr.matcher(target).find();
	}
	public static boolean visitcommonfuncheader (String target) {
		return commonfuncheader.matcher(target).find();
	}
	
	public static boolean visitMainret (String target) {
		return mainret.matcher(target).find();
	}
	public static boolean visitPrintf (String target) {
		return patternPrintf.matcher(target).find();
	}
	public static boolean visitScanf (String target) {
		return patternScanf.matcher(target).find();
	}
	public static boolean visitStdio (String target) {
		return patternStdio.matcher(target).find();
	}
	
	public static boolean visitPointer (String target) {
		return patternpointer.matcher(target).find();
	}
	
	public static boolean visitArray (String target) {
		return patternArray.matcher(target).find();
	}
	
	
	public static boolean visitArrSub (String target) {
		return patternArrSub.matcher(target).find();
	}

	//private static final Pattern variableDeclation = Pattern.compile("[var1|var2]"));
	public static boolean visitVariableDeclaration (String target) {
		return (varNumberVer1.matcher(target).find() || varnumberVer2.matcher(target).find()
				|| varCharVer1.matcher(target).find() || varCharVer2.matcher(target).find()
				|| varBoolVer1.matcher(target).find() || varBoolVer2.matcher(target).find());
	}
	
	public static boolean visitReturn (String target) {
		return patternReturn.matcher(target).find();
	}
	
	public static boolean visitVarAssignFunc (String target) {
		if(!(target.contains("="))){
			return false;
		}
		else{
			String[] arr = target.split("=");
			// check left side the variable matching
			if(!(visitVariable(arr[0].trim()))){
				return false;
			}
			// check right side the function call matching
			if(!(visitFunctionCall(arr[1].trim()))){
				return false;
			}
		}
		return true;
	}
	
	public static boolean visitFunctionCall (String target) {
		return funcCall.matcher(target).find();
	}
	
	public static boolean visitVariable (String target) {
		return variable.matcher(target).find();
	}
	
	public static boolean visitParam (String target) {
		return parameter.matcher(target).find();
	}
	
	public static boolean visitPrimitive (String target) {
		return primitive.matcher(target).find();
	}
	
	public static boolean visitReturnType (String target) {
		return Returntype.matcher(target).find();
	}
       
   	public static boolean visitmain (String target) {
		return main.matcher(target).find();
	}
   	
   	public static boolean visitfunction (String target) {
   		/*
   		 * 3 cases only
   		 * int func1 ( int a, int b )
   		 * int func1 ( int a )
   		 * int func1 ( )
   		 * any will work go in if block and check for the parameter
   		 */
   		if(funcheader.matcher(target).find() == false){
   			return false;
   		}
   		if(funcheader.matcher(target).find()){
   			// check param by first substring them out
   			target = target.replaceAll(",", "");
   			String paramlist = target.substring(target.indexOf("(")+1,target.indexOf(")"));
   			paramlist = paramlist.trim();
   			// case int func1 ( )
   			if(paramlist.equals("") || paramlist.isEmpty()){
   				return true;
   			}
   			// case at least 1 param
   			else{
   				// split the param using space
   				String[] paramArr = paramlist.split(" ");
   				if((paramArr.length % 2) == 1){
   					//param arr size must be even 2 4 6 8 10
   					return false;
   				}
   				// check all data type
   				for(int i = 0; i < paramArr.length; i = i+2){
   					if(!(visitPrimitive(paramArr[i]))){
   						// not primitive type
   						return false;
   					}
   				}
   				// check the variable naming limit
   				for(int j = 1; j < paramArr.length; j = j+2){
   					if(!(visitVariable(paramArr[j]))){
   						// variable name isn't passing
   						return false;
   					}
   				}
   			}
   		}
		return true;
	}
   	
	// remove all comma, semi colon
	public String RemoveAllCommaNSemicolon(String token){
		token = token.replace(",", "");
		token = token.replace(";", "");
		return token;
	}

}
