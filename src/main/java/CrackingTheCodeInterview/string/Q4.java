package CrackingTheCodeInterview.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HB
 * @Description: 面试题01.04 - 回文排列
 *               描述: 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 *                     回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 *                     回文串不一定是字典当中的单词。
 *               Case:
 *               Input: "tactcoa"
 *               Output: true
 *               Limit:
 *               Remark:
 * @CreateDate: 9:53 2021/4/8
 */

public class Q4 {

    /**
     * @Author: HB
     * @Description: HashMap 解法
     * @Date: 9:54 2021/4/8
     * @Params: null
     * @Returns:
    */
    // 判断一个字符串是否是一个回文字符串的排列
    // 只需要判断字符串中的字符的出现次数是否成偶数次出现, 且奇数次出现的个数不能超过一个
    public boolean canPermutePalindromeByHashMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int odd = 0;
        for (Map.Entry<Character, Integer> entry: map.entrySet()){
            int value = entry.getValue();
            if (value % 2 != 0 && ++odd > 1)
                return false;
        }
        return true;
    }

    /**
     * @Author: HB
     * @Description: 数组解法
     * @Date: 9:54 2021/4/8
     * @Params: null
     * @Returns:
    */
    // 判断一个字符串是否是一个回文字符串的排列
    // 只需要判断字符串中的字符的出现次数是否成偶数次出现, 且奇数次出现的个数不能超过一个
    public boolean canPermutePalindromeByArray(String s) {
        int[] count = new int[128];
        // ASCII码的打印字符从32开始, 127结束
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 32]++;
        }
        int odd = 0;
        for (int i = 0; i < 128; i++){
            if (count[i] % 2 != 0 && ++odd > 1)
                return false;
        }
        return true;
    }

    /**
     * @Author: HB
     * @Description: 位运算解法
     * @Date: 9:55 2021/4/8
     * @Params: null
     * @Returns:
    */
    // 判断一个字符串是否是一个回文字符串的排列
    // 只需要判断字符串中的字符的出现次数是否成偶数次出现, 且奇数次出现的个数不能超过一个
    // 位运算解法 - 对于128位的ASCII码字符, 需要使用两个long来表示
    // 当前位是0, 表示字符个数为偶数个, 当前位为1, 表示字符个数位奇数个
    public boolean canPermutePalindromeByBit(String s) {
        long lowBit = 0;
        long highBit = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 字符的十进制大于64, 则存储在高位, 否则在低位
            if (ch > 64) {
                highBit = highBit ^ (1L << (ch - 64));
            } else {
                lowBit = lowBit ^ (1L <<  ch);
            }
        }

        // 最后1的出现次数不可大于1
        return Long.bitCount(highBit) + Long.bitCount(lowBit) <= 1;
    }
}
