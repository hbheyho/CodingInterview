package S2Offer.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题27 - 二叉树的镜像
 *               描述: 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *               Case:
 *                   Input:     1             1
 *                            /   \         /  \
 *                           3     4       4   3
 *                          / \   / \    / \  / \
 *                         8  10 9  7   7  9 10 8
 *                   OutPut: 9
 *               Limit: 0 <= 节点个数 <= 1000
 *               Remark: <Lc> 226 翻转二叉树
 * @CreateDate: 17:41 2021/01/30
 */
public class Q27 {
    /*
    *  Definition for a binary S2Offer.tree node
    * */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x) {
            this.val = x;
        }
    }

    /**
     * @Author: HB
     * @Description: 迭代解法
     * @Date: 18:03 2021/1/30
     * @Params: null
     * @Returns:
    */
    public TreeNode mirrorTreeByRecursion(TreeNode root) {
        // 1. 递归结束条件
        if (root == null)
            return null;

        // 2. 递进操作
        TreeNode rightNode = mirrorTreeByRecursion (root.left);
        TreeNode leftNode = mirrorTreeByRecursion (root.right);
        root.right = rightNode;
        root.left = leftNode;

        // 3. 递归返回值
        return root;
    }

    /**
     * @Author: HB
     * @Description: 递归解法的另外一种实现形式
     * @Date: 18:09 2021/1/30
     * @Params: null
     * @Returns:
    */
    public TreeNode mirrorTreeByRecursion2(TreeNode root) {

        // 1. 递归结束条件
        if (root == null)
            return null;

        // 2. 递进操作
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        mirrorTreeByRecursion2 (root.left);
        mirrorTreeByRecursion2 (root.right);

        // 3. 递归返回值
        return root;

    }

    /**
     * @Author: HB
     * @Description: 迭代解法
     * @Date: 18:05 2021/1/30
     * @Params: null
     * @Returns:
    */
    public TreeNode mirrorTreeByIteration(TreeNode root) {
        if (root == null)
            return null;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 进行交换
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null)
                queue.offer (node.left);
            if (node.right != null)
                queue.offer (node.right);
        }

        return root;
    }

}
