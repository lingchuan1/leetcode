package easy.search.wordsearch;

import java.util.*;

public class Solution {
    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中79
     *
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

    //DFS，遍历周围四个方向
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
    //*****************************************************************************************************//

    /**
     * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。212
     * 回溯和字典树
     *
     * @data 2019/6/12 未通过呀
     * @param board
     * @param words
     * @return
     */
    List<String> res = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0 || words.length == 0)
            return res;
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (trie.root.next.get(board[i][j]) != null) {
                    StringBuilder s = new StringBuilder();
                    dfs(board, trie.root, i, j, visited, s);
                }
            }
        }
        HashSet<String> set = new HashSet<>(res);
        List<String> res2 = new ArrayList<>(set);
        return res2;
    }

    public void dfs(char[][] board, Trie.Node cur, int startx, int starty, boolean[][] visited, StringBuilder s) {
        if (startx < 0 || starty < 0 || startx >= board.length || starty >= board[0].length || visited[startx][starty])
            return;
        if (cur.next.size() != 0) {
            cur = cur.next.get(board[startx][starty]);
        }else return;
        visited[startx][starty] = true;
        if (cur != null) {
            s.append(board[startx][starty]);
            for (int i = 0; i < 4; i++) {
                int newx = startx + d[i][0];
                int newy = starty + d[i][1];
                dfs(board, cur, newx, newy, visited, s);
            }
            if (cur.isWord == true) {
                res.add(new String(s));
                s.delete(s.length() - 1, s.length());
                cur.isWord = false;
            }
        }
        visited[startx][starty] = false;
    }

    class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public class Node {
            boolean isWord;
            TreeMap<Character, Node> next;

            public Node() {
                isWord = false;
                next = new TreeMap<>();
            }
        }

        void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!cur.next.containsKey(c))
                    cur.next.put(c, new Node());
                cur = cur.next.get(c);
            }
            if (!cur.isWord)
                cur.isWord = true;
        }
    }

    //*****************************************************************************************************//
    public static void main(String[] args) {
        char[] board[] = {
                {'a', 'b'},
                {'a', 'a'
                }};
        String[] words = {"baa","aaa"};
        Solution s = new Solution();
        List<String> res = s.findWords(board, words);
        for (String word : res)
            System.out.println(word);
    }
}
