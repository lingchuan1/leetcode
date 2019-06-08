package easy.advance.trie;

import java.util.*;

public class LongestWord {
    /**
     * 找到最长字符串720
     * @data 2019/5/29
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
//        int MaxLength = 0;
//        String res = null;
//        if(words.length == 0)
//            return res;
//        //创建哈希表存放数组
//        HashSet<String> set = new HashSet<>();
//        for(String str:words)
//            set.add(str);
//        //创建一个队列，将长度为1的字符串放进队列，作为种子
//        Queue<String> queue = new PriorityQueue<>();
//        for(String str:words){
//            if(str.length() == 1)
//                queue.add(str);
//        }


        String res = null;
        if(words.length == 0)
            return res;
        int resLength = 0;
        List<String> list = new LinkedList();
        for(String str:words)
            list.add(str);
        //先进行排序
        Collections.sort(list);
        for(String str:list){
            //字符串长度为1不作处理
            if(str.length() == 1)
                continue;
            //否则，看字符串去掉最后一个字符后的字符串是否存在，存在则更新当前结果
            String s = str.substring(0,str.length() - 1);
            if(list.contains(s)){
                if(str.length() > resLength) {
                    res = str;
                    resLength = res.length();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"w","wo","wor","worl","world"};
        LongestWord l = new LongestWord();
        System.out.println(l.longestWord(words));
    }

}
