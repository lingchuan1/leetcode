package easy.search.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /**
     * 给定一个没有重复数字的序列，返回其所有的全排列46
     * @param nums
     * @return
     * @data 2019/4/7
     */
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length == 0)
            return res;
        int[] visited = new int[nums.length];
        LinkedList<Integer> out = new LinkedList<Integer>();
        permuteDFS(nums,0,visited,out);
        return res;
    }
    //level记录当前已经拼的数，如果达到nums长度，说明是一次全排列，添加进结果,visited数组用于表示是否已访问过
    private void permuteDFS(int[] nums,int level,int[] visited,LinkedList<Integer> out){
        if(level == nums.length){
            res.add(new LinkedList<>(out));
            return;
        }
        for(int i = 0;i < nums.length;i ++){
            if(visited[i] == 1)
                continue;
            visited[i] = 1;
            out.add(nums[i]);
            permuteDFS(nums,level + 1,visited,out);
            out.removeLast();
            visited[i] = 0;
        }
        return;
    }
    //*****************************************************************************************************//
    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。47
     * @param nums
     * @return
     * @data 2019/6/11
     */
    List<List<Integer>> res1 = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums.length == 0)
            return res1;
        Arrays.sort(nums);
        int[] visted = new int[nums.length];
        List<Integer> out = new ArrayList<>();
        permuteUniqueDFS(nums,0,visted,out);
        return res1;
    }
    private void permuteUniqueDFS(int nums[],int level,int[] visited,List<Integer> out){
        if(level == nums.length){
            res1.add(new ArrayList<>(out));
            return;
        }
        for(int i = 0;i < nums.length;i ++){
            if(visited[i] == 1)
                continue;
            //防止有重复的排列
            if(i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)
                continue;
            out.add(nums[i]);
            visited[i] = 1;
            permuteUniqueDFS(nums,level + 1,visited,out);
            out.remove(out.size() - 1);
            visited[i] = 0;
        }
    }
    //*****************************************************************************************************//

    /**
     * 字母大小写全排列784
     * @param S
     * @return
     */
    List<String> res2 = new ArrayList<>();
    public List<String> letterCasePermutation(String s) {
        if(s.length() == 0)
            return res2;
        dfs(new StringBuilder(s),0);
        return res2;
    }
    private void dfs(StringBuilder s,int level){
        if(level == s.length()){
            res2.add(s.toString());
            return;
        }
        if(Character.isDigit(s.charAt(level))){
            dfs(s,level + 1);
        }else {
            char ch = s.charAt(level);
            s.setCharAt(level,Character.toLowerCase(ch));
            dfs(s,level + 1);
            s.setCharAt(level, Character.toUpperCase(ch));
            dfs(s,level + 1);
        }
    }

    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = {1,1,2};
        List<List<Integer>> re = s.permuteUnique(nums);
        for(List<Integer> a:re) {
            for (int i : a) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
