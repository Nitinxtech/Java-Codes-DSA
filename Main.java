package com.nitin;

import static java.util.Collections.max;

public class Main {
    static void printArr(String arr[]){
        for(int i=0; i< arr.length; i++){
            System.out.println(arr[i]);
        }
    }
    static void mergeSort(String arr[], int si, int ei) {
        int mid = (si + ei) / 2;
        if (si == ei) {
            return;
        }
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        merge(arr, si,mid, ei);
    }
    static void merge(String arr[], int si, int mid, int ei){
        String temp []= new String[ei-si+1];
        int i = si;
        int j = mid+1;
        int k= 0;
        while(i<=mid && j<=ei){
            if (arr[i].compareTo(arr[j])<0){
                temp[k]=arr[i];
                i++;
                k++;
            }
            else{
                temp[k]=arr[j];
                j++;
                k++;
            }
        }
        while(i<=mid){
            temp[k]=arr[i];
            i++;
            k++;
        }
        while(j<=ei){
            temp[k]=arr[j];
            j++;
            k++;
        }
        for(k=0; k<(ei-si+1); k++){
            arr[si+k]= temp[k];
        }

    }

    static int count(int arr[], int si, int num, int ei){
        int count=0;
        for(int i=0; i<=ei; i++){
            if (arr[i] == num){
                count++;
            }
        }
        return count;
    }
    static int max(int arr[], int si, int ei){

        if (si == ei) {
            return si;
        }

        int mid = (si+ei)/2;
        int maxleft = max(arr,si,mid);
        int maxright = max(arr,mid+1,ei);

        if(maxleft==maxright){
            return maxleft;
        }

        int countleft = count(arr,si,maxleft,ei);
        int countright = count(arr,si,maxright,ei);

        return countleft>countright?maxleft:maxright;
    }


    private static int countInRange(int[] nums, int num, int lo, int hi) {
    int count = 0;
        for (int i = lo; i <= hi; i++) {
        if (nums[i] == num) {
            count++;
        }
    }
        return count;
}
private static int majorityElementRec(int[] nums, int lo, int hi) {
// base case; the only element in an array of size 1 is the majority
// element.
    if (lo == hi) {
        return nums[lo];
    }
// recurse on left and right halves of this slice.
    int mid = (hi-lo)/2 + lo;
    int left = majorityElementRec(nums, lo, mid);
    int right = majorityElementRec(nums, mid+1, hi);
// if the two halves agree on the majority element, return it.
    if (left == right) {
        return left;
    }
// otherwise, count each element and return the "winner".
    int leftCount = countInRange(nums, left, lo, hi);
    int rightCount = countInRange(nums, right, lo, hi);
    return leftCount > rightCount ? left : right;
}
public static int majorityElement(int[] nums) {
    return majorityElementRec(nums, 0, nums.length-1);
}


        public static void main(String[] args) {
//        String arr[] = { "sun", "earth", "mars", "mercury" };
//        mergeSort(arr,0, arr.length-1);
//        printArr(arr);

        int arr[]={3,2,3};
        System.out.println(max(arr,0, arr.length-1));
        int nums[] = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));

    }
}



