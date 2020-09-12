package cc.charles.community.leetCode;

/**
 * 快速排序
 * 快速排序的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据比另一部分的所有数据要小，
 * 再按这种方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，使整个数据变成有序序列。
 *
 * @author charlesdong
 * @version 1.0
 * @since 1.8
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] numList = new int[]{100, 2, 1, 7, 13, 21, 5, 14, 19};
        quickSort(numList, 0, 8);
        for (int num : numList) {
            System.out.println(num);
        }
    }

    public static void quickSort(int[] numList, int start, int end) {
        int point = numList[start];
        int left = start;
        int right = end;
        while (left <= right) {
            while (numList[left] < point) {
                left++;
            }
            while (numList[right] > point) {
                right--;
            }
            if (left <= right) {
                int temp = numList[left];
                numList[left] = numList[right];
                numList[right] = temp;
                left++;
                right--;
            }
        }
        if (left < end) {
            quickSort(numList, left, end);
        }
        if (start < right) {
            quickSort(numList, start, right);
        }
    }

    /**
     * 递归排序
     *
     * @param src
     * @param begin
     * @param end
     */
    private static void quickSort2(int[] src, int begin, int end) {
        if (begin < end) {
            int key = src[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                while (i < j && src[j] > key) {
                    j--;
                }
                if (i < j) {
                    src[i] = src[j];
                    i++;
                }
                while (i < j && src[i] < key) {
                    i++;
                }
                if (i < j) {
                    src[j] = src[i];
                    j--;
                }
            }
            src[i] = key;
            quickSort2(src, begin, i - 1);
            quickSort2(src, i + 1, end);
        }
    }
}
