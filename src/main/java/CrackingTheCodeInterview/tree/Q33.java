package CrackingTheCodeInterview.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题04.12 - 求和路径
 *               描述:  给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
 *               设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
 *               注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 *               Case:
 *                  Input: 给定如下二叉树，以及目标和 sum = 22，
 *                                   5
 *                                 /  \
 *                                4    8
 *                               /    / \
 *                              11   13  4
 *                             / \     /  \
 *                            7  2    5   1
 *                  Output: 3 和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 *               Limit:
 *               Remark:
 * @CreateDate: 22:16 2021/4/22
 */

public class Q33 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @Author: HB
     * @Description: 前缀和解法
     * @Date: 22:19 2021/4/22
     * @Params: null
     * @Returns:
    */
    // 前缀和： 在到达当前元素的路径上, 所有元素之和
    // 算法思路：当到达一个节点B时, 此时前缀和为currSum, 则向前查找前缀和为 currSum - target的元素个数,
    // 若找到一个节点A, 则 A -> B上的路径之和便为为target
    public int pathSumByPreSum(TreeNode root, int sum) {
        // key/value => 前缀和/前缀和出现次数
        Map<Integer, Integer> sumMap = new HashMap<>();
        // 初始化前缀和出现次数为1
        sumMap.put(0, 1);
        return recursionBySum (root, sum, sumMap, 0);
    }

    // recursionBySum: 计算以 (node节点值结尾的前缀和 - target) 所能够找到的前缀和个数
    public int recursionBySum (TreeNode node, int target, Map<Integer, Integer> sumMap, int currSum) {

        if (node == null)
            return 0;

        // 保存结果
        int res = 0;

        // 计算当前前缀和
        currSum += node.val;
        // 向前查找符合条件的前缀和个数
        res += sumMap.getOrDefault(currSum - target, 0);
        // 更新前缀和个数
        sumMap.put (currSum, sumMap.getOrDefault(currSum, 0) + 1);

        // 去左右子树继续递归寻找
        res += recursionBySum (node.left, target, sumMap, currSum);
        res += recursionBySum (node.right, target, sumMap, currSum);

        // 回溯, 防止当前节点的前缀和影响上一层
        sumMap.put(currSum, sumMap.get(currSum) - 1);

        return res;
    }

    /**
     * @Author: HB
     * @Description: 双重递归解法：
     * @Date: 22:19 2021/4/22
     * @Params: null
     * @Returns:
    */
    // 1. 依次递归遍历root节点和其左右节点, 统计从当前节点开始的符合条件的求和路径
    // 2. 从当前node节点开始, 存在和为sum的路径条数
    // pathSum: 依次递归遍历root节点和其左右节点, 统计从当前节点开始的符合条件的求和路径
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        return isHasPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    // isHashPath: 从当前node节点开始, 存在和为sum的路径条数
    public int isHasPath (TreeNode node, int sum) {
        if (node == null)
            return 0;

        sum = sum - node.val;
        int count = (sum == 0 ? 1 : 0);

        count += isHasPath (node.left, sum);
        count += isHasPath (node.right, sum);

        return count;

    }
}
