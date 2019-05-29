package easy.search.permutations;

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
    //level记录当前已经拼的数，如果达到nums长度，说明是一次全排列，添加进结果
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
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> re = s.permute(nums);
        for(List<Integer> a:re) {
            for (int i : a) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
