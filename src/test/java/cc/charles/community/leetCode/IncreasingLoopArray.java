package cc.charles.community.leetCode;

/**
 * 腾讯-微信社招算法题：
 *
 * 给定一个递增循环整数数组，从里面找出最小的元素，使用的算法越快越好。特别地，最小的元素可能出现在数组中间。比如：50, 52, 63, 90, 3, 8, 15, 44。
 * int findmin( int array[], int count );
 * @author charlesdong
 * @version 1.0
 * @description
 * @since 1.8
 */
public class IncreasingLoopArray {

    public static void main(String[] args) {
        int[] numList = new int[]{50, 52, 63, 90, 3, 8, 15, 44};
        int res = findMin(numList, 8);
        System.out.print(res);
    }

    private static int findMin(int[] arr, int count) {
        if(arr[count - 1] > arr[0]) {
            return arr[0];
        }
        int left = 0;
        int right = count - 1;

        while (right > left + 1) {
            int mid = left + (right - left) / 2;
            if(arr[mid] < arr[(mid - 1 + count) % count]) {
                return arr[mid];
            } else {
                if(arr[mid] > arr[left]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        return arr[right];
    }
}
