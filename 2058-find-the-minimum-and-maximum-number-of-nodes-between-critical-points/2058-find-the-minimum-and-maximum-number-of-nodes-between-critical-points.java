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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int min = Integer.MAX_VALUE;
        int first = -1;
        int prevCritical = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr.next != null) {
            boolean critical = (curr.val > prev.val && curr.val > curr.next.val) ||
                               (curr.val < prev.val && curr.val < curr.next.val);

            if (critical) {
                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - prevCritical);
                }

                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (first == -1 || first == prevCritical) {
            return new int[]{-1, -1};
        }

        int max = prevCritical - first;

        return new int[]{min, max};
    }
}