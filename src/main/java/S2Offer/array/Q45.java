package S2Offer.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: HB
 * @Description: 面试题45 - 把数组排成最小的数
 *               描述: 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，
 *               打印能拼接出的所有数字中最小的一个。
 *               Case:
 *                   Input: [3,30,34,5,9]
 *                   OutPut: "3033459"
 *               Limit: 0 < nums.length <= 100
 *               Remark:
 * @CreateDate: 15:05 2021/2/9
 */

public class Q45 {

    /**
     * @Author: HB
     * @Description: 排序解法
     * @Date: 15:06 2021/2/9
     * @Params: null
     * @Returns:
    */
    // 贪心算法 - 不可以贪心, 因为数字位数不同. 不要步入贪心陷阱
    // 定义如下排序规则：给定数字n,m, nm表示其字符串拼接
    // (1) 若nm < mn, 则表示n小于m
    // (2) 若mn < nm, 则表示m小于n
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);

        // 根据上述定义的规则进行排序
        Arrays.sort(strs, new Comparator<String>(){
            public int compare(String a, String b) {
                return (a + b).compareTo(b + a);
            }
        });

        // 构建结果
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(str);

        return sb.toString();
    }


    /**
     * @Author: HB
     * @Description: 手动实现快排
     * @Date: 15:33 2021/2/9
     * @Params: null
     * @Returns:
    */
    // 贪心算法 - 不可以贪心, 因为数字位数不同. 不要步入贪心陷阱
    // 定义如下排序规则：给定数字n,m, nm表示其字符串拼接
    // (1) 若nm < mn, 则表示n小于m
    // (2) 若mn < nm, 则表示m小于n
    public String minNumberByQuickSort(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);

        // 进行快速排序
        quickSort(strs, 0, strs.length - 1);

        // 构建结果
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(str);

        return sb.toString();
    }

    public void quickSort (String[] strs, int left, int right) {
        if (left >= right)
            return;
        String x = strs[left];
        int i = left - 1, j = right + 1;
        while (i < j) {
            // strs[i]x < xstrs[i], strs[i] < x
            do i++; while((strs[i] + x).compareTo(x + strs[i]) < 0);
            // xstrs[j] < strs[j]x, x < strs[j]
            do j--; while((x + strs[j]).compareTo(strs[j] + x) < 0);
            if (i < j) {
                String temp = strs[i];
                strs[i] = strs[j];
                strs[j] = temp;
            }
        }

        quickSort(strs, left, j );
        quickSort(strs, j + 1, right);
    }



}
