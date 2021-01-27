package linkedlist;

/**
 * @Author: HB
 * @Description: 面试题18 - 在O(1)时间删除链表节点
 *               描述：给定单向链表的一个节点指针，定义一个函数在O(1)时间删除该结点。
 *               假设链表一定存在，并且该节点一定不是尾节点。
 *               Case:
 *                   Input: "1 -> 4 -> 6 -> 8", 删除第二个节点
 *                   Output: "1 -> 4 -> 8"
 *               Limit:
 *               Remark:
 * @CreateDate: 14:41 2021/1/14
 */

public class Q18 {
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
     * @Description: 巧妙解法
     * @Date: 14:46 2021/1/24
     * @Params: null
     * @Returns:
    */
    // 给定node节点, 然后删掉给定节点, 需O(1)时间复杂度
    // 算法思想：
    // 1. 使用后面的值node.next.val覆盖要删除的节点值node.val
    // 2. 最后删除当前node的下一个节点, node.next = node.next.next
    public void deleteNode(ListNode node) {

        node.val = node.next.val;
        node.next = node.next.next;

    }

}
