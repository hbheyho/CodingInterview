package array;


/**
 * @Author: HB
 * @Description: 面试题03OII - 不修改数组找出重复的数字
 *               描述: 在一个长度为 n + 1的数组 nums 里的所有数字都在 0～n 的范围内。所以数组中至少有一个数字是重复的。
 *               请找出数组中任意一个重复的数字, 但不能修改输入的数组。
 *               Case:
 *                   Input: [2,3,5,4,3,2,6,7]
 *                   Output: 2/3
 * @CreateDate: 15:15 2020/10/15
 */

public class Q3 {

    /**
     * @Author: HB
     * @Description: 数组复制
     *               时间复杂度：O(N)
     *               空间复杂度：O(1)
     * @Date: 15:17 2020/10/15
     * @Params: null
     * @Returns:
    */
    public static int findRepeatNumberByCopy(int[] nums) {
        // 开辟一个辅助数字
        int[] temp = new int[nums.length];
        for (Integer value : nums) {
            if (value == temp[value]) {
                return value;
            }
            temp[value] = value;
        }
        return 0;
    }

    /**
     * @Author: HB
     * @Description: 基于二分查找的解法 - 迭代解法
     *               时间复杂度：O(NlogN)
     *               空间复杂度：O(1)
     * @Date: 15:19 2020/10/15
     * @Params:
     * @Returns:
    */
    public static int findRepeatNumberByBinarySearch(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        int middle = 0;
        while (start <= end) {
            // 不直接使用 (end + start) / 2 是为了防止int类型的溢出
            middle = (end - start) / 2 + start;
            // 计算出现次数
            int count = countRange (nums, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                }else {
                    break;
                }
            }
            // 若 [start,middle]范围的数字在数组中出现的次数 大于 (middle - start) + 1, 则代表重复
            // 数字出现在[start,middle]中, 否则重复数字出现在[middle+1, end]中
            if (count > (middle - start + 1)){
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return 0;
    }

    // 统计start 至 middle 在数组出出现的
    private static int countRange (int[] nums, int start, int middle) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= start && nums[i] <= middle) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,5,3,1,2,6,7};
        System.out.println(findRepeatNumberByCopy(nums));
        System.out.println(findRepeatNumberByBinarySearch(nums));
    }
}
