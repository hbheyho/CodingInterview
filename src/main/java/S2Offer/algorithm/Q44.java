package S2Offer.algorithm;

/**
 * @Author: HB
 * @Description: 面试题44 - 数字序列中某一位的数字
 *               描述: 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 *               在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *               请写一个函数，求任意第n位对应的数字。
 *               Case:
 *                   Input:  3
 *                   OutPut: 3
 *               Limit: 0 <= n < 2^31
 *               Remark: <LC 400> 第N位数字
 * @CreateDate: 11:56 2021/2/9
 */

public class Q44 {
    
    /**
     * @Author: HB
     * @Description: 数学解法
     * @Date: 11:59 2021/2/9
     * @Params: null
     * @Returns: 
    */
    // 防止溢出, 需要设置long类型
    public int findNthDigit(int n) {
        // 当n小于10时, 直接返回
        if (n < 10)
            return n;
        n -= 10;

        long digit = 2, base = 90;
        // 若需要查找的第n位数大于digit位组成的总位数, 则继续向后查找
        while (digit * base < n) {
            n -= (digit * base);
            digit += 1;
            base *= 10;
        }

        // 此时查找的第n位数小于digit位组成的总位数, 所有第n位数是某个digit位数字中的一位
        // 要查找的第n位数存在于number数字的第bit位上(从左往右, 且从0开始)
        long number = (long)Math.pow(10, digit - 1) + (n / digit);
        long bits = n % digit;

        // 构造结果
        long res = 0;
        // 从左往右查找
        long position = digit - bits;
        while (position-- > 0) {
            res = number % 10;
            number /= 10;
        }

        return (int)res;

    }


    /**
     * @Author: HB
     * @Description: 《剑指Offer》解法 - 与解法一相似
     * @Date: 12:16 2021/2/9
     * @Params: null
     * @Returns:
    */
    public int findNthDigit2(int n) {
        if (n < 0)
            return -1;
        int digits = 1, res = 0;
        while (true) {
            long numbers = countOfInteger(digits);
            // 判断numbers个数字所占据了位数是否超过了n, 若小于则开始进行寻找具体位置
            if (n < numbers * digits) {
                res = digitsAtIndex(n, digits);
                break;
            }
            n -= numbers * digits;
            digits++;
        }
        return res;
    }

    // 计算digits位的数字在整数序列中一共有多少个. 例如当digits = 2时, [10,99] 共有90个
    public long countOfInteger(int digits) {
        if (digits == 1)
            return 10;
        return 9 * (long)Math.pow(10, digits - 1);
    }

    // 找出n位数的具体位置并返回
    public int digitsAtIndex (int n, int digits) {
        long number = beginNumber(digits) + n / digits;
        int indexFromRight = digits - n % digits;
        for (int i = 1; i < indexFromRight; i++)
            number /= 10;
        return (int)number % 10;
    }

    //  求digits位的第一个数字
    public long beginNumber(int digits) {
        if (digits == 1)
            return 0;
        return (long)Math.pow(10, digits - 1);
    }

}
