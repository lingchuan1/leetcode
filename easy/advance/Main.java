package easy.advance;

import easy.advance.trie.WordDictionary;

import java.util.ArrayList;
import java.util.Stack;
public class Main {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList();
        if (listNode == null)
            return list;
        Stack<Integer> stack = new Stack();
        ListNode current = listNode;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        StringBuffer c = new StringBuffer("abc");

    }
}