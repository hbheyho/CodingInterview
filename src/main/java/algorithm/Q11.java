package algorithm;

/**
 * @Author: HB
 * @Description: 面试题11 - 旋转数组的最小数字
 *               描述: 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *               输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 *               例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *               Case:
 *               Input: [3,4,5,1,2]
 *               Output: 1
 *               Limit:
 *               Remark: <LeeCode 154> 寻找旋转排序数组中的最小值
 * @CreateDate: 22:20 2020/11/10
 */

public class Q11 {

    /**
     * @Author: HB
     * @Description: 二分查找解法
     * @Date: 20:54 2020/11/10
     * @Params: null
     * @Returns:
    */
    public static int minArrayByBinarySearch(int[] numbers) {
        // 称旋转数组的旋转分界点为旋转点
        int len = numbers.length;
        int left = 0, right = len - 1;

        while (left < right) {
            int mid = (right - left) / 2 + left;
            // 旋转点在[mid + 1, right]中
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
                // 旋转点在[left, mid]中
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
                // 面对 numbers[mid] == numbers[right], 缩减范围
            } else {
                right--;
            }
        }

        return numbers[left];
    }


    /**
     * @Author: HB
     * @Description: 二分查找解法二 - 剑指Offer 官方解法
     * @Date: 21:47 2020/11/10
     * @Params: null
     * @Returns:
    */
    public static int minArrayByBinarySearch2(int[] numbers) {
        // 处理输入数组长度 == 1的情况
        if(numbers.length == 1) return 1;

        int len = numbers.length;
        int left = 0, right = len - 1;
        int mid = left;
        // 以numbers[left] >= numbers[right]为判断条件是为了处理 1,2,3,4 此类特例
        while (numbers[left] >= numbers[right]) {
            // 此时left指向左有序数组的最后一个元素, right指向右有序数组的第一个元素
            if ((right - left) == 1) {
                // mid = right, 便于与返回结果一致
                mid = right;
                break;
            }
            mid = (right - left) / 2 + left;
            // 当left, right, mid三者元素值相等时, 无法判定最小值在中间节点的前面还是后面
            // 所以退化为顺序查找
            if (numbers[left] == numbers[right]
                    && numbers[left] == numbers[mid]) {
                return minValue(numbers, left, right);
            }
            // 中间节点大于左有序数组, 则代表最小值在中间后面, 且中间节点在左有序数组上
            if (numbers[mid] >= numbers[left]) {
                left = mid;
            }
            // 中间节点小于右有序数组, 则代表最小值在中间节点前面, 且中间节点在右有序数组上
            if (numbers[mid] <= numbers[right]) {
                right = mid;
            }
        }

        return numbers[mid];

    }

    // 找到最小值
    public static int minValue(int[] numbers, int left, int right) {
        int min = numbers[left];
        for (int i = left + 1; i <= right; i++) {
            if (min > numbers[i])
                min = numbers[i];
        }
        return min;
    }
    
    /**
     * @Author: HB
     * @Description: 二分查找解法三
     * @Date: 9:25 2021/1/17
     * @Params: null
     * @Returns: 
    */
    // 显而易见直接可以使用O(n)的时间复杂度求解, 但是绝对不是合理的答案, 需要想一个小于O(n)的时间复杂度算法
    // 题意：通过数组旋转的特性找出输入数组中的最小值
    // 二分查找: 未必一定要是升序序列, 只要对于一个序列, 存在那个一个性质可以输入序列分为两部分即可使用二分查找
    // 本题特性在于: 最小值的索引为i, 则最小值左边的元素nums[0, i -1]都大于等于nums[0], 而最小值右边的元素nums[i, nums.length - 1](包括自身)不满足这种特性
    // 那么我们就可以基于这个特性来完成二分查找
    // 1. 但要完成上述操作需要处理特殊情况, 即若最小值最右边的元素中存在与nums[0]相等的元素时, 需要先将其删除只有进行了这种额外的处理, 才能满足上述特性
    // 2. 另外要注意处理完全单调的情况, 即完成(1)步骤的数据预处理之后, 剩下的最后一个数大于等于第一个数, 则说明数组完全单调
    public static int minArrayByBinarySearch3(int[] nums) {

        int n = nums.length - 1;
        if (n < 0)
            return -1;

        // 1. 数据预先处理, 使得满足二分查找性质
        while (n > 0 && nums[n] == nums[0]) n--;

        // 2. 剩下的最后一个数大于等于第一个数, 则说明数组完全单调, 直接返回最后一个元素
        if (nums[n] >= nums[0]) return nums[0];

        // 3. 进行二分查找
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            // mid索引所在的位置满足上述所描述的特性
            if (nums[mid] >= nums[0]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

}
