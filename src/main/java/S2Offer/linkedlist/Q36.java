package S2Offer.linkedlist;

/**
 * @Author: HB
 * @Description: 面试题36 - 二叉搜索树与双向链表
 *               描述: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任
 *               何新的结点，只能调整树中结点指针的指向。
 *               Case:
 *                   Input: 见《剑指Offer》
 *                   Output: 见《剑指Offer》
 *               Limit:
 *               Remark: 需要返回双向链表最左侧的节点。
 *                      <Lc> 436 将二叉搜索树转换为排序的双向链表
 *
 * @CreateDate: 17:12 2021/2/7
 */

public class Q36 {

    /**
     * Definition for a binary S2Offer.tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 中序遍历解法
     * @Date: 17:18 2021/2/7
     * @Params: null
     * @Returns:
    */
    // 中序遍历解法
    // pre: 指向前驱节点; head: 指向头节点;
    TreeNode pre = null, head = null;
    public TreeNode treeToDoublyList(TreeNode root) {
        dfs (root);
        return head;
    }

    public void dfs (TreeNode root) {
        // 1. 递归结束条件
        if (root == null)
            return;

        // 2. 递进操作
        dfs (root.left);

        // root节点指向其前序pre
        root.left = pre;
        // 当pre为null, 表示找到了头节点
        if (pre == null)
            head = root;
            // pre 节点指向其后继root
        else
            pre.right = root;

        // 更新pre节点
        pre = root;

        dfs (root.right);
    }
}
