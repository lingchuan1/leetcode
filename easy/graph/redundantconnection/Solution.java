package easy.graph.redundantconnection;

import java.util.*;

public class Solution {
    /**
     * 冗余连接 684(返回一条可以删去的边，使得结果图是一个有着N个节点的树，关键点是找到成环的边)
     *
     * @param edges
     * @return
     * @data 2019/7/20
     */
    /*
    public int[] findRedundantConnection(int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0;i < edges.length + 1;i ++){
            list.add(new ArrayList<>());
        }
        for(int[] t:edges){
            if(hasCycle(list,t[0],t[1],-1))//判断当前边是否会形成环，形成环返回当前边
                return t;
            list.get(t[0]).add(t[1]);
            list.get(t[1]).add(t[0]);
        }
        return new int[]{};
    }

    private boolean hasCycle(List<List<Integer>> list,int cur,int target,int post){
        if(list.get(cur).contains(target))
            return true;
        for(int i:list.get(cur)){
            if(i == post)
                continue;
            if(hasCycle(list,i,target,cur))
                return true;
        }
        return false;
    }*/

    /**
     * Union Find
     *
     * @param edges
     * @return
     * @data 2019/9/29
     */
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for (int[] edge : edges) {
            int uID = find(parent, edge[0]);
            int vID = find(parent, edge[1]);
            if (vID == uID) return edge;
            parent[uID] = vID;
        }
        return new int[]{-1, -1};
    }

    public int find(int[] parent, int i) {
        while (parent[i] != 0) {
            i = parent[i];
        }
        return i;
    }

    /**
     * K 站中转内最便宜的航班787
     * @data 2019/10/4 想了很久数据怎么存储
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n;i ++){
            graph[i] = new ArrayList<>();
        }
        for(int[] flight:flights){
            graph[flight[0]].add(new int[]{flight[1],flight[2]});
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        priorityQueue.add(new int[]{0,src,K + 1});
        while(!priorityQueue.isEmpty()){
            int[] arr = priorityQueue.poll();
            if(arr[1] == dst)
                return arr[0];
            if(arr[2] > 0){
                for(int[] ints:graph[src]){
                    priorityQueue.add(new int[]{arr[0] + ints[1],ints[0],arr[2] - 1});
                }
            }
        }
        return -1;
    }
}
