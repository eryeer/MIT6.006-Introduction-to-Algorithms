package hash_table;

public class MyHashTable {
	int slots;// hash表的大小
	SlotNode[] nodeList;// hash表数组
	int keyNum; // 表中记录的key的个数

	/**
	 * @param slots
	 */
	public MyHashTable(int slots) {
		super();
		this.slots = slots;
		this.nodeList = new SlotNode[slots];
		this.keyNum = 0;
		initNodeList();
	}

	public MyHashTable() {
		this(1);
	}

	public SlotNode find(Integer key) {
		int slot = hash(key);
		SlotNode n = nodeList[slot].getNext();
		while (n != null) {
			if (n.getKey().equals(key)) {
				return n;
			}
			n = n.getNext();
		}
		return null;
	}

	/**
	 * insert key
	 * 
	 * @param key
	 */
	public void insert(Integer key) {
		SlotNode find = find(key);
		if (find == null) {
			SlotNode slotNode = new SlotNode();
			slotNode.setKey(key);
			int slot = hash(key);
			SlotNode firstNode = nodeList[slot].getNext();
			if (firstNode != null) {
				slotNode.setNext(firstNode);
			}
			nodeList[slot].setNext(slotNode);
			keyNum++;
			if (keyNum >= slots) {
				increaseTableSize();
			}
		} else {
			find.setKey(key);
		}
	}

	/**
	 * delete key
	 * 
	 * @param key
	 */
	public void delete(Integer key) {

		int slot = hash(key);
		SlotNode n = nodeList[slot].getNext();
		SlotNode prev = nodeList[slot];
		SlotNode target = null;
		while (n != null) {
			if (n.getKey().equals(key)) {
				target = n;
				prev.setNext(n.getNext());
				this.keyNum--;
				if (keyNum <= slots / 4) {
					// TODO:decrese Table Size
					decreaseTableSize();
				}
				return;
			}
			prev = n;
			n = n.getNext();
		}
		if (target == null) {
			throw new RuntimeException("Key doesn't exist!");
		}

	}

	private void decreaseTableSize() {
		SlotNode[] oldNodeList = new SlotNode[slots];
		System.arraycopy(this.nodeList, 0, oldNodeList, 0, slots);

		this.slots /= 2;

		this.nodeList = new SlotNode[slots];
		initNodeList();
		this.keyNum = 0;
		for (SlotNode oldSlotNode : oldNodeList) {
			SlotNode next = oldSlotNode.getNext();
			while (next != null) {
				insert(next.getKey());
				next = next.getNext();
			}
		}

	}

	private void increaseTableSize() {
		// copy the formal node list
		SlotNode[] oldNodeList = new SlotNode[slots];
		System.arraycopy(this.nodeList, 0, oldNodeList, 0, slots);
		// double size of the node list
		this.slots *= 2;
		this.nodeList = new SlotNode[slots];
		initNodeList();
		this.keyNum = 0;
		// rehash the key and insert into new node list
		for (SlotNode oldSlotNode : oldNodeList) {
			SlotNode next = oldSlotNode.getNext();
			while (next != null) {
				insert(next.getKey());
				next = next.getNext();
			}
		}

	}

	private void initNodeList() {
		for (int i = 0; i < nodeList.length; i++) {
			nodeList[i] = new SlotNode();
			nodeList[i].setKey(null);
			nodeList[i].setNext(null);
		}
	}

	/**
	 * hash function
	 * 
	 * @param key
	 * @return
	 */
	public int hash(int key) {
		return key % this.slots;
	}

}
