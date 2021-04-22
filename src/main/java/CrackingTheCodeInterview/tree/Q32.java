package CrackingTheCodeInterview.tree;

/**
 * @Author: HB
 * @Description: 面试题04.10 - 检查子树
 *               描述:  检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，
 *                      判断 T2 是否为 T1 的子树。
 *                      如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n
 *                      处把树砍断，得到的树与 T2 完全相同。
 *               Case:
 *                  Input: t1 = [1, 2, 3], t2 = [2]
 *                  Output: true
 *               Limit:
 *               Remark:
 * @CreateDate: 20:56 2021/4/22
 */

public class Q32 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 双重递归解法
     * @Date: 20:58 2021/4/22
     * @Params: null
     * @Returns:
    */
    // 1. 递归判断t1的的node节点开始的子树是否和t2子树相等;
    // 2. 若不相等, 在继续递归判断t1的node.left节点开始的子树是否和t2子树相等, 在继续递归判断node2.right节点开始的子树是否和t2子树相等
    public boolean checkSubTreeByRecursion(TreeNode t1, TreeNode t2) {
        if (t2 == null)
            return true;
        if (t1 == null)
            return false;
        return isEqual (t1, t2) || checkSubTreeByRecursion (t1.left, t2) || checkSubTreeByRecursion (t1.right, t2);
    }

    // isEqual: 判断node节点和t2节点开始的两个子树是否相等
    public boolean isEqual (TreeNode node, TreeNode t2) {
        if (node == null && t2 == null)
            return true;
        if ((node == null && t2 != null) || (node != null && t2 == null) || (node.val != t2.val))
            return false;
        return isEqual(node.left, t2.left) && isEqual(node.right, t2.right);
    }

    /**
     * @Author: HB
     * @Description: 前序遍历 + KMP算法
     * @Date: 20:58 2021/4/22
     * @Params: null
     * @Returns:
    */
    // 前序遍历解法, 先序遍历能够保存二叉树的结构和数据, 所以若t2是t1的子树, 则 t1.preorder.contains(t2.preorder), 在前序遍历时, 需要把null也存储起来
    // 该题后序遍历也行, 都能够唯一的表征一颗树, 中序遍历不行
    // 最后找子序的过程, 可以使用KMP算法
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        StringBuilder s = new StringBuilder(), p = new StringBuilder();
        preorder(t1, s);
        preorder(t2, p);
        return kmp(s.toString(), p.toString());
    }

    public void preorder (TreeNode root, StringBuilder s) {
        if (root == null) {
            s.append("null");
            return;
        }
        s.append(root.val);
        preorder (root.left, s);
        preorder (root.right, s);
    }

    public boolean kmp (String s, String p) {
        int n = s.length(), m = p.length();
        s = " " + s;
        p = " " + p;
        char[] chsS = s.toCharArray();
        char[] chsP = p.toCharArray();

        // next[i] = j: 保存了P[1, i]的前缀和后缀的相等最大长度
        int[] next = new int[m + 1];
        // 计算next数组
        // 只有一个字符时, 其前缀和后缀的相等的最大长度为0
        next[1] = 0;
        for (int i = 2, j = 0; i <= m; i++) {
            while (j != 0 && chsP[i] != chsP[j + 1])
                j = next[j];

            if (chsP[i] == chsP[j + 1])
                j++;
            next[i] = j;
        }

        // 匹配过程
        for (int i = 1, j = 0; i <= n; i++) {
            while (j != 0 && chsS[i] != chsP[j + 1])
                j = next[j];
            if (chsS[i] == chsP[j + 1])
                j++;
            if (j == m)
                return true;
        }
        return false;
    }
}
