package linkedlist;

/**
 * @Author: HB
 * @Description: 面试题22 - 链表中倒数第k个节点
 *               描述: 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，
 *               本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *               例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。
 *               这个链表的倒数第 3 个节点是值为 4 的节点
 *               Case:
 *                   Input: 1->2->3->4->5->NULL k = 2
 *                   Output: 4 -> 5 -> NULL
 *               Limit:
 *               Remark:
 * @CreateDate: 10:38 2021/4/5
 */

public class Q22 {

    /**
     * Definition for singly-linked list.
    */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 朴素解法
     * @Date: 10:40 2021/4/5
     * @Params: null
     * @Returns:
    */
    public ListNode getKthFromEndByNormal(ListNode head, int k) {

        if (head == null)
            return head;

        // 链表长度
        int len = 0;

        // 倒数第k个节点, 可通过正数第 n - k 个节点求出
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int re = len - k;
        while (re > 0) {
            head = head.next;
            re--;
        }

        return head;

    }


    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 10:41 2021/4/5
     * @Params: null
     * @Returns:
    */
    // 定义一个全局变量记录走过的节点个数
    int size = 0;
    public ListNode getKthFromEndByRecursion(ListNode head, int k) {

        if (head == null)
            return head;

        ListNode node = getKthFromEndByRecursion(head.next, k);
        size++;
        if (size == k)
            return head;
        else
            return node;

    }

    /**
     * @Author: HB
     * @Description: 快慢指针解法
     * @Date: 10:41 2021/4/5
     * @Params: null
     * @Returns:
    */
    public ListNode getKthFromEndBySlowFast(ListNode head, int k) {

        if (head == null)
            return head;

        ListNode slow = head, fast = head;
        // fast先走k步, 使得slow 和 fast相差k个节点
        while (k > 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }
}
