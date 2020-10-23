package tree;

import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题07 - 二叉树的下一个节点
 *               描述: 给定一颗二叉树和其中一个节点, 如何找出中序遍历序列的下一个节点?
 *               树中的节点除了有两个分别指向左右节点的指针, 还有一个指向父节点的指针。
 *               Case:
 *                   Input:     1        1
 *                            /   \
 *                           3     4
 *                          / \   / \
 *                         8  10 9  7
 *                   OutPut: 9
 *               Limit:
 * @CreateDate: 14:41 2020/10/23
 */

public class Q8 {

    // 树结点定义
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode findNextNode (TreeNode node) {
        TreeNode nextNode = null;
        // 当前结点有右子树, 则从其右子树一直向左遍历就可找到下一个结点
        if (node.right != null) {
            nextNode = node.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
        }
        // 没有右子树,且当父节点不为空时
        else if (node.parent != null) {
            TreeNode currNode = node;
            TreeNode parentNode = node.parent;
            // 当前结点是父节点的右结点时,往上进行查找下一个结点
            // 找到一个是它父节点的左节点的节点
            while (parentNode != null && currNode == parentNode.right) {
                currNode = parentNode;
                parentNode = parentNode.parent;
            }
            // 也对当前结点是父节点的左结点这种情况进行了处理
            nextNode = parentNode;
        }
        return nextNode;
    }


    public static void main(String[] args) {
        // 构建二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.parent = root;
        root.right.parent = root;

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.parent = root.left;
        root.left.right.parent = root.left;

        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.left.right.left.parent = root.left.right;
        root.left.right.right.parent = root.left.right;

        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(9);
        root.right.left.parent = root.right;
        root.right.right.parent = root.right;

        // 测试用例
        TreeNode node = root.left;
        node = root.left.left;
        node = root.right.left;
        node = root.left.right.right;
        node = root.right.right;

        findNextNode(node);
    }

}
