import java.util.Comparator;
import java.util.function.Consumer;

// Define a heap interface
public interface IHeap<T extends Comparable<T>> {
    void setComparator(Comparator<T> comparator);

    T getValue(int index);

    void insert(T value);

    void update(T value, int index);

    void delete(int index);

    void heapSort();

    T getRootValue();

    void buildHeap();

    void traverseRecursive(int nodeIndex, Consumer<T> action, HeapOfArray.TraverseType type);

    enum TraverseType {PRE_ORDER, IN_ORDER, POST_ORDER}
}

