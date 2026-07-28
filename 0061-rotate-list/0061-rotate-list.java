class Solution {

    public int length(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null)
            return head;

        int n = length(head);

        k = k % n;
        if (k == 0)
            return head;

        ListNode f = head;
        ListNode s = head;

        for (int i = 1; i <= k; i++) {
            f = f.next;
        }

        while (f.next != null) {
            f = f.next;
            s = s.next;
        }

        ListNode newHead = s.next;
        s.next = null;
        f.next = head;

        return newHead;
    }
}