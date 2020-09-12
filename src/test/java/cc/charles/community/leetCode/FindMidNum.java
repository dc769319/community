package cc.charles.community.leetCode;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author charlesdong
 * @version 1.0
 * @since 1.8
 */
public class FindMidNum {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 6, 8, 10};
        int[] nums2 = new int[]{2, 4, 7, 13, 21, 31};
        double midNum = findMinNum(nums1, nums2);
        System.out.println(midNum);
    }

    private static double findMinNum(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mergeLen = len1 + len2;
        int[] mergeArr = new int[mergeLen];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                mergeArr[k] = nums1[i];
                k++;
                i++;
            } else {
                mergeArr[k] = nums2[j];
                k++;
                j++;
            }
        }
        while (i < len1) {
            mergeArr[k] = nums1[i];
            k++;
            i++;
        }

        while (j < len2) {
            mergeArr[k] = nums2[j];
            k++;
            j++;
        }
        if (mergeLen % 2 == 0) {
            int midIndexRight = mergeLen / 2;
            int midIndexLeft = midIndexRight - 1;
            return (float) (mergeArr[midIndexLeft] + mergeArr[midIndexRight]) / 2;
        } else {
            int midIndex = mergeLen / 2;
            return mergeArr[midIndex];
        }
    }
}
