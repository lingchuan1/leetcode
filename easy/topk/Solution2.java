package easy.topk;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

public class Solution2 {

    /**
     * 前k个高频单词692（OK 2019/9/22）
     * O(nlgk)
     * @param words
     * @param k
     * @return
     * @data2019/5/29
     */
    public List<String> topKFrequent(String[] words, int k) {
       List<String> res = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word,map.getOrDefault(word,0) + 1);
        }
        //创建一个大小为k的小顶堆存放结果,自定义排序
        PriorityQueue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1).equals(map.get(o2)))
                    return o2.compareTo(o1);
                return map.get(o1) - map.get(o2);
            }
        });

        //用小顶堆存放当前结果
        for (String key: map.keySet()) {
            minHeap.add(key);
            if(minHeap.size() > k)
                minHeap.poll();
        }
        for(int i = 0;i < k;i++)
            res.add(minHeap.poll());
        //将结果逆序
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> list = s.topKFrequent(words, 4);
        for (String word : list)
            System.out.println(word);
    }
}
