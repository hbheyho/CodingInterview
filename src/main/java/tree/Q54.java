package tree;

/**
 * @Author: HB
 * @Description: 面试题54 - 二叉树中第k大节点
 *               描述: 给定一棵二叉搜索树，请找出其中第k大的节点。
 *               Case:
 *                   Input:     3       k = 3
 *                            /   \
 *                           2     5
 *                               / \
 *                              4   6
 *                   OutPut: 4
 *               Limit: 1 ≤ k ≤ 二叉搜索树元素个数
 *               Remark:
 * @CreateDate: 17:07 2021/2/17
 */

public class Q54 {

     /* Definition for a binary tree node */
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
    /**
     * @Author: HB
     * @Description: 反中序遍历
     * @Date: 17:09 2021/2/17
     * @Params: null
     * @Returns:
    */
    // 中序遍历解法
    int res = 0, count = 0;
    public int kthLargest(TreeNode root, int k) {
        postOrder (root, k);
        return res;
    }

    public void postOrder (TreeNode root, int k) {
        if (root == null)
            return;

        kthLargest(root.right, k);

        if (++count == k) {
            res = root.val;
            return;
        }

        kthLargest(root.left, k);

    }
}
