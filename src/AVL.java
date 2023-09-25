import java.util.Random;

public class AVL<T extends Comparable<T>> extends ATree<T> {

    public static void main(final String[] args) {
        final int[][] versions = {
                {50, 30, 70, 60, 80, 65},
                {50, 30, 70, 20, 10},
                {50, 30, 70, 80, 90},
                {50, 30, 70, 20, 40, 35},
                {50, 30, 70, 40, 35},
                {50, 30, 70, 60, 65},
                {50, 30, 70, 20, 10, 25},
                {50, 30, 70, 20, 10, 5}
        };

        for (int i = 0; i < versions.length; i++) {
            final AVL<Integer> avlTree = new AVL<>();
            System.out.println("Inserting version " + (i + 1) + " elements:");
            for (final int element : versions[i]) {
                System.out.print(element + " ");
                avlTree.insert(element);
            }

            // Print the original AVL tree
            System.out.println("\nOriginal AVL Tree:");
            avlTree.printTree();

            // Randomly delete nodes
            final Random random = new Random();
            System.out.println("\nDeleting nodes:");
            for (int j = 0; j < versions[i].length; j++) {
                int indexToDelete = random.nextInt(versions[i].length - j);

                indexToDelete = j;
                final int elementToDelete = versions[i][indexToDelete];
                System.out.println("Deleting node with value " + elementToDelete);
                avlTree.delete(elementToDelete);

                // Remove the deleted element from the array
                versions[i][indexToDelete] = versions[i][versions[i].length - j - 1];

                // Print the AVL tree after deletion
                avlTree.printTree();

                final boolean isBalanced = avlTree.isBalanced();
                System.out.println("\nIs AVL tree balanced? " + isBalanced + "\n");
            }
            System.out.println("=====================\n");
        }
    }

    public boolean isBalanced() {
        return this.isBalanced(this.root);
    }

    private boolean isBalanced(final Node<T> node) {
        if (null == node)
            return true;

        final int balanceFactor = this.getBalanceFactor(node);
        return 1 >= Math.abs(balanceFactor) && this.isBalanced(node.left) && this.isBalanced(node.right);
    }

    private int getBalanceFactor(final Node<T> node) {
        return this.getHeight(node.left) - this.getHeight(node.right);
    }

    @Override
    public void printTree() {
        this.printTree(this.root, 0);
    }

    private void printTree(final Node<T> node, final int indent) {
        if (null == node)
            return;

        // Print right subtree
        this.printTree(node.right, indent + 4);

        // Print the current node
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(node.val);

        // Print left subtree
        this.printTree(node.left, indent + 4);
    }


    private int balanceFactor(final Node<T> node) {
        final int a = this.getHeight(node.left);
        final int b = this.getHeight(node.right);
        return a - b;
    }


    private Node<T> leftRotation(final Node<T> node) {
        final Node<T> rightLeft = node.right.left;
        final Node<T> head = node.right;
        head.left = node;
        node.right = rightLeft;
        return head;
    }

    private Node<T> rightRotation(final Node<T> node) {
        final Node<T> leftRight = node.left.right;
        final Node<T> head = node.left;
        head.right = node;
        node.left = leftRight;
        return head;
    }

    @Override
    public void insert(T val) {
        if (null == this.root) {
            root = new Node<>(val);
        } else {
            this.root = this.insert(root, val);
        }
    }


    private Node<T> insert(final Node<T> curr, T val) {
        if (null == curr) return new Node<>(val);
        if (0 > this.comparator.compare(curr.val, val)) {
            curr.right = this.insert(curr.right, val);
        } else {
            curr.left = this.insert(curr.left, val);
        }
        final int balanceFactor = this.balanceFactor(curr);
        if (1 < balanceFactor || -1 > balanceFactor) {
            return this.toBalanceInInsert(curr, val, balanceFactor);
        }
        return curr;
    }

    private Node<T> toBalanceInInsert(final Node<T> curr, final T val, final int balanceFactor) {
        if (1 < balanceFactor) {    //case L?
            if (0 > this.comparator.compare(curr.left.val, val)) { // case LR
                return this.toBalance(curr, true, true);
            }
            return this.toBalance(curr, true, false); //case LL
        } else if (-1 > balanceFactor) {  //case R?
            if (0 < this.comparator.compare(curr.right.val, val)) { // case RL
                return this.toBalance(curr, false, true);
            }
            return this.toBalance(curr, false, false);//case RR
        }
        return null;
    }

    private Node<T> toBalance(final Node<T> head, final boolean levelOneCase, final boolean levelTwoCase) {
        if (levelOneCase) {
            if (levelTwoCase) { // case LR
                head.left = this.leftRotation(head.left);
            }
            return this.rightRotation(head);
        } else {
            if (levelTwoCase) { // case RL
                head.right = this.rightRotation(head.right);
            }
            return this.leftRotation(head);
        }
    }

    @Override
    public void delete(T val) {
        if (null == this.root) return;
        if (this.root.val == val) {
            this.root = null;
        } else {
            this.delete(this.root, val);
        }
    }

    private Node<T> delete(final Node<T> curr, final T val) throws IllegalArgumentException {
        if (0 > this.comparator.compare(curr.val, val)) {
            if (null == curr.right) {
                throw new IllegalArgumentException("Value not found" + val);
            }
            if (curr.right.val != val) {
                curr.right = this.delete(curr.right, val);
            } else {
                return null;
            }

        } else {
            if (null == curr.left) {
                throw new IllegalArgumentException("Value not found" + val);
            }
            if (curr.left.val != val) {
                curr.left = this.delete(curr.left, val);
            } else {
                return null;
            }
        }
        final int balanceFactor = this.balanceFactor(curr);
        if (1 < balanceFactor || -1 > balanceFactor) {
            return this.toBalanceInDelete(curr, val, balanceFactor);
        }
        return curr;

    }

    private Node<T> toBalanceInDelete(final Node<T> curr, final T val, final int balanceFactor) {
        final int balanceFactorLevelTwo;
        if (1 < balanceFactor) {    //case L?
            balanceFactorLevelTwo = this.balanceFactor(curr.left);
            if (0 > balanceFactorLevelTwo) { // case LR
                return this.toBalance(curr, true, true);
            }
            return this.toBalance(curr, true, false); //case LL
        } else if (-1 > balanceFactor) {  //case R?
            balanceFactorLevelTwo = this.balanceFactor(curr.right);
            if (0 < balanceFactorLevelTwo) { // case RL
                return this.toBalance(curr, false, true);
            }
            return this.toBalance(curr, false, false);//case RR
        }
        return null;
    }

}

