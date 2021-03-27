package array;

/**
 * @Author: HB
 * @Description: 面试题57I - 和为S的两个数字
 *               描述: 输入一个递增排序的数组和一个数字s，在数组中查找两个数，
 *               使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *               Case:
 *                   Input: nums = [2,7,11,15], target = 9
 *                   OutPut: [2, 7]
 *               Limit: 1 <= nums.length <= 10^5
 *                      1 <= nums[i] <= 10^6.
 *               Remark:
 * @CreateDate: 12:03 2021/3/27
 */

public class Q57I {

    /**
     * @Author: HB
     * @Description: 二分查找解法 - O(nlogn)
     * @Date: 12:04 2021/3/27
     * @Params: null
     * @Returns:
    */
    public int[] twoSumByBinarySearch(int[] nums, int target) {
        int n = nums.length - 1;
        // 当前元素为i, 则从[i + 1, n - 1]中寻找符合条件的元素
        for (int i = 0; i < n; i++) {
            int left = i + 1, right = nums.length - 1, value = target - nums[i];
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (value < nums[mid]) {
                    right = mid - 1;
                } else if (value > nums[mid]) {
                    left = mid + 1;
                } else {
                    return new int[] {nums[i], value};
                }
            }
        }

        return new int[0];
    }

    /**
     * @Author: HB
     * @Description: 对撞指针解法 - 数组有序
     * @Date: '12:07' 2021/3/27
     * @Params: null
     * @Returns:
    */
    public int[] twoSumTwoPoints(int[] nums, int target) {

        int n = nums.length;
        int i = 0, j = n - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else {
                return new int[] {nums[i], nums[j]};
            }
        }

        return new int[0];
    }

    /**
     * @Author: HB
     * @Description: 暴力解法 - O(n ^ 2)超时, 但是可以从后往前遍历, 以达到剪枝效果
     * @Date: 12:08 2021/3/27
     * @Params: null
     * @Returns:
    */
    public int[] twoSumByForce(int[] nums, int target) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > target)
                continue;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > target - nums[i])
                    continue;
                if (nums[i] + nums[j] == target)
                    return new int[]{nums[i], nums[j]};
            }
        }
        return new int[0];
    }

}
