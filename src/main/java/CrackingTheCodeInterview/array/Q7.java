package CrackingTheCodeInterview.array;

/**
 * @Author: HB
 * @Description: 面试题01.07 - 旋转数组
 *               描述: 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。
 *                     请你设计一种算法，将图像旋转 90 度。
 *                     不占用额外内存空间能否做到？
 *               Case:
 *               Input: matrix = [[1,2,3],
 *                                [4,5,6],
 *                                [7,8,9]]
 *               Output: [[7,4,1],
 *  *                     [8,5,2],
 *  *                     [9,6,3]]
 *               Limit:
 *               Remark: <LC 48> 旋转图像
 * @CreateDate: 10:30 2021/4/10
 */

public class Q7 {

    /**
     * @Author: HB
     * @Description: 两次翻转
     * @Date: 10:33 2021/4/10
     * @Params: null
     * @Returns:
    */
    // 矩阵的原地旋转
    // 旋转规则: 第一行变为最右一列, 第二行变为倒数第二列, 以此类推...
    // 可以得到翻转规律：matrix[row][col] => matrix[col][n - row - 1]
    // 原地解法
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 首先进行水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j]  = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        // 再次进行按主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 10:33 2021/4/10
     * @Params: null
     * @Returns:
    */
    public void rotate2(int[][] matrix) {
        int n  = matrix.length;

        // 总共需要进行n >> 1轮顺时针交换
        int times = 0;
        while (times <= (n >> 1)) {
            // 每轮会进行len次交换
            int len = n - (times << 1);
            // 进行具体交换操作
            for (int i = 0; i < len - 1; i++) {
                // 保存每次旋转的开头元素, 每次进行元素交换时都是从后 -> 前开始循坏交换的
                int temp = matrix[times][times + i];
                matrix[times][times + i] = matrix[times + len - i - 1][times];
                matrix[times + len - i - 1][times] = matrix[times + len - 1][times + len - i - 1];
                matrix[times + len - 1][times + len - i - 1] = matrix[times + i][times + len - 1];
                matrix[times + i][times + len - 1] = temp;
            }
            times++;
        }

    }
}
