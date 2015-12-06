import java.util.Iterator;
import java.util.Stack;
import java.util.*;

public class BinaryTree implements Iterable{

	public Node root;
	public int size;
	public Node cursor;

	public BinaryTree(){
		root = null;
		this.cursor = this.root;
		this.size = 0;
	}

	public BinaryTree(Object obj){
		root = new Node(obj);
		this.cursor = root;
		size = 1;
	}

	public boolean contains(Object obj){
		for(Object a : this){
			if(a == obj) {
				return true;
			}
		}
		return false;
	}

	public boolean findSimilar(Node thisNode, Node similarNode){
		if(thisNode == null && similarNode == null){
			return true;
		}

		if((thisNode == null && similarNode != null) || (thisNode != null && similarNode == null)){
			return false;
		}

		return findSimilar(thisNode.leftSon, similarNode.leftSon) && (findSimilar(thisNode.rightSon, similarNode.rightSon));
	}

	public boolean similar(Object obj){
		return findSimilar(this.root, ((BinaryTree)obj).root);
	}


	public boolean equals(Object obj){
		if((!(obj instanceof BinaryTree)) || ((BinaryTree)obj).size() != this.size()){
			return false;
		}

		Iterator PREORDERITERATOR1 = this.iterator();
		Iterator PREORDERITERATOR2 = ((BinaryTree)obj).iterator();
		while(PREORDERITERATOR1.hasNext()){
			Object preIt1 = PREORDERITERATOR1.next();
			Object preIt2 = PREORDERITERATOR2.next();
			if((preIt1 == null && preIt1 != preIt2) || (!(preIt1.equals(preIt2)))){
				return false;
			}
		}
		Iterator INORDERITERATOR1 = this.inOrder();
		Iterator INORDERITERATOR2 = ((BinaryTree)obj).inOrder();
		while(INORDERITERATOR1.hasNext()){
			Object inIt1 = INORDERITERATOR1.next();
			Object inIt2 = INORDERITERATOR2.next();
			if((inIt1 == null && inIt1 != inIt2) || (!(inIt1.equals(inIt2)))) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty(){
		if(size == 0){
			return true;
		}
		return false;
	}

	public int size(){
		return this.size;
	}

	public int hashCode(){
		return super.hashCode();
	}

	public Iterator iterator(){
		return new PreOrderIterator(this);
	}

	public class PreOrderIterator implements Iterator{

		Stack<Node> preOrderStack = new Stack<Node>();
		Node next;

		private PreOrderIterator (BinaryTree a){
			next = a.root;
		}
		
		public boolean hasNext(){
			if(next != null){
				return true;
			}
			return false;
		}

		public Object next(){

			Node tempPoint = next; 

			if(next.leftSon != null && next.rightSon != null){
				preOrderStack.push(next);
				next = next.leftSon;
			} else if(next.leftSon == null && next.rightSon != null){
				next = next.rightSon;
			} else if(next.leftSon != null && next.rightSon == null){
				next = next.leftSon;
			} else {
				if(!(preOrderStack.empty())) {
					next = (Node) preOrderStack.pop().rightSon;
				} else {
					next = null;
				}
			}
			return tempPoint.data;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

	public class InOrderIterator implements Iterator {

		Stack<Node> inOrderStack = new Stack<Node>();
		Node presentNode;
		Node tempNode;
		BinaryTree b; 
		int count;

		private InOrderIterator (BinaryTree b){
			this.b = b;
		}

		public boolean hasNext(){
			if(count != b.size()){
				return true;
			}
			return false;
		}

		public Object next() {
			if(!(hasNext())) {
				throw new NoSuchElementException();
			}

			if(presentNode == null){
				presentNode = b.root;
			}

			while(true) {
				if ((!inOrderStack.isEmpty()) && inOrderStack.peek() == presentNode) {
					if(presentNode.rightSon != null){
						presentNode = presentNode.rightSon;
						continue;
					}
					presentNode = presentNode.dad; 
					continue;
				}
				if((!inOrderStack.isEmpty()) && inOrderStack.peek() == presentNode.leftSon) {
					inOrderStack.pop();
					inOrderStack.push(presentNode);
					count++;
					return presentNode.getData();
				}
				if((!inOrderStack.isEmpty()) && inOrderStack.peek() == presentNode.rightSon) {
					inOrderStack.pop();
					presentNode = presentNode.dad;
					continue;
				}
				if(presentNode.leftSon != null) {
					presentNode = presentNode.leftSon;
					continue;
				}
				count++;
				inOrderStack.push(presentNode);
				return presentNode.getData();
			}	
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

	public Iterator inOrder(){
		return new InOrderIterator(this);
	}

	public boolean putCursorAtRoot(){
		if(root != null){
			this.cursor = this.root;
			return true;
		}
		return false;
	}

	public boolean putCursorAtLeftSon(){
		if(cursor.leftSon != null){
			this.cursor = cursor.leftSon;
			return true;
		}
		return false;
	}

	public boolean putCursorAtRightSon(){
		if(cursor.rightSon != null){
			this.cursor = cursor.rightSon;
			return true;
		}
		return false;
	}

	public boolean putCursorAtFather(){
		if(cursor.dad != null){
			this.cursor = cursor.dad;
			return true;
		}
		return false;
	}

	public boolean attachLeftSonAtCursor(Object obj){
		Node newLeftSon = new Node(obj);
		if(cursor.leftSon == null){
			cursor.leftSon = newLeftSon;
			newLeftSon.dad = cursor;
			size++;
			return true;
		}
		return false;
	}

	public boolean attachRightSonAtCursor(Object obj){
		Node newRightSon = new Node(obj);
		if(cursor.rightSon == null){
			cursor.rightSon = newRightSon;
			newRightSon.dad = cursor;
			size++;
			return true;
		}
		return false;
	}

	public boolean pruneFromCursor(){
		throw new UnsupportedOperationException();
	}

	class Node {

		public Object data;
		public Node dad;
		public Node rightSon;
		public Node leftSon;


		public Node(Object data){
			this.data = data;
		}

		public Node(Object data, Node dad, Node leftSon, Node rightSon){
			this.data = data;
			this.dad = dad;
			this.leftSon = leftSon;
			this.rightSon = rightSon;
		}

		public Object getData(){
			return data;
		}
	}
}
