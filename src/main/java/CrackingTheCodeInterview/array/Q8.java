package CrackingTheCodeInterview.array;

import java.util.Arrays;

/**
 * @Author: HB
 * @Description: 面试题01.08 - 零矩阵
 *               描述: 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *               Case:
 *               Input: matrix = [[1,1,1],
 *                                [1,0,1],
 *                                [1,1,1]]
 *               Output: [[1,0,1],
 *  *                     [0,0,0],
 *  *                     [1,0,1]]
 *               Limit:
 *               Remark: <LC 73> 矩阵置为零
 * @CreateDate: 12:27 2021/4/10
 */



public class Q8 {

    /**
     * @Author: HB
     * @Description: 不使用额外数组解法
     * @Date: 12:29 2021/4/10
     * @Params: null
     * @Returns:
    */
    // 借用二维数组的第一行和第一列作为标记, 标记当前行, 当前列是否存在0, 但是需要预先记录第一行, 第一列是否存在0, 防止被其他行影响
    public void setZeroes(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        // 计算第一行第一列是否有0
        boolean rowZero = false, colZero = false;
        for (int i = 0; i < col; i++) {
            if (matrix[0][i] == 0)
                rowZero = true;
        }
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0)
                colZero = true;
        }

        // 统计是否存在0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 依次进行清0操作
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 0)
                Arrays.fill(matrix[i], 0);
        }

        for (int j = 1; j < col; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < row; i++)
                    matrix[i][j] = 0;
            }
        }

        // 依次判断第一行第一列是否需要置为0
        if (rowZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (colZero) {
            for (int i = 0; i < row; i++)
                matrix[i][0] = 0;
        }
    }

    /**
     * @Author: HB
     * @Description: 解法1优化
     * @Date: 12:29 2021/4/10
     * @Params: null
     * @Returns:
    */
    // 常数空间改进 - 原地算法, 在方法一基础上进行优化
    // 如何利用给定的matrix数组进行计算
    // 算法思想：
    // 1. 使用matrix的第一行和第一列记录该行或该列是否有0, 即标志位
    // 2. 使用一个标记变量记录第一列是否原本存在0, 使用第一列的第一个元素来标记第一行是否存在0
    // 3. 防止第一列的第一个元素被提前更新，需要从最后一行开始，倒序地处理矩阵元素
    public void setZeroes2(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        // 保存第一列是否有0
        boolean colZero = false;

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0)
                colZero = true;
            // 使用第一列的第一个元素标记第一行是否存在0
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 防止第一列的第一个元素被提前更新，需要从最后一行开始，倒序地处理矩阵元素
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
            if (colZero)
                matrix[i][0] = 0;
        }

    }
}
