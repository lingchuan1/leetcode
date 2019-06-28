package easy.dp.wordbreak;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。139
     *
     * @param s
     * @param wordDict
     * @return
     * @data 2019/6/27
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>();
        for (String word : wordDict)
            wordSet.add(word);
//        int[] memo = new int[s.length()];
//        if(check(s,wordSet,memo,0) == 1)
        if (check(s, wordSet))
            return true;
        return false;
    }

    //使用记忆数组的递归
    int check(String s, HashSet<String> wordSet, int[] memo, int start) {
        if (start >= s.length())
            return 1;
        if (memo[start] != 0)
            return memo[start];
        for (int i = start + 1; i <= s.length(); i++) {
            if (wordSet.contains(s.substring(start, i)) && check(s, wordSet, memo, i) == 1) {
                memo[start] = 1;
                return 1;
            }
        }
        memo[start] = -1;
        return -1;
    }

    //dp
    boolean check(String s, HashSet<String> wordSet) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。140
     *
     * @param s
     * @param wordDict
     * @return
     */
    List<String> res = new ArrayList<>();

    public List<String> wordBreak2(String s, List<String> wordDict) {
        StringBuffer out = new StringBuffer();
        helper(s, wordDict, 0, out);
        return res;
    }

    public void helper(String s, List<String> wordDict, int start, StringBuffer out) {

    }


    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("new");
        wordDict.add("news");
        wordDict.add("dog");
        wordDict.add("sdog");
        System.out.println(new Solution().wordBreak2("newsdog", wordDict));
    }

}
