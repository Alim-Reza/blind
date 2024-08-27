package twoPointers;

import arraysAndHashing.ContainsDuplicate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ContainerWithMostWater {
    public HashMap<List<Integer>, Integer> testCases() {
        List<Integer> test1 = Arrays.asList(1,8,6,2,5,4,8,3,7);
        Integer result = 49;

        HashMap<List<Integer>, Integer> testCase = new HashMap<>();
        testCase.put(test1, result);

        return testCase;
    }

    public int maxArea(List<Integer> inp) {
//        List<Integer> inp = Arrays.stream(height).boxed().toList();
        int lp = 0;
        int rp = inp.size()-1;
        int max = 0;
        while(lp < rp) {
            int smaller = 0;
            int distance = rp-lp;

            if(inp.get(lp) > inp.get(rp)) {
                smaller = inp.get(rp);
                rp--;
            }
            else {
                smaller = inp.get(lp);
                lp++;
            }

            int container = smaller * distance;
            max = max > container ? max : container;

        }
        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater c = new ContainerWithMostWater();
        c.testCases().forEach((key, val) -> {
            System.out.println("list: " + key + " answer is: " + c.maxArea(key) + " : " + (c.maxArea(key) == val));
        });
    }
}
