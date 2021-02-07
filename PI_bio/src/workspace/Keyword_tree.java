package workspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Keyword_tree {
	
	public static void main(String[] args) {
		Keyword_tree tree =new Keyword_tree();
		tree.add_string("ADCR");
		tree.add_string("DCRG");
		tree.add_string("CRGH");
		tree.add_string("RGHC");
		String t="BDADCRGNRADACRGHC";
		int k =4;
		for(int i=0;i<t.length()-k+1;i++) {
			if(tree.find(t.substring(i,i+k))) {
				System.out.println(i);
			}
		}
	}
	
	private Character c;
	private ArrayList<Keyword_tree> tab_children = new ArrayList<Keyword_tree>();
	
	/*
	 *  Constructor
	 */
	
	public Keyword_tree() {
		this.c='#'; //root is '#';
		this.tab_children= new ArrayList<Keyword_tree>();
	}
	
	public Keyword_tree(Character c) {
		this.c=c;
		this.tab_children= new ArrayList<Keyword_tree>();
	}
	
	public Keyword_tree(Character c, ArrayList<Keyword_tree> tab_children) {
		this.c=c;
		this.tab_children=tab_children;
	}
	
	
	public List<Keyword_tree> getChildren(){
		return this.tab_children;
	}
	
	/*
	 * Creat child
	 */
	
	private static Keyword_tree creat_child(String s) {
		Keyword_tree child= new Keyword_tree();
		if(s.length()==0) {
			return child;
		}else {
			child.c=s.charAt(0);
			child.tab_children.add(creat_child(s.substring(1)));
			return child;
		}
	}
	
	/*
	 *  find a string in the keyword tree
	 */
	
	public boolean find(String s) {
		if(s.length()==0) {
			return true;
		}else {
			
			for(Keyword_tree child:this.tab_children) {
				if(child.c==s.charAt(0)) {
					return child.find(s.substring(1));
				}
			}
			return false;
		}
	}

	/*
	 *  Add a string to the keyword tree
	 */
	
	public void add_string(String s) {
		if(s.length()!=0) {
			boolean is_found=false;
			for(Keyword_tree child:this.tab_children) {
				
				if(child.c==s.charAt(0)) {
					is_found=true;
					child.add_string(s.substring(1));
					break;
				}
			}
			if(!is_found) {
				Keyword_tree child = creat_child(s);
				this.tab_children.add(child);
			}
		}
	}		
}