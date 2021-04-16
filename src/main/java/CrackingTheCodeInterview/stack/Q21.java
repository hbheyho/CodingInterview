package CrackingTheCodeInterview.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题03.04 - 化栈为队
 *               描述: 实现一个MyQueue类，该类用两个栈来实现一个队列。
 *               Case:
 *               Input:  MyQueue queue = new MyQueue();
 *                       queue.push(1);
 *                       queue.push(2);
 *                       queue.peek();  // 返回 1
 *                       queue.pop();   // 返回 1
 *                       queue.empty(); // 返回 false
 *               Output:
 *               Limit:
 *               Remark:
 * @CreateDate: 10:32 2021/4/16
 */

public class Q21 {

    /**
     * @Author: HB
     * @Description: 用栈模拟队列
     * @Date: 10:33 2021/4/16
     * @Params: null
     * @Returns:
    */
    class MyQueue {

        // 两个栈实现队列
        // 实现思想：
        // 1. stack1入栈元素, 即队列入队
        // 2. stack2负责出队元素, 当stack2为空时, 将stack1中的元素移到stack1中
        // 3. stack1, stack2同时为空, 队列为空
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        /** Initialize your data structure here. */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack2.isEmpty()) {
                if (stack1.isEmpty())
                    return -1;
                while (!stack1.isEmpty())
                    stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack2.isEmpty()) {
                if (stack1.isEmpty())
                    return -1;
                while (!stack1.isEmpty())
                    stack2.push(stack1.pop());
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
