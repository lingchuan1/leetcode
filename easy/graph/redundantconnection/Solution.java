package easy.graph.redundantconnection;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 冗余连接 684
     *
     * @param edges
     * @return
     * @data 2019/7/20
     */
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
    }
}
