package easy.search.combinations;

import java.util.*;

public class Solution {

    String dict[] = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();

    /**
     * 电话号码的字母组合17
     * 回溯递归
     * @param digits
     * @return
     * @data 2019/4/7
     */
//    public List<String> letterCombinations(String digits) {
//        if (digits.isEmpty())
//            return res;
//        findCombination(digits, 0, "");
//        return res;
//    }
//
//    private void findCombination(String digits, int index, String s) {
//        if (index == digits.length()) {
//            res.add(s);
//            return;
//        }
//        char c = digits.charAt(index);
//        String letters = dict[c - '0'];
//        for (int i = 0; i < letters.length(); i++) {
//            findCombination(digits, index + 1, s + letters.charAt(i));
//        }
//    }
    Map<Character,String> map = new HashMap<>();
   // List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0)
            return res;
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        helper(digits,0,"");
        return res;
    }
    private void helper(String digits,int index,String out){
        if(index == digits.length()){
            res.add(out);
            return;
        }
        System.out.println(digits.charAt(index));
        System.out.println(map.get("2"));
        String str = map.get(digits.charAt(index));
        System.out.println(str);
        for(int i = 0;i < str.length();i ++){
            helper(digits,index + 1,out + str.charAt(i));
        }
    }
//*****************************************************************************************************//
    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合77
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
        //i最大为n-(k-out.size())+1
        for (int i = start; i <= n - (k - out.size()) + 1; i++) {
            out.add(i);
            generateCombinations(n, k, i + 1, out);
            out.remove(out.size() - 1);
        }
        return;
    }
//*****************************************************************************************************//
    /**
     *给定一个无重复元素的数组 candidates（正） 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的数字可以无限制重复被选取。39
     * @data 2019/5/30(未完成) 2019/6/8
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> res2 = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates.length == 0||target == 0)
            return res2;
        List<Integer> out = new ArrayList<>();
        generateCombinations(candidates,target,out,0);
        return res2;
    }

    private void generateCombinations(int[] candidates,int target,List<Integer> out,int index){
        if(out.size() != 0) {
            if (target == 0) {
                res2.add(new ArrayList<>(out));
                return;
            }
        }
        if(target < 0)
            return;
        for(int i = index;i < candidates.length; ++i){
            out.add(candidates[i]);
            generateCombinations(candidates,target - candidates[i],out,i );//可以重复，所以index传值为i
            out.remove(out.size() - 1);
        }
    }

    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。(与上题区别：数组元素可能重复，每个数字只能使用一次)40
     * @param candidates
     * @param target
     * @data 2019/6/8
     * @return
     */
    List<List<Integer>> res3 = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length == 0||target == 0)
            return res3;
        List<Integer> out = new ArrayList<>();
        Arrays.sort(candidates);
        generateCombinations2(candidates,target,out,0);
        return res3;
    }

    private void generateCombinations2(int[] candidates,int target,List<Integer> out,int index){
        if(out.size() != 0) {
            if (target == 0) {
                res3.add(new ArrayList<>(out));
                return;
            }
        }
        if(target < 0)
            return;
        for(int i = index;i < candidates.length; ++i){
            if(i > index && candidates[i] == candidates[i - 1])
                continue;
            out.add(candidates[i]);
            generateCombinations2(candidates,target - candidates[i],out,i + 1 );//不重复，所以index传值为i + 1
            out.remove(out.size() - 1);
        }
    }
    //*****************************************************************************************************//

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）78
     * @data 2019/6/8
     * @param nums
     * @return
     */
    List<List<Integer>> res4 = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> out = new ArrayList<>();
        res4.add(new ArrayList<>(out));
        if(nums.length == 0)
            return res4;
        generateSubsets(0,out,nums);
        return res4;
     }

     private void generateSubsets(int start,List<Integer> out,int[] nums){
        for(int i = start;i < nums.length;i ++){
            out.add(nums[i]);
            res4.add(new ArrayList<>(out));
            generateSubsets(i + 1,out,nums);
            out.remove(out.size() - 1);
        }
     }
    //*****************************************************************************************************//

    /**
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。90
     * @data 2019/6/10
     * @param nums
     * @return
     */
    List<List<Integer>> res6 = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0)
            return res6;
        Arrays.sort(nums);
        List<Integer> out = new ArrayList<>();
        generateSubsets2(0,out,nums);
        return res6;
    }
    private void generateSubsets2(int start,List<Integer> out,int[] nums){
        res6.add(new ArrayList<>(out));
        for(int i = start;i < nums.length;i ++){
            out.add(nums[i]);
            generateSubsets2(i + 1,out,nums);
            out.remove(out.size() - 1);
            while(i + 1 < nums.length && nums[i] == nums[i + 1])
                i++;
        }
    }
    //*****************************************************************************************************//

    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。216
     * @param k
     * @param n
     * @data 2019/6/10
     * @return
     */
    public List<List<Integer>> res5 = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if(k == 0 || n == 0)
            return res5;
        List<Integer> out = new ArrayList<>();
        generateCombinations(k,n,out,1,0);
        return res5;
    }
    private void generateCombinations(int k,int n,List<Integer> out,int index,int sum){
        if(out.size() == k){
            if(sum == n)
                res5.add(new ArrayList<>(out));
            else return;
        }
        for(int i = index;i < 10;i ++){
            out.add(i);
            generateCombinations(k,n,out,i + 1,sum + i);
            out.remove(out.size() - 1);
        }
    }
    //*****************************************************************************************************//
    /**
     * 将给定数字序列重新排列成字典序中下一个更大的排列31
     * @data 2019/4/22
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if(n <= 1)
            return;
        int t = findReverse(nums);
        if(t == n - 1){
            //如果最后两个数字是顺序的，直接调换最后两个数字的位置即得下一个最大的排列
            reverse(nums,n - 2,n - 1);
            return;
        }
        if(t == 0){
            reverse(nums,0,n - 1);
            return;
        }
        int index = findMax(nums,t - 1);
        int value = nums[index];
        nums[index] = nums[t - 1];
        nums[t - 1] = value;
        reverse(nums,t,n - 1);
    }
    //从最后一位开始查找，找到一位比它右边的数字要小
    public int findReverse(int[] nums){
        int n = nums.length;
        int i = n - 1;
        while(i > 0 && nums[i] <= nums[i - 1])
            i--;
        return i;
    }
    //反转l到r的数字序列
    public void reverse(int[] nums,int l,int r){
        int v;
        while(l < r){
            v = nums[l];
            nums[l] = nums[r];
            nums[r] = v;
            l++;
            r--;
        }
    }
    //找到nums数组中第index位数字后面第一个比第index位大的数字
    public int findMax(int[] nums,int index){
        int n = nums.length;
        for(int j = n -1;;j --){
            if(nums[j] > nums[index])
                return j;
        }
    }
    //*****************************************************************************************************//
    public static void main(String[] args) {
      new Solution().letterCombinations("23");
    }
}
