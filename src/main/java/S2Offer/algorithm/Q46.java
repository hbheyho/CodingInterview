package S2Offer.algorithm;

/**
 * @Author: HB
 * @Description: 面试题46 - 把数字翻译成字符串
 *               描述: 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，
 *               1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
 *               请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *               Case:
 *                   Input:  12258
 *                   OutPut: 5, 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *               Limit: 0 <= num < 231
 *               Remark:
 * @CreateDate: 11:56 2021/2/9
 */

public class Q46 {

    /**
     * @Author: HB
     * @Description:  动态规划解法
     * @Date: 10:50 2021/2/12
     * @Params: null
     * @Returns:
    */
    // 算法思路:
    // 1. 状态定义：最后要返回的结果为一个数字的翻译方法数量, 那么定义状态为 dp[i]: 到达下标i处所拥有的翻译方法数量;
    // 2. 状态转移方程: 将数字num转换为字符数组, 那么当到达下标i处时, num[i]可能被翻译为字母(char)(num[i] + 'a'), 或者和 num[i- 1]组合翻译成字母(char)(num[i - 1] * 10 + num[i] + 'a'), 那么可以得到状态转移方程为:
    // dp[i] = dp[i - 1] + dp[i - 2]  (num[i - 1] * 10 + num[i] <= 25)
    // dp[i] = dp[i - 1]  (num[i - 1] * 10 + num[i] > 25 或 nums[i - 1] == '0')
    // 3. 初始化：dp[0] = 1, dp[1] = 2 (num[i - 1] * 10 + num[i] <= 25) / dp[1] = 1 (num[i - 1] * 10 + num[i] > 25)
    // 4. 结果: dp[num.length - 1];
    // 5. 特殊处理：当nums[i - 1]为0时,  dp[i] = dp[i - 1]. 例如 num=506, 06不能翻译为 g
    public int translateNumByDP(int num) {

        char[] nums = String.valueOf(num).toCharArray();
        int len = nums.length;
        if (len < 2)
            return len;

        // 创建dp数组
        int[] dp = new int[len];

        // 初始化
        dp[0] = 1;
        dp[1] = (nums[0] - '0') * 10 + (nums[1] - '0') > 25 ? 1 : 2;

        // 填充dp数组
        for (int i = 2; i < len; i++) {
            if (nums[i - 1] == '0' || (nums[i - 1] - '0') * 10 + (nums[i] - '0') > 25)
                dp[i] = dp[i - 1];
            else
                dp[i] = dp[i - 1] + dp[i - 2];

        }

        return dp[len - 1];

    }


    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 10:51 2021/2/12
     * @Params: null
     * @Returns:
    */
    // 下标i处的状态，依靠i - 1和i - 2处的状态，所有可以进行状态压缩。
    public int translateNumByStateCompression (int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int len = nums.length;
        if (len < 2)
            return len;

        // 初始化
        int prePreCount = 1;
        int preCount = (nums[0] - '0') * 10 + (nums[1] - '0') > 25 ? 1 : 2;
        // 填充dp数组
        for (int i = 2; i < len; i++) {
            int count = 0;
            if (nums[i - 1] == '0' || (nums[i - 1] - '0') * 10 + (nums[i] - '0') > 25) {
                count = preCount;
                prePreCount = preCount;
                preCount = count;
            }
            else {
                count = preCount + prePreCount;
                prePreCount = preCount;
                preCount = count;
            }

        }

        return preCount;

    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 11:31 2021/2/12
     * @Params: null
     * @Returns:
    */
    public int translateNumByRecursion(int num) {
        if (num <= 9)
            return 1;
        // 获取输入数字的余数, 然后递归计算翻译方法
        int rm = num % 100;
        // 如果余数小于等于9或者大于等于26, 则余数不能按照2位数翻译, 06不能被组合翻译
        if (rm <= 9 || rm >= 26)
            return translateNumByRecursion(num / 10);
            // rms属于 [10, 25]时, 即可以分别翻译也可以组合翻译
        else
            return translateNumByRecursion(num / 10) + translateNumByRecursion(num / 100);
    }


    /**
     * @Author: HB
     * @Description:  剑指offer》 官方解法
     * @Date: 12:01 2021/2/12
     * @Params: null
     * @Returns:
    */
    public int translateNumByOffer(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int n = nums.length;
        if (n < 2)
            return n;

        // counts[i]: 表示从下标i处字符开始所拥有的翻译个数
        int[] counts = new int[n];
        counts[n - 1] = 1;
        counts[n - 2] = 10 <= (nums[n - 2] - '0') * 10 + (nums[n - 1] - '0')
                && (nums[n - 2] - '0') * 10 + (nums[n - 1] - '0') <= 25 ? 2 : 1;

        for (int i = n - 3; i >= 0; i--) {

            int combination = (nums[i] - '0') * 10 + (nums[i + 1] - '0');

            if (combination >= 10 && combination <= 25) {
                counts[i] = counts[i + 1] + counts[i + 2];
            } else {
                counts[i] = counts[i + 1];
            }

        }

        return counts[0];
    }


}
