package S2Offer.tree;

/**
 * @Author: HB
 * @Description: 面试题68II - 二叉树的最近公共祖先
 *               描述: 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *                     百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 *                     最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 *                     （一个节点也可以是它自己的祖先）。”
 *                      例如，给定如下二叉树:  root = [6,9,8,5,4,7,10]
 *               Case:
 *                   Input:        6        p = 9, q = 8
 *  *                            /   \
 *  *                           9    8
 *  *                          / \   / \
 *  *                         5  4  7  10
 *                   OutPut: 6
 *               Limit: 所有节点的值都是唯一的。
 *                      p、q 为不同节点且均存在于给定的二叉搜索树中。
 *               Remark: <LC 236 > 二叉树的最近公共祖先
 * @CreateDate: 14:08 2021/3/9
 */

public class Q68II {

    // Definition for a binary S2Offer.tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 14:09 2021/3/9
     * @Params: null
     * @Returns:
    */
    // 对于公共祖先问题, 主要面临以下三种情况:
    // 1. p, q 分别在root的左右子树上, 那么最近公共祖先即为root节点
    // 2. p, q 在root的左子树上, 则继续root.left查找
    // 3. p, q 在root的右子树上, 则继续root.right查找
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 1. 递归结束条件
        if (root == null)
            return null;
        if (p == root || q == root)
            return root;

        // 2. 递进操作
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 3. 递归返回值
        // 1. 若left, right不为空, 则表示p, q分别在root的左右子树上
        if (left != null && right != null)
            return root;

        // 2. 若left不为空, right为空, 则表示p, q在root的左子树上
        if (left != null && right == null)
            return left;

        // 3. 若right不为空, left为空, 则表示p, q在root的右子树上
        if (right != null && left == null)
            return right;

        return null;
    }
}
