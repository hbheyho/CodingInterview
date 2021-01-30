package tree;

/**
 * @Author: HB
 * @Description: 面试题26 - 树的子结构
 *               描述: 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *               B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *               Case:
 *                   Input:     3         4
 *                            /   \      /  \
 *                           4     5    1   2
 *                          / \   / \
 *                         1  2 9  7
 *                   OutPut: true
 *               Limit: 0 <= 节点个数 <= 10000
 * @CreateDate: 14:41 2020/10/23
 */
public class Q26 {

    /**
    * Definition for a binary tree node.
    */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法 - 时间复杂度：O(nm), n 为树A的节点个数, m 为树B的节点个数
     * @Date: 17:11 2021/1/30
     * @Params: null
     * @Returns:
    */
    // 空树不是任何树的子结构
    // 要判断树B是否是树A的子结构, 可以分以下两步来进行：
    // 1. 在树A中找到和树B的根节点的值一样的节点R;
    // 2. 判断树A中以R为根节点的子树是不是包含和树B一样的结构.
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        if (A == null || B == null)
            return false;

        boolean result = false;

        // 1. 在树A中和找到和树B的根节点一样的节点R
        // 若找到了, 则判断是否具有一样的结构
        // 若没有找到, 则继续向左右子树寻找
        if (A.val == B.val)
            result = doesHasSameTree(A, B);
        if (!result)
            result = isSubStructure(A.left, B);
        if (!result)
            result = isSubStructure (A.right, B);

        return result;
    }

    // 2. 判断树A中以R为根节点的子树是不是包含和树B一样的结构.
    public boolean doesHasSameTree (TreeNode rootA, TreeNode rootB) {

        // 1. 递归结束条件
        if (rootB == null)
            return true;
        if (rootA == null)
            return false;
        if (rootA.val != rootB.val)
            return false;

        // 2./3. 递进操作和递归结束条件
        // 继续递归判断左右子树是否有着相同的结构
        return doesHasSameTree (rootA.left, rootB.left) && doesHasSameTree (rootA.right, rootB.right);

    }

}
