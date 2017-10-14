package linear_sort;

import java.util.Arrays;

public class CountingSort {
	public static void main(String[] args) {
		int[] arr = { 3, 7, 5, 8, 6, 8, 5 };
		CountingSort counting_sort = new CountingSort();
		int[] sort = counting_sort.sort(arr);
		System.out.println(Arrays.toString(sort));
	}

	public int[] sort(int[] arr) {
		int len = arr.length;
		int max = arr[0];
		int min = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		// 创建一个记录数组,记录从min增加到max每一个数出现的次数
		int[] c = new int[max - min + 1];
		for (int i = 0; i < arr.length; i++) {
			c[arr[i] - min]++;
		}
		// 记录每一个数<=它的数的个数
		for (int i = 1; i < c.length; i++) {
			c[i] += c[i - 1];
		}
		int[] result = new int[len];
		for (int i = arr.length - 1; i >= 0; i--) {
			result[c[arr[i] - min] - 1] = arr[i];
			c[arr[i] - min]--;
		}
		return result;
	}
}
