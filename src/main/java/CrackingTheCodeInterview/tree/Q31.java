package CrackingTheCodeInterview.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题04.09 - 二叉搜索树序列
 *               描述:  从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 *                     给定一个由不同节点组成的二叉搜索树，输出所有可能生成此树的数组。
 *               Case:
 *               Input:
 *                                2
 *                               / \
 *                              1  3
 *                 Output: [ [2,1,3], [2,3,1]]
 *               Limit:
 *               Remark:
 * @CreateDate: 16:52 2021/4/22
 */

public class Q31 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * @Author: HB
     * @Description: 回溯解法
     * @Date: 16:55 2021/4/22
     * @Params: null
     * @Returns:
    */
    // 回溯算法
    public List<List<Integer>> BSTSequences(TreeNode root) {
        // 保存结果
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            result.add(new ArrayList<>());
            return result;
        }

        // 保存当前可能的输出元素选择
        LinkedList<TreeNode> list = new LinkedList<>();

        // 保存可能的结果序列, 先将根节点存入
        List<Integer> path = new ArrayList<>();
        path.add(root.val);

        dfs (root, result, path, list);
        return result;
    }

    // dfs函数：root已经加入到输出序列中, 将root的左右子树加入到候选输出元素, 继续递归输出
    public void dfs (TreeNode root, List<List<Integer>> result, List<Integer> path, LinkedList<TreeNode> list) {
        if (root == null)
            return;

        // 左右子树不为空, 则将其加入到候选输出
        if (root.left != null) {
            list.addLast(root.left);
        }
        if (root.right != null) {
            list.addLast(root.right);
        }

        // 若候选元素为空, 则表示找到了一个输出序列
        if (list.isEmpty()) {
            result.add(new ArrayList<>(path));
            return;
        }

        int size = list.size();
        // 从队列中挑选候选输出
        for (int i = 0; i < size; i++) {

            TreeNode node = list.get(i);
            list.remove(i);
            path.add(node.val);

            // 创建一个新的LinkedList对象, 保证里面只保存当前root的孩子节点
            dfs (node, result, path, new LinkedList<>(list));

            // 回溯
            path.remove(path.size() - 1);
            list.add(i, node);
        }
    }
}
