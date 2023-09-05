import java.util.Comparator;
import java.util.function.Consumer;

public class Heap<T extends Comparable> implements IHeap {
    private Comparator<T> comparator;
    private Node<T> root;
    private void swap(Node<T> i, Node<T> j) {
        T temp = i.val;
        i.val = j.val;
        j.val = temp;
    }
//    private void bubbleUp(int index) {
//        int parentIndex = parent(index);
//        if (parentIndex != -1) {
//            if (comparator.compare(array[parentIndex], array[index]) < 0) {
//                swap(index, parentIndex);
//                bubbleUp(parentIndex);
//            }
//        }
//    }

    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void insert(Comparable value) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public void heapSort() {

    }

    @Override
    public void buildHeap() {

    }

    @Override
    public void traverseRecursive(int nodeIndex, Consumer action, HeapWithArray.TraverseType type) {

    }

    class Node<T> {
        private T val;
        private Node<T> left;
        private Node<T> right;

        public Node(final T val) {
            this.val = val;
        }

        public Node(final T val, final Node<T> left, final Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
