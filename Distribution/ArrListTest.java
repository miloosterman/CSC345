import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ArrListTest {
    private static Random gen = new Random(System.currentTimeMillis());
    private static ArrayList<Integer> exp = new ArrayList<Integer>();
    private static ArrList act = new ArrList();
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();
    
    public static void main(String[] args) {
	setUpMap();
	double score = 0;
	score += testAddLast();
	score += testAddFirst();
	score += testGet();
	score += testIndexOf();
	score += testContains();
	score += testLastIndexOf();
	score += testRemoveFirst();
	score += testRemoveLast();
	score += testRemoveByIndex();
	score += testRemoveByValue();
	score += testSet();
	score += finalTest();
	System.out.println("\nExpected Score: " + score);
    }

    private static void setUpMap() {
	map.put("addLast", 500);
	map.put("addFirst", 620);
	map.put("get", 100);
	map.put("indexOf", 1000);
	map.put("contains", 1500);
	map.put("lastIndexOf", 1000);
	map.put("removeFirst", 327);
	map.put("removeLast", 277);
	map.put("removeByIndex", 25000);
	map.put("removeByValue", 25000);
	map.put("set", 200);
	map.put("final", 5000);
    }

    //testing the addLast method
    private static double testAddLast() {
	double score = 0;
	System.out.println("\nTesting addLast method...");
	int count = 0;
	while(count < 100) {
	    int num = gen.nextInt(100);
	    exp.add(num);
	    act.addLast(num);
	    count++;
	}
	if(checkLists()) {
	    score += 2;
	}
	if(checkCounts("addLast")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the addFirst method
    private static double testAddFirst() {
	double score = 0;
	System.out.println("\nTesting addFirst method...");
	int count = 0;
	while(count < 100) {
	    int num = gen.nextInt(100);
	    exp.add(0, num);
	    act.addFirst(num);
	    count++;
	}
	if(checkLists()) {
	    score += 2;
	}
	if(checkCounts("addFirst")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the get method
    private static double testGet() {
	double score = 0;
	System.out.println("\nTesting get method...");
	int count = 0;
	boolean passed = true;
	while(count < 100) {
	    int num = gen.nextInt(exp.size());
	    int e = exp.get(num);
	    int a = act.get(num);
	    if(e != a) {
		System.out.println("Values do not match at index " + num + ".");
		passed = false;
	    }
	    count++;
	}
	if(passed) {
	    score += 2;
	}
	if(checkCounts("get")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the indexOf method
    private static double testIndexOf() {
	double score = 0;
	System.out.println("\nTesting indexOf method...");
	int count = 1;
	while(count <= 4) {
	    int num = gen.nextInt(50);
	    exp.add(0, 500);
	    act.addFirst(500);
	    for(int i = 0; i < num; i++) {
		exp.add(0, 1);
		act.addFirst(1);
	    }
	    int e = exp.indexOf(500);
	    int a = act.indexOf(500);
	    
	    if(e != a) {
		System.out.println("The expected index is " + e + ".");
		System.out.println("The index you produced is " + a + ".");
	    } else
		score += 0.5;
	    count++;
	}
	if(checkCounts("indexOf")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the lastIndexOf method
    private static double testLastIndexOf() {
	double score = 0;
	System.out.println("\nTesting lastIndexOf method...");
	int count = 1;
	while(count <= 4) {
	    int num = gen.nextInt(50);
	    exp.add(500);
	    act.addLast(500);
	    for(int i = 0; i < num; i++) {
		exp.add(1);
		act.addLast(1);
	    }
	    int e = exp.lastIndexOf(500);
	    int a = act.lastIndexOf(500);
	    
	    if(e != a) {
		System.out.println("The expected index is " + e + ".");
		System.out.println("The index you produced is " + a + ".");
	    } else
		score += 0.5;
	    count++;
	}
	if(checkCounts("lastIndexOf")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the contains method
    private static double testContains() {
	double score = 0;
	System.out.println("\nTesting contains method...");
	int count = 1;
	while(count <= 4) {
	    int num = gen.nextInt(200);
	    boolean e = exp.contains(num);
	    boolean a = act.contains(num);
	    
	    if(e != a) {
		System.out.println("Results do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
	    } else
		score += 0.5;
	    count++;
	}
	if(checkCounts("contains")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the removeFirst method
    private static double testRemoveFirst() {
	double score = 0;
	System.out.println("\nTesting removeFirst method...");
	int count = 1;
	int e = -1;
	int a = -1;
	while(count <= 50) {
	    try {
		e = exp.remove(0);
		a = act.removeFirst();
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }	
	    
	    if(e != a) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkLists()) {
	    score += 2;
	}
	if(checkCounts("removeFirst")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the removeLast method
    private static double testRemoveLast() {
	double score = 0;
	System.out.println("\nTesting removeLast method...");
	int count = 1;
	int e = -1;
	int a = -1;
	while(count <= 50) {
	    try {
		e = exp.remove(exp.size()-1);
		a = act.removeLast();
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }
	    
	    if(e != a) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkLists()) {
	    score += 2;
	}
	if(checkCounts("removeLast")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the remove-by-index method
    private static double testRemoveByIndex() {
	double score = 0;
	System.out.println("\nTesting remove-by-index method...");
	int count = 1;
	int e = -1;
	int a = -1;
	while(count <= 50) {
	    int i = gen.nextInt(exp.size());
	    try {
		e = exp.remove(i);
		a = act.removeByIndex(i);
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }
	    
	    if(e != a) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkLists()) {
	    score += 2;
	}
	if(checkCounts("removeByValue")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the remove-by-value method
    private static double testRemoveByValue() {
	double score = 0;
	System.out.println("\nTesting remove-by-value method...");
	int count = 1;
	boolean e = false;
	boolean a = false;
	while(count <= 50) {
	    Integer num = gen.nextInt(150);
	    try {
		e = exp.remove(num);
		a = act.removeByValue(num);
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }
	    
	    if(e != a) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkLists()) {
	    score += 2;
	}
	if(checkCounts("removeByValue")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the set method
    private static double testSet() {
	double score = 0;
	System.out.println("\nTesting set method...");
	int count = 1;
	int e = -1;
	int a = -1;
	while(count <= 10) {
	    int i = gen.nextInt(exp.size());
	    int num = gen.nextInt(500);
	    e = exp.set(i, num);
	    a = act.set(i, num);
	    count++;
	}
	if(checkLists()) {
	    score += 2;
	}
	if(checkCounts("set")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }


    //runs various methods many times and tests the results
    private static double finalTest() {
	act = new ArrList(10);
	exp = new ArrayList<Integer>();
	double score = 0;
	System.out.println("\nTesting for robustness...");
	int count = 0;
	//add a bunch of elements to the lists
	System.out.println("Adding elements...");
	while(count < 500) {
	    int num = gen.nextInt(500);//System.out.println("adding " + num);
	    exp.add(num);
	    act.addLast(num);
	    count++;
	}
	if(checkLists())
	    score += 1;
	while(count > 0) {
	    int num = gen.nextInt(500);//System.out.println("adding " + num);
	    exp.add(0, num);
	    act.addFirst(num);
	    count--;
	}
	if(checkLists())
	    score += 1;
	while(!exp.isEmpty()) {
	    try {
		exp.remove(0);
		act.removeFirst();
		if(!exp.isEmpty()) {
		    exp.remove(exp.size()-1);
		    act.removeLast();
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
		return score;
	    }
	}
	if(act.isEmpty())
	    score += 1;
	if(checkCounts("final")) {
	    score += 1;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }	    

    private static boolean checkLists() {
	//compare list sizes
	if(exp.size() != act.size()) {
	    System.out.println("Sizes of lists do not match.");
	    return false;
	}
	//compare the elements
	for(int i = 0; i < exp.size(); i++) {
	    if(!exp.get(i).equals(act.get(i))) {
		System.out.println("Items do not match at index " + i + ".");
		System.out.println("Expected: " + exp.get(i));
		System.out.println("Actual: " + act.get(i));
		//return false;
	    }
	}
	return true;
    }

    private static boolean checkCounts(String method) {
	int e = ((Integer)map.get(method)).intValue();
	int a = act.getAccessCount();
	if(a > 1.5*e) {
	    System.out.println("The access count for " + method + " is too high.");
	    System.out.println("Expected: " + e);
	    System.out.println("Actual: " + a);
	    act.resetAccessCount();
	    return false;
	}
	act.resetAccessCount();
	return true;
    }
}

    
	
