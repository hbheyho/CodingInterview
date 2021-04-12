package CrackingTheCodeInterview.linkedlist;

import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题02.04 - 分割链表
 *               描述: 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在
 *               大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x
 *               的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要
 *               被置于左右两部分之间。
 *               Case:
 *               Input:  head = 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1, x = 5
 *               Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 *               Limit:
 *               Remark:
 * @CreateDate: 19:40 2021/4/12
 */

public class Q13 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode (int val) {
            this.val = val;
        }
    }
    
    
    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 20:03 2021/4/12
     * @Params: null
     * @Returns: 
    */
    // 小于x的节点放在左半部分, 大于或等于x的节点放在右半部分, 对于x节点只需要保证在右半部分即可
    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return head;
        // 创建一个新链表来保存结果
        ListNode newHead = new ListNode(head.val), newTail = newHead, curr = head.next;
        head.next = null;
        while (curr != null) {
            // 断开节点连接
            ListNode temp = curr.next;
            curr.next = null;
            // 大于或等于x的节点放在右半部分 - 尾插法
            if (curr.val >= x) {
                newTail.next = curr;
                newTail = newTail.next;
                // 小于x的节点放在左半部分 - 头插法
            } else {
                curr.next = newHead;
                newHead = curr;
            }
            curr = temp;
        }
        return newHead;
    }


    /**
     * @Author: HB
     * @Description: 双指针解法
     * @Date: 20:02 2021/4/12
     * @Params: null
     * @Returns: 
    */
    // 小于x的节点放在左半部分, 大于或等于x的节点放在右半部分, 对于x节点只需要保证在右半部分即可
    // 双指针解法
    public static ListNode partitionByTwoPoints(ListNode head, int x) {
        if (head == null)
            return head;
        ListNode p = head, q = head.next, pre = p;
        while (q != null) {
            // 若q.val小于x, 则将其提到前面
            if (q.val < x) {
                pre.next = q.next;
                q.next = p;
                p = q;
                q = pre.next;
            } else {
                pre = q;
                q = q.next;
            }

        }
        return p;
    }
}
