package com.nitin;
import java.util.ArrayList;
import java.util.Collections;

public class AssignAList {

//    public static boolean monoList(ArrayList<Integer> list){
//        boolean incFlag = true;
//        boolean decFlag = true;
//        for (int i=0; i<list.size()-1; i++){
//            if (list.get(i)>list.get(i+1)){
//                incFlag = false;
//            }
//        }
//
//        for (int i=0; i<list.size()-1; i++){
//            if (list.get(i)<list.get(i+1)){
//                decFlag = false;
//            }
//        }
//
//        if (incFlag||decFlag == true) {
//            return true;
//        }
//        return false;
//    }



    public static void lonelyNum(ArrayList<Integer> list){
        ArrayList<Integer> newList = new ArrayList<>();
        Collections.sort(list);

        //One Element Case
        if (list.size() == 1){
            newList.add(list.get(0));
        }

        // Left Boundary Case
        if (list.get(1) - list.get(0)>1){
            newList.add(list.get(0));
        }

        //Middle ArrayList Case
        for (int i=1; i<list.size()-1; i++){
            if (list.get(i+1) - list.get(i) > 1 && list.get(i) - list.get(i-1) > 1){
                newList.add(list.get(i));
            }
        }

        // Right Boundary Case
        if (list.get(list.size()-1) - list.get(list.size()-2) >1){
            newList.add(list.get(list.size()-1));
        }
        for (int i=0; i<newList.size(); i++){
            System.out.println(newList.get(i));
        }
    }

    public static void countFreq(ArrayList<Integer> list, int key) {
        int count = 0;
        int tempc = 0;
        for (int i=key+1; i<list.size(); i++){
            for (int j=key+2; j<list.size(); j++){
                if (list.get(i)==list.get(j)){
                    tempc++;
                }
                count = Math.max(count, tempc);
            }
        }
        System.out.print(count + " ");
    }
    public static void mostFreqNum(ArrayList<Integer> list, int target) {
        for (int i=0; i<list.size(); i++){
            for (int j=i+1; j<list.size(); j++) {
                if (list.get(i) != list.get(j)) {
                    countFreq(list, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(100);
        list.add(200);
        list.add(1);
        list.add(100);
//        System.out.println(monoList(list));
//        lonelyNum(list);
        mostFreqNum(list, 1);
    }
}
