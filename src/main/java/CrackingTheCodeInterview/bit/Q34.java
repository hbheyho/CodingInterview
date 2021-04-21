package CrackingTheCodeInterview.bit;

/**
 * @Author: HB
 * @Description: 面试题05.01 - 插入
 *               描述:给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
 *                   编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处
 *                   用 0 补齐。
 *                   题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。
 *               Case:
 *               Input: N = 1024(10000000000), M = 19(10011), i = 2, j = 6
 *               Output: N = 1100(10001001100)
 *               Limit:
 *               Remark: 
 * @CreateDate: 14:01 2021/4/21
 */

public class Q34 {
    
    /**
     * @Author: HB
     * @Description: 位运算解法 + 循环
     * @Date: 14:07 2021/4/21
     * @Params: null
     * @Returns: 
    */
    // 1. 将N的二进制形式的i~j置为0
    // 2. 将M << i
    // 3. 将 M | N即为结果
    public int insertBits(int N, int M, int i, int j) {
        // 1. 将N的二进制形式 i ~ j置为0 - 循环置为0
        for (int k = i; k <= j; k++) {
            // 将1左移k位后取反, 之后与N进行与运算即可完成k位置上置0操作
            N = N & (~(1 << k));
        }
        // 2. 将M << i
        M <<= i;
        // 3. 返回转换结果
        return M | N;
    }


    /**
     * @Author: HB
     * @Description: 位运算解法
     * @Date: 14:08 2021/4/21
     * @Params: null
     * @Returns:
    */
    // 1. 将N的二进制形式的i~j置为0
    // 2. 将M << i
    // 3. 将 M | N即为结果
    public int insertBits2(int N, int M, int i, int j) {
        // 1. 将N的二进制形式 i ~ j置为0
        int mask = (((1 << (j - i + 1)) - 1) << i);
        mask = ~mask;
        N &= mask;
        // 2. 将M << i
        M <<= i;
        // 3. 返回转换结果
        return M | N;
    }
}
