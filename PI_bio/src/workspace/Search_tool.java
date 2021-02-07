package workspace;

import java.util.Vector;

public class Search_tool {
	public static void main(String[] args) {
		System.out.println(b_string("ADCR"));
		int k=4;
		float th=0.9f;
		for(int n=0 ; n<(int) Math.pow(nb_alpha, k);n++) {
			numtoString(n,k);
		}
	}
	
	
	
	static String alphas="ARNDCQEGHILKMFPSTWYV";
	static int nb_alpha=alphas.length();
	
	/*
	 * Program of task 7
	 */
	public static Vector<Integer> search(String g,String t, float th,int k) {
		Keyword_tree sg = sg(g,th,k);
		Vector<Integer> tab_ids= new Vector<Integer>();
		for(int i=0;i<t.length()-k+1;i++) {
			if(sg.find(t.substring(i,i+k))) {
				tab_ids.add(i);
			}
		}
		return tab_ids;
	}
	
	/*
	 * Take g and k as variable and Generate wg
	 */
	
	public static Vector<String> wg(String g,int k){
		Vector<String> wg = new Vector<String>();
		for(int i=0;i<g.length()-k+1;i++) {
			wg.add(g.substring(i,i+k));
		}
		return wg;
	}
	
	/*
	 * Take g a sequence, th and k , and Generate sg
	 */
	
	public static Keyword_tree sg(String g,float th,int k) {
		Keyword_tree sg = new Keyword_tree();
		Vector<String> wg = wg(g,k);
		int size_wg=wg.size();
		Vector<Float> score_wg= new Vector<Float>();
		for(int i=0;i<size_wg;i++) {
			score_wg.add(b_string(wg.get(i)));
		}
		
		for(int n=0;n<(int) Math.pow(nb_alpha, k);n++) {
			for(int i=0;i<size_wg;i++) {
				String sp = numtoString(n,k);
				if(b_string(sp,wg.get(i))>th*score_wg.get(i)) {
					sg.add_string(sp);
				}
			}
		}
		return sg;
	}
	
	/*
	 *  return score value for simplify the code
	 */
	
	public static float b_string(String s1,String s2) {
		// il faut que |s1|=|s2|
		float score=0;
		for(int i=0;i<s1.length();i++){
			score+=Blosum50.getScore(s1.charAt(i), s2.charAt(i));
		}
		return score;
	}
	
	
	
	public static float b_string(String s) {
		float score=0;
		for(int i=0;i<s.length();i++){
			score+=Blosum50.getScore(s.charAt(i), s.charAt(i));
		}
		return score;
	}
	
	/*
	 * Make a injection from number to a word with longer k
	 */
	
	public static String numtoString(int n,int k) {
		String s="";
		for(int i=0;i<k;i++) {
			s+=alphas.charAt(n%nb_alpha);
			n=n/nb_alpha;
		}
		return s;
	}
	
}



