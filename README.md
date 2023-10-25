# Data Structures Library

This is a collection of commonly used data structures implemented in Python. Each data structure is designed to provide efficient operations for various use cases.

## Data Structures Included

1. **ATree**  
  The ATree class is an abstract base class for various tree structures. It includes common tree operations and features:
  
  setComparator(Comparator<T> comparator): Sets the comparator for the tree.
  isEmpty(): Checks if the tree is empty.
  clear(): Clears the tree.
  contains(T val): Checks if the tree contains a specific value.
  getHeight(): Gets the height of the tree.
  getSize(): Gets the size (number of nodes) of the tree.
  getMin(): Gets the minimum value in the tree.
  getMax(): Gets the maximum value in the tree.
  successor(T val): Finds the successor of a given value.
  predecessor(T val): Finds the predecessor of a given value.
  Traversal methods: preorderTraversal and inorderTraversal.
  Abstract methods: printTree, insert, and delet

2. **AVL Tree**  
  It appears you've shared two Java classes: AVL and BST, which are implementations of binary search trees. The AVL class implements an AVL tree, which is a self-balancing binary search tree, while the BST class implements a basic binary search tree.
  
  Here's a brief overview of the two classes:
  
  AVL Class
  The AVL class implements an AVL tree, which is a self-balancing binary search tree.
  It uses generics to allow the storage of elements of any class that implements the Comparable interface.
  The main method contains code to create different versions of the AVL tree, insert elements, and randomly delete nodes from the tree, checking the balance factor at each step.
  The isBalanced method checks if the AVL tree is balanced.
  The printTree method is used to print the tree structure.
  It includes methods for left and right rotations to maintain balance during insertion and deletion.
  The insert method inserts elements into the AVL tree while maintaining balance.
  The delete method removes elements from the AVL tree and balances it accordingly.

3. **Binary Search Tree (BST)**  
  The BST class implements a basic binary search tree (not self-balancing).
  Similar to the AVL class, it also uses generics to store elements that implement the Comparable interface.
  The main method demonstrates the basic operations of the binary search tree, including insertion, checking for element existence, finding minimum and maximum values, getting the tree's height and size, finding successor and predecessor, and deleting elements from the tree.
  The printTree method is used to print the tree structure.
  The insert method inserts elements into the binary search tree.
  The delete method removes elements from the binary search tree.

4. **HeapOfArray**  
  The HeapOfArray class is an implementation of a binary heap using an array. It includes methods for building a heap, insertion, deletion, heap sort, and more.
  
  setComparator(Comparator<T> comparator): Sets the comparator for the heap.
  insert(T value): Inserts a value into the heap.
  update(T value, int index): Updates the value at a specific index.
  delete(int index): Deletes the element at a specific index.
  heapSort(): Sorts the heap using heap sort.
  Traversal methods: traverseRecursive.

5. **Priority Queue**  
  The PriorityQueue class is an implementation of a priority queue using a max-heap. It uses the HeapOfArray class to manage its underlying heap structure.
  
  insert(T value, int priority): Inserts an element with a priority value into the priority queue.
  getmax(): Gets the element with the maximum priority.
  extractMax(): Removes and returns the element with the maximum priority.
  incresingKey(int index, int addingProirity): Increases the priority of an element at a specific index.
