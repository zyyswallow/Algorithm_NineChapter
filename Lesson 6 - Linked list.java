1.  Remove Duplicates from Sorted List II
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-list-ii/

public class Solution {
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of the linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null){ // length >= 2
            if (head.next.val == head.next.next.val){  // 先判断是否相等
                int value = head.next.val;
                while (head.next != null && head.next.val == value){
                    head.next = head.next.next;
                }
            } else {   
                head = head.next;  // 是在else语句里面
            }
        }
        return dummy.next;
    }
}

此题是除去重复了的全部项，而不是留下第一个重复项。
1. while条件不要怕写head.next.next,此时head已经往前移了一位，与之前判断的head.next==null是能衔接的。
2. 要先判断是否重复，再处理所有重复项。
3. 如果重复了，就不用在最后移动head了。


2. Reverse Linked List II 
Reverse a linked list from position m to n.
Example: Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.
http://www.lintcode.com/en/problem/reverse-linked-list-ii/

public class Solution {
    /**
     * @param ListNode head is the head of the linked list 
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m , int n) {
        // write your code
        if (m >= n || head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < m; i++){  //找到m前面那个点
            prev = prev.next;
        }
        ListNode post = prev.next;
        for (int i = 0; i < n - m + 1; i++){  //找到n前面那个点
            post = post.next;
        }
        ListNode start = prev.next;
        prev.next = reverse(start, n - m + 1);
        start.next = post;
        
        return dummy.next;
    }
    
    public ListNode reverse(ListNode head, int len){
        ListNode prev = null;
        while (len > 0){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            len--;
        }
        return prev;
    }
}

如果觉得有点乱，可以把reverse部分单独拿出来写，不像九章答案那样合起来写。
不难，但要细心。




