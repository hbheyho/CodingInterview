package algorithm;

/**
 * @Author: HB
 * @Description: 面试题49 - 丑数
 *               描述: 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
 *               求按从小到大的顺序的第 n 个丑数。
 *               Case:
 *                   Input:  n = 10
 *                   OutPut: 12。1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *               Limit: 1是丑数; n 不超过1690
 *               Remark: <LC 264> 丑数II
 * @CreateDate: 23:04 2021/2/13
 */

public class Q49 {

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 23:06 2021/2/13
     * @Params: null
     * @Returns:
    */
    // 首先根据丑数的定义：丑数应该是另一个丑数乘以2,3或5的结果. 例如 10 = 5 * 2.
    // 算法思路:
    // 设已知长度为 n 的丑数序列 x1, x2,.., xn, 求第 n+1 个丑数xn+1。根据递推性质，丑数xn+1 只可能是以下三种情况其中之一（索引 a, b, ca,b,c 为未知数）：
    //      xn+1 = xa * 2, a属于[1, a]
    //      xn+1 = xb * 3, b属于[1, b]
    //      xn+1 = xc * 5, c属于[1, c]
    // 又因为 xn+1 是最接近 xn 的丑数, 所以a, b, c需要满足以下条件
    //      xa ​× 2 > xn ​≥ xa−1 ​× 2​，即xa​为首个乘以2后大于xn​的丑数
    //      xb ​× 3 > xn ​≥ xb−1 ​× 3，即xb​为首个乘以3后大于xn​的丑数
    //      xc ​× 5 > xn ​≥ xc−1 ​× 5，即xc​为首个乘以5后大于xn​的丑数​
    // 若索引 a,b,c 满足以上条件，则可使用递推公式计算下个丑数 xn+1 其为三种情况中的最小值，即 xn+1 = min(xa × 2, xb × 3, xc × 5). 因此，可设置指针 a,b,c 指向首个丑数（即 1 ），循环根据递推公式得到下个丑数，并每轮将对应指针执行 +1 即可。
    // 1. 状态定义：设置状态dp[i]：代表第 i + 1个丑数
    // 2. 状态转移方程：当索引 a, b, c 满足以下条件时，dp[i] 为三种情况的最小值.
    //      xa ​× 2 > xn ​≥ xa−1 ​× 2​，即xa​为首个乘以2后大于xn​的丑数
    //      xb ​× 3 > xn ​≥ xb−1 ​× 3，即xb​为首个乘以3后大于xn​的丑数
    //      xc ​× 5 > xn ​≥ xc−1 ​× 5，即xc​为首个乘以5后大于xn​的丑数​
    //              xn+1 = min(xa × 2, xb × 3, xc × 5)
    // 3. 初始化：dp[0] = 1
    // 4. 返回结果: dp[n - 1]
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        // 初始化
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2;
            int n3 = dp[b] * 3;
            int n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            // 每轮计算dp[i] 后，需要更新索引 a, b, c 的值，使其始终满足方程条件
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }

        return dp[n - 1];
    }

}
