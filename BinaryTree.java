import java.util.Iterator;
import java.util.Stack;
import java.util.*;

public class BinaryTree implements Iterable{

	private static int attempts = 0;
    private static int successes = 0;

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
		if(root == null){
			return false;
		}
		return true;
	}

	public boolean similar(Object obj){
		if((!(obj instanceof BinaryTree)) || ((BinaryTree)obj).size() != this.size()){
			return false;
		}

		Iterator justNodeIterator1 = this.justNodeIterator();
		Iterator justNodeIterator2 = ((BinaryTree)obj).justNodeIterator();
		while(justNodeIterator1.hasNext()){
			if((justNodeIterator1.next() == null && justNodeIterator1.next() != justNodeIterator2.next()) || (!(justNodeIterator1.next().equals(justNodeIterator2)))){
				return false;
			}
		}
		return true;
	}

	public boolean equals(Object obj){
		if((!(obj instanceof BinaryTree)) || ((BinaryTree)obj).size() != this.size()){
			return false;
		}

		Iterator preOrderIterator1 = this.iterator();
		Iterator preOrderIterator2 = ((BinaryTree)obj).iterator();
		while(preOrderIterator1.hasNext()){
			if((preOrderIterator1.next() == null && preOrderIterator1.next() != preOrderIterator2.next()) || (!(preOrderIterator1.next().equals(preOrderIterator2)))){
				return false;
			}
		}
		Iterator inOrderIterator1 = this.inOrder();
		Iterator inOrderIterator2 = ((BinaryTree)obj).inOrder();
		while(inOrderIterator1.hasNext()){
			if((inOrderIterator1.next() == null && inOrderIterator1.next() != inOrderIterator2.next()) || (!(inOrderIterator1.next().equals(inOrderIterator2)))) {
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

	public Iterator justNodeIterator(){
		return new JustNodeIterator(this);
	}

	public class JustNodeIterator implements Iterator{
		
		Stack<Node> justNodeStack = new Stack<Node>();
		Node next;
		
		private JustNodeIterator (BinaryTree c){
			next = c.root;
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
				justNodeStack.push(next);
				next = next.leftSon;
			} else if(next.leftSon == null && next.rightSon != null){
				next = next.rightSon;
			} else if(next.leftSon != null && next.rightSon == null){
				next = next.leftSon;
			} else {
				if(!(justNodeStack.empty())) {
					next = (Node) justNodeStack.pop().rightSon;
				} else {
					next = null;
				}
			}
			return tempPoint;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
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
