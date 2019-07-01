package easy.binarysearch.rotatedarray;

public class Solution {
    /**
     * 搜索旋转排序数组33
     *
     * @param nums
     * @param target
     * @return
     * @data 2019/7/1
     */
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target)
                return mid;
            //中间的数小于最右边的数，说明右边时有序的
            if(nums[mid] < nums[right]){
                if(nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else right = mid - 1;
            }else{ //否则，左边是有序的
                if(nums[left] <= target && nums[mid] > target)
                    right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;
    }
}
