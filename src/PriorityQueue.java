public class PriorityQueue<T extends Comparable<T>> {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Insert elements into the PriorityQueue
        pq.insert(5, 5);
        pq.insert(10, 10);
        pq.insert(2, 2);
        pq.insert(8, 8);

        // Get the maximum element
        int max = pq.getmax();
        System.out.println("Maximum element: " + max); // Output: Maximum element: 10

        // Extract the maximum element
        pq.extractMax();

        // Get the new maximum element
        max = pq.getmax();
        System.out.println("New Maximum element: " + max); // Output: New Maximum element: 8

        // Increase the priority of an element at index 1 by 3
        pq.incresingKey(1, 7);

        // Get the new maximum element
        max = pq.getmax();
        System.out.println("Updated Maximum priority element: " + max); // Output: Updated Maximum element: 10

    }

    HeapOfArray<Node<T>> heap;

    public PriorityQueue() {
        heap = new HeapOfArray<>();
    }

    public void insert(T value, int proirity) {
        heap.insert(new Node<>(proirity, value));
    }

    public T getmax() {

        return heap.getRootValue().getValue();
    }

    public void extractMax() {
        heap.delete(0);
    }

    public void incresingKey(int index, int addingProirity) {
        Node<T> node = heap.getValue(index);
        node.setValProirity(node.getValProirity() + addingProirity);
        heap.update(node , index);
    }

    class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

        private int valProirity;
        private T value;

        public Node(int valProirity, T val) {
            this.valProirity = valProirity;
            this.value = val;
        }

        public int compareTo(int o) {
            return Integer.compare(valProirity, o);
        }

        @Override
        public int compareTo(Node<T> o) {
            return Integer.compare(valProirity, o.valProirity);
        }

        public int getValProirity() {
            return this.valProirity;
        }

        public T getValue() {
            return this.value;
        }

        public void setValProirity(final int valProirity) {
            this.valProirity = valProirity;
        }

        public void setValue(final T value) {
            this.value = value;
        }
    }
}


