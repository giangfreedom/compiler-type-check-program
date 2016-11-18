package simpleTypeCheck;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner in;
		typecheck mycheck = new typecheck();
		try {
			in = new Scanner(new FileReader("C:\\Users\\SuperAdmin\\Desktop\\fortranproject\\simpleTypeCheck\\CsimpleCalculator.txt"));
			// as long as there is a nextline we continue to read
			while(in.hasNextLine()){
				String input = in.nextLine();
				input = input.trim();
				if(input.isEmpty()){
					System.out.println("empty line");
					continue;
				}
				System.out.println(input);
				mycheck.check(input);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
