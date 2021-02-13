package algorithm;

/**
 * @Author: HB
 * @Description: 面试题50 - 第一个只出现一次的字符
 *               描述: 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
 *               s 只包含小写字母。
 *               Case:
 *                   Input: s = "abaccdeff"
 *                   OutPut: b
 *               Limit: 0 <= s 的长度 <= 50000
 *               Remark:
 * @CreateDate: 23:19 2021/2/13
 */

public class Q50 {

    /**
     * @Author: HB
     * @Description: Hash表解法
     * @Date: 23:20 2021/2/13
     * @Params: null
     * @Returns:
    */
    public char firstUniqChar(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[] counts = new int[26];
        // 统计每个字符串出现次数
        for (int i = 0; i < n; i++)
            counts[chs[i] - 'a']++;

        // 找到只出现一次的字符
        for (int i = 0; i < n; i++) {
            if (counts[chs[i] - 'a'] == 1)
                return chs[i];
        }
        return ' ';
    }
}
