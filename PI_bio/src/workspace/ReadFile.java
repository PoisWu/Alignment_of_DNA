package workspace;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


/*
 *  For read the file
 */

public class ReadFile {
  

	public static void main(String[] args) {
		display(getGene("SubSeq_Res52.txt"));
		
	}
	
	public static String[] getGene(String file_name) {
		// This function return the pair of gene in the file with type String[].
		String[] gene= new String[2];
		int i=0;
		try {
	    	File myfile = new File(file_name);
	    	Scanner myReader = new Scanner(myfile);
	    	
	    	while (myReader.hasNextLine()) {
	    		String data = myReader.nextLine();
	    		gene[i]=data;
	    		i++;
	    	}
	    	myReader.close();
	    	
	    } catch (FileNotFoundException e) {
	    		System.out.println("An error occurred.");
	    		e.printStackTrace();
	    }
		return gene;
	}
	
	public static void display(String[] object) {
		for(int i=0;i<object.length;i++) {
			System.out.println(object[i]);
		}
	}
}
