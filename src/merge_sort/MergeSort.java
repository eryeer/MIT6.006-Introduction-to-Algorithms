package merge_sort;

public class MergeSort {
	public int[] sort(int[] arr) {
		if (arr.length == 1) {
			return arr;
		}
		int[] arr1 = new int[arr.length / 2];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = arr[i];
		}
		int[] arr2 = new int[arr.length - arr.length / 2];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = arr[i + arr.length / 2];
		}
		int[] a1 = sort(arr1);
		int[] a2 = sort(arr2);

		return merge(a1, a2);
	}

	public int[] merge(int[] a, int[] b) {
		// new a merge array
		int[] c = new int[a.length + b.length];
		// pointer of a,b,c
		int pa = 0, pb = 0, pc = 0;

		while (pa < a.length && pb < b.length) {
			if (a[pa] < b[pb]) {
				c[pc++] = a[pa++];
			} else {
				c[pc++] = b[pb++];
			}
		}
		if (pa == a.length) {
			while (pb < b.length) {
				c[pc++] = b[pb++];
			}
		} else if (pb == b.length) {
			while (pa < a.length) {
				c[pc++] = a[pa++];
			}
		}
		return c;
	}
}
