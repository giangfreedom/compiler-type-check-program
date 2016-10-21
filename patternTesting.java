package simpleTypeCheck;

public class patternTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatternMatching mycheck = new PatternMatching();
		String m = "int main()";
		String m1 = "double myfunc1 (int myvalue1)";
		String m2 = "int";
		String m3 = "short myvalue int";
		
		if(mycheck.visitmain(m)){
			System.out.println("int main() passed");
		}
		if(mycheck.visitfunction(m1)){
			System.out.println("double myfunc1 passed");
		}
		if(mycheck.visitPrimitive(m2)){
			System.out.println("int passed");
		}
		if(mycheck.visitParam(m3)){
			System.out.println("param passed");
		}
	}

}
