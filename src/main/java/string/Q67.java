package string;

/**
 * @Author: HB
 * @Description: 面试题67 - 把字符串转换为整数
 *               描述: 写一个函数 StrToInt，实现把字符串转换成整数这个功能。
 *               不能使用 atoi 或者其他类似的库函数。
 *               首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *               当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
 *               作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，
 *               形成整数。
 *               该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不
 *               应该造成影响。
 *               注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白
 *               字符时，则你的函数不需要进行转换。
 *               在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *               说明：假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 *               如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *               Case:
 *                   Input: "   -42"
 *                   Output: -42
 *               Limit:
 *               Remark: <LC 8> 字符串转换整数(atoi)
 * @CreateDate: 11:30 2021/3/14
 */

public class Q67 {

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 11:34 2021/3/14
     * @Params: null
     * @Returns:
    */
    // 考虑下面四种情况：
    // 1. 首部空格：直接删除即可
    // 2. 符号位：三种情况：'+', '-' 和 无符号; 新建一个sign变量来标志符号位的正负
    // 3. 非数字字符：直接返回
    // 4. 数字字符：进行转换, 转换过程中判断是否发生上溢和下溢
    public int strToInt(String str) {
        char[] chs = str.toCharArray();
        int len = chs.length;
        // 字符串为空, 直接返回0
        if (len == 0)
            return 0;

        // boundary: 大数边界 => 214748364
        int res = 0, boundary = Integer.MAX_VALUE / 10;
        // i：字符串遍历起始位; sign: 符号位
        int i = 0, sign = 1;

        // 1. 去除前置空格
        while (i < len && chs[i] == ' ') {
            if (++i == len)
                return 0;
        }

        // 2. 判断符号位, 对符号位的处理
        if (chs[i] == '-')
            sign = -1;
        // 开头存在符号位, 则跳过符号位
        if (chs[i] == '+' || chs[i] == '-')
            i++;

        for (int j = i; j < len; j++) {

            // 3. 遇到数字, 直接拼接
            if (chs[j] >= '0' && chs[j] <= '9') {
                // 判断是否发生溢出
                if (res > boundary || res == boundary && chs[j] > '7' ) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = res * 10 + chs[j] - '0';
                // 4. 遇到非数字, 直接退出
                // 这个判断也同时考虑了 "88 uu"情况, "77 87"情况 和 "+-88"情况
            } else {
                break;
            }
        }

        return sign * res;
    }
}
