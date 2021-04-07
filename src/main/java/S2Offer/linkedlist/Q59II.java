package S2Offer.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题59II - 队列的最大值
 *               描述: 请定义一个队列并实现函数 max_value 得到队列里的最大值，
 *               要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *               若队列为空，pop_front 和 max_value 需要返回 -1
 *               Case:
 *                   Input: ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 *                          [[],[1],[2],[],[],[]]
 *                   OutPut: [null,null,null,2,1,2]
 *               Limit: 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 *                      1 <= value <= 10^5
 *               Remark:
 * @CreateDate: 12:49 2021/2/20
 */

public class Q59II {

    /**
     * @Author: HB
     * @Description: 单调队列解法
     * @Date: 12:51 2021/2/20
     * @Params: null
     * @Returns:
    */
    static class MaxQueue {

        // 普通队列 - 保存输入元素
        static Deque<Integer> queue;
        // 单调队列 - 单调递减队列: 保存队列中可能的最大值
        static Deque<Integer> subsequence;
        public MaxQueue() {
            queue = new LinkedList<>();
            subsequence = new LinkedList<>();
        }

        // 得到队列最大值
        public static int max_value() {
            return subsequence.isEmpty() ? -1 : subsequence.peek();
        }

        // 队尾插入元素
        public static void push_back(int value) {
            queue.offer(value);
            // 插入的元素值大于单调队列的队尾元素
            while (!subsequence.isEmpty() && value >= subsequence.peekLast()) {
                subsequence.pollLast();
            }
            subsequence.offer(value);
        }

        // 队头移除元素
        public static int pop_front() {

            if (queue.isEmpty())
                return -1;

            // 若移除的是最大值, 则subsequence也要同时进行移除
            if (!subsequence.isEmpty() && queue.peek().equals(subsequence.peek())) {
                subsequence.poll();
            }

            return queue.poll();

        }
    }


    /**
     * @Author: HB
     * @Description: 数组实现队列解法
     * @Date: 14:50 2021/2/20
     * @Params: null
     * @Returns:
    */
    class MaxQueueByArray {

        // 普通队列 - 保存输入元素
        int N = 10010;
        int[] queue = new int[N];
        int hh = 0, tt = -1;
        // 单调队列 - 单调递减队列: 保存队列中可能的最大值
        int[] subsequence = new int[N];
        int shh = 0, stt = -1;

        public MaxQueueByArray() {
        }

        // 得到队列最大值
        public int max_value() {
            return shh > stt ? -1 : subsequence[shh];
        }

        // 队尾插入元素
        public void push_back(int value) {
            queue[++tt] = value;
            // 插入的元素值大于单调队列的队尾元素, 为了维护单调队列性质, 不断出队
            while (shh <= stt && value >= subsequence[stt]) {
                stt--;
            }
            subsequence[++stt] = value;
        }

        // 队头移除元素
        public int pop_front() {

            if (hh > tt)
                return -1;

            // 若移除的是最大值, 则subsequence也要同时进行移除
            while (shh <= stt && queue[hh] == subsequence[shh]) {
                shh++;
            }

            return queue[hh++];

        }
    }

}
