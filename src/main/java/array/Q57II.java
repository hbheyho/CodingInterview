package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HB
 * @Description: 面试题57II - 和为S的连续正整数
 *               描述: 输入一个正整数 target ，输出所有和为 target 的连续正整数序列
 *               （至少含有两个数）。
 *                序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *               Case:
 *                   Input: target = 9
 *                   OutPut: [[2,3,4],[4,5]]
 *               Limit: 1 <= target <= 10^5
 *               Remark:
 * @CreateDate: 17:56 2021/3/27
 */

public class Q57II {

    /**
     * @Author: HB
     * @Description: 求子区间问题 - 滑动窗口解法
     * @Date: 17:57 2021/3/27
     * @Params: null
     * @Returns:
    */
    public int[][] findContinuousSequenceBySlidingWindow(int target) {

        List<int[]> result = new ArrayList<>();

        // 定义滑动窗口left = 1, right = 1, [left, right)
        int left = 1, right = 1;
        // 定义sum为窗口元素之和
        int sum = 0;
        // left移动到target / 2即可结束, 因为后面[target / 2 + 1, ..]之和都会大于target
        while (left <= target / 2) {
            // 扩大窗口
            if (sum < target) {
                sum += right;
                right++;
            }
            // 缩小窗口
            else if (sum > target) {
                sum -= left;
                left++;
            } else {
                int[] temp = new int[right - left];
                for (int i = left; i < right; i++) {
                    temp[i - left] = i;
                }
                result.add(temp);
                // 左边界向右移动
                sum -= left;
                left++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     * @Author: HB
     * @Description: 前缀和解法
     * @Date: 17:57 2021/3/27
     * @Params: null
     * @Returns:
    */
    // 把target看成1 ~ target
    // 例如target = 9, 则[1, 2, 3, 4, 5, 6, 7, 8, 9]
    // 1. 因为所要找的区间子数组和, 所以首先计算前缀和
    // 2. 根据计算得到的前缀和找到满足条件的区间
    public int[][] findContinuousSequenceByForce(int target) {

        List<int[]> result = new ArrayList<>();

        // preSum[i]: 前i个元素之和
        int[] preSum = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            preSum[i] = preSum[i - 1] + i;
        }

        // 找到符合条件的子序列 - 需要保证子数组个数大于或等于2
        for (int i = 0; i < target; i++) {
            for (int j = i + 2; j < target; j++) {
                // 得到原数组中nums[i, j - 1]之间的子数组元素和
                int subSum = preSum[j] - preSum[i];
                // 达到剪枝效果
                if (subSum > target)
                    break;
                if (subSum == target) {
                    int[] subNums = new int[j - i];
                    int index = 0;
                    for(int k = i + 1; k <= j; k++) {
                        subNums[index++] = k;
                    }
                    result.add(subNums);
                }
            }
        }

        // 二维数组声明只需要声明行就可以
        int[][] answer = new int[result.size()][];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

}
