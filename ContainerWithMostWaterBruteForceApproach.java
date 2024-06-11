package com.nitin;

import java.util.ArrayList;

public class ContainerWithMostWaterBruteForceApproach {


    public static int storeWater(ArrayList<Integer> container){
        int maxWater = 0;
        for (int i=0; i<container.size(); i++){
            for (int j=i+1; j<container.size(); j++){
                int height = Math.min(container.get(i), container.get(j));
                int width = j-i;
                int water = height * width;
                maxWater = Math.max(water, maxWater);
            }
        }
        return maxWater;
    }


    public static void main(String[] args) {
        ArrayList<Integer> container  = new ArrayList<>();
        container.add(1);
        container.add(8);
        container.add(6);
        container.add(2);
        container.add(5);
        container.add(4);
        container.add(8);
        container.add(3);
        container.add(7);

        System.out.println(storeWater(container));
    }
}
