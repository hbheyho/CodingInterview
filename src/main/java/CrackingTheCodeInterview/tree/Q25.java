package CrackingTheCodeInterview.tree;

/**
 * @Author: HB
 * @Description: 面试题04.02 - 最小高度树
 *               描述: 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 *               Case:
 *               Input: [-10,-3,0,5,9]
 *               Output: [0,-3,9,-10,null,5]
 *               Limit:
 *               Remark:
 * @CreateDate: 21:06 2021/4/19
 */

public class Q25 {

     /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 21:08 2021/4/19
     * @Params: null
     * @Returns:
    */
    // 要保证最小高度树, 那么可以递归从中间开始建造树节点
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST (nums, 0, nums.length - 1);
    }

    public TreeNode buildBST (int[] nums, int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST (nums, left, mid - 1);
        root.right = buildBST (nums, mid + 1, right);
        return root;
    }
}
