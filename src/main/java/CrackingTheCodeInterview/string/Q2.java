package CrackingTheCodeInterview.string;

/**
 * @Author: HB
 * @Description: 面试题01.02 - 判断是否为字符串重排
 *               描述: 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串
 *               的字符重新排列后，能否变成另一个字符串。
 *               Case:
 *               Input: s1 = "abc", s2 = "bca"
 *               Output: true
 *               Limit: 0 <= len(s1) <= 100
 *                      0 <= len(s2) <= 100
 *               Remark:
 * @CreateDate: 14:08 2021/4/7
 */

public class Q2 {

    /**
     * @Author: HB
     * @Description: 数组解法
     * @Date: 14:09 2021/4/7
     * @Params: null
     * @Returns:
    */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int[] count = new int[26];
        int i = 0;
        while (i < s1.length()) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
            i++;
        }
        for (int j = 0; j < 26; j++) {
            if (count[j] != 0)
                return false;
        }
        return true;
    }

}
