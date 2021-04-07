package S2Offer.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题10II - 青蛙跳台阶问题
 *               描述: 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *               答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *               Case:
 *               Input: 5
 *               Output: 8
 *               Limit: 0 <= n <= 100
 *               Remark: <LeeCode 70> 爬楼梯
 * @CreateDate: 22:20 2020/11/02
 */
public class Q10II {

    /**
     * @Author: HB
     * @Description: 递归解法 - 当n过大时超时
     * @Date: 15:18 2020/11/02
     * @Params: null
     * @Returns:
    */
    public static int numWaysByRecursion(int n) {

        // 1. 递归结束条件
        if (n < 2)
            return 1;

        // 2. 递归操作
        return numWaysByRecursion(n - 1) + numWaysByRecursion(n - 2);

    }


    /**
     * @Author: HB
     * @Description: 记忆化递归 - 备忘录算法
     * @Date: 15:24 2020/11/02
     * @Params: null
     * @Returns:
    */
    static Map<Integer, Integer> map = new HashMap<>();
    public static int numWaysByRecursion2(int n) {

        // 1. 递归结束条件
        if (n < 2)
            return 1;

        // 2. 递归操作
        // 当前结果已经计算过一次, 直接返回
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int value = (numWaysByRecursion2(n - 1) + numWaysByRecursion2(n - 2)) % 1000000007;
        map.put(n, value);
        return value;

    }


    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 15:25 2020/10/27
     * @Params: null
     * @Returns:
    */
    public static int numWaysDP(int n) {

        if (n < 2)
            return 1;
        int[] dp = new int[n + 1];

        // 初始化
        dp[0] = 1;
        dp[1] = 1;

        // 遍历台阶,得到每个台阶的总方法
        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        // 输出结果
        return dp[n];
    }


    /**
     * @Author: HB
     * @Description: 动态规划解法 - 状态压缩
     * @Date: 15:52 2020/11/2
     * @Params: null
     * @Returns:
    */
    public int numWaysByDP2(int n) {

        if (n < 2)
            return 1;

        // 初始化台阶为0和1的情况
        int a = 1;
        int b = 1;

        // 遍历台阶,得到每个台阶的总方法
        for(int i = 2; i <= n; i++) {
            int temp = (a + b) % 1000000007;
            a = b;
            b = temp;
        }
        // 输出结果
        return b;
    }

}
