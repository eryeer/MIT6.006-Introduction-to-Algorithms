package avl_tree;

public class AVLTree {
	private Node root;

	public Node getRoot() {
		return root;
	}

	public AVLTree(Node root) {
		super();
		this.root = root;

	}

	public int getTreeHeight() {
		return root.getHeight();
	}

	public int getNodeHeight(Node node) {
		if (node == null) {
			return -1;
		} else {
			return node.getHeight();
		}
	}

	public void updateNodeHeight(Node node) {
		node.setHeight(Math.max(getNodeHeight(node.getLeft()),
				getNodeHeight(node.getRight())) + 1);
	}

	// 先序遍历
	public void preOrder() {
		preOrderRecur(root);
	}

	private void preOrderRecur(Node node) {
		if (node != null) {
			System.out.print(node.getValue() + " ");
			preOrderRecur(node.getLeft());
			preOrderRecur(node.getRight());
		}
	}

	// 中序遍历
	public void inOrder() {
		inOrderRecur(root);
	}

	private void inOrderRecur(Node node) {
		if (node != null) {
			inOrderRecur(node.getLeft());
			System.out.print(node.getValue() + " ");
			inOrderRecur(node.getRight());
		}
	}

	// 后序遍历
	public void postOrder() {
		postOrderRecur(root);
	}

	private void postOrderRecur(Node node) {
		if (node != null) {
			postOrderRecur(node.getLeft());
			postOrderRecur(node.getRight());
			System.out.print(node.getValue() + " ");
		}
	}

	// 获得最小值
	public Node getMin(Node tRoot) {
		if (tRoot == null) {
			return null;
		}
		while (tRoot.getLeft() != null) {
			tRoot = tRoot.getLeft();
		}
		return tRoot;
	}

	// 获得最大值
	public Node getMax(Node tRoot) {
		if (tRoot == null) {
			return null;
		}
		while (tRoot.getRight() != null) {
			tRoot = tRoot.getRight();
		}
		return tRoot;
	}

	// 插入节点
	public void insert(Node node) {
		recursiveInsert(root, node);
		rebalance(node.getParent().getParent());

	}

	private void recursiveInsert(Node tRoot, Node node) {
		if (node != null) {
			if (node.getValue() < tRoot.getValue()) {
				if (tRoot.getLeft() != null) {
					recursiveInsert(tRoot.getLeft(), node);
				} else {
					tRoot.setLeft(node);
					node.setParent(tRoot);
					node.setHeight(0);
				}
			} else {
				if (tRoot.getRight() != null) {
					recursiveInsert(tRoot.getRight(), node);
				} else {
					tRoot.setRight(node);
					node.setParent(tRoot);
					node.setHeight(0);
				}
			}
			tRoot.setHeight(Math.max(getNodeHeight(tRoot.getLeft()),
					getNodeHeight(tRoot.getRight())) + 1);
			tRoot.setSize(tRoot.getSize() + 1);
		}
	}

	private void rebalance(Node node) {
		while (node != null) {
			this.updateNodeHeight(node);
			if (getNodeHeight(node.getLeft()) >= 2 + getNodeHeight(node
					.getRight())) {
				if (getNodeHeight(node.getLeft().getLeft()) > getNodeHeight(node
						.getLeft().getRight())) {
					this.singleRotateWithLeft(node);
				} else {
					this.doubleRotateWithLeft(node);
				}
			} else if (getNodeHeight(node.getRight()) >= 2 + getNodeHeight(node
					.getLeft())) {
				if (getNodeHeight(node.getRight().getRight()) > getNodeHeight(node
						.getRight().getLeft())) {
					this.singleRotateWithRight(node);
				} else {
					this.doubleRotateWithRight(node);
				}
			}
			node = node.getParent();
		}
	}

	private Node singleRotateWithRight(Node node) {
		Node right = node.getRight();
		Node parent = node.getParent();
		// 修正size
		int sizeNodeLeft = node.getLeft() == null ? 0 : node.getLeft()
				.getSize();
		int sizeRightRight = right.getRight() == null ? 0 : right.getRight()
				.getSize();
		node.setSize(node.getSize() - 1 - sizeRightRight);
		right.setSize(right.getSize() + 1 + sizeNodeLeft);
		// 旋转
		node.setParent(right);
		node.setRight(right.getLeft());
		if (right.getLeft() != null) {
			right.getLeft().setParent(node);
		}
		right.setParent(parent);
		right.setLeft(node);
		if (parent != null) {
			if (parent.getLeft() == node) {
				parent.setLeft(right);
			} else {
				parent.setRight(right);
			}
		} else {
			this.root = right;
		}
		this.updateNodeHeight(node);
		this.updateNodeHeight(right);
		return right; // new root
	}

	private Node singleRotateWithLeft(Node node) {
		Node left = node.getLeft();
		Node parent = node.getParent();
		// 修正size
		int sizeLeftLeft = left.getLeft() == null ? 0 : left.getLeft()
				.getSize();
		int sizeNodeRight = node.getRight() == null ? 0 : node.getRight()
				.getSize();
		node.setSize(node.getSize() - 1 - sizeLeftLeft);
		left.setSize(left.getSize() + 1 + sizeNodeRight);
		// 旋转
		node.setParent(left);
		node.setLeft(left.getRight());
		if (left.getRight() != null) {
			left.getRight().setParent(node);
		}
		left.setParent(parent);
		left.setRight(node);
		if (parent != null) {
			if (parent.getLeft() == node) {
				parent.setLeft(left);
			} else {
				parent.setRight(left);
			}
		} else {
			this.root = left;
		}
		this.updateNodeHeight(node);
		this.updateNodeHeight(left);
		return left; // new root
	}

	private void doubleRotateWithRight(Node node) {
		this.singleRotateWithLeft(node.getRight());
		this.singleRotateWithRight(node);
	}

	private void doubleRotateWithLeft(Node node) {
		this.singleRotateWithRight(node.getLeft());
		this.singleRotateWithLeft(node);

	}

	// select kth smallest node in the tree
	public Node select(int k) {
		return recuriveSelect(root, k);
	}

	private Node recuriveSelect(Node node, int k) {
		int leftSize = 0;
		if (node.getLeft() != null) {
			leftSize = node.getLeft().getSize();
		}
		if (leftSize + 1 == k) {
			return node;
		} else if (leftSize + 1 < k) {
			return recuriveSelect(node.getRight(), k - 1 - leftSize);
		} else {
			return recuriveSelect(node.getLeft(), k);
		}
	}

	// 删除节点,三种情况
	// 1.p为叶子节点，直接删除该节点，再修改其父节点的指针
	// 2.p为单支节点（即只有左子树或右子树）。让p的子树与p的父亲节点相连，删除p即可；
	// 3.有两个孩子的情况，当前结点与右子树中最小的元素交换，然后删除原最小元素结点.
	// 删除之后从被删除节点的父节点开始向上重新矫正height和size,并rebalance
	public void delete(int val) {
		Node parent = deleteRecursion(this.root, val);
		Node node = parent;
		while (node != null) {
			updateNodeHeight(node);
			node.setSize(node.getSize() - 1);
			node = node.getParent();
		}
		rebalance(parent);
	}

	private Node deleteRecursion(Node root, int val) {
		if (root == null) {
			throw new RuntimeException("Node is not found");
		}
		if (root.getValue() > val) {
			return deleteRecursion(root.getLeft(), val);
		} else if (root.getValue() < val) {
			return deleteRecursion(root.getRight(), val);
		} else {// 找到删除节点位置
			if (root.getLeft() != null && root.getRight() != null) {
				return deleteRightMin(root);
			} else {
				Node parent = root.getParent();
				// 如果删除的是根节点,使其左/右子树的根作为根
				if (parent == null) {
					if (root.getLeft() != null) {
						this.root = root.getLeft();
					} else if (root.getRight() != null) {
						this.root = root.getRight();
					} else {
						this.root = null;
					}
				} else { // 如果不是根节点
					if (root.getLeft() != null) {
						if (parent.getLeft() == root) {
							parent.setLeft(root.getLeft());
						} else {
							parent.setRight(root.getLeft());
						}
						root.getLeft().setParent(parent);
					} else if (root.getRight() != null) {
						if (parent.getLeft() == root) {
							parent.setLeft(root.getRight());
						} else {
							parent.setRight(root.getRight());
						}
						root.getRight().setParent(parent);
					} else {
						if (parent.getLeft() == root) {
							parent.setLeft(null);
						} else {
							parent.setRight(null);
						}
					}
					freeNode(root);
				}
				return parent;
			}
		}
	}

	private Node deleteRightMin(Node root) {
		Node min = getMin(root.getRight());
		Node parent = min.getParent();
		root.setValue(min.getValue());
		if (min.getRight() != null) {
			if (min.getParent().getLeft() == min) {
				min.getParent().setLeft(min.getRight());
			} else {
				min.getParent().setRight(min.getRight());
			}
			min.getRight().setParent(min.getParent());
		} else {
			if (min.getParent().getLeft() == min) {
				min.getParent().setLeft(null);
			} else {
				min.getParent().setRight(null);
			}
		}
		freeNode(min);
		return parent;
	}

	private void freeNode(Node node) {
		if (node != null) {
			node.setParent(null);
			node.setLeft(null);
			node.setRight(null);
		}
	}
}
