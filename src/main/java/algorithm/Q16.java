package algorithm;

/**
 * @Author: HB
 * @Description: 面试题16 - 数值的整数次方
 *               描述: 实现函数double Power(double base, int exponent)，求base的exponent次方。
 *               不得使用库函数，同时不需要考虑大数问题。
 *               Case:
 *                   Input: "2.0 10"
 *                   Output: "100"
 *               Limit: -100.0 < x < 100.0
 *                      n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1]
 *               Remark: <Lc> 50 Pow(x, n)
 * @CreateDate: 14:41 2021/1/14
 */

public class Q16 {

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 12:20 2021/1/24
     * @Params: null
     * @Returns:
    */
    // x: base  n: exponent
    // 不需要考虑大数问题
    // 不能直接无脑递归 - 内存爆!
    // 需要分片递归
    public static double myPow(double x, int n) {

        // 1. 递归结束条件
        if (n == 1)
            return x;
        if (n == 0)
            return 1;
        // 当n为负数时, x^-n = 1/x * 1/x...
        if (n == -1)
            return 1 / x;

        // 2. 递进操作 - 分片进行递归
        double half = myPow(x, n / 2);
        double mod = myPow(x, n % 2);

        // 3. 递归返回值 - 最后将分片递归值进行合并
        return half * half * mod;

    }

    /**
     * @Author: HB
     * @Description:  快速幂解法
     * @Date: 12:21 2021/1/24
     * @Params: null
     * @Returns:
    */
    // 不需要考虑大数问题
    // 快速幂解法 - 本题的指数是int范围, 可能很大，所以需要用快速幂求解. 时间复杂度：O(logn)
    // 注意当指数是负数时，我们需要先取指数的绝对值，最后将乘积的倒数作为答案
    // 2^9, 其中9 = (1001)2 = 1*2^3 + 0*2^2 + 0*2^1 + 1*2^0
    // 所有 2^9 = 2^(1*2^3 + 0*2^2 + 0*2^1 + 1*2^0) = 2^(1*2^3) * 2^(0*2^2) * 2^(0*2^1) * 2^(1*2^0)
    // 通过上述等式可以快速求解
    public static double myPow2(double x, int n) {
        // 当n = -2147383648时, 无法转为正数
        long exponent = n;
        // 处理n为负数情况
        if (n < 0) {
            exponent = -exponent;
            x = 1/x;
        }
        double res = 1;
        // 快速幂求解
        while (exponent > 0) {
            // 只有n对应位的二进制为1时才进行处理
            if ((exponent & 1) == 1) {
                res *= x;
            }
            x *= x;
            // n 不断进行无符号右移
            exponent >>>= 1;
        }

        return res;

    }

}
