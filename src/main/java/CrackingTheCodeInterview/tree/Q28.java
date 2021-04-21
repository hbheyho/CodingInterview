package CrackingTheCodeInterview.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题04.05 - 合法二叉搜索树
 *               描述: 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 *               Case:
 *               Input:
 *                               10
 *                              / \
 *                             9  20
 *                              /   \
 *                             15   22
 *                 Output: true
 *               Limit:
 *               Remark:
 * @CreateDate: 23:50 2021/4/20
 */

public class Q28 {
    /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 中序遍历解法 - 递归
     * @Date: 23:52 2021/4/20
     * @Params: null
     * @Returns:
    */
    // BST的中序遍历都是有序的, 所有可以判断当前节点是否大于中序遍历情况下的前一个节点
    // 保存前一个结点
    TreeNode pre;
    public boolean isValidBSTByInorderRecursion(TreeNode root) {
        if (root == null)
            return true;
        if (!isValidBSTByInorderRecursion (root.left))
            return false;
        if (pre != null && pre.val >= root.val)
            return false;
        // 更新前一个结点
        pre = root;
        if (!isValidBSTByInorderRecursion (root.right))
            return false;
        return true;
    }

    /**
     * @Author: HB
     * @Description: 中序遍历 - 非递归解法
     * @Date: 23:53 2021/4/20
     * @Params: null
     * @Returns:
    */
    // BST的中序遍历都是有序的, 所有可以判断当前节点是否大于中序遍历情况下的前一个节点
    // 中序遍历非递归解法
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        Deque<TreeNode> stack = new LinkedList<>();
        // 当前结点的前一个结点
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            // 找到当前结点的最左边的结点, 并把经过的结点存入栈中
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 出栈一个结点
            root = stack.pop();
            if (pre != null && pre.val >= root.val)
                return false;
            // 更新结点指针
            pre = root;
            root = root.right;
        }
        return true;
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 23:54 2021/4/20
     * @Params: null
     * @Returns:
    */
    // 二叉搜索树性质：如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树
    // 设置递归函数：isValidBST(root, minValue, maxValue), 判断root.val是否在(minValue, maxValue)之间
    // 若不满足, 则表示不是符合条件的二叉搜索树; 否则继续调用递归函数判断其左右子树都是否满足
    public boolean isValidBSTByRecursion(TreeNode root) {
        return isValidBST (root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST (TreeNode root, long minValue, long maxValue) {
        if (root == null)
            return true;
        int val = root.val;
        if (val <= minValue || val >= maxValue)
            return false;
        // 判断左/右子树是否符合条件
        return isValidBST (root.left, minValue, val) && isValidBST (root.right, val, maxValue);
    }

}
