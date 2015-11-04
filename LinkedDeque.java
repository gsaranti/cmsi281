public class LinkedDeque{

	int currentSize;
	public Node furthestLeft;
	public Node furthestRight;

	public LinkedDeque (){ // default constructor

	}	

	public void insertLeft ( Object obj ){
		furthestLeft = new Node(obj, null, furthestLeft);
		currentSize++;
		if(furthestRight == null){
			furthestRight = furthestLeft;
		}
		if(furthestRight != null){
			furthestRight = furthestRight;
		}
	}

	public void insertRight ( Object obj ){
		furthestRight = new Node(obj, furthestRight, null);
		currentSize++;
		if(furthestLeft == null){
			furthestLeft = furthestRight;
		}
		if(furthestLeft != null){
			furthestLeft = furthestLeft;
		}
	}

	public void deleteLeft (){
		if(furthestLeft == null){
			return;
		}
		furthestLeft = furthestLeft.rightNode;

		if(furthestLeft != null){
			furthestLeft.leftNode = null;
		}
		currentSize--;
	}

	public void deleteRight (){
		if(furthestRight == null){
			return;
		}
		furthestRight = furthestRight.leftNode;

		if(furthestRight != null){
			furthestRight.rightNode = null;
		}
		if(furthestLeft != null && furthestRight == null){
			furthestLeft = null;
		}
		currentSize--;
	}

	public Object left (){ // returns the left element without modifiying the deque
		return furthestLeft.data;
	}

	public Object right (){ // returns the right element without modifiying the deque
		return furthestRight.data;
	}

	public int size (){
		return currentSize;
	}

	public String toString (){ // returns [obj][obj]...[obj]
		if(furthestLeft == null){
			return "";
		}
		if(furthestLeft != null){
			return furthestLeft.toString("");
		}
		return null;
	}

	class Node {

		public Object data;
		public Node rightNode;
		public Node leftNode;

		public Node(Object data){
			this.data = data;
		}

		public Node(Object data, Node leftNode, Node rightNode){
			this.data = data;
			this.leftNode = leftNode;
			this.rightNode = rightNode;

			if(leftNode != null){
				leftNode.rightNode = this; 
			}
			if(rightNode != null){
				rightNode.leftNode = this; 
			}
		}

		public String toString(String str){
			str += "[" + data + "]";

			if(rightNode == null){
				return str;
			}
			if(rightNode != null){
				return rightNode.toString(str);
			}
			return null;
		}
	}

	public static void main ( String[] args ){ // runs a comprehensive set of unit tests

		test_insertLeft();
		test_insertRight();
		test_deleteLeft();
		test_deleteRight();
		test_left();
		test_right();
		test_size();
		test_toString();

	}

    public static void test_insertLeft(){
    	System.out.println("Testing insertLeft...");

    	LinkedDeque test1 = new LinkedDeque();
    	
    	test1.insertLeft("pizza");
    	if(test1.toString().equals("[pizza]")){
    		System.out.println("true");
    	} else {
    		System.out.println("failed");
    	}

    	test1.insertLeft("pizza");
    	if(test1.toString().equals("[pizza][pizza]")){
    		System.out.println("true");
    	} else {
    		System.out.println("failed");
    	}

    	test1.insertLeft(123L);
    	if(test1.toString().equals("[123][pizza][pizza]")){
    		System.out.println("true");
    	} else {
    		System.out.println("failed");
    	}
    }

    public static void test_insertRight(){
    	System.out.println("Testing insertRight...");

    	LinkedDeque test = new LinkedDeque();

    	test.insertRight("pizza");
    	if(test.toString().equals("[pizza]")){
    		System.out.println("true");
    	} else {
    		System.out.println("failed");
    	}

    	test.insertRight("pizza");
    	if(test.toString().equals("[pizza][pizza]")){
    		System.out.println("true");
    	} else {
    		System.out.println("failed");
    	}

    	test.insertRight(123L);
    	if(test.toString().equals("[pizza][pizza][123]")){
    		System.out.println("true");
    	} else {
    		System.out.println("failed");
    	}
    }

    public static void test_deleteLeft(){
    	System.out.println("Testing deleteLeft...");

    	LinkedDeque test = new LinkedDeque();

    	test.insertRight("car");
    	test.insertRight("car");
    	test.insertRight("car");
    	test.insertRight("car");
    	test.insertRight("car");
    	test.insertRight("car");
    	test.deleteLeft();
    	if(test.toString().equals("[car][car][car][car][car]")){
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}
    }

    public static void test_deleteRight(){
    	System.out.println("Testing deleteRight...");

    	LinkedDeque test = new LinkedDeque();

    	test.insertRight("hi");

    	test.deleteRight();
    	if(test.toString().equals("")){
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}

    }

    public static void test_left(){
    	System.out.println("Testing left...");

    	LinkedDeque test = new LinkedDeque();

    	test.insertRight("hi");
    	test.insertRight("car");
    	test.insertRight("go");
    	test.insertRight("big");
    	test.insertRight("lit");
    	test.insertRight("happy");

    	if(test.left().equals("hi")){
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}

    	LinkedDeque test2 = new LinkedDeque();

    	test2.insertRight("lit");

    	if(test2.left().equals("lit")){
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}
    }

    public static void test_right(){
    	System.out.println("Testing right...");

    	LinkedDeque test = new LinkedDeque();

    	test.insertRight("hi");
    	test.insertRight("car");
    	test.insertRight("go");
    	test.insertRight("big");
    	test.insertRight("lit");
    	test.insertRight("happy");

    	if(test.right().equals("happy")){
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}

    	LinkedDeque test2 = new LinkedDeque();

    	test.insertRight("big");

    	if(test.right().equals("big")){
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}
    }

    public static void test_size(){
    	System.out.println("Testing size...");

    	LinkedDeque test = new LinkedDeque();

    	test.insertRight("hi");
    	test.insertRight("car");
    	test.insertRight("go");
    	test.insertRight("big");
    	test.insertRight("lit");
    	test.insertRight("happy");
    	test.size();

    	if(test.size() == 6){
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}
    }

    public static void test_toString(){
    	System.out.println("Testing toString...");

    	LinkedDeque test = new LinkedDeque();

    	test.insertRight("hi");
    	test.insertRight("car");
    	test.insertRight("go");

    	if(test.toString().equals("[hi][car][go]")) {
    		System.out.println("true");
    	} else {
    		System.out.println("false");
    	}
    }
}
