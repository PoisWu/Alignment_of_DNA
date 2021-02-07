package workspace;

import java.util.Collections;
import java.util.Vector;

public class Refine_alignement {
	public static void main(String[] args) {
		boolean bo=true;
		
		if (bo){
			String s1="CACTAAGCATCAGC";
			String s2="ATAGGGCAATCT";
			float result = refine_alig(s1,s2); 
			System.out.println(result);
			
		}else {
			String[] data = ReadFile.getGene("SubSeq_Res3275.txt");
			float result = refine_alig(data[0],data[1]);
			System.out.println(result);
			
		}
	}
	
	public static float refine_alig(String s1,String s2) {
		int l1 = s1.length(); int l2 = s2.length();
		float[] score_j_negative = new float[l1];
		float[] score_now = new float[l2];
		float[] score_next = new float[l2];
		float choose;
		float up;
		float left;
		int[][] tab_dir = new int[l1][l2]; 
		//0 go diagonal
		//1 go left
		//2 go right 		
	
		//Construct the -1th column
		for(int i=0;i<l1;i++) {
			if(i!=0) {
				score_j_negative[i]=score_j_negative[i-1]+b(s1.charAt(i),'-');
			}else {
				score_j_negative[i]=b(s1.charAt(i),'-');
			}
		}
		
		//Construct the -1th line
		for(int j=0;j<l2;j++) {
			switch(j){
			case 0:
				score_now[j]=Blosum50.getScore('-', s2.charAt(j));
				break;
			default:
				score_now[j]=score_now[j-1]+Blosum50.getScore('-', s2.charAt(j));
				break;
			}
			
		}
		// Calculate the score and construct the table_direction
		for(int i=0;i<l1;i++) {
			for(int j=0;j<l2;j++) {
				//Calculate the score from the 3 direction up,left and choose.
				
				switch(j) {
				case 0:
					if(i!=0) {
						up = score_now[j]+b('-',s2.charAt(j));
						left=score_j_negative[i]+b('-',s2.charAt(j));
						choose=score_j_negative[i-1]+b(s1.charAt(i),s2.charAt(j));
						
					}else {
						up = score_now[j]+b('-',s2.charAt(j));
						left=score_j_negative[i]+b('-',s2.charAt(j));
						choose=b(s1.charAt(i),s2.charAt(j));
					}
					break;
				default:
					choose = score_now[j-1]+b(s1.charAt(i), s2.charAt(j));
					up = score_now[j]+b('-',s2.charAt(j));
					left = score_next[j-1]+b(s1.charAt(i),'-');
					break;
				}
				// Decide the best direction.
				
				if (choose>up  &&choose>left) {
					score_next[j]=choose;
					tab_dir[i][j]=0;
				}else {
					if(up>left) {
						score_next[j]=up;
						tab_dir[i][j]=1;
					}else {
						score_next[j]=left;
						tab_dir[i][j]=2;
					}
				}
			}
			//score_now=score_next
			for(int j=0;j<l2;j++) {
				score_now[j]=score_next[j];
			}
		}
		
		//Construct the two string.
		int i = l1-1;
		int j = l2-1;
		Vector<Character> res1 = new Vector<Character>();
		Vector<Character> res2 = new Vector<Character>();		
		while(i>-1 && j>-1) {
			switch (tab_dir[i][j]) {
			case 0://go diagonal
				res1.add(s1.charAt(i));
				res2.add(s2.charAt(j));
				i--; j--;
				break;
			case 1://go up
				res1.add(s1.charAt(i));
				res2.add('-');
				i--;
				break;
			case 2: //go left
				res1.add('-');
				res2.add(s2.charAt(j));
				j--;
				break;
			}
		}
		
		
		while(i>-1) {
			res1.add(s1.charAt(i));
			res2.add('-');
			i--;
		}
		while(j>-1) {
			res1.add('-');
			res2.add(s2.charAt(j));
			j--;
		}
		Collections.reverse(res1); Collections.reverse(res2);
		System.out.println(res1); System.out.println(res2);
		return score_now[l2-1];
	}
	
	/*
	 *  Small function to simply the code.
	 */
	
	private static float b(char c1,char c2) {
		return Blosum50.getScore(c1, c2);
	}
}
