package easy.advance.trie;

import java.util.TreeMap;

public class MagicDictionary {
    class Node{
        boolean isWord;
        TreeMap<Character,Node> next;

        public Node(){
            isWord = false;
            next = new TreeMap();
        }
    }
    private Node root;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new Node();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        Node cur = root;
        for(String word:dict){
            for(int i = 0;i < word.length();i ++){
                if(cur.next.get(word.charAt(i))==null)
                    cur.next.put(word.charAt(i),new Node());
                cur = cur.next.get(word.charAt(i));
            }
            cur.isWord = true;
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for(int i = 0;i < word.length();i ++){
            char c = word.charAt(i);
            char[] arr = word.toCharArray();
            for(char a = 'a';a < 'z';a ++){
                if(a == c)
                    continue;
                arr[i] = a;
                if(count(arr.toString()))
                    return true;
            }
            arr[i] = c;
        }
        return false;
    }

    /** Returns if the word is in the trie. */
    public boolean count(String word) {
        Node cur = root;
        for(int i = 0;i < word.length();i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }
}
