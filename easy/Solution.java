package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    //第1题 两数之和
    /*
    暴力解法，时间复杂度n^2
    public int[] twoSum(int[] nums,int target){
        int i,j;
        int[] a = new int[2];
        for(i = 0;i < nums.length;i ++){
            for(j = i +1;j < nums.length;j ++){
                if(nums[i] + nums[j] == target) {
                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
        }
        return null;
    }*/
    public int[] twoSum(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            map.put(nums[i],i);
        }
        for(int j = 0;j < nums.length;j++){
            int complement = target - nums[j];
            if(map.containsKey(complement) && !map.get(complement).equals(j)){
                return new int[]{j,map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum is target");
    }


    //第20题
    public boolean isValid(String s){
        int length = s.length();
        int i = 0;
        int index = 0;
        ArrayList<String> arrayList = new ArrayList<>();
        while(i<length){
            if(arrayList.isEmpty()){
                if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{') {
                    arrayList.add(index, String.valueOf(s.charAt(i)));
                    index++;
                    i++;
                }else
                    return false;
            }else{
                String res = arrayList.get(index-1);
                if(res.equals("(")&&s.charAt(i)==')'||res.equals("{")&&s.charAt(i)=='}'||res.equals("[")&&s.charAt(i)==']'){
                    arrayList.remove(index-1);
                    index--;
                    i++;
                }else if (s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                    arrayList.add(index, String.valueOf(s.charAt(i)));
                    index++;
                    i++;
                }else return false;
            }
        }
        if(arrayList.isEmpty())
            return true;
        else return false;
    }
}

