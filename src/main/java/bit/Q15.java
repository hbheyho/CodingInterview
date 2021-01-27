package bit;

/**
 * @Author: HB
 * @Description: 面试题15 - 二进制中1的个数
 *               描述: 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
 *               例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *               Case:
 *                   Input: "00000000000000000000000000001011"
 *                   Output: "3"
 *               Limit: 输入必须是长度为 32 的 二进制串
 *               Remark: <Lc> 191 位1的个数
 * @CreateDate: 14:41 2021/1/14
 */

public class Q15 {

    /**
     * @Author: HB
     * @Description: lowbit应用
     * @Date: 21:21 2021/1/14
     * @Params: null
     * @Returns:
    */
    // 将 n看作成一个无符号整数
    // 本质是lowbit方法的应用：
    // lowbits(x)：返回x的最后一位1 => x & (~x + 1) 或 x & (-x)
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n - (n & -n);
            count++;
        }
        return count;
    }

}
