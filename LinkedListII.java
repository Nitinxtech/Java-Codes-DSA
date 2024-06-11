package com.nitin;
import java.util.LinkedList;
import java.util.Stack;

public class LinkedListII {

    public static class Node{
        int data;
        Node next;
        Node prev;

        public Node (int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){
        Node newNode = new Node(data);
        size++;

        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void addLast(int data){
        Node newNode = new Node(data);
        size++;

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void print(){
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public int removeFirst() {
        if (head == null) {
            System.out.println("Linked List empty");
            return Integer.MIN_VALUE;
        }

        if (size == 1) {
            int val = head.data;
            head = tail = null;
            size--;
            return val;
        }

        int val = head.data;
        head = head.next;
        head.prev = null;
        size--;
        return val;

    }

    public int removeLast() {
        if (head == null) {
            System.out.println("Linked List empty");
            return Integer.MIN_VALUE;
        }

        if (size == 1) {
            int val = head.data;
            head = tail = null;
            size--;
            return val;
        }
        int val = head.data;
        tail.prev.next = null;
        tail.prev = null;
        size--;
        return val;

    }

    public static boolean llIsPallindrome(Node head) {
        Node slow = head;
        boolean istrue = true;
        Stack<Integer> s = new Stack<>();
        while (slow!=null) {
            s.push(slow.data);
            slow=slow.next;
        }
        while (head!=null){
            int top =s.pop();
            if (head.data == top){
                istrue = true;
            } else {
                istrue = false;
                break;
            }
            head = head.next;
        }
        return istrue;
    }

    public static void main(String[] args) {
//        LinkedList<Integer> ll = new LinkedList<>();

//        ll.addLast(2);
//        ll.addLast(3);
//        ll.addFirst(1);
//        System.out.println(ll);
//        ll.removeFirst();
//        ll.removeLast();
//        System.out.println(ll);

        LinkedListII ll = new LinkedListII();
//        ll.addLast(1);
//        ll.addLast(2);
//        ll.addLast(1);
//        ll.addLast(2);

        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(3);
        Node six = new Node(3);
        Node seven = new Node(1);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
//        ll.print();
//        System.out.println(size);
//        ll.removeFirst();
//        ll.print();
//        System.out.println(size);
//        ll.removeLast();
//        ll.print();
//        System.out.println(size);

        System.out.println(llIsPallindrome(one));

    }
}
