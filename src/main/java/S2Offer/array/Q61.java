package S2Offer.array;

import java.util.Arrays;

/**
 * @Author: HB
 * @Description: 面试题61 - 扑克牌中的顺子
 *               描述: 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌
 *               是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，
 *               而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *               Case:
 *                   Input: [0,0,1,2,5]
 *                   OutPut: true
 *               Limit: 数组长度为 5
 *                      数组的数取值为 [0, 13] .
 *               Remark:
 * @CreateDate: 15:29 2021/2/20
 */

public class Q61 {

    /**
     * @Author: HB
     * @Description: 双指针解法
     * @Date: 15:31 2021/2/20
     * @Params: null
     * @Returns:
    */
    // 算法思路：
    // 1. 统计牌中0的个数, 因为0可以看成是任意数字
    // 2. 元素值相等直接返回false
    // 3. 计算两个牌直接的差值, 若差值等于1, 则跳过继续计算; 若差值大于1, 则使用0来两张牌之间的差值
    // 3. 若剩下的0的个数大于等于0, 则表示是顺子, 否则不是顺子
    public boolean isStraight(int[] nums) {
        int n = nums.length;

        // 预先进行排序 - 排序数组长度为14, 可以使用计数排序替换普通排序
        Arrays.sort(nums);

        int zeroCount = 0;
        for (int i = 0; i < n; i++) {

            // 统计牌中0的个数
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }

            int j = i + 1;
            // 元素值相等直接返回false
            if (j < n && nums[j] == nums[i])
                return false;

            // 相邻两个元素之差相差大于1, 使用0进行抵消
            if (j < n && nums[j] - nums[i] > 1) {
                zeroCount -= (nums[j] - nums[i] - 1);
            }

        }

        return zeroCount >= 0;
    }

}
