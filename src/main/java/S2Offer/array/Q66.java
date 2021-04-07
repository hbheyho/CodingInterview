package S2Offer.array;

/**
 * @Author: HB
 * @Description: 面试题66 - 构建乘积数组
 *               描述: 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 *               其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 *               即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *               Case:
 *                   Input: [1,2,3,4,5]
 *                   OutPut: [120,60,40,30,24]
 *               Limit: 所有元素乘积之和不会溢出 32 位整数
 *                      a.length <= 100000
 *               Remark:
 * @CreateDate: 16:09 2021/3/26
 */

public class Q66 {

    /**
     * @Author: HB
     * @Description: 类似前缀和解法
     * @Date: 16:15 2021/3/26
     * @Params: null
     * @Returns:
    */
    // 不会出现大数问题; 不能使用除法
    // 数据长度为： 10^5
    public int[] constructArr(int[] a) {

        int n = a.length;
        if (n == 0)
            return new int[0];

        // preProduct[i]: 0 ~ i - 1乘积和
        int[] preProduct = new int[n + 1];
        // suProduct[i]: i + 1 ~ n - 1乘积和
        int[] suProduct = new int[n];

        // 初始化
        preProduct[0] = 1;
        suProduct[n - 1] = 1;

        // 计算preProduct
        for (int i = 1; i <= n; i++) {
            preProduct[i] = preProduct[i - 1] * a[i - 1];
        }

        // 计算suProduct
        for (int i = n - 2; i >= 0; i--) {
            suProduct[i] = suProduct[i + 1] * a[i + 1];
        }

        // 保存结果
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = preProduct[i] * suProduct[i];
        }

        return result;

    }
}
