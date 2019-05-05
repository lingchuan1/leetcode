package easy.topk;

import java.util.*;

public class Solution {
    /**
     * 前k个高频元素347
     * @data2019/5/5
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
//        List<Integer> res = new LinkedList<>();
//        if (k == 0 || nums == null)
//            return res;
//        Map<Integer, Integer> map = new TreeMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (!map.containsKey(nums[i]))
//                map.put(nums[i], 1);
//            else
//                map.put(nums[i], map.get(nums[i]) + 1);
//        }
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//        for (Map.Entry<Integer, Integer> mapping : list) {
//            res.add(mapping.getKey());
//            if(res.size() == k)
//                return res;
//        }
//        return res;
        List<Integer> res = new LinkedList<>();
        if(k == 0||nums == null)
            return res;
        //分桶法，不需要排序，利用判断来代替排序
        List<Integer>[] bucket = new ArrayList[nums.length+1];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i ++)
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);

        for(int key:map.keySet()){
            int value = map.get(key);
            if(bucket[value] == null){
                ArrayList<Integer> list = new ArrayList<>();
                bucket[value] = list;
            }
            bucket[value].add(key);
        }

        for(int i = nums.length;res.size() < k && i>0;i--){
            if(bucket[i] != null)
            res.addAll(bucket[i]);
        }
        return res;
    }
}
