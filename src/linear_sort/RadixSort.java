package linear_sort;

import java.util.Arrays;

public class RadixSort {
	public static void main(String[] args) {
		int[] arr = { 3667, 722, 51, 8990, 611, 83, 512 };
		RadixSort radixSort = new RadixSort();
		int[] sort = radixSort.sort(arr, 10, 4);
		System.out.println(Arrays.toString(sort));
	}

	/**
	 * 
	 * @param arr
	 *            输入数组
	 * @param radix
	 *            基数,即每一位可能出现数字的个数
	 * @param b
	 *            位数
	 * @return
	 */
	public int[] sort(int[] arr, int radix, int b) {
		int len = arr.length;
		int[] temp = new int[len];
		// 创建记录数组
		int[] bucket = new int[radix];

		for (int j = 0, rate = 1; j < b; j++) {
			// 清空记录数组
			Arrays.fill(bucket, 0);
			System.arraycopy(arr, 0, temp, 0, len);
			// 记录每位数字个数
			for (int i = 0; i < temp.length; i++) {
				int num = (temp[i] / rate) % radix;
				bucket[num]++;
			}
			// 记录每一个数<=它的数的个数
			for (int i = 1; i < bucket.length; i++) {
				bucket[i] += bucket[i - 1];
			}

			for (int i = temp.length - 1; i >= 0; i--) {
				int num = (temp[i] / rate) % radix;
				arr[--bucket[num]] = temp[i];
			}
			rate *= radix;
		}
		return arr;
	}
}
