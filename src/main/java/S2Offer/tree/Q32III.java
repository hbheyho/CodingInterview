package S2Offer.tree;

import java.util.*;

/**
 * @Author: HB
 * @Description: 面试题32III - 从上到下打印二叉树III
 *               描述:  请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 *               第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *               Case:
 *                   Input:     3
 *                            /   \
 *                           9     10
 *                                / \
 *                               8  7
 *                   OutPut: [[1],[10,9],[8,7]
 *               Limit: 0 <= 节点个数 <= 1000
 *               Remark:
 * @CreateDate: 18:37 2021/4/5
 */

public class Q32III {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public  TreeNode (int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 双端队列
            LinkedList<Integer> subResult = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int level = result.size();
                // 处于奇数层, 则从后到前存储
                // 处于偶数层, 则从前到后存储
                if (level % 2 != 0)
                    subResult.addFirst(node.val);
                else
                    subResult.addLast(node.val);

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
     * @Description: reverse API解法
     * @Date: 18:52 2021/4/5
     * @Params: null
     * @Returns:
    */
    public List<List<Integer>> levelOrderByAPI(TreeNode root) {
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
            if (result.size() % 2 != 0) {
                Collections.reverse(subResult);
            }

            result.add(subResult);
        }

        return result;

    }
}
