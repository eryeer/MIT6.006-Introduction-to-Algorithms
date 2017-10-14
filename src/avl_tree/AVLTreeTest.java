package avl_tree;

import org.junit.Test;

public class AVLTreeTest {
	@Test
	public void test1() {
		AVLTree tree = generateTree();
		tree.insert(new Node(12));
		tree.insert(new Node(23));
		tree.insert(new Node(24));
		tree.insert(new Node(19));
		tree.inOrder();
		System.out.println(tree.select(5));
		System.out.println(tree.select(10));
	}

	@Test
	public void test2() {
		AVLTree tree = generateTree();
		tree.delete(13);
		tree.delete(13);
		tree.delete(10);
		tree.delete(14);
		tree.inOrder();

	}

	public AVLTree generateTree() {
		AVLTree tree = new AVLTree(new Node(20));
		tree.insert(new Node(16));
		tree.insert(new Node(25));
		tree.insert(new Node(13));
		tree.insert(new Node(17));
		tree.insert(new Node(22));
		tree.insert(new Node(37));
		tree.insert(new Node(10));
		tree.insert(new Node(11));
		tree.insert(new Node(14));
		tree.insert(new Node(13));
		return tree;
	}

}
