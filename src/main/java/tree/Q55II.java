package tree;

/**
 * @Author: HB
 * @Description: 面试题55II - 平衡二叉树
 *               描述: 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 *               如果某二叉树中任意节点的左右子树的深度相差不超过1，
 *               那么它就是一棵平衡二叉树。
 *               Case:
 *                   Input:     3
 *                            /   \
 *                           2     5
 *                               / \
 *                              4   6
 *                   OutPut: true
 *               Limit: 节点总数 <= 10000
 *               Remark: <LC 110> 平衡二叉树
 * @CreateDate: 17:26 2021/2/17
 */

public class Q55II {

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
     * @Date: 17:29 2021/2/17
     * @Params: null
     * @Returns:
    */
    // 保存左右子树高度最大差值
    int maxSubHeight = 0;
    public boolean isBalancedByRecursion(TreeNode root) {
        if (root == null)
            return true;
        isBalancedByRecursion(root);
        if (maxSubHeight > 1)
            return false;
        else
            return true;
    }

    // 得到树的最大高度 - 在递归过程中计算左右子树高度的最大差值
    public int getDepth (TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = getDepth(root.left);
        int rightHeight = getDepth(root.right);
        // 更新最大差值
        if (Math.abs(leftHeight - rightHeight) > maxSubHeight)
            maxSubHeight = Math.abs(leftHeight - rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }


    /**
     * @Author: HB
     * @Description: 递归解法 - 进行剪枝操作
     * @Date: 17:31 2021/2/17
     * @Params: null
     * @Returns:
    */
    // 保存左右子树高度最大差值
    public boolean isBalancedByRecursion2(TreeNode root) {
        return getDepth2(root) != -1;
    }

    // 得到树的最大高度 - 在递归过程中计算左右子树高度的最大差值
    public int getDepth2 (TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = getDepth(root.left);
        if (leftHeight == -1)
            return -1;
        int rightHeight = getDepth(root.right);
        if (rightHeight == -1)
            return -1;
        // 当前节点的左右子树破坏了平衡二叉树性质, 直接返回-1
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
