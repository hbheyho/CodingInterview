package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题32II - 从上到下打印二叉树II
 *               描述:  从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，
 *                      每一层打印到一行。
 *               Case:
 *                   Input:     1
 *                            /   \
 *                           2     2
 *                          / \   / \
 *                         8  10 10  8
 *                   OutPut: [[1],[2,2,],[8,10,10,8]]
 *               Limit: 0 <= 节点个数 <= 1000
 *               Remark: <102> 二叉树的层次遍历
 * @CreateDate: 18:37 2021/4/5
 */

public class Q32II {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public  TreeNode (int x) {
            val = x;
        }
    }

    /**
     * @Author: HB
     * @Description: 层次遍历解法
     * @Date: 18:40 2021/4/5
     * @Params: null
     * @Returns:
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subResult.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            result.add(subResult);
        }

        return result;
    }


    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 18:49 2021/4/5
     * @Params: null
     * @Returns:
    */
    public List<List<Integer>> levelOrderByRecursion(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs (root, 0, result);
        return result;
    }

    // level: 二叉树层数
    public void dfs (TreeNode root, int level, List<List<Integer>> result) {

        if (root == null)
            return;

        if (level == result.size())
            result.add(new ArrayList<>());
        result.get(level).add(root.val);

        dfs (root.left, level + 1, result);
        dfs (root.right, level + 1, result);

    }


}
