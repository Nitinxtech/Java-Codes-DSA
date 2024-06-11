package com.nitin;

import java.util.*;

public class StacksQues {

    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }

        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }

    public static String reverseString(String str) {
        Stack<Character> s = new Stack<>();
        for (int i=0; i<str.length(); i++) {
            s.push(str.charAt(i));
        }

        StringBuilder result = new StringBuilder("");
        while(!s.isEmpty()){
            char curr = s.pop();
            result.append(curr);
        }
        return result.toString();
    }

    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty()){
            return;
        }
        int top = s.pop();
        reverseStack(s);
        pushAtBottom(s, top);
    }

    public static void printStack(Stack<Integer> s) {
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    public static void stockSpan(int stocks[], int span[]) {
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);
        for (int i=0; i<stocks.length; i++) {
            int currPrice = stocks[i];
            while (!s.isEmpty() && currPrice > stocks[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                int prevHigh = s.peek();
                span[i] = i - prevHigh;
            }
            s.push(i);
        }
    }

    public static void nextGreaterElement() {
        Stack<Integer> s = new Stack<>();
        int arr[] = {6,8,0,1,3};
        int nxtGr[] = new int[arr.length];

        for (int i=arr.length-1; i>=0; i--) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }

            if (s.isEmpty()) {
                nxtGr[i] = -1;
            } else {
                nxtGr[i] = arr[s.peek()];
            }
            s.push(i);
        }
        for (int j=0; j<nxtGr.length; j++) {
            System.out.print(nxtGr[j] + " ");
        }
        System.out.println();
    }

    public static boolean validParanthesis(String str) {
        Stack<Character> s = new Stack<>();

        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '[' || ch == '(' || ch == '{') {
                s.push(ch);
            } else {
                if (s.isEmpty()) {
                    return false;
                }
                if ((s.peek() == '[' && ch == ']') || (s.peek() == '(' && ch == ')') || (s.peek() == '{' && ch == '}')) {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        if (s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDuplicate(String str) {
        Stack<Character> s = new Stack<>();

        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);

            //closing
            if (ch == ')') {
                int count = 0;
                while (s.peek() != '(') {
                    s.pop();
                    count++;
                }
                if (count < 1) {
                    return true; //duplicate
                } else {
                    s.pop(); //opening pair
                }
            }
            else {
                //opening
                s.push(ch);
            }
        }
        return false;
    }

    public static void maxAreaHistogram(int arr[]) { //O(n) - optimized code
        int maxArea = 0;
        int nsl[] = new int[arr.length];
        int nsr[] = new int[arr.length];

        //Calculating next smaller right O(n)
        Stack<Integer> s = new Stack<>();
        for (int i=arr.length-1; i>=0; i--) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }

            if (s.isEmpty()) {
                nsr[i] = arr.length;
            } else {
                nsr[i] = s.peek();
            }
            s.push(i);
        }

        //Calculating next smaller left O(n)
        s = new Stack<>();
        for (int i =0; i<arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                nsl[i] = -1;
            } else {
                nsl[i] = s.peek();
            }
            s.push(i);
        }

        //Finding Max Area O(n)
        for (int i=0; i<arr.length; i++) {
            int height = arr[i];
            int width = nsr[i] - nsl[i] - 1;
            int currArea = height * width;
            maxArea = Math.max(maxArea, currArea);
        }
        System.out.println("Max Area = " + maxArea);
    }


    public static void main(String[] args) {
//        Stack<Integer> s = new Stack<>();
//        s.push(1);
//        s.push(2);
//        s.push(3);
//
//        reverseStack(s);
//        printStack(s);
//
//        pushAtBottom(s, 4);

//        while (!s.isEmpty()) {
//            System.out.println(s.pop());
//        }
//        String result = reverseString("abc");
//        System.out.println(result);
//        int stocks[] = {100, 80, 60, 70, 60, 85, 100};
//        int span[] = new int[stocks.length];
//
//        stockSpan(stocks, span);
//
//        for (int i=0; i<span.length; i++){
//            System.out.print(span[i] + " ");
//        }



//        nextGreaterElement();

//        String str = "(({})[])";
//        System.out.println(validParanthesis(str));

//        String str = "(a-b)";
//        System.out.println(isDuplicate(str));

//        int arr[] = {2,4};
//        maxAreaHistogram(arr);


    }
}
