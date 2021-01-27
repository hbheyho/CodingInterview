package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题07 - 重建二叉树
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
    // 算法流程：
    // 递归建立整棵二叉树：先递归创建左右子树，然后创建根节点，并让指针指向两棵子树。
    // 具体步骤如下：
    // 先利用前序遍历找根节点：前序遍历的第一个数，就是根节点的值;
    // 在中序遍历中找到根节点的位置 inorderRoot，则 inorderRoot 左边是左子树的中序遍历，右边是右子树的中序遍历;
    // 假设左子树的中序遍历的长度是 leftNums，则在前序遍历中，根节点后面的 leftNums 个数，是左子树的前序遍历，剩下的数是右子树的前序遍历;
    // 有了左右子树的前序遍历和中序遍历，我们可以先递归创建出左右子树，然后再创建根节点.

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

    public static void main(String[] args) {
        int x = 34, y = 35;
        int sum = 0;
        while (x > 0 | y > 0) {
            sum += (x % 10) + (y % 10);
            x /= 10;
            y /= 10;
        }
        System.out.println(sum);
        // return sum == threshold;
    }
}
