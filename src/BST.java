import javax.management.AttributeNotFoundException;

public class BST<T extends Comparable<T>> extends ATree<T> {
    public static void main(String[] args) {
        // Create an instance of BST
        BST<Integer> bst = new BST<>();

        // Insert elements
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        bst.printTree();
        // Check if the tree contains specific elements
        int elementToCheck1 = 30;
        int elementToCheck2 = 90;

        System.out.println("Does the tree contain " + elementToCheck1 + "? " + bst.contains(elementToCheck1)); // Should return true
        System.out.println("Does the tree contain " + elementToCheck2 + "? " + bst.contains(elementToCheck2)); // Should return false

        // Find the minimum and maximum values in the tree
        System.out.println("Minimum value in the tree: " + bst.getMin()); // Should return 20
        System.out.println("Maximum value in the tree: " + bst.getMax()); // Should return 80

        // Get the height and size of the tree
        System.out.println("Height of the tree: " + bst.getHeight()); // Should return 2
        System.out.println("Size of the tree: " + bst.getSize()); // Should return 7

        // Find the successor and predecessor of specific elements
        int elementToFindSuccessor = 40;
        int elementToFindPredecessor = 60;

        System.out.println("Successor of " + elementToFindSuccessor + ": " + bst.successor(elementToFindSuccessor)); // Should return 50
        System.out.println("Predecessor of " + elementToFindPredecessor + ": " + bst.predecessor(elementToFindPredecessor)); // Should return 50

        // Delete an element from the tree
        int elementToDelete = 30;
        bst.delete(elementToDelete);
        System.out.println("Does the tree contain " + elementToDelete + " after deletion? " + bst.contains(elementToDelete)); // Should return false
    }


    @Override
    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node<T> node, int indent) {
        if (node == null)
            return;

        // Print right subtree
        printTree(node.right, indent + 4);

        // Print the current node
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(node.val);

        // Print left subtree
        printTree(node.left, indent + 4);
    }

    @Override
    public void insert(final T val) {
        if (null == root) {
            this.root = new Node<>(val);
        } else {
            this.insert(this.root, val);
        }
    }

    private Node<T> insert(Node<T> curr, final T val) {
        if (null == curr) return null;
        while (true) {
            if (0 > comparator.compare(curr.val, val)) {
                if (null == curr.right) {
                    curr.right = new Node<>(val);
                    return curr.right;
                }
                curr = curr.right;
            } else {
                if (null == curr.left) {
                    curr.left = new Node<>(val);
                    return curr.left;
                }
                curr = curr.left;
            }
        }
    }

    @Override
    public void delete(final T val) {
        if (null == root) return;
        try {
            this.delete(this.root, val);
        } catch (final AttributeNotFoundException e) {
            System.out.println(e);
        }
    }

    private Node<T> delete(final Node<T> curr, final T val) throws AttributeNotFoundException {
        if (null == curr) throw new AttributeNotFoundException("Not fount element: " + val);
        if (0 == comparator.compare(curr.val, val)) {
            if (null == curr.left) return curr.right;
            if (null == curr.right) return curr.left;
            final Node<T> successor = this.deleteSuccessor(curr.right);
            curr.val = null == successor ? null : successor.val;
            return curr;
        } else if (0 > comparator.compare(curr.val, val)) {
            curr.right = this.delete(curr.right, val);
            return curr;
        } else {
            curr.left = this.delete(curr.left, val);
            return curr;
        }
    }

    private Node<T> deleteSuccessor(final Node<T> node) {
        if (null == node) return null;
        if (null != node.left) {
            if (null == node.left.left) {
                final Node<T> successor = node.left;
                node.left = null;
                return successor;
            }
            return this.deleteSuccessor(node.left);
        }
        return node;
    }


}
