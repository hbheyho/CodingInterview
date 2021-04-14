package CrackingTheCodeInterview.stack;

/**
 * @Author: HB
 * @Description: 面试题03.01 - 三合一
 *               描述: 三合一。描述如何只用一个数组来实现三个栈。
 *                     你应该实现push(stackNum, value)、pop(stackNum)、
 *                     isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈
 *                     下标，value表示压入的值。
 *                     构造函数会传入一个stackSize参数，代表每个栈的大小
 *               Case:
 *               Input:  ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 *                       [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *               Output: [null, null, null, 1, -1, -1, true]
 *               Limit:
 *               Remark:
 * @CreateDate: 14:57 2021/4/14
 */

public class Q18 {

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 14:57 2021/4/14
     * @Params: null
     * @Returns:
    */
    class TripleInOne {
        // 一个数组实现三个栈
        int[] stack;
        int top1, top2, top3;
        int size;
        public TripleInOne(int stackSize) {
            stack = new int[stackSize * 3 + 2];
            // 初始化三个栈的栈头
            top1 = -1;
            top2 = stackSize;
            top3 = 2 * stackSize + 1;
            size = stackSize;
        }

        public void push(int stackNum, int value) {
            switch(stackNum) {
                case 0 :
                    // 栈满了, 直接返回, 否则入栈
                    if (top1 + 1 >= size)
                        return;
                    stack[++top1] = value;
                    break;
                case 1 :
                    // 栈满了, 直接返回, 否则入栈
                    if (top2 >= 2 * size)
                        return;
                    stack[++top2] = value;
                    break;
                case 2 :
                    // 栈满了, 直接返回, 否则入栈
                    if (top3 >= 3 * size + 1)
                        return;
                    stack[++top3] = value;
                    break;
            }
        }

        public int pop(int stackNum) {
            int result = 0;
            switch(stackNum) {
                case 0 :
                    // 栈为空, 返回-1
                    if (top1 == -1)
                        return -1;
                    result = stack[top1];
                    top1--;
                    break;
                case 1 :
                    // 栈为空, 返回-1
                    if (top2 == size)
                        return -1;
                    result = stack[top2];
                    top2--;
                    break;
                case 2 :
                    // 栈为空, 返回-1
                    if (top3 == size * 2 + 1)
                        return -1;
                    result = stack[top3];
                    top3--;
                    break;
            }
            return result;
        }

        public int peek(int stackNum) {
            int result = 0;
            switch(stackNum) {
                case 0 :
                    if (top1 == -1)
                        return -1;
                    result = stack[top1];
                    break;
                case 1 :
                    if (top2 == size)
                        return -1;
                    result = stack[top2];
                    break;
                case 2 :
                    if (top3 ==  2 * size + 1)
                        return -1;
                    result = stack[top3];
                    break;
            }
            return result;
        }

        public boolean isEmpty(int stackNum) {
            boolean isEmpty = false;
            switch(stackNum) {
                case 0 :
                    isEmpty = top1 == -1 ? true : false;
                    break;
                case 1 :
                    isEmpty = top2 == size ? true : false;
                    break;
                case 2 :
                    isEmpty = top3 == 2 * size + 1 ? true : false;
                    break;
            }
            return isEmpty;
        }
    }

    /**
     * @Author: HB
     * @Description: 优化解法
     * @Date: 14:59 2021/4/14
     * @Params: null
     * @Returns:
    */
    class TripleInOne2 {
        // 一个数组实现三个栈 - 不同于一个栈的数据都连续存储在一起, 为了代码简便性, 可以不同栈数据分段存储
        int[] stack;
        int[] top;
        public TripleInOne2(int stackSize) {
            stack = new int[stackSize * 3];
            // 初始化三个栈的栈头
            top = new int[]{0, 1, 2};
        }

        public void push(int stackNum, int value) {
            if (top[stackNum] < stack.length) {
                // 为栈赋值
                stack[top[stackNum]] = value;
                // 更新栈的指针
                top[stackNum] = top[stackNum] + 3;
            }
        }

        public int pop(int stackNum) {
            if (isEmpty(stackNum))
                return -1;
            // 更新栈的指针
            top[stackNum] = top[stackNum] - 3;
            return stack[top[stackNum]];
        }

        public int peek(int stackNum) {
            if (isEmpty(stackNum))
                return -1;
            return stack[top[stackNum] - 3];
        }

        public boolean isEmpty(int stackNum) {
            if (top[stackNum] - 3 < 0)
                return true;
            return false;
        }
    }
}
