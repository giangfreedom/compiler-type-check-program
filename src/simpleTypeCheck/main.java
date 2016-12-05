package simpleTypeCheck;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner in;
		Scanner in1;
		typecheck mycheck = new typecheck();
		ArrayList<String> programString = new ArrayList<String>();
		ArrayList<String> SemeticprogramString = new ArrayList<String>();
		try {
			in = new Scanner(new FileReader("C:\\Users\\SuperAdmin\\Desktop\\fortranproject\\simpleTypeCheck\\CsimpleCalculator.txt"));
			// pick up all string in program and fill the arraylist withit.
			while(in.hasNextLine()){
				String input = in.nextLine();
				input = input.trim();
				// add into arraylist
				if(input.isEmpty() || (input.equals(null)) || (input.equals("EOF"))){
					System.out.println("ignore line");
				}
				else{
					programString.add(input);
				}
			}
			
			// now run through the arraylist check for declaration.
			// if declaration return true. we will remove the item from arraylist.
			// else keep the item for semantic check in next loop.
			for(int i = 0; i < programString.size(); i++){
				System.out.println(programString.get(i));
				if(!(mycheck.Declaration(programString.get(i)))){
					// we got a string that is not a declaration
					// add it to the SemeticprogramString arraylist for semantic check.
					SemeticprogramString.add(programString.get(i));
				}
			}
			System.out.println("END OF DECLARATION CHECK");
			// semantoc check loop
			for(int j = 0; j < SemeticprogramString.size(); j++){
				System.out.println(SemeticprogramString.get(j));
				mycheck.check(SemeticprogramString.get(j));
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
