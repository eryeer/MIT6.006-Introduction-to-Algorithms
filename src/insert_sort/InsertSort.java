package insert_sort;

public class InsertSort {

	public int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
				swap(arr, j, j - 1);
			}
		}
		return arr;
	}

	// 交换i,j位置的数值
	private void swap(int[] arr, int i, int j) {
		int x = arr[i];
		arr[i] = arr[j];
		arr[j] = x;

	}

}
