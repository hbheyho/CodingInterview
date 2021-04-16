package CrackingTheCodeInterview.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题03.05 - 栈排序
 *               描述: 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最
 *               多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据
 *               结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。
 *               当栈为空时，peek 返回 -1。
 *               Case:
 *               Input:  ["SortedStack", "push", "push", "peek", "pop", "peek"]
 *                        [[], [1], [2], [], [], []]
 *               Output: [null,null,null,1,null,2]
 *               Limit:
 *               Remark:
 * @CreateDate: 10:46 2021/4/16
 */

public class Q22 {

    /**
     * @Author: HB
     * @Description: 单调栈解法
     * @Date: 10:47 2021/4/16
     * @Params: null
     * @Returns:
    */
    class SortedStack {
        // 单调递增栈
        Deque<Integer> stack = new LinkedList<>();
        // 临时栈 - 临时存放单调递增栈出栈的元素
        Deque<Integer> temp = new LinkedList<>();
        public SortedStack() {

        }

        public void push(int val) {
            // 维护单调栈的单调性
            while (!stack.isEmpty() && stack.peek() < val) {
                temp.push(stack.pop());
            }
            stack.push(val);
            // 将临时栈的元素重新存回stack中
            while (!temp.isEmpty())
                stack.push(temp.pop());
        }

        public void pop() {
            if (stack.isEmpty())
                return;
            stack.pop();
        }

        public int peek() {
            if (stack.isEmpty())
                return -1;
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
}
