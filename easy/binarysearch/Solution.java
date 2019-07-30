package easy.binarysearch;

import java.util.Arrays;

public class Solution {
    /**
     * 在排序数组中查找元素的第一个和最后一个位置34
     *
     * @param nums
     * @param target
     * @return
     * @data 2019/7/1
     */
    public int[] searchRange(int[] nums, int target) {
//        int index = binarySearch(nums, 0, nums.length - 1, target);
//        if (index == -1)
//            return new int[]{-1, -1};
//        int left = index, right = index;
//        //寻找左边界
//        while (left >= 0 && nums[left] == target)
//            left--;
//        //寻找有边界
//        while (right < nums.length && nums[right] == target)
//            right++;
//        return new int[]{++left, --right};

        int[] res = {-1, -1};
        if (nums.length == 0)
            return res;
        int left = 0, right = nums.length - 1;
        //二分查找左边界
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        if (nums[right] != target)
            return res;
        res[0] = right;
        right = nums.length;
        //二分查找右边界
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target)
                left = mid + 1;
            else right = mid;
        }
        res[1] = --right;
        return res;
    }

    //二分查找
    private int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right)
            return -1;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            return binarySearch(nums, mid + 1, right, target);
        else return binarySearch(nums, left, mid - 1, target);
    }

    /**
     * 两个有序数组中的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     * @data 2019/7/4
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int left = (n1 + n2 + 1) / 2;
        int right = (n1 + n2 + 2) / 2;//转化为要求第k小，总数为奇数时两个结果一样
        return (getKth(nums1, 0, nums2, 0, left) + getKth(nums1, 0, nums2, 0, right)) / 2;
    }

    private int getKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (start1 >= nums1.length)
            return nums2[start2 + k - 1];
        if (start2 >= nums2.length)
            return nums1[start1 + k - 1];
        if (k == 1)
            return Math.min(nums1[start1], nums2[start2]);
        int midVal1 = (start1 + k / 2 - 1) < nums1.length ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (start2 + k / 2 - 1) < nums2.length ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;//分别求出两个数组k/2位的数
        if (midVal1 < midVal2)               //midVal1小于midVal2，说明midVal1之前的数肯定不是第k小，缩小查找范围
            return getKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);//递归查找
        else return getKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
    }

    /**
     * 加热器475
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        int n = heaters.length,res = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
//        for(int i = 0;i < houses.length;i ++){
//            int cur = houses[i];
//            while(j < n - 1 && Math.abs(heaters[j + 1] - cur) <= Math.abs(heaters[j] - cur))
//                j ++;
//            res = Math.max(res,Math.abs(heaters[j] - cur));
//        }
        for(int house:houses){
           int left = 0,right = heaters.length - 1;
           while(left < right){
               int mid = left + (right - left)/2;
               if(heaters[mid] < house)
                   left = mid + 1;
               else right = mid;
           }//找到第一个不小于house的数
           int dist1 = Math.abs(heaters[right] - house);
           int dist2 = right == 0 ? Integer.MAX_VALUE : house - heaters[right - 1];
           res = Math.max(res,Math.min(dist1,dist2));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] houses = {1,5};
        int[] heaters = {2};
       int res = new Solution().findRadius(houses,heaters);
        System.out.println(res);
    }
}
