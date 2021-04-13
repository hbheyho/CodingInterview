package CrackingTheCodeInterview.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题02.06 - 回文链表
 *               描述: 编写一个函数，检查输入的链表是否是回文的。
 *               Case:
 *               Input:  1->2->2->1
 *               Output: true
 *               Limit:
 *               Remark:
 * @CreateDate: 10:28 2021/4/13
 */

public class Q15 {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode (int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: 快慢指针 + 链表翻转解法
     * @Date: 11:10 2021/4/13
     * @Params: null
     * @Returns:
    */
    // 通过快慢指针找到中间节点
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;

        ListNode slow = head, fast = head, mid = null;
        // fast != null: 针对偶数个节点的结束；fast.next != null: 针对奇数个节点的 结束
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        mid = slow;
        // 反转链表[head, mid)
        ListNode curr = head, pre = null;
        while (curr != mid) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        // 从中间节点开始进行比较是否为回文链表
        ListNode left = pre, right = mid;
        // 对奇数个节点特殊处理
        if (fast != null)
            right = mid.next;

        while (left != null && right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }

    /**
     * @Author: HB
     * @Description: 快慢指针 + 链表翻转解法优化
     * @Date: 11:18 2021/4/13
     * @Params: null
     * @Returns:
    */
    // 通过快慢指针找到中间节点
    public boolean isPalindrome2(ListNode head) {
        if (head == null)
            return true;

        ListNode slow = head, fast = head, mid = null;
        // 从中间节点开始进行比较是否为回文链表
        // 在快慢指针的过程中进行翻转
        ListNode right = head, left = null;
        // fast != null: 针对偶数个节点的结束；fast.next != null: 针对奇数个节点的 结束
        while (fast != null && fast.next != null) {
            right = slow;
            slow = slow.next;
            fast = fast.next.next;
            right.next = left;
            left = right;
        }

        // 对奇数个节点特殊处理
        if (fast != null)
            slow = slow.next;

        while (slow != null && right != null) {
            if (slow.val != right.val)
                return false;
            slow = slow.next;
            right = right.next;
        }

        return true;
    }

}
