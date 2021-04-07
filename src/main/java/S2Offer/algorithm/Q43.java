package S2Offer.algorithm;

/**
 * @Author: HB
 * @Description: 面试题43 - 1 ~ n整数中1出现的次数
 *               描述: 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *               例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *               Case:
 *                   Input: n = 12
 *                   Output: 5
 *               Limit: 1 <= n < 2^31
 *               Remark: <LC 233> 数字1的个数
 * @CreateDate: 17:01 2021/4/6
 */

public class Q43 {

    /**
     * @Author: HB
     * @Description: 数学解法
     *               题解：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
     * @Date: 17:03 2021/4/6
     * @Params: null
     * @Returns:
    */
    // 算法思想：将1 ~ n的个位, 十位, 百位....的1出现次数依次相加, 即为最后1出现的次数
    public int countDigitOneByMath(int n) {
        int countOne = 0;

        // 变量初始化
        // curr：当前计算1出现次数的位, 从个位开始
        int curr = n % 10;
        // hight: curr 右边的位数元素值
        int hight = n / 10;
        // low：curr 左边的位数元素值
        int low = 0;
        // digit: curr位的位数因子
        int digit = 1;

        // 当 high 和 cur 同时为 0 时，说明已经越过最高位，因此跳出
        while (hight != 0 || curr != 0) {
            if (curr == 0) {
                countOne += hight * digit;
            } else if (curr == 1) {
                countOne += (hight * digit) + low + 1;
            } else {
                countOne += (hight + 1) * digit;
            }

            // 更新变量
            low = curr * digit + low;
            curr = hight % 10;
            hight = hight / 10;
            digit *= 10;
        }

        return countOne;
    }


    /**
     * @Author: HB
     * @Description: 递归解法
     *               题解： https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/javadi-gui-by-xujunyi/
     * @Date: 17:44 2021/4/6
     * @Params: null
     * @Returns:
    */
    // 定义递归函数f(n):  1 ~ n中n个整数的十进制表示中1出现次数
    public int countDigitOneByRecursion(int n) {
        return f(n);
    }

    public int f (int n) {
        if (n <= 0)
            return 0;
        String num = String.valueOf(n);
        int hight = num.charAt(0) - '0';
        int pow = (int)Math.pow(10, num.length() - 1);
        int last = n - hight * pow;
        if (hight == 1)
            return f(pow - 1) + last + 1 + f(last);
        else
            return pow + hight * f(pow - 1) + f(last);
    }



}
