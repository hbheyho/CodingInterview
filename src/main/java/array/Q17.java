package array;

/**
 * @Author: HB
 * @Description: 面试题17 - 打印从1到最大的n位数
 *               描述: 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，
 *               则打印出 1、2、3 一直到最大的 3 位数 999。
 *               Case:
 *               Input: 1
 *               Output: [1,2,3,4,5,6,7,8,9]
 * @CreateDate: 11:34 2021/2/1
 */

public class Q17 {

    /**
     * @Author: HB
     * @Description: 大数加法 - 使用数组来进行大数解法
     * @Date: 14:57 2021/2/1
     * @Params: null
     * @Returns:
    */
    // n表示为n位十进制数
    // 使用数组来模拟数组加法
    // 可能存在溢出, 所以不存在返回值, 直接打印
    public static void printNumbers(int n) {

        // 创建一个n位数的数组, 数组中的元素范围为 1~9
        // 数组长度需要n + 1, 第一位的来判断是否打印完成
        // 即n = 3时, [0,9,9,9] => [1,0,0,0]则表示打印完成
        int[] nums = new int[n + 1];

        // 循环进行递增
        while (true) {

            // 表示进位
            int t = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                // 最后一位递增1
                if (i == nums.length - 1)
                    t += nums[i] + 1;
                else
                    t += nums[i];
                nums[i] = t % 10;
                // 计算进位
                t /= 10;
                // 若不需要进位,则表示当前计算处理完毕
                if (t == 0)
                    break;
            }

            // 打印完成, 退出循环
            if (nums[0] == 1)
                break;

            // 进行打印
            printSubNumber (nums);

        }

    }

    /**
     * @Author: HB
     * @Description: 递归解法
     * @Date: 15:43 2021/2/1
     * @Params: null
     * @Returns:
    */
    // n表示为n位十进制数
    // 使用全排列解法：例如当n = 3时, 打印结果其实就是 001 ~ 999的全排列问题, 使用递归的思路找到n位数, 每位数字可能是0 ~ 9的全排列, 然后打印出来即可
    // 可能存在溢出, 所以不存在返回值, 直接打印
    public static void printNumbersByRecursion(int n) {
        // 保存结果
        int[] res = new int[n];
        // 从第0位开始全排列操作
        dfs (res, n, 0);

    }

    // 递归得到全排列
    public static void dfs (int[] res, int length, int index) {

        // 1. 递归结束条件
        if (index == length) {
            printSubNumber (res);
            return;
        }

        // 2. 递进操作
        // 每一位可能存在的数字范围为0 ~ 9
        for (int i = 0; i < 10; i++) {
            res[index] = i;
            dfs (res, length, index + 1);
        }

        // 3. 递归返回值 - 暂无
    }

    // 进行元素打印
    public static void printSubNumber (int[] numbers) {
        // 前导0不需要打印
        int i = 0;
        while (i < numbers.length && numbers[i] == 0)
            i++;
        // 从第一个不是0的元素开始打印
        for (int j = i; j < numbers.length; j++) {
            System.out.print(numbers[j]);
        }
        System.out.println();

    }

    public static void main(String[] args) {
        int n = 3;
        // printNumbers(n);
        printNumbersByRecursion(n);
    }
}
