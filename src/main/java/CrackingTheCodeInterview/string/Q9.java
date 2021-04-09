package CrackingTheCodeInterview.string;

/**
 * @Author: HB
 * @Description: 面试题01.09 - 字符串轮转
 *               描述: 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
 *               （比如，waterbottle是erbottlewat旋转后的字符串）。
 *               Case:
 *               Input: s1 = "waterbottle", s2 = "erbottlewat"
 *               Output: true
 *               Limit: 字符串长度在[0, 100000]范围内
 *               Remark:
 * @CreateDate: 14:52 2021/4/9
 */

public class Q9 {
    /**
     * @Author: HB
     * @Description: 找规律
     * @Date: 14:53 2021/4/9
     * @Params: null
     * @Returns:
    */
    // 重点：若s2为s1的旋转字符串, 那么将s2与其本身进行拼接, 所拼接得到的字符串会包括s1
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        String s =  s2 + s2;
        return s.contains(s1);
    }
}
