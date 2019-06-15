package easy.search.matrix;

import java.util.*;

public class Solution {
    int[] d[] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 砍树675
     * BFS
     * @param forest
     * @return
     * @data 2019/6/15
     */
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.get(0).size();
        int n = forest.size();
        //用优先队列存储数据，自定义排列顺序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>((a,b)->(a[2] - b[2]));
        for(int i = 0;i < n;i ++){
            for(int j = 0;j < m;j ++){
                int h = forest.get(i).get(j);
                if(h > 1){
                    priorityQueue.add(new int[]{i,j,h});
                }
            }
        }

        int totalStep = 0;
        int[] start = new int[2];
        while(!priorityQueue.isEmpty()){
            int[] target = priorityQueue.poll();
            int step = bfs(start,target,n,m,forest);
            if(step == -1)
                return -1;
            totalStep += step;
            start[0] = target[0];
            start[1] = target[1];
        }
        return totalStep;
    }

    private int bfs(int[] start,int[] target,int n,int m,List<List<Integer>> forest){
        int[] visited[] = new int[n][m];
        visited[start[0]][start[1]] = 1;
        Queue<int[]> queue = new LinkedList<>();
        ((LinkedList<int[]>) queue).add(start);
        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i < size;i ++){
                int[] cur = queue.poll();
                if(cur[0] == target[0] && cur[1] == target[1])
                    return step;
                else{
                    for(int j = 0;j < 4;j ++){
                        int newX = cur[0] + d[j][0];
                        int newY = cur[1] + d[j][1];
                        if(newX >= 0 && newX < n && newY >= 0 && newY < m && visited[newX][newY] == 0 && forest.get(newX).get(newY) >= 1){
                            ((LinkedList<int[]>) queue).add(new int[]{newX,newY});
                            visited[newX][newY] = 1;
                        }
                    }
                }
            }
            step ++;
        }
        return -1;
    }
    //*****************************************************************************************************//

    /**
     * 最短的桥934
     * @param A
     * @return
     * @data 2019/6/15 not ac
     */
    public int shortestBridge(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        boolean found = false;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0;i < n;i ++){
            for(int j = 0;j < m;j ++){
                if(!found && A[i][j] == 1){
                    found = true;
                    dfs(A,n,m,i,j);
                }
                if(found && A[i][j] == 1)
                     queue.add(new int[]{i,j});
            }
        }
        return bfs(A,queue,n,m);
    }
    //dfs区分两个岛屿
    private void dfs(int[][] A,int n,int m,int x,int y){
        if(x < 0 || y < 0 || x >= n || y >= m || A[x][y] == 0 || A[x][y] == 2)
            return;
        A[x][y] = 2;
        dfs(A,n,m,x - 1, y);
        dfs(A,n,m,x + 1, y);
        dfs(A,n,m,x , y - 1);
        dfs(A,n,m,x , y + 1);
    }
    private int bfs(int[][] A,Queue<int[]> queue,int n,int m){
        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int j = 0;j < size;j ++) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = cur[0] + d[i][0];
                    int y = cur[1] + d[i][1];
                    if (x < 0 || x >= n || y < 0 || y >= m || A[x][y] == 1)
                        continue;
                    else if (A[x][y] == 2)
                        return res;
                    else
                        queue.add(new int[]{x, y});
                }
            }
            res ++;
        }
        return 1;
    }
    public static void main(String[] args) {
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(54000090);
//        list1.add(66667789);
//        list1.add(22400000);
////        List<Integer> list2 = new ArrayList<>();
////        list2.add(0);
////        list2.add(0);
////        list2.add(4);
////        List<Integer> list3 = new ArrayList<>();
////        list3.add(7);
////        list3.add(6);
////        list3.add(5);
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(list1);
////        list.add(list2);
////        list.add(list3);
//        int res = new Solution().cutOffTree(list);
//        System.out.println(res);
        int A[][] = {{0,1,0},{0,0,0},{0,0,1}};
        System.out.println(new Solution().shortestBridge(A));
    }
}
