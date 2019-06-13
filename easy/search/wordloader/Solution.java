package easy.search.wordloader;

import java.net.ServerSocket;
import java.util.*;

public class Solution {
    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。126
     * @data 2019/6/13
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
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

    public static void main(String[] args) {
       List<String> wordList = new LinkedList<>();
       wordList.add("hot");
       wordList.add("dot");
       wordList.add("dog");
       wordList.add("lot");
       wordList.add("log");
       wordList.add("cog");
       int res = new Solution().ladderLength("hit","cog",wordList);
       System.out.println(res);
    }
}
