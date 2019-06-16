package easy.search.partition;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 复原ip地址93
     * @param s
     * @return
     * @data 2019/6/16
     */
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if(s.isEmpty())
            return res;
        helper(0,s,"");
        return res;
    }
    private void helper(int k,String s,String out){
        if(k == 4) {
            if(s.isEmpty())
            res.add(out);
            return;
        }
        for(int i = 1;i < 4;i ++){
            if(s.length() < i)break;
            if(s.charAt(0) == '0') {
                helper(k + 1, s.substring(i), out + s.substring(0, i) + (k == 3 ? "" : "."));
                break;
            }
            int val = Integer.parseInt(s.substring(0,i));
            if(val > 255)
                continue;
            helper( k + 1,s.substring(i),out + s.substring(0,i) + (k == 3 ? "" : "."));
        }
    }
    //*****************************************************************************************************//

    /**
     * 分割回文串131
     * @param s
     * @return
     * @data 2019/6/16
     */
    List<List<String>> res2 = new ArrayList<>();
    public List<List<String>> partition(String s) {
        if(s.length() == 0)
            return res2;
        List<String> out = new ArrayList<>();
        helper(s,0,out);
        return res2;
    }
    //递归
    private void helper(String s,int start,List<String> out){
        int n = s.length();
        if(start == n){
            res2.add(new ArrayList<>(out));
        }
        for(int i = start;i < n;i ++){
            if(!isPalindome(s,start,i))
                continue;
            out.add(s.substring(start,i + 1));
            helper(s,i + 1,out);
            out.remove(out.size() - 1);
        }
    }
    //判断是不是回文串
    private boolean isPalindome(String s,int start,int end){
        while(start < end){
            if(s.charAt(start) != s.charAt(end))
                return false;
            start ++;
            end --;
        }
        return true;
    }
    public static void main(String[] args) {
       List<List<String>> res = new Solution().partition("aab");
       for(List<String> out:res){
           for(String s:out)
               System.out.println(s);
       }
     }
}
