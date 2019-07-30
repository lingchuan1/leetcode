package easy.graph.evaluatedivision;

import java.util.*;

public class Solution {
    /**
     * 等式方程的可满足性 990
     *
     * @param equations
     * @return
     * @data 2019/7/24
     */
    public boolean equationsPossible(String[] equations) {
        ArrayList<Integer>[] graph = new ArrayList[26];
        for(int i = 0;i < 26;i ++){
            graph[i] = new ArrayList<>();
        }
        //建立图
        for(String str:equations){
            if(str.charAt(1) == '='){
                int x = str.charAt(0) - 'a';
                int y = str.charAt(3) - 'a';
                graph[x].add(y);
                graph[y].add(x);
            }
        }
        int[] color = new int[26];
        int t = 0;
        for(int start = 0;start < 26;start ++){
            if(color[start] == 0){
                t ++;//深度优先遍历，一个连通图标记上一个t值
                Stack<Integer> stack = new Stack<>();
                stack.push(start);
                while(!stack.empty()){
                    int node = stack.pop();
                    for(int cur:graph[node]){
                        if(color[cur] == 0){
                            color[cur] = t;
                            stack.push(cur);
                        }
                    }
                }
            }
        }
        for(String str:equations){
            if(str.charAt(1) == '!'){
                int x = str.charAt(0) - 'a';
                int y = str.charAt(3) - 'a';
                if(x == y || color[x] != 0 && color [x] == color[y])
                    return false;
            }
        }
        return true;
    }
}
