package array;

/**
 * @Author: HB
 * @Description: 面试题42 - 连续子数组的最大和
 *               描述: 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。
 *                     求所有子数组的和的最大值。
 *                     要求时间复杂度为O(n)。
 *               Case:
 *               Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 *               Output: 6
 *               Limit： 1 <= arr.length <= 10^5
 *                       -100 <= arr[i] <= 100
 *               Remark：<53> 最大子序和
 * @CreateDate: 15:24 2021/4/6
 */

public class Q42 {

    /**
     * @Author: HB
     * @Description: 前缀和解法
     * @Date: 15:25 2021/4/6
     * @Params: null
     * @Returns:
    */
    public int maxSubArrayByPreSum(int[] nums) {
        int n = nums.length;

        // preSum[i]: 前i个元素的元素和
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            preSum[i] = preSum[i - 1] + nums[i - 1];

        // 保存子数组的最大和
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // preSum[j] - preSum[i]: 会计算 nums[i, j - 1]之间的元素之和
                maxSum = Math.max(maxSum, preSum[j] - preSum[i]);
            }
        }

        return maxSum;
    }

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 15:26 2021/4/6
     * @Params: null
     * @Returns:
    */
    // 1. 定义状态：dp[i]：以第i个元素结尾的子数组的最大和
    // 2. 状态转移方程：当在处理第i个元素时, 若dp[i - 1] < 0, 则dp[i] = nums[i - 1]; 若dp[i - 1] >= 0, 则dp[i] = dp[i - 1] + nums[i -1]. 综上：
    //      dp[i] = nums[i - 1], dp[i - 1] < 0 (另起炉灶)
    //      dp[i] = dp[i - 1] + nums[i - 1], dp[i - 1] >= 0
    // 综上： dp[i - 1] = Math.max(dp[i - 1] + nums[i - 1], dp[i - 1]);
    // 3. 初始化：dp[0] = 0, dp[1] = nums[0]
    // 4. 返回结果： maxSum => 对dp数组进行一次遍历取得最大值
    public int maxSubArrayByDP(int[] nums) {
        int n = nums.length;

        // 初始化dp数组
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        int maxSum = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(nums[i - 1], dp[i - 1] + nums[i - 1]);
            maxSum = Math.max(dp[i], maxSum);
        }

        return maxSum;
    }

    /**
     * @Author: HB
     * @Description: 动态规划状态压缩
     * @Date: 15:26 2021/4/6
     * @Params: null
     * @Returns:
    */
    // 1. 状态压缩：因为要计算dp[i], 只需要知道 dp[i - 1]即可, 即可完成状态压缩
    public int maxSubArrayByCompress(int[] nums) {
        int n = nums.length;

        // 初始化preDP遍历
        int preSum = 0;

        int maxSum = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int sum = Math.max(nums[i - 1], preSum + nums[i - 1]);
            maxSum = Math.max(sum, maxSum);
            preSum = sum;
        }

        return maxSum;
    }

}
