package easy.topk;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中第k大元素
 *
 * @data 2019/9/22
 */
public class KthLargest {

    private PriorityQueue<Integer> queue;
    private int k;

    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        this.k = k;
        for (int i : nums) {
            if (queue.size() != k)
                queue.add(i);
            else {
                if (i > queue.peek()) {
                    queue.poll();
                    queue.add(i);
                }
            }
        }
    }

    public int add(int val) {
        if (queue.size() != k)
            queue.add(val);
        else {
            if (val > queue.peek()) {
                queue.poll();
                queue.add(val);
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {-10,1,3,1,4};
        KthLargest kthLargest = new KthLargest(3, arr);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }
}
