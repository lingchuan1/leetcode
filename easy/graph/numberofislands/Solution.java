package easy.graph.numberofislands;

public class Solution {
    /**
     * 朋友圈547
     *
     * @param M
     * @return
     * @data 2019/7/16
     */
    public int findCircleNum(int[][] M) {
        int n = M.length, res = 0;
        int[] visited = new int[n];//使用一个数组来表示已经访问
        for (int i = 0; i < n; i++) {
            if (visited[i] == 1)
                continue;
            helper(M, visited, i);
            res++;//调用完helper返回说明访问完一个朋友圈（访问完一个连通分量，问题本质是求有几个连通分量）
        }
        return res;
    }

    //dfs
    private void helper(int[][] M, int[] visited, int k) {
        visited[k] = 1;
        for (int i = 0; i < M[k].length; i++) {
            if (M[k][i] == 0 || visited[i] == 1)
                continue;
            helper(M, visited, i);
        }
    }

    /**
     * 岛屿数量200
     *
     * @param grid
     * @return
     * @data 2019/7/17
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length, m = grid[0].length, res = 0;
        int[] visited[] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0' || visited[i][j] == 1)
                    continue;
                helper(grid, visited, i, j);
                res++;
            }
        }
        return res;
    }

    private void helper(char[][] grid, int[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0' || visited[x][y] == 1)
            return;
        visited[x][y] = 1;
        helper(grid, visited, x + 1, y);
        helper(grid, visited, x, y + 1);
        helper(grid, visited, x - 1, y);
        helper(grid, visited, x, y - 1);
    }

    /**
     * 岛屿的最大面积695
     *
     * @param grid
     * @return
     * @data 2019/7/17
     */
    private int sum = 0;
    private int res = Integer.MIN_VALUE;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)
                    continue;
                helper(grid, i, j);
                res = Math.max(res, sum);
                sum = 0;
            }
        }
        return res == Integer.MIN_VALUE ? 0 : res;
    }

    private void helper(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0)
            return;
        sum++;
        grid[x][y] = 0;//遍历是将当前位置置为0
        helper(grid, x + 1, y);
        helper(grid, x, y + 1);
        helper(grid, x - 1, y);
        helper(grid, x, y - 1);
        return;
    }

    /**
     * 图像渲染733
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     * @data 2019/7/17
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int n = image.length,m = image[0].length;
        int color = image[sr][sc];
        if(color == newColor)
            return image;
        help(image,sr,sc,color,newColor);
        return image;
    }

    private void help(int[][] image,int x,int y,int color,int newColor){
        if(x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != color)
            return;
        image[x][y] = newColor;
        help(image,x + 1,y,color,newColor);
        help(image,x ,y + 1,color,newColor);
        help(image,x ,y - 1,color,newColor);
        help(image,x - 1,y,color,newColor);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}
        };
        System.out.println(new Solution().floodFill(grid,0,0,2));
    }


}
