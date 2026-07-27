/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteMiddle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        int length = 0;
        ListNode temp = head;

        while (temp != null) {
            length++;
            temp = temp.next;
        }

        int middle = length / 2;

        temp = head;

        for (int i = 1; i < middle; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return head;
    }
}