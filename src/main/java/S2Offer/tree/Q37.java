package S2Offer.tree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题37 - 序列化二叉树
 *               描述: 请实现两个函数，分别用来序列化和反序列化二叉树。
 *               Case:
 *                   Input:     1
 *                            /   \
 *                           2     3
 *                               / \
 *                              4  5
 *                   OutPut: [1,2,3,null,null,4,5]
 *               Limit:
 *               Remark: 序列化格式未必需要时层次遍历格式, 也可以是其他格式
 *                      <Lc> 297 二叉树的序列化与反序列化
 * @CreateDate: 21:44 2021/2/7
 */

public class Q37 {
      /* Definition for a binary S2Offer.tree node.*/
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 前序遍历解法 - 借助前序遍历来实现序列化和反序列化
     * @Date: 14:31 2021/2/8
     * @Params: null
     * @Returns:
    */
    // Encodes a S2Offer.tree to a single S2Offer.string.
    static String serializeByPreOrder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        serializeDFS (root, sb);
        // 取出序列化字符的最后一个','
        sb.deleteCharAt(sb.length() - 1);
        return sb.append("]").toString();
    }

    private static void serializeDFS (TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("$").append(",");
            return;
        }
        // 将上',' 以便区分不同的节点值
        sb.append(node.val).append(",");
        serializeDFS (node.left, sb);
        serializeDFS (node.right, sb);
    }

    // Decodes your encoded data to S2Offer.tree.
    static TreeNode deserializeByPreOrder(String data) {
        // 进行字符串分割
        String[] strs = data.substring(1, data.length() - 1).split(",");
        // 进行反序列化操作
        return deserializeDFS (strs);
    }

    // 指示待反序列化字符串的访问下标
    private static int index = 0;
    private static TreeNode deserializeDFS(String[] strs) {

        // 1. 递归结束条件
        if ("$".equals(strs[index]))
            return null;

        // 2. 递进操作
        // 创建新节点
        TreeNode node = new TreeNode(Integer.valueOf(strs[index]));
        index++;
        node.left = deserializeDFS(strs);
        index++;
        node.right = deserializeDFS(strs);

        // 3. 递归返回值
        return node;
    }


    /**
     * @Author: HB
     * @Description: 层次遍历解法 - 借助层次遍历来实现序列化和反序列化
     * @Date: 15:32 2021/2/8
     * @Params: null
     * @Returns:
    */
    // 采用层次遍历来序列号和反序列化二叉树
    // Encodes a S2Offer.tree to a single S2Offer.string.
    public String serializeByLevelOrder(TreeNode root) {
        if (root == null)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null)
                sb.append(node.val).append(",");
            else {
                sb.append("$,");
                continue;
            }
            queue.offer(node.left);
            queue.offer(node.right);
        }

        // 取出序列化字符的最后一个','
        sb.deleteCharAt(sb.length() - 1);
        return sb.append("]").toString();

    }

    // Decodes your encoded data to S2Offer.tree.
    public TreeNode deserializeByLevelOrder(String data) {
        if ("[]".equals(data))
            return null;

        // 进行字符串分割
        String[] strs = data.substring(1, data.length() - 1).split(",");
        // 使用队列来存储构建节点
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        queue.add(root);

        // 进行反序列化操作
        // 指针i指向当前构建节点的左右孩子
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!"null".equals(strs[i])) {
                node.left = new TreeNode(Integer.parseInt(strs[i]));
                queue.add(node.left);
            }
            i++;
            if (!"null".equals(strs[i])) {
                node.right = new TreeNode(Integer.parseInt(strs[i]));
                queue.add(node.right);
            }
            i++;
        }

        return root;

    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(-7);
        root.right = new TreeNode(-3);
        deserializeByPreOrder( serializeByPreOrder(root));
    }
}
