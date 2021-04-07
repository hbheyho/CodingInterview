package S2Offer.linkedlist;

import java.util.*;

/**
 * @Author: HB
 * @Description: 面试题05 - 从尾到头打印链表
 *               描述: 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *               Case:
 *                   Input: head = [1,3,2]
 *                   Output: [2,3,1]
 *               Limit: 0 <= length <= 10000
 * @CreateDate: 14:41 2020/10/17
 */

public class Q6 {
    // Define LinkedList
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: 栈解法
     * @Date: 14:43 2020/10/17
     * @Params: null
     * @Returns:
    */
    public int[] reversePrintByStack (ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        // 利用栈先进后出的特性, 对结果进行出栈
        int stackSize = stack.size();
        int[] res = new int[stackSize];
        for (int i = 0; i < stackSize; i++) {
            res[i] = stack.pop();
        }
        return res;
    }


    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 14:44 2020/10/17
     * @Params: null
     * @Returns:
    */
    static List<Integer> res = new ArrayList<>();
    public static int[] reversePrintByRecursion(ListNode head) {
        ListNode cur = head;
        recursion(cur);
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private static void recursion (ListNode cur) {
        // 1. 递归返回值
        if (cur == null)
            return;
        // 2. 递进操作
        recursion(cur.next);
        res.add(cur.val);
        // 3. 递归返回值 - 暂无
    }

    /**
     * @Author: HB
     * @Description:  List 解法
     * @Date: 14:45 2020/10/17
     * @Params: null
     * @Returns:
    */
    public static int[] reversePrintByList(ListNode head) {
        LinkedList<Integer> res = new LinkedList<>();
        while (head != null) {
            res.addFirst(head.val);
            head = head.next;
        }

        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * @Author: HB
     * @Description: 朴素解法
     * @Date: 15:37 2020/10/17
     * @Params: null
     * @Returns:
    */
    public static int[] reversePrintBySimple(ListNode head) {
        // 计算链表节点个数
        int count = 0;
        ListNode node = head;
        while (node != null) {
            count++;
            node = node.next;
        }

        // 知道了节点数构造结果数组
        int[] res = new int[count];
        node = head;
        // 倒序填充结果数组
        for (int i = count - 1; i >= 0; i--) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }

}
