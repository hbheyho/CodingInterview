package linkedlist;

/**
 * @Author: HB
 * @Description: 面试题18II -删除链表中重复的节点
 *               描述：在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留。
 *               Case:
 *                   Input: "1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5"
 *                   Output: "1 -> 2 -> 5"
 *               Limit:
 *               Remark:
 * @CreateDate: 14:41 2021/1/14
 */

public class Q18II {
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
     * @Description: 虚拟头结点解答
     * @Date: 14:46 2021/1/24
     * @Params: null
     * @Returns: 
    */
    public ListNode deleteDuplication(ListNode head) {
        // 设置一个虚拟头节点, 以便统一化对头节点的删除
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode, curr = head;

        while (curr != null) {

            // 一直向右查找, 一直找到不重复元素
            while (curr.next != null && curr.next.val == curr.val)
                curr = curr.next;

            // 若找到了一连串重复元素(pre与next的间隔大于1), 即pre.next != curr, 则删除
            // 否则指针继续向后移动
            if (pre.next != curr) {
                // 删除重复节点
                pre.next = curr.next;
            } else {
                pre = pre.next;
            }
            curr = curr.next;

        }
        return dummyNode.next;
    }
}
