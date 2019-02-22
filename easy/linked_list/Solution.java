package easy.linked_list;


public class Solution {
    /**
     * 移除链表元素
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        while(head != null&&head.val == val) head = head.next;
        if(head == null) return null;
        ListNode p;
        p = head;
        while(p.next != null){
            if(p.next.val == val){
                ListNode q = p.next;
                p.next = q.next;
                q.next = null;
            }else
                p = p.next;
        }
        return head;
    }

    /**
     * 删除链表中的结点
     * @date 2019/2/15
     * node是链表的一部分，且node不在链表尾
     * 因为无法直接删去node结点，将node下一结点的值复制给node，删除node的下一结点
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 反转链表
     * @date 2019/2/17
     * 头插法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode p = head.next;
        ListNode q;
        head.next = null;
        while(p != null){
            q = p.next;
            p.next = head;
            head = p;
            p = q ;
        }
        return head;
    }

    /**
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
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
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
