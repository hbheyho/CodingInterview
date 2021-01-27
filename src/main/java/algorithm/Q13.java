package algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题13 - 机器人的运动范围
 *               描述: 地上有一个m行n列的方格, 从坐标 [0,0] 到坐标 [m-1,n-1]。
 *               一个机器人从坐标 [0, 0] 的格子开始移动, 它每次可以向左、右、上、下移动一格（不能移动到方格外）,
 *               也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时, 机器人能够进入方格 [35, 37],
 *               因为3+5+3+7=18。但它不能进入方格 [35, 38], 因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *               Case:
 *               Input: m = 2, n = 3, k = 1
 *               Output: 3
 *               Limit: 1 <= n,m <= 100
 *                      0 <= k <= 200
 *               Remark:
 * @CreateDate: 22:20 2020/11/20
 */

public class Q13 {


    /**
     * @Author: HB
     * @Description: 回溯解法一
     * @Date: 21:24 2020/11/22
     * @Params: null
     * @Returns:
    */
    // 机器人能够到达的格子个数
    static int maxCount = 0;
    // 到达一个格子后, 移动范围为左/右/上/下移动
    static int[][] coords = {{0 , -1}, {0 , 1}, {-1 , 0}, {1, 0}};
    static boolean[][] isVisited;
    static int row, col;
    public static int movingCount(int m, int n, int k) {
        row = m;
        col = n;
        isVisited = new boolean[m][n];

        // 坐标(0,0)开始移动到坐标(m - 1, n - 1)
        dfs(0, 0, k);
        return maxCount;
    }

    public static void dfs (int x, int y, int k) {

        // 1. 递归结束条件
        if (x < 0 || x >= row || y < 0 || y >= col || isAccord(x, y, k) || isVisited[x][y]) {
            return;
        }

        // 2. 递进操作
        maxCount = maxCount + 1;
        isVisited[x][y] = true;
        for (int i = 0; i < coords.length; i++) {
            int newX = coords[i][0] + x;
            int newY = coords[i][1] + y;
            dfs(newX, newY, k);
        }

        // 进行回溯
        isVisited[x][y] = false;
        // 3. 递归返回值 - 暂无

    }

    /**
     * @Author: HB
     * @Description: 回溯解法二 - 算法思路同一, 只是形式不一致
     * @Date: 21:19 2020/11/23
     * @Params: null
     * @Returns:
    */
    // 标识元素是否被访问
    static boolean[][] isVisited2;
    static int row2, col2;
    public static int movingCount2(int m, int n, int k) {
        row2 = m;
        col2 = n;
        isVisited2 = new boolean[m][n];

        // 坐标(0,0)开始移动到坐标(m - 1, n - 1)
        return dfs2(0, 0, k);
    }

    public static int dfs2(int x, int y, int k) {
        // 1. 递归结束条件
        if (x < 0 || x >= row || y < 0 || y >= col || isAccord(x, y, k) || isVisited[x][y]) {
            return 0;
        }

        // 2. 递进操作
        isVisited2[x][y] = true;
        // 3. 递归返回值
        // 到达当前位置之后可运动范围：左/右/上/下
        return 1 + dfs2(x, y - 1, k) + dfs2(x, y + 1, k) + dfs2(x - 1, y, k) + dfs2(x + 1, y, k);

    }



    /**
     * @Author: HB
     * @Description: BFS 解法
     * @Date: 9:52 2021/1/19
     * @Params: null
     * @Returns:
    */
    // Pair => 坐标(x,y), 也可用Java提供的Pair类来进行
    static class Pair {
        int key;
        int value;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 机器人每次都可左,右,上,下
    static int[][] move = new int[][]{{0, -1},{0, 1}, {-1, 0}, {1, 0}};
    public static int movingCount3(int m, int n, int k)
    {
        if (m == 0 || n == 0)
            return 0;

        // 保存最大能经过的格子数量
        int step = 0;
        // 存储每个位置是否被访问
        boolean[][] visited = new boolean[m][n];

        Deque<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0));


        while (!queue.isEmpty()) {

            Pair coordinate = queue.poll();
            int x = coordinate.key;
            int y = coordinate.value;
            visited[x][y] = true;
            step++;

            // 从当前坐标开始, 从左右上下开始行走
            for (int i = 0; i < 4; i++) {
                int newX = x + move[i][0];
                int newY = y + move[i][1];
                // 满足边界条件/未被访问/数位之和未大于threshold
                if (0 <= newX && newX < m && 0 <= newY && newY < n && !visited[newX][newY] && !isAccord(newX, newY, k)) {
                    queue.offer(new Pair(newX, newY));
                }
            }

        }

        return step;
    }


    // 判断是否满足进入条件
    public static boolean isAccord(int x, int y, int k) {

        int radixSum = 0;
        while (x > 0 || y > 0) {

            radixSum += (x % 10) + (y % 10);
            x = x / 10;
            y = y / 10;

        }

        return  radixSum > k;
    }





    public static void main(String[] args) {
        int m = 4, n = 5, k = 7;
        movingCount3(m, n, k);
    }
}
