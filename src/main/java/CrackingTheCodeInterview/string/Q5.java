package CrackingTheCodeInterview.string;

/**
 * @Author: HB
 * @Description: 面试题01.05 - 一次编辑
 *               描述: 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
 *               给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 *               Case:
 *               Input: first = "pale"
 *                      second = "ple"
 *               Output: true
 *               Limit:
 *               Remark:
 * @CreateDate: 16:45 2021/4/8
 */

public class Q5 {
    /**
     * @Author: HB
     * @Description: 双指针解法
     * @Date: 16:46 2021/4/8
     * @Params: null
     * @Returns:
    */
    public boolean oneEditAwayByTwoPoints(String first, String second) {
        // 计算first 和 second 的长度差值
        int subLen = first.length() - second.length();
        if (subLen > 1 || subLen < -1)
            return false;

        // 定义指针i, j, 分别指向first和second
        // 代表可以进行几次操作
        int operation = 1;
        for (int i = 0, j = 0; i < first.length() && j < second.length(); i++, j++) {
            // 当遇到不同字符时, 需要进行如下处理
            // 1. 若字符长度相等, 即subLen == 0, 则直接 i++, j++, 相当进行了一次替换操作
            // 2. first字符串比second字符串长1, 则j--, 相等于对first进行一次删除操作
            // 3. first字符串比second字符串短1, 则i--, 相当于对first进行一次添加操作
            if (first.charAt(i) != second.charAt(j)) {
                if (subLen == 1) {
                    j--;
                } else if (subLen == -1) {
                    i--;
                }
                operation--;
            }
            if (operation < 0)
                return false;
        }
        return true;
    }

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 16:47 2021/4/8
     * @Params: null
     * @Returns:
    */
    public boolean oneEditAwayByDP(String first, String second) {
        int lenFirst = first.length(), lenSecond = second.length();
        if (lenSecond == 1 && lenFirst == 1)
            return true;
        if (Math.abs(lenFirst - lenSecond) > 1)
            return false;

        int[][] dp = new int[lenFirst + 1][lenSecond + 1];

        // 初始化
        for (int i = 0; i <= lenFirst; i++)
            dp[i][0] = i;
        for (int j = 0; j <= lenSecond; j++)
            dp[0][j] = j;

        // 更新dp数组
        for (int i = 1; i <= lenFirst; i++) {
            for (int j = 1; j <= lenSecond; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        return dp[lenFirst][lenSecond] <= 1;
    }

    /**
     * @Author: HB
     * @Description: 递归解法 + 备忘录算法优化
     * @Date: 16:47 2021/4/8
     * @Params: null
     * @Returns:
    */
    int[][] meno;
    public boolean oneEditAwayByRecursion(String first, String second) {
        int lenFirst = first.length(), lenSecond = second.length();
        if (lenSecond == 1 && lenFirst == 1)
            return true;
        if (Math.abs(lenFirst - lenSecond) > 1)
            return false;
        meno = new int[lenFirst][lenSecond];

        return editAway (first, second) <= 1;
    }

    public int editAway (String first, String second) {
        int len1 = first.length(), len2 = second.length();
        if (len1 == 0 || len2 == 0)
            return Math.max(len1, len2);

        if (first.charAt(len1 - 1) == second.charAt(len2 - 1))
            return editAway(first.substring(0, len1 - 1), second.substring(0, len2 - 1));

        if (meno[len1 - 1][len2 - 1] != 0)
            return meno[len1 - 1][len2 - 1];

        int minOperation =  1 + Math.min(editAway(first, second.substring(0, len2 - 1)),
                Math.min(editAway(first.substring(0, len1 - 1), second),                                           editAway(first.substring(0, len1 - 1), second.substring(0, len2 - 1))));
        meno[len1 - 1][len2 - 1] = minOperation;
        return minOperation;
    }

}
