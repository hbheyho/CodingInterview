package CrackingTheCodeInterview.stack;

/**
 * @Author: HB
 * @Description: 面试题03.02 - 栈的最小值
 *               描述: 请设计一个栈，除了常规栈支持的pop与push函数以外，
 *               还支持min函数，该函数返回栈元素中的最小值。执行push、pop
 *               和min操作的时间复杂度必须为O(1)。
 *               Case:
 *               Input:  ["MinStack","push","push","push","push","getMin","pop","getMin","pop","getMin","pop","getMin"]
 *                       [[],[2],[0],[3],[0],[],[],[],[],[],[],[]]
 *               Output: [null,null,null,null,null,0,null,0,null,0,null,2]
 *               Limit:
 *               Remark:
 * @CreateDate: 15:47 2021/4/14
 */

public class Q19 {

    /**
     * @Author: HB
     * @Description: 双栈解法
     * @Date: 15:48 2021/4/14
     * @Params: null
     * @Returns:
    */
    class MinStack {

        // 双栈解法
        // 1. 栈1正常入栈和出栈元素
        // 2. 栈1用来存在最小元素 - 单调递增栈
        //  1) 若当前元素x小于等于stack2[top2], 则当前x入栈
        //  2) 若当前出栈的元素是最小元素, stack2[top2]也要出栈
        int N = 10010;
        int[] stack1 = new int[N];
        int top1 = -1;
        // 单调递增栈
        int[] stack2 = new int[N];
        int top2 = -1;
        public MinStack() {

        }

        public void push(int x) {
            stack1[++top1] = x;
            // 只有当前元素x小于stack2[top2]时, 才入stack2
            if (top2 == -1 || stack2[top2] >= x) {
                stack2[++top2] = x;
            }
        }

        public void pop() {
            if (top1 == -1)
                return;
            // 若要弹出的数是最小数, 则stack2也要弹出
            if (stack1[top1] == stack2[top2]) {
                top2--;
            }
            top1--;
        }

        public int top() {
            if (top1 == -1)
                return -1;
            return stack1[top1];
        }

        public int getMin() {
            if (top2 == -1)
                return -1;
            return stack2[top2];
        }
    }

    /**
     * @Author: HB
     * @Description: 单栈解法
     * @Date: 15:49 2021/4/14
     * @Params: null
     * @Returns:
    */
    class MinStack2 {

        // 单栈解法
        // 思想：冗余解法, 当遇到更小的值时, 将次小值保存栈中
        int N = 10010;
        int[] stack = new int[N];
        int top = -1;
        // 保存最小值
        int minValue = Integer.MAX_VALUE;
        public MinStack2() {

        }

        public void push(int x) {
            // 当遇到更小的值时, 将次小值保存在栈中, 并更新minValue
            if (x <= minValue) {
                stack[++top] = minValue;
                minValue = x;
            }
            stack[++top] = x;
        }

        public void pop() {
            // 若要出栈的值为最小值, 那么会连续出栈两次, 并更新最小值
            if (stack[top--] == minValue) {
                minValue = stack[top--];
            }
        }

        public int top() {
            return stack[top];
        }

        public int getMin() {
            return minValue;
        }
    }
}
