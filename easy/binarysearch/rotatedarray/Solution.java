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
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            //中间的数小于最右边的数，说明右边时有序的
            if (nums[mid] < nums[right]) {
                if (nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else right = mid - 1;
            } else { //否则，左边是有序的
                if (nums[left] <= target && nums[mid] > target)
                    right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 搜索旋转排序数组II81
     * 可能包含重复元素
     *
     * @param nums
     * @param target
     * @return
     * @data 2019/7/2
     */
    public boolean search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] < nums[right]) {
                if (nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else right = mid - 1;
            } else if (nums[mid] > nums[right]) {
                if (nums[mid] > target && nums[left] <= target)
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (nums[mid] == nums[right]) { //与上题区别，可能有重复元素，当nums[mid] == nums[right],无法判断哪边是有序的，让右边界减一继续判断
                right--;
            }
        }
        return false;
    }

    /**
     * 寻找旋转数组的最小值153
     *
     * @param nums
     * @return
     * @data 2019/7/2
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //将中间指针和右边指针比较
            if (nums[mid] < nums[right])
                right = mid;
            else
                left = mid + 1;
        }
        return nums[left];
    }

    /**
     * 寻找旋转数组的最小值(可能有重复数字)154
     *
     * @param nums
     * @return
     * @data 2019/7/3
     */
    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right])//不知道左边有序还是右边有序，将有边界减1
                right--;
            else if (nums[mid] < nums[right])//右边界有序，最小值一定在[left,mid]
                right = mid;
            else if (nums[mid] > nums[right])//左边界有序，最小值一定在[mid,right]
                left = mid + 1;
        }
        return nums[left];
    }

    /**
     * 寻找峰值162
     *
     * @param nums
     * @return
     * @data 2019/7/3
     */
    public int findPeakElement(int[] nums) {
//        int n = nums.length;
//        if (n == 1)
//            return 0;
//        if (nums[0] > nums[1])
//            return 0;
//        for (int i = 1; i < n - 1; i++) {
//            if (nums[i] > nums[i + 1])
//                return i;
//        }
//        return n - 1;
        //二分查找
        int left = 0,right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < nums[mid + 1])
                left = mid + 1;
            else right = mid;
        }
        return right;
    }
}
