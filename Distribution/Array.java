import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Array {
    private int[] array;//the underlying array
    private int accessCount;//counts the number of times the array is accessed

    //constructor that reads in the items (as Strings) from a file and creates the array
    public Array (String fn) {
	this.accessCount = 0;
	int n = 0;
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    if(line != null) {
		n = Integer.parseInt(line);
	    }
	    line = br.readLine();
	    int i = 0;
	    array = new int[n];
	    while(line != null && i < n) {
		int num = Integer.parseInt(line);
		array[i] = num;
		line = br.readLine();
		i++;
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    //constructor that creates an array of 0's of size <cap>
    public Array (int cap) {
	this.accessCount = 0;
	this.array = new int[cap];
    }

    //gets a String representation of the array
    public String toString() {
	return Arrays.toString(array);
    }

    //gets the length of the array
    public int length() {
	return array.length;
    }

    //gets the value at index i
    public int getVal(int i) {
	int num = array[i];
	accessCount++;
	return num;
    }

    //sets the value at index i to val
    public void setVal(int i, int val) {
	array[i] = val;
	accessCount++;
    }

    //swaps the values at i and j
    public void swap(int i, int j) {
	int temp = array[i];
	array[i] = array [j];
	array[j] = temp;
	accessCount+=4;
    }

    //returns the accessCount
    public int getAccessCount() {
	return accessCount;
    }

    //resets accessCount to 0--used only for testing!!!
    public void resetAccessCount() {
	this.accessCount = 0;
    }

    //sorts the array--used for testing only!!!
    public void sort() {
	Arrays.sort(this.array);
    }

    //compares the elements in two arrays--used for testing only!!!
    public boolean equals(Array a) {
	if(this.length() != a.length())
	    return false;
	this.sort();
	a.sort();
	for(int i = 0; i < this.length(); i++) {
	    if(this.getVal(i) != a.getVal(i))
		return false;
	}
	return true;
    }

    //resizes the array
    public void resize(int newCap, int start, int n) {
	int[] temp = new int[newCap];
	int i = start;
	int j = 0;
	while (j < n) {
	    temp[j] = this.array[i];
	    j++;
	    i = (i+1)%array.length;
	    accessCount+=2;
	}
	this.array = temp;
    }	    
}
