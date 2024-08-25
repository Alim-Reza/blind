package arraysAndHashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ContainsDuplicate {

    public HashMap<List<Integer>, Boolean> testCases() {
        List<Integer> test_1 = Arrays.asList(1,2,3,1);
        List<Integer> test_2 = Arrays.asList(1,2,3,4);
        List<Integer> test_3 = Arrays.asList(1,1,1,3,3,4,3,2,4,2);

        HashMap<List<Integer>, Boolean> listAndResult = new HashMap<>();
        listAndResult.put(test_1, true);
        listAndResult.put(test_2, false);
        listAndResult.put(test_3, true);

        return listAndResult;
    }
    public boolean solution(List<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            if(map.containsKey(i)) {
                return true;
//                map.put(i, map.get(i)+1);
            } else {
                map.put(i, 1);
            }
        }
        return false;
    }
    public static void main(String[] args) {
        ContainsDuplicate cd = new ContainsDuplicate();
        cd.testCases().forEach((key, value) -> {
            System.out.println("testCase: " + key + " passed: " + (cd.solution(key) == value));
        });
    }
}

