package S2Offer.stack;

import java.util.Stack;

/**
 * @Author: HB
 * @Description: 面试题09 - 用两个栈实现队列
 *               描述: 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead,
 *               分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *               Case:
 *                   Input: ["CQueue","appendTail","deleteHead","deleteHead"]
 *                          [[],[3],[],[]]
 *                   Output: [null,null,3,-1]
 *               Limit: 1 <= values <= 10000, 最多会进行10000次调用
 * @CreateDate: 14:41 2020/10/23
 */

public class Q9 {

    // 算法思想：
    // 两个栈模拟队列 => 栈: 先进后出; 队列: 先进先出
    // 1. appendTail(x): 将元素x插入到队尾 => 把元素都插入到栈1中, 以完成进队
    // 2. deleteHead(): 将队首的元素弹出，并返回该元素 => 若栈2为空, 则将栈1中的元素弹出并存储在栈2中, 并弹出栈顶元素以完成出队
    // 综上: 栈1只管进队元素; 栈2只管出队元素

    // 栈1：存放入队元素
    private static Stack<Integer> stack1;
    // 栈2：存放出队元素
    private static Stack<Integer> stack2;
    public Q9() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // 往队列中添加元素
    public static void appendTail(int value) {
        // 队列入队, 则直接将元素放入到栈1中
        stack1.push(value);
    }

    // 从队列中删除元素
    public static int deleteHead() {
        // 若栈2不为空, 则直接出队
        if(!stack2.isEmpty()) {
            return stack2.pop();
            // 将栈1元素赋值到栈2, 然后出栈
        } else if(!stack1.isEmpty()) {
            int stackSize = stack1.size();
            for(int i = 0; i < stackSize; i++) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
        return -1;
    }

}
