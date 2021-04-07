package S2Offer.tree;

/**
 * @Author: HB
 * @Description: 面试题33 - 二叉搜索树的后序遍历序列
 *               描述: 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 *               如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *               Case:
 *                   Input:  [1,3,2,6,5]
 *                   OutPut: true
 *               Limit: 数组长度 <= 1000
 *               Remark:
 * @CreateDate: 21:00 2021/2/2
 */

public class Q33 {

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 21:01 2021/2/2
     * @Params: null
     * @Returns:
    */
    // 结合后序遍历和二叉搜索树的特性可知: [左子树|右子树|根节点],
    // 其中根节点的值大于左子树的值, 根节点的值小于右子树的值;
    public boolean verifyPostorder(int[] postorder) {
        return verify (postorder, 0, postorder.length - 1);
    }

    // 二叉搜索树验证操作
    public boolean verify (int[] postorder,int left, int right) {

        // 1. 递归结束条件
        if (left >= right)
            return true;

        // 2. 递进操作
        // 当前根节点值
        int root = postorder[right];

        // 在二叉搜索树中根节点的值大于左子树的值, 找到左右子树分界线
        int i = left;
        while (postorder[i] < root) {
            i++;
        }

        // 在二叉搜索树中根节点的值小于右子树的值
        int j = i;
        while (j < right) {
            // 不满足二叉搜索树定义, 直接返回false
            if (postorder[j] < root)
                return false;
            j++;
        }

        // 3. 递归返回值
        // 继续递归判断左子树和右子树是不是二叉搜索树
        return verify (postorder, left, i - 1) && verify (postorder, i, right - 1);

    }
}
