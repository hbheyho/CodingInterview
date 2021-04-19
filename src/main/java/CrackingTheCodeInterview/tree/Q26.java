package CrackingTheCodeInterview.tree;

import java.util.*;

/**
 * @Author: HB
 * @Description: 面试题04.03 - 特定深度节点链表
 *               描述: 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，
 *                    则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *               Case:
 *               Input: [1,2,3,4,5,null,7,8]
 *  *               Output: [[1],[2,3],[4,5,7],[8]]
 *               Limit:
 *               Remark:
 * @CreateDate: 21:23 2021/4/19
 */

public class Q26 {
     /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

/* Definition for singly-linked list.*/
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: BFS解法
     * @Date: 21:24 2021/4/19
     * @Params: null
     * @Returns:
    */
    public ListNode[] listOfDepthByBFS(TreeNode tree) {
        List<ListNode> result = new ArrayList<>();
        if(tree == null) return result.toArray(new ListNode[result.size()]);
        // 创建一个队列进行BFS操作
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode head = new ListNode(-1), curr = head;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curr.next = new ListNode(node.val);
                curr = curr.next;
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            result.add(head.next);
        }

        return result.toArray(new ListNode[result.size()]);

    }

    /**
     * @Author: HB
     * @Description: DFS解法
     * @Date: 21:31 2021/4/19
     * @Params: null
     * @Returns:
    */
    // key/value => 当前层/保存一个链表的最末尾的链表节点
    Map<Integer, ListNode> map = new HashMap<>();
    List<ListNode>  result = new ArrayList<>();
    public ListNode[] listOfDepthByDFS(TreeNode tree) {
        dfs (tree, 0);
        return result.toArray(new ListNode[result.size()]);
    }

    public void dfs (TreeNode root, int level) {
        if (root == null)
            return;
        ListNode node = new ListNode(root.val);
        // 是否存在当前层
        if (map.containsKey(level)) {
            map.get(level).next = node;
        } else {
            // 若map中还不存在, 则代表是到达了全新的一层, 则node作为一个新链表结点（头结点）存到result中
            result.add(node);
        }
        // 更新一个链表的尾节点
        map.put(level, node);
        dfs (root.left, level + 1);
        dfs (root.right, level + 1);
    }
}
