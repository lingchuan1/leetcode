package easy.topk;

import java.util.*;

public class Solution2 {

    /**
     * 前k个高频单词692（已找出满足的k个单词，但结果顺序可能不满足要求）
     * @data2019/5/29
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new LinkedList<>();
        if(words.length == 0 || k <= 0)
            return res;
        //创建一个TreeMap来映射字符串及其出现次数之间的关系，TreeMap添加时会默认按字母顺序排序
        TreeMap<String,Integer> map = new TreeMap<>();
        for(String word:words){
            if(!map.containsKey(word))
                map.put(word,1);
            else
                map.put(word,map.get(word) + 1);
        }
        //创建一个大小为k的小顶堆存放结果
       PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
           @Override
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
               return o1.getValue() - o2.getValue();
           }
       });
        //用小顶堆存放当前结果，遍历键值对，如果当前键值对的value值大于当前堆顶的value,说明出现频率高于堆顶元素，将当前元素替换掉堆顶元素
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            if(minHeap.size() != k){
                minHeap.add(entry);
            }else {
                if(entry.getValue() > minHeap.peek().getValue()){
                    minHeap.poll();
                    minHeap.add(entry);
                }
            }
        }

        for(Map.Entry<String,Integer> entry:minHeap){
            res.add(entry.getKey());
        }
        return res;

    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> list = s.topKFrequent(words,2);
        for(String word:list)
            System.out.println(word);
    }
}
