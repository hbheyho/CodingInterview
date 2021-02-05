package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题35 - 复杂链表的复制
 *               描述: 请实现一个函数可以复制一个复杂链表。在复杂链表中，每个结点除了有一个指针
 *               指向下一个结点外，还有一个额外的指针指向链表中的任意结点或者null。
 *
 *               Case:
 *                   Input: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *                   Output: 1[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *               Limit: -10000 <= Node.val <= 10000
 *                      Node.random 为空（null）或指向链表中的节点。
 *                      节点数目不超过 1000 。
 *                      函数结束后原链表要与输入时保持一致。
 *               Remark: <Lc> 138 复制带随机指针的链表
 */

public class Q35 {
    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * @Author: HB
     * @Description: 暴力解法 - 时间复杂度: O(n^2); 空间复杂度：O(1)
     * @Date: 16:32 2021/2/5
     * @Params: null
     * @Returns:
    */
    // 实现随机链表的深拷贝 - 不是引用的拷贝, 所以每次都要创建一个新节点
    public Node copyRandomListByForce(Node head) {
        if (head == null)
            return null;
        // 创建新链表的头节点
        Node newHead = new Node(head.val);
        Node p = head, q = newHead;
        // 1. 首先完成next指针的复制
        while (p.next != null) {
            q.next = new Node(p.next.val);
            p = p.next;
            q = q.next;
        }

        // 2. 完成random指针的复制
        // random指针指向的节点存在两种情况：
        // 1. random指针指向null
        // 2. random指针指向的节点不为空
        p = head;
        q = newHead;
        while (p != null) {

            Node randomNode = p.random;
            // (1) random指针为空
            if (randomNode == null)
                q.random = null;
                // (2) random指向的节点不为空
            else {
                // 计算从head节点到randomNode节点需要的步数
                int step = 0;
                Node tempHead = head;
                Node tempNewHead = newHead;
                while (tempHead != null && randomNode != tempHead) {
                    tempHead = tempHead.next;
                    step++;
                }
                // 从newHead开始行走, 根据步数来确定randomNode的位置
                while (step-- > 0) {
                    tempNewHead = tempNewHead.next;
                }

                q.random = tempNewHead;

            }

            p = p.next;
            q = q.next;

        }

        return newHead;

    }


    /**
     * @Author: HB
     * @Description: Hash表解法 - 时间复杂度: O(n); 空间复杂度：O(n)
     * @Date: 16:33 2021/2/5
     * @Params: null
     * @Returns:
    */
    // 实现随机链表的深拷贝 - 不是引用的拷贝, 所以每次都要创建一个新节点
    public Node copyRandomListByHashMap(Node head) {
        if (head == null)
            return null;
        // 创建新链表的头节点
        Node newHead = new Node(head.val);
        Node p = head, q = newHead;
        // key/value => 原链表节点/复制链表节点
        Map<Node, Node> map = new HashMap<>();
        map.put(p, q);
        // 1. 首先完成next指针的复制
        while (p.next != null) {
            q.next = new Node(p.next.val);
            map.put(p.next, q.next);
            p = p.next;
            q = q.next;
        }

        // 2. 完成random指针的复制
        // random指针指向的节点存在两种情况：
        // 1. random指针指向null
        // 2. random指针指向的节点不为空
        p = head;
        q = newHead;
        while (p != null) {

            Node randomNode = p.random;
            // (1) random指针为空
            if (randomNode == null)
                q.random = null;
                // (2) random指向的节点不为空
            else {
                // 从Hash表中找到random的对应节点
                Node newRandomNode = map.get(randomNode);
                q.random = newRandomNode;
            }

            p = p.next;
            q = q.next;

        }

        return newHead;

    }


    /**
     * @Author: HB
     * @Description: 剑指offer解法
     * @Date: 16:34 2021/2/5
     * @Params: null
     * @Returns:
    */
    // 实现随机链表的深拷贝 - 不是引用的拷贝, 所以每次都要创建一个新节点
    // 算法思路:
    // 1. 首先完成复制链表的next指针链接, 在复制的同时, 将复制链表的每个节点依次链接到原链表之后
    // 2. 完成复制链表的random指针的链表：原链表中node节点的random指针指向random节点, 那么复制链表中的node'节点的random指针指向random.next节点, 可即为random'节点
    // 3. 最后将将两个链表分离
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        // 创建新链表的头节点
        Node newHead = new Node(head.val);
        Node p = head;
        newHead.next = p.next;
        p.next = newHead;
        // 1. 首先完成复制链表的next指针链接, 在复制的同时, 将复制链表的每个节点依次链接到原链表之后
        while (p.next.next != null) {
            // 复制链表的节点链接到原链表对应节点之后
            Node node = new Node(p.next.next.val);
            node.next = p.next.next.next;
            p.next.next.next = node;

            p = p.next.next;

        }

        // 2. 完成复制链表的random指针的链表
        // 原链表中node节点的random指针指向random节点, 那么复制链表中的node'节点的random指针指向random.next节点, 可即为random'节点
        p = head;
        Node q = newHead;
        while (p != null) {

            Node randomNode = p.random;
            // (1) random指针为空
            if (randomNode == null)
                q.random = null;
                // (2) random指向的节点不为空
            else {
                q.random = randomNode.next;
            }

            p = p.next.next;
            if (q.next != null)
                q = q.next.next;

        }

        // 3. 最后将将两个链表分离
        p = head;
        q = newHead;
        while (true) {
            p.next = q.next;
            p = p.next;
            // 完成链表分离
            if (p == null)
                break;
            q.next = p.next;
            q = q.next;
        }

        return newHead;

    }
}
