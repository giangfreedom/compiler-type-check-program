package simpleTypeCheck;

public class patternTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatternMatching mycheck = new PatternMatching();
		String m = "int main()";
		String m1 = "double myfunc1 (int myvalue1)";
		String m2 = "double";
		String m3 = "int dg1dfgd";
		String m4 = "m1yval23kjhfghj";
		String m5 = "func ();";
		String m6 = "return abc;";
		String m7 = "bool aaa1 = true;";
		String m8 = "int * aaa;";
		String m9 = "int aa [ 8 ];";
		String m10 = "arr [8]";
		/*
		if(mycheck.visitmain(m)){
			System.out.println("int main() passed");
		}
		if(mycheck.visitfunction(m1)){
			System.out.println("double myfunc1 passed");
		}*/
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
	}

}
