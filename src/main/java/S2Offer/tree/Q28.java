package S2Offer.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题28 - 对称的二叉树
 *               描述: 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，
 *               那么它是对称的。
 *               Case:
 *                   Input:     1             1
 *                            /   \         /  \
 *                           2     2       4   4
 *                          / \   / \    / \  / \
 *                         8  10 10  8  7  10 10
 *                   OutPut: true
 *               Limit: 0 <= 节点个数 <= 1000
 *               Remark: <Lc> 101 对称二叉树翻转二叉树
 * @CreateDate: 17:41 2021/01/30
 */

public class Q28 {

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
     * @Date: 20:23 2021/1/31
     * @Params: null
     * @Returns:
    */
    public boolean isSymmetricByRecursion(TreeNode root) {

        if (root == null)
            return true;
        // 从根节点的左右依次进行比较
        return judgeSymmetrc(root.left, root.right);

    }

    public boolean judgeSymmetrc (TreeNode left, TreeNode right) {

        // 1. 递归结束条件
        if (left == null && right == null)
            return true;
        if (left == null || right == null || left.val != right.val)
            return false;

        // 2. 递进操作 3. 递归返回值
        return judgeSymmetrc (left.left, right.right) && judgeSymmetrc (left.right, right.left);

    }

    /**
     * @Author: HB
     * @Description: 迭代解法
     * @Date: 20:31 2021/1/31
     * @Params: null
     * @Returns:
    */
    public boolean isSymmetricByIteration(TreeNode root) {
        if (root == null)
            return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            TreeNode q = queue.poll();
            if (p == null && q == null)
                continue;
            if (p == null || q == null || q.val != p.val)
                return false;
            // p, q的左右孩子依次进队
            queue.offer(p.left);
            queue.offer(q.right);
            queue.offer(p.right);
            queue.offer(q.left);
        }

        return true;

    }
}
