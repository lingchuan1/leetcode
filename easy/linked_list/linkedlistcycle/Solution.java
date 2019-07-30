package easy.linked_list.linkedlistcycle;


public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 环形链表141
     *
     * @param head
     * @return
     * @data 2019/7/29
     */
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode slow = head;
        ListNode quick = head.next;
        while (quick != null && quick.next != null) {
            if (slow == quick)
                return true;
            slow = slow.next;
            quick = quick.next.next;
        }
        return false;
    }

    /**
     * 环形链表II 142
     * 快慢指针，当两个指针相遇，快指针比慢指针多走一圈，起点到入环结点的距离等于快指针所在结点到入环结点的距离
     *
     * @param head
     * @return
     * @data 2019/7/29
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head.next, quick = head.next.next;
        while (quick != null && quick.next != null) {
            if (slow == quick) {
                ListNode t = head;
                while (t != quick) {
                    t = t.next;
                    quick = quick.next;
                }
                return t;
            } else {
                slow = slow.next;
                quick = quick.next.next;
            }
        }
        return null;
    }
}
