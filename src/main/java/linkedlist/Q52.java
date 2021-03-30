package linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: HB
 * @Description: 面试题52 - 两个链表的第一个公共节点
 *               描述: 输入两个链表，找出它们的第一个公共节点。
 *               Case:
 *                   Input: intersectVal = 8, listA = [4,1,8,4,5],
 *                          listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 *                   Output: Reference of the node with value = 8
 *               Limit: 如果两个链表没有交点，返回 null.
 *                      在返回结果后，两个链表仍须保持原有的结构。
 *                      可假定整个链表结构中没有循环。
 *                      程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存
 *               Remark: <Lc> 160 相交节点
 *                       上述输入输出参考LeetCode
 * @CreateDate: 23:42 2021/3/30
 */

public class Q52 {

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * @Author: HB
     * @Description: Hash表解法
     * @Date: 23:46 2021/3/30
     * @Params: null
     * @Returns:
    */
    public ListNode getIntersectionNodeByHashMapByHashMap(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 23:54 2021/3/30
     * @Params: null
     * @Returns:
    */
    // 算法思路： 让长的先走, 然后再一起往前走
    public ListNode getIntersectionNodeByNormal(ListNode headA, ListNode headB) {
        // A链表节点数量
        int countA = 0;
        // B链表节点数量
        int countB = 0;
        ListNode currA = headA, currB = headB;
        while (currA != null) {
            countA++;
            currA = currA.next;
        }
        while (currB != null) {
            countB++;
            currB = currB.next;
        }

        // 两者相差节点数
        int subCount = Math.abs(countA - countB);
        if (countA < countB) {
            ListNode temp = headA;
            headA = headB;
            headB = temp;
        }

        // 让长的先走
        while (subCount > 0) {
            subCount--;
            headA = headA.next;
        }

        while (headA != null && headB != null) {
            if (headA == headB)
                return headA;
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    /**
     * @Author: HB
     * @Description: 妙妙解法
     * @Date: 23:45 2021/3/30
     * @Params: null
     * @Returns: 
    */
    // 算法思路： 
    // 1. 假设两个链表的长度为L1 + C, L2 + C, 其中C为链表的公共部分
    // 2. 链表A 和 链表B同时开始遍历, 若当链表A遍历到末尾时, 将其重新定位到链表B的开头进行遍历; 若当链表B遍历到末尾时, 将其重新定位到链表A的开头遍历
    // 3. 若链表存在交集, 则它们将会相遇, 即两者将会分别行走 L1 + L2 + C步数
    // 4. 若它们没有交集时, 分别将会走 L1 + L2 步, 将会同时到达尾部
    public ListNode getIntersectionNodeByBest(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode currA = headA, currB = headB;
        while (currA !=  currB) {
            currA = currA == null ? headB : currA.next;
            currB = currB == null ? headA : currB.next;
        }
        return currA;
    }
}
