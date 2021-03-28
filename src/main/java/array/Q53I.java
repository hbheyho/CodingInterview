package array;

/**
 * @Author: HB
 * @Description: 面试题53I - 数字在排序数组中出现的次数
 *               描述: 统计一个数字在排序数组中出现的次数。
 *               Case:
 *                   Input: nums = [5,7,7,8,8,10], target = 8
 *                   OutPut: 2
 *               Limit: 0 <= 数组长度 <= 50000
 *               Remark: <LC 34> 在排序数组中查找元素的第一个和最后一个位置
 * @CreateDate: 11:49 2021/3/28
 */

public class Q53I {

    /**
     * @Author: HB
     * @Description: 二分查找解法
     * @Date: 11:52 2021/3/28
     * @Params: null
     * @Returns:
    */
    // 分别查到开头位置和结尾位置即可
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        int start = binarySearchStart (nums, 0, nums.length - 1, target);
        int end = binarySearchEnd (nums, 0, nums.length - 1, target);
        if (nums[start] != target || nums[end] != target)
            return 0;
        return end - start + 1;
    }


    // 找到target的开头位置
    public int binarySearchStart (int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    // 找到target的结束位置
    public int binarySearchEnd (int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (nums[mid] <= target)
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

}
