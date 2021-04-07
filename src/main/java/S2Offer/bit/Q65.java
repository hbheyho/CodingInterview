package S2Offer.bit;

/**
 * @Author: HB
 * @Description: 面试题65 - 不用加减乘除做加法
 *               描述: 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *               Case:
 *                   Input: a = 3, b = 4
 *                   Output: 7
 *               Limit: a, b 均可能是负数或 0
 *                      结果不会溢出 32 位整数
 *               Remark:
 * @CreateDate: 14:31 2021/2/26
 */

public class Q65 {
    
    /**
     * @Author: HB
     * @Description: 位运算解法
     * @Date: 14:33 2021/2/26
     * @Params: null
     * @Returns: 
    */
    // 不能使用'+','-','*','/', 那么代表只可能使用位运算进行相关操作 => 1. 使用异或来模拟向加过程, 相当于无进制位求和; 2. 使用与运算和求相加导致的进位. 
    // 1. 计算 a ^ b的异或值, 记为subRed1, 异或运算: 相同为0, 相异为1;
    // 2. 对 1 ^ 1进行特殊处理, 因为1 + 1产生了进位. 处理方法：两个数相与得到需要进位的位置, 例如 0010 & 0110 = 0010, 表示二进制的第二位产生了进位, 对与运算结果向左移动一位, 记为subRes2; 
    // 3. 将一二步的结果进行相加 
    public int add(int a, int b) {

        // sum: 保存相加计算结果; carray: 保存进位 
        int sum = 0, carray = -1;
        // 当carray 为0时退出计算
        while (carray != 0) {
            sum = a ^ b;
            carray = (a & b) << 1;
            a = sum;
            b = carray;
        }

        return sum;

    }
}
