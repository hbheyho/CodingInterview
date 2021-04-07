package CrackingTheCodeInterview.string;

/**
 * @Author: HB
 * @Description: 面试题01.01 - 判断字符是否唯一
 *               描述: 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *               Case:
 *               Input: s = "leetcode"
 *               Output: false
 *               Limit: 0 <= len(s) <= 100
 *               Remark:
 * @CreateDate: 14:01 2021/4/7
 */

public class Q1 {

    /**
     * @Author: HB
     * @Description: 位运算解法
     * @Date: 14:02 2021/4/7
     * @Params: null
     * @Returns:
    */
    // 题目中所给字符串示例都为小写字符串
    // 位运算解法 - 使用一个int变量代替数组来进行26个的标识
    // 例如ch = 'c', 则 index = 'c' - 'a' = 2, 则当出现'c'时, 将mark标志的二进制形式中第三位置为1
    public boolean isUniqueByBit(String astr) {
        char[] chs = astr.toCharArray();
        int mark = 0;
        for (char ch : chs) {
            int index = ch - 'a';
            if ((mark >>> index & 1) == 1)
                return false;
            else
                mark = mark | 1 << index;
        }
        return true;
    }

    /**
     * @Author: HB
     * @Description: 数组解法
     * @Date: 14:02 2021/4/7
     * @Params: null
     * @Returns:
    */
    public boolean isUniqueByArray(String astr) {
        char[] chs = astr.toCharArray();
        int len = chs.length;
        int[] count = new int[128];
        for (int i = 0; i < len; i++) {
            if ('A' <= chs[i] && chs[i] <= 'Z') {
                if (count[chs[i] - 'A'] > 0)
                    return false;
                count[chs[i] - 'A']++;
            } else {
                if (count[chs[i] - 'a'] > 0)
                    return false;
                count[chs[i] - 'a']++;
            }
        }
        return true;
    }
}

