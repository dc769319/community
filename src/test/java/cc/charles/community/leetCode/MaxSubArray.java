package cc.charles.community.leetCode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author charlesdong
 * @version 1.0
 * @link https://leetcode-cn.com/problems/maximum-subarray/solution/xiang-xi-jie-du-dong-tai-gui-hua-de-shi-xian-yi-li/
 * @since 1.8
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] numList = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxNum = findMax(numList);
        System.out.println(maxNum);
    }

    /**
     * 找到最大和
     *
     * @param numList 整数数组
     * @return 连续子数组的最大和
     */
    private static int findMax(int[] numList) {
        if (numList == null) {
            return 0;
        }
        int len = numList.length;
        if (len == 1) {
            return numList[0];
        }
        int curSumMax = numList[0];
        int subSumMax = numList[0];
        for (int i = 1; i < len; i++) {
            curSumMax = Math.max(numList[i], curSumMax + numList[i]);
            subSumMax = Math.max(subSumMax, curSumMax);
        }
        return subSumMax;
    }
}
