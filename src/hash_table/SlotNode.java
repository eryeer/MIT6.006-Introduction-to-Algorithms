package hash_table;

public class SlotNode {

	private SlotNode next;
	private Integer key;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public SlotNode getNext() {
		return next;
	}

	public void setNext(SlotNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "SlotNode [key=" + key + "]";
	}

}
