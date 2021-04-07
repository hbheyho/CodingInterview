package S2Offer.array;

/**
 * @Author: HB
 * @Description: 面试题21 - 调整数组顺序使奇数位于偶数前面
 *               描述: 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 *               使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *               Case:
 *               Input: nums = [1,2,3,4]
 *               Output:[1,3,2,4]
 * @CreateDate: 23:30 2021/4/1
 */

public class Q21 {


    /**
     * @Author: HB
     * @Description: 双指针解法 - 快慢指针
     * @Date: 23:37 2021/4/1
     * @Params: null
     * @Returns:
    */
    public int[] exchange(int[] nums) {
        int n  = nums.length;
        // slow：下一个奇数应该存储的位置; fast：向前搜索奇数位置
        int slow = 0, fast = 0;
        while (fast < n) {
            // fast指向位置为奇数, 即和slow进行交换
            if ((nums[fast] & 1) != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
            fast++;
        }
        return nums;
    }


    /**
     * @Author: HB
     * @Description: 双指针解法 - 对撞指针
     * @Date: 23:37 2021/4/1
     * @Params: null
     * @Returns:
    */
    public int[] exchange2(int[] nums) {
        int n  = nums.length;
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < n && nums[i] % 2 != 0) {
                i++;
            }

            while (j >= 0 && nums[j] % 2 == 0) {
                j--;
            }

            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }
}
