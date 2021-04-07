package S2Offer.array;

/**
 * @Author: HB
 * @Description: 面试题53III - 数组中数值和下标相等的元素
 *               描述: 假设一个单调递增的数组里的每个元素都是整数并且是唯一的。
 *                     请编程实现一个函数找出数组中任意一个数值等于其下标的元素。
 *                     例如，在数组[-3, -1, 1, 3, 5]中，数字3和它的下标相等。
 *                     若不存在，则返回-1。
 *               Case:
 *                   Input: [-3, -1, 1, 3, 5]
 *                   OutPut: 3
 *               Limit:
 *               Remark:
 * @CreateDate: 16:55 2021/2/17
 */

public class Q53III {

    /**
     * @Author: HB
     * @Description: 二分查找解法
     * @Date: 16:59 2021/2/17
     * @Params: null
     * @Returns:
    */
    // 元素都是单调递增, 尝试二分查找法求解
    // [-3, -1, 1, 3, 5]   当nums[mid] < mid时,[nums[i]都小于mid, mid ,
    // nums[i]大于等于mid]
    //   0   1  2  3  4
    //         mid
    public int getNumberSameAsIndex(int[] nums) {
        int index = binarySearch(nums, 0, nums.length - 1);
        if (index == nums[index])
            return index;
        else
            return -1;
    }

    public int binarySearch(int[] nums, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= mid){
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
