package easy.search.partition;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 复原ip地址93
     *
     * @param s
     * @return
     * @data 2019/6/16
     */
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.isEmpty())
            return res;
        helper(0, s, "");
        return res;
    }

    private void helper(int k, String s, String out) {
        if (k == 4) {
            if (s.isEmpty())
                res.add(out);
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (s.length() < i) break;
            if (s.charAt(0) == '0') {
                helper(k + 1, s.substring(i), out + s.substring(0, i) + (k == 3 ? "" : "."));
                break;
            }
            int val = Integer.parseInt(s.substring(0, i));
            if (val > 255)
                continue;
            helper(k + 1, s.substring(i), out + s.substring(0, i) + (k == 3 ? "" : "."));
        }
    }
    //*****************************************************************************************************//

    /**
     * 分割回文串131
     *
     * @param s
     * @return
     * @data 2019/6/16
     */
    List<List<String>> res2 = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s.length() == 0)
            return res2;
        List<String> out = new ArrayList<>();
        helper(s, 0, out);
        return res2;
    }

    //递归
    private void helper(String s, int start, List<String> out) {
        int n = s.length();
        if (start == n) {
            res2.add(new ArrayList<>(out));
        }
        for (int i = start; i < n; i++) {
            if (!isPalindome(s, start, i))
                continue;
            out.add(s.substring(start, i + 1));
            helper(s, i + 1, out);
            out.remove(out.size() - 1);
        }
    }

    //判断是不是回文串
    private boolean isPalindome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    //*****************************************************************************************************//

    /**
     * 为运算表达式设计优先级241
     *
     * @param input
     * @return
     * @data 2019/6/17
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (input.length() == 0)
            return res;
        res = helper(input);
        return res;
    }

    private List<Integer> helper(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = helper(input.substring(0, i));
                List<Integer> right = helper(input.substring(i + 1));
                //使用foreach遍历比使用传统for循环快？
                for (int j : left) {
                    for (int k : right) {
                        if (c == '+')
                            res.add(j + k);
                        else if (c == '-')
                            res.add(j - k);
                        else if (c == '*')
                            res.add(j * k);
                        else {
                        }
                    }
                }
            }
        }
        if (res.isEmpty())
            res.add(Integer.parseInt(input));
        return res;
    }

    //*****************************************************************************************************//

    /**
     * 将字符串拆分成斐波那契数列
     *
     * @param S
     * @return
     * @data 2019/6/17
     * 未ac,溢出
     */
    List<Integer> res3 = new ArrayList<>();
    public List<Integer> splitIntoFibonacci(String S) {
        if (S.length() == 0)
            return res3;
        List<Integer> out = new ArrayList<>();
        helper(0,S,out);
        return res3;
    }

    private void helper(int start, String s, List<Integer> out) {
        if (!res3.isEmpty())
            return ;
        int n = s.length();
        if(start == n && out.size() >= 3) {
            for(int i:out)
                res3.add(i);
            return;
        }
        for (int i = start; i < n; i++) {
            String cur = s.substring(start,i + 1);
            if(cur.length() > 1 && cur.charAt(0) == '0' || cur.length() >= 10)
                break;
            int num = Integer.parseInt(cur);
            int size = out.size();
            if (size >= 2) {
                int num1 = out.get(size - 2);
                int num2 = out.get(size - 1);
                if (num1 + num2 < num)
                    break;
                if (num1 + num2 > num)
                    continue;
            }
            out.add( num);
            helper(i + 1, s, out);
            out.remove(out.size() - 1);
        }
    }

    public static void main(String[] args) {
       String input = "5511816597";
        List<Integer> res = new Solution().splitIntoFibonacci(input);
        for (int i : res)
            System.out.println(i);
    }
}
