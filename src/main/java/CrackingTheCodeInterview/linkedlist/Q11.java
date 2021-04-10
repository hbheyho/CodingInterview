package CrackingTheCodeInterview.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题02.02 - 返回倒数第k个节点
 *               描述: 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *               Case:
 *               Input: 1->2->3->4->5 和 k = 2
 *               Output: 4
 *               Limit:
 *               Remark:
 * @CreateDate: 13:21 2021/4/10
 */

public class Q11 {
     /* Definition for singly-linked list.*/
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 快慢指针解法
     * @Date: 13:23 2021/4/10
     * @Params: null
     * @Returns:
    */
    public int kthToLastByFastSlow(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }

        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow.val;
    }

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 13:23 2021/4/10
     * @Params: null
     * @Returns:
    */
    public int kthToLast(ListNode head, int k) {
        // 链表长度
        int len = 0;
        ListNode curr  = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int remain = len - k;

        while (remain-- > 0) {
            head = head.next;
        }

        return head.val;
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 13:24 2021/4/10
     * @Params: null
     * @Returns:
    */
    int step;
    public int kthToLastByRecursion(ListNode head, int k) {
        step = k;
        ListNode result = recursion(head);
        return result.val;
    }

    public ListNode recursion (ListNode head) {
        // 1. 递归结束条件
        if (head == null)
            return head;

        // 2. 递进操作
        ListNode node = recursion (head.next);
        step--;
        if (step == 0)
            return head;

        // 3. 递归返回值
        return node;
    }
}
