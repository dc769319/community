package cc.charles.community;

/**
 * @author charlesdong
 * @version 1.0
 * @description
 * @since 1.8
 */
public class LinkedListRevert {
    public class ListNode {

        ListNode next;

        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    private static ListNode revert(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public static void main(String[] args) {
        LinkedListRevert linkedListRevert = new LinkedListRevert();
        LinkedListRevert.ListNode node1 = linkedListRevert.new ListNode(1);
        LinkedListRevert.ListNode node2 = linkedListRevert.new ListNode(2);
        LinkedListRevert.ListNode node3 = linkedListRevert.new ListNode(3);
        LinkedListRevert.ListNode node4 = linkedListRevert.new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode node = revert(node1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
