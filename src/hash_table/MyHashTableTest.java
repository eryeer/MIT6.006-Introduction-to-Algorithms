package hash_table;

public class MyHashTableTest {
	public static void main(String[] args) {
		// testInsert();
		MyHashTable table = new MyHashTable();
		table.insert(5);
		table.insert(28);
		table.insert(19);
		table.insert(15);
		table.insert(20);
		table.insert(33);
		table.insert(12);
		table.insert(17);
		table.insert(10);

		table.delete(28);
		table.delete(19);
		table.delete(15);
		table.delete(10);
		table.delete(5);
		table.delete(20);
		table.delete(33);
		table.delete(12);
		table.delete(17);
	}

	private static void testInsert() {
		MyHashTable table = new MyHashTable();
		table.insert(5);
		table.insert(28);
		table.insert(19);
		table.insert(15);
		table.insert(20);
		table.insert(33);
		table.insert(12);
		table.insert(17);
		table.insert(10);
		System.out.println(table.find(28));
	}

}
