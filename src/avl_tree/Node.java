package avl_tree;

public class Node {
	private int value;
	private Node parent;
	private Node left;
	private Node right;
	private int height;
	private int size;// this size contains size of both subtrees and itself

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * temporarily set aside one has left, right and parent
	 * 
	 * @param value
	 * @param parent
	 * @param left
	 * @param right
	 * @param height
	 * @param size
	 */
	public Node(int value, Node parent, Node left, Node right) {
		this.value = value;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.height = Math.max((left == null ? -1 : left.getHeight()),
				(right == null ? -1 : right.getHeight())) + 1;
		this.size = 1;

	}

	public Node(int value) {
		this(value, null, null, null);
	}

	/**
	 * 
	 */
	public Node() {
		super();
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", height=" + height + ", size=" + size
				+ "]";
	}

}
