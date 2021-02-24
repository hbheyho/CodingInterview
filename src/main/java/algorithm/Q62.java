package algorithm;

import java.util.ArrayList;

/**
 * @Author: HB
 * @Description: 面试题62 - 圆圈中最后剩下的数字
 *               描述: 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字
 *               （删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *                例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
 *                则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *               Case:
 *                   Input: n = 5, m = 3
 *                   OutPut: 3
 *               Limit: 1 <= n <= 10^5
 *                      1 <= m <= 10^6
 *               Remark:
 * @CreateDate: 12:31 2021/2/24
 */

public class Q62 {

    /**
     * @Author: HB
     * @Description: 模拟解法：O(n^2)
     * @Date: 12:32 2021/2/24
     * @Params: null
     * @Returns:
    */
    // 一直绕着圆圈删除, 直到只剩下最后一个数字, 然后返回

    public int lastRemainingByForce(int n, int m) {

        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i);
        }

        int idx = 0;
        // 要只剩下1个元素, 要么就要删除n - 1个元素
        while (n > 1) {
            idx = (idx + m - 1) % n;
            // LinkedList删除结点会花费O(n)时间复杂度
            nums.remove(idx);
            n--;
        }

        return nums.get(0);

    }

    /**
     * @Author: HB
     * @Description:  约瑟夫环解法
     * @Date: 12:33 2021/2/24
     * @Params: null
     * @Returns:
    */
    // 一直绕着圆圈删除, 直到只剩下最后一个数字, 然后返回
    public int lastRemaining(int n, int m) {
        int index = 0;
        // 从只剩下两种情况下进行反推
        for (int i = 2; i <= n; i++) {
            index = (index + m) % i;
        }
        return index;
    }
}
