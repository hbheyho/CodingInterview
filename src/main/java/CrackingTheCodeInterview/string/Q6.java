package CrackingTheCodeInterview.string;

/**
 * @Author: HB
 * @Description: 面试题01.06 - 字符串压缩
 *               描述: 字符串压缩。利用字符重复出现的次数，编写一种方法，
 *               实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。
 *               若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中
 *               只包含大小写英文字母（a至z）。
 *               Case:
 *               Input: "aabcccccaaa"
 *                      "a2b1c5a3"
 *               Output: true
 *               Limit: 字符串长度在[0, 50000]范围内
 *               Remark:
 * @CreateDate: 11:48 2021/4/9
 */

public class Q6 {

    /**
     * @Author: HB
     * @Description: 双指针解法
     * @Date: 11:49 2021/4/9
     * @Params: null
     * @Returns:
    */
    public String compressStringByTwoPoints(String S) {
        char[] chs = S.toCharArray();
        int len = chs.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int j = i;
            while (j < len && chs[j] == chs[i]) {
                j++;
            }
            sb.append(chs[i]);
            sb.append(j - i);
            i = j - 1;
        }

        return sb.length() < len ? sb.toString() : S;
    }

    /**
     * @Author: HB
     * @Description: 栈解法
     * @Date: 11:50 2021/4/9
     * @Params: null
     * @Returns:
    */
    public String compressStringByStack(String S) {
        char[] chs = S.toCharArray();
        int len = chs.length;
        StringBuilder sb = new StringBuilder();
        // 声明一个栈
        char[] stack = new char[50010];
        int top = -1;
        int i = 0;
        while (i < len) {
            while (i < len && (top == -1 || stack[top] == chs[i])) {
                stack[++top] = chs[i];
                i++;
            }
            sb.append(stack[top]);
            sb.append(top + 1);
            top = -1;
        }

        return sb.length() < len ? sb.toString() : S;
    }
}
