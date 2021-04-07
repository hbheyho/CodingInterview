package S2Offer.algorithm;

/**
 * @Author: HB
 * @Description: 面试题63 - 股票的最大利润
 *               描述: 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次
 *               可能获得的最大利润是多少？
 *               Case:
 *                   Input: [7,1,5,3,6,4]
 *                   OutPut:5
 *               Limit: 0 <= 数组长度 <= 10^5
 *               Remark: <LC 121> 买卖股票的最佳时机
 * @CreateDate: 11:04 2021/3/27
 */

public class Q63 {

    /**
     * @Author: HB
     * @Description: 贪心解法
     * @Date: 11:05 2021/3/27
     * @Params: null
     * @Returns:
    */
    // 每次卖出要得到最大利润, 首先要保证买入的价格是最低的
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        // 使用min保存当前遍历价格左边最小的价格
        // 使用maxProfit保存最大利润
        int min = prices[0];
        int maxProfit = -prices[0];
        for (int i = 1; i < n; i++) {
            int subProfit = prices[i] - min;
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(subProfit, maxProfit);
        }
        return maxProfit < 0 ? 0 : maxProfit;
    }

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 11:06 2021/3/27
     * @Params: null
     * @Returns:
    */
    // 状态转移方程：
    // 定义dp[i]: 第i天股票所能获得的最大利润
    // 1. 第i天不卖出：dp[i] = dp[i - 1] (prices[i - 1] < minPrice)
    // 2. 第i天卖出：dp[i] = Math.max(dp[i - 1], prices[i - 1] - minPrice) (prices[i - 1] > minPrice)
    public int maxProfitByDP(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;
        // 保存左边的最小股票价格
        int minPrice = prices[0];
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = -prices[0];

        // 从第2天开始遍历
        for (int i = 1; i < n; i++) {
            if (prices[i] > minPrice) {
                dp[i + 1] = Math.max(dp[i], prices[i] - minPrice);
            } else {
                dp[i + 1] = dp[i];
            }
            minPrice = Math.min(minPrice, prices[i]);
        }

        return dp[n] < 0 ? 0 : dp[n];
    }


}
