package simpleTypeCheck;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner in = new Scanner("C\\user\\file.txt");
		typecheck mycheck = new typecheck();
		// as long as there is a nextline we continue to read
		while(in.hasNextLine()){
			String input = in.nextLine();
			input = input.trim();
			if(input.isEmpty()){
				continue;
			}
			mycheck.check(input);
		}
	}

}
