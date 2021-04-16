package CrackingTheCodeInterview.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题03.03 - 堆盘子
 *               描述: 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，
 *                     盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，
 *                     模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
 *                     此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，
 *                     pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，
 *                     根据指定的子栈，执行pop操作。
 *                     当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 *               Case:
 *               Input: ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 *                      [[1], [1], [2], [1], [], []]
 *               Output: [null, null, null, 2, 1, -1]
 *               Limit:
 *               Remark:
 * @CreateDate: 10:13 2021/4/16
 */

public class Q20 {

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 10:15 2021/4/16
     * @Params: null
     * @Returns:
    */
    class StackOfPlates {
        // 声明一堆盘子
        LinkedList<Deque<Integer>> setOfPlates = new LinkedList<>();
        // 每个盘子容量
        int capacity;
        public StackOfPlates(int cap) {
            capacity = cap;
        }

        public void push(int val) {
            // 盘子容量没有, 则不能存
            if (capacity <= 0) {
                return;
            }
            // 一共有几堆盘子
            int size = setOfPlates.size();
            // 暂时还没盘子 或 最后一个盘子装满了, 新建一个盘子来存储
            // 否则存储在最后一个盘子上
            if (size == 0 || setOfPlates.get(size - 1).size() == capacity) {
                Deque<Integer> stack = new LinkedList<>();
                stack.push(val);
                setOfPlates.add(stack);
            } else {
                setOfPlates.get(size - 1).push(val);
            }
        }

        public int pop() {
            // 一共有几堆盘子
            int size = setOfPlates.size();
            // 没有盘子, 直接返回-1
            if (size == 0) {
                return -1;
            }
            // 从最后一个盘子取
            int result = setOfPlates.get(size - 1).pop();
            // 弹出之后, 最后一个盘子盘子为空, 则直接移除盘子
            if ( setOfPlates.get(size - 1).size() == 0) {
                setOfPlates.remove(size - 1);
            }
            return result;
        }

        public int popAt(int index) {
            int size = setOfPlates.size();
            // 盘子索引越界
            if (index >= size)
                return -1;
            int result = setOfPlates.get(index).pop();
            // 弹出之后, 盘子为空了, 则直接移除盘子
            if ( setOfPlates.get(index).size() == 0) {
                setOfPlates.remove(index);
            }
            return result;
        }
    }

}
