package CrackingTheCodeInterview.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题02.03 - 删除中间节点
 *               描述: 实现一种算法，删除单向链表中间的某个节点（即不是第一个或
 *               最后一个节点），假定你只能访问该节点。
 *               Case:
 *               Input: a -> b -> c -> d -> e -> f node = c
 *               Output: a -> b -> d -> e -> f
 *               Limit:
 *               Remark:
 * @CreateDate: 19:08 2021/4/12
 */

public class Q12 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode (int val) {
            this.val = val;
        }
    }
    /**
     * @Author: HB
     * @Description: 替换解法
     * @Date: 19:10 2021/4/12
     * @Params: null
     * @Returns: 
    */
    // 后面的结点替换要删除的结点, 然后删除后续结点
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
