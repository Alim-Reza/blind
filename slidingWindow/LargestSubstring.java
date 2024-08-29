package slidingWindow;

import java.util.*;

public class LargestSubstring {
    public HashMap<String, Integer> testCases() {
        HashMap<String, Integer> testCase = new HashMap<>();

        testCase.put("abcabcbb", 3);
        testCase.put("bbbbb", 1);
        testCase.put("pwwkew", 3);
        testCase.put("aab", 2);
        testCase.put("yancvbnmyo", 7);

        return testCase;
    }
    public LinkedHashSet<String> removeUntilDuplicate(LinkedHashSet<String> ts, String dup) {
        Iterator<String> iterator = ts.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            iterator.remove();  // Remove the current element
            if (element.equals(dup)) break;  // Stop if the duplicate is found
        }
        return ts;
    }
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        LinkedHashSet<String> ts = new LinkedHashSet<>();
        int maxSize = 0;
        List<String> splittedString = Arrays.stream(s.split("")).toList();
        for(String x: splittedString ){
            int sizeBefore = ts.size();
            ts.add(x);
            int sizeAfter = ts.size();

            if(sizeBefore == sizeAfter) {
                removeUntilDuplicate(ts, x);
                ts.add(x);
            } else {
                maxSize = maxSize > ts.size() ? maxSize : ts.size();
            }
        }
        return maxSize;
    }
    public static void main(String[] args) {
        LargestSubstring ls = new LargestSubstring();
        ls.testCases().forEach((key, val) -> {
            int length = ls.lengthOfLongestSubstring(key);
            System.out.println("String: " + key + " length: "+ length +  " result: " + (length == val));
        });
    }
}
