package algorithm;

/**
 * @Author: HB
 * @Description: 面试题47 - 礼物的最大价值
 *               描述: 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 *               你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 *               给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *               Case:
 *                   Input:  [
 *                              [1,3,1],
 *                              [1,5,1],
 *                              [4,2,1]
 *                          ]
 *                   OutPut: 12
 *               Limit: 0 < grid.length <= 200
 *                      0 < grid[0].length <= 200
 *               Remark:
 * @CreateDate: 19:51 2021/2/12
 */

public class Q47 {

    /**
     * @Author: HB
     * @Description: 动态规划解法
     * @Date: 19:53 2021/2/12
     * @Params: null
     * @Returns:
    */
    // 算法思想:
    // 1. 状态定义： 定义dp[i][j]: 到达下标i, j处时能拿到的最大价值礼物
    // 2. 状态转移方程: 因为每次只能向右, 向下移动, 那么当到达下标i, j时, 其所能拿到的最大价值礼物dp[i][j]依赖于dp[i - 1][j] 和 dp[i][j - 1]处的最大价值礼物, 所以可以得到状态转移方程为:
    // dp[i][j] = Max{dp[i - 1][j], dp[i][j - 1]} + grid[i][j]  (i - 1 >= 0 && j - 1 >= 0)
    // dp[i][j] = dp[i][j - 1] + grid[i][j] (j - 1 > 0)
    // dp[i][j] = dp[i - 1][j] + grid[i][j] (i - 1 > 0)
    // 3. 初始化：dp[0][0] = grid[0][0]
    // 4. 返回结果：dp[row - 1][col - 1]
    // 5. 注意事项: 注意处理边界情况

    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        // 创建dp数组
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 初始化
                if (i == 0 && j == 0)
                    dp[i][j] = grid[i][j];
                if (i - 1 >= 0 && j - 1 >= 0)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j] ;
                else if (j - 1 >= 0)
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                else if (i - 1 >= 0)
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }

    /**
     * @Author: HB
     * @Description: 动态规划解法二
     * @Date: 19:54 2021/2/12
     * @Params: null
     * @Returns:
    */
    // 定义状态dp[i][j]：表示[0, 0] -> [i - 1, j - 1]的礼物最大价值.
    // 动态数组多定义一行一列, 避免了边界问题的讨论, 使得代码更加简便
    public int maxValue2(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        // 创建dp数组
        int[][] dp = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1] ;
            }
        }

        return dp[row][col];
    }

    /**
     * @Author: HB
     * @Description: 动态规划解法三 - 滚动数组优化
     * @Date: 20:09 2021/2/12
     * @Params: null
     * @Returns:
    */
    // 定义状态dp[i][j]：表示[0, 0] -> [i - 1, j - 1]的礼物最大价值.
    // 滚动数据优化：因为dp[i][j]只依赖于dp[i][j - 1] 和 dp[i - 1][j]的礼物价值, 所有可以使用一个一维数组代替二维数组
    public int maxValue3(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        // 创建dp数组
        int[] dp = new int[col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // 其中dp[j]保存了dp[i - 1][j], dp[j - 1]保存了dp[i][j - 1]
                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i - 1][j - 1] ;
            }
        }

        return dp[col];
    }

}
