package easy.binarysearch;

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

        int[] res = {-1,-1};
        if(nums.length == 0)
            return res;
        int left = 0,right = nums.length - 1;
        //二分查找左边界
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        if(nums[right] != target)
            return res;
        res[0] = right;
        right = nums.length;
        //二分查找右边界
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] <= target)
                left = mid + 1;
            else right = mid ;
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


    public static void main(String[] args) {
        int[] nums = {5, 6, 7, 7, 8, 8, 10};
        int[] res = new Solution().searchRange(nums, 8);
        for (int i : res)
            System.out.println(i);
    }
}
