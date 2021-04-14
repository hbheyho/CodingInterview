package CrackingTheCodeInterview.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题02.08 - 环路检测
 *               描述: 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
 *                     如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，
 *                     则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos
 *                     来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，
 *                     则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表
 *                     的实际情况。
 *               Case:
 *               Input:  head = [3,2,0,-4], pos = 1
 *               Output: 1
 *               Limit:
 *               Remark: <LC 142> 环形链表II
 * @CreateDate: 10:28 2021/4/14
 */

public class Q17 {

    public class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: 快慢指针解法
     * @Date: 10:37 2021/4/14
     * @Params: null
     * @Returns:
    */
    // 可以使用快慢指针来判断是否有环, 通过一点点修改也可以来查找环路的开始位置
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // fast指针和slow指针相遇, 表示存在环路, 继续找到环路的入口
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

}
