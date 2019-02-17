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
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
