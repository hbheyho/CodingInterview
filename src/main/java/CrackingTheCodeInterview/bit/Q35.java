package CrackingTheCodeInterview.bit;

/**
 * @Author: HB
 * @Description: 面试题05.02 - 二进制数转字符串
 *               描述: 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，
 *                    打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 *               Case:
 *               Input: 0.625
 *               Output: "0.101"
 *               Limit: 32位包括输出中的"0."这两位
 *               Remark:
 * @CreateDate: 23:35 2021/5/9
 */

public class Q35 {

    /**
     * @Author: HB
     * @Description: 模拟解法一
     * @Date: 23:37 2021/5/9
     * @Params: null
     * @Returns:
    */
    public String printBin1(double num) {
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        for (int i = 2; i < 32; i++) {

            // 当num为0.0退出
            if (num == 0.0)
                return sb.toString();

            double value = num * 2;
            // 取出整数部分作为二进制数
            int temp = (int)value;
            sb.append(temp);
            // num保存为取出整数后的剩余部分
            num = value - temp;

        }

        return "ERROR";
    }

    /**
     * @Author: HB
     * @Description: 模拟解法二
     * @Date: 23:37 2021/5/9
     * @Params: null
     * @Returns:
    */
    public String printBin2(double num) {
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        for (int i = 2; i < 32; i++) {

            // 当num为0.0退出
            if (num == 0.0)
                return sb.toString();

            double value = num * 2;
            // 取出整数部分作为二进制数
            String[] temp = String.valueOf(value).split("\\.");
            sb.append(temp[0]);
            // num保存为取出整数后的剩余部分
            num = Double.valueOf("0." + temp[1]);

        }

        return "ERROR";
    }
}
