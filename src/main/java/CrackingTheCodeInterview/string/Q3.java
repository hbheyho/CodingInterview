package CrackingTheCodeInterview.string;

/**
 * @Author: HB
 * @Description: 面试题01.03 - URL化
 *               描述: URL化。编写一种方法，将字符串中的空格全部替换为%20。
 *               假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 *               （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 *               Case:
 *               Input: "Mr John Smith    ", 13
 *               Output: "Mr%20John%20Smith"
 *               Limit: 字符串长度在 [0, 500000] 范围内。
 *               Remark:
 * @CreateDate: 14:29 2021/4/7
 */

public class Q3 {

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 14:38 2021/4/7
     * @Params: null
     * @Returns:
    */
    public String replaceSpaces(String S, int length) {
        // 计算空格数量
        int blankNums = 0;
        char[] chs = S.toCharArray();
        for (int i = 0; i < length; i++) {
            if (chs[i] == ' ')
                blankNums++;
        }

        // 创建存放URL化之后的字符数组
        char[] resultURL = new char[length + 2 * blankNums];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (chs[i] == ' ') {
                resultURL[index++] = '%';
                resultURL[index++] = '2';
                resultURL[index++] = '0';
            } else {
                resultURL[index++] = chs[i];
            }
        }

        return String.valueOf(resultURL);
    }

    /**
     * @Author: HB
     * @Description:  从后向前计算
     * @Date: 14:38 2021/4/7
     * @Params: null
     * @Returns:
    */
    public static String replaceSpaces2(String S, int length) {
        char[] chs = S.toCharArray();
        int len = chs.length;
        int i = length - 1, j = len - 1;
        while (i >= 0) {
            if (chs[i] == ' ') {
                chs[j--] = '0';
                chs[j--] = '2';
                chs[j--] = '%';
                i--;
            } else {
                chs[j--] = chs[i--];
            }
        }
        // 选取合适的字符串进行返回
        return String.valueOf(chs, j + 1, len - j - 1);
    }
}
