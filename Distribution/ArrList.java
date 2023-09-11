
public class ArrList {
    private Array arr;
    private int start = 0;
    private int end = 0;
    private int eleCount = 0;
    private int maxSize = 0;

    // Default constructor, makes array of size 10
    ArrList() {
        arr = new Array(10);
        maxSize = 10;
    }

    // Constructor sizes array from parameter
    ArrList(int cap) {
        arr = new Array(cap);
        maxSize = cap;
    }

    // Adds to end of the array list
    void addLast(int num) {
        checkSize();
        arr.setVal(end, num);
        end = (end + 1) % maxSize;
        eleCount++;
    }

    // Adds to beginning of the array list
    void addFirst(int num) {
        checkSize();
        start = (start + maxSize - 1) % maxSize;
        arr.setVal(start, num);
        eleCount++;
    }

    // Checks whether the array needs to be resized according to spec
    void checkSize() {
        if (eleCount == maxSize) {
            maxSize *= 2;
            arr.resize(maxSize, start, eleCount);
            start = 0;
            end = eleCount;
        } else if (eleCount < (maxSize / 4) && (maxSize / 2) >= 10) {
            maxSize /= 2;
            arr.resize(maxSize, start, eleCount);
            start = 0;
            end = eleCount;
        }
    }

    // Returns string formatting of array list
    String print() {
        return arr.toString() + "start = " + start + "end = " + end;
    }

    // Returns the value of the index provided by parameter
    int get(int i) {
        int trueIndex = (i + start) % maxSize;
        return arr.getVal(trueIndex);
    }

    // Returns the index of the first occurence of the provided value
    int indexOf(int num) {
        for (int i = 0; i < maxSize; i++) {
            if (arr.getVal((i + start) % maxSize) == num) {
                return i;
            }
        }
        return -1;
    }

    // Returns true if the array list contains the provided value, false otherwise
    boolean contains(int num) {
        for (int i = 0; i < maxSize; i++) {
            if (arr.getVal((i + start) % maxSize) == num) {
                return true;
            }
        }
        return false;
    }

    // Returns true if the array list is empty, false otherwise
    boolean isEmpty() {
        return eleCount == 0;
    }

    // Returns the index of the last occurence of the provided value
    int lastIndexOf(int num) {
        for (int i = eleCount - 1; i >= 0; i--) {
            int trueIndex = (start + i) % maxSize;
            if (num == arr.getVal(trueIndex)) {
                return i;
            }
        }
        return -1;
    }

    // Removes and returns the first element in the list
    int removeFirst() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        int removed;
        removed = arr.getVal(start);
        arr.setVal(start, 0);
        start = (start + 1) % maxSize;
        eleCount--;
        checkSize();

        return removed;

    }

    // Removes and returns the last element in the list
    int removeLast() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        int removed;
        end = (end + maxSize - 1) % maxSize;
        removed = arr.getVal(end);
        arr.setVal(end, 0);
        eleCount--;
        checkSize();

        return removed;
    }

    // Remove and return the element at index of parameter i
    int removeByIndex(int i) throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        int trueIndex = (i + start) % maxSize;
        int existing = arr.getVal(trueIndex);
        arr.setVal(trueIndex, 0);

        if (trueIndex == start) {
            start = (start + 1) % maxSize;
        } else if (trueIndex == end) {
            end = (end - 1) % maxSize;
        } else {
            for (int j = trueIndex; j != end; j = (j + 1) % maxSize) {
                int next = (j + 1) % maxSize;
                arr.setVal(j, arr.getVal(next));
            }
            end = (end + 1) % maxSize;
        }
        eleCount--;
        checkSize();
        return existing;
    }

    // Remove the first occurence of the num parameter
    boolean removeByValue(int num) throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        int index = this.indexOf(num);
        if (index != -1) {
            this.removeByIndex(index);
            return true;
        }
        return false;

    }

    // Sets the value at the provided index to the provided number, returning the
    // existing index value
    int set(int index, int num) {
        int existing;
        existing = arr.getVal((index + start) % maxSize);
        arr.setVal((index + start) % maxSize, num);

        return existing;
    }

    // Returns the number of elements in the list
    int size() {
        return eleCount;
    }

    // used only for testing!!!
    public int getAccessCount() {
        return arr.getAccessCount();
    }

    // used only for testing!!!
    public void resetAccessCount() {
        arr.resetAccessCount();
    }
}