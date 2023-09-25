import java.util.Comparator;
import java.util.Stack;
import java.util.function.Consumer;

public abstract class ATree<T extends Comparable<T>> {
    protected Node<T> root;
    protected Comparator<T> comparator;

    protected ATree() {
        this.comparator = Comparable::compareTo;
    }











    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return null == root;
    }

    public void clear() {
        this.root = null;
    }

    public boolean contains(final T val) {
        return null != search(root, val);
    }

    protected Node<T> search(final Node<T> node, final T val) {
        if (null == node) return null;
        if (0 == comparator.compare(node.val, val)) {
            return node;
        } else if (0 > comparator.compare(node.val, val)) {
            return this.search(node.right, val);
        }
        return this.search(node.left, val);
    }

    public int getHeight() {
        return this.getHeight(this.root);
    }

    protected int getHeight(final Node<T> node) {
        if (null == node) return -1;
        return Integer.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
    }

    public int getSize() {
        return this.getSize(this.root);
    }

    protected int getSize(final Node<T> node) {
        if (null == node) return 0;
        return this.getSize(node.left) + this.getSize(node.right) + 1;
    }

    public T getMin() {
        final Node<T> min = this.getMin(this.root);
        return null == min ? null : min.val;
    }

    protected Node<T> getMin(final Node<T> node) {
        if (null == node.left) return node;
        return this.getMin(node.left);
    }

    public T getMax() {
        final Node<T> max = this.getMax(this.root);
        return null == max ? null : max.val;
    }

    protected Node<T> getMax(final Node<T> node) {
        if (null == node.right) return node;
        return this.getMax(node.right);
    }

    public T successor(final T val) {
        final Node<T> successor = this.successor(this.root, val);

        return null == successor ? null : successor.val;
    }

    protected Node<T> successor(final Node<T> node, final T val) {
        if (null == node) return null;

        return this.successorWithThisNode(node, val);
    }

    protected Node<T> successorWithThisNode(Node<T> curr, final T val) {
        Node<T> finded = null;
        while (null != curr) {
            if (0 == comparator.compare(curr.val, val)) {
                if (null != curr.right) {
                    return this.getMin(curr.right);
                }
                return finded;
            }
            if (0 > comparator.compare(curr.val, val)) {
                curr = curr.right;
            } else {
                finded = curr;
                curr = curr.left;
            }
        }
        return finded;
    }

    public T predecessor(final T val) {
        final Node<T> predecessor = this.predecessor(this.root, val);

        return null == predecessor ? null : predecessor.val;
    }

    protected Node<T> predecessor(final Node<T> node, final T val) {
        if (null == node) return null;

        return this.predecessorWithThisNode(node, val);
    }


    protected Node<T> predecessorWithThisNode(Node<T> curr, final T val) {
        Node<T> finded = null;
        while (null != curr) {
            if (0 == comparator.compare(curr.val, val)) {
                if (null != curr.left) {
                    return this.getMax(curr.left);
                }
                return finded;
            }
            if (0 > comparator.compare(curr.val, val)) {
                finded = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return finded;
    }

    public void preorderTraversal(Node<T> root, Consumer<T> fptr) {
        Stack<Node<T>> s = new Stack<>();
        while (root != null || !s.isEmpty()) {
            if (root != null) {
                fptr.accept(root.val);
                s.push(root);
                root = root.left;
            } else {
                root = s.pop().right;
            }
        }
    }


    public void inorderTraversal(Node<T> root, Consumer<T> fptr) {
        Stack<Node<T>> s = new Stack<>();
        while (root != null || !s.isEmpty()) {
            if (root != null) {
                s.push(root);
                root = root.left;
            } else {
                root = s.pop().right;
                fptr.accept(root.val);
            }
        }
    }

    public abstract void printTree();

    public abstract void insert(T val);

    public abstract void delete(T val);


    protected static class Node<T> {
        Node<T> left;
        Node<T> right;
        T val;

        protected Node(T val) {
            this.val = val;
        }

        protected Node(Node<T> left, Node<T> right, T val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }
}
