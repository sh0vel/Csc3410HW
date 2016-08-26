/* Shovon Hossain
 * Homework 7
 * 12/2/14
 * 
 * The purpose of this program is to sort 50 randomly generated numbers
 * using a binary search tree. It consists of two classes: treeNode and
 * BSTree The treeNode class is used to create nodes that make up the binary
 * tree. It has an int value which holds the values of the nodes. It has a
 * leftChild and a rightChild variable which are used to create the left and
 * right children of each node. The constructer of this class takes only an
 * int value that contains the value of the node. The addNode method adds
 * nodes into the tree based on its value. If the tree has not root it makes
 * the node the root. If there is a root the root becomes the parent node
 * and it checks the parent nodes value and compares it to the new nodes
 * value. If the new node has a lesser value it adds the new node as the
 * leftChild of the parent node, else it becomes the right child. The sort
 * method is a recursive function that prints the tree in order of its
 * values from smallest to largest. It has one parameter called currentNode
 * which is to be the root note upon execution. From there it checks if the
 * current node is null if not it calls itself with the currentNodes
 * leftChild as the currentNode. It continues this untill there is no more
 * left children. Then it prints out the value and does the same with the
 * rightChild.
 */
public class BSTree {
	static treeNode root;
	public static BSTree tree = new BSTree();

	/*
	 * The treeNode class is used to create nodes that make up the binary tree.
	 * It has an int value which holds the values of the nodes. It has a
	 * leftChild and a rightChild variable which are used to create the left and
	 * right children of each node. The constructer of this class takes only an
	 * int value that contains the value of the node.
	 */
	class treeNode {
		int value;
		treeNode leftChild, rightChild;

		treeNode(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		// BSTree tree = new BSTree();
		int numbers[] = generate50Numbers();
		printArray(numbers);
		for (int i = 0; i < numbers.length; i++) {
			addNode(numbers[i]);
		}
		System.out.println("\n\nSorted tree: ");
		tree.sort(root);

	}

	/*
	 * The addNode method adds nodes into the tree based on its value. If the
	 * tree has not root it makes the node the root. If there is a root the root
	 * becomes the parent node and it checks the parent nodes value and compares
	 * it to the new nodes value. If the new node has a lesser value it adds the
	 * new node as the leftChild of the parent node, else it becomes the right
	 * child.
	 */
	public static void addNode(int value) {
		treeNode newNode = tree.new treeNode(value);
		if (root == null) {
			root = newNode;
		} else {
			treeNode currentNode = root;
			treeNode parent;
			while (true) {
				parent = currentNode;
				if (value < currentNode.value) {
					currentNode = currentNode.leftChild;
					if (currentNode == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					currentNode = currentNode.rightChild;
					if (currentNode == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	/*
	 * The sort method is a recursive function that prints the tree in order of
	 * its values from smallest to largest. It has one parameter called
	 * currentNode which is to be the root note upon execution. From there it
	 * checks if the current node is null if not it calls itself with the
	 * currentNodes leftChild as the currentNode. It continues this untill there
	 * is no more left children. Then it prints out the value and does the same
	 * with the rightChild.
	 */
	public static void sort(treeNode currentNode) {
		if (currentNode != null) {
			sort(currentNode.leftChild);
			System.out.print(currentNode.value + " ");
			sort(currentNode.rightChild);
		}
	}

	/* The randomNumber method simply generated random numbers between 1 and 50 */
	static int randomNumber() {
		int range = (99 - 1) + 1;
		return (int) (Math.random() * range) + 1;
	}

	/*
	 * The generate50Numbers simply creates an array called numbers and store 50
	 * random numbers in it. The random numbers are created using the
	 * randomNumber method.
	 */
	public static int[] generate50Numbers() {
		int numbers[] = new int[50];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = randomNumber();
		}
		return numbers;
	}

	/* The printArray method is used to print out an aray of numbers. */
	public static void printArray(int array[]) {
		System.out.println("Numbers generated: ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}
