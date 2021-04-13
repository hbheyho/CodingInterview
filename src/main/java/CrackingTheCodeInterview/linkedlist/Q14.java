package CrackingTheCodeInterview.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题02.05 - 链表求和
 *               描述: 给定两个用链表表示的整数，每个节点包含一个数位。
 *                     这些数位是反向存放的，也就是个位排在链表首部。
 *                     编写函数对这两个整数求和，并用链表形式返回结果。
 *               Case:
 *               Input:  (7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 *               Output: 2 -> 1 -> 9，即912
 *               Limit:
 *               Remark:
 * @CreateDate: 10:26 2021/4/13
 */

public class Q14 {

    public class ListNode {
        int val;
        ListNode next;
        public ListNode (int val) {
            this.val = val;
        }
    }

    /**
     * @Author: HB
     * @Description: 模拟手工求和解法
     * @Date: 10:28 2021/4/13
     * @Params: null
     * @Returns:
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 保存进位
        int t = 0;
        ListNode curr1 = l1, curr2 = l2, head = new ListNode(0), curr = head;
        while (curr1 != null || curr2 != null || t != 0) {
            int subSum = t;
            if(curr1 != null) {
                subSum += curr1.val;
                curr1 = curr1.next;
            }
            if (curr2 != null) {
                subSum += curr2.val;
                curr2 = curr2.next;
            }
            // 尾插法插入计算值
            curr.next = new ListNode(subSum % 10);
            curr = curr.next;
            // 计算进位
            t = subSum / 10;
        }

        return head.next;

    }
}
