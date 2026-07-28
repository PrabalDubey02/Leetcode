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
    public ListNode removeNthFromEnd(ListNode head, int n) {
         if (head == null || head.next == null) {
            return null;
        }

        int length = 0;
        ListNode temp1 = head;
        


        while (temp1 != null) {
            length++;
            temp1= temp1.next;
        }
        temp1 = head;
         if (length == n) {
            return head.next;
        }

        temp1 = head;
        for(int i =1;i<length-n;i++){
            temp1=temp1.next;

        }
        temp1.next = temp1.next.next;
        return head ;

        
    }
}