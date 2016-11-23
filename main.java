package simpleTypeCheck;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner in;
		Scanner in1;
		typecheck mycheck = new typecheck();
		try {
			in = new Scanner(new FileReader("C:\\Users\\SuperAdmin\\Desktop\\fortranproject\\simpleTypeCheck\\CsimpleCalculator.txt"));
			in1 = new Scanner(new FileReader("C:\\Users\\SuperAdmin\\Desktop\\fortranproject\\simpleTypeCheck\\CsimpleCalculator.txt"));
			// check declaration
			while(in.hasNextLine()){
				String input = in.nextLine();
				input = input.trim();
				if(input.isEmpty()){
					System.out.println("empty line");
					continue;
				}
				System.out.println(input);
				mycheck.Declaration(input);
			}
			// check the whole file if detect declaration output skip ...
			while(in1.hasNextLine()){
				String input = in.nextLine();
				input = input.trim();
				if(input.isEmpty()){
					System.out.println("empty line");
					continue;
				}
				System.out.println(input);
				mycheck.check(input);
			}
			if(!(mycheck.braceCount())){
				System.out.println("error braces count");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
