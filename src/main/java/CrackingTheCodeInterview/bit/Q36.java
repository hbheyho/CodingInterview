package CrackingTheCodeInterview.bit;

/**
 * @Author: HB
 * @Description: 面试题05.03 - 翻转数位
 *               描述: 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 *               Case:
 *               Input: num = 1775(110111011112)2
 *               Output: 8
 *               Limit:
 *               Remark:
 * @CreateDate: 10:03 2021/5/10
 */

public class Q36 {


    /**
     * @Author: HB
     * @Description: 一次迭代
     * @Date: 10:03 2021/5/10
     * @Params: null
     * @Returns:
    */
    // 模拟解法 - 一次迭代
    // currLen：记录当前长度; preLen：记录上次翻转的长度
    // 算法思路：当遇到一个0时, 且因为只能转换一次, 所以currLen的长度减去上次翻转的长度preLen
    public int reverseBits(int num) {
        int currLen = 0, preLen =  0, maxLen = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & 1) == 0) {
                currLen -= preLen;
                preLen = currLen + 1;
            }
            currLen++;
            maxLen = Math.max(maxLen, currLen);
            num >>>= 1;
        }

        return maxLen;
    }


    /**
     * @Author: HB
     * @Description: 滑动窗口解法
     * @Date: 10:04 2021/5/10
     * @Params: null
     * @Returns:
    */
    // 滑动窗口解法
    public int reverseBitsBySlideWindow(int num) {
        int[] numArr = new int[32];
        int index = 0;
        for (int i = 0; i < 32; i++) {
            numArr[index++] = num & 1;
            num >>>= 1;
        }

        // 滑动窗口找出改变一个0之后, 可以获得的连续的1的最长长度
        int j = 0, i = 0, transfer = 1, result = 0;
        while (j < 32) {

            // 扩大窗口
            while(i < 32 && (numArr[i] == 1 || (numArr[i] == 0 && transfer == 1))) {
                if (numArr[i] == 0 && transfer == 1)
                    transfer--;
                i++;
            }

            // 记录结果
            if (i - j > result)
                result = i - j;

            // 缩小窗口, 找到下一次可能得到连续最长1的位置
            while (j < 32 && numArr[j] == 1)
                j++;
            j++;

            transfer = 1;

        }

        return result;
    }
}
