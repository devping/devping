package org.jbug.devping.tag;


public class TST<Value> {
	private int N;
	private Node root;
	
	private class Node{
		private char c;
		private Node left, mid, right;
		private Value val;
	}

	public int size(){return N;}

	public boolean contains(String key) { return get(key)!= null;}
	
	public Value get(String key){
		if(key==null) throw new NullPointerException();
		if(key.length()==0)throw new IllegalArgumentException("key must have length >1");
		Node x = get(root,key,0);
		if(x==null)return null;
		return x.val;
	}

	private Node get(Node x, String key, int d) {
		if(key==null) throw new NullPointerException();
		if(key.length()==0)throw new IllegalArgumentException();
		if(x==null)return null;
		char c= key.charAt(d);
		if(c< x.c)	return get(x.left,key,d);
		else if(c > x.c) return get(x.right,key,d);
		else if(d<key.length()-1) return get(x.mid,key, d+1);
		else return x;
	}

	
	public void put(String s, Value val){
		if(!contains(s)) N++;
		root = put(root, s, val, 0);
	}
	
	private Node put(Node x, String s, Value val, int d){
		char c=s.charAt(d);
		if(x == null){
			x = new Node();
			x.c=c;
		}
		if(c <x.c)   x.left = put(x.left, s, val, d);
		else if(c>x.c) x.right = put(x.right,s,val,d);
		else if(d <s.length() -1) x.mid = put(x.mid,s,val,d+1);
		else  x.val=val;
		return x;
	}
	
	public String longestPrefixOf(String s){
		if(s==null || s.length() ==0 ) return null;
		int length = 0;
		Node x =root;
		int i =0;
		while (x!=null && i< s.length()){
			char c=s.charAt(i);
			if(c< x.c) x =x.left;
			else if(c> x.c) x= x.right;
			else {
				i++;
				if(x.val !=null) length = i;
				x= x.mid;
			}
			
		}
		return s.substring(0, length);
						
			}
	
	public Iterable<String> keys(){
		Queue<String> queue = new Queue<>();
		collect(root,"",queue);
		return queue;
	}
	
	public Iterable<String> prefixMatch(String prefix){
		Queue<String> queue = new Queue<>();
		Node x = get(root,prefix, 0);
		if(x == null) return queue;
		if(x.val != null) queue.enqueue(prefix);
		collect(x.mid,prefix,queue);
		return queue;
	}
	private void collect(Node x, String prefix, Queue<String> queue){
		if(x==null) return;
		collect(x.left,prefix,queue);
		if(x.val !=null)  queue.enqueue(prefix + x.c);
		collect(x.mid, prefix + x.c, queue);
		collect(x.right, prefix, queue);
	}
	public Iterable<String> wildcardMatch(String pat){
		Queue<String> queue = new Queue<>();
		collect(root,"",0,pat,queue);
		return queue;
	}
	
	private void collect(Node x, String prefix, int i, String pat, Queue<String> q){
		if(x==null) return ;
		char c= pat.charAt(i);
		if(c=='.'|| c< x.c) collect(x.left, prefix, i, pat, q);
		if(c== '.' || c==x.c){
			if(i== pat.length()-1 && x.val !=null) q.enqueue(prefix +  x.c);
			if(i<pat.length()-1) collect(x.mid, prefix+x.c, i+1,pat,q);
		}
		if( c =='.' || c>x.c) collect(x.right,prefix,i,pat,q);
	}
	
}

