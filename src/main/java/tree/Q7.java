package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题05 - 替换空格
 *               描述: 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 *               假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *               Case:
 *                   Input: 前序遍历 preorder = [3,9,20,15,7]
 *                          中序遍历 inorder = [9,3,15,20,7]
 *                   Output:   3
 *                            / \
 *                           9  20
 *                           /   \
 *                          15   7
 *               Limit: 0 <= preorder.length <= 5000
 * @CreateDate: 14:41 2020/10/22
 */
public class Q7 {
    // Definition for binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 17:03 2020/10/22
     * @Params:
     * @Returns:
    */
    // 使用Map来记录中序遍历中每个节点位置
    static Map<Integer, Integer> inorderMap;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        // 存储中序节点值以及每个值出现位置
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return createTree(preorder, inorder , 0 , preorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode createTree(int[] preorder, int[] inorder ,int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {

        // 1. 递归结束条件
        if (preorderLeft > preorderRight)
            return null;

        // 2. 递进操作
        // 根据中序遍历创建根节点
        TreeNode root = new TreeNode(preorder[preorderLeft]);
        // 得到当前根节点值在中序遍历中的位置
        int inorderRoot = inorderMap.get(preorder[preorderLeft]);
        // 得到当前根节点的左子树节点个数
        int leftNums = inorderRoot - inorderLeft;
        // 根据所得到的左子树节点个数, 将前序遍历和中序遍历进行划分,
        // 继续遍历左子树
        root.left = createTree(preorder, inorder, preorderLeft + 1, preorderLeft + leftNums, inorderLeft, inorderRoot - 1);
        // 继续遍历右子树
        root.right = createTree(preorder, inorder, preorderLeft + leftNums + 1, preorderRight, inorderRoot + 1, inorderRight);

        // 3. 递归返回值
        return root;
    }
}
