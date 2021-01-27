package array;

/**
 * @Author: HB
 * @Description: 面试题03 - 二维数组中的查找
 *               描述: 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *               请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *               Case:
 *               Input: [                           target: 5
 *                       [1,   4,  7, 11, 15],
 *                       [2,   5,  8, 12, 19],
 *                       [3,   6,  9, 16, 22],
 *                       [10, 13, 14, 17, 24],
 *                       [18, 21, 23, 26, 30]
 *                      ]
 *               Output: true
 * @CreateDate: 22:20 2020/10/15
 */

public class Q4 {

    /**
     * @Author: HB
     * @Description: 暴力法解法
     * @Date: 11:26 2020/10/16
     * @Params:
     * @Returns:
    */
    public static boolean findNumberIn2DArrayByForce(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) return false;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if(matrix[i][j] == target)
                    return true;
            }
        }
        return false;
    }


    /**
     * @Author: HB
     * @Description: 基于二分查找解法
     * @Date: 11:28 2020/10/16
     * @Params: null
     * @Returns:
    */
    public static boolean findNumberIn2DArrayByBinarySearch(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) return false;
        for (int i = 0; i < row; i++){
            if(binarySearch(matrix[i], 0, matrix[i].length - 1, target))
                return true;
        }
        return false;
    }

    private static boolean binarySearch (int[] nums, int start, int end, int target) {
        if(start > end) {
            return false;
        }
        int mid = (end - start) / 2 + start;
        if (nums[mid] > target) {
            return binarySearch(nums, start, mid - 1, target);
        } else if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, end, target);
        } else {
            return true;
        }
    }

    /**
     * @Author: HB
     * @Description: 线性查找解法
     * @Date: 22:25 2020/10/15
     * @Params: null
     * @Returns:
    */
    // 根据输入二维数组的性质：
    // 假定当前枚举的元素为x：
    //   1. 如果 x 等于target，则说明我们找到了目标值，返回true；
    //   2. 如果 x 小于target，则 x 左边的数一定都小于target，我们可以直接排除当前一整行的数；
    //   3. 如果 x 大于target，则 x 下边的数一定都大于target，我们可以直接排序当前一整列的数
    public static boolean findNumberIn2DArrayByLine(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) return false;
        int col = matrix[0].length;
        // 从当前矩阵的最右上角开始遍历
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if(matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;
        findNumberIn2DArrayByLine(matrix, target);
    }
}
