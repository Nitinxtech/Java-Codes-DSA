package com.nitin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GreedyAlgorithms {

    //Activity Selection Problem
    public static void activitySelection(int start[], int end[]) {
        int maxAct = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        //Sorting end[]
        int activities[][] = new int[start.length][3];
        for (int i=0; i<start.length; i++) {
            activities[i][0] = i;
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));

        //1st Activity
        maxAct = 1;
        ans.add(activities[0][0]);
        int lastEnd = activities[0][2];

        for (int i=0; i<end.length; i++) {
            if (activities[i][1] >= lastEnd) {
                //Activity Select
                maxAct++;
                ans.add(activities[i][0]);
                lastEnd = activities[i][2];
            }
        }
        System.out.println("Maximum Activities = " + maxAct);

        for (int i=0; i<ans.size(); i++) {
            System.out.println("A" + ans.get(i));
        }
    }

    //Fractional Knapsack Problem
    public static void fractionalKnapsack(int val[] , int weight[], int w) {
        double ratio[][] = new double[val.length][2];
        //Oth col -> Ratio, 1st col -> Ratio

        for (int i=0; i<val.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = val[i]/(double)weight[i];
        }

        Arrays.sort(ratio, Comparator.comparingDouble(o-> o[1]));

        int capacity = w;
        int finalValue = 0;
        for (int i=ratio.length-1; i>=0; i--) {
            int idx = (int)ratio[i][0];
            if (capacity >= weight[idx]) { //include full item
                finalValue += val[idx];
                capacity -= weight[idx];
            } else { //include fractional item
                finalValue += (ratio[i][1] * capacity);
                capacity = 0;
                break;
            }
        }
        System.out.println(finalValue);
    }

    //Minimum Absolute Difference Pairs Problem
    public static void minAbsoluteDifferencePairs(int A[], int B[]) {
        Arrays.sort(A);
        Arrays.sort(B);

        int minDiff = 0;

        for (int i=0; i<A.length; i++) {
            minDiff += Math.abs(A[i] - B[i]);
        }
        System.out.println(minDiff);
    }

    //Maximum Length Chain Of Pairs Problem
    public static void maxLengthChainOfPairs(int pairs[][]) {
        Arrays.sort(pairs, Comparator.comparingDouble(o -> o[1]));
        int chainLength = 1;
        int pairEnd = pairs[0][1];

        for (int i=0; i<pairs.length; i++) {
            if (pairs[i][0] > pairEnd) {
                chainLength++;
                pairEnd = pairs[i][1];
            }
        }
        System.out.println(chainLength);
    }

    //Indian Coins Problem
    public static void indianCoins(Integer coins[], int amount) {
        Arrays.sort(coins, Comparator.reverseOrder());
        int countOfCoins = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i=0; i<coins.length; i++) {
            if (coins[i] <= amount) {
                while ((coins[i] <= amount)) {
                    countOfCoins++;
                    ans.add(coins[i]);
                    amount -= coins[i];
                }
            }
        }
        System.out.println(countOfCoins);

        for (int i=0; i<ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }


    //Job Sequencing Problem
    static class Job {
        int deadline;
        int profit;
        int id;

        public Job(int i, int d, int p) {
            id = i;
            deadline = d;
            profit = p;
        }
    }

    public static void jobSequencing(int jobsInfo[][]) {
        ArrayList<Job> jobs = new ArrayList<>();

        for (int i=0; i<jobsInfo.length; i++) {
            jobs.add(new Job(i, jobsInfo[i][0], jobsInfo[i][1]));
        }
        Collections.sort(jobs, (obj1, obj2) -> obj2.profit - obj1.profit); //Descending Order profit

        ArrayList<Integer> seq = new ArrayList<>();
        int time = 0;
        for (int i=0; i<jobs.size(); i++) {
            Job curr = jobs.get(i);
            if (curr.deadline > time) {
                seq.add(curr.id);
                time++;
            }
        }

        //print sequence
        System.out.println("Total jobs scheduled = " + seq.size());
        for (int i=0; i<seq.size(); i++) {
            System.out.print(seq.get(i) + " ");
        }
    }

    //Chocola Problem
    public static void chocola(Integer costVer[], Integer costhor[]) {
        Arrays.sort(costVer, Collections.reverseOrder());
        Arrays.sort(costhor, Collections.reverseOrder());

        int h = 0 , v = 0;
        int hp = 1, vp = 1;
        int cost = 0;

        while (v < costVer.length && h < costhor.length) {
            if (costVer[v] <= costhor[h]) { //horizontal cut
                cost += (vp * costhor[h]);
                hp++;
                h++;
            } else { //vertical cut
                cost += (hp * costVer[v]);
                vp++;
                v++;
            }
        }

            while (h < costhor.length) {
                cost += (vp * costhor[h]);
                hp++;
                h++;
            }

            while (v < costVer.length) {
                cost += (hp * costVer[v]);
                vp++;
                v++;
            }
        System.out.println("Minimum cost of cuts = " + cost);
    }

    public static void main(String[] args) {
//        int start[] = {1,3,0,5,8,5};
//        int end[] = {2,4,6,7,9,9};
//        activitySelection(start, end);

//        int val[] = {60,100,120};
//        int weight[] = {10,20,30};
//        fractionalKnapsack(val, weight,50);

//        int A[] = {4,1,8,7};
//        int B[] = {2,3,6,5};
//        minAbsoluteDifferencePairs(A, B);

//        int pairs[][] = {{5,24},{39,60},{5,28},{27,40},{50,90}};
//        maxLengthChainOfPairs(pairs);

//        Integer coins[] = {10,20,50,100,500,2000};
//        int amount = 590;
//        indianCoins(coins, amount);

//        int jobsInfo[][] = {{4,20},{1,10},{1,40},{1,30}};
//        jobSequencing(jobsInfo);

//        Integer costVer[] = {2,1,3,1,4};
//        Integer costhor[] = {4,1,2};
//        chocola(costVer, costhor);
    }
}
