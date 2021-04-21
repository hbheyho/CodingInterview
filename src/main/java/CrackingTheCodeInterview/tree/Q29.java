package CrackingTheCodeInterview.tree;


/**
 * @Author: HB
 * @Description: 面试题04.06 - 后继者
 *               描述: 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *                     如果指定节点没有对应的“下一个”节点，则返回null。
 *               Case:
 *               Input:
 *                               2   p = 1
 *                              / \
 *                             1  3
 *                 Output: 2
 *               Limit:
 *               Remark:
 * @CreateDate: 10:01 2021/4/21
 */

public class Q29 {

    /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 10:03 2021/4/21
     * @Params: null
     * @Returns:
    */
    // BST: 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树
    // 中序遍历递归解法
    public TreeNode inorderSuccessorByRecursion(TreeNode root, TreeNode p) {
        if (root == null || p == null)
            return null;
        // 若p.val >= root.val从root的右子树查找
        if (p.val >= root.val) {
            return inorderSuccessorByRecursion (root.right, p);
            // 若p.val < root.val, 那么其后继节点存在两个可能性：
            // 1. 要么在root.left中
            // 2. 要么就是root
        } else {
            TreeNode left = inorderSuccessorByRecursion (root.left, p);
            return left == null ? root : left;
        }
    }


    /**
     * @Author: HB
     * @Description: 中序遍历迭代解法
     * @Date: 10:04 2021/4/21
     * @Params: null
     * @Returns:
    */
    // BST: 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树
    public TreeNode inorderSuccessorByIterator(TreeNode root, TreeNode p) {
        // 1. P 节点有右子树, 则找到其右子树的最左节点
        if (p.right != null) {
            TreeNode node = p.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // 2. p节点没有右子树
        // 保存第一次发生左转的元素
        TreeNode pre = null;
        while (root.val != p.val) {
            // 发生左转
            if (p.val < root.val) {
                pre = root;
                root = root.left;
                // 发生右转
            } else {
                root = root.right;
            }
        }
        return pre;
    }


}
