package easy.advance.trie;

import java.util.List;
import java.util.TreeMap;

/**
 * 替换单词648
 * @data 2019/4/27
 * 1.根据字典前缀构造前缀树
 * 2.遍历单词，若找到该单词的前缀，返回前缀，否则返回单词
 * 3.将第2步返回结果连接起来作为结果，注意空格
 */
public class Solution {
    class TrieNode{
        boolean isWord;
        TreeMap<Character,TrieNode> next;

        public TrieNode(){
            isWord = false;
            next = new TreeMap<>();
        }
    }

    private void insert(TrieNode node,String str){
        TrieNode cur = node;
        for(int i = 0;i < str.length();i ++){
            char c = str.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c,new TrieNode());
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    private String findPrefix(TrieNode root,String word){
        StringBuilder res = new StringBuilder();
        TrieNode cur = root;
        for(int i = 0;i < word.length();i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return word;
            res.append(c);
            cur = cur.next.get(c);
            if(cur.isWord)
                return res.toString();
        }
        return word;
    }

    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        //构造前缀树
        for(String str:dict)
            insert(root,str);

        String[] splited = sentence.split(" ");
//        String res = "";
//        for(String word:splited) {
//            if (!res.isEmpty())
//                res += " ";
//                res += findPrefix(root, word);
//        }
//        return res;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<splited.length; i++){
            if(i!=0) sb.append(" ");
            sb.append(findPrefix(root,splited[i]));
        }
        return sb.toString();
    }
}
