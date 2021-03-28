package string;

/**
 * @Author: HB
 * @Description: 面试题58I - 翻转单词顺序
 *               描述: 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 *               为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
 *               则输出"student. a am I"。
 *               Case:
 *                   Input: "the sky is blue"
 *                   Output: "blue is sky the"
 *               Limit: 无空格字符构成一个单词。
 *                      输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *                      如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *               Remark：翻转字符串里的单词
 * @CreateDate: 10:30 2021/3/28
 */

public class Q58I {

    /**
     * @Author: HB
     * @Description: 两次翻转解法
     * @Date: 10:36 2021/3/28
     * @Params: null
     * @Returns:
    */
    public String reverseWordsByTwoReverse(String s) {
        // 去除前后空格
        s = s.trim();
        s = new StringBuilder(s).reverse().toString();
        char[] chs = s.toCharArray();
        int n = chs.length;
        // 保存结果
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {

            // 去除中间的空格
            if (chs[i] == ' ')
                continue;

            for (int j = i + 1; j <= n; j++) {

                // 对单个单词翻转, 并且对最后的单词进行处理
                if (j == n || chs[j] == ' ') {
                    int k = j - 1;
                    int h = i;
                    while (h < k) {
                        char temp = chs[h];
                        chs[h] = chs[k];
                        chs[k] = temp;
                        h++;
                        k--;
                    }
                    // 保存翻转结果
                    for (int m = i; m <= j - 1; m++) {
                        result.append(chs[m]);
                    }
                    if (j < n)
                        result.append(' ');
                    i = j;
                    break;
                }
            }
        }

        return result.toString();
    }

    /**
     * @Author: HB
     * @Description: 双指针解法
     * @Date: 10:35 2021/3/28
     * @Params: null
     * @Returns:
    */
    public String reverseWordsByTwoPoints(String s) {
        // 去除前后空格并进行首次翻转
        s = s.trim();
        StringBuilder result = new StringBuilder();
        int j = s.length() - 1, i = j;
        while (i >= 0) {
            // 搜索首个空格
            while (i >= 0 && s.charAt(i) != ' ')
                i--;
            // 将结果保存
            result.append(s.substring(i + 1, j + 1) + " ");
            // 跳过中间空格
            while (i >= 0 && s.charAt(i) == ' ')
                i--;
            j = i;
        }
        return result.toString().trim();
    }

    /**
     * @Author: HB
     * @Description: API解法
     * @Date: 10:36 2021/3/28
     * @Params: null
     * @Returns:
    */
    // 分割 + 倒序解法
    // 以空格为分割符完成字符串分割后，若两单词间有 x > 1 个空格，则在单词列表 strs 中，此两单词间会多出      // x - 1 个 "空单词" （即 "" ）
    public String reverseWordsByAPI(String s) {
        StringBuilder result = new StringBuilder();
        String[] strings = s.trim().split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            if ("".equals(strings[i]))
                continue;
            result.append(strings[i] + " ");
        }
        return result.toString().trim();
    }
}
