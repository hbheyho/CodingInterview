package S2Offer.tree;


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
 * @CreateDate: 16:41 2021/01/30
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
    // 给定的节点主要可以分以下几种情况：
    // 1. 如果当前节点有右儿子，则右子树中最左侧的节点就是当前节点的后继;
    // 2. 如果当前节点没有右儿子，则需要沿着father域一直向上找，找到第一个是其father左儿子的节点，该节点的father就是当前节点的后继
    public static TreeNode findNextNode (TreeNode node) {
        TreeNode nextNode = null;
        // 1. 如果当前节点有右儿子，则右子树中最左侧的节点就是当前节点的后继;
        if (node.right != null) {
            nextNode = node.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
        }
        // 2. 如果当前节点没有右儿子，则需要沿着parent域一直向上找，找到第一个是其parent左儿子的节点，该节点的parent就是当前节点的后继
        else if (node.parent != null) {
            TreeNode currNode = node;
            TreeNode parentNode = node.parent;
            // 当前结点是父节点的右结点时,往上进行查找下一个结点
            while (parentNode != null && currNode == parentNode.right) {
                currNode = parentNode;
                parentNode = parentNode.parent;
            }
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
