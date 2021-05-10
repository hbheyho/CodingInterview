package CrackingTheCodeInterview.bit;

/**
 * @Author: HB
 * @Description: 面试题05.04 - 下一个数
 *               描述: 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 *               Case:
 *               Input: num = 2
 *               Output: [4,1]
 *               Limit: num的范围在[1, 2147483647]之间；
 *               Remark: 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 * @CreateDate: 21:49 2021/5/10
 */

public class Q37 {

    /**
     * @Author: HB
     * @Description: 模拟解法
     * @Date: 21:53 2021/5/10
     * @Params: null
     * @Returns:
    */
    // 如下实例：
    // 5 => 0101, [6(110), 3(011)]
    // 7 => 0111, [11(1011), -1]
    // 9 => 1001, [10(1010), 6(0110)]
    // 14 => 1110, [19(10011), 13 (1101)]

    // 算法思路：
    // 1. 找到略大的数：找到第一个形如zh01xy的位置, 寻找过程中统计二进制1的出现个数为cnt,
    // 将二进制0的位置置为1,将二进制1的位置置为0, 并将剩余位置从低位y开始置为1(操作次数为cnt - 1)
    // 3. 找到略小的数：找到第一个形如zh10xy的位置, 寻找过程中统计二进制1的出现个数为cnt,
    // 将二进制0的位置置为1,将二进制1的位置置为0 并将剩余位置从高位x开始置为1(操作次数为cnt)
    public int[] findClosedNumbers(int num) {
        // 对Integer.MAX_VALUE特殊处理
        if (num == Integer.MAX_VALUE)
            return new int[]{-1,-1};

        // 转换为二进制数组形式
        int[] numArr = new int[32];
        int temp = num;
        for (int i = 31; i >= 0; i--) {
            numArr[i] = (temp & 1);
            temp >>>= 1;
        }

        // 保存结果
        int[] result = new int[2];

        // 找到略大的数字
        transferUpper (numArr.clone(), result, num);

        // 找到略小的数字
        transferDown (numArr.clone(), result, num);

        return result;
    }

    // transferDown: 找到略小的数
    public void transferDown (int[] numArr, int[] result, int num) {

        int cnt = 0;
        for (int i = 31; i >= 0; i--) {
            int j = i - 1;
            if (numArr[i] == 1)
                cnt++;
            // 找到第一个形如xx10xx的位置
            if (j >= 0 && numArr[i] == 0 && numArr[j] == 1) {
                numArr[i] = 1;
                numArr[j] = 0;
                cnt = cnt - 1;
                for (int k = i + 1; k < 32; k++) {
                    if (cnt >= 0)
                        numArr[k] = 1;
                    else
                        numArr[k] = 0;
                    cnt--;
                }
                break;
            }
        }

        // 转换为十进制
        result[1] = transfer2Ten(numArr);
        // 处理之后仍是本身, 则返回-1
        if (result[1] == num)
            result[1] = -1;

    }

    // transferUpper: 找到略大的数
    public void transferUpper (int[] numArr, int[] result, int num) {

        int cnt = 0;
        for (int i = 31; i >= 0; i--) {
            int j = i - 1;
            if (numArr[i] == 1)
                cnt++;
            // 找到第一个形如xx01xx的位置, 进行处理
            if (j >= 0 && numArr[i] == 1 && numArr[j] == 0) {
                numArr[i] = 0;
                numArr[j] = 1;
                cnt = cnt - 1;
                for (int k = 31; k >= i + 1; k--) {
                    if (cnt > 0)
                        numArr[k] = 1;
                    else
                        numArr[k] = 0;
                    cnt--;
                }
                break;
            }
        }

        // 转换为十进制
        result[0] = transfer2Ten(numArr);
        // 处理之后仍是本身, 则返回-1
        if (result[0] == num)
            result[0] = -1;

    }

    // transfer2Ten：二进制转换为十进制
    public int transfer2Ten(int[] numArr) {
        int result = 0, base = 1;
        for (int i = 31; i >= 0; i--) {
            result += (numArr[i] * base);
            base *= 2;
        }
        return result;
    }
}
