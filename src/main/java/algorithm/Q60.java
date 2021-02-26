package algorithm;

import java.util.ArrayList;

/**
 * @Author: HB
 * @Description: 面试题60 - n个骰子的点数
 *               描述: 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，
 *                      打印出s的所有可能的值出现的概率。
 *                     你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能
 *                     掷出的点数集合中第 i 小的那个的概率。
 *               Case:
 *                   Input: 1
 *                   OutPut: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 *               Limit: 0 <= n <= 11
 *               Remark:
 * @CreateDate: 16:21 2021/2/26
 */

public class Q60 {

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 16:22 2021/2/26
     * @Params: null
     * @Returns:
    */
    public double[] dicesProbabilityByDP(int n) {

        // dp[i][j]: 当投掷琬i枚骰子之后, i枚骰子的点数之和为j的次数
        int[][] dp = new int[n + 1][n * 6 + 1];

        // 初始化
        for (int i = 1; i <= 6; i++)
            dp[1][i] = 1;

        // 填充DP数组
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 6 * n; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j >= k)
                        dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        // 计算出现概率
        int totalCounts = (int)Math.pow(6, n);
        ArrayList<Double> res = new ArrayList<>();
        for (int i = 1; i <= 6 * n; i++) {
            if (dp[n][i] == 0)
                continue;
            res.add(dp[n][i] / (double)totalCounts);
        }

        return res.stream().mapToDouble(Double::valueOf).toArray();
    }

    /**
     * @Author: HB
     * @Description:  动态规划解法 - 滚动数组优化
     * @Date: 16:23 2021/2/26
     * @Params: null
     * @Returns:
    */
    public double[] dicesProbabilityByDP2(int n) {

        // dp[i][j]: 当投掷琬i枚骰子之后, i枚骰子的点数之和为j的次数
        // 开辟一个一维数组, 进行滚动数组优化
        int[] dp = new int[n * 6 + 1];

        // 初始化
        for (int i = 1; i <= 6; i++)
            dp[i] = 1;

        // 填充DP数组
        // 防止覆盖以前的结果, 需要从右往左进行计算
        for (int i = 2; i <= n; i++) {
            for (int j = 6 * n; j >= 1; j--) {
                dp[j] = 0;
                for (int k = 1; k <= 6; k++) {
                    if (j >= k)
                        dp[j] += dp[j - k];
                }
            }
        }

        // 计算出现概率
        int totalCounts = (int)Math.pow(6, n);
        // n个骰子的最大值为 6 * n, 最小值为n
        double[] res = new double[6 * n - n + 1];
        int idx = 0;
        for (int i = 1; i <= 6 * n; i++) {
            if (dp[i] == 0)
                continue;
            res[idx++] = dp[i] / (double)totalCounts;
        }

        return res;
    }
}
