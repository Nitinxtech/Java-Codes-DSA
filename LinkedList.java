package com.nitin;

public class LinkedList {

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data) {
        //step1 = create new node
        Node newNode = new Node(data);
        size++;

        if (head == null) {
            head = tail = newNode;
            return;
        }

        //step2 = newNode next = head
        newNode.next = head;

        //step3 = head = newNode
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail =newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;

    }

    public void print(){
        if (head == null){
            System.out.println("LL is empty");
            return;
        }

        Node temp = head;
        while (temp!=null) {
            System.out.print(temp.data + "-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void add(int idx, int data) {
        if (idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while (i < idx-1) {
            temp = temp.next;
            i++;
        }

        //i= idx-1; temp -> prev
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst() {
        if (size == 0) {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() {
        if (size == 0) {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }

        //prev : i = size-2
        Node prev = head;
       for (int i=0; i<size-2; i++) {
           prev = prev.next;
       }

       int val = prev.next.data; //tail.data
       prev.next = null;
       tail = prev;
       size--;
       return val;
    }

    public int search(int key){
        Node temp = head;
        int i = 0;

        while (temp != null) {
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }

//Recursive search O(n)
    public int helper(Node head, int key){
        if (head == null){
            return -1;
        }

        if (head.data == key) {
            return 0;
        }
        int idx = helper(head.next, key);
        if (idx == -1) {
            return -1;
        }
        return idx+1;
    }

    public int recSearch(int key) {
        return helper(head, key);
    }

//Reverse LL
    public void reverse(){
        Node prev = null;
        Node curr = head;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void deleteNthFromEnd(int n) {

         //Calculate size
        int sz = 0;
        Node temp = head;
         while (temp != null){
             temp = temp.next;
             sz++;
         }

         if (n == sz) {
             head = head.next; //remove first
             return;
         }

         //sz-n
         int i = 1;
         int iToFInd = sz-n; //prev
         Node prev = head;
         while (i < iToFInd) {
             prev = prev.next;
             i++;
         }
         prev.next = prev.next.next;
         return;
    }

    public Node findMid(Node head) {     //Helper
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null ) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; //slow is middle
    }

    public boolean checkPallindrome() {
        if (head == null || head.next == null) {
            return true;
        }

//        Step-1 = Find Mid
        Node midNode = findMid(head);

//        Step-2 = Reverse 2nd Half
        Node prev = null;
        Node curr = midNode;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node right = prev; //Right half head
        Node left = head;

//        Step-3 = Check half and right half
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static boolean isCycle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void removeCycle(){
        Node slow = head;
        Node fast = head;
        boolean cycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }

        slow = head;
        Node prev = null;
         while (slow != fast) {
             prev = fast;
             slow = slow.next;
             fast = fast.next;
         }
         prev.next = null;
    }

    public Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; //mid
    }

    public Node merge (Node head1, Node head2) {
        Node mergeLL = new Node(-1);
        Node temp = mergeLL;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }
        }
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return mergeLL.next;
    }

    public Node mergeSort(Node head) {
        //Base Case
        if (head == null || head.next == null) {
            return head;
        }

        //Find Mid
        Node mid = getMid(head);

        //Recursive Function
        Node rightHead = mid.next;
        mid.next = null;

        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);

        //Merge
        return merge(newLeft, newRight);
    }

    public void zigZag(){

        //Find Mid
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;

        // Reverse 2md Half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node left = head;
        Node right = prev;
        Node nextL, nextR;

        //Alternate Merging - zigZag merge
        while (left != null && right !=null) {
            nextL = left.next;
            left.next = right;
            nextR = right.next;
            right.next = nextL;

            //update
            left = nextL;
            right = nextR;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        ll.addLast(6);
//        ll.add(2,9);

        ll.print();
//        System.out.println(ll.size);

//        ll.removeFirst();
//        ll.print();
//        System.out.println(ll.size);
//
//        ll.removeLast();
//        ll.print();
//        System.out.println(ll.size);
//
//        System.out.println(ll.recSearch(3));
//        System.out.println(ll.recSearch(10));

//        ll.reverse();
//        ll.print();

//        ll.deleteNthFromEnd(3);
//        ll.print();

//        System.out.println(ll.checkPallindrome());
//        head = new Node(1);
//        Node temp = new Node(2);
//        head.next = temp;
//        head.next.next = new Node(3);
//        head.next.next.next = temp;
//        System.out.println(isCycle());
//        removeCycle();
//        System.out.println(isCycle());

//        ll.head = ll.mergeSort(ll.head);
//        ll.print();

        ll.zigZag();
        ll.print();
    }
}
