package CrackingTheCodeInterview.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: HB
 * @Description: 面试题02.01 - 移除重复节点
 *               描述: 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *               Case:
 *               Input: [1, 2, 3, 3, 2, 1]
 *               Output: [1,2,3]
 *               Limit: 链表长度在[0, 20000]范围内
 *                      链表元素在[0, 20000]范围内
 *               Remark:
 * @CreateDate: 12:42 2021/4/10
 */

public class Q10 {

    class ListNode {
        int val;
        ListNode next;
        ListNode (int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: Hash表解法
     * @Date: 12:44 2021/4/10
     * @Params: null
     * @Returns:
    */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null)
            return head;
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode curr = head.next, pre = head;
        while (curr != null) {
            if (set.contains(curr.val)) {
                pre.next = curr.next;
            } else {
                set.add(curr.val);
                pre = pre.next;
            }
            curr = pre.next;
        }
        return head;
    }


    /**
     * @Author: HB
     * @Description: 双重循环解法 - 暴力解法
     * @Date: 13:06 2021/4/10
     * @Params: null
     * @Returns:
    */
    public ListNode removeDuplicateNodesByForce(ListNode head) {
        if (head == null)
            return head;
        ListNode curr = head;
        while (curr != null) {
            ListNode currNext = curr.next, pre = curr;
            while (currNext != null) {
                if (currNext.val != curr.val) {
                    pre = currNext;
                    currNext = currNext.next;
                } else {
                    pre.next = currNext.next;
                    currNext = pre.next;
                }
            }
            curr = curr.next;
        }
        return head;
    }


}
