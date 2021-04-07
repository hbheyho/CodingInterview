package S2Offer.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题25 - 合并两个有序链表
 *               描述: 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *               Case:
 *                   Input: 1->2->4, 1->3->4
 *                   Output: 1->1->2->3->4->4
 *               Limit: 0 <= 链表长度 <= 1000
 *               Remark: <Lc> 21 合并两个有序链表
 * @CreateDate: 21:41 2021/1/29
 */

public class Q25 {

    class ListNode {
        int val;
        ListNode next;
        ListNode (int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: 二路归并解法
     * @Date: 23:57 2021/1/29
     * @Params: null
     * @Returns:
    */
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1), curr = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;

        return newHead.next;
    }
}
