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

    /**
     * 输入n个整数，找出其中最小的K个数
     * 冒泡直到找到最小的k个数，停止冒泡，返回结果,O(n*k)
     * @data 2019/5/18
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input.length == 0 || k > input.length||k == 0)
            return res;
        int n = input.length;
        for(int i = 0;i < k;i ++){
            for(int j = i ;j < n - 1;j ++){
                if(input[j] < input[j + 1]){
                    int tem = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = tem;
                }
            }
        }
        for(int i = n - 1;i >= n - k;i --)
            res.add(input[i]);
        return res;
    }

    /**
     * 输入n个整数，找出其中最小的K个数
     * @data 2019/5/18
     * 前k个数的堆排序，O(nlgk)
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution1(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input.length == 0 || k > input.length||k == 0)
            return res;
        int n = input.length;
        //建大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0;i < n;i ++){
            if(maxHeap.size() != k){
                maxHeap.add(input[i]);
            }else {
                if(input[i] < maxHeap.peek()){
                    maxHeap.poll();
                    maxHeap.add(input[i]);
                }
            }
        }
        for(int i:maxHeap)
            res.add(i);
        return res;
    }
}
