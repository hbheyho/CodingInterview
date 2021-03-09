package tree;

/**
 * @Author: HB
 * @Description: 面试题68I - 二叉搜索树的最近公共祖先
 *               描述: 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *                     百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 *                     最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 *                     （一个节点也可以是它自己的祖先）。”
 *                      例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9]
 *               Case:
 *                   Input:        6        p = 2, q = 8
 *  *                            /   \
 *  *                           2     8
 *  *                          / \   / \
 *  *                         0  4  7  9
 *                   OutPut: 6
 *               Limit: 所有节点的值都是唯一的。
 *                      p、q 为不同节点且均存在于给定的二叉搜索树中。
 *               Remark: <LC 235 > 二叉搜索树的最近公共祖先
 * @CreateDate: 12:52 2021/3/9
 */

public class Q68I {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 12:55 2021/3/9
     * @Params: null
     * @Returns:
    */
    // 利用二叉搜索树的性质进行处理
    // 1. 递归函数：定义递归函数lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)：以root作为根节点, 寻找p, q的最近公共祖先
    // 2. 递归结束条件：当root为空时, 递归结果
    // 3. 递进操作：因为树为二叉搜索树, 判断p.val, q.val 和 root.val的大小.
    //      (1) 若p.val <= root.val <= q.val, 则表示找到了最近公共祖先, 返回root;
    //      (2) 若p.val < root.val, q.vla < root.val, 则表示最近公共祖先在root的左子树上, 继续递归查找;
    //      (3) 若p.val > root.val, q.vla > root.val, 则表示最近公共祖先在root的右子树上, 继续递归查找;
    // 4. 递归返回值：root, 最近公共祖先
    public TreeNode lowestCommonAncestorByRecursion(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorByRecursion(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorByRecursion(root.left, p, q);
        } else {
            return root;
        }
    }


    /**
     * @Author: HB
     * @Description: 迭代解法
     * @Date: 12:58 2021/3/9
     * @Params: null
     * @Returns:
    */
    public TreeNode lowestCommonAncestorByIterator(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }
}
