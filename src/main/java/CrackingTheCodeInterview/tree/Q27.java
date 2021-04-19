package CrackingTheCodeInterview.tree;

/**
 * @Author: HB
 * @Description: 面试题04.04 - 检查平衡性
 *               描述: 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：
 *                     任意一个节点，其两棵子树的高度差不超过 1。
 *               Case:
 *               Input: [3,9,20,null,null,15,7]
 *                               3
 *                              / \
 *                             9  20
 *                              /   \
 *                             15   7
 *                 Output: true
 *               Limit:
 *               Remark:
 * @CreateDate: 21:58 2021/4/19
 */

public class Q27 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 22:01 2021/4/19
     * @Params: null
     * @Returns:
    */
    public boolean isBalanced(TreeNode root) {
        return dfs(root) >= 0;
    }

    public int dfs (TreeNode root) {
        if (root == null)
            return 0;
        int leftLevel = dfs (root.left);
        int rightLevel = dfs (root.right);
        if (leftLevel == -1 || rightLevel == -1 || Math.abs(leftLevel - rightLevel) > 1)
            return -1;
        return Math.max(leftLevel, rightLevel) + 1;
    }
}
