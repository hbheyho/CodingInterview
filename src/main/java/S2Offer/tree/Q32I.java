package S2Offer.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题32I - 从上到下打印二叉树
 *               描述: 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，
 *               那么它是对称的。
 *               Case:
 *                   Input:     1
 *                            /   \
 *                           2     2
 *                          / \   / \
 *                         8  10 10  8
 *                   OutPut: [1,2,2,8,10,10,8]
 *               Limit: 0 <= 节点个数 <= 1000
 *               Remark:
 * @CreateDate: 13:01 2021/4/5
 */

public class Q32I {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x) {
            val = x;
        }
    }

    /**
     * @Author: HB
     * @Description: 层次遍历解法
     * @Date: 17:55 2021/4/5
     * @Params: null
     * @Returns:
    */
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
