package heap_sort;

public class HeapSort {
	// 执行堆排序
	public int[] sort(int[] arr) {
		buildMaxHeap(arr);
		int[] resultArr = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, 0, i);
			resultArr[resultArr.length - i - 1] = arr[i];
			arr[i] = Integer.MIN_VALUE;
			maxHeapify(arr, 0);
		}
		return resultArr;
	}

	// 建立一个max heap
	public int[] buildMaxHeap(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			maxHeapify(arr, i);
		}
		return arr;
	}

	// 对第i位置的节点进行堆最大化
	public int[] maxHeapify(int[] arr, int i) {
		// 如果是叶子节点,则返回
		if (isLeaf(arr, i)) {
			return arr;
			// 如果只有左侧子节点,则判断是否交换后返回
		} else if (hasOnlyLeft(arr, i)) {
			if (arr[i] < arr[2 * i + 1]) {
				swap(arr, i, 2 * i + 1);
			}
			return arr;
		} else {
			// 如果分支已经是max heap,则返回
			if (arr[i] >= arr[i * 2 + 1] && arr[i] >= arr[i * 2 + 2]) {
				return arr;
			} else {
				// 左侧子节点最大,则和左侧子节点交换,并递归
				if (arr[i * 2 + 1] > arr[i * 2 + 2]) {
					swap(arr, i, i * 2 + 1);
					return maxHeapify(arr, i * 2 + 1);
					// 右侧子节点最大,则和右侧子节点交换,并递归
				} else {
					swap(arr, i, i * 2 + 2);
					return maxHeapify(arr, i * 2 + 2);
				}
			}
		}
	}

	// 交换i,j位置的数值
	private void swap(int[] arr, int i, int j) {
		int x = arr[i];
		arr[i] = arr[j];
		arr[j] = x;

	}

	// 判断是否只有叶子节点
	public boolean isLeaf(int[] arr, int i) {
		return i >= arr.length / 2;
	}

	// 判断是否只有左侧子节点
	public boolean hasOnlyLeft(int[] arr, int i) {
		return i * 2 + 2 == arr.length;
	}
}
