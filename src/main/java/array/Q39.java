package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题39 - 数组中出现次数超过一半的数字
 *               描述: 输数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *                     你可以假设数组是非空的，并且给定的数组总是存在多数元素
 *               Case:
 *               Input: matrix = [1, 2, 3, 2, 2, 2, 5, 4, 2]
 *               Output: 2
 *               Limit： 1 <= 数组长度 <= 50000
 * @CreateDate: 16:44 2021/2/8
 */

public class Q39 {

    /**
     * @Author: HB
     * @Description: Hash表解法
     * @Date: 16:46 2021/2/8
     * @Params: null
     * @Returns:
    */
    // Hash表求解
    public int majorityElementByHash(int[] nums) {
        int n = nums.length;
        // key/value => 元素/元素出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >=  (n / 2) + 1)
                return entry.getKey();
        }

        return 0;
    }

    /**
     * @Author: HB
     * @Description: 摩尔投票法
     *               摩尔投票算法是基于事实：每次从序列里选择两个不相同的数字删除掉（或称为“抵消”）,
     *               最后剩下一个数字或几个相同的数字，就是出现次数大于总数一半的那个。
     * @Date: 16:46 2021/2/8
     * @Params: null
     * @Returns:
    */
    public int majorityElementByMoore(int[] nums) {
        int n = nums.length;
        int value = nums[0];
        // count: 字符出现次数
        int count = 1;
        for (int i = 1; i < n; i++) {
            // 已经完全抵消, 选择另外一个元素继续进行消除
            if (count == 0) {
                value = nums[i];
                count++;
                continue;
            }
            // 出现次数+1或消除
            count += (nums[i] == value ? 1 : -1);
        }
        return value;
    }


    /**
     * @Author: HB
     * @Description: 基于快速排序解法
     * @Date: 17:56 2021/2/8
     * @Params: null
     * @Returns:
    */
    // 基于快速排序思想解法
    // 思路：数组中有一个数字的出现次数超过数组长度的一半. 那么将这个元素排序之后,
    // 那么排序之后处于数组中间的数字一定就是那个出现次数超过数组长度一半的数字.
    // 统计学上的中位数, 即长度为n的数组中第n/2大的数字.
    public int majorityElementByQuickSort(int[] nums) {
        int mid = nums.length >> 1;
        int left = 0, right = nums.length - 1;
        // 进行分区操作, 返回分区之后的基准元素下标
        int index = partition(nums, left, right);
        // 判断是否选取到中位数
        while (mid != index) {
            // 中位数出现在左边
            if (index > mid) {
                right = index - 1;
                index = partition (nums, left, right);
            }
            // 中位数出现在右边
            else {
                left = index + 1;
                index = partition (nums, left, right);
            }
        }

        return nums[mid];
    }

    // 分区操作
    private int partition(int[] nums, int left, int right) {
        // 选择最左边的数为基准元素 - 可以随机选择一个元素作为基准元素, 加快运行速度
        int pivot = left;
        // 1. 定义变量index，其[pivot + 1,index)范围内的元素小于基准元素
        int index = pivot + 1;
        // 从 左->右开始扫描, 若基准元素值nums[pivot] > nums[i], 则进行
        // index 与 i 交换, 否则不进行交换, 保持不变   
        // 2. 定义变量i，其[index, i - 1]范围内的元素大于基准元素
        for (int i = index; i <= right; i++) {
            // 3. 触发index变量转移条件，保证循环不变量定义正确, 将等于 pivot 的值放置在右边
            if (nums[i] < nums[pivot]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }

        // 将基准元素放置在正确位置上
        int temp = nums[index - 1];
        nums[index - 1] = nums[pivot];
        nums[pivot] = temp;

        return index - 1;
    }

}
