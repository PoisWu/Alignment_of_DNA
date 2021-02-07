package workspace;
import static java.lang.Math.max;

import java.util.Vector;

import java.util.Collections;

public class Common_sequence {
	

		/*
		 *   Function main  TEST
		 */
		
		public static void main(String[] args) {
			
			
			boolean bo=false;
			if (bo){
				String s1="AA";
				String s2="ARN";
				Type_Result result = com_sub_new(s1,s2); 
				System.out.println(result);
				result.display();
			}else {
				String[] data = ReadFile.getGene("SubSeq_Res52.txt");
				Type_Result result = com_sub_new(data[0],data[1]);
				System.out.println(result);
				result.display();
				
			}
		}
		
		/*
		 *   Program of task 3. Find the longest common subsequence of s1 and s2
		 */
		
		public static Type_Result com_sub_new(String s1,String s2) {
			int l1 = s1.length(); int l2 = s2.length();
			int[] tab_now= new int[l2]; //table of the 
			int[] tab_next=new int[l2];
			
			Type_Result result = new Type_Result(s1,s2);
			
			Vector<int[]> tab_id = new Vector<int[]>(); 
			// This table is used for store the index i,j which s1[i] and s2[j] have to be allige to have the longest common subsequence
			// The content include the length of common subsequence , i and j. 
			
			// Initialize the tab
			for(int j=0;j<l2;j++) {
				switch (j) {
					case 0 : 
						if(s1.charAt(0)==s2.charAt(j)) {
							tab_now[j]=1;
							tab_id.add(new int[] {tab_now[j],0,j});
						}else {
							tab_now[j]=0;
						}
						break;
						
					default :
						if(tab_now[j-1]>0) {
							tab_now[j]=1;
						}else {
							if(s1.charAt(0)==s2.charAt(j)) {
								tab_now[j]=1;
								tab_id.add(new int[] {tab_now[j],0,j});
							}
						}
				}
				
			}		
			
			// Calculate the length and construct the tab_id
			for(int i =1;i<l1;i++) {
				for(int j = 0;j<l2;j++) {
					switch (j) {
					case 0:
						if (tab_now[j]==1) {
							tab_next[j]=1;
						}else {
							if(s1.charAt(i)==s2.charAt(j)) {
								tab_next[j]=1;
								tab_id.add(new int[] {tab_next[j],i,j});
							}else {
								tab_next[i]=0;
							}
						}
						break;
						
					default:
						if(s1.charAt(i)==s2.charAt(j)) {
							tab_next[j]=tab_now[j-1]+1;
							if(tab_next[j-1]<tab_next[j] && tab_now[j]<tab_next[j]) {
								tab_id.add(new int[] {tab_next[j],i,j});

							}
							
						}else {
							tab_next[j]=max(tab_now[j],tab_next[j-1]);
						}
						break;
					}
				}
				// do tab_next = tab_now
				for(int j=0;j<l2;j++) {
					tab_now[j]=tab_next[j];
				}
			}
			
			// Construct the alignment. 
			int len = tab_now[l2-1];
			int i = l1;
			int j = l2;
			for(int n= tab_id.size()-1;n>-1;n--) {
				int[] ids_next = tab_id.get(n);
				int len_next = ids_next[0];
				int i_next=ids_next[1];
				int j_next=ids_next[2];
				if(j_next<j && i_next<i && len_next==len) {
					result.add1(i_next);
					result.add2(j_next);
					i = i_next;
					j = j_next;
					len--;
				}
			}
			
			System.out.println(result.sub_s1.size());
		    Collections.reverse(result.sub_s1);
		    Collections.reverse(result.sub_s2);
			result.length=result.sub_s1.size();
	
			
			return result;
		}
}


