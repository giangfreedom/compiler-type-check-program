package simpleTypeCheck;

public class patternTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatternMatching mycheck = new PatternMatching();
		String m = "int main()";
		String m1 = "double myfunc1 (int myvalue1)";
		String m2 = "ddouble";
		String m3 = "int dg1dfgd";
		String m4 = "m1yval23kjhfghj";
		String m5 = "div (firstNumber, secondNumber);";
		String m6 = "return abc;";
		String m7 = "bool aaa1 = true;";
		String m8 = "int * aaa;";
		String m9 = "int aa [ 8 ];";
		String m10 = "arr [8]";
		String m11 = "printf(Enter two operands: );";
		String m12 = "scanf(%c, &operator);";
		String m13 = "# include <stdio.h>";
		String m14 = "void add (double num1, double num2)";
		String m15 = "else if ( operator == '/' )";
		String m16 = "bool check = true;";
		
		if(mycheck.visitmain(m)){
			System.out.println("int main() passed");
		}
		if(mycheck.visitfunction(m14)){
			System.out.println("func header passed");
		}
		if(mycheck.visitPrimitive(m2)){
			System.out.println("double passed");
		}
		if(mycheck.visitParam(m3)){
			System.out.println("param passed");
		}
		if(mycheck.visitVariable(m4)){
			System.out.println("variable name passed");
		}
		
		if(mycheck.visitFunctionCall(m5)){
			System.out.println("function call passed");
		}
		if(mycheck.visitReturn(m6)){
			System.out.println("return passed");
		}
		if(mycheck.visitVariableDeclaration(m7)){
			System.out.println("var dec passed");
		}
		if(mycheck.visitPointer(m8)){
			System.out.println("ptr passed");
		}
		if(mycheck.visitArray(m9)){
			System.out.println("arr dec passed");
		}
		if(mycheck.visitArrSub(m10)){
			System.out.println("arr sub passed");
		}
		if(mycheck.visitPrintf(m11)){
			System.out.println("printf passed");
		}
		if(mycheck.visitScanf(m12)){
			System.out.println("scanf passed");
		}
		if(mycheck.visitStdio(m13)){
			System.out.println("stdio passed");
		}
		if(mycheck.visitcommonfuncheader(m15)){
			System.out.println("common function header passed");
		}
		if(mycheck.visitCommonVar(m16)){
			System.out.println("common var passed");
		}
	}

}
