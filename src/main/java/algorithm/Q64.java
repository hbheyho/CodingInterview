package algorithm;

/**
 * @Author: HB
 * @Description: 面试题64 - 求 1 + 2 + 3 + .. + n
 *               描述: 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case
 *               等关键字及条件判断语句（A?B:C）
 *               Case:
 *                   Input: n = 3
 *                   OutPut:6
 *               Limit: 1 <= n <= 10000
 *               Remark:
 * @CreateDate: 16:22 2021/3/26
 */

public class Q64 {
    /**
     * @Author: HB
     * @Description: 递归解法 + 逻辑短路解法(分为&&短路和||短路)
     * @Date: 16:34 2021/3/26
     * @Params: null
     * @Returns:
    */
    int sum = 0;
    public int sumNums(int n) {
        // 使用&&短路, 若 n > 1不成立则直接返回
        boolean isDone = (n > 1 && sumNums(n - 1) > 0);
        sum += n;
        return sum;
    }
}
