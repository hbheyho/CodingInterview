package S2Offer.array;

/**
 * @Author: HB
 * @Description: 面试题29 - 顺时针打印矩阵
 *               描述: 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *               Case:
 *               Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *               Output: [1,2,3,6,9,8,7,4,5]
 * @CreateDate: 22:20 2020/01/31
 */

public class Q29 {

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 21:34 2021/1/31
     * @Params: null
     * @Returns:
    */
    public int[] spiralOrder(int[][] matrix) {

        if (matrix.length == 0)
            return new int[0];
        int row = matrix.length;
        int col = matrix[0].length;
        // 保存结果
        int[] res = new int[row * col];
        int idx = 0;

        // 模拟边界进行顺时针打印 - 打印顺序为 "左 -> 右, 上 -> 下, 右 -> 左, 下 -> 上"
        // 为每个打印顺序定义边界, left, top, right, bottom
        // 注意每次遍历之后的退出条件
        int left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (true) {
            // "左 -> 右"遍历
            for (int i = left; i <= right; i++)
                res[idx++] = matrix[top][i];
            if (++top > bottom)
                break;

            // "上 -> 下"遍历
            for (int i = top; i <= bottom; i++)
                res[idx++] = matrix[i][right];
            if (--right < left)
                break;

            // "右 -> 左"遍历
            for (int i = right; i >= left; i--)
                res[idx++] = matrix[bottom][i];
            if (--bottom < top)
                break;

            // "下 -> 上"遍历
            for (int i = bottom; i >= top; i--)
                res[idx++] = matrix[i][left];
            if (++left > right)
                break;

        }

        return res;

    }

    /**
     * @Author: HB
     * @Description: 模拟边界解法二
     * @Date: 15:23 2021/2/2
     * @Params: null
     * @Returns:
    */
    public int[] spiralOrder2(int[][] matrix) {
        int row = matrix.length;
        if (row == 0)
            return new int[0];
        int col = matrix[0].length;

        // 保存结果
        int[] res = new int[row * col];
        int idx = 0;
        // 标识元素是否被访问
        boolean[][] visited = new boolean[row][col];
        // 顺序打印的可移动方向："左 -> 右", "上 -> 下", "右 -> 左", "下 -> 上"
        int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, d = 0;

        for (int i = 0; i < row * col; i++) {
            res[idx++] = matrix[x][y];
            visited[x][y] = true;
            // 进行顺时针移动
            int a = x + move[d][0], b = y + move[d][1];
            // 当前方位已经访问完毕, 换个方位
            if (a < 0 || a >= row || b < 0 || b >= col || visited[a][b]) {
                d = (d + 1) % 4;
                a = x + move[d][0];
                b = y + move[d][1];
            }
            x = a;
            y = b;
        }

        return res;

    }


    /**
     * @Author: HB
     * @Description:  // 剑指offer官方解法 - 太冗余难懂
     * @Date: 15:57 2021/2/2
     * @Params: null
     * @Returns:
    */
    // 保存结果
    static int[] res;
    static int idx = 0;
    public int[] spiralOrder3(int[][] matrix) {
        int row = matrix.length;
        if (row == 0)
            return new int[0];
        int col = matrix[0].length;
        res = new int[row * col];
        // 把整个矩阵看成是由一个圈一个圈组成的, 那么圈起点位(0,0) -> (start, start)
        int start = 0;
        // 循环结束条件为 2 * start > row || 2 * start > col
        while (2 * start < row && 2 * start < col) {
            printMatrixInCircle(matrix, row, col, start);
            start++;
        }

        return res;

    }

    // 打印顺序为 "左 -> 右, 上 -> 下, 右 -> 左, 下 -> 上"
    // 在打印时注意边界条件
    public static void printMatrixInCircle (int[][] matrix, int row, int col, int start) {
        int endX =  col - start - 1;
        int endY =  row - start - 1;

        // 从左->右打印
        for (int i = start; i <= endX; i++)
            res[idx++] = matrix[start][i];

        // 从上->下打印
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++)
                res[idx++] = matrix[i][endX];
        }

        // 从右 -> 左打印
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--)
                res[idx++] = matrix[endY][i];
        }

        // 从上 -> 下打印
        if (start < endX && start < (endY - 1)) {
            for (int i = endY - 1; i >= start + 1; i--)
                res[idx++] = matrix[i][start];
        }
    }

}
