package bst_tree;

public class BSTTree {
	private Node root;

	public BSTTree(Node root) {
		this.root = root;
	}

	public BSTTree() {
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	// 先序遍历
	public void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.getValue() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}

	// 中序遍历
	public void inOrder(Node node) {
		if (node != null) {
			inOrder(node.getLeft());
			System.out.print(node.getValue() + " ");
			inOrder(node.getRight());
		}
	}

	// 后序遍历
	public void postOrder(Node node) {
		if (node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			System.out.print(node.getValue() + " ");
		}
	}

	// 插入
	public void insert(Node tRoot, Node node) {
		if (node != null) {
			if (node.getValue() < tRoot.getValue()) {
				if (tRoot.getLeft() != null) {
					insert(tRoot.getLeft(), node);
				} else {
					tRoot.setLeft(node);
					node.setParent(tRoot);
				}
			} else {
				if (tRoot.getRight() != null) {
					insert(tRoot.getRight(), node);
				} else {
					tRoot.setRight(node);
					node.setParent(tRoot);
				}
			}
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

	// 获得后继节点
	public Node getSuccessor(Node node) {
		if (node.getRight() != null) {
			return getMin(node.getRight());
		}

		while (node.getParent() != null && node.getParent().getRight() == node) {
			node = node.getParent();
		}
		return node.getParent();
	}

	// 获得前驱节点
	public Node getPredecessor(Node node) {
		if (node.getLeft() != null) {
			return getMax(node.getLeft());
		}

		while (node.getParent() != null && node.getParent().getLeft() == node) {
			node = node.getParent();
		}
		return node.getParent();
	}

	// 查找值为val的节点
	public Node searchNode(int val) {
		return searchNodeRecursion(this.root, val);
	}

	private Node searchNodeRecursion(Node root, int val) {
		if (root == null) {
			return null;
		}
		if (root.getValue() < val) {
			return searchNodeRecursion(root.getRight(), val);
		} else if (root.getValue() > val) {
			return searchNodeRecursion(root.getLeft(), val);
		} else {
			return root;
		}
	}

	// 删除节点,三种情况
	// 1.p为叶子节点，直接删除该节点，再修改其父节点的指针
	// 2.p为单支节点（即只有左子树或右子树）。让p的子树与p的父亲节点相连，删除p即可；
	// 3.有两个孩子的情况，当前结点与右子树中最小的元素交换，然后删除原最小元素结点.
	public void delete(int val) {
		deleteRecursion(this.root, val);
	}

	private void deleteRecursion(Node root, int val) {
		if (root == null) {
			throw new RuntimeException("Node is not found");
		}
		if (root.getValue() > val) {
			deleteRecursion(root.getLeft(), val);
		} else if (root.getValue() < val) {
			deleteRecursion(root.getRight(), val);
		} else {// 找到删除节点位置
			if (root.getLeft() != null && root.getRight() != null) {
				deleteRightMin(root);
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
			}
		}
	}

	private void deleteRightMin(Node root) {
		Node min = getMin(root.getRight());
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
	}

	private void freeNode(Node node) {
		if (node != null) {
			node.setParent(null);
			node.setLeft(null);
			node.setRight(null);
		}
	}

}
