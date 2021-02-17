package array;

/**
 * @Author: HB
 * @Description: 面试题53II - 0 ~ n - 1中缺失的数字
 *               描述: 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
 *               并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且
 *               只有一个数字不在该数组中，请找出这个数字。
 *               Case:
 *                   Input: [0,1,2,3,4,5,6,7,9]
 *                   OutPut: 8
 *               Limit: 0 < nums.length <= 10000
 *               Remark:
 * @CreateDate: 16:25 2021/2/17
 */

public class Q53II {
    // 数组长度为0 ～ n - 1, 且所有元素范围都在0 ～ n - 1之内, 找出不在0 ～ n - 1递增数组中的数字
    // 暴力解法 - O(n)
    public int missingNumberByForce(int[] nums) {
        int n = nums.length + 1;
        int res = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (res != nums[i]) {
                break;
            }
            res--;
        }
        return res;
    }


    /**
     * @Author: HB
     * @Description: 二分差值解法
     * @Date: 16:27 2021/2/17
     * @Params: null
     * @Returns:
    */
    // 数组长度为0 ～ n - 1, 且所有元素范围都在0 ～ n - 1之内,
    // 找出不在0 ～ n - 1递增数组中的数字
    // 二分查找解法 - O(logn)
    // [0,1,3,4,5,6,7,8]  => [元素值和下标对应|原始值和下标不对应]
    //  0 1 2 3 4 5 6 7
    public int missingNumberByBinarySearch(int[] nums) {
        // 注意: 需要将二分查找的right设置为nums.length
        return binarySearch (nums, 0, nums.length);
    }

    public int binarySearch(int[] nums, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid != nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }



}
