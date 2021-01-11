package algorithm;

/**
 * @Author: HB
 * @Description: 面试题14 - 剪绳子
 *               描述: 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 *               每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 *               例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *               Case:
 *               Input: n = 10
 *               Output: 36
 *               Limit: 2 <= n <= 58
 *               Remark:
 * @CreateDate: 22:20 2021/1/11
 */

public class Q14 {

    // 动态规划解法
    // 1. 状态定义: 根据题目可知, 题目最后要得到的结果是剪断绳子之后能够得到的最大乘积,
    // 则设dp[i]为把长度为i的剪成若干段绳子之后最大乘积 => 根据题目返回结果来确定状态
    // 2. 状态转移方程：对于长度为i的绳子, 对其剪一刀共有 [1, i - 1] 种剪法, 找出使其乘积最大的剪法即可,可以得到状态转移方程为 dp[i] = Max{dp[j] * dp[i - j]}, 0 < i < j
    // 3. 初始化：当n = 1, 则易知道 dp[1] = 1
    //           当n = 2, 则易知 dp[2] = 2
    //           当n = 3, 则易知 dp[3] = 3
    // 注意: 为了保证乘积最大, 初始化dp[2], dp[3] 和 根据定义所求解的dp[2], dp[3]的元素值不同
    // 4. 返回结果： dp[n]
    public static int cuttingRope(int n) {

        // 对n <= 3进行特殊处理
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;

        // 定义dp数组
        int[] dp = new int[n + 1];

        // 初始化, 要保证n = 4之后的绳子乘积最大值, 需要初始化dp[2] = 2, dp[3] = 3
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; i++) {
            // 剪每段绳子时都由 [1, i - 1]中选择, 从中找出使其乘积最大的剪法
            for (int j = 1; j <= i/2; j++) {
                dp[i] = Math.max(dp[i], (dp[j] * dp[i - j]));
            }
        }

        return dp[n];

    }

    // 贪心解法
    // 1. 当n >= 5时, 尽可能多地剪长度为3的绳子;
    // 2. 当剩下的绳子长度为4时, 把绳子剪为两段长度为2的绳子
    public static int cuttingRope2(int n) {

        // 对n <= 3进行特殊处理
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;

        // 1. 尽可能多地剪去长度为3的绳子
        int timesOf3 = n / 3;

        // 2. 当剩下的绳子长度为4时, 不能再剪去长度为3的绳子, 而是把绳子剪为两段
        if (n - timesOf3 * 3 == 1)
            timesOf3--;
        int timesOf2 = (n - timesOf3 * 3) / 2;

        return (int)(Math.pow(3, timesOf3) * Math.pow(2, timesOf2));

    }

}
