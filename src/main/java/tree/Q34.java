package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题34 -二叉树中和为某一值的路径
 *               描述: 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 *               从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *               Case:
 *                   Input:     5
 *                            /   \
 *                           4     8
 *                          /    / \
 *                         11  13  4
 *                        / \     / \
 *                       7  2    5  1
 *                   OutPut: true
 *               Limit: 数组长度 <= 1000
 *               Remark:
 * @CreateDate: 21:44 2021/2/2
 */

public class Q34 {

    /* Definition for a binary tree node.*/
    static public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 回溯解法
     * @Date: 22:07 2021/2/2
     * @Params: null
     * @Returns:
    */
    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs (root, new ArrayList<>(), sum);
        return res;
    }

    public static void dfs (TreeNode root, List<Integer> path, int sum) {
        // 1. 递归结束条件
        if (root == null)
            return;

        // 2. 递进操作
        path.add(root.val);
        sum -= root.val;

        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<>(path));
        }

        dfs (root.left, path, sum);
        dfs (root.right, path, sum);

        // path回溯
        path.remove(path.size() - 1);

        // 3. 递归返回值 - 暂无
    }
}
