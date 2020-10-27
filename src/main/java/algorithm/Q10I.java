package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题03 - 求斐波那契数列的第n项
 *               描述: 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *                    F(0) = 0,   F(1) = 1
 *                    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *               斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *               答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *               Case:
 *               Input: 5
 *               Output: 5
 *               Limit: 0 <= n <= 100
 *               Remark: <LeeCode 509> 斐波那契数列
 * @CreateDate: 22:20 2020/10/27
 */
public class Q10I {

    /**
     * @Author: HB
     * @Description: 递归解法 - 当n过大时超时
     * @Date: 15:18 2020/10/27
     * @Params: null
     * @Returns:
    */
    public static int fibByRecursion(int n) {
        int result = 0;
        result = compute(n);
        return result;
    }

    public static int compute(int n) {
        // 1. 递归结束条件
        if(n == 0){
            return 0;
        } else if (n == 1){
            return 1;
        }

        // 2. 递进操作 以及返回递归结果
        return (compute(n - 1) + compute(n - 2))  % 1000000007 ;
    }


    /**
     * @Author: HB
     * @Description: 记忆化递归
     * @Date: 15:24 2020/10/27
     * @Params: null
     * @Returns:
    */
    static Map<Integer, Integer> map;
    public static int fibByRecursion2(int n) {
        map = new HashMap<>();
        int result = 0;
        result = compute2(n);
        return result;
    }

    public static int compute2(int n) {
        // 1. 递归结束条件
        if(n == 0){
            return 0;
        } else if (n == 1){
            return 1;
        }

        // 2. 递进操作 以及返回递归结果
        if(map.get(n) != null) {
            return map.get(n);
        }

        int subResult = (compute(n - 1) + compute(n - 2)) % 1000000007;
        map.put(n, subResult);
        return  subResult;

    }


    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 15:25 2020/10/27
     * @Params: null
     * @Returns:
    */
    public static int fibByDP(int n) {
        int a = 0, b = 1;
        if (n == 0)
            return a;
        if (n == 1)
            return b;

        int result = 0;
        for (int i = 2; i <= n; i++) {
            // F(N) = F(N - 1) + F(N - 2) => 状态转移方程,  需要对结果进行求模运算, 当n = 44, int 会溢出
            result = (a + b) % 1000000007;
            // F(N - 2) = F(N - 1)
            a = b;
            // F(N - 1) = F(N)
            b = result;
        }
        return result;
    }
}
