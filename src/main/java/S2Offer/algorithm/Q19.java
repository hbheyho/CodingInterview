package S2Offer.algorithm;

/**
 * @Author: HB
 * @Description: 面试题19 - 正则表达式匹配
 *               描述: 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 *               而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。
 *               例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配
 *               Case:
 *                   Input: "aab"
 *                   Output: "c*a*b"
 *               Limit: s 可能为空，且只包含从 a-z 的小写字母。
 *                      p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 *               Remark: <Lc 10> 正则表达式匹配
 * @CreateDate: 14:41 2021/1/14
 */

public class Q19 {

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 16:28 2021/1/24
     * @Params: null
     * @Returns:
    */
    // 模式中的字符'.'表示任意一个字符, 而'*'表示它前面的字符可以出现任意次（含0次）
    // 匹配是指字符串s的所有字符匹配整个模式p
    public static boolean isMatch(String s, String p) {

        if (s == null || p == null)
            return false;
        char[] charS = s.toCharArray(), charP = p.toCharArray();

        return match(charS, 0, charP, 0);

    }

    public static boolean match (char[] charS, int i, char[] charP, int j) {

        // 1. 递归结束条件
        // charS, charP全部遍历完毕
        if (i == charS.length && j == charP.length)
            return true;

        // charS没有遍历完, 但是charP遍历完毕
        if (i < charS.length && j == charP.length)
            return false;

        // 2. 递进操作
        // 当前模式串的第二个字符为'*', 则面临着多种匹配方式
        if (j + 1 < charP.length && charP[j + 1] == '*') {

            // (1) 字符串和模式串第一个字符相等或模式串为'.'的情况
            if (i  < charS.length && (charP[j] == charS[i] || charP[j] == '.')) {
                // 模式串和字符串都移动到下一个状态, 代表着charP[j]出现1次
                return  match (charS, i + 1, charP, j + 2)
                        // 模式串保持不变, 字符串向后移动, 代表着charP[j]出现次数大于1
                        || match (charS, i + 1, charP, j)
                        // 忽略模式串 charP[i]*, 则标识charP[j]出现0次
                        || match (charS, i, charP, j + 2);
            } else {
                // (2) 忽略模式串 charP[j]*, 则代表 charP[j] 出现0次
                return match (charS, i, charP, j + 2);
            }

        }

        // 处理字符串和模式串第一个字符相等或模式串为'.'的情况
        // 字符串和模式串都向后移动一位
        if (i < charS.length && (charS[i] == charP[j] || charP[j] == '.'))
            return match (charS, i + 1, charP, j + 1);

        // 3. 递归返回值
        return false;

    }

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 16:19 2021/1/27
     * @Params: null
     * @Returns:
    */
    // 1. 定义状态: 根据题意定义状态dp[i][j]: 其表示为字符串s的下标i之前(不包括下标i)的字符是否和模式串p的j下标之前(不包括下标j)的字符相互匹配
    // 2. 状态转移方程: 当到达字符串s下标i, 模式串p下标j时, 要完成状态转移, 需要分类讨论：
    // (1) 当s[i - 1] == p[j - 1]时, 则dp[i][j] = dp[i - 1][j - 1], 例如字符串 s = "abd", p = "abd"
    // (2) 当p[j - 1] == '.', 因为'.'可以为任意字符, 则 dp[i][j] == dp[i - 1][j - 1], 例如 s = "abc", p = "a.c"
    // (3) 当p[j - 1] == '*'时, 需要再次进行分类讨论:
    //      1) 当模式串的前一个字符p[j - 2]和s[i - 1]不相等或者p[j - 2]不为 '.'时, dp[i][j] = dp[i][j - 2] => 这意味着p[j - 2]出现0次, 例如 s = "abd", p = "abc*d"为true
    //      2) 当模式串的前一个字符p[j - 2]和s[i - 1]相等或者p[j - 2]为 '.'时,又有如下三种情况： 1. p[j - 2] 出现0次, dp[i][j] = dp[i][j - 2], 例如 s = "ad", p = "aa*e"; 2. p[j - 2]出现一次, dp[i][j] = dp[i][j - 1] / dp[i][j] = dp[i - 1][j], 例如s = "abc", p = "ab*c"为true ; 3. p[j - 2]出现多次, dp[i][j] = dp[i - 1][j], 例如s = "abbbbbd", p = "ab*d"
    // 3. 初始化 dp[0][0] = true, 且初始化" "和p的匹配关系
    // 4. 结果 dp[s.length()][p.length()]

    public static boolean isMatch2(String s, String p) {

        if (s == null || p == null)
            return false;

        int n = s.length(), m = p.length();
        char[] chS = s.toCharArray(), chP = p.toCharArray();
        boolean[][] dp = new boolean[n + 1][m + 1];

        // 初始化dp[0][0] = true
        dp[0][0] = true;
        // " " 和p的匹配关系初始化(即初始化二维表格的第一行), a*a*a*a*a*这种能够匹配空串，其他的是都是false
        // 奇数位不管什么字符都是false，偶数位为 * 时则: dp[0][i] = dp[0][i - 2]
        for (int i = 2; i <= m; i += 2) {
            if (chP[i - 1] == '*')
                dp[0][i] = dp[0][i - 2];
        }

        // 填充dp数组, 从第二行开始进行填充
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char sc = chS[i - 1];
                char pc = chP[j - 1];
                // 符合状态转移方程(1)/(2)情况
                if (sc == pc || pc == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                    // 符合状态转移方程(3)情况
                else if (pc == '*') {
                    // 处理情况1.
                    if (dp[i][j - 2])
                        dp[i][j] = true;
                        // 合并处理情况2. 3.
                    else if (sc == chP[j - 2] || chP[j - 2] == '.')
                        dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        String s = "ab";
        String p = ".*c";
        isMatch(s, p);
    }
}
