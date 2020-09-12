package cc.charles.community.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * 找出缺失的第一个正整数：
 * 如：给定一个数组A[0....N-1],找到从1开始，第一个不在数组中的正整数，比如 3,5,7,1 ，6，-3，2 结果是4.   时间O(n) 空间O(1)
 *
 * @author charlesdong
 * @version 1.0
 * @since 1.8
 */
public class DeletionNum {
    public static void main(String[] args) {
        int[] numList = new int[]{3, 4, -1, 1};
        int num = findMissingNum(numList);
        System.out.println(num);
    }

    /**
     * 利用hashSet存储数组中的数，循环[1,N+1]，不在Set中的数即是最小缺失的整数。该算法空间复杂度为O(N)，不满足需求
     *
     * @param nums 整型数组
     * @return 缺失的数字
     */
    private static int findNum(int[] nums) {
        int len = nums.length;
        if (len < 1) {
            return 1;
        }
        Set<Integer> intSet = new HashSet<>();
        intSet.add(nums[0]);
        for (int i = 1; i < len; i++) {
            intSet.add(nums[i]);
        }
        for (int j = 1; j <= len + 1; j++) {
            if (!intSet.contains(j)) {
                return j;
            }
        }
        return 1;
    }

    private static int findMissingNum(int[] nums) {
        int n = nums.length;
        //遍历数组，将小于0的元素置为N+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        //[3,4,5,1]
        //遍历数组，绝对值小于N的数字作为下标，下标-1对应的元素变为负数
        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]);
            if (x <= n) {
                nums[x - 1] = -Math.abs(nums[x - 1]);
            }
        }

        //遍历数组，找到第一个正整数，下标+1即是需要找的值
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
