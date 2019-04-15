package easy.search.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    String dict[] = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();

    /**
     * 电话号码的字母组合17
     * 回溯递归
     *
     * @param digits
     * @return
     * @data 2019/4/7
     */
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty())
            return res;
        findCombination(digits, 0, "");
        return res;
    }

    private void findCombination(String digits, int index, String s) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }
        char c = digits.charAt(index);
        String letters = dict[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination(digits, index + 1, s + letters.charAt(i));
        }
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合77
     *
     * @param n
     * @param k
     * @return
     * @data 2019/4/7
     */
    List<List<Integer>> res1 = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0)
            return res1;
        List<Integer> out = new ArrayList<>();
        generateCombinations(n, k, 1, out);
        return res1;
    }

    private void generateCombinations(int n, int k, int start, List<Integer> out) {
        if (out.size() == k) {
            res1.add(new ArrayList<>(out));
            return;
        }
        //还有k-out.size()个空位，[i....n]中至少要有k-out.size()个元素
        //i最多为n-(k-out.size())+1
        for (int i = start; i <= n - (k - out.size()) + 1; i++) {
            out.add(i);
            generateCombinations(n, k, i + 1, out);
            out.remove(out.size() - 1);
        }
        return;
    }
}
