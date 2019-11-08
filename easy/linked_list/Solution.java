package easy.linked_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    /**
     * 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
//        if(head == null) return null;
//        while(head != null&&head.val == val) head = head.next;
//        if(head == null) return null;
//        ListNode p;
//        p = head;
//        while(p.next != null){
//            if(p.next.val == val){
//                ListNode q = p.next;
//                p.next = q.next;
//                q.next = null;
//            }else
//                p = p.next;
//        }
//        return head;
        //使用虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
                delNode.next = null;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 删除链表中的结点
     *
     * @param node
     * @date 2019/2/15
     * node是链表的一部分，且node不在链表尾
     * 因为无法直接删去node结点，将node下一结点的值复制给node，删除node的下一结点
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     * @date 2019/2/17
     * 头插法
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //反转递归
//    public ListNode reverseList(ListNode head){
//        if(head == null || head.next == null)
//            return head;
//        ListNode node = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return node;
//    }

    /**
     * 合并两个有序链表21
     *
     * @param l1
     * @param l2
     * @return
     */
    /*
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3 = null,p,node = null;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                p = new ListNode(l2.val);
                l2 = l2.next;
            }
            if (node == null){
                node = p;
                l3 = node;
            }
            else {
                node.next = p;
                node = p;
            }
        }
        if(node != null){
            if(l1 == null )
                node.next = l2;
            if(l2 == null)
                node.next = l1;
        }
        else {
            node = (l1 == null?l2:l1);
            l3 = node;
        }
        return l3;
    }
    */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    /**
     * 判断一个链表是否是回文链表
     * 2019/3/12
     *
     * @param head
     * @return
     */
    /*
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        if (head == null || head.next == null)
            return true;
        ListNode p = head;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }
        p = head;
        while (!stack.empty()) {
            int v = stack.pop();
            if (v != p.val)
                return false;
            p = p.next;
        }
        return true;
    }
*/

    //不使用栈,空间复杂度O(1) 快慢指针
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;//慢指针每次移一个单位，快指针每次移动两个单位，快指针到达尾时，慢指针到达中间
        }
        ListNode pre = slow;
        ListNode cur = pre.next;
        while (cur != null && cur.next != null) {//对后半部分反转
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        ListNode t = head;
        slow = slow.next;
        while (slow != null) {//前半部分和反转后的后半部分比较
            if (t.val != slow.val)
                return false;
            t = t.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 反转从位置m到n的链表
     *
     * @param head
     * @param m
     * @param n
     * @return
     * @data 2019/4/5
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        for (int i = 1; i < m; i++)
            pre = pre.next;
        ListNode cur = pre.next;
        for (int i = m; i < n; i++) {
            ListNode t = cur.next;

            cur.next = t.next;
            t.next = pre.next;
            pre.next = t;
        }
        return dummy.next;
    }

    /**
     * 两两交换链表中的结点24
     *
     * @param head
     * @return
     * @data 2019/4/5
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        while (pre.next != null && pre.next.next != null) {
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;

            node1.next = node2.next;
            node2.next = node1;
            pre.next = node2;

            pre = node1;
        }
        ListNode retNode = dummy.next;
        dummy.next = null;
        return retNode;
    }

    /**
     * 相交链表160
     * 走的路程相同，两个结点有交点就会相遇
     *
     * @param headA
     * @param headB
     * @return
     * @data 2019/7/29
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            if (p1 == null)
                p1 = headB;
            else
                p1 = p1.next;
            if (p2 == null)
                p2 = headA;
            else
                p2 = p2.next;
        }
        return p1;
    }

    /**
     * 删除排序链表中的重复元素 II 82
     *
     * @param head
     * @return
     * @data 2019/7/29
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            if (cur.val != next.val) {
                pre = cur;
                cur = cur.next;
            } else {
                while (next != null && next.val == cur.val)
                    next = next.next;
                cur = next;
                pre.next = cur;
            }
        }
        return dummy.next;
    }

    /**
     * 删除链表的倒数第N个节点19
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = head, p2 = dummy;
        int i = 0;
        while (i < n) {
            p1 = p1.next;
            i++;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return dummy.next;
    }

    /**
     * 链表中的下一个更大结点1019
     * O(n^2)
     * @param head
     * @return
     * @data 2019/8/3
     */
    /*
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode cur = head;
        if(head == null)
            return new int[]{};
        while(cur != null){
            ListNode node = cur.next;
            int res = 0;
           while(node != null){
               if(node.val > cur.val){
                   res = node.val;
                   break;
               }else{
                   node = node.next;
               }
           }
           cur = cur.next;
           list.add(res);
        }
        int[] res = new int[list.size()];
        for(int i = 0;i < list.size();i ++){
            res[i] = list.get(i);
        }
        return res;
    }
    */

    /**
     * 只遍历一次 O(n)
     * @data 2019/9/20
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        Map<ListNode,Integer> map = new HashMap<>();
        ListNode node = head;
        int count = 0;
        while(node != null){
            map.put(node,0);
            node = node.next;
            count ++;
        }
        node = head;
        while(node != null){
            while(!stack.isEmpty()){
                int num = stack.peek().val;
                if(node.val > num){
                    ListNode cur = stack.pop();
                    map.put(cur,node.val);
                }else
                    break;
            }
            stack.push(node);
            node = node.next;
        }
        int[] results = new int[count];
        int i = 0;
        node = head;
        while(node != null){
            results[i] = map.get(node);
            node = node.next;
            i ++;
        }
        return results;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(7);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        int[] res = new Solution().nextLargerNodes(l1);
        for(int i : res)
            System.out.println(i);
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
