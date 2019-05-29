package easy.advance.trie;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * 实现一个魔法字典676
 */
public class MagicDictionary {
//
//    class Node{
//        boolean isWord;
//        TreeMap<Character,Node> next;
//
//        public Node(){
//            isWord = false;
//            next = new TreeMap();
//        }
//    }
//    private Node root;
//    /** Initialize your data structure here. */
//    public MagicDictionary() {
//        root = new Node();
//    }
//
//    /** Build a dictionary through a list of words */
//    public void buildDict(String[] dict) {
//        Node cur;
//        for(String word:dict){
//            cur = root;
//            for(int i = 0;i < word.length();i ++){
//                if(cur.next.get(word.charAt(i))==null)
//                    cur.next.put(word.charAt(i),new Node());
//                cur = cur.next.get(word.charAt(i));
//            }
//            cur.isWord = true;
//        }
//    }
//
//    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
//    public boolean search(String word) {
//        for(int i = 0;i < word.length();i ++){
//            char c = word.charAt(i);
//            char[] arr = word.toCharArray();
//            for(char a = 'a';a <= 'z';a ++){
//                if(a == c)
//                    continue;
//                arr[i] = a;
//                if(count(arr))
//                    return true;
//            }
//            arr[i] = c;
//        }
//        return false;
//    }
//
//    /** Returns if the word is in the trie. */
//    public boolean count(char[] arr) {
//        Node cur = root;
//        for(int i = 0;i < arr.length;i ++){
//            char c = arr[i];
//            if(cur.next.get(c) == null)
//                return false;
//            cur = cur.next.get(c);
//        }
//        return cur.isWord;
//    }

    Set<String> set;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        set = new HashSet<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for(String word:dict)
            set.add(word);
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for(String arr:set){
            int dif = 0;
            if(word.length() == arr.length()){
                for(int i = 0;i < word.length();i ++){
                    if(word.charAt(i) != arr.charAt(i))
                        dif ++;
                    if(dif == 2)
                        continue;
                }
                if(dif == 1)
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        MagicDictionary m = new MagicDictionary();
        String[] dict = {"l","judge"};
        m.buildDict(dict);
        System.out.println(m.search("juage"));
    }
}
