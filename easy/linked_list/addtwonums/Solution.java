package easy.linked_list.addtwonums;

import java.util.Stack;

public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 两数相加2
     *
     * @param l1
     * @param l2
     * @return
     * @data 2019/7/29
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        int t = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (node1 != null && node2 != null) {
            int val = (node1.val + node2.val + t) % 10;
            t = (node1.val + node2.val + t) / 10;
            ListNode node = new ListNode(val);
            cur.next = node;
            cur = node;
            node1 = node1.next;
            node2 = node2.next;
        }
        while (node1 != null) {
            int val = (node1.val + t) % 10;
            t = (node1.val + t) / 10;
            ListNode node = new ListNode(val);
            cur.next = node;
            cur = node;
            node1 = node1.next;
        }
        while (node2 != null) {
            int val = (node2.val + t) % 10;
            t = (node2.val + t) / 10;
            ListNode node = new ListNode(val);
            cur.next = node;
            cur = node;
            node2 = node2.next;
        }
        if (t != 0) {
            ListNode node = new ListNode(t);
            cur.next = node;
        }
        return dummy.next;
    }

    /**
     * 两数相加II 445
     *
     * @param l1
     * @param l2
     * @return
     * @data 2019/7/29
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while(l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int t = 0;
        ListNode l3 = null;
        while(!stack1.empty() && !stack2.empty()){
            int val1 = stack1.pop();
            int val2 = stack2.pop();
            int val = (val1 + val2 + t) % 10;
            t = (val1 + val2 + t) / 10;
            ListNode node = new ListNode(val);
            node.next = l3;
            l3 = node;
        }
        while(!stack1.empty()){
            int val = stack1.pop();
            ListNode node = new ListNode((val + t) % 10);
            t = (val + t)/10;
            node.next = l3;
            l3 = node;
        }
        while(!stack2.empty()){
            int val = stack2.pop();
            ListNode node = new ListNode((val + t) % 10);
            t = (val + t)/10;
            node.next = l3;
            l3 = node;
        }
        if(t != 0){
            ListNode node = new ListNode(t);
            node.next = l3;
            l3 = node;
        }
        return l3;
    }
}
