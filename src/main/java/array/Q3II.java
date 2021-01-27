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

public class Q3II {

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
    //  二分查找解法：
    // 算法思想:
    // 1. 将1 ~ n的数字从中间的数字m分为两部分, 分别为 1 ~ m 和 m + 1 ~ n
    // 2. 如果1 ~ m范围的数组在输入数组nums中出现的次数超过了m, 则 1 ~ m中一定存在重复数字; 否则, 重复
    // 数字出现了 m + 1 ~ n中
    // 3. 继续把包含了重复数字的数值范围一分为二, 直到找到重复数字
    public static int findRepeatNumberByBinarySearch(int[] nums) {
        // 二分查找
        // 基于[1, n]进行二分查找
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            // 计算[left, mid]在nums中的出现次数
            int count = countRange (nums, left, mid);
            // 如果出现次数大于 mid - left + 1, 则表示重复元素出现在[left, mid]之中
            // 否则出现在[mid + 1, right]中
            if (count > mid - left + 1)
                right = mid;
            else
                left = mid + 1;
        }

        return left;

    }

    // 计算[left, mid]在nums中的出现次数
    public static int countRange(int[] nums, int left, int right) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,5,3,1,2,6,7};
        System.out.println(findRepeatNumberByCopy(nums));
        System.out.println(findRepeatNumberByBinarySearch(nums));
    }
}
