package com.nitin;

import java.util.ArrayList;

public class Alist {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//
//        System.out.println(list);
//        System.out.println(list.get(2));
////        System.out.println(list.remove(2));
//        int ele = list.remove(2);
//        System.out.println(list);
//        list.set(2,10);
//        System.out.println(list);
//        System.out.println(list.contains(1));
//        System.out.println(list.contains(11));
//        list.add(1,9);
//        System.out.println(list);
//        System.out.println(list.size());
//
//        for (int i=0; i<list.size(); i++){
//            System.out.println(list.get(i));
//        }
//        Collections.sort(list, Collections.reverseOrder());
//        System.out.println(list);

        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        mainList.add(list);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        mainList.add(list2);


        for (int i=0; i<mainList.size(); i++){
            ArrayList<Integer> currentList = mainList.get(i);
            for (int j=0; j<currentList.size(); j++){
                System.out.print(currentList.get(j) + " ");
            }
            System.out.println();
        }

        System.out.println(mainList);
    }

}
