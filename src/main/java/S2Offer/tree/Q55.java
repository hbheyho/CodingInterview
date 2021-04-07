package S2Offer.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题55 - 二叉树的深度
 *               描述: 输入一棵二叉树的根节点，求该树的深度。
 *               从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，
 *               最长路径的长度为树的深度。
 *               Case:
 *                   Input:     3
 *                            /   \
 *                           2     5
 *                               / \
 *                              4   6
 *                   OutPut: 3
 *               Limit: 节点总数 <= 10000
 *               Remark: <LC 104> 二叉树的最大深度
 * @CreateDate: 17:15 2021/2/17
 */

public class Q55 {

    /* Definition for a binary S2Offer.tree node.*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 17:24 2021/2/17
     * @Params: null
     * @Returns:
    */
    public int maxDepthByRecursion(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepthByRecursion(root.left) + 1, maxDepthByRecursion(root.right) + 1);
    }


    /**
     * @Author: HB
     * @Description: 迭代解法
     * @Date: 17:24 2021/2/17
     * @Params: null
     * @Returns:
    */
    public int maxDepthByIterator(TreeNode root) {
        if (root == null)
            return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++ ){
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            res++;
        }
        return res;
    }
}
