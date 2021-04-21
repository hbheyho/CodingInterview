package CrackingTheCodeInterview.tree;

/**
 * @Author: HB
 * @Description: 面试题04.07 - 首个公共祖先
 *               描述: 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。
 *                     不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树
 *               Case:
 *               Input:
 *                                3     p = 5, q = 1
 *                               / \
 *                              5   1
 *                             / \ / \
 *                            6  2 0  8
 *                                 / \
 *                                7   4
 *                 Output: 3
 *               Limit:
 *               Remark:
 * @CreateDate: 10:49 2021/4/21
 */

public class Q30 {
    /* Definition for a binary tree node.*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 10:51 2021/4/21
     * @Params: null
     * @Returns:
    */
    // 最近公共祖先
    // 算法思路：当p, q分别位于root的左右子树时, 最近公共祖先为root
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        // p, q在root的左子树中, 那么继续去root左子树寻找
        if (hasTreeNode(root.left, p) && hasTreeNode(root.left, q))
            return lowestCommonAncestor(root.left, p, q);
            // p, q在root的右子树中, 那么继续去root右子树寻找
        else if (hasTreeNode(root.right, q) && hasTreeNode(root.right, p))
            return lowestCommonAncestor (root.right, p, q);
            // p, q存在root的左右子树上, 那么最近公共祖先即为root
        else
            return root;
    }

    // hasTreeNode：判断当前root为根的子树中是否包含了node节点
    public boolean hasTreeNode (TreeNode root,TreeNode node) {
        if (root == null)
            return false;
        if (root.val == node.val)
            return true;
        return hasTreeNode (root.left, node) || hasTreeNode (root.right, node);
    }

    /**
     * @Author: HB
     * @Description: 递归解法优化
     * @Date: 10:52 2021/4/21
     * @Params: null
     * @Returns:
    */
    // 最近公共祖先
    // 算法思路：当p, q分别位于root的左右子树时, 最近公共祖先为root
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q)
            return root;
        // 去root的左右子树寻找p, q
        TreeNode left = lowestCommonAncestor2 (root.left, p, q);
        TreeNode right = lowestCommonAncestor2 (root.right, p, q);
        // 1. p, q分别位于root的左右子树上, 直接返回root
        if (left != null && right != null)
            return root;
        // 2. p, q都位于root的左子树上, 返回left即可
        if (left != null && right == null) {
            return left;
            // 3. p, q都位于root的右子树上, 返回right
        } else {
            return right;
        }

    }
}
