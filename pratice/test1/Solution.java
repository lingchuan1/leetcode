package pratice.test1;

import java.util.*;

public class Solution {
    /**
     * 给定两个数组，返回两个数组中共有的元素
     * 能否只用一个set?
     * 若数组有序？
     *
     * @param nums1
     * @param nums2
     * @return
     * @data 2019/4/5
     */
    public List<Integer> getAllHave1(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i))
                resSet.add(i);
        }
        for (int i : resSet) {
            res.add(i);
        }
        return res;
    }

    /**
     * 给定两个数组，返回两个数组的交集
     *
     * @param nums1
     * @param nums2
     * @return
     * @data 2019/4/5
     */
    public List<Integer> getAllHave2(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            Integer count = map.get(nums1[i]);
            map.put(nums1[i], count == null ? 1 : count + 1);
        }
        for (int i = 0, val; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && (val = map.get(nums2[i])) > 0) {
                res.add(nums2[i]);
                map.put(nums2[i], --val);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums1[] = {1, 2, 2, 1};
        int nums2[] = {2, 2};
        List<Integer> list1 = solution.getAllHave1(nums1, nums2);
        System.out.println("list1:");
        for (int i : list1)
            System.out.println(i);
        List<Integer> list2 = solution.getAllHave2(nums1, nums2);
        System.out.println("list2:");
        for (int i : list2)
            System.out.println(i);
    }
}
