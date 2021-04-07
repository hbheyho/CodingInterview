package S2Offer.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题48 - 最长不含重复字符的子字符串
 *               描述: 给请从字符串中找出一个最长的不包含重复字符的子字符串，
 *               计算该最长子字符串的长度。
 *               Case:
 *                   Input:  "abcabcbb"
 *                   OutPut: 3
 *               Limit: s.length <= 40000
 *               Remark: <LC 3> 无重复字符的最长子串
 * @CreateDate: 11:45 2021/2/13
 */

public class Q48 {

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 12:01 2021/2/13
     * @Params: null
     * @Returns:
    */
    // 算法思想:
    // 1. 状态定义：定义dp[i]：以s[i]结尾的最长不包含重复字符的子串长度
    // 2. 状态转移方程：固定右边界i, 设字符s[i]左边距离最近的相同字符为s[j], 即s[i] = s[j],
    // 那么可以得到如下状态转移方程:
    //      (1) 当 j < 0, 表示s[i]左边无相同字符, 则dp[i] = dp[i - 1] + 1
    //      (2) 当 dp[i - 1] < i - j, 说明字符s[j]在子字符串dp[i - 1]区间之外, 则 dp[i] = dp[i - 1] + 1
    //      (3) 当 dp[i - 1] >= i - j, 说明字符s[j]在字符串dp[i - 1]区间之内, 则 dp[i] = i - j
    // 其中(1), (2)可以合并成一个状态转移方程.
    // 3. 初始化: 使用一个Hash表统计在遍历过程中, 各个字符最后一次出现的索引位置
    // 4. 返回结果: max{dp}
    // 5. 状态压缩：通过使用temp变量保存前一个字符的不重复字符长度
    public int lengthOfLongestSubstringByDP(String s) {

        char[] chs = s.toCharArray();
        int n = chs.length;

        // 统计在遍历过程中, 各个字符最后一次出现的索引位置
        Map<Character, Integer> map = new HashMap<>();

        // res：保存结果; temp：保存前一个字符的不重复字符长度
        int res = 0, temp = 0;
        for (int i = 0; i < n; i++) {

            // 得到chs[i]左边距离最近的相同字符s[j]的下标
            int j = map.getOrDefault(chs[i], -1);
            // 更新chs[i]在当前遍历出现的最后一次位置
            map.put(chs[i], i);

            if (temp < i - j) {
                temp = temp + 1;
                res = Math.max(temp, res);
            } else {
                temp = i - j;
                res = Math.max(temp, res);
            }

        }

        return res;

    }


    /**
     * @Author: HB
     * @Description: 滑动窗口解法
     * @Date: 12:01 2021/2/13
     * @Params: null
     * @Returns:
    */
    public int lengthOfLongestSubstringBySlidingWindow(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        // 计算字符出现次数
        int[] counts = new int[256];
        // 定义窗口大小[left, right]
        int left = 0, right = -1, res = 0;;
        while (left < n) {

            if (right + 1 < n && counts[chs[right + 1]] < 1) {
                counts[chs[right + 1]]++;
                right++;
            } else {
                counts[chs[left]]--;
                left++;
            }

            res = Math.max(res, right - left + 1);

        }

        return res;
    }

    /**
     * @Author: HB
     * @Description: // 滑动窗口解法二
     * @Date: 12:02 2021/2/13
     * @Params: null
     * @Returns:
    */
    public int lengthOfLongestSubstringBySlidingWindow2(String s) {

        char[] chs = s.toCharArray();
        int n = chs.length;

        // 统计在遍历过程中, 各个字符最后一次出现的索引位置
        Map<Character, Integer> map = new HashMap<>();

        int res = 0, left = -1, right = 0;

        for (right = 0; right < n; right++) {

            // hash表中已经包含了字符chs[right], 更新left的位置,保证区间 [left + 1, right]内无重复字符且最大。
            if (map.containsKey(chs[right]))
                left = Math.max(left, map.get(chs[right]));

            // 更新chs[right]最后一次出现的索引
            map.put(chs[right], right);

            // 更新结果
            res = Math.max(res, right - left);

        }

        return res;

    }

}
