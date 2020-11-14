package algorithm;

/**
 * @Author: HB
 * @Description: 面试题12 - 矩阵中的路径
 *               描述: 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 *               路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 *               如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 *               例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径
 *                   [["a","b","c","e"],
 *                    ["s","f","c","s"],
 *                    ["a","d","e","e"]]
 *               但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
 *               路径不能再次进入这个格子
 *               Case:
 *               Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 *               Output: true
 *               Limit: 1 <= board.length <=200
 *                      1 <= board[i].length <= 200
 *               Remark: <LeeCode 79> 单词搜索
 * @CreateDate: 22:20 2020/11/13
 */

public class Q12 {

    /**
     * @Author: HB
     * @Description: 回溯解法一
     * @Date: 17:48 2020/11/13
     * @Params: board, word
     * @Returns:
    */
    // 查找坐标, 查找方向为上/左/下/右
    static int[][] coords = {{-1,0},{0,1},{1,0},{0,-1}};
    // 访问数组
    static boolean[][] visited;
    // 行数和列数
    static int row;
    static int col;
    public static boolean existBackTracking(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];

        // 依次从平面中的每一个节点开始遍历, 以期望得到从i,j坐标开始的符合条件的路径
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, i, j, 0, word))
                    return true;
            }
        }

        return false;
    }

    /*
     * board: 遍历矩阵
     * i, j: 坐标
     * index：字符索引
     * word：查找字符
     */
    public static boolean dfs(char[][] board, int x, int y, int index, String word) {

        // 1. 递归结束条件 - 找到了符合条件的路径
        if (index == word.length() - 1)
            return board[x][y] == word.charAt(index);

        // 2. 递进操作
        // 当前位置的字符符合
        if (board[x][y] == word.charAt(index)) {
            // 当前元素置为已访问
            visited[x][y] = true;
            for (int i = 0; i < coords.length; i++) {
                // 更新遍历位置
                int newX = coords[i][0] + x;
                int newY = coords[i][1] + y;
                // 判断边界条件是否符合条件并且元素未访问
                if ((0 <= newX && newX < row
                        && 0 <= newY && newY < col)
                        && !visited[newX][newY]) {
                    // 返回false可以继续换方向继续遍历
                    if(dfs(board, newX, newY, index + 1, word))
                        return true;
                }
            }
            // 进行回溯
            visited[x][y] = false;
        }

        // 3. 递归返回值
        return false;
    }



    /**
     * @Author: HB
     * @Description: 回溯解法二
     * @Date: 11:08 2020/11/14
     * @Params: null
     * @Returns:
    */
    // 访问数组, 判断一个数组是否被访问
    /*boolean[][] visited;*/
    // 行数和列数
    /*int row;
    int col;*/
    public static boolean existBackTracking2(char[][] board, String word) {

        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];

        // 依次从平面中的每一个节点开始遍历, 以期望得到从i,j坐标开始的符合条件的路径
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs2(board, i, j, 0, word))
                    return true;
            }
        }

        return false;
    }

    /*
     * board: 遍历矩阵
     * i, j: 坐标
     * index：字符索引
     * word：查找字符
     */
    public static boolean dfs2(char[][] board, int x, int y, int index, String word) {

        // 1. 递归结束条件 - 找到了符合条件的路径
        if (index == word.length())
            return true;

        // 2. 递进操作
        // 当前位置边界符合条件并且未被访问, 并且字符相等
        if ((0 <= x && x < row
                && 0 <= y && y < col)
                && !visited[x][y]
                && board[x][y] == word.charAt(index)) {
            // 当前元素置为已访问
            visited[x][y] = true;

            // 尝试去当前坐标的上/左/下/右位置匹配
            if(dfs(board, x - 1, y, index + 1, word)
                    || dfs(board, x, y + 1, index + 1, word)
                    || dfs(board, x + 1, y, index + 1, word)
                    || dfs(board, x, y - 1, index + 1, word))
                return true;

            // 当前坐标的四个方向都找不到合适匹配, 则回溯到上一个坐标进行查找
            visited[x][y] = false;
        }

        // 3. 递归返回值
        return false;
    }




}
