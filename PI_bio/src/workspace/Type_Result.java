package workspace;

import java.util.Vector;
import static java.lang.Math.max;


/*
 *  This class is used to define the type of the result of the function l_common_sup 
 *  	it compose of
 *  	int length : length of the l_C_s 
 *  	String s1 : 1st code genetic
 *  	String s2 : 2nd code genetic
 *  	Vector<INT> sub_s1 : Index of 1st sub_seq
 *  	Vector<INT> sub_s2 : Index of 2nd sub_seq
 */


public class Type_Result {
	

	int length;
	String s1;
	String s2;
	Vector<Integer> sub_s1;
	Vector<Integer> sub_s2;
	
	
/*
 * 		Constructor	
 */
	Type_Result(){
		this.sub_s1= new Vector<Integer>();
		this.sub_s2= new Vector<Integer>();
	}
	
	Type_Result(int length, Vector<Integer> sub_s1,Vector<Integer> sub_s2) {
		this.length=length;
		this.sub_s1=sub_s1;
		this.sub_s2=sub_s2;
	}
	
	Type_Result(String s1, String s2){
		this.s1 = s1;
		this.s2 = s2;
		this.sub_s1= new Vector<Integer>();
		this.sub_s2= new Vector<Integer>();
	}
	
/*
 *   	Method ADD
 */

	
	public void add1(int index) {
		this.sub_s1.add(index);
	}
	
	public void add2(int index) {
		this.sub_s2.add(index);
	}
	
/*
 * 		Method toString  and display
 */
	
	@Override
	public String toString() {
		String s="";
			s=s+"s1 is                     : " + this.s1+"\n";
			s=s+"s2 is                     : " + this.s2+"\n";
			s=s+"length of alignment       : " + this.length+"\n";
			s=s+"indexs M_score gene1      : " + this.sub_s1+"\n";
			s=s+"indexs M_score gene2      : " + this.sub_s2+"\n";
		return s;
	}
	
/*
 *  	Method DISPLAY do the task 3 
 */

	
	public void display() {
		System.out.println("The longest common subsequence is "+this.length);
		String disp0=""; // Final Alignment of the index
		String disp1=""; // Final Alignment of string 1
		String disp2=""; // Final Alignment of string 2
		int id1;
		int id2;
		int id1_next;
		int id2_next;
		for(int i=0; i<this.length;i++) {
			id1 = this.sub_s1.get(i);
			id2 = this.sub_s2.get(i);
			
			if(i<this.length-1) {
				id1_next = this.sub_s1.get(i+1);
				id2_next = this.sub_s2.get(i+1);
			}else {
				id1_next = this.s1.length();
				id2_next = this.s2.length();
			}
			
			//Construct the alignment in front of the id1[0].
			if(i==0){
				disp0+=duplicate(" ",max(id1,id2));
				
				disp1 = disp1+this.s1.substring(0,id1);
				disp2 = disp2+this.s2.substring(0,id2);
				if(id1 > id2) {
					disp2+=duplicate("-",id1-id2);
		
				}else {
					disp1=duplicate("-",id2-id1);
				}
			}
			
			//Construct the alignment between the id1,id_next and id2_id_next
			disp0+="*";
			disp1+=this.s1.substring(id1,id1_next);
			disp2+=this.s2.substring(id2,id2_next);
			disp0+=duplicate(" ",id1_next-id1-1);			
			int diff = (id1_next-id1)-(id2_next-id2);
			if(diff>0) {
				disp2+=duplicate("-",diff);
			}else {
				disp1+=duplicate("-",-diff);
				disp0+=duplicate(" ",-diff);
			}
		}
		System.out.println(disp0);
		System.out.println(disp1);
		System.out.println(disp2);
	}
	
	private String duplicate(String s,int n) {
		String copy_s="";
		for(int i=0;i<n;i++) {
			copy_s+=s;
		}
		return copy_s;
	}
}
