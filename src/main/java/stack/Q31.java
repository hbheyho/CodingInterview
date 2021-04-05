package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题31 - 栈的压入和弹出序列
 *               描述: 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个
 *               序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，
 *               序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列
 *               对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *               Case:
 *                   Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 *                   OutPut: true
 *               Limit: 0 <= pushed.length == popped.length <= 1000
 *                      0 <= pushed[i], popped[i] < 1000
 *                      pushed 是 popped 的排列。
 *               Remark: <LC 946> 验证栈序列
 * @CreateDate: 12:50 2021/4/5
 */

public class Q31 {

    /**
     * @Author: HB
     * @Description: 栈解法
     * @Date: 12:52 2021/4/5
     * @Params: null
     * @Returns:
    */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 辅助栈
        Deque<Integer> stack = new LinkedList<>();
        // 指针j指向popped数组
        int j = 0, len = pushed.length;

        for (int element : pushed) {
            // 入栈数组中的元素依次入栈
            stack.push(element);
            // 若出栈数组中的元素与辅助栈的栈顶元素相同, 则辅助栈不断出栈, 并 j++;
            while (j < len && !stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == len;
    }
}
