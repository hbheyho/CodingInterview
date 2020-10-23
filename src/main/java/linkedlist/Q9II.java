package linkedlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: HB
 * @Description: 面试题09 - 用两个队列实现栈
 *               描述: 用两个队列实现一个栈。栈的声明如下，请实现它的函数 push(x), pop(), top(), empty()。
 *               可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作。
 *               Case:
 *                   Input: ["MyStack","push","push","top","pop","empty"]
 *                          [[],[1],[2],[],[],[]]
 *                   Output: [null,null,null,2,2,false]
 *               Limit:
 *               Remark: <LeeCode 225> 用队列实现栈
 * @CreateDate: 15:41 2020/10/23
 */

public class Q9II {
    /*==================两个队列实现栈解法一========================*/
    static LinkedList<Integer> queue1 = null;
    static LinkedList<Integer> queue2 = null;

    /*==================两个队列实现栈解法二========================*/
    /*static Queue<Integer> queue1 = null;
    static Queue<Integer> queue2 = null;*/

    /*==================一个队列实现栈解法========================*/
    /*static LinkedList<Integer> queue = null;*/

    public Q9II() {
        /*==================两个队列实现栈解法一========================*/
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();

        /*==================两个队列实现栈解法二========================*/
        /*queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();*/

        /*==================一个队列实现栈解法========================*/
        /*queue = new LinkedList<>();*/

    }

    // 入栈操作
    public static void push(int x) {

        /*==================两个队列实现栈解法一========================*/
        // 将元素入队到不为空的队列中
        // 若全为空, 则入队到queue1
        if(!queue1.isEmpty()) {
            queue1.offer(x);
        } else if(!queue2.isEmpty()) {
            queue2.offer(x);
        } else {
            queue1.offer(x);
        }

        /*==================两个队列实现栈解法二========================*/
        /*// 将元素入队到queue2中
        queue2.offer(x);
        // 若queue1不为空, 则将queue1中的元素转存到queue2中, 保证新加入的元素在队列头部
        while(!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        // 交换queue1和queue2的引用
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;*/

        /*==================一个队列实现栈解法========================*/
        /*// 把元素添加到队列的尾部
        stack.add(x);
        // 根据栈后进先出(先进后出的特性), 需要将其他元素重新进行移动, 移到队列的尾部
        // 从1开始是因为新进来的元素不变, 对其余元素进行处理
        for(int i = 1; i < stack.size(); i++){
            stack.add(stack.remove());
        }*/

    }

   // 出栈操作
    public static int pop() {
        /*==================两个队列实现栈解法一========================*/
        if(!queue1.isEmpty()) {
            // 将queue1中的元素出队并入队到queue2中, 并删除末尾元素, 以模仿出栈
            int size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        } else if(!queue2.isEmpty()) {
            // 将queue2中的元素出队并入队到queue1中, 并删除末尾元素, 以模仿出栈
            int size = queue2.size();
            for (int i = 0; i < size - 1; i++) {
                queue1.offer(queue2.poll());
            }
            return queue2.poll();
        }
        // 栈为空
        return -1;

        /*==================两个队列实现栈解法二========================*/
        /*return queue1.poll()*/

        /*==================一个队列实现栈解法========================*/
        /*return stack.poll()*/
    }

    // 得到栈顶元素
    public static int top() {
        /*==================两个队列实现栈解法一========================*/
        if(!queue1.isEmpty()) {
            int size = queue1.size();
            return queue1.get(size - 1);
        } else if(!queue2.isEmpty()) {
            int size = queue2.size();
            return queue2.get(size - 1);
        }
        return -1;

        /*==================两个队列实现栈解法二========================*/
        /*return queue1.poll();*/

        /*==================一个队列实现栈解法========================*/
        /*return stack.poll();*/

    }

    // 判断栈是否为空
    public static boolean empty() {
        /*==================两个队列实现栈解法一========================*/
        if(!queue1.isEmpty() || !queue2.isEmpty()) {
            return false;
        }
        return true;

        /*==================两个队列实现栈解法二========================*/
        /*if(!queue1.isEmpty()) {
           return false;
        }
        return true;*/

        /*==================一个队列实现栈解法========================*/
        /*if(!stack.isEmpty()) {
           return false;
        }
        return true;*/
    }


}
