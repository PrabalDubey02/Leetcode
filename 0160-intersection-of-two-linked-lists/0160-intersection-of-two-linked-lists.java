/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l1 = 0;
        int l2 = 0;
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while (temp1 != null) {
            temp1 = temp1.next;
            l1++;
        }
        while (temp2 != null) {
            temp2 = temp2.next;
            l2++;
        }
        temp1 = headA;
        temp2 = headB;
      for (int i = 1; i <= Math.abs(l1 - l2); i++) {
    if (l1 > l2) {
        temp1 = temp1.next;
    } else {
        temp2 = temp2.next;
    }
      }

       while(temp1!=temp2){
               
                 temp1 = temp1.next;
                 temp2 = temp2.next;
        }

         return temp1;
    }
    }
