package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: HB
 * @Description: 面试题03 - 数组中重复的数字
 *               描述: 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 *               但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。 2 <= n <= 100000
 *               Case:
 *                   Input: [2,3,1,0,2,5,3]
 *                   Output: 2/3
 * @CreateDate: 15:15 2020/10/14下·
 */

public class Q3II {

    /**
     * @Author: HB
     * @Description: 哈希表解法
     *               时间复杂度：O(N)
     *               空间复杂度：O(N)
     * @Date: 15:17 2020/10/14
     * @Params: null
     * @Returns:
    */
    public int findRepeatNumberByHashTable(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // 若插入失败, 则代表哈希表中已经右重复元素, 则直接返回
            if (!set.add(nums[i])){
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * @Author: HB
     * @Description: 排序 + 双指针
     *               时间复杂度：O(NlogN)
     *               空间复杂度：O(1)
     * @Date: 15:18 2020/10/14
     * @Params: null
     * @Returns:
    */
    public int findRepeatNumberBySort(int[] nums) {
        // 先对数组进行排序 - 使用快排排序进行
        // sort(nums);
        Arrays.sort(nums);
        // 对于已排序的元素进行遍历, 找到相同元素
        for (int i = 0; i < nums.length - 1; i++) {
            int j = i + 1;
            if (nums[i] == nums[j])
                return nums[i];
        }
        return 0;
    }

    /**
     * @Author: HB
     * @Description: 原地解法
     *               时间复杂度：O(N)
     *               空间复杂度：O(1)
     * @Date: 15:19 2020/10/14
     * @Params:
     * @Returns:
    */
    public int findRepeatNumbeByInPlacer(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            // 若nums[i] 不等于i, 则代表nums[i]不在正确位置上
            while (nums[i] != i) {
                // 将nums[i] 与 nums[nums[i]]比较, 若相等, 则代表找到了重复的元素, 否则进行交换
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                } else {
                    // 将nums[i] 放在属于他的位置上
                    int temp = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return 0;
    }
}
