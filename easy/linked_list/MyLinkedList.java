package easy.linked_list;

/**
 * 设计链表
 */
public class MyLinkedList {
    private ListNode head;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new ListNode(-1);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        ListNode node = head.next;
        while(node != null){
            if(index == 0)
                return node.val;
            node = node.next;
            index --;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        ListNode node = head.next;
        head.next = newNode;
        newNode.next = node;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode node = head;
        while (node.next != null){
            node = node.next;
        }
        node.next = newNode;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index < 0)
            addAtIndex(0,val);
        ListNode pre = head;
        while(index > 0 && pre.next != null){
            pre = pre.next;
            index --;
        }
        if(index == 0){
            ListNode node = new ListNode(val);
            ListNode next = pre.next;
            pre.next = node;
            node.next = next;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        ListNode pre = head;
        while(index > 0 && pre.next != null){
            index --;
            pre = pre.next;
        }
        if(index == 0 && pre.next != null){
            pre.next = pre.next.next;
        }
    }
}
