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


3. Partition List
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.
For example, Given 1->4->3->2->5->2->null and x = 3, return 1->2->2->4->3->5->null.
http://www.lintcode.com/en/problem/partition-list/
 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode 
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        ListNode leftDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode rightDummy = new ListNode(0);
        ListNode right = rightDummy;
        while (head != null){
            if (head.val < x){
                left.next = head;
                left = left.next;
                //left.next = null;   不能立即把next置为null
            } else {
                right.next = head;
                right = right.next;
                //right.next = null;
            }
            head = head.next;
        }
        left.next = rightDummy.next;
        right.next = null;   // 要在最后把链表最后置为null
        return leftDummy.next;
        
    }
}

while循环中，left与right后面链上了原链表，如果立即置为null，会导致原链表也一同置为null。


4. Sort List
Sort a linked list in O(n log n) time using constant space complexity.
Example: Given 1-3->2->null, sort it to 1->2->3->null.
http://www.lintcode.com/en/problem/sort-list/


5. Reorder List
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
You must do this in-place without altering the nodes` values.
http://www.lintcode.com/en/problem/reorder-list/#

public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: void
     */
    public void reorderList(ListNode head) {  
        // write your code here
        if (head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = reverse(slow.next);
        slow.next = null;
        merge(head, right);
    }
    
    public ListNode reverse(ListNode node){
        ListNode prev = null;
        while (node != null){
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
    
    public void merge(ListNode left, ListNode right){
        while (left.next != null && right != null){   // left.next不为null是为了处理left较短的情况。
            ListNode temp = left.next;                // 但这里是把right合进left，不需要用right.next。否则会出错。比如，right链表只有一个节点。
            left.next = right;
            right = right.next;
            left.next.next = temp;
            left = left.next.next;
        }
        if (left.next == null){
            left.next = right;
        }
    }
}
先找中点，再reverse右半段链表，最后merge。
九章的做法中，merge方法内用的是dummy node。但我把left作为主段，把right的节点合进left中，写法上简单一些。


6. Merge k Sorted Lists
Merge k sorted linked lists and return it as one sorted list.
http://www.lintcode.com/en/problem/merge-k-sorted-lists/

方法一：两两合并 Divide & Conquer -- O(logn)
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
        if (lists == null || lists.size() == 0){
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
    }
    
    public ListNode mergeHelper(List<ListNode> lists, int start, int end){
        if (start >= end){
            return lists.get(start);
        }
        int mid = start + (end - start) / 2;    // 这里不是binary search的方法，是分治法
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
    
    public ListNode mergeTwoLists(ListNode a, ListNode b){
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (a != null && b != null){
            if (a.val < b.val){
                head.next = a;
                a = a.next;
            } else {
                head.next = b;
                b = b.next;
            }
            head = head.next;
        }
        if (a == null){
            head.next = b;
        } else {
            head.next = a;
        }
        return dummy.next;
    }
}
思想是两两合并，可以用递归分治法合并，也可以用循环来控制合并。但前者更好。

方法二：PriorityQueue O(kn*logk)
public class Solution {
    public class ListNodeComparator implements Comparator<ListNode>{   // 要继承Comparator接口（没有小括号）
        public int compare(ListNode a, ListNode b){
            if (a != null && b != null){
                return a.val - b.val;
            } else if (a == null){
                return -1;
            } else {
                return 1;
            }
        }
    }  
    
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
        if (lists == null || lists.size() == 0){
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        // 使用最小堆的方法，要申明堆的大小和比较器 
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), new ListNodeComparator());
        for (int i = 0; i < lists.size(); i++){
            ListNode node = lists.get(i);
            if (node != null){    // 要筛掉null的节点！
                heap.offer(node);
            }
        }
        while (!heap.isEmpty()){
            ListNode min = heap.poll();
            head.next = min;
            head = head.next;
            if (min.next != null){
                heap.offer(min.next);
            }
        }
        return dummy.next;
    }
}


7. Copy List with Random Pointer
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.
http://www.lintcode.com/en/problem/copy-list-with-random-pointer/

public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        if (head == null){
            return null;
        }
        RandomListNode node = head;
        while (node != null){
            RandomListNode tmp = node.next;
            node.next = new RandomListNode(node.label);
            node.next.next = tmp;
            node = tmp;
        }
        node = head;
        while (node != null){
            if (node.random != null){   //不要忘记先判空哦！
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode tail = dummy;
        node = head;
        while (node != null){
            tail.next = node.next;
            tail = tail.next;
            node = node.next.next;
        }
        return dummy.next;
    }
}
三次循环，先copy节点，再给random赋值，最后把copy的节点分出来。
也可以把这三步写成三个functions


8. Convert Sorted List to Balanced BST
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
http://www.lintcode.com/en/problem/convert-sorted-list-to-balanced-bst/





9. Linked List Cycle
Given a linked list, determine if it has a cycle in it.
http://www.lintcode.com/en/problem/linked-list-cycle/

public class Solution {
    public boolean hasCycle(ListNode head) {  
        // write your code here
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            if (slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}


10. Reverse Nodes in k-Group
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
You may not alter the values in the nodes, only nodes itself may be changed. Only constant memory is allowed.
http://www.lintcode.com/en/problem/reverse-nodes-in-k-group/





