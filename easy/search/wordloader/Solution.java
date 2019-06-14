package easy.search.wordloader;

import java.net.ServerSocket;
import java.util.*;

public class Solution {
    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。126
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     * @data 2019/6/13
     */
    /*
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        ((LinkedList<String>) queue).add(beginWord);
        int step = 0;
        //使用队列存放当前单词，队列不为空时，遍历队列单词进行操作，BFS
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i < size;i ++){
                String cur = queue.poll();
                //若当前单词时队尾单词，找到最短，返回step+1
                if(cur.equals(endWord))
                    return ++step;
                //遍历单词列表，与队列单词比较是否可转变，可转变加入队列,并将该单词从单词列表清除
                Iterator<String> iterator = wordList.iterator();
                while(iterator.hasNext()){
                    String tem = iterator.next();
                    if(conTransform(cur,tem)){
                        iterator.remove();
                        ((LinkedList<String>) queue).add(tem);
                    }
                }
            }
            step ++;
        }
        return 0;
    }
    //判断两个单词能否转变
    private boolean conTransform(String currentWord,String tempWord){
        int size = tempWord.length();
        for(int i = 0,count = 0;i < size;i ++){
            if(currentWord.charAt(i) != tempWord.charAt(i) && ++count > 1)
                return false;
        }
        return true;
    }
*/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0 || endWord.length() == 0)
            return 0;
        //用set存储，同时可以去重
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        HashSet<String> dic = new HashSet<>(wordList);
        if (!dic.contains(endWord))
            return 0;
        start.add(beginWord);
        end.add(endWord);
        return dfs(start, end, dic, 2);
    }

    private int dfs(HashSet<String> start, HashSet<String> end, HashSet<String> dic, int l) {
        //两端查找时，任意一端出现断裂，返回0
        if (start.size() == 0)
            return 0;
        //通过两端查找提高时间效率。如果首端的长度大于尾端长度，从尾端向前查找
        if (start.size() > end.size())
            return dfs(end, start, dic, l);
        //用过的不重复使用
        dic.removeAll(start);
        HashSet<String> next = new HashSet<>();
        for (String str : start) {
            char[] arr = str.toCharArray();
            for (int i = 0; i < str.length(); i++) {
                char tem = arr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (tem == c)
                        continue;
                    arr[i] = c;
                    String nstr = new String(arr);
                    if (dic.contains(nstr)) {
                        if (end.contains(nstr))
                            return l;
                        else
                            next.add(nstr);
                    }
                }
                arr[i] = tem;
            }
        }
        return dfs(next, end, dic, l + 1);
    }

    //*****************************************************************************************************//

    /**
     * 打开转盘锁752
     *
     * @param deadends
     * @param target
     * @return
     * @data 2019/6/14
     */
//    public int openLock(String[] deadends, String target) {
//        HashSet<String> dead = new HashSet<>();
//        for (String str : deadends)
//            dead.add(str);
//        if (dead.contains("0000") || dead.contains(target))
//            return -1;
//        Queue<String> queue = new LinkedList<>();
//        HashSet<String> visited = new HashSet<>();
//        ((LinkedList<String>) queue).add("0000");
//        visited.add("0000");
//        int res = 0;
//        while (!queue.isEmpty()) {
//            res++;
//            for (int j = queue.size(); j > 0;j --)
//            {
//                String tem = queue.poll();
//                for (int i = 0; i < 4; i++) {
//                    char c = tem.charAt(i);
//                    String str1 = tem.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + tem.substring(i+1);
//                    String str2 = tem.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + tem.substring(i+1);
//                    if (str1.equals(target) || str2.equals(target))
//                        return res;
//                    else {
//                        if (!dead.contains(str1) && !visited.contains(str1))
//                            ((LinkedList<String>) queue).add(str1);
//                        if (!dead.contains(str2) && !visited.contains(str2))
//                            ((LinkedList<String>) queue).add(str2);
//                        visited.add(str1);
//                        visited.add(str2);
//                    }
//                }
//            }
//        }
//        return -1;
//    }

    public int openLock(String[] deadends,String target){
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        start.add("0000");
        end.add(target);
        HashSet<String> dead = new HashSet<>();
        for(String str:deadends)
            dead.add(str);
        if(dead.contains("0000"))
            return -1;
        HashSet<String> visited = new HashSet<>();
        return dfs2(start,end,dead,1,visited);
    }
    private int dfs2(HashSet<String> start,HashSet<String> end,HashSet<String> dead,int l,HashSet<String> visited){
        if(start.isEmpty())
            return -1;
        //如上题，通过两端查找
        if(start.size() > end.size()){
            return dfs2(end,start,dead,l,visited);
        }
        HashSet<String> next = new HashSet<>();
        for(String str:start){
            for(int i = 0;i < 4;i ++){
                char c = str.charAt(i);
                String str1 = str.substring(0,i) + (c == '9' ? 0 : c - '0' + 1) + str.substring(i + 1);
                String str2 = str.substring(0,i) + (c == '0' ? 9 : c - '0' - 1) + str.substring(i + 1);
                if(end.contains(str1) || end.contains(str2))
                    return l;
                else{
                    if(!dead.contains(str1) && !visited.contains(str1))
                        next.add(str1);
                    if(!dead.contains(str2) && !visited.contains(str2))
                        next.add(str2);
                    visited.add(str1);
                    visited.add(str2);
                }
            }
        }
        return dfs2(next,end,dead,l + 1,visited);
    }
    public static void main(String[] args) {
//        List<String> wordList = new LinkedList<>();
//        wordList.add("hot");
//        wordList.add("dot");
//        wordList.add("dog");
//        wordList.add("lot");
//        wordList.add("log");
//        wordList.add("cog");
//        int res = new Solution().ladderLength("hit", "cog", wordList);
//        System.out.println(res);

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        int res = new Solution().openLock(deadends, target);
        System.out.println(res);
    }
}
