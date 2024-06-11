package com.nitin;
import java.net.Inet4Address;
import java.util.*;
import java.util.LinkedList;

public class QueuesII {

    static class StackImplementByTwoQueues{
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        public static boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        public static void add(int data) {
            if (!q1.isEmpty()) {
                q1.add(data);
            } else {
                q2.add(data);
            }
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Empty Stack");
                return -1;
            }
            int top = -1;

            //Case1
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }
            } else { //Case2
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }
            }
            return top;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Empty Stack");
                return -1;
            }
            int top = -1;

            //Case1
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }
            } else { //Case2
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }
            }
            return top;
        }
     }
    static class QueueImplementByTwoStacks{
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();

        public static boolean isEmpty() {
            return s1.isEmpty();
        }

        //add-O(n)
        public static void add(int data) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);

            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        //remove-O(1)
        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return s1.pop();
        }

        //peek-O(1)
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return s1.peek();
        }
    }

    public static void firstNonRepeatingLetter(String s) {
        int freq[] = new int[26];
        Queue<Character> q = new LinkedList<>();

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            q.add(ch);
            freq[ch-'a']++;
            while (!q.isEmpty() && freq[q.peek()-'a'] > 1) {
                q.remove();
            }
            if (q.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(q.peek());
            }
        }
    }

    public static void interLeaveQueue(Queue<Integer> q) {
        Queue<Integer> firstHalf = new LinkedList<>();
        int size = q.size();

        for (int i = 0; i < size / 2; i++) {
            firstHalf.add(q.remove());
        }

        while (!firstHalf.isEmpty()) {
            q.add(firstHalf.remove());
            q.add(q.remove());
        }
    }

    public static void queueReversal(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();
         while (!q.isEmpty()) {
             s.push(q.remove());
         }
         while (!s.isEmpty()){
             q.add(s.pop());
         }
    }

    public static void generateBinaryNumbers(int n) {

        Queue<String> q = new LinkedList<String>();
        q.add("1");
        while (n> 0) {
            String s1 = q.peek();

        q.remove();
        System.out.println(s1);
        String s2 = s1;
        q.add(s1 + "0");
        q.add(s2 + "1");
        n--;
        }
    }


    public static void reverseKelements(Deque<Integer> q, int k) {
        Deque<Integer> q2 = new LinkedList<>();
        for (int i=0; i<=k; i++) {
            q2.addFirst(q.remove());
        }
        while (!q2.isEmpty()) {
            q.addFirst(q2.removeLast());
        }
        while (!q.isEmpty()) {
            System.out.println(q.removeFirst());
        }
    }

    public static void main(String[] args) {
        Deque<Integer> q = new LinkedList<>();
//        Queue<Integer> q = new ArrayDeque<>();
//        QueueImplementByTwoStacks q = new QueueImplementByTwoStacks();
//        StackImplementByTwoQueues q = new StackImplementByTwoQueues();
        q.addLast(1);
        q.addLast(2);
        q.addLast(3);
        q.addLast(4);
        q.addLast(5);
        q.addLast(6);
        q.addLast(7);
        q.addLast(8);
        q.addLast(9);
        q.addLast(10);
//        interLeaveQueue(q);
//        queueReversal(q);
//         while (!q.isEmpty()) {
//             System.out.println(q.remove());
//         }

//        String s = "aabccxb";
//        firstNonRepeatingLetter(s);

//        generateBinaryNumbers(5);

        reverseKelements(q, 4);


    }
}
