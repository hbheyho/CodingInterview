package linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题59I - 滑动窗口的最大值
 *               描述: 给定一个数组 nums 和滑动窗口的大小 k，
 *               请找出所有滑动窗口里的最大值。
 *               Case:
 *                   Input: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 *                   OutPut: [3,3,5,5,6,7]
 *               Limit:
 *               Remark:
 * @CreateDate: 11:11 2021/2/20
 */

public class Q59I {


    /**
     * @Author: HB
     * @Description: 滑动窗口 + 单调队列(递减)解法
     * @Date: 11:13 2021/2/20
     * @Params: null
     * @Returns:
    */
    // 重点：滑动窗口的固定大小为k; 单调队列中始终保存的值最大的下标;
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        if (n == 0)
            return new int[0];

        // 单调队列中始终依次保存的值最大的下标
        Deque<Integer> queue = new LinkedList<>();
        // 保存结果
        int idx = 0;
        int[] res = new int[n - k + 10];

        // 窗口固定大小为k
        int right = 0;
        while (right < n) {

            // 队列不为空, 并且队头元素已经滑出窗口, 则队头元素出队
            if (!queue.isEmpty() && queue.peek() < right - k + 1)
                queue.poll();

            // 若队列不为空, 并且当前元素大于队尾元素, 为了维持单调队列, 不断出队队尾元素
            while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.pollLast();
            }

            queue.offer(right);

            if (right >= k - 1)
                res[idx++] = nums[queue.peek()];

            right++;

        }

        return res;

    }

    /**
     * @Author: HB
     * @Description: 滑动窗口 + 单调队列(递减)解法(数组实现队列)
     * @Date: 11:21 2021/2/20
     * @Params: null
     * @Returns:
    */
    // 重点：滑动窗口的固定大小为k; 单调队列中始终保存的值最大的下标;
    public int[] maxSlidingWindowByArray(int[] nums, int k) {

        int n = nums.length;
        if (n == 0)
            return new int[0];

        // 单调队列中始终依次保存的值最大的下标
        int[] queue = new int[100010];
        int hh = 0, tt = -1;
        // 保存结果
        int idx = 0;
        int[] res = new int[n - k + 1];

        // 窗口固定大小为k
        int right = 0;
        while (right < n) {

            // 队列不为空, 并且队头元素已经滑出窗口, 则队头元素出队
            if (hh <= tt && queue[hh] < right - k + 1)
                hh++;

            // 若队列不为空, 并且当前元素大于队尾元素, 为了维持单调队列, 不断出队队尾元素
            while (hh <= tt && nums[right] >= nums[queue[tt]]) {
                tt--;
            }

            queue[++tt] = right;

            if (right >= k - 1)
                res[idx++] = nums[queue[hh]];

            right++;

        }

        return res;

    }
    
}
