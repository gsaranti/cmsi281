public class NumberList implements java.util.Collection {

    private static int attempts = 0;
    private static int successes = 0;
    public int currentSize = 0;
    public Long[] objArray = new Long[16];


    public NumberList(){ 
        this.objArray = new Long[1];
    }

    public NumberList( Long[] l ){  // Asymptotic Performance Guarantee: Big Theta = n
        this.objArray = new Long[l.length];
        for(int i = 0; i < l.length; i++){
            this.objArray[i] = l[i];
            currentSize++;
        }
    }
    
    public boolean add ( Object obj ) { //Asymptotic Performance Guarantee: Big Theta = i
        if(!(obj instanceof Long)){
            return false;
        }
        if(currentSize == objArray.length) {
            Long[] tempArray = new Long[objArray.length * 2 + 1];
            System.arraycopy(objArray, 0, tempArray, 0, objArray.length);
            objArray = tempArray;
        }
        objArray[currentSize++] = (Long) obj;
        return true;
    }
    
    public boolean addAll ( java.util.Collection c  ) { // Asymptotic Performance Guarantee: Big Theta = n
        if(c instanceof NumberList){
            for(Object x : c.toArray()){
                add(x);
            }
            return true;
        }
        return false;
    }   
       
    public void clear () { // Asymptotic Performance Guarantee: Big Theta = i 
        objArray = new Long[0];
    }

    public boolean contains ( Object obj ) { // Asymptotic Performance Guarantee: Big Theta = n
        if(!(obj instanceof Long)){
            return false;
        }
        for(int i = 0; i < this.objArray.length; i++){
            if(objArray[i] == (Long) obj){
                return true;
            }
        }
        return false;
    }

    public boolean containsAll ( java.util.Collection c ) { // Asymptotic Performance Guarantee: Big Theta = n^2
        if(c instanceof NumberList){
            for(Object x : c.toArray()){
                if(!(this.contains(x))){
                    return false;
                }
            }
        }
        return true;
    }
 
    public boolean equals ( Object obj ) { // Asymptotic Performance Guarantee: Big Theta = n
        if(obj instanceof Long){
            return false;
        }

        NumberList objNumList = (NumberList) obj;
        if(objNumList.currentSize != this.currentSize){
            return false;
        }

        for(int i = 0; i < currentSize; i++){
            if(objArray[i] != objNumList.objArray[i]){
                return false;
            }
        }
        return true;
    }

    public int hashCode () { // Asymptotic Performance Guarantee: Big Theta = i 
        return super.hashCode();
    }

    public boolean isEmpty () { // Asymptotic Performance Guarantee: Big Theta = i 
        if(objArray.length == 0){
            return true;
        }
        return false;
    }

    public java.util.Iterator iterator () { // Asymptotic Performance Guarantee: Big Theta = i 
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }

    public boolean remove ( Object obj ) { // Asymptotic Performance Guarantee: Big Theta = n
        if(obj instanceof Long){
            for(int i = 0; i < objArray.length; i++){
                if(obj == objArray[i]){
                    System.arraycopy(objArray, i + 1, objArray, i, objArray.length - 1 - i);
                    objArray[--currentSize] = null;
                }
            }
            return true;
        }
        return false;
    }

    public boolean removeAll ( java.util.Collection c ) { // Asymptotic Performance Guarantee: Big Theta = n^3 
        if(c instanceof NumberList){
            for(Object x : c.toArray()) {
                if(this.contains(x)){
                    this.remove(x);
                }               
            }
        }
        return true;
    }

	public boolean retainAll ( java.util.Collection c ) { // Asymptotic Performance Guarantee: Big Theta = n^3
        if(c instanceof NumberList){
            for(int i = 0; i < currentSize; i++){
                if(!(c.contains(objArray[i]))){
                    remove(objArray[i]);
                    --i;
                }    
            }
            return true;
        }
        return false;
    }

    public int sizeIncludingDuplicates () { // Asymptotic Performance Guarantee: Big Theta = i 
        return currentSize;
    }

    public Long[] toArray () { // Asymptotic Performance Guarantee: Big Theta = n^2
        Long[] newListArray = new Long[objArray.length];
        NumberList newNumberList = new NumberList(newListArray);
        for(int i = 0; i < objArray.length; i++){
            if(!(newNumberList.contains(objArray[i]))){
                newListArray[i] = objArray[i];
            }
        }
        return newListArray;
    }

    public NumberList[] toArray ( Object[] obj ) { // Asymptotic Performance Guarantee: Big Theta = i 
        throw new UnsupportedOperationException();
    }

    public int size () { // Asymptotic Performance Guarantee: Big Theta = i 
        return this.toArray().length;   
    }

    public int count ( Object obj ) { // Asymptotic Performance Guarantee: Big Theta = n
        int counter = 0;
        for(int i = 0; i < objArray.length; i++){
            if(obj == objArray[i]){
                counter++;
            }
        }
        return counter;
    }
    
    public String toString () { // Asymptotic Performance Guarantee: Big Theta = n
        String stringNumList = "";
        for(int i = 0; i < currentSize; i++){
            stringNumList = stringNumList + this.objArray[i] + " ";
        }
        return stringNumList;
    }
    
    public static NumberList fromArray ( long[] l ) { // Asymptotic Performance Guarantee: Big Theta = n
        Long[] classLong = new Long[l.length];
        for(int i = 0; i < classLong.length; i++){
            classLong[i] = l[i];
        }
        NumberList filledNumList = new NumberList(classLong);
        return filledNumList;
    }

    public static void main ( String[] args ) { 
        attempts = 0;
        successes = 0;

        test_Constructors();
        test_add();
        test_addAll();
        test_contains();
        test_remove();
        test_equals();
        test_containsAll();
        test_removeAll();
        test_count();
        test_retainAll();


        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println((value ? "success" : "failure"));
    }

    public static void displayFailure(){
        displaySuccessIfTrue(false);
    }

    public static void test_Constructors(){
        System.out.println("Testing Constructors...");

        Long[] testConstructor1 = new Long[]{1L, 2L};
        NumberList testConstructorMethod1 = new NumberList(testConstructor1);

        try{
            displaySuccessIfTrue(testConstructorMethod1.objArray[0] == 1L && testConstructorMethod1.objArray[1] == 2L);
        } catch(Exception e) {
            displayFailure();
        }

        Long[] testConstructor2 = new Long[]{5L, 6L, 7L, 8L};
        NumberList testConstructorMethod2 = new NumberList(testConstructor2);

        try{
            displaySuccessIfTrue(testConstructorMethod2.objArray[0] == 5L && testConstructorMethod2.objArray[2] == 7L);
        } catch(Exception e) {
            displayFailure();
        }
    }

    private static void test_add(){
        System.out.println("testing add...");

        Long[] testAddMethod1 = new Long[]{4L, 5L, 7L, 8L};
        NumberList testAdd1 = new NumberList(testAddMethod1);
        testAdd1.add(9L);

        try{
            displaySuccessIfTrue(testAdd1.objArray[4] == 9L);
        } catch (Exception e){
            displayFailure();
        }

        Long[] testAddMethod2 = new Long[]{4L, 5L, 7L, 8L};
        NumberList testAdd2 = new NumberList(testAddMethod2);
        testAdd2.add(9L);

        try{
            displaySuccessIfTrue(testAdd2.objArray[4] == 9L);
        } catch (Exception e){
            displayFailure();
        }
    }

    private static void test_addAll(){
        System.out.println("testing addAll...");

        Long[] testaddAllMethod1 = new Long[]{6L, 7L, 9L};
        NumberList testaddAll1 = new NumberList(testaddAllMethod1);
        testaddAll1.addAll(testaddAll1);

        try{
            displaySuccessIfTrue(testaddAll1.objArray[3] == 6L && testaddAll1.objArray[4] == 7L);
        } catch (Exception e){
            displayFailure();
        }
    }

    private static void test_contains(){
        System.out.println("testing contains...");

        Long[] testContainsMethod1 = new Long[]{4L, 5L, 5L};
        NumberList testContains1 = new NumberList(testContainsMethod1);

        try{
            displaySuccessIfTrue(testContains1.contains(5L));
        } catch (Exception e){
            displaySuccessIfTrue(false);
        }

        Long[] testContainsMethod2 = new Long[]{4L, 5L, 6L, 7L};
        NumberList testContains2 = new NumberList(testContainsMethod2);

        try{
            displaySuccessIfTrue(!(testContains2.contains(14L)));
        } catch (Exception e){
            displaySuccessIfTrue(false);
        }
    }

    private static void test_equals(){
        System.out.println("Testing equals...");

        Long[] testEqualsMethod1 = new Long[]{4L, 5L};
        NumberList testEquals1 = new NumberList(testEqualsMethod1);
        Long[] testEqualsMethod2 = new Long[]{4L, 5L};
        NumberList testEquals2 = new NumberList(testEqualsMethod2);

        try{
            displaySuccessIfTrue(testEquals1.equals(testEquals2));
        } catch (Exception e){
            displayFailure();
        }

        Long[] testEqualsMethod3 = new Long[]{4L, 5L};
        NumberList testEquals3 = new NumberList(testEqualsMethod3);
        Long[] testEqualsMethod4 = new Long[]{4L, 5L, 6L};
        NumberList testEquals4 = new NumberList(testEqualsMethod4);

        try{
            displaySuccessIfTrue(!(testEquals3.equals(testEquals4)));
        } catch (Exception e){
            displayFailure();
        }
    }

    private static void test_remove(){
        System.out.println("testing remove...");

        Long[] testRemoveMethod1 = new Long[]{4L, 5L};
        NumberList testRemove1 = new NumberList(testRemoveMethod1);
        Long[] testRemoveMethod2 = new Long[]{5L};
        NumberList testRemove2 = new NumberList(testRemoveMethod2);
        testRemove1.remove(4L);

        try{
            displaySuccessIfTrue(testRemove1.equals(testRemove2));
        } catch(Exception e){
            displayFailure();
        }
    }

    private static void test_containsAll(){
        System.out.println("testing containsAll...");

        Long[] testcontainsAllMethod1 = new Long[]{1L, 2L, 3L, 4L, 5L};
        NumberList testcontainsAll1 = new NumberList(testcontainsAllMethod1);
        Long[] testcontainsAllMethod2 = new Long[]{4L, 2L};
        NumberList testcontainsAll2 = new NumberList(testcontainsAllMethod2);
        testcontainsAll1.containsAll(testcontainsAll2);

        try{
            displaySuccessIfTrue(testcontainsAll1.containsAll(testcontainsAll2));
        } catch (Exception e){
            displayFailure();
        }

        Long[] testcontainsAllMethod3 = new Long[]{1L, 2L, 3L, 4L, 5L};
        NumberList testcontainsAll3 = new NumberList(testcontainsAllMethod3);
        Long[] testcontainsAllMethod4 = new Long[]{2L, 7L};
        NumberList testcontainsAll4 = new NumberList(testcontainsAllMethod4);
        testcontainsAll3.containsAll(testcontainsAll4);

        try{
            displaySuccessIfTrue(!(testcontainsAll3.containsAll(testcontainsAll4)));
        } catch (Exception e){
            displayFailure();
        }
    }

    private static void test_removeAll(){
        System.out.println("testing removeAll...");

        Long[] testremoveAllMethod1 = new Long[]{1L, 2L, 3L, 4L, 5L};
        NumberList testremoveAll1 = new NumberList(testremoveAllMethod1);
        Long[] testremoveAllMethod2 = new Long[]{1L, 2L, 7L};
        NumberList testremoveAll2 = new NumberList(testremoveAllMethod2);
        Long[] testremoveAllMethod3 = new Long[]{3L, 4L, 5L};
        NumberList testremoveAll3 = new NumberList(testremoveAllMethod3);
        testremoveAll1.removeAll(testremoveAll2);

        try{
            displaySuccessIfTrue(testremoveAll1.equals(testremoveAll3));
        } catch (Exception e){
            displayFailure();
        }
    }

    private static void test_count(){
        System.out.println("testing count...");

        Long[] testcountMethod1 = new Long[]{1L, 1L, 3L, 4L};
        NumberList testcount1 = new NumberList(testcountMethod1);
        int testingcount1 = testcount1.count(1L);

        try{
            displaySuccessIfTrue(testingcount1 == 2);
        } catch (Exception e){
            displayFailure();
        }
    }

    private static void test_retainAll(){
        System.out.println("testing retainAll...");
        
        Long[] testretainAllMethod1 = new Long[]{1L, 2L, 3L, 3L, 4L, 5L};
        NumberList testretainAll1 = new NumberList(testretainAllMethod1);
        Long[] testretainAllMethod2 = new Long[]{1L, 3L, 5L};
        NumberList testretainAll2 = new NumberList(testretainAllMethod2);
        Long[] testretainAllMethod3 = new Long[]{1L, 3L, 3L, 5L};
        NumberList testretainAll3 = new NumberList(testretainAllMethod3);
        testretainAll1.retainAll(testretainAll2);

        try{
            displaySuccessIfTrue(testretainAll1.equals(testretainAll3));
        } catch (Exception e){
            displayFailure();
        }
    }
}
