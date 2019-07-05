package easy.binarysearch.dpbinary;

import java.util.Arrays;

public class Solution {
    /**
     * 地下城游戏174
     *
     * @param dungeon
     * @return
     * @data 2019/7/5
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];//dp数组比原数组多一行一列，表示所需最小生命值
        for (int i = 0; i < m + 1; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[m - 1][n] = 1;//最后生命值至少为1
        dp[m][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                //从最后一格逆推，这一格的生存血量是下一格所需最小的生存血量减去本格数字与1作比较，取较大值
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(new Solution().calculateMinimumHP(dungeon));
    }
}
