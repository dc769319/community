package cc.charles.community.leetCode;

import java.util.Stack;

/**
 * @author charlesdong
 * @version 1.0
 * @since 1.8
 */
public class LinkedListReverse {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode reverseHead = reverse3(node1);

        System.out.println("Head Node: " + reverseHead.val);
        ListNode curNode = reverseHead;
        while (curNode != null) {
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }

    /**
     * 借助栈结构解决问题
     *
     * @param head 头节点
     * @return 翻转后的头节点
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode curNode = head;
        while (curNode != null) {
            stack.push(curNode);
            curNode = curNode.next;
        }

        ListNode reverseHead = stack.pop();
        ListNode tempNode = reverseHead;
        while (!stack.empty()) {
            ListNode node = stack.pop();
            node.next = null;
            tempNode.next = node;
            tempNode = tempNode.next;
        }
        return reverseHead;
    }

    /**
     * 我们可以申请两个指针：
     * <p>
     * 第一个指针叫 pre，最初是指向 null 的
     * <p>
     * 第二个指针 cur 指向 head，然后不断遍历 cur
     * <p>
     * 每次迭代到 cur，都将 cur 的 next 指向 pre，然后 pre 和 cur 前进一位。
     * <p>
     * 都迭代完了(cur 变成 null 了)，pre 就是最后一个节点了。
     * <p>
     * 作者：iqqcode
     * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/ru-guo-ni-kan-wan-ping-lun-he-ti-jie-huan-you-wen-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head 头节点
     * @return 翻转后的头节点
     * @link https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/ru-guo-ni-kan-wan-ping-lun-he-ti-jie-huan-you-wen-/
     */
    public static ListNode reverse2(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            //把下一个节点暂存
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    /**
     * 递归方式解决
     *
     * @param head 头节点
     * @return 头节点指针
     */
    public static ListNode reverse3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode reverse = reverse3(next);
        next.next = head;
        head.next = null;
        return reverse;
    }
}
