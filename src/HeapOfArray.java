import java.util.Comparator;
import java.util.function.Consumer;

public class HeapOfArray<T extends Comparable<T>> {
    private static final int START_CAPACITY = 10;
    private int capacity;
    private int size;
    private T[] array;
    private Comparator<T> comparator;

    public HeapOfArray(final T[] initialArray) {
        this.array = initialArray;
        this.capacity = this.size = initialArray.length;
        comparator = Comparable::compareTo;
        buildHeap();
    }

    public HeapOfArray() {
        this.array = (T[]) new Comparable[START_CAPACITY];
        this.capacity = array.length;
        this.size = 0;
        comparator = Comparable::compareTo;
        buildHeap();
    }


    public void setComparator(final Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int parent(int currentIndex) {
        return currentIndex > 0 ? (currentIndex - 1) / 2 : -1;
    }

    private int leftChild(int currentIndex) {
        return currentIndex > -1 ? currentIndex * 2 + 1 : -1;
    }

    private int rightChild(int currentIndex) {
        return currentIndex > -1 ? currentIndex * 2 + 2 : -1;
    }


    public T getRootValue() {
        return array[0];
    }

    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public T getValue(int index) {
        return array[index];
    }


    public void insert(T value) {
        if (size == capacity) {
            resizeArray();
        }
        array[size] = value;
        incresing(size++);
    }


    public void update(T value, int index) {
        if (value.compareTo(array[parent(index)]) > 0) {
            incresing(index);
        } else {
            heapify(index);
        }


    }

    private void resizeArray() {
        int newCapacity = capacity * 2;
        T[] newArray = (T[]) new Comparable[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newCapacity;
    }

    private void incresing(int index) {
        int parentIndex = parent(index);
        if (parentIndex != -1) {
            if (comparator.compare(array[parentIndex], array[index]) < 0) {
                swap(index, parentIndex);
                incresing(parentIndex);
            }
        }
    }


    public void delete(int index) {
        if (index >= 0 && index < size) {
            --size;
            swap(index, size);
            heapify(index);
        }
    }


    public void heapSort() {
        int originalSize = size;
        for (int i = 0; i < originalSize - 1; i++) {
            delete(0);
        }
        size = originalSize;
    }


    public void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    private boolean hasChildren(int currentIndex) {
        return currentIndex <= (size / 2) - 1;
    }

    private void heapify(int index) {
        if (!hasChildren(index)) {
            return;
        }
        int largest = index;
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);
        if (leftChildIndex < size &&
                comparator.compare(array[leftChildIndex], array[largest]) > 0) {
            largest = leftChildIndex;
        }


        if (rightChildIndex < size && comparator.compare(array[rightChildIndex], array[largest]) > 0) {
            largest = rightChildIndex;
        }

        if (largest != index) {
            swap(largest, index);
            heapify(largest);
        }
    }


    public void traverseRecursive(int nodeIndex, Consumer<T> action, TraverseType type) {
        if (nodeIndex > (size + 1) / 2) return;
        switch (type) {
            case PRE_ORDER:
                action.accept(array[nodeIndex]);
                traverseRecursive(leftChild(nodeIndex), action, type);
                traverseRecursive(rightChild(nodeIndex), action, type);
                break;
            case IN_ORDER:
                traverseRecursive(leftChild(nodeIndex), action, type);
                action.accept(array[nodeIndex]);
                traverseRecursive(rightChild(nodeIndex), action, type);
                break;
            case POST_ORDER:
                traverseRecursive(leftChild(nodeIndex), action, type);
                traverseRecursive(rightChild(nodeIndex), action, type);
                action.accept(array[nodeIndex]);
                break;
        }
    }

    enum TypeHeap {MAX, MIN}

    enum TraverseType {PRE_ORDER, IN_ORDER, POST_ORDER}
}
