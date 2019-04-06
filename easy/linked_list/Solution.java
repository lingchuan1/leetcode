package easy.linked_list;


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
//        ListNode p = head.next;
//        ListNode q;
//        head.next = null;
//        while(p != null){
//            q = p.next;
//            p.next = head;
//            head = p;
//            p = q ;
//        }
//        return head;
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
     * @param head
     * @return
     * @data 2019/4/5
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        while(pre.next != null && pre.next.next != null){
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

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
