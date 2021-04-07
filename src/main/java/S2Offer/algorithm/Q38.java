package S2Offer.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: HB
 * @Description: 面试题38 - 字符串的排列
 *               描述: 输入一个字符串，打印出该字符串中字符的所有排列。你可以以任意顺序返回
 *               这个字符串数组，但里面不能有重复元素。
 *               Case:
 *                   Input: s = "abc"
 *                   Output: ["abc","acb","bac","bca","cab","cba"]
 *               Limit: 1 <= s 的长度 <= 8
 *               Remark:
 * @CreateDate: 21:53 2021/2/7
 */

public class Q38 {

    /**
     * @Author: HB
     * @Description: 回溯算法
     * @Date: 15:38 2021/2/8
     * @Params: null
     * @Returns:
    */
    // 全排列问题 - 回溯算法
    // 保存结果并去重
    Set<String> res;
    // 判断字符是否被访问
    boolean[] visited;
    public String[] permutation(String s) {
        char[] chs = s.toCharArray();
        res = new HashSet<>();
        visited = new boolean[s.length()];

        // 进行DFS遍历
        dfs (chs, new StringBuilder());

        // 构建结果
        String[] ans = new String[res.size()];
        int idx = 0;
        for (String str : res)
            ans[idx++]  = str;

        return ans;
    }

    public void dfs (char[] chs, StringBuilder path) {

        // 1. 递归结束条件
        if (path.length() == chs.length) {
            res.add(path.toString());
            return;
        }

        // 2. 递进操作
        for (int i = 0; i < chs.length; i++) {
            // 若字符已访问, 则直接跳过
            if (visited[i])
                continue;

            path.append(chs[i]);
            visited[i] = true;

            dfs(chs, path);

            // 回溯 - 回溯变量(visited, path)
            visited[i] = false;
            path.deleteCharAt(path.length() - 1);
        }

        // 3. 递归返回值 - 暂无
    }


    /**
     * @Author: HB
     * @Description: 《剑指Offer》解法
     * @Date: 16:40 2021/2/8
     * @Params: null
     * @Returns:
    */
    Set<String> res2 = new HashSet<>();
    public String[] permutation2(String s) {
        char[] chs = s.toCharArray();
        dfs (chs, 0);
        // 将集合转换为字符串数组
        return res2.toArray(new String[0]);
    }

    public void dfs (char[] chs, int index) {
        // 1. 递归结束条件
        if (index == chs.length) {
            res2.add(String.valueOf(chs));
            return;
        }

        // 2. 递进操作
        // (1). index处的字符串和其后面的字符串交换
        for (int i = index; i < chs.length; i++) {
            // 交换chs[index]和chs[i]
            char temp = chs[index];
            chs[index] = chs[i];
            chs[i] = temp;

            // (2). 固定index处的字符, 递归且求解后面的全排列
            dfs(chs, index + 1);

            // 重新将chs[index] 和chs[i]交换回来
            temp = chs[index];
            chs[index] = chs[i];
            chs[i] = temp;
        }

        // 3. 递归返回值 - 暂无
    }

}
