package CrackingTheCodeInterview.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题02.07 - 链表相交
 *               描述: 给定两个（单向）链表，判定它们是否相交并返回交点。
 *                     请注意相交的定义基于节点的引用，而不是基于节点的值。
 *                     换句话说，如果一个链表的第k个节点与另一个链表的第j个节点
 *                     是同一节点（引用完全相同），则这两个链表相交。
 *               Case:
 *               Input:  intersectVal = 8, listA = [4,1,8,4,5],
 *                       listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 *               Output: 8
 *               Limit: 如果两个链表没有交点，返回 null 。
 *                      在返回结果后，两个链表仍须保持原有的结构。
 *                      可假定整个链表结构中没有循环。
 *                      程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *               Remark: <LC 160> 相交链表
 * @CreateDate: 10:10 2021/4/14
 */

public class Q16 {

    public class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 10:14 2021/4/14
     * @Params: null
     * @Returns:
    */
    // 算法思路：例如链表A长度为lenA, 链表B长度为lenB,
    // A, B的相交节点之后的节点个数相同, 即为commonLen,
    // 不相同的之后相交节点, 分别记为remainLenA, remainLenB,
    // A, B都分别行走LenA + remainLenB, LenB + remianLenA节点步数, 最后两个节点会在相交节点相遇
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode currA = headA, currB = headB;
        while (currA != currB) {
            currA = currA == null ? headB : currA.next;
            currB = currB == null ? headA : currB.next;
        }
        return currA;
    }
}
