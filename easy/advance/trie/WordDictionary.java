package easy.advance.trie;

import java.util.TreeMap;

/**
 * 添加与搜索单词211
 * @data 2019/4/26
 */
public class WordDictionary {
    class Node{
        boolean isWord;
        TreeMap<Character,Node> next;

        public Node(){
            isWord = false;
            next = new TreeMap<>();
        }
    }

   private Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for(int i = 0;i < word.length();i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c,new Node());
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root,word,0);
    }

    private boolean match(Node node,String word,int index){
        if(index == word.length())
            return node.isWord;
        if(word.charAt(index)!='.'){
           char c = word.charAt(index);
           if(node.next.get(c) == null)
               return false;
           return match(node.next.get(c),word,index + 1);
        }else{
            for(char nextChar:node.next.keySet()){
                return match(node.next.get(nextChar),word,index + 1);
            }
        }
        return false;
    }
}
