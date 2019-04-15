package easy.search.wordsearch;

public class Solution {
    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中79
     * @param board
     * @param word
     * @return
     * @data 2019/4/7
     */
    int[] d[] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0)
            return false;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (search(board, word, 0, i, j, visited))
                    return true;
        return false;
    }

    private boolean search(char[][] board, String word, int index, int startx, int starty, boolean[][] visited) {
        if (index == word.length())
            return true;
        int m = board.length, n = board[0].length;
        if (startx < 0 || starty < 0 || startx >= m || starty >= n || visited[startx][starty] || board[startx][starty] != word.charAt(index))
            return false;
        visited[startx][starty] = true;
        for (int i = 0; i < 4; i++) {
            int newx = startx + d[i][0];
            int newy = starty + d[i][1];
            if (search(board, word, index + 1, newx, newy, visited))
                return true;
        }
        visited[startx][starty] = false;
        return false;
    }

    public static void main(String[] args) {
        char[] board[] = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String words = "ABCCED";
        Solution s = new Solution();
        s.exist(board,words);
    }
}
