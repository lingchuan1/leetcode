package easy.string;

import java.util.*;

public class Solution {
    /**
     * 找到字符串中所有字母异位词438
     * 使用滑动窗口
     *
     * @param s
     * @param p
     * @return
     * @data 2019/4/5
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int l = 0, r = 0;                             //窗口左右指针
        int numsDifference = p.length();             //差异度指数
        int[] asciiChar = new int[256];            //记录p中字符有哪些及其数量的数组
        for (int i = 0; i < p.length(); i++)
            asciiChar[p.charAt(i)]++;
        while (r < s.length()) {
            asciiChar[s.charAt(r)]--;               //在该字符相应位置减1
            if (asciiChar[s.charAt(r)] >= 0)        //如果加进来的字符在p中，差异度减1
                numsDifference--;
            if (r - l == p.length() - 1) {                   //如果这时窗口大小为p.length()
                if (numsDifference == 0)
                    res.add(l);                       //差异度为0时，将左窗口加到结果中
                if (asciiChar[s.charAt(l)] >= 0)       //如果将被踢出的左窗口的字符在p中，numsDifferce加1
                    numsDifference++;
                asciiChar[s.charAt(l)]++;            //数组中相应字符位置计数加回来
                l++;                                  //滑动左窗口
            }
            r++;                                      //滑动右窗口
        }
        return res;
    }

    /**
     * 判断数组nums[]中是否存在相距不超过k的i和j满足nums[i] == nums[j]219
     * 使用查找表和滑动窗口
     *
     * @param nums
     * @param k
     * @return
     * @data 2019/4/5
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                return true;
            map.put(nums[i], i);
            if (map.size() == k + 1) {
                map.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 判断数组nums[]中是否存在相距不超过k的i和j满足nums[i]和nums[j]差的绝对值小于t 220
     *
     * @param nums
     * @param k
     * @param t
     * @return
     * @data 2019/4/5
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        SortedSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            SortedSet<Long> son = set.subSet((long) nums[i] - t, (long) nums[i] + t + 1);
            if (!son.isEmpty())
                return true;
            if (i >= k)
                set.remove((long) nums[i - k]);
            set.add((long) nums[i]);
        }
        return false;
//        int n = nums.length;
//        for(int i = 0;i < n;i ++){
//            for(int j = Math.max(0,i - k);j < i ;j ++){
//                if(Math.abs((long)nums[i] - (long)nums[j]) <= t)
//                    return true;
//            }
//        }
//        return false;
    }

    /**
     * 无重复字符的最长子串3
     *
     * @param s
     * @return
     * @data2019/7/4
     */
    public int lengthOfLongestSubstring(String s) {
        int[] m = new int[256];
        Arrays.fill(m,-1);
        int res = 0, left = -1;
        //滑动窗口，left记录左边界，i是移动的右边界
        for (int i = 0; i < s.length(); i++) {
            left = Math.max(left, m[s.charAt(i)]);
            m[s.charAt(i)] = i;
            res = Math.max(res, i - left);
        }
        return res;
    }

    /**
     * 最小覆盖字串 76
     * @data 2019/11/23
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int[] arr = new int[256];
        for(int i = 0;i < t.length();i ++){
            arr[t.charAt(i)] ++;
        }
        int l = 0,r = 0;
        int index = -1,curLength = Integer.MAX_VALUE;
        int dif = t.length();
        while(r < s.length()){
            arr[s.charAt(r)] --;
            if(arr[s.charAt(r)] >= 0)
                dif --;
            if(dif == 0){//窗口包含t的全部字符串
                while(arr[s.charAt(l)] < 0){//尽可能的右移左窗口
                    arr[s.charAt(l)] ++;
                    l++;
                }
                //更新结果
                if(r - l + 1 < curLength){
                    index = l;
                    curLength = r - l + 1;
                }
                arr[s.charAt(l)] ++;
                dif ++;
                l++;
            }

            r++;
        }
        if(index == -1)
            return null;
        return s.substring(index,index + curLength);
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minWindow("ADOBECODEBANC",
                "ABC"));
    }
}
