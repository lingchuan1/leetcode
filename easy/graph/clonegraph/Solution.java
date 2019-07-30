package easy.graph.clonegraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * 克隆图133（图以邻接链表表示）
     *
     * @param node
     * @return
     * @data 2019/7/16
     */
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        HashMap<Integer,Node> map = new HashMap<>();
        return dfs(node,map);
    }
    //深度优先遍历，终止条件：为空直接返回空，已经复制了，直接返回复制节点
    private Node dfs(Node node, HashMap<Integer,Node> map){
        if(node == null)
            return null;
        if(map.containsKey(node.val))//map包含要复制的节点，不用再clone,返回已clone
            return map.get(node.val);
        Node clone = new Node(node.val,new ArrayList<>());
        map.put(node.val,clone);//把clone的节点放进map
        for(Node nei:node.neighbors){//深度优先遍历clone子节点
            clone.neighbors.add(dfs(nei,map));
        }
        return clone;
    }
}
