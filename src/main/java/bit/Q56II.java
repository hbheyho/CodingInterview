package bit;

/**
 * @Author: HB
 * @Description: 面试题56II - 数组中数字出现的次数 II
 *               描述: 在一个数组 nums 中除一个数字只出现一次之外，
 *               其他数字都出现了三次。请找出那个只出现一次的数字。
 *               Case:
 *                   Input: nums = [3,4,3,3]
 *                   Output: 4
 *               Limit: 1 <= nums.length <= 10000
 *                      1 <= nums[i] < 2^31
 *               Remark:
 * @CreateDate: 11:37 2021/2/18
 */

public class Q56II {

    /**
     * @Author: HB
     * @Description: 位元素解法
     * @Date: 11:38 2021/2/18
     * @Params: null
     * @Returns:
    */
    // 一个数字如果出现了三次, 那么其二进制表示的每一位(0/1)也出现了三次, 基于此,
    // 将数组中所有数字的二进制表示的每一位都加起来, 如果某一位的和能被3整除,
    // 那么那么只出现一次的数字的二进制中对应的那一位为0, 否则为1
    public int singleNumber(int[] nums) {

        int[] bits = new int[32];
        for (int i = 0; i < nums.length; i++) {
            computeBits(bits, nums[i]);
        }

        int res = 0;
        // 将二进制转换位十进制
        // for (int i = 0; i < 32; i++) {
        //     if (bits[i] % 3 != 0)
        //         res += Math.pow(2,i);
        // }

        // 将二进制转换为十进制
        for (int i = 31; i >= 0; i--) {
            res = res << 1;
            res += bits[i] % 3;
        }

        return res;
    }

    public void computeBits (int[] bits, int x) {
        for (int i = 0; i < 32; i++) {
            bits[i] += (x & 1);
            x >>= 1;
        }
    }

}
