package string;

import java.util.Arrays;

/**
 * @Author: HB
 * @Description: 面试题05 - 替换空格
 *               描述: 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *               Case:
 *                   Input: "We are happy."
 *                   Output: "We%20are%20happy."
 *               Limit: 0 <= s.length <= 10000
 * @CreateDate: 14:41 2020/10/16
 */

public class Q5 {
    /**
     * @Author: HB
     * @Description: StringBuilder 解法
     * @Date: 14:43 2020/10/16
     * @Params: null
     * @Returns:
    */
    public static String replaceSpaceByStringBuilder (String s) {
        int len = s.length();
        if (len == 0) return s;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == 32) {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @Author: HB
     * @Description: 双指针解法
     * @Date: 14:44 2020/10/16
     * @Params: null
     * @Returns:
    */
    public static String replaceSpaceByDoublePoint(String s) {
        // 其中count为空格出现的次数
        int len = s.length(), count = 0;
        if (len == 0) return s;

        // 计算字符中空格的个数
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 32) {
                count++;
            }
        }

        // 因为遇到一个空格就遇到一个%20, 所以替换之后的字符串长度为 len + count * 2
        char[] chars = Arrays.copyOf(s.toCharArray(), len + count * 2);

        // 使用两个指针分别指向原字符串的末尾(i), 新字符串的末尾(j)
        int i = len - 1, j = len + count * 2 - 1;
        while (i != j) {
            // 遇到的为普通字符, 直接往后移动
            while (chars[i] != 32) {
                chars[j--] = chars[i--];
            }
            // 遇到了空格, 替换成 %20
            chars[j--] = '0';
            chars[j--] = '2';
            chars[j--] = '%';
            i--;
        }
        return String.valueOf(chars);
    }

    /**
     * @Author: HB
     * @Description:  双指针解法II
     * @Date: 14:45 2020/10/16
     * @Params: null
     * @Returns:
    */
    public static String replaceSpaceByDoublePointII(String s) {
        // 其中count为空格出现的次数
        int len = s.length(), count = 0;
        if (len == 0) return s;

        // 计算字符中空格的个数
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 32) {
                count++;
            }
        }

        // 因为遇到一个空格就遇到一个%20, 所以替换之后的字符串长度为 len + count * 2
        char[] chars = new char[len + count * 2];
        int index = 0;
        for(int j = 0; j < len; j++) {
            if(s.charAt(j) == 32) {
                chars[index++] = '%';
                chars[index++] = '2';
                chars[index++] = '0';
            } else {
                chars[index++] = s.charAt(j);
            }
        }
        return String.valueOf(chars);
    }
}
