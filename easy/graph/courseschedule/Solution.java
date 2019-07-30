package easy.graph.courseschedule;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

import java.util.*;

public class Solution {
    /**
     * 课程清单I 207
     *
     * @param numCourses
     * @param prerequisites
     * @return
     * @data 2019/7/18
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
       // Map<Integer, List<Integer>> graph = new HashMap();//使用map存储有向图
        ArrayList<List<Integer>> graph = new ArrayList<>();//使用list，会快点，可能是使用map用到getOrDefault()函数比较耗时？
        int[] in = new int[numCourses];//in数组表示入度
        for(int i = 0;i < numCourses;i ++){
            graph.add(new ArrayList<>());
        }
        for (int[] t : prerequisites) {
            graph.get(t[1]).add(t[0]);
            in[t[0]]++;
        }
        //bfs
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0)
                ((LinkedList<Integer>) queue).add(i);//入度为0加入队列
        }
        while (!queue.isEmpty()) {
            int num = ((LinkedList<Integer>) queue).pop();
            for (int q : graph.get(num)) {
                in[q]--;//当前节点相连的节点的入读减一
                if (in[q] == 0)
                    ((LinkedList<Integer>) queue).add(q);
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (in[i] != 0)
                return false;//如果还有入度不为0的节点，返回false
        }
        return true;
    }

    /**
     * 课程表II 210
     *
     * @param numCourses
     * @param prerequisites
     * @return
     * @data 2019/7/19
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new ArrayList<>();
        //Map<Integer, List<Integer>> graph = new HashMap<>();
        List<List<Integer>> graph = new ArrayList<>();
        int[] in = new int[numCourses];
//        for (int[] t : prerequisites) {
//            List<Integer> list = new ArrayList<>();
//            if (graph.contains(t[1]))
//                list = graph.get(t[1]);
//            list.add(t[0]);
//            graph.get(t[1]).add(t[0]);
//            in[t[0]]++;
//        }
        for(int i = 0;i < numCourses;i ++){
            graph.add(new ArrayList<>());
        }
        for(int[] t:prerequisites){
            graph.get(t[1]).add(t[0]);
            in[t[0]] ++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0)
                ((LinkedList<Integer>) queue).add(i);
        }
        while (!queue.isEmpty()) {
            int num = ((LinkedList<Integer>) queue).pop();
            res.add(num);//将当前节点加入结果集合
            for (int t : graph.get(num)) {
                in[t]--;
                if (in[t] == 0)
                    ((LinkedList<Integer>) queue).add(t);
            }
        }
        int[] res2 = {};
        if (res.size() == numCourses) {//将结果集合转换成数组
            res2 = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res2[i] = res.get(i);
            }
        }
        return res2;
    }

    /**
     * 找到最终的安全状态802
     *
     * @param graph
     * @return
     * @data 2019/7/19
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int[] safe = new int[graph.length];
        Queue<Integer> queue = new LinkedList<>();
//        Map<Integer, List<Integer>> graphMap = new HashMap<>();//graphMap存储原图
//        Map<Integer, List<Integer>> reGraph = new HashMap<>();//reGraph存储反过来的图
        List<List<Integer>> graphList = new ArrayList<>();
        List<List<Integer>> reGraph = new ArrayList<>();
        for(int i = 0;i < graph.length;i ++){
            graphList.add(new ArrayList<>());
            reGraph.add(new ArrayList<>());
        }
        //解题思路：将出度为0的节点加入队列，遍历通过reGraph将到达出度为0的节点在graphMap中去掉相应的队列中的节点，若该节点出度变为0加入队列，并将安全标志置1
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0)
                ((LinkedList<Integer>) queue).add(i);
//            List<Integer> list = new ArrayList();
//            for (int t : graph[i]) {
//                list.add(t);
//                List<Integer> list1 = new ArrayList<>();
//                if (reGraph.containsKey(t))
//                    list1 = reGraph.get(t);
//                list1.add(i);
//                reGraph.put(t, list1);
//            }
//            graphMap.put(i, list);
            for(int t:graph[i]){
                graphList.get(i).add(t);
                reGraph.get(t).add(i);
            }
        }
        while (!queue.isEmpty()) {
            int num = ((LinkedList<Integer>) queue).pop();
            safe[num] = 1;//安全标志置为1
                for (int i : reGraph.get(num)){
                    graphList.get(i).remove((Object)num);
                    if(graphList.get(i).isEmpty())
                        ((LinkedList<Integer>) queue).add(i);
                }
            }

        for(int i = 0;i < graph.length;i ++){
            if(safe[i] == 1)
                res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
