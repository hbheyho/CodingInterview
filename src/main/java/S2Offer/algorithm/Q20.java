package S2Offer.algorithm;

/**
 * @Author: HB
 * @Description: 面试题20 - 表示数值的字符串
 *               描述: 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *               例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 *               但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *               Case:
 *                   Input: "+100"
 *                   Output: "true"
 *               Limit:
 *               Remark:
 * @CreateDate: 14:57 2021/2/2
 */

public class Q20 {

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 14:58 2021/2/2
     * @Params: null
     * @Returns:
    */
    // 1e9 => 1*10^9; 1e-9 => 1*10^(-9)
    // 数字的格式可以归纳为：A[.[B]][e|EC] 或者 .B[e|EC], 其中A,C都是整数(可以有'+' 或 '-'), 而B是一个无符号整数
    // 标识扫描到了字符串的哪个位置
    static int index = 0;
    public static boolean isNumber(String s) {
        if (s == null)
            return false;
        char[] chs = s.toCharArray();

        // 标识是否是数字
        boolean isNumeric = false;

        // 处理前导空格问题
        while (index < chs.length && chs[index] == ' ')
            index++;

        // 1. 扫描数字格式中的A部分
        isNumeric = scanInteger (chs);

        // 2. 遇到了小数点'.', 则扫描小数部分的B
        if (index < chs.length && chs[index] == '.') {
            index++;
            // 下面代码使用||的原因:
            // (1) 小数.前面可以没有整数, 例如 .123;
            // (2) 小数点后面可以没有数字, 例如 223.;
            // (3) 小数点后面和前面都可以数字, 例如 22.22。
            isNumeric = scanUnsignedInteger(chs) || isNumeric;
        }

        // 2. 遇到了'e'和'E'部分, 则扫描指数部分C
        if (index < chs.length && (chs[index] == 'e' || chs[index] == 'E')) {
            index++;
            // 下面代码使用&&的原因
            // (1) 当e或E前面没有数字时, 整个字符串不能表示数字, 例如 e2;
            // (2) 当e或E后面没有数字时, 整个字符串不能表示数字, 例如 12e。
            isNumeric = scanInteger (chs) && isNumeric;
        }

        // 处理后导空格问题
        while (index < chs.length && chs[index] == ' ')
            index++;

        return isNumeric && index == chs.length;

    }

    // 符号数扫描
    public static boolean scanInteger (char[] chs) {
        if (index < chs.length && (chs[index] == '+' || chs[index] == '-'))
            index++;
        return scanUnsignedInteger (chs);
    }

    // 无符号数字扫描
    public static boolean scanUnsignedInteger (char[] chs) {
        int befor = index;
        while (index < chs.length && chs[index] >= '0' && chs[index] <= '9')
            index++;
        // 当befor ~ index存在若干个0 ~ 9, 则返回true
        return index > befor;
    }
}
