import java.util.Comparator;
import java.util.function.Consumer;

// Define a heap interface
public interface IHeap<T extends Comparable<T>> {
    void setComparator(Comparator<T> comparator);

    void insert(T value);

    void delete(int index);

    void heapSort();

    void buildHeap();

    void traverseRecursive(int nodeIndex, Consumer<T> action, HeapWithArray.TraverseType type);

    enum TraverseType {PRE_ORDER, IN_ORDER, POST_ORDER}
}

