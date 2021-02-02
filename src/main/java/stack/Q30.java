package stack;

/**
 * @Author: HB
 * @Description: 面试题30 - 包含min函数的栈
 *               描述: 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 *               调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *               Case:
 *                   Input: ["MinStack","push","push","push","min","pop","min"]
 *                          [[],[0],[1],[0],[],[],[]]
 *                   Output: [null,null,null,null,0,null,0]
 *               Limit: <LC> 155 最小栈
 * @CreateDate: 19:12 2020/01/31
 */

public class Q30 {

    /**
     * @Author: HB
     * @Description: 双栈解法
     * @Date: 22:09 2021/1/31
     * @Params: null
     * @Returns:
    */
    class MinStackByDoubleStack {

        /** initialize your data structure here. */
        int N = 20010;
        // 正常存储所有元素
        int[] stack1 = new int[N];
        int top1 = -1;
        // 存储栈中最小元素和以前的最小元素 - 单调栈(单调递增栈)
        int[] stack2 = new int[N];
        int top2 = -1;
        public MinStackByDoubleStack() {

        }

        public void push(int x) {
            stack1[++top1] = x;
            // 存储最小元素
            if (top2 == -1 || stack2[top2] >= x) {
                stack2[++top2] = x;
            }
        }

        public void pop() {

            int value = stack1[top1--];
            // 判断移除的是否是最小元素, 若是最小元素, 则也需要移除
            if (stack2[top2] == value)
                top2--;

        }

        public int top() {
            return stack1[top1];
        }

        public int min() {
            return stack2[top2];
        }
    }


    /**
     * @Author: HB
     * @Description: 单栈解法 - 会存在数据冗余
     * @Date: 22:49 2021/1/31
     * @Params: null
     * @Returns: 
    */
    class MinStackBySingleStack {

        /** initialize your data structure here. */
        int N = 20010;
        // 正常存储所有元素
        int[] stack = new int[N];
        int top = -1;
        // 存储最小值
        int minValue = Integer.MAX_VALUE;
        public MinStackBySingleStack (){
        }

        public void push(int x) {
            // 找到一个新的最小值时, 更新minValue, 并将以前的最小值重新入栈
            if (x <= minValue) {
                stack[++top] = minValue;
                minValue = x;
            }
            stack[++top] = x;
        }

        public void pop() {
            // 在移除时, 如果移除的是最小值minValue, 则需要重新找到上一次最小值
            // 需要进行两次移除, 1. 移除最小值; 2. 移除上一次最小值并保存
            if (stack[top] == minValue) {
                top--;
                // 保存上一次的最小值
                minValue = stack[top--];
            } else {
                top--;
            }
        }

        public int top() {
            return stack[top];
        }

        public int min() {
            return minValue;
        }
    }


}
