package easy.sort;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public String largestNumber(int[] nums) {
        String[] numsStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numsStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Long.parseLong(o2 + o1) - Long.parseLong(o1 + o2) > 0 ? 1 : -1;
            }
        });
        StringBuffer res = new StringBuffer();
        for (String s : numsStr)
            res.append(s);
        if(res.toString().startsWith("0"))
            return "0";
        return res.toString();
    }

    /**
     * 合并两个有序数组88
     * 双指针，时间复杂度O(m + n),空间复杂度O(m)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @data 2019/7/29
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] numsCopy = new int[m];
        System.arraycopy(nums1, 0, numsCopy, 0, m);
        int i = 0, j = 0, t = 0;
        while (i < m && j < n) {
            if (numsCopy[i] <= nums2[j]) {
                nums1[t] = numsCopy[i];
                i++;
            } else {
                nums1[t] = nums2[j];
                j++;
            }
            t++;
        }
        if (j < n) {
            System.arraycopy(nums2, j, nums1, t, n - j);
        }
        if (i < m) {
            System.arraycopy(numsCopy, i, nums1, t, m - i);
        }
    }

    //双指针，从后往前，时间复杂度O(m + n),空间复杂度O(1)
    public void merge2(int[] nums1,int m,int[] nums2,int n){
        int i = m;
        int j = n;
        int t = m + n;
        while(i > 0 && j > 0){
            if(nums1[i - 1] >= nums2[j - 1]){
                nums1[--t] = nums1[--i];
            }else{
                nums1[--t] = nums2[--j];
            }
        }
        if(j > 0){
            System.arraycopy(nums2,0,nums1,0,j);
        }
    }
    public static void main(String[] args) {
        String a = "10";
        String b = "2";
        System.out.println(Integer.parseInt(a + b));
    }
}
