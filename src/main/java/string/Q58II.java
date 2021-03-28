package string;

/**
 * @Author: HB
 * @Description: 面试题58I - 左旋转字符串
 *               描述:字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 *               请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，
 *               该函数将返回左旋转两位得到的结果"cdefgab"。
 *               Case:
 *                   Input: s = "abcdefg", k = 2
 *                   Output: "cdefgab"
 *               Limit: 1 <= k < s.length <= 10000
 *               Remark：
 * @CreateDate: 11:07 2021/3/28
 */

public class Q58II {
    /**
     * @Author: HB
     * @Description:  三次翻转解法
     * @Date: 11:08 2021/3/28
     * @Params: null
     * @Returns:
    */
    // 将字符串看成两部分：例如 "abcdefg", k = 2, 看成 ab 和 cdefg
    // 1. 分别对上述两个部分进行翻转, bagfedc
    // 2. 在对整个字符串进行翻转, cdefgab
    public String reverseLeftWordsByThreeReverse(String s, int n) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        if (len == 0)
            return s;
        reverseChars(chs, 0, n - 1);
        reverseChars(chs, n, len - 1);
        reverseChars(chs, 0, len - 1);
        return String.valueOf(chs);
    }

    // 翻转i, j范围的字符
    public void reverseChars(char[] chs, int i , int j) {
        while (i < j) {
            char temp = chs[i];
            chs[i] = chs[j];
            chs[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * @Author: HB
     * @Description: 一次遍历解法
     * @Date: 11:09 2021/3/28
     * @Params: null
     * @Returns:
    */
    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < len; i++) {
            sb.append(s.charAt(i));
        }

        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

}
