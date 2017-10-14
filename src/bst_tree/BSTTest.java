package bst_tree;

public class BSTTest {

	public static void main(String[] args) {
		// test1();

		Node root = new Node(5, null, null, null);
		BSTTree tree = new BSTTree(root);
		tree.insert(root, new Node(7, null, null, null));
		tree.insert(root, new Node(1, null, null, null));
		tree.insert(root, new Node(3, null, null, null));
		Node node9 = new Node(9, null, null, null);
		tree.insert(root, node9);
		tree.insert(root, new Node(2, null, null, null));
		tree.insert(root, new Node(10, null, null, null));
		tree.insert(root, new Node(4, null, null, null));
		tree.insert(root, new Node(8, null, null, null));
		tree.inOrder(root);
		tree.delete(10);
		tree.inOrder(root);
	}

	private static void test1() {
		Node root = new Node(5, null, null, null);
		BSTTree tree = new BSTTree(root);
		tree.insert(root, new Node(7, null, null, null));
		tree.insert(root, new Node(1, null, null, null));
		tree.insert(root, new Node(3, null, null, null));
		Node node9 = new Node(9, null, null, null);
		tree.insert(root, node9);
		tree.insert(root, new Node(2, null, null, null));
		tree.insert(root, new Node(10, null, null, null));
		Node successor = tree.getSuccessor(node9);
		System.out.println(successor.getValue());
		Node predecessor = tree.getPredecessor(node9);
		System.out.println(predecessor.getValue());
	}
}
