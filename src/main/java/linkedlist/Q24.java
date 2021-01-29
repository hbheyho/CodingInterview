package linkedlist;

import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题24 - 反转链表
 *               描述: 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *               Case:
 *                   Input: 1->2->3->4->5->NULL
 *                   Output: 5->4->3->2->1->NULL
 *               Limit: 0 <= 节点个数 <= 5000
 *               Remark: <Lc> 206 反转链表
 * @CreateDate: 21:41 2021/1/29
 */

public class Q24 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode (int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 23:39 2021/1/29
     * @Params: null
     * @Returns:
    */
    public static ListNode reverseListByRecursion(ListNode head) {

        // 1. 递归结束条件
        if (head == null || head.next == null)
            return head;

        // 2. 递进操作
        ListNode root = reverseListByRecursion (head.next);
        ListNode node = head.next;
        head.next = node.next;
        node.next = head;

        // 3. 递归返回值
        return root;

    }


    /**
     * @Author: HB
     * @Description: 迭代解法
     * @Date: 23:41 2021/1/29
     * @Params: null
     * @Returns:
    */
    public static ListNode reverseListByIteration (ListNode head) {

        ListNode pre = null, curr = head;

        while (curr != null) {
            // 保存curr的下一个节点
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        return pre;

    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        reverseListByIteration(root);
    }


}

