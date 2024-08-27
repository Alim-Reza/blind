package twoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TwoSum {

    public HashMap<HashMap<List<Integer>, Integer>, List<Integer>> testCases() {
        List<Integer> inputArray = Arrays.asList(1,2,4,7,11,15);
        Integer target = 9;
        List<Integer> result = Arrays.asList(1,3);

        HashMap<List<Integer>, Integer> input = new HashMap<>();
        input.put(inputArray, target);
        HashMap<HashMap<List<Integer>, Integer>, List<Integer>> testCase = new HashMap<>();
        testCase.put(input, result);

        return testCase;
    }
    public int[] solution(int[] nums, int target) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> newArray = list.stream().sorted().toList();
        int leftPointer = 0;
        int rightPointer = nums.length - 1;
        while(leftPointer < rightPointer) {
            if((newArray.get(leftPointer) + newArray.get(rightPointer)) > target) rightPointer--;
            else if((newArray.get(leftPointer) + newArray.get(rightPointer)) < target) leftPointer++;
            else return new int[] {list.indexOf(newArray.get(leftPointer)) ,
                        list.lastIndexOf(newArray.get(rightPointer))};
        }
        return new int[]{0,0};
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
//        ts.testCases().forEach(x -> {
//            int
//            System.out.println();
//        });
        int[] res = (ts.solution(new int[]{3,3}, 6));
        System.out.println(res[0] + " , " + res[1]);
    }
}
